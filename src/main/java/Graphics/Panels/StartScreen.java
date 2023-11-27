package Graphics.Panels;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class StartScreen extends JPanel implements MouseListener {
    private BufferedImage start;
    private int choice;

    public StartScreen() {
        try {
            start = ImageIO.read(Objects.requireNonNull(StartScreen.class.getResource("/Images/Screens/StartScreen.jpg")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        //switch(choice){
        /*case 1: */
        g.drawImage(start, 0, 0, 1920, 1080, null);
        // }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x >= 0 && x <= 295 && y >= 0 && y <= 124){
            System.out.println("Downloaded Rules");
            downloadRules();
        }
        if(x>=443 && x<=924 && y>=562 && y<=706){
            System.out.println("Game Started!");
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
    private void downloadRules(){
        if (Desktop.isDesktopSupported()) {
            try {
                InputStream in = getClass().getResourceAsStream("GizmosRules.pdf");
                File temp = File.createTempFile("GizmosRules", ".pdf");
                temp.deleteOnExit();
                assert in != null;
                Files.copy(in, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
                Desktop.getDesktop().open(temp);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

