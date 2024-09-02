package com.husph.mymemory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.husph.mymemory.models.BoardSize;
import com.husph.mymemory.models.MemoryCard;

import java.util.List;

public class MemoryBoardAdapter extends RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder> {

    static final int NUM_OF_ELEMENTS_IN_GRID = 8;
    static final int NUM_OF_COLUMNS_IN_GRID = 2;
    static final int MARGIN_SIZE = 10;
    static final String TAG = "MemoryBoardAdapter";

    private final Context context;
    private final BoardSize boardSize;
    protected final List<MemoryCard> cards;


    public MemoryBoardAdapter(Context context, BoardSize boardSize, List<MemoryCard> cards) {
        this.context = context;
        this.boardSize = boardSize;
        this.cards = cards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final int cardWidth = parent.getWidth() / boardSize.getCardWidth();
        final int cardHeight = parent.getHeight() / boardSize.getCardHeight();
        final int cardSideLength = Math.min(cardWidth, cardHeight) - (2 * MARGIN_SIZE);

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.memory_card, parent, false);

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)
                itemView.findViewById(R.id.cardView).getLayoutParams();

        layoutParams.height = cardSideLength;
        layoutParams.width = cardSideLength;
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position, cards);
    }

    @Override
    public int getItemCount() {
        return boardSize.getNumOfCards();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        private final ImageButton imageButton = itemView.findViewById(R.id.imageButton);

        void bind(int position, List<MemoryCard> cards) {
            imageButton.setImageResource(cards.get(position).getIdentifier());
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "Clicked item as pos: " + position);
                }
            });
        }
    }
}
