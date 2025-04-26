package com.flashcards;

import org.apache.commons.cli.*;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Options opts = new Options();
        opts.addOption("h", "help", false, "Show help");
        opts.addOption(null, "order", true, "Order: random, worst-first, recent-mistakes-first");
        opts.addOption(null, "repetitions", true, "Times to repeat correct answer");
        opts.addOption(null, "invertCards", false, "Invert question and answer");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(opts, args, true);
            if (cmd.hasOption("help")) {
                new HelpFormatter().printHelp("flashcard <cards-file> [options]", opts);
                return;
            }
            List<String> remaining = cmd.getArgList();
            if (remaining.isEmpty()) {
                System.err.println("Error: Missing <cards-file>");
                return;
            }
            String path = remaining.get(0);
            List<Flashcard> cards = CardsFileLoader.load(path);

            CardOrganizer sorter;
            String order = cmd.getOptionValue("order", "random");
            switch (order) {
                case "random": sorter = new RandomSorter(); break;
                case "worst-first": sorter = new WorstFirstSorter(); break;
                case "recent-mistakes-first": sorter = new RecentMistakesFirstSorter(); break;
                default:
                    System.err.println("Invalid order: " + order);
                    return;
            }
            int reps = Integer.parseInt(cmd.getOptionValue("repetitions", "1"));
            boolean invert = cmd.hasOption("invertCards");

            AchievementManager am = new AchievementManager();
            for (int r = 0; r < reps; r++) {
                List<Flashcard> round = sorter.organize(cards);
                for (Flashcard c : round) {
                    String q = invert ? c.getAnswer() : c.getQuestion();
                    String a = invert ? c.getQuestion() : c.getAnswer();
                    long start = System.currentTimeMillis();
                    System.out.println(q);
                    String input = System.console().readLine();
                    boolean correct = input.trim().equalsIgnoreCase(a);
                    long time = System.currentTimeMillis() - start;
                    if (!correct) System.out.println("Wrong! Correct is: " + a);
                    c.recordResult(correct);
                }
                am.check(round);
            }
            am.report();
        } catch (ParseException e) {
            System.err.println("Command error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }
}