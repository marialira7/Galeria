package moreira.lira.galeria;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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