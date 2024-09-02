package com.husph.mymemory;

import android.content.Context;
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

    private static final int MARGIN_SIZE = 10;

    private final Context context;
    private final BoardSize boardSize;
    private final List<MemoryCard> cards;
    private final CardClickListener cardClickListener;

    public MemoryBoardAdapter(Context context, BoardSize boardSize, List<MemoryCard> cards, CardClickListener cardClickListener) {
        this.context = context;
        this.boardSize = boardSize;
        this.cards = cards;
        this.cardClickListener = cardClickListener;
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

        return new ViewHolder(itemView, cardClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(cards.get(position));
    }

    @Override
    public int getItemCount() {
        return boardSize.getNumOfCards();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageButton imageButton;

        ViewHolder(@NonNull View itemView, final CardClickListener cardClickListener) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(view -> cardClickListener.onCardClick(getAdapterPosition()));
        }

        void bind(MemoryCard memoryCard) {
            imageButton.setImageResource(memoryCard.getIsFaceUp() ? memoryCard.getIdentifier() : R.drawable.ic_launcher_background);
            imageButton.setAlpha(memoryCard.getIsMatched() ? 0.4f : 1.0f);
        }
    }

    public interface CardClickListener {
        void onCardClick(int position);
    }
}
