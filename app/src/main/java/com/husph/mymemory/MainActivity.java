package com.husph.mymemory;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.husph.mymemory.models.BoardSize;
import com.husph.mymemory.models.MemoryGame;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final BoardSize boardSize = BoardSize.MEDIUM;
    private MemoryGame memoryGame;
    private MemoryBoardAdapter memoryBoardAdapter;

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

        //setting up memory game class
        memoryGame = new MemoryGame(boardSize);

        MemoryBoardAdapter.CardClickListener onCardClick = (position) -> {
            Log.i(TAG, "Click detected in " + TAG);
            Log.i(TAG, "Another thing we need to flip at " + position);

        };

        memoryBoardAdapter = new MemoryBoardAdapter(
                this, boardSize,
                memoryGame.getCards(),
                onCardClick
        );

        //setting up the recycler view
        rvBoard.setAdapter(memoryBoardAdapter);
        rvBoard.setHasFixedSize(true);
        rvBoard.setLayoutManager(new GridLayoutManager(this, boardSize.getCardWidth()));
    }



}