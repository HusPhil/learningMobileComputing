package com.husph.mymemory.models;

import com.husph.mymemory.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryGame {

    private BoardSize boardSize;
    private List<MemoryCard> cards;

    public MemoryGame(BoardSize boardSize) {
        this.boardSize = boardSize;
        this.cards = getShuffledMemoryCards(boardSize.getCardPairs());
    }

    private static List<MemoryCard> getShuffledMemoryCards(int numOfPairs) {

        List<Integer> takenImages =  Constants.DEFAULT_ICONS
                .subList(0, Math.min(Constants.DEFAULT_ICONS.size(), numOfPairs));

        List<Integer> randomizedImages = new ArrayList<>(takenImages);

        randomizedImages.addAll(takenImages);
        Collections.shuffle((randomizedImages));

        return randomizedImages.stream()
                .map(id -> new MemoryCard(id))
                .collect(Collectors.toList());
    }

    public List<MemoryCard> getCards() {
        return cards;
    }
}
