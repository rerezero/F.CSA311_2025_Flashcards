package com.flashcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Random ordering of cards.
 */
public class RandomSorter implements CardOrganizer {
    @Override
    public List<Flashcard> organize(List<Flashcard> cards) {
        List<Flashcard> copy = new ArrayList<>(cards);
        Collections.shuffle(copy);
        return copy;
    }
}