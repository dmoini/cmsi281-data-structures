package sentinal;

import java.util.LinkedList;

public class PhraseHash implements PhraseHashInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    
    private final static int BUCKET_COUNT = 1000;
    private int size, longest;
    private LinkedList<String>[] buckets;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    
    @SuppressWarnings("unchecked") // Don't worry about this >_>
    PhraseHash() {
        size = 0;
        longest = 0;
        buckets = new LinkedList[BUCKET_COUNT];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<String>();
        }
    }
    
    
    // -----------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void put(String s) { //TODO ask if case of empty string passed through
        int index = hash(s);
        if (buckets[index].contains(s)) {
            return;
        }
        buckets[index].addFirst(s);
        size++;
        if (getPhraseLength(s) > longest) {
            longest = getPhraseLength(s);
        }
    }
    
    public String get(String s) {
        int index = hash(s);
        return buckets[index].contains(s) ? s : null;
    }
    
    public int longestLength() {
        return longest;
    }
    
    
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    private static int hash(String s) {
        int index = 1;
        s = String.join("", s.split(" "));
        for (int i = 0; i < s.length(); i++) {
            index *= 31 * s.charAt(i);
        }
        return Math.abs(index) % BUCKET_COUNT;
    }
    
    private int getPhraseLength(String s) {
        return s.split(" ").length;
    }
}
