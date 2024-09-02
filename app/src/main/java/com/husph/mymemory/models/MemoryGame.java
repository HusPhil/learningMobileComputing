package com.husph.mymemory.models;

import android.util.Log;

import com.husph.mymemory.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryGame {

    private static final int NONE_SELECTED = -777;
    private static final String TAG = "MemoryGame";

    private final BoardSize boardSize;
    private List<MemoryCard> cards;

    private int prevSelectedCardPosition = NONE_SELECTED;
    private int numOfPairsFound;

    public MemoryGame(BoardSize boardSize) {
        this.boardSize = boardSize;
        this.cards = getShuffledMemoryCards(boardSize.getCardPairs());
    }

    private static List<MemoryCard> getShuffledMemoryCards(int numOfPairs) {
        List<Integer> takenImages = Constants.DEFAULT_ICONS
                .subList(0, Math.min(Constants.DEFAULT_ICONS.size(), numOfPairs));

        List<Integer> shuffledImages = new ArrayList<>(takenImages);
        shuffledImages.addAll(takenImages);
        Collections.shuffle(shuffledImages);

        return shuffledImages.stream()
                .map(MemoryCard::new)
                .collect(Collectors.toList());
    }

    public List<MemoryCard> getCards() {
        return cards;
    }

    public int getNumOfPairsFound() {
        return numOfPairsFound;
    }

    public void flipCard(int position) {
        MemoryCard card = cards.get(position);

        if (card.getIsMatched() || position == prevSelectedCardPosition) return;

        if (prevSelectedCardPosition == NONE_SELECTED) {
            restoreCards();
            card.setIsFaceUp(true);
            prevSelectedCardPosition = position;
        } else {
            card.setIsFaceUp(true);
            boolean matchFound = checkIsMatch(prevSelectedCardPosition, position);
            prevSelectedCardPosition = NONE_SELECTED;

            if (matchFound) {
                numOfPairsFound++;
            }
        }
    }

    private boolean checkIsMatch(int prevSelectedCardPosition, int newSelectedCardPosition) {

        MemoryCard prevSelectedCard, newSelectedCard;
        prevSelectedCard = cards.get(prevSelectedCardPosition);
        newSelectedCard = cards.get(newSelectedCardPosition);

        if(prevSelectedCard.getIdentifier() == newSelectedCard.getIdentifier()) {
            prevSelectedCard.setIsMatched(true);
            newSelectedCard.setIsMatched(true);
            return true;
        }
        return false;
    }

    private void restoreCards() {
        for (MemoryCard card : cards) {
            if (!card.getIsMatched()) {
                card.setIsFaceUp(false);
            }
        }
    }

}
