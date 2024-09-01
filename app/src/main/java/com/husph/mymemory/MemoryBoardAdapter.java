package com.husph.mymemory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MemoryBoardAdapter extends RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder> {
    private final Context context;
    private final int numOfElementsInGrid;

    public MemoryBoardAdapter(Context context, int numOfElementsInGrid) {
        this.context = context;
        this.numOfElementsInGrid = numOfElementsInGrid;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final int NUM_OF_ELEMENTS_IN_GRID = 8;
        final int NUM_OF_COLUMNS_IN_GRID = 2;

        final int cardHeight = parent.getHeight() / (NUM_OF_ELEMENTS_IN_GRID/NUM_OF_COLUMNS_IN_GRID);
        final int cardWidth = parent.getWidth() / (NUM_OF_COLUMNS_IN_GRID);

        final int cardSideLength = Math.min(cardWidth, cardHeight);

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.memory_card, parent, false);

        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();

        layoutParams.height = cardSideLength;
        layoutParams.width = cardSideLength;

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numOfElementsInGrid;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(int position) {
            //no-op
            System.out.println("position: " + position);
        }
    }
}
