package com.flashcards;

import java.util.List;

/**
 * Defines strategy to order flashcards for each round.
 */
public interface CardOrganizer {
    /**
     * Order the given list of cards and return a new list.
     */
    List<Flashcard> organize(List<Flashcard> cards);
}