import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * This is the display that will show the animation.
 *
 * @author Nicolette Fink
 * @version 2.1
 */
public class Display extends JPanel {
    
    private Graphics g = getGraphics();
    private Location[] points;
    private Location[] points1;
    private Location[] points2;
    private Location[] points3;
    private Timer timer;
    private int count;
    
    /**
     * Constructor for the display.
     * @param points1 The set of initial points for the "A" animation.
     * @param points2 The set of initial points for the "b" animation.
     * @param points3 The set of initial points for the "C" animation.
     */
    public Display(Location[] points1,Location[] points2,Location[] points3) {
        this.points = points1;
        this.points1 = points1;
        this.points2 = points2;
        this.points3 = points3;
        timer = new Timer(200, new TimerListener());
        timer.stop();
        count = 0;
    }
    
    /**
     * Class that manages the timer listener.
     */
    private class TimerListener implements ActionListener {
        /**
         * Updates the display in time with the timer.
         */
        public void actionPerformed(ActionEvent ae) {
            updateDisplay();
        }
    }
    
    /**
     * Updates the display by rotating the letter.
     */
    public void updateDisplay() {
        if (count <= 46) {
            points = points1;
            count++;
            for (Location point: points) {
                if (point.getX() > 200 + point.getRange()) {
                    point.setDirection("left");
                } else if (point.getX() < 200 - point.getRange()) {
                    point.setDirection("right");
                }
                if (point.getDirection().equals("left")) {
                    point.setX(point.getX() - point.getStep());
                } else if (point.getDirection().equals("right")) {
                    point.setX(point.getX() + point.getStep());
                }
            }
        } else if (count <= 167) {
            points = points2;
            count++;
            for (Location point: points) {
                if (point.getY() > 230 + point.getRange()) {
                    point.setDirection("down");
                } else if (point.getY() < 230 - point.getRange()) {
                    point.setDirection("up");
                }
                if (point.getDirection().equals("down")) {
                    point.setY(point.getY() - point.getStep());
                } else {
                    point.setY(point.getY() + point.getStep());
                }
            }
        } else if (count <= 203) {
            points = points3;
            count++;
            for (Location point: points) {
                Point p = new Point(point.getX(),point.getY());
                AffineTransform rotation = new AffineTransform();
                rotation.rotate(Math.PI / 6, 200, 230);
                rotation.transform(p, p);
                point.setX(p.x);
                point.setY(p.y);
            }
        } else {
            count = 0;
        }
        if (timer.isRunning()) {
            draw(g);
            repaint();
        }
    }

    /**
     * Paints the graphics on the display.
     * @param g The graphics object to be painted.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (g != null) {
            draw(g);
        }
        repaint();
    }
    
    /**
     * Draws the points that make up the letter.
     * @param g The graphics object to be painted.
     */
    public void draw(Graphics g) {
        for (int i = 0; i < points.length - 1; i = i + 2) {
            if (g != null) {
                Graphics2D line = (Graphics2D) g;
                line.setPaint(Color.WHITE);
                Location point1 = points[i];
                Location point2 = points[i + 1];
                line.drawLine(point1.getX(),point1.getY(),point2.getX(),point2.getY());
            }
        }
    }
    
    /**
     * Returns the timer.
     * @return The timer.
     */
    public Timer getTimer() {
        return timer;
    }
    
    /**
     * Pauses the timer.
     */
    public void pauseTimer() {
        timer.stop();
    }
    
    /**
     * Start the timer.
     */
    public void startTimer() {
        timer.start();
    }
}