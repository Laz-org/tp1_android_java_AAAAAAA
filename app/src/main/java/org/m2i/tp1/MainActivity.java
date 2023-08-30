package org.m2i.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Timer michel = new Timer();
    int result = 0, reponse;
    boolean game = false;
    Random random = new Random(); // class random => generer nombres aleatoires ...
    TextView nbr1_txt, nbr2_txt, counter;
    Button resp1_btn, resp2_btn, resp3_btn, start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart() {
        super.onStart();
        nbr1_txt = findViewById(R.id.nbr1);
        nbr2_txt = findViewById(R.id.nbr2);
        resp1_btn = findViewById(R.id.resp1);
        resp2_btn = findViewById(R.id.resp2);
        resp3_btn = findViewById(R.id.resp3);
        counter = findViewById(R.id.counter);
        start = findViewById(R.id.billy);
        //reponse = resp3_btn.getId();

        resp1_btn.setOnClickListener(this);
        resp2_btn.setOnClickListener(this);
        resp3_btn.setOnClickListener(this);
        start.setOnClickListener(this);
    }

    public void start_game(){
        int n1 = random.nextInt(200); // générer un nombre aleatoire entre 0 - 200
        int n2 = random.nextInt(200); // générer un nombre aleatoire entre 0 - 200
        int resp = n1 + n2;
        int r1 = random.nextInt(400); // générer un nombre aleatoire entre 0 - 200
        int r2 = random.nextInt(400); // générer un nombre aleatoire entre 0 - 200
        int i = random.nextInt(3);

        michel.schedule(new TimerTask() {
            @Override
            public void run() {
                result -= 1000;
            }
        }, 15*1000);

        counter.setText("" + result);
        nbr1_txt.setText(String.valueOf(n1));
        nbr2_txt.setText(""+n2);

        if(i == 0){
            resp1_btn.setText(resp+"");
            reponse = resp1_btn.getId();
            resp2_btn.setText(r1+"");
            resp3_btn.setText(r2+"");
        }else if (i == 1)
        {
            resp2_btn.setText(resp+"");
            reponse = resp2_btn.getId();
            resp1_btn.setText(r1+"");
            resp3_btn.setText(r2+"");
        }else {
            resp3_btn.setText(resp+"");
            reponse = resp3_btn.getId();
            resp2_btn.setText(r1+"");
            resp1_btn.setText(r2+"");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == start.getId()) {
            if (game == false) {
                game = true;
                start_game();
                start.setText("reset");
                return;
            }
            else {
                michel.purge();
                result = 0;
                start_game();
                return;
            }
        }
        if (game == false)
            return;
        if (v.getId() == reponse) {
            result++;
        }
        else
            result--;
        michel.purge();
        start_game();
        return;
    }
}