package Graphics;

import javax.swing.*;

/**
 * This holds the game in a JFrame.
 */
public class GraphicsFrame extends JFrame {
    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;
    public GraphicsFrame() {
        super("Gizmos");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new GamePanel());
        setVisible(true);
    }
}
