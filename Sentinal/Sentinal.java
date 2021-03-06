package sentinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sentinal implements SentinalInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    
    private PhraseHash posHash, negHash;

    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    
    Sentinal(String posFile, String negFile) throws FileNotFoundException {
        posHash = new PhraseHash();
        negHash = new PhraseHash();
        loadSentimentFile(posFile, true);
        loadSentimentFile(negFile, false);
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    
    public void loadSentiment(String phrase, boolean positive) {
        ((positive) ? posHash : negHash).put(phrase);
    }
    
    public void loadSentimentFile(String filename, boolean positive) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        while(sc.hasNext()) {
            loadSentiment(sc.nextLine(), positive);
        }
        sc.close();
    }
    
    public String sentinalyze(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        int count = 0;      
        while(sc.hasNext()) {
            int phraseLength = 1;
            String[] sentence = sc.nextLine().split(" ");
            while (phraseLength <= posHash.longestLength() || phraseLength <= negHash.longestLength()) {
                for (int i = 0; i < sentence.length + 1 - phraseLength; i++) {
                    String phrase = "";
                    for (int j = i; j < i + phraseLength; j++) {
                        phrase += sentence[j] + " ";
                    }
                    phrase = phrase.trim();
                    count = updateCount(phrase, count);
                }
                phraseLength++;
            }
        }
        sc.close();
        return positiveNegativeOrNeutral(count);
    }
    
    
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    private String positiveNegativeOrNeutral(int c) {
        if (c > 0) {
            return "positive";
        } else if (c < 0) {
            return "negative";
        } else {
            return "neutral";
        }
    }
    
    private int updateCount(String phrase, int count) {
        if (posHash.get(phrase) != null) {
            count++;
        }
        if (negHash.get(phrase) != null) {
            count--;
        }
        return count;
    }
 }
