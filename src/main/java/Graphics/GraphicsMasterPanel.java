package Graphics;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Objects;

public class GraphicsMasterPanel extends JPanel implements MouseListener {
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
        addMouseListener(this);
    }
    public void paint(Graphics g){
        super.paint(g);;
        //switch(choice){
            /*case 1: */g.drawImage(start, 0, 0, 1920, 1080, null);
       // }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
        System.out.println(e.getX() + " " + e.getY());
        int x = e.getX();
        int y = e.getY();
        if (x >= 0 && x <= 409 && y >= 0 && y <= 171){
            System.out.println("Downloaded Rules");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
