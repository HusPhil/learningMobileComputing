package com.husph.mymemory;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private final int NUM_OF_ELEMENTS_IN_GRID = 8;
    private final int NUM_OF_COLUMNS_IN_GRID = 2;

    private RecyclerView rvBoard;
    private TextView tvNumMoves;
    private TextView tvNumPairs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //access the references of the views that need to change
        rvBoard = findViewById(R.id.rvBoard);
        tvNumMoves = findViewById(R.id.tvNumMove);
        tvNumPairs = findViewById(R.id.tvNumPairs);

        //setting up the recycler view
        rvBoard.setAdapter(new MemoryBoardAdapter(this, NUM_OF_ELEMENTS_IN_GRID));
        rvBoard.setHasFixedSize(true);
        rvBoard.setLayoutManager(new GridLayoutManager(this, NUM_OF_COLUMNS_IN_GRID));
    }



}