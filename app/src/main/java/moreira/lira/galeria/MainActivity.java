package moreira.lira.galeria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static int RESULT_TAKE_PICTURE = 1;
    String currentPhotoPath;
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        private void dispatchTakePictureIntent() {
            File f = null;
            try {
                f = creatImageFile();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "Não foi possível criar o arquivo", Toast.LENGTH_LONG).show();
                return;
                ;
            }
        }
        currentPhotoPath = f.getAbsolutePath();

        if(f != null) {
            Uri fUri = FileProvider.getUriForFile(MainActivity.this, "moreira.lira.galeria.fileprovider", f);
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            i.putExtra(MediaStore.EXTRA_OUTPUT, fUri);
            startActivityForResult(i, RESULT_TAKE_PICTURE);
        }
        private File createImageFile() throws IOException {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp;
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File f = File.createTempFile(imageFileName, ".jpg", storageDir);
            return f;

        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == RESULT_TAKE_PICTURE) {
                if(resultCode == Activity.RESULT_OK) {
                    photos.add(currentPhotoPath); mainAdapter.notifyItemInserted(photos.size()-1);
                }
                else {
                    File f = new File(currentPhotoPath);
                    f.delete();
                }
            }
        }
        //obtendo elemento tbmain
        Toolbar toolbar = findViewById(R.id.tbMain);
        //dizendo para o mainactivity que agr o tbmain é actionbar
        setSupportActionBar(toolbar);

        //obtendo elemento tbphoto
        Toolbar toolbar = findViewById(R.id.tbPhoto);
        //dizendo para o mainactivity que agr o tbphoto é actionbar
        setSupportActionBar(toolbar);
    }

    private void setSupportActionBar(Toolbar toolbar) {

    }
    @Override
    //criando as opções do menu definidas no arquivo/adiciona as definições no menu da activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_tb, menu);
        return true;
    }
    @Override
    //toda vez que o item no toolbar for selecionado sera executado o codigo que abre a camera
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
        case R.id.opCamera:
            dispatchTakePictureIntent();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}
