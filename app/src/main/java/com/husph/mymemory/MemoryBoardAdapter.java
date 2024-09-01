package com.husph.mymemory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MemoryBoardAdapter extends RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder> {

    static final int NUM_OF_ELEMENTS_IN_GRID = 8;
    static final int NUM_OF_COLUMNS_IN_GRID = 2;
    static final int MARGIN_SIZE = 10;
    static final String TAG = "MemoryBoardAdapter";

    private final Context context;
    private final int numOfElementsInGrid;


    public MemoryBoardAdapter(Context context, int numOfElementsInGrid) {
        this.context = context;
        this.numOfElementsInGrid = numOfElementsInGrid;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final int cardHeight = parent.getHeight()/(NUM_OF_ELEMENTS_IN_GRID/NUM_OF_COLUMNS_IN_GRID);
        final int cardWidth = parent.getWidth()/(NUM_OF_COLUMNS_IN_GRID);
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

        private final ImageButton imageButton = itemView.findViewById(R.id.imageButton);

        void bind(int position) {
            //no-op
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "Clicked item as pos: " + position);
                }
            });
        }
    }
}
