package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private TextView skorhome;
    private TextView skoraway;
    private TextView home;
    private TextView away;
    private Button tsatu, tdua, ttiga, tsatuu, tduaa, ttigaa, result, reset;
    private ImageView logohome, logoaway;
    private int homescore, awayscore;
    private String win, a, b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

        skorhome = findViewById(R.id.score_home);
        skoraway = findViewById(R.id.score_away);
        home = findViewById(R.id.txt_home);
        away = findViewById(R.id.txt_away);
        logohome = findViewById(R.id.home_logo);
        logoaway = findViewById(R.id.away_logo);
        tsatu = findViewById(R.id.satu);
        tdua = findViewById(R.id.dua);
        ttiga = findViewById(R.id.tiga);
        tsatuu = findViewById(R.id.satuu);
        tduaa = findViewById(R.id.duaa);
        ttigaa = findViewById(R.id.tigaa);
        result = findViewById(R.id.hasil);
        reset = findViewById(R.id.reset);

        homescore = 0;
        awayscore = 0;
        skorhome.setText(String.valueOf(homescore));
        skoraway.setText(String.valueOf(awayscore));

        Bundle bundle = getIntent().getExtras();
        a = bundle.getString("home");
        home.setText(a);
        b = bundle.getString("away");
        away.setText(b);
        logohome.setImageURI(Uri.parse(bundle.getString("homeimg")));
        logoaway.setImageURI(Uri.parse(bundle.getString("awayimg")));

        tsatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homescore += 1;
                skorhome.setText(String.valueOf(homescore));
            }
        });
        tdua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homescore += 2;
                skorhome.setText(String.valueOf(homescore));
            }
        });
        ttiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homescore += 3;
                skorhome.setText(String.valueOf(homescore));
            }
        });
        tsatuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayscore += 1;
                skoraway.setText(String.valueOf(awayscore));
            }
        });
        tduaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayscore += 2;
                skoraway.setText(String.valueOf(awayscore));
            }
        });
        ttigaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayscore += 3;
                skoraway.setText(String.valueOf(awayscore));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayscore = 0;
                homescore = 0;
                skorhome.setText(String.valueOf(homescore));
                skoraway.setText(String.valueOf(awayscore));
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                win = "empty";
                if(homescore > awayscore){
                    win = "Pemenangnya Adalah " + a;
                }
                else if (homescore == awayscore){
                    win = "Draw!";
                }
                else {
                    win = "Pemenangnya Adalah " + b;
                }

                Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
                intent.putExtra("winner", win);
                startActivity(intent);
            }
        });




    }
}
