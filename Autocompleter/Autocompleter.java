package autocompleter;

import java.util.ArrayList;

public class Autocompleter implements AutocompleterInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    TTNode root;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Autocompleter () {
        root = null;
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------

    public boolean isEmpty () {
        return root == null;
    }
    
    public void addTerm (String toAdd) {
        root = insert(root, normalizeTerm(toAdd), 0);
    }
    
    public boolean hasTerm (String query) {
        return find(query) != null && find(query).wordEnd;
    }
    
    public String getSuggestedTerm (String query) {
        TTNode current = find(query);
        if (current == null) {
            return null;
        }
        String suggested = normalizeTerm(query);
        while(!current.wordEnd) {
            current = current.mid;
            suggested += current.letter;
        }
        return suggested;
    }
    
    public ArrayList<String> getSortedTerms () {
        ArrayList<String> sortedTerms = new ArrayList<String>();
        sortTTArray(sortedTerms);
        return sortedTerms;
    }
    
    
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    private String normalizeTerm (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }
    
    /*
     * Returns:
     *   int less than 0 if c1 is alphabetically less than c2
     *   0 if c1 is equal to c2
     *   int greater than 0 if c1 is alphabetically greater than c2
     */
    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }
    
    private TTNode insert(TTNode current, String word, int index) {
        if (current == null) {
            current = new TTNode(word.charAt(index), false);
        }
        int compare = compareChars(word.charAt(index), current.letter);
        if (compare < 0) {
            current.left = insert(current.left, word, index);
        } else if (compare > 0) {
            current.right = insert(current.right, word, index);
        } else {
            if (index + 1 < word.length()) {
                current.mid = insert(current.mid, word, index + 1);
            } else {
                current.wordEnd = true;
            }
        }
        return current;
    }
    
    private TTNode find(String query) {
        return find(root, normalizeTerm(query), 0);
    }

    private TTNode find(TTNode current, String word, int index) {
        if (current == null) {
            return null;
        }
        int compare = compareChars(word.charAt(index), current.letter);
        if (compare < 0) {
            return find(current.left, word, index);
        } else if (compare > 0) {
            return find(current.right, word, index);
        } else {
            if (index == word.length() - 1) {
                return current;
            }
            return find(current.mid, word, index + 1);
        }
    }
    
    private void sortTTArray(ArrayList<String> arr) {
        sortTTArray(root, "", arr);
    }
    
    private void sortTTArray(TTNode current, String prefix, ArrayList<String> arr) {
        if (current == null) {
            return;
        }
        sortTTArray(current.left, prefix, arr);
        if (current.wordEnd) {
            arr.add(prefix + current.letter);
        }
        sortTTArray(current.mid, prefix + current.letter, arr);
        sortTTArray(current.right, prefix, arr);
    }
    
    // -----------------------------------------------------------
    // TTNode Internal Storage
    // -----------------------------------------------------------
    
    /*
     * Internal storage of autocompleter search terms
     * as represented using a Ternary Tree with TTNodes
     */
    private class TTNode {
        
        boolean wordEnd;
        char letter;
        TTNode left, mid, right;
        
        TTNode (char c, boolean w) {
            letter = c;
            wordEnd = w;
            left = null;
            mid = null;
            right = null;
        }
    }
}
