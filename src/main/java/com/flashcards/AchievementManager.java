package com.flashcards;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Tracks achievements per round.
 */
public class AchievementManager {
    private final Set<String> unlocked = new HashSet<>();

    public void check(List<Flashcard> roundCards) {
        // CORRECT
        boolean allCorrect = roundCards.stream().allMatch(Flashcard::lastCorrect);
        if (allCorrect) unlocked.add("CORRECT");
        // REPEAT
        roundCards.stream()
            .filter(c -> c.getTimesAsked() > 5)
            .findAny()
            .ifPresent(c -> unlocked.add("REPEAT"));
        // CONFIDENT
        roundCards.stream()
            .filter(c -> c.getTimesCorrect() >= 3)
            .findAny()
            .ifPresent(c -> unlocked.add("CONFIDENT"));
    }
    public void report() {
        if (unlocked.isEmpty()) return;
        System.out.println("Achievements unlocked: " + unlocked);
    }
}