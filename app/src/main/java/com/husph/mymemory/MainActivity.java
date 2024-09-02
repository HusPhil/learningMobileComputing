package com.husph.mymemory;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.husph.mymemory.models.BoardSize;
import com.husph.mymemory.models.MemoryGame;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final BoardSize boardSize = BoardSize.HARD;

    private ConstraintLayout clRoot;
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

        clRoot = findViewById(R.id.clRoot);

        ViewCompat.setOnApplyWindowInsetsListener(clRoot, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUpBoard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mi_refresh) {
            setUpBoard();
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateGameWithFlip(int position) {
        if(memoryGame.haveWonGame()) {
            Snackbar.make(clRoot, "You have found all the pairs!", Snackbar.LENGTH_LONG)
                    .show();
            return;
        }

        if(memoryGame.getCards().get(position).getIsFaceUp()) {
            Snackbar.make(clRoot, "Invalid move!", Snackbar.LENGTH_LONG)
                    .show();
            return;
        }

        final boolean matchFound = memoryGame.flipCard(position, memoryBoardAdapter);
        memoryBoardAdapter.notifyItemChanged(position);

        final String numMoveText = "Moves: " + memoryGame.getNumMoves();
        tvNumMoves.setText(numMoveText);

        if(!matchFound) {
            return;
        }

        int color = (int) new ArgbEvaluator().evaluate(
                (float) memoryGame.getNumOfPairsFound() / boardSize.getCardPairs(),
                ContextCompat.getColor(this, R.color.c_progress_none),
                ContextCompat.getColor(this, R.color.c_progress_full)
        );

        tvNumPairs.setTextColor(color);
        String numPairsText = "Pairs: " + memoryGame.getNumOfPairsFound() + "/" + boardSize.getCardPairs();
        tvNumPairs.setText(numPairsText);
        if(memoryGame.haveWonGame()) {
            Snackbar.make(clRoot, "Congratulation! You won the game!", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    private void setUpBoard() {
        //access the references of the views that need to change
        rvBoard = findViewById(R.id.rvBoard);
        tvNumMoves = findViewById(R.id.tvNumMove);
        tvNumPairs = findViewById(R.id.tvNumPairs);

        //setting up memory game class
        memoryGame = new MemoryGame(boardSize);

        final String numMovesText = "Moves: " + memoryGame.getNumMoves();
        final String numPairsText = "Pairs: " + memoryGame.getNumOfPairsFound() + "/" + boardSize.getCardPairs();

        tvNumMoves.setText(numMovesText);
        tvNumPairs.setTextColor(ContextCompat.getColor(this, R.color.c_progress_none));
        tvNumPairs.setText(numPairsText);

        MemoryBoardAdapter.CardClickListener cardClickListener = position -> {
            Log.i(TAG, "Click detected in " + TAG);
            updateGameWithFlip(position);
        };

        memoryBoardAdapter = new MemoryBoardAdapter(
                this, boardSize,
                memoryGame.getCards(),
                cardClickListener
        );

        //setting up the recycler view
        rvBoard.setAdapter(memoryBoardAdapter);
        rvBoard.setHasFixedSize(true);
        rvBoard.setLayoutManager(new GridLayoutManager(this, boardSize.getCardWidth()));
    }
}