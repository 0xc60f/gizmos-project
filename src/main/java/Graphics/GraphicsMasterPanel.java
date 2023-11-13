package Graphics;

import Classes.*;
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphicsMasterPanel extends JPanel implements MouseListener
{
    private BufferedImage start;
    private int choice;
    private ArrayList<Player> playerList;
    private Deck deck;
    private Player currentPlayer;
    private Player firstPlayer;
    private boolean gameEnd;
    private boolean gameStart;
    private EnergyDispenser energyDispenser;
    private String prompt;
    private boolean fileButtonVisible, pickButtonVisible, buildButtonVisible, researchButtonVisible, endTurnButtonVisible;
    private boolean fileButtonClicked, pickButtonClicked, buildButtonClicked, researchButtonClicked, endTurnButtonClicked;
    private int card1, card2, card3, card4, card5, card6, card7, card8, card9;
    private boolean card1Clicked, card2Clicked, card3Clicked, card4Clicked, card5Clicked, card6Clicked, card7Clicked, card8Clicked, card9Clicked;
    public GraphicsMasterPanel()
    {
        try
        {
            start = ImageIO.read(Objects.requireNonNull(GraphicsMasterPanel.class.getResource("/Images/Screens/StartScreen.jpg")));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        addMouseListener(this);

        playerList = new ArrayList<>();
        IntStream.rangeClosed(1, 4).forEach(i -> playerList.add(new Player(i)));
        deck = new Deck();
        currentPlayer = playerList.get(0); //not sure of
        firstPlayer = playerList.get(0);
        gameEnd = false;
        gameStart = true;
        energyDispenser = new EnergyDispenser();
        fileButtonVisible = false; pickButtonVisible = false; buildButtonVisible = false; researchButtonVisible = false; endTurnButtonVisible = false;
        fileButtonClicked = false; pickButtonClicked = false; buildButtonClicked = false; researchButtonClicked = false; endTurnButtonClicked = false;
        card1 = 1; card2 = 2; card3 = 3; card4 = 4; card5 = 5; card6 = 6; card7 = 7; card8 = 8; card9 = 9;
        card1Clicked = false; card2Clicked = false; card3Clicked = false; card4Clicked = false; card5Clicked = false; card6Clicked = false; card7Clicked = false; card8Clicked = false; card9Clicked = false;
    }

    /**
     * runs the game loop and calls playerTurn methods
     */
    public void play()
    {

    }


    /**
     * runs the player's turn
     */
    public void playerTurn(Player p)
    {
        if (endGameConditions() == true)
            gameEnd = true;
        if ((gameEnd == false) || (gameEnd == true && currentPlayer != firstPlayer))
        {
            setPrompt("Please choose an action:");
            //repaint()
            waitFor4ActionClick();
            if (fileButtonClicked == true)
            {
                setPrompt("Please choose a card on the field to file:");
                //wait for player input, will write code later


            }

            int playerNum = p.getPlayerNumber();
        }
    }

    /**
     * returns the currentPlayer
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     This method sets the order and returns the current player, and if its the last player
     then it starts back to 1 and will go through 1-4
     */
    public void setNextPlayer()
    {
        currentPlayer = playerList.get((playerList.indexOf(currentPlayer) + 1) % playerList.size());
    }

    /**
     * What happens after the game ends, calls the calculate scores method, and returns an arrayList of the ranking, and tiebreakers
     */
    public ArrayList<Player> endGame()
    {
        ArrayList<Player> ranking = new ArrayList<>(playerList);
        ranking.sort(Comparator.comparing(Player::getScore).reversed());
        //If any players have the same two scores, sort only those players by their number of cards
        for (int i = 0; i < ranking.size() - 1; i++)
        {
            if (ranking.get(i).getScore() == ranking.get(i + 1).getScore())
            {
                int personOneCards = ranking.get(i).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                int personTwoCards = ranking.get(i + 1).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                if (personOneCards < personTwoCards)
                    Collections.swap(ranking, i, i + 1);

            }
        }
        //If any players have the same score and number of cards, sort only those players by the number of marbles in their reserve
        for (int i = 0; i < ranking.size() - 1; i++)
        {
            if (ranking.get(i).getScore() == ranking.get(i + 1).getScore())
            {
                int personOneCards = ranking.get(i).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                int personTwoCards = ranking.get(i + 1).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                if (personOneCards == personTwoCards)
                {
                    int personOneMarbles = ranking.get(i).getEnergyRing().size();
                    int personTwoMarbles = ranking.get(i + 1).getEnergyRing().size();
                    if (personOneMarbles < personTwoMarbles)
                        Collections.swap(ranking, i, i + 1);
                }
            }
        }
        //If finally, any players are still tied, compare only those players using the compareTo method in the Player class. THe greater number is ranked above
        for (int i = 0; i < ranking.size() - 1; i++)
        {
            if (ranking.get(i).getScore() == ranking.get(i + 1).getScore())
            {
                int personOneCards = ranking.get(i).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                int personTwoCards = ranking.get(i + 1).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                if (personOneCards == personTwoCards)
                {
                    int personOneMarbles = ranking.get(i).getEnergyRing().size();
                    int personTwoMarbles = ranking.get(i + 1).getEnergyRing().size();
                    if (personOneMarbles == personTwoMarbles)
                    {
                        if (ranking.get(i).compareTo(ranking.get(i + 1)) < 0)
                            Collections.swap(ranking, i, i + 1);
                    }
                }
            }
        }
        return ranking;
    }

    /**
     * checks to see if the game will end
     */
    public boolean endGameConditions()
    {
        //Check all players and their cards. Return true if any player has at least four cards where the GizmoLevel is LEVEL3
        for (Player p : playerList) {
            Toolbar playerToolbar = p.getToolBar();
            //The getCards method returns a treemap of arraylists of gizmocards. Use streams to accumulate all the values in all of the arraylists into one arraylist
            ArrayList<GizmoCard> allCards = playerToolbar.getCards().values().stream().flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));
            if (allCards.size() >= 16) return true;
        }
        if (deck.isDeckEmpty() || energyDispenser.isEmpty()) return true;
        for (Player p : playerList) {
            Toolbar playerToolbar = p.getToolBar();
            ArrayList<GizmoCard> allCards = new ArrayList<>();
            for (ArrayList<GizmoCard> gizmoCards : playerToolbar.getCards().values()) {
                allCards.addAll(gizmoCards);
            }
            int numlevel3 = (int) allCards.stream().filter(c -> c.getLevel().equals(GizmoLevel.LEVEL3)).count();
            if (numlevel3 >= 4) return true;
        }
        return false;
    }

    public GizmoCard pickedCard(int cardClicked)
    {
        switch (cardClicked)
        {
            case 1: return deck.getTier1()[0];
            case 2: return deck.getTier1()[1];
            case 3: return deck.getTier1()[2];
            case 4: return deck.getTier1()[3];
            case 5: return deck.getTier2()[0];
            case 6: return deck.getTier2()[1];
            case 7: return deck.getTier2()[2];
            case 8: return deck.getTier3()[1];
            case 9: return deck.getTier3()[1];
        }
        return deck.getTier1()[0];
    }


    public void paint(Graphics g)
    {
//        super.paint(g);
//        Menu Screen
        g.drawImage(start, 0, 0, 1366, 768, null);

//        Game Screen
//        if (gameStart == true)
//            g.drawImage();
        //g.drawString(prompt, 800, 700); // just made some random coordinates for where the prompt is gonna be, change later
    }

    public void setPrompt(String str)
    {
        prompt = str;
    }

    public void waitForSeconds(double seconds)
    {
        try
        {
            Thread.sleep((int) (seconds * 1000));
        } catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void waitFor4ActionClick()
    {
        while (fileButtonClicked == false && pickButtonClicked == false && researchButtonClicked == false && buildButtonClicked == false)
        {
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        if (x >= 0 && x <= 295 && y >= 0 && y <= 124){
            System.out.println("Downloaded Rules");
            downloadRules();
        }
        if(x>=443 && x<=924 && y>=562 && y<=706){
            System.out.println("Game Started!");
            gameStart = true;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {    }
    @Override
    public void mouseReleased(MouseEvent e) {    }
    @Override
    public void mouseEntered(MouseEvent e) {    }
    @Override
    public void mouseExited(MouseEvent e) {    }
    private static void downloadUsingStream(String urlStr, String file) throws IOException
    {
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

    private static void downloadUsingNIO(String urlStr, String file) throws IOException
    {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

    private void downloadRules()
    {
        String url = "https://cmon-files.s3.amazonaws.com/pdf/assets_item/resource/126/Rulebook_Gizmos.pdf";
        String home = System.getProperty("user.home");

        try
        {
            downloadUsingNIO(url, home+"/Downloads/GizmosRules.pdf");

            downloadUsingStream(url, home+"/Downloads/GizmosRules.pdf");
            Desktop.getDesktop().open(new File(home+"/Downloads/GizmosRules.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
