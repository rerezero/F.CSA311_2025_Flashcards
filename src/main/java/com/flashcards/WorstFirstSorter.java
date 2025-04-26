package com.flashcards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Orders by lowest overall accuracy first.
 */
public class WorstFirstSorter implements CardOrganizer {
    public List<Flashcard> organize(List<Flashcard> cards) {
        List<Flashcard> copy = new ArrayList<>(cards);
        copy.sort(Comparator.comparingDouble(Flashcard::accuracy));
        return copy;
    }
}