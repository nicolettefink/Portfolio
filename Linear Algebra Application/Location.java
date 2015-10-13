/**
 * Location of a point relative to the center of the panel
 * it is being placed on and contains information allowing
 * it to be rotated around an axis.
 *
 * @author Nicolette Fink
 * @version 1.1
 */
public class Location {

    int[] location = new int[2];
    String direction;
    int step;
    int range;
    
    /**
     * Constructs a location.
     * @param x The x-coordinate on the panel.
     * @param y The y-coordinate on the panel.
     * @param direction The direction the point is moving in.
     * @param step The distance the point moves each time.
     * @param range How far away the point is from the center.
     */
    public Location(int x, int y, String direction, int step, int range) {
        location[0] = x;
        location[1] = y;
        this.direction = direction;
        this.step = step;
        this.range = range;
    }
    
    /**
     * Gets the x-coordinate of the location.
     * @return The x-coordinate.
     */
    public int getX() {
        return location[0];
    }
    
    /**
     * Gets the y-coordinate of the location.
     * @return The y-coordinate.
     */
    public int getY() {
        return location[1];
    }
    
    /**
     * Sets the x-coordinate of the location.
     * @param x The new x-coordinate.
     */
    public void setX(int x) {
        location[0] = x;
    }
    
    /**
     * Sets the y-coordinate of the location.
     * @param y The new y-coordinate.
     */
    public void setY(int y) {
        location[1] = y;
    }
    
    /**
     * Gets the direction of motion of the point.
     * @return The direction.
     */
    public String getDirection() {
        return direction;
    }
    
    /**
     * Sets the direction of motion for the point.
     * @param direction The new direction.
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    /**
     * Gets the step distance of the point.
     * @return The step distance.
     */
    public int getStep() {
        return step;
    }
    
    /**
     * Sets the step distance of the point.
     * @param step The new step distance.
     */
    public void setStep(int step) {
        this.step = step;
    }
    
    /**
     * Gets the range of the point from the center.
     * @return The range.
     */
    public int getRange() {
        return range;
    }
    
    /**
     * Sets the range of the point.
     * @param range The new range.
     */
    public void setRange(int range) {
        this.range = range;
    }
}