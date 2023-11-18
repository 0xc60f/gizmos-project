package Graphics;

import Classes.*;

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
import static java.lang.System.*;

public class GamePanel extends JPanel implements MouseListener
{
    private BufferedImage start, gameScreen, black1Rank1, multiColor1Rank3;
    private int choice;
    private ArrayList<Player> playerList;
    private TreeMap<Integer, Integer> archiveCardCoord;
    private Deck deck;
    private Player currentPlayer;
    private Player firstPlayer;
    private boolean gameEnd;
    private boolean gameStart;
    private EnergyDispenser energyDispenser;
    private String prompt;
    private boolean fileButtonVisible, pickButtonVisible, buildButtonVisible, researchButtonVisible, endTurnButtonVisible, fieldButtonVisible, archiveButtonVisible;
    private boolean fileButtonClicked, pickButtonClicked, buildButtonClicked, researchButtonClicked, endTurnButtonClicked, fieldButtonClicked, archiveButtonClicked;
    private boolean marble1Clicked, marble2Clicked, marble3Clicked, marble4Clicked, marble5Clicked, marble6Clicked;
    private boolean card1Clicked, card2Clicked, card3Clicked, card4Clicked, card5Clicked, card6Clicked, card7Clicked, card8Clicked, card9Clicked;
    private boolean tier1DeckClicked, tier2DeckClicked, tier3DeckClicked, rulesButtonClicked;
    public GamePanel()
    {
        try
        {
            start = ImageIO.read(Objects.requireNonNull(GamePanel.class.getResource("/Images/Screens/startScreen.JPG")));
            gameScreen = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/Screens/gameSreen.png"))));
            black1Rank1 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/GizmoCards/Black1Rank1.png"))));
            multiColor1Rank3 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/GizmoCards/MultiColor1Rank3.png"))));
        }
        catch (Exception e){
            System.out.println(e.getMessage() +" hello");
            return;
        }
        addMouseListener(this);

        playerList = new ArrayList<>();
        archiveCardCoord = new TreeMap<Integer, Integer>();

        IntStream.rangeClosed(1, 4).forEach(i -> playerList.add(new Player(i)));

        try {
            deck = new Deck();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        currentPlayer = playerList.get(0); //not sure of
        firstPlayer = playerList.get(0);
        gameEnd = false;
        gameStart = false;
        energyDispenser = new EnergyDispenser();
        fileButtonVisible = false; pickButtonVisible = false; buildButtonVisible = false; researchButtonVisible = false; endTurnButtonVisible = false; fieldButtonVisible = false; archiveButtonVisible = false;
        fileButtonClicked = false; pickButtonClicked = false; buildButtonClicked = false; researchButtonClicked = false; endTurnButtonClicked = false; fieldButtonClicked = false; archiveButtonClicked = false;
        marble1Clicked = false; marble2Clicked = false; marble3Clicked = false; marble4Clicked = false; marble5Clicked = false; marble6Clicked = false;
        card1Clicked = false; card2Clicked = false; card3Clicked = false; card4Clicked = false; card5Clicked = false; card6Clicked = false; card7Clicked = false; card8Clicked = false; card9Clicked = false;
        tier1DeckClicked = false; tier2DeckClicked = false; tier3DeckClicked = false; rulesButtonClicked = false;

        //set archive card coordinates
//        for (int i = 0; i < 7; i++)
//        {
//            int x = 200;
////            archiveCardCoord.put(, )
//        }
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
//            waitFor4ActionClick();
            if (fileButtonClicked == true)
            {
                setPrompt("Please choose a card on the field to file:");
                //wait for player input, will write code later

                int num = getCardNumberPicked();
                p.file(pickedCard(num));
            }
            else if (pickButtonClicked == true)
            {
                setPrompt("Please choose marble from the energy dispenser to add to your ring:");
                //wait for player input, will write code later
                int num = getMarbleNumberPicked();
                p.pickFrom6(num, energyDispenser.getFirstSix());
            }
            else if (buildButtonClicked == true)
            {
                setPrompt("Please choose a card from the field or your archive to build:");
                //wait for player input to pick either field or archive, will write code later

                if (fieldButtonClicked == true)
                {
                    setPrompt("Please choose a card from the field to build:");
                    //wait for player input to pick either field or archive, will write code later

                    int num = getCardNumberPicked();
                    EnergyRing temp = p.getPlayerRingClass();
                    p.build(temp.getNumOfRed(), temp.getNumOfBlue(), temp.getNumOfYellow(), temp.getNumOfBlack(), pickedCard(num));
                }
                else if (archiveButtonClicked == true)
                {
                    setPrompt("Please choose a card from your archive to build:");
                    //wait for player input to pick either field or archive, will write code later
                }
            }
            else if (researchButtonClicked == true)
            {
                setPrompt("Please choose a tier of cards to research from");
                //wait for player input to pick either field or archive, will write code later


            }

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

    /**
     * @return the number of the card that the player picked. the boolean card_clicked is set to true in the mouseListener
     */
    public int getCardNumberPicked()
    {
        if (card1Clicked == true)
            return 1;
        else if (card2Clicked == true)
            return 2;
        else if (card3Clicked == true)
            return 3;
        else if (card4Clicked == true)
            return 4;
        else if (card5Clicked == true)
            return 5;
        else if (card6Clicked == true)
            return 6;
        else if (card7Clicked == true)
            return 7;
        else if (card8Clicked == true)
            return 8;
        else if (card9Clicked == true)
            return 9;
        return 1;
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
            case 8: return deck.getTier3()[0];
            case 9: return deck.getTier3()[1];
        }
        return null;
    }

    public int getMarbleNumberPicked()
    {
        if (marble1Clicked == true)
            return 1;
        else if (marble2Clicked == true)
            return 2;
        else if (marble3Clicked == true)
            return 3;
        else if (marble4Clicked == true)
            return 4;
        else if (marble5Clicked == true)
            return 5;
        else if (marble6Clicked == true)
            return 6;
        return 1;
    }

    public void paint(Graphics g)
    {
//        super.paint(g);
//        Menu Screen
        if(gameStart == false)
            g.drawImage(start, 0, 0, 1600, 900, null);

        out.println(getWidth()+" "+ getHeight());
//        Rules downloaded
        if (rulesButtonClicked == true)
            drawRulesDownloaded(g);

//        Game Screen
        if (gameStart == true) {
            g.drawImage(gameScreen, 0, 0, 1600, 900, null);
            fileButtonVisible = true; pickButtonVisible = true; buildButtonVisible = true; researchButtonVisible = true;
            g.drawImage(black1Rank1, 180, 25, 150, 150, null);
            g.drawImage(multiColor1Rank3, 310, 25, 150, 150, null);
            g.drawImage(black1Rank1, 440, 25, 150, 150, null);
            g.drawImage(multiColor1Rank3, 570, 25, 150, 150, null);
            g.drawImage(black1Rank1, 700, 25, 150, 150, null);
            g.drawImage(multiColor1Rank3, 830, 25, 150, 150, null);
            g.drawImage(black1Rank1, 960, 25, 150, 150, null);

            g.drawImage(black1Rank1, 560, 705, 100, 100, null);
            g.drawImage(black1Rank1, 660, 705, 100, 100, null);
            g.drawImage(black1Rank1, 760, 705, 100, 100, null);
            g.drawImage(black1Rank1, 860, 705, 100, 100, null);
            g.drawImage(black1Rank1, 960, 705, 100, 100, null);


            g.drawImage(black1Rank1, 560, 800, 100, 100, null);
            g.drawImage(black1Rank1, 660, 800, 100, 100, null);
            g.drawImage(black1Rank1, 760, 800, 100, 100, null);
            g.drawImage(black1Rank1, 860, 800, 100, 100, null);
            g.drawImage(black1Rank1, 960, 800, 100, 100, null);

        }
        // four buttons

        //g.drawString(prompt, 800, 700); // just made some random coordinates for where the prompt is gonna be, change later

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        if (x >= 566 && x <= 1052 && y >= 734 && y <= 805 && gameStart == false){
            rulesButtonClicked = true;
            repaint();
            System.out.println("Downloaded Rules");
            waitForSeconds(2);
            downloadRules();
        }
        if(x>=514 && x<= 1086 && y>=586 && y<=721){
            System.out.println("Game Started!");
            gameStart = true;
        }
        if (x >= 200 && y >= 30 && x <= 320 && y <= 160)
            System.out.println("card 1 archive clicked");
        if (x >= 330 && y >= 30 && x <= 450 && y <= 160)
            System.out.println("card 2 archive clicked");

        out.println("( "+ x +", "+ y +" )");
        // player clicks on card to file from the 3 tiers
        // player clicks on 4 buttons
        // after player clicks on build, show two buttons: field and archive and allow player to choose one
        // player clicks on one of the 3 tiers and then clicks on a card to research
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {    }
    @Override
    public void mouseReleased(MouseEvent e) {    }
    @Override
    public void mouseEntered(MouseEvent e) {    }
    @Override
    public void mouseExited(MouseEvent e) {    }
    public void drawRulesDownloaded(Graphics g)
    {
        Font f = new Font("Serif", Font.BOLD, 35);
        g.setFont(f);
        g.setColor(Color.WHITE);
        g.drawString("Rules successfully downloaded! Loading it for you right now...", 300, 50);
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
