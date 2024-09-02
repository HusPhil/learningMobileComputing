package com.husph.mymemory.models;

import com.husph.mymemory.MemoryBoardAdapter;
import com.husph.mymemory.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryGame {

    private static final int NONE_SELECTED = -777;
    private static final String TAG = "MemoryGame";

    private final BoardSize boardSize;
    private final List<MemoryCard> cards;

    private int prevSelectedCardPosition = NONE_SELECTED;
    private int numOfPairsFound = 0;
    private int numOfValidMoves = 0;

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

    public boolean flipCard(int position, MemoryBoardAdapter memoryBoardAdapter) {
        MemoryCard card = cards.get(position);

        if (card.getIsMatched() || position == prevSelectedCardPosition) return false;
        numOfValidMoves++;

        boolean matchFound = false;

        if (prevSelectedCardPosition == NONE_SELECTED) {
            restoreCards(memoryBoardAdapter);
            card.setIsFaceUp(true);
            prevSelectedCardPosition = position;
        } else {
            card.setIsFaceUp(true);
            matchFound = checkIsMatch(prevSelectedCardPosition, position, memoryBoardAdapter);
            prevSelectedCardPosition = NONE_SELECTED;

            if (matchFound) {
                numOfPairsFound++;
            }
        }

        return matchFound;
    }

    private boolean checkIsMatch(int prevSelectedCardPosition, int newSelectedCardPosition, MemoryBoardAdapter memoryBoardAdapter) {

        MemoryCard prevSelectedCard, newSelectedCard;
        prevSelectedCard = cards.get(prevSelectedCardPosition);
        newSelectedCard = cards.get(newSelectedCardPosition);

        if(prevSelectedCard.getIdentifier() == newSelectedCard.getIdentifier()) {
            prevSelectedCard.setIsMatched(true);
            newSelectedCard.setIsMatched(true);

            memoryBoardAdapter.notifyItemChanged(prevSelectedCardPosition);
            memoryBoardAdapter.notifyItemChanged(newSelectedCardPosition);

            return true;
        }
        return false;
    }

    private void restoreCards(MemoryBoardAdapter memoryBoardAdapter) {
        for (int i = 0; i < cards.size(); i++) {
            MemoryCard card = cards.get(i);
            if (!card.getIsMatched() && card.getIsFaceUp()) {
                card.setIsFaceUp(false);
                memoryBoardAdapter.notifyItemChanged(i);
            }
        }
    }

    public int getNumMoves() {
        return numOfValidMoves / 2;
    }

    public boolean haveWonGame() {
        return numOfPairsFound == boardSize.getCardPairs();
    }
}
