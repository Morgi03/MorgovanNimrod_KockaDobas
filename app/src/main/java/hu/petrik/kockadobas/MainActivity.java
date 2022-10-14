package hu.petrik.kockadobas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView dobo1;
    private ImageView dobo2;
    private Button egyKocka;
    private Button kettoKocka;
    private Button dobas;
    private Button reset;
    private TextView dobasokText;
    private boolean kockakSzama;
    private Random rnd;
    private boolean elsosor;
    private AlertDialog.Builder resetAblak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        egyKocka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dobo2.setVisibility(View.GONE);
                dobo1.setImageResource(R.drawable.kocka1);
                kockakSzama = true;
            }
        });
        kettoKocka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dobo2.setVisibility(View.VISIBLE);
                dobo1.setImageResource(R.drawable.kocka1);
                dobo2.setImageResource(R.drawable.kocka1);
                kockakSzama = false;
            }
        });
        dobas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!kockakSzama) {
                    int szam1 = rnd.nextInt(6) + 1;
                    int szam2 = rnd.nextInt(6) + 1;
                    int osszeg = szam1 + szam2;
                    String s = osszeg + " (" + szam1 + "+" + szam2 + ")";
                    if (!elsosor) {
                        dobasokText.setText(s);
                        elsosor = true;
                    } else {
                        s = dobasokText.getText() + "\n" + osszeg + " (" + szam1 + "+" + szam2 + ")";
                        dobasokText.setText(s);
                    }
                    dobo1.setImageResource(changeKep(szam1));
                    dobo2.setImageResource(changeKep(szam2));
                    s = osszeg + " (" + szam1 + "+" + szam2 + ")";
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                } else {
                    int szam3 = rnd.nextInt(6) + 1;
                    if (!elsosor) {
                        dobasokText.setText(String.valueOf(szam3));
                        elsosor = true;
                    } else {
                        String s2 = dobasokText.getText() + "\n" + szam3;
                        dobasokText.setText(s2);
                    }
                    dobo1.setImageResource(changeKep(szam3));
                    Toast.makeText(MainActivity.this, String.valueOf(szam3), Toast.LENGTH_SHORT).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAblak.show();
            }
        });
    }

    private int changeKep(int num) {
        switch (num){
            case 1:
                return R.drawable.kocka1;
            case 2:
                return R.drawable.kocka2;
            case 3:
                return R.drawable.kocka3;
            case 4:
                return R.drawable.kocka4;
            case 5:
                return R.drawable.kocka5;
            case 6:
                return R.drawable.kocka6;
        }
        return 0;
    }


    private void init() {
        dobo1 = findViewById(R.id.dobo1);
        dobo2 = findViewById(R.id.dobo2);
        egyKocka = findViewById(R.id.egyKocka);
        kettoKocka = findViewById(R.id.kettoKocka);
        dobas = findViewById(R.id.dobas);
        reset = findViewById(R.id.reset);
        dobasokText = findViewById(R.id.dobasokText);
        kockakSzama = false;
        rnd = new Random();
        elsosor = false;
        resetAblak = new AlertDialog.Builder(this);
        resetAblak.setCancelable(false);
        resetAblak.setMessage("Biztos, hogy törölni szeretné az eddigi dobásokat?");
        resetAblak.setTitle("Reset");
        resetAblak.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        resetAblak.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                elsosor = false;
                dobasokText.setText("");
                dobo1.setImageResource(R.drawable.kocka1);
                dobo2.setImageResource(R.drawable.kocka1);
            }
        });
    }
}