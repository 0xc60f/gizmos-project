package Graphics;

import javax.swing.*;

/**
 * This holds the game in a JFrame.
 */
public class GraphicsFrame extends JFrame {
    public GraphicsFrame() {
        super("Gizmos");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GraphicsMasterPanel());
        setVisible(true);
    }
}
