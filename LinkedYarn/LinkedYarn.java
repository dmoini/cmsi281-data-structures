package linked_yarn;

import java.util.NoSuchElementException;

public class LinkedYarn implements LinkedYarnInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Node head;
    private int size; 
    private int uniqueSize;
    private int modCount;
    
    
    // -----------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------
    LinkedYarn() {
        head = null;
        size = 0;
        uniqueSize = 0;
        modCount = 0;
    }
    
    LinkedYarn(LinkedYarn other) {
        size = other.size;
        uniqueSize = other.uniqueSize;
        modCount = other.modCount;
        for (Node n = other.head; n != null; n = n.next) {
            prepend(n.text, n.count);
        }
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public int getUniqueSize() {
        return this.uniqueSize;
    }
    
    public void insert(String toAdd) {
        Node found = findNode(toAdd);
        if (found != null) {
            found.count++;
        } else {
            prepend(toAdd, 1);
            uniqueSize++;
        }
        size++;
        modCount++;
    }
    
    public int remove(String toRemove) {
        if (isEmpty() || !contains(toRemove)) {
            return 0;
        }
        return removeAmount(toRemove, 1);
    }
    
    public void removeAll(String toNuke) {
        if (isEmpty() || !contains(toNuke)) {
            return;
        }
        Node found = findNode(toNuke);
        size -= found.count;
        deleteNode(found);
        uniqueSize--;
        modCount++;
    }
    
    public int count(String toCount) {
        if (isEmpty() || !contains(toCount)) {
            return 0;
        }
        return findNode(toCount).count;
    }
    
    public boolean contains(String toCheck) {
        return findNode(toCheck) != null;
    }
    
    public String getMostCommon() {
        String mostCommon = null;
        int highestCount = -1;
        for (Node n = head; n != null; n = n.next) {
            if(n.count > highestCount) {
                highestCount = n.count;
                mostCommon = n.text;
            }
        }
        return mostCommon;
    }
    
    public void swap(LinkedYarn other) {
        Node tempHead = this.head;
        int tempSize = this.size;
        int tempUniqueSize = this.uniqueSize;
        int tempModCount = this.modCount++;
        this.head = other.head;
        this.size = other.size;
        this.uniqueSize = other.uniqueSize;
        this.modCount = other.modCount++;
        other.head = tempHead;
        other.size = tempSize;
        other.uniqueSize = tempUniqueSize;
        other.modCount = tempModCount;
    }
    
    public LinkedYarn.Iterator getIterator() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return new Iterator(this);
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static LinkedYarn knit(LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn y3 = new LinkedYarn(y1);
        for (Node n = y2.head; n != null; n = n.next) {
            y3.insertAmount(n.text, n.count);
        }
        return y3;
    }
    
    public static LinkedYarn tear(LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn result = new LinkedYarn(y1);
        for (Node n = y2.head; n != null; n = n.next) {
            result.removeAmount(n.text, n.count);
        }
        return result;
    }
    
    public static boolean sameYarn(LinkedYarn y1, LinkedYarn y2) {
        return tear(y1, y2).isEmpty() && tear(y2, y1).isEmpty();
    }
    
    
    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------
    
    private void deleteNode(Node n) { 
        if (n == head) {
            head = n.next;
        }
        if (n.next != null) {
            n.next.prev = n.prev;
        }
        if (n.prev != null) {
            n.prev.next = n.next;
        }
    }
    
    private void prepend(String str, int count) {
        Node pre = new Node(str, count);
        if (head == null) {
            head = pre;
        } else {
            Node currentHead = head;
            head = pre;
            head.next = currentHead;
            currentHead.prev = head;
        }
    }
    
    private Node findNode(String str) {
        for (Node n = head; n != null; n = n.next) {
            if (n.text.equals(str)) {
                return n;
            }
        }
        return null;
    }
    
    private void insertAmount(String str, int c) {
        if (!contains(str)) {
            prepend(str, c);
            uniqueSize++;
        } else {
            findNode(str).count += c;
        }
        size += c;
        modCount++;
    }
    
    private int removeAmount(String str, int c) {
        Node found = findNode(str);
        if (found != null) {
            if (found.count > c) {
                found.count -= c;
                size -= c;
                modCount++;
                return found.count;
            } else {
                removeAll(str);
                return 0;
            }
        }
        return -1;
    }
    
    
    // -----------------------------------------------------------
    // Inner Classes
    // -----------------------------------------------------------
    
    public class Iterator implements LinkedYarnIteratorInterface {
        LinkedYarn owner;
        Node current;
        int itModCount;
        int itWordCount;
        
        Iterator(LinkedYarn y) {
            owner = y;
            current = y.head;
            itModCount = y.modCount;
            itWordCount = 1;
        }
        
        public boolean hasNext() {
            return isValid() && (current.next != null || itWordCount < current.count);
        }
        
        public boolean hasPrev() {
            return isValid() && (current.prev != null || itWordCount > 1);
        }
        
        public boolean isValid() {
            return itModCount == owner.modCount;
        }
        
        public String getString() {
            if (!isValid()) {
                return null;
            }
            return current.text;
        }

        public void next() {
            testValidity();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (itWordCount < current.count) {
                itWordCount++;
            } else {
                current = current.next;
                itWordCount = 1;
            }
        }
        
        public void prev() {
            testValidity();
            if (!hasPrev()) {
                throw new NoSuchElementException();
            }
            if (itWordCount > 1) {
                itWordCount--;
            } else {
                current = current.prev;
                itWordCount = current.count;
            }
        }
        
        public void replaceAll(String toReplaceWith) {
            testValidity();
            Node found = owner.findNode(toReplaceWith);
            if (current == found) {
                return;
            }
            current.text = toReplaceWith;
            if (found != null) {
                current.count += found.count;
                deleteNode(found);
            }
            modCount++;
            itModCount++;
        }
        
        private void testValidity() {
            if (!isValid()) {
                throw new IllegalStateException();
            }
        }
    }
        
    class Node {
        Node next; 
        Node prev;
        String text;
        int count;
        
        Node(String t, int c) {
            text = t;
            count = c;
        }
    }
}
