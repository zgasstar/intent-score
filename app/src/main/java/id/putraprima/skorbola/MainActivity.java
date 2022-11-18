package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;
    private ImageView home, away;
    private EditText h,a;
    private Button click;
    private Uri logohome;
    private  Uri logoaway;
    private boolean change_img_home = false;
    private boolean change_img_away = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity

        home = findViewById(R.id.home_logo);
        away = findViewById(R.id.away_logo);
        h = findViewById(R.id.home_team);
        a = findViewById(R.id.away_team);
        click = findViewById(R.id.btn_team);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (h.getText().toString().isEmpty()){
                    h.setError("Home Team is empty!");
                } else if (a.getText().toString().isEmpty()){
                    a.setError("Away Team is empty!");
                } else  if (!change_img_home){
                    Toast.makeText(MainActivity.this, "Image " + h.getText().toString() + " must be change", Toast.LENGTH_SHORT).show();
                } else  if (!change_img_away){
                    Toast.makeText(MainActivity.this, "Image " + a.getText().toString() + " must be change", Toast.LENGTH_SHORT).show();
                } else {

                    Intent pindah = new Intent(MainActivity.this, MatchActivity.class);
                    pindah.putExtra("home", h.getText().toString());
                    pindah.putExtra("away", a.getText().toString());
                    pindah.putExtra("homeimg", logohome.toString());
                    pindah.putExtra("awayimg", logoaway.toString());
                    startActivity(pindah);
                }
            }
        });



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), HOME_REQUEST_CODE);
            }
        });

        away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AWAY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            Log.d(TAG, "Dicancel");
            return;
        }
        else if (requestCode == HOME_REQUEST_CODE){
            if(data != null){
                try {
                    change_img_home = true;
                    Uri imageUri = data.getData();
                    logohome = imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    home.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "cant load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }

        }
        else if (requestCode == AWAY_REQUEST_CODE){
            if(data != null) {
                try {
                    change_img_away = true;
                    Uri imageUri = data.getData();
                    logoaway = imageUri;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    away.setImageBitmap(bitmap);
                } catch (IOException error) {
                    Toast.makeText(this, "cant load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
    }

}
