package linked_yarn;

public interface LinkedYarnIteratorInterface {

    boolean isValid ();
    boolean hasNext ();
    boolean hasPrev ();
    String getString ();
    void next ();
    void prev ();
    void replaceAll (String toReplaceWith);
    
}
