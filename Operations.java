package Calc;
public interface Operations {
    float evaluate();
    // Composite helper methods
    default void add(Operations component) {}
    default void remove(Operations component) {}
    default Operations getChild(int index) {
        return null; 
    }
}


