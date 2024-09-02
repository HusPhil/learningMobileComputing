package com.husph.mymemory.models;

public class MemoryCard {


    private final int identifier;
    private boolean isFaceUp;
    private boolean isMatched;

    public int getIdentifier() {
        return identifier;
    }

    public boolean getIsFaceUp() {
        return isFaceUp;
    }

    public void setIsFaceUp(boolean isFaceUp) {
        this.isFaceUp = isFaceUp;
    }

    public boolean getIsMatched() {
        return isMatched;
    }

    public void setIsMatched(boolean matched) {
        isMatched = matched;
    }

    public MemoryCard(int identifier) {
        this.identifier = identifier;
    }
}
