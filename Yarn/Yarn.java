package yarn;
    
public class Yarn implements YarnInterface {
    
    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
       
    private Strand[] items;
    private int size;
    private int uniqueSize;
    private final int MAX_SIZE = 100;
        
        
    // -----------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------
    
    Yarn () {
        items = new Strand[MAX_SIZE];
        size = 0;
        uniqueSize = 0;
    }
    
    Yarn (Yarn other) {
        	size = other.size;
        uniqueSize = other.uniqueSize;
        	items = new Strand[MAX_SIZE];
        	for (int i = 0; i < uniqueSize; i++) {
        	    items[i] = new Strand(other.items[i].text, other.items[i].count);
        	}
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    
    public boolean isEmpty() {
    	    return size == 0;
    }
    
    public int getSize() {
        return size;
    }
    
    public int getUniqueSize() {
        return uniqueSize;
    }
    
    public boolean insert(String toAdd) {
    	    if ((uniqueSize >= 100) && !contains(toAdd)) {
    	        return false;
    	    }
    	    if (!contains(toAdd)) {
    	        items[uniqueSize] = new Strand(toAdd, 1);
    	        uniqueSize++;
    	    } else {
    	        items[getStrandIndex(toAdd)].count++;
    	    }
    	    size++;
    	    return true;
    }
    
    public int remove(String toRemove) {
        if (!contains(toRemove)) {
            return 0;
        }
        int index = getStrandIndex(toRemove);
        items[index].count--;
        size--;
        if (items[index].count == 0) {
            moveOver(index);
            return 0;
        }
        return items[index].count;
    }
    
    public void removeAll(String toNuke) {
        if (!contains(toNuke)) {
            return;
        }
        int index = getStrandIndex(toNuke);
        size -= items[index].count;
        items[index].count = 0;
        moveOver(index);
    }
    
    public int count(String toCount) {
        if (!contains(toCount)) {
            return 0;
        }
        int index = getStrandIndex(toCount);
        return items[index].count;
    }
    
    public boolean contains(String toCheck) {
        return getStrandIndex(toCheck) >= 0;
    }
    
    public String getNth(int n) {
        for (int i = 0; i < uniqueSize; i++) {
            if (n < items[i].count) {
                return items[i].text;
            } else {
                n -= items[i].count;
            }
        }
        return "ERROR";
    }
    
    public String getMostCommon() {
        if (size == 0) {
            return null;
        }
        int most = 0;
        int index = 0;
        for (int i = 0; i < uniqueSize; i++) {
            if (items[i].count > most) {
                most = items[i].count;
                index = i;
            }
        }
        return items[index].text;
    }
    
    public void swap(Yarn other) {
        Yarn temp = new Yarn();
        temp.items = items;
        	temp.size = size;
        	temp.uniqueSize = uniqueSize;
        	items = other.items;
        	size = other.size;
        	uniqueSize = other.uniqueSize;
        	other.items = temp.items;
        	other.size = temp.size;
        	other.uniqueSize = temp.uniqueSize;
    }
    
        public String toString() {
        		String sequence = "{ ";
    		int i = 0;
    		while (i < uniqueSize) {
    		    Strand strand = new Strand(items[i].text, items[i].count);
    		    sequence += "\"" + strand.text + "\": " + strand.count;
    		    if (uniqueSize - i > 1) {
    		        sequence += ", "; 
    		    }
    		    i++;
    		}
    		sequence += " }";
    		return sequence;
    }
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static Yarn knit(Yarn y1, Yarn y2) {
        if (y1.uniqueSize + y2.uniqueSize > 100) {
    		    throw new Error("Make sure that the sum of each yarn's size is less than or equal to 100.");
    		}
        if (y1.isEmpty() || y2.isEmpty()) {
            return y1.isEmpty() ? y2 : y1;
        }
        Yarn yarny = new Yarn(y1);
        for (int i = 0; i < y1.uniqueSize; i++) {
            for (int j = 0; j < y2.items[i].count; j++) {
                yarny.insert(y2.items[i].text);
            }
        }
        return yarny;
    }
    
    public static Yarn tear(Yarn y1, Yarn y2) {
        Yarn y3 = new Yarn();
        for (int i = 0; i < y1.uniqueSize; i++) {
            String text = y1.items[i].text;
            int count = y1.items[i].count;
            if (!y2.contains(text)) {
                for (int j = 0; j < count; j++) {
                    y3.insert(text);
                }
            } else {
                for (int j = 0; j < y2.uniqueSize; j++) {
                    String text2 = y2.items[j].text;
                    int count2 = y2.items[j].count;
                    if (text.equals(text2) && (count > count2)) {
                        for (int k = 0; k < (count - count2); k++) {
                            y3.insert(text);
                        }
                        break;
                    }
                }
            }
        }
        return y3;
    }
    
    public static boolean sameYarn(Yarn y1, Yarn y2) {
        return tear(y1, y2).isEmpty() && tear(y2, y1).isEmpty();
    }
    
    
    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------
    // Add your own here!
    
    private void moveOver(int index) {
        items[index] = items[uniqueSize - 1];
        uniqueSize--;
    }
    
    private int getStrandIndex(String s) { // must check externally if Yarn contains Strand
        int index = -1;
        for (int i = 0; i < uniqueSize; i++) {
            if (items[i].text.equals(s)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
    
class Strand {
    String text;
    int count;
    
    Strand (String s, int c) {
        text = s;
        count = c;
    }
}
