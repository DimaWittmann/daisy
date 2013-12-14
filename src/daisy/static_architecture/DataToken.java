package daisy.static_architecture;

/**
 *
 * @author Wittman
 */
public class DataToken {
    public int destination;
    public int destPosition;
    
    public int data;

    public DataToken(int destination, int destPosition, int data) {
        this.destination = destination;
        this.destPosition = destPosition;
        this.data = data;
    }
    
    
}
