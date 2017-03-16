package claudia.com.galleryphoto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private Uri filePath;

    private ProgressDialog dialog = null;
    private String imagepath = null;
    private int serverResponseCode = 0;
    private String upLoadServerUri;

    private ImageView image;
    private Button bouton;
    private Button boutonSauvegarde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bouton = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.image);
        boutonSauvegarde  = (Button) findViewById(R.id.sauvegarde);

        //Quand on clique sur le bouton Importer photo
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ouverture de l'app gallery photo
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 1);

            }
        });


        //Quand on clique sur le bouton sauvegarder
        boutonSauvegarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //upload des images vers le serveur
                dialog = ProgressDialog.show(MainActivity.this, "", "Chargement des photos...", true);
                new Thread(new Runnable() {
                    public void run() {

                        String nomFinalFichier = uploadFile(imagepath);
                        Log.i("FICHIER ", nomFinalFichier);

                    }
                }).start();
            }
        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            imagepath = getPath(filePath);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }//onActivityResult




    //Méthode qui récupère le chemin de l'image séléctionnée
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }



    //Méthode qui permet de télécharger l'image sur le serveur 
    public String uploadFile(String sourceFileUri) {


        String name = "image";
        String tag = name + ".jpg";
        String fileName = sourceFileUri.replace(sourceFileUri, tag);

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {
            dialog.dismiss();
            Log.i("uploadFile", "Le fichier source n'existe pas " + imagepath);
            return "erreur";

        } else {
            try {
                Log.i("uploadFile", "Le fichier source existe :" + imagepath);

                FileInputStream fileInputStream = new FileInputStream(sourceFile);

                //URL du fichier PHP
                upLoadServerUri = getString(R.string.URL_PHP);  //remplacer par l'url du serveur
                URL url = new URL(upLoadServerUri);

                // Connexion avec le serveur
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);
                conn.setRequestProperty("nom", "utilisateur");

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + fileName + "\"" + lineEnd);

                dos.writeBytes(lineEnd);

                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                //récupération du code réponse du serveur
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "Réponse HTTP   : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if (serverResponseCode == 200) {

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, "L'image a été téléchargée sur le serveur", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

                dialog.dismiss();
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this, "MalformedURLException", Toast.LENGTH_SHORT).show();
                    }
                });

                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);

            } catch (Exception e) {

                dialog.dismiss();
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this, "Une erreur s'est produite : vérifiez votre connexion ", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("Upload", "Exception" + e.getMessage(), e);
            }
            dialog.dismiss();
            return tag;

        }
    }


}




