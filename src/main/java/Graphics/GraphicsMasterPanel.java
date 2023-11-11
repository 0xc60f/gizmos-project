package Graphics;

import Logic.Game;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Objects;

public class GraphicsMasterPanel extends JPanel implements MouseListener {
    private BufferedImage start;
    private int choice;
    private Game game;
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
        /*case 1: */g.drawImage(start, 0, 0, 1366, 768, null);
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
    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

    private static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
    private void downloadRules(){
        String url = "https://cmon-files.s3.amazonaws.com/pdf/assets_item/resource/126/Rulebook_Gizmos.pdf";
        String home = System.getProperty("user.home");

        try {
            downloadUsingNIO(url, home+"/Downloads/GizmosRules.pdf");

            downloadUsingStream(url, home+"/Downloads/GizmosRules.pdf");
            Desktop.getDesktop().open(new File(home+"/Downloads/GizmosRules.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
