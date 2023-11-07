package Graphics;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.Objects;

public class GraphicsMasterPanel extends JPanel{
    private BufferedImage start;
    private int choice;
    public GraphicsMasterPanel(){
        try{
            start = ImageIO.read(Objects.requireNonNull(GraphicsMasterPanel.class.getResource("/Images/Screens/StartScreen.jpg")));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        //addKeyListener(this);
    }
    public void paint(Graphics g){
        super.paint(g);;
        //switch(choice){
            /*case 1: */g.drawImage(start, 0, 0, 1600, 900, null);
       // }
    }
}
