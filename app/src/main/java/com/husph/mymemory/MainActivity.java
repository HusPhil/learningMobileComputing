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

import com.husph.mymemory.models.BoardSize;
import com.husph.mymemory.utils.Constants;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BoardSize boardSize = BoardSize.MEDIUM;

    private RecyclerView rvBoard;
    private TextView tvNumMoves;
    private TextView tvNumPairs;

    public List<Integer> cardImages = Constants.getShuffledImages(boardSize.getCardPairs());


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
        rvBoard.setAdapter(new MemoryBoardAdapter(this, boardSize, cardImages));
        rvBoard.setHasFixedSize(true);
        rvBoard.setLayoutManager(new GridLayoutManager(this, boardSize.getCardWidth()));
    }



}