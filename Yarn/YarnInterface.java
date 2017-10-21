package yarn;

public interface YarnInterface {

    boolean isEmpty();
    int getSize();
    int getUniqueSize();
    boolean insert(String toAdd);
    int remove(String toRemove);
    int count(String toCount);
    void removeAll(String toNuke);
    boolean contains(String toCheck);
    String getNth(int n);
    String getMostCommon();
    void swap(Yarn other);
    String toString();
    
}
