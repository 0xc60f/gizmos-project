package Graphics;

import javax.swing.*;

/**
 * This holds the game in a JFrame.
 */
public class GraphicsFrame extends JFrame {
    private final int WIDTH = 1600;
    private final int HEIGHT = 900;
    public GraphicsFrame() {
        super("Gizmos");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel g = new GamePanel();
        add(g);
        setVisible(true);
        g.play();
    }
}
