package Graphics;

import javax.swing.*;

/**
 * This holds the game in a JFrame.
 */
public class GraphicsFrame extends JFrame
{
   private final static int WEIGHT = 1600;
    private final static int HEIGHT = 1600;
    public GraphicsFrame()
    {
        super("Gizmos");
        setSize(WEIGHT, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GraphicsMasterPanel());
        setVisible(true);
    }
}
