package com.miguel.app.trisarray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguel.app.trisarray.adapter.MyAdapter;
import com.miguel.app.trisarray.pojo.Tris;

public class TrisActivity extends AppCompatActivity {

    GridView gridView;
    MyAdapter adapter;
    Context context;

    Tris tris;
    TextView result;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame();
    }

    private void startGame() {

        context = this;
        tris = new Tris();

        gridView = findViewById(R.id.grid_view);
        adapter = new MyAdapter(this, tris.getMap());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(gridEvent);

        result = findViewById(R.id.txt_result);
        btnRestart = findViewById(R.id.btn_restart);

        btnRestart.setOnClickListener(restartEvent);
    }

    AdapterView.OnItemClickListener gridEvent = (parent, view, position, id) -> {

        if(tris.checkStatus(position) && Tris.inProgress()) {

            tris.play(position);
//            Toast.makeText(context, "pos: " + position, Toast.LENGTH_SHORT).show();


            updateGame();
        } else if (Tris.isWinner()) {
            result.setText("------- TRIS -----------");
        } else if (Tris.isFull()) {
            result.setText("--- It's a DRAW! ------");
        } else {
            Toast.makeText(context, "Casella gia usata!", Toast.LENGTH_SHORT).show();
        }


    };

    private void updateGame() {
        adapter.reload(tris.getMap());
        adapter.notifyDataSetChanged();
        gridView.invalidateViews();
        gridView.refreshDrawableState();
    }

    View.OnClickListener restartEvent = v -> {
        tris = new Tris();
        result.setText("Partita in corso");
        result.setTextColor(Color.BLUE);
        updateGame();
    };

}