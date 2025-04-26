package com.flashcards;

public class Flashcard {
    private final String question;
    private final String answer;
    private int timesAsked = 0;
    private int timesCorrect = 0;
    private boolean lastCorrect = true;

    public Flashcard(String q, String a) {
        this.question = q;
        this.answer = a;
    }
    public String getQuestion() { return question; }
    public String getAnswer() { return answer; }
    public void recordResult(boolean correct) {
        timesAsked++;
        lastCorrect = correct;
        if (correct) timesCorrect++;
    }
    public boolean lastCorrect() { return lastCorrect; }
    public double accuracy() {
        return timesAsked == 0 ? 1.0 : (double) timesCorrect / timesAsked;
    }
    public int getTimesAsked() { return timesAsked; }
    public int getTimesCorrect() { return timesCorrect; }
}