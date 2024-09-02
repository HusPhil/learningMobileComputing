package com.husph.mymemory.models;

public class MemoryCard {


    private final int identifier;
    private int isFaceUp;
    private boolean isMatched;

    public int getIdentifier() {
        return identifier;
    }

    public int getIsFaceUp() {
        return isFaceUp;
    }

    public void setIsFaceUp(int isFaceUp) {
        this.isFaceUp = isFaceUp;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public MemoryCard(int identifier) {
        this.identifier = identifier;
    }
}
