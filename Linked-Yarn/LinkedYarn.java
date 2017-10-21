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
            if (!isValid()) {
                return false;
            }
            return current.next != null || itWordCount < current.count;
        }
        
        public boolean hasPrev() {
            if (!isValid()) {
                return false;
            }
            return current.prev != null || itWordCount > 1;
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
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (!isValid()) {
                throw new IllegalStateException();
            }
            if (itWordCount < current.count) {
                itWordCount++;
            } else {
                current = current.next;
                itWordCount = 1;
            }
        }
        
        public void prev() {
            if (!hasPrev()) {
                throw new NoSuchElementException();
            }
            if (!isValid()) {
                throw new IllegalStateException();
            }
            if (itWordCount > 1) {
                itWordCount--;
            } else {
                current = current.prev;
                itWordCount = current.count;
            }
        }
        
        public void replaceAll(String toReplaceWith) {
            if (!isValid()) {
                throw new IllegalStateException();
            }
            Node found = owner.findNode(toReplaceWith);
            current.text = toReplaceWith;
            if (found != null) {
                current.count += found.count;
                deleteNode(found);
            }
            modCount++;
            itModCount++;
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

/*
        In comparing Yarn and LinkedYarn with each other, each had some easier and harder components to program compared to the
    other. In terms of Yarn, the hardest part was definitely getNth. The issue with getNth, other than not understanding its
    use originally, was not knowing how to program and efficient method of returning the nth String. For LinkedYarn, the most
    difficult methods were definitely the remove and removeAll methods. These methods were easier in Yarn due to random access,
    since I could instantly pinpoint the location of which String I wanted to remove, but for LinkedYarn I had to go through
    every single Node until I either found the one I was looking for or got to the end and did not find it. Adding on to the
    difficulty, if remove left the String count at zero or I called removeAll, repairing the Node references to delete the
    current Node was more difficult to understand and implement compared to replacing the current index of an array with the
    last Strand in the array. One thing to note for LinkedYarn is that, for the most part besides having to understand Nodes,
    it was a bit easier to start working on compared to Yarn because LinkedYarn is essentially the same thing as Yarn but
    that it uses Nodes instead of an array. This meant that methods from Yarn essentially translated to LinkedYarn with much
    ease in terms of concepts. 

        A scenario in which I would prefer to use the sequential list implementation over the linked list implementation would
    be for players on the field during a soccer match. Since soccer always has twenty-two players on the field, there could be
    an array which holds the information of each player on the field, where the information consists of each player’s name,
    team, number, position, goals, assists, and many other pieces of information. Even when a substitution is made, since
    sequential lists support random access it could find the index of the player being substituted off and replace it with the
    player being substituted on the field. If a player has received a red card, a boolean field can be updated to show that,
    and when a player is trying to be substituted in there can be a check to make sure that the boolean field regarding a red
    card is false. A scenario in which I would prefer to use the linked list implementation over the sequential list
    implementation would be for keeping a record of all the players in a soccer club’s history. Since the list of all the
    player in a club’s history is unlimited and always expanding, a linked list would be best for this situation. When a
    player comes up through the youth academy or is bought by the club, a new node of that player would be created with all of
    the information of the player such as name, position, club appearances, club goals, time at the club, yellow cards, and
    many other aspects, and can simply be prepended to the linked list. If that player retires or transfers to another team,
    then the time at club data would be updated to appropriately represent that. 
*/