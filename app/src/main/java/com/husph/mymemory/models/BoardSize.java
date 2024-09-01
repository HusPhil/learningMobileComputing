package com.husph.mymemory.models;

public enum BoardSize {
    EASY(8),
    MEDIUM(18),
    HARD(24);

    private final int numOfCards;

    BoardSize(int numOfCards) {
        if(numOfCards % 2 != 0) {
            throw new IllegalArgumentException("Only even number of cards is accepted for the board size.");
        }
        this.numOfCards = numOfCards;
    }

    public int getCardWidth() {
        switch (this) {
            case EASY:
                return 2;
            case MEDIUM:
                return 3;
            case HARD:
                return 4;
            default:
                throw new IllegalArgumentException("Invalid arguments received in BoardSize");
        }
    };

    public int getCardHeight() {
        return numOfCards / getCardWidth();
    }

    public int getCardPairs() {
        return numOfCards / 2;
    }

    public int getNumOfCards() {
        return numOfCards;
    }

}
