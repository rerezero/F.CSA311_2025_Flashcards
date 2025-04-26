package com.flashcards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads cards from a text file, each line: question|answer
 */
public class CardsFileLoader {
    public static List<Flashcard> load(String path) throws IOException {
        List<Flashcard> cards = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|", 2);
                if (parts.length < 2) continue;
                cards.add(new Flashcard(parts[0].trim(), parts[1].trim()));
            }
        }
        return cards;
    }
}