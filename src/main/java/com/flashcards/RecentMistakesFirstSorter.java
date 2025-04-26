package com.flashcards;

import java.util.ArrayList;
import java.util.List;

/**
 * Puts cards answered incorrectly in previous round first.
 */
public class RecentMistakesFirstSorter implements CardOrganizer {
    @Override
    public List<Flashcard> organize(List<Flashcard> cards) {
        List<Flashcard> mistakes = new ArrayList<>();
        List<Flashcard> others = new ArrayList<>();
        for (Flashcard c : cards) {
            if (!c.lastCorrect()) {
                mistakes.add(c);
            } else {
                others.add(c);
            }
        }
        // Preserve relative order
        mistakes.addAll(others);
        return mistakes;
    }
}