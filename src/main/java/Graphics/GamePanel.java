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

import java.util.Iterator;
import java.util.ArrayList;
import java.util.TreeMap;


public class GamePanel extends JPanel implements MouseListener {
    private BufferedImage start, gameScreen, black1Rank1, multiColor1Rank3, energyRing, blankGameScreen, blackMarble, blueMarble, redMarble, yellowMarble;
    private BufferedImage firstPlayerTB, otherPlayerTB, tier1, tier2, tier3, promptBox, yellow9Rank1, Black2Rank1, Black3Rank1, Black7Rank1, Black9Rank1;
    private BufferedImage Rank1, Rank2, Rank3, prompt, victoryPoint1, victoryPoint5, fileButton, pickButton, buildButton, researchButton, yesButton, noButton, endTurnButton;
    private BufferedImage playerOne, playerTwo, playerThree, playerFour, first, second, third, fourth;
    private ArrayList<Player> playerList;
    private TreeMap<Integer, Integer> archiveCardCoord;
    private Deck deck;
    private Player currentPlayer;
    private Player firstPlayer;
    private EnergyDispenser energyDispenser;
    private String promptStr;
    private int marbleClickedIndex;
    private int erX, erY, erWidth, erMarbleR1X, erMarbleR1Y, erMarbleWidth, cardWidth;
    private int archX, archY, archWidth, archTitleX, archTitleY, archTitleLength;
    private int tbX, tbY, tbSectionWidth, tbWidth, tbLength;
    private int first6X, first6Y, first6Width;
    private int ownedCardsHeaderLength;
    private int tierX, tierY;
    private int VPTitleX, VPTitleY, VPTitleWidth, VPTitleLength, VPX, VPY, VPWidth, VPlength;
    private int promptBoxX, promptBoxY, promptBoxWidth, promptBoxLength, promptX, promptY, promptWidth, promptLength, promptROrBCardRow1X, promptROrBCardRow1Y, promptROrBCardRow2X, promptROrBCardRow2Y;
    private int smallPlayerCardWidth, smallTitleX, smallTitleY, smallTitleWidth, smallTitleLength, smallTBX, smallTBY, smallTBWidth, smallTBLength, smallTBSectionWidth;
    private int smallCardWidth, smallCardHeaderLength, smallERX, smallERY, smallERWidth, smallEMR1X, smallEMR1Y, smallEMWidth;
    private int smallVPTitleX, smallVPTitleY, smallVPTitleWidth, smallVPTitleLength, smallVPX, smallVPY, smallVPWidth, smallVPlength;
    private MarbleColor colorClicked;
    private GizmoCard cardClicked, cardResearching;
    private GizmoCard mostRecentCardActivated, mostRecentCardBuilt;
    private MarbleColor mostRecentColorPicked;
    private Marble mostRecentMarblePicked;
    private boolean archiveSectionClicked, convertSectionClicked, fileSectionClicked, pickSectionClicked, buildSectionClicked, researchingCardsClicked, first6Clicked;
    private boolean tier1SectionClicked, tier2SectionClicked, tier3SectionClicked, rulesButtonClicked, startOfPlayerTurn;
    private boolean fileButtonVisible, pickButtonVisible, buildButtonVisible, researchButtonVisible, endTurnButtonVisible, fieldButtonVisible, archiveButtonVisible, yesButtonVisible, noButtonVisible, researchingCardsVisible, marblesVisible;
    private boolean fileButtonClicked, pickButtonClicked, buildButtonClicked, researchButtonClicked, endTurnButtonClicked, fieldButtonClicked, archiveButtonClicked, yesButtonClicked, noButtonClicked, startGameButtonClicked;
    private boolean cardIsClicked, archiveCardClicked, drawOwnedMarbleOnlyForConvert, marbleColorClicked;
    private boolean archiveSectionClickable, convertSectionClickable, fileSectionClickable, pickSectionClickable, buildSectionClickable, researchingCardsClickable, first6Clickable;
    private boolean tier1SectionClickable, tier2SectionClickable, tier3SectionClickable, first6MarbleClickable, tier1CoverClicked, tier2CoverClicked, tier3CoverClicked;
    private boolean tier1CoverClickable, tier2CoverClickable, tier3CoverClickable, drawTier1Cards, drawTier2Cards, drawTier3Cards, drawResearchingCards, pickColorToResearchClicked;
    private boolean gameEnd, gameStart, chainReactionStart, chainReactionCardClicked, chainReactionCardsVisible, filed, built, picked, builtFromArchive, firstTimeFiling;
    private TreeMap<String, int[]> generalSectionCoord;
    private int displayPromptChoice, tierCardClickedIndex;
    public GamePanel() {

        initializeImages();

        addMouseListener(this);

        playerList = new ArrayList<>();
        archiveCardCoord = new TreeMap<Integer, Integer>();

        IntStream.rangeClosed(1, 4).forEach(i -> {
            try {
                playerList.add(new Player(i));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            deck = new Deck();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        firstPlayer = playerList.get(0);
        currentPlayer = firstPlayer;

        gameEnd = false;
        gameStart = false;
        energyDispenser = new EnergyDispenser();

        resetVisibleFlags();

        startOfPlayerTurn = false;
        cardIsClicked = false;
        archiveCardClicked = false;

        initializeAllXY();
        generalSectionCoord = new TreeMap<String, int[]>();
        initializeAllSectionCoord(generalSectionCoord);

//        initializePlayer();

    }

    public void initializeImages() {
        try {
            start = ImageIO.read(Objects.requireNonNull(GamePanel.class.getResource("/Images/Screens/startScreen.JPG")));
            gameScreen = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/Screens/gameScreen.png"))));
//            black1Rank1 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/GizmoCardsTrimmed/Black1Rank1.png"))));
//            multiColor1Rank3 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/GizmoCardsTrimmed/MultiColor1Rank3.png"))));
            energyRing = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/energyRing.png"))));
            blackMarble = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/BlackMarble.png"))));
            blueMarble = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/BlueMarble.png"))));
            redMarble = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/RedMarble.png"))));
            yellowMarble = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/YellowMarble.png"))));
            firstPlayerTB = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/firstPlayerToolbar.png"))));
            otherPlayerTB = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/otherPlayerToolbar.png"))));
            tier1 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/tier1.png"))));
            tier2 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/tier2.png"))));
            tier3 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/tier3.png"))));
            promptBox = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/promptBox.png"))));
//            yellow9Rank1 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/GizmoCardsTrimmed/yellow9Rank1.png"))));
//            Black2Rank1 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/GizmoCardsTrimmed/Black2Rank1.png"))));
//            Black3Rank1 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/GizmoCardsTrimmed/Black3Rank1.png"))));
//            Black7Rank1 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/GizmoCardsTrimmed/Black7Rank1.png"))));
//            Black9Rank1 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/GizmoCardsTrimmed/Black9Rank1.png"))));
            Rank1 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/Rank1.png"))));
            Rank2 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/Rank2.png"))));
            Rank3 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/Rank3.png"))));
            prompt = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/prompt.png"))));
            victoryPoint1 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/victoryPoint1.png"))));
            victoryPoint5 = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/victoryPoint5.png"))));
            fileButton = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/File.png"))));
            pickButton = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/Pick.png"))));
            buildButton = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/Build.png"))));
            researchButton = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/Research.png"))));
            endTurnButton = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/End_Turn.png"))));
            yesButton = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/Yes.png"))));
            noButton = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/No.png"))));
            playerOne = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/1.png"))));
            playerTwo = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/2.png"))));
            playerThree = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/3.png"))));
            playerFour = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/4.png"))));
            first = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/1st.png"))));
            second = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/2nd.png"))));
            third = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/3rd.png"))));
            fourth = ImageIO.read((Objects.requireNonNull(GamePanel.class.getResource("/Images/4th.png"))));

        } catch (Exception e) {
            System.out.println(e.getMessage() + " hello");
            return;
        }
    }

    public void resetMouseClickEvents() {
        fileButtonClicked = false;
        pickButtonClicked = false;
        buildButtonClicked = false;
        researchButtonClicked = false;
        endTurnButtonClicked = false;
        fieldButtonClicked = false;
        archiveButtonClicked = false;
        archiveSectionClicked = false;
        convertSectionClicked = false;
        fileSectionClicked = false;
        pickSectionClicked = false;
        buildSectionClicked = false;
        tier1SectionClicked = false;
        tier2SectionClicked = false;
        tier3SectionClicked = false;
        researchingCardsClicked = false;
        first6Clicked = false;
        yesButtonClicked = false;
        noButtonClicked = false;
        drawOwnedMarbleOnlyForConvert = false;
        archiveSectionClickable = false;
        convertSectionClickable = false;
        fileSectionClickable = false;
        pickSectionClickable = false;
        buildSectionClickable = false;
        researchingCardsClickable = false;
        first6Clickable = false;
        tier1SectionClickable = false;
        tier2SectionClickable = false;
        tier3SectionClickable = false;
        first6MarbleClickable = false;
        startGameButtonClicked = false;
        marbleColorClicked = false;
        tier1CoverClicked = false;
        tier2CoverClicked = false;
        tier3CoverClicked = false;
        tier1CoverClickable = false;
        tier2CoverClickable = false;
        tier3CoverClickable = false;
        drawTier1Cards = false;
        drawTier2Cards = false;
        drawTier3Cards = false;
        drawResearchingCards = false;
        pickColorToResearchClicked = false;
        colorClicked = null;
        cardClicked = null;
        chainReactionStart = false;
        chainReactionCardClicked = false;
        chainReactionCardsVisible = false;
        filed = false;
        built = false;
        picked = false;
        builtFromArchive = false;
        firstTimeFiling = false;
        for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.UPGRADE).size(); i++)
            currentPlayer.getToolBar().getCards().get(GizmoType.UPGRADE).get(i).setTriggered(false);
        for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.CONVERTOR).size(); i++)
            currentPlayer.getToolBar().getCards().get(GizmoType.CONVERTOR).get(i).setTriggered(false);
        for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.FILE).size(); i++)
            currentPlayer.getToolBar().getCards().get(GizmoType.FILE).get(i).setTriggered(false);
        for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.PICK).size(); i++)
            currentPlayer.getToolBar().getCards().get(GizmoType.PICK).get(i).setTriggered(false);
        for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.BUILD).size(); i++)
            currentPlayer.getToolBar().getCards().get(GizmoType.BUILD).get(i).setTriggered(false);

    }

    public void resetVisibleFlags() {
        fileButtonVisible = false;
        pickButtonVisible = false;
        buildButtonVisible = false;
        researchButtonVisible = false;
        endTurnButtonVisible = false;
        fieldButtonVisible = false;
        archiveButtonVisible = false;
        yesButtonVisible = false;
        noButtonVisible = false;
        researchingCardsVisible = false;
        marblesVisible = false;
    }

    /**
     * runs the game loop and calls playerTurn methods
     */
    public void play() {
        resetMouseClickEvents();
        resetVisibleFlags();
        repaint();
        if (startGameButtonClicked) {
            gameStart = true;
            currentPlayer = firstPlayer;
            repaint();
        }

        while (!endGameConditions() || (endGameConditions() && currentPlayer != firstPlayer)) {
            if (endGameConditions() && currentPlayer != firstPlayer)
                setPrompt("FINAL TURN! MAKE IT COUNT!");
            playerTurn(); //player turn

            if (currentPlayer.getPlayerNumber() == 4)
                currentPlayer = firstPlayer;
            else
                //You would need to add one to go to next player but also subtract one because index starts at 0, so you don't add or subtract here bc they cancel out
                currentPlayer = playerList.get(currentPlayer.getPlayerNumber()); //Player number is 1 based while index is 0 based
            setPrompt("Next player's turn");
            resetMouseClickEvents();
            repaint();
            waitForSeconds(1);
        }
        setPrompt("THAT'S GAME!");
        repaint();
        waitForSeconds(2);
        setPrompt("FINAL LEADERBOARD...");
        gameEnd = true;
        repaint();
        waitForSeconds(2);
        ArrayList<Player> finalRanking = endGame();
        repaint();

    }

    public void set4ActionsInvisible()
    {
        fileButtonVisible = false;
        pickButtonVisible = false;
        buildButtonVisible = false;
        researchButtonVisible = false;
    }

    public void resetCardClicked()
    {
        cardClicked = null;
    }

    public void initializePlayer()
    {
        try {
            playerList.get(0).getToolBar().getCards().get(GizmoType.BUILD).add(new GizmoCard(MarbleColor.BLUE,7,7, GizmoType.BUILD, "buildThisOrThatResearch", 7, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue3Rank3.png"))), MarbleColor.YELLOW, MarbleColor.RED, GizmoLevel.LEVEL3));
//            playerList.get(0).getToolBar().getCards().get(GizmoType.PICK).add(new GizmoCard(MarbleColor.BLACK, 1,1, GizmoType.PICK, "pickPickRandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black4Rank1.png"))), MarbleColor.BLUE, GizmoLevel.LEVEL1));
//            playerList.get(0).getToolBar().getCards().get(GizmoType.BUILD).add(new GizmoCard(MarbleColor.BLACK, 5, 5, GizmoType.BUILD, "buildThisOrThatGet2VictoryPoints", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black3Rank3.png"))), MarbleColor.RED, MarbleColor.BLUE, GizmoLevel.LEVEL3));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        playerList.get(0).getPlayerRingClass().setEnergyRing(7);
        playerList.get(0).getEnergyRing().add(new Marble(MarbleColor.RED));
        playerList.get(0).getEnergyRing().add(new Marble(MarbleColor.RED));
        playerList.get(0).getEnergyRing().add(new Marble(MarbleColor.RED));
        playerList.get(0).getEnergyRing().add(new Marble(MarbleColor.BLUE));
        playerList.get(0).getEnergyRing().add(new Marble(MarbleColor.BLUE));
        playerList.get(0).getEnergyRing().add(new Marble(MarbleColor.BLUE));
;

    }

    /**
     * runs the player's turn
     */
    public void playerTurn() {
        mostRecentCardActivated = null;
        startOfPlayerTurn = true;
        endTurnButtonVisible = true;
        displayPromptChoice = 1;
        HashSet<GizmoCard> triggeredCards = new HashSet<>();
        //check for the 4 main actions that can be activated
        if (currentPlayer.getArchive().size() < currentPlayer.getMaxArchive())
            fileButtonVisible = true;
        if (currentPlayer.getEnergyRing().size() < currentPlayer.getPlayerRingClass().getEnergyRingMax())
            pickButtonVisible = true;
        if (!currentPlayer.getEnergyRing().isEmpty())
            buildButtonVisible = true;
        if (currentPlayer.getArchive().size() < currentPlayer.getMaxArchive() || !currentPlayer.getEnergyRing().isEmpty())
            researchButtonVisible = true;
        setPrompt("Please choose an action:");
        repaint();
        waitFor4ActionClick();

        if (fileButtonClicked)
        {
            set4ActionsInvisible();
            firstTimeFiling = true;
            startOfPlayerTurn = false;
            setPrompt("Please choose a card from the 3 tiers to file.");
            tier1SectionClickable = true;
            tier2SectionClickable = true;
            tier3SectionClickable = true;
            filed = true;
            repaint();
            waitForFileCardChoice();
            fileAction();
            chainReactionStart = true;
            for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.FILE).size(); i++)
            {
                if (currentPlayer.getToolBar().getCards().get(GizmoType.FILE).get(i).getLevel() == GizmoLevel.LEVEL0)
                    triggeredCards.add(currentPlayer.getToolBar().getCards().get(GizmoType.FILE).get(i));
            }

        }
        else if (pickButtonClicked)
        {
            set4ActionsInvisible();
            startOfPlayerTurn = false;
            first6MarbleClickable = true;
            setPrompt("Please choose a marble to add to your energy ring");
            repaint();
            waitForPickMarbleClick();
//            waitForSeconds(0.5);
            pickFrom6Action();
            chainReactionStart = true;
        }
        else if (buildButtonClicked)
        {
            set4ActionsInvisible();
            startOfPlayerTurn = false;
            tier1SectionClickable = true;
            tier2SectionClickable = true;
            tier3SectionClickable = true;
            archiveSectionClickable = true;
            setPrompt("Please choose a card to build, either from the field or your archive");
            repaint();
            waitForBuildCardChoice();
//            waitForSeconds(0.5);
            buildAction();
            chainReactionStart = true;

        }
        //Research
        else if (researchButtonClicked)
        {
            set4ActionsInvisible();
            startOfPlayerTurn = false;
            tier1CoverClickable = true;
            tier2CoverClickable = true;
            tier3CoverClickable = true;
            tier1SectionClickable = true;
            tier2SectionClickable = true;
            tier3SectionClickable = true;
            displayPromptChoice = 4;
            researchingCardsVisible = true;
            setPrompt("Choose a tier to research from");
            repaint();
            waitForResearchTierChoice();
            if (tier1CoverClicked)
                for (int i = 0; i < currentPlayer.getMaxResearch(); i++)
                    currentPlayer.getCardsResearching().add(deck.getDeck1().pop());
            if (tier2CoverClicked)
                for (int i = 0; i < currentPlayer.getMaxResearch(); i++)
                    currentPlayer.getCardsResearching().add(deck.getDeck2().pop());
            if (tier3CoverClicked)
                for (int i = 0; i < currentPlayer.getMaxResearch(); i++)
                    currentPlayer.getCardsResearching().add(deck.getDeck3().pop());

            setPrompt("Choose a card to research");
            repaint();
            waitForResearchCardChoice();

            researchingCardsVisible = false;
            if (tier1CoverClicked)
                for (int i = 0; i < currentPlayer.getCardsResearching().size(); i++) {
                    if (currentPlayer.getCardsResearching().get(i) != cardClicked)
                        deck.getDeck1().add(0, currentPlayer.getCardsResearching().get(i));
                }
            if (tier2CoverClicked)
                for (int i = 0; i < currentPlayer.getCardsResearching().size(); i++) {
                    if (currentPlayer.getCardsResearching().get(i) != cardClicked)
                        deck.getDeck2().add(0, currentPlayer.getCardsResearching().get(i));
                }
            if (tier3CoverClicked)
                for (int i = 0; i < currentPlayer.getCardsResearching().size(); i++) {
                    if (currentPlayer.getCardsResearching().get(i) != cardClicked)
                        deck.getDeck3().add(0, currentPlayer.getCardsResearching().get(i));
                }
            drawTier1Cards = false;
            drawTier2Cards = false;
            drawTier3Cards = false;

            setPrompt("Do you want to File or Build that card?");
            buildButtonVisible = false;
            fileButtonVisible = false;
            displayPromptChoice = 5;
            fileButtonClicked = false;
            buildButtonClicked = false;
            researchButtonClicked = false;
            pickButtonClicked = false;
            repaint();
            waitFor4ActionClick();

            if (fileButtonClicked)
            {
                set4ActionsInvisible();
                drawResearchingCards = false;

                repaint();
                fileAction();
            }
            else if (buildButtonClicked)
            {
                set4ActionsInvisible();
                drawResearchingCards = false;
                repaint();
                buildAction();
            }
            chainReactionStart = true;
        }

        HashSet<GizmoCard> availableCards = getAllTriggeredCards();
        if (chainReactionStart)
        {
            while (!availableCards.isEmpty())
            {
                setPrompt("Please choose an available card to activate");
                displayPromptChoice = 7;
                chainReactionCardClicked = false;
                chainReactionCardsVisible = true;
                repaint();
                waitForChainReactionCardChoice();
                GizmoCard cardActivating = cardClicked;

                if (filed)
                {
                    if (cardActivating.getEffectType().equals("filePick1From6"))
                        filePick1From6();
                    else if (cardActivating.getEffectType().equals("filePick3Random"))
                        filePick3Random();
                    else if (cardActivating.getEffectType().equals("fileGet1VictoryPoint"))
                        fileGet1VictoryPoint();
                    filed = false;
                }
                else if (picked) {
                    pickEffects();
                    picked = false;
                }
                else if (built)
                {
                    if (cardActivating.getEffectType().equals("buildGet1VictoryPoint"))
                        buildGet1VictoryPoint();
                    else if (cardActivating.getEffectType().equals("buildPick1From6"))
                        buildPick1From6();
                    else if (cardActivating.getEffectType().equals("buildThisOrThatGet2VictoryPoints"))
                        buildThisOrThatGet2VictoryPoints();
                    else if (cardActivating.getEffectType().equals("buildThisOrThatGet1VictoryPoint"))
                        buildThisOrThatGet1VictoryPoint();
                    else if (cardActivating.getEffectType().equals("buildThisOrThatResearch"))
                        buildThisOrThatResearch();
                    else if (cardActivating.getEffectType().equals("buildThisOrThatPick1From6"))
                        buildThisOrThatPick1From6();
                    else if (cardActivating.getEffectType().equals("buildTier2Pick2From6") && cardActivating.getLevel().equals(GizmoLevel.LEVEL2))
                        buildTier2Pick2From6();
                    else if (cardActivating.getEffectType().equals("buildThisOrThatBuildTier1For0"))
                        buildThisOrThatBuildTier1For0();
                    else if (cardActivating.getEffectType().equals("buildThisOrThatFile"))
                        buildThisOrThatFile();


                    if (builtFromArchive){
                    if (cardActivating.getEffectType().equals("buildFromFileGet2VictoryPoints"))
                        buildFromFileGet2VictoryPoints();
                    else if (cardActivating.getEffectType().equals("buildFromFilePick2From6"))
                        buildFromFilePick2From6();
                    built = false;
                }
                }
                availableCards = getAllTriggeredCards();
                if (availableCards.isEmpty())
                    break;

                chainReactionCardsVisible = false;
                displayPromptChoice = 2;
                setPrompt("Do you want to continue to keep activating cards?");
                yesButtonClicked = false;
                noButtonClicked = false;
                yesButtonVisible = true;
                noButtonVisible = true;
                repaint();
                waitForYesOrNoClick();
                if (noButtonClicked) {
                    noButtonClicked = false;
                    yesButtonVisible = false;
                    noButtonVisible = false;
                    repaint();
                    break;
                }
                yesButtonClicked = false;
                noButtonClicked = false;
                yesButtonVisible = false;
                noButtonVisible = false;
                repaint();


            }
        }
        setPrompt("Click the end turn button to go to the next player");
        displayPromptChoice = 6;
//        resetVisibleFlags();
        repaint();
        waitForEndTurnClick();
        resetCardClicked();
    }


    public void buildGet1VictoryPoint(){
        setPrompt("You earned 1 bonus victory point!");
        currentPlayer.addBonusVPTokens(new BonusVictoryPoint(1));
        repaint();
        waitForSeconds(1);
        cardClicked.setTriggered(true);

    }
    public void buildPick1From6(){
        if (currentPlayer.getEnergyRing().size() < currentPlayer.getPlayerRingClass().getEnergyRingMax()) {
            setPrompt("Pick a marble to add to your energy ring");
            first6MarbleClickable = true;
            repaint();
            waitForPickMarbleClick();
            mostRecentMarblePicked = energyDispenser.getMarbles().get(marbleClickedIndex);
            currentPlayer.pickFrom6(marbleClickedIndex, energyDispenser.getMarbles());
            setPrompt("You added that marble to your energy ring!");
            picked = true;
            repaint();
            waitForSeconds(1);
            cardClicked.setTriggered(true);
        }
        else {
            setPrompt("You do not have enough space in your energy ring");
            repaint();
            waitForSeconds(1);
        }

    }
    public void buildThisOrThatGet1VictoryPoint(){
        setPrompt("You earned 1 bonus victory point!");
        currentPlayer.addBonusVPTokens(new BonusVictoryPoint(1));
        repaint();
        waitForSeconds(1);
        cardClicked.setTriggered(true);

    }
    public void buildThisOrThatPick1From6(){
        if (currentPlayer.getEnergyRing().size() < currentPlayer.getPlayerRingClass().getEnergyRingMax()) {
            setPrompt("Pick a marble to add to your energy ring");
            first6MarbleClickable = true;
            repaint();
            waitForPickMarbleClick();
            mostRecentMarblePicked = energyDispenser.getMarbles().get(marbleClickedIndex);
            currentPlayer.pickFrom6(marbleClickedIndex, energyDispenser.getMarbles());
            setPrompt("You added that marble to your energy ring!");
            picked = true;
            repaint();
            waitForSeconds(1);
            cardClicked.setTriggered(true);
        }
        else {
            setPrompt("You do not have enough space in your energy ring");
            repaint();
            waitForSeconds(1);
        }

    }
    public void buildThisOrThatFile(){
        if (currentPlayer.getArchive().size() < currentPlayer.getMaxArchive()) {
            GizmoCard recentlyClicked = cardClicked;
            setPrompt("Please choose a card from the 3 tiers to file.");
            tier1SectionClickable = true;
            tier2SectionClickable = true;
            tier3SectionClickable = true;
            tier1SectionClicked = false;
            tier2SectionClicked = false;
            tier3SectionClicked = false;
            repaint();
            cardClicked = null;
            waitForFileCardChoice();
            fileAction();
            recentlyClicked.setTriggered(true);
        }
        else {
            setPrompt("You do not have enough space in your archive");
            repaint();
            waitForSeconds(1);
        }

    }
    public void buildThisOrThatGet2VictoryPoints(){
        setPrompt("You earned 2 bonus victory points!");
        currentPlayer.addBonusVPTokens(new BonusVictoryPoint(1));
        currentPlayer.addBonusVPTokens(new BonusVictoryPoint(1));
        repaint();
        waitForSeconds(1);
        cardClicked.setTriggered(true);
    }
    public void buildThisOrThatResearch(){
        if (currentPlayer.getArchive().size() < currentPlayer.getMaxArchive() || currentPlayer.getEnergyRing().size() < currentPlayer.getPlayerRingClass().getEnergyRingMax()) {
            tier1CoverClickable = true;
            tier2CoverClickable = true;
            tier3CoverClickable = true;
            tier1CoverClicked = false;
            tier2CoverClicked = false;
            tier3CoverClicked = false;
            researchingCardsVisible = true;
            displayPromptChoice = 4;
            setPrompt("Choose a tier to research from");
            repaint();
            waitForResearchTierChoice();
            if (tier1CoverClicked)
                for (int i = 0; i < currentPlayer.getMaxResearch(); i++)
                    currentPlayer.getCardsResearching().add(deck.getDeck1().pop());
            if (tier2CoverClicked)
                for (int i = 0; i < currentPlayer.getMaxResearch(); i++)
                    currentPlayer.getCardsResearching().add(deck.getDeck2().pop());
            if (tier3CoverClicked)
                for (int i = 0; i < currentPlayer.getMaxResearch(); i++)
                    currentPlayer.getCardsResearching().add(deck.getDeck3().pop());

            setPrompt("Choose a card to research");
            repaint();
            waitForResearchCardChoice();

            researchingCardsVisible = false;
            if (tier1CoverClicked)
                for (int i = 0; i < currentPlayer.getCardsResearching().size(); i++) {
                    if (currentPlayer.getCardsResearching().get(i) != cardClicked)
                        deck.getDeck1().add(0, currentPlayer.getCardsResearching().get(i));
                }
            if (tier2CoverClicked)
                for (int i = 0; i < currentPlayer.getCardsResearching().size(); i++) {
                    if (currentPlayer.getCardsResearching().get(i) != cardClicked)
                        deck.getDeck2().add(0, currentPlayer.getCardsResearching().get(i));
                }
            if (tier3CoverClicked)
                for (int i = 0; i < currentPlayer.getCardsResearching().size(); i++) {
                    if (currentPlayer.getCardsResearching().get(i) != cardClicked)
                        deck.getDeck3().add(0, currentPlayer.getCardsResearching().get(i));
                }
            drawTier1Cards = false;
            drawTier2Cards = false;
            drawTier3Cards = false;

            setPrompt("Do you want to File or Build that card?");
            buildButtonVisible = false;
            fileButtonVisible = false;
            displayPromptChoice = 5;
            fileButtonClicked = false;
            buildButtonClicked = false;
            researchButtonClicked = false;
            pickButtonClicked = false;
            repaint();
            waitFor4ActionClick();

            if (fileButtonClicked)
            {
                set4ActionsInvisible();
                drawResearchingCards = false;

                repaint();
                fileAction();
            }
            else if (buildButtonClicked)
            {
                set4ActionsInvisible();
                drawResearchingCards = false;
                repaint();
                buildAction();
            }
        }
    }
    public void buildThisOrThatBuildTier1For0(){
        setPrompt("Select a card from Tier 1 to build for 0 marbles");
        tier1SectionClickable = true;
        repaint();
        waitForBuildCardChoice();
        //Copy the code to build a card from Tier 1 but with 0 marbles
        GizmoType type = cardClicked.getType();
        mostRecentCardBuilt = cardClicked;
        currentPlayer.build(cardClicked, 0, mostRecentCardBuilt.getColorOfCost(), energyDispenser);
        setPrompt("You built that card for 0 marbles!");
        repaint();
        waitForSeconds(0.5);
        cardClicked.setTriggered(true);

    }
    public void buildTier2Pick2From6(){
        setPrompt("Pick a marble to add to your energy ring");
        first6MarbleClickable = true;
        repaint();
        waitForPickMarbleClick();
        currentPlayer.pickFrom6(marbleClickedIndex, energyDispenser.getMarbles());
        setPrompt("You added that marble to your energy ring!");
        repaint();
        waitForSeconds(0.5);
        setPrompt("Pick a marble to add to your energy ring");
        first6MarbleClickable = true;
        repaint();
        waitForPickMarbleClick();
        currentPlayer.pickFrom6(marbleClickedIndex, energyDispenser.getMarbles());
        setPrompt("You added that marble to your energy ring!");
        repaint();
        waitForSeconds(0.5);
        cardClicked.setTriggered(true);
    }
    public void buildFromFileGet2VictoryPoints(){
        setPrompt("You earned 2 bonus victory points!");
        currentPlayer.addBonusVPTokens(new BonusVictoryPoint(1));
        currentPlayer.addBonusVPTokens(new BonusVictoryPoint(1));
        repaint();
        waitForSeconds(0.5);
        cardClicked.setTriggered(true);
    }

    public void fileAction()
    {
        if (currentPlayer.getArchive().size() < currentPlayer.getMaxArchive()) {
            filed = true;
            currentPlayer.file(cardClicked);
            setPrompt("You added that card to your archive!");
            if (tier1SectionClicked && !tier1CoverClicked)
                deck.addCardToTier1(tierCardClickedIndex);
            else if (tier2SectionClicked && !tier2CoverClicked)
                deck.addCardToTier2(tierCardClickedIndex);
            else if (tier3SectionClicked && !tier3CoverClicked)
                deck.addCardToTier3(tierCardClickedIndex);
            repaint();
            waitForSeconds(1);
            if (firstTimeFiling) {
                setPrompt("Drawing random marble from dispenser...");
                repaint();
                waitForSeconds(1);
                currentPlayer.pickRandom(energyDispenser);
                setPrompt("Marble Added!");
                repaint();
                waitForSeconds(0.7);
            }
            firstTimeFiling = false;
        }
        else{
            setPrompt("You do not have enough space in your archive");
            repaint();
            waitForSeconds(1);
        }
    }

    public void pickFrom6Action()
    {
        mostRecentMarblePicked = currentPlayer.pickFrom6(marbleClickedIndex, energyDispenser.getMarbles());
        picked = true;
        setPrompt("You added that marble to your energy ring!");
        repaint();
        waitForSeconds(1);
    }

    public void pickEffects()
    {
        if (currentPlayer.getEnergyRing().size() < currentPlayer.getPlayerRingClass().getEnergyRingMax() && mostRecentMarblePicked.getNewColor() == cardClicked.getColor1() && cardClicked.getColor2() == null) {
            currentPlayer.pickRandom(energyDispenser);
            setPrompt("Drawing random marble...");
            repaint();
            waitForSeconds(1);
            setPrompt("Random marble added!");
            repaint();
            waitForSeconds(1);
            picked = true;
            cardClicked.setTriggered(true);
        }
        else if (currentPlayer.getEnergyRing().size() < currentPlayer.getPlayerRingClass().getEnergyRingMax() && mostRecentMarblePicked.getNewColor() == cardClicked.getColor1() || mostRecentMarblePicked.getNewColor() == cardClicked.getColor2()) {
            currentPlayer.pickRandom(energyDispenser);
            setPrompt("Drawing random marble...");
            repaint();
            waitForSeconds(1);
            setPrompt("Random marble added!");
            repaint();
            waitForSeconds(1);
            picked = true;
            cardClicked.setTriggered(true);
        }
        else {
            setPrompt("You do not have enough space to add a marble:(");
            repaint();
            waitForSeconds(1);
            cardClicked.setTriggered(true);
        }
    }
    public void buildAction()
    {
        GizmoCard cardBeingBuilt = cardClicked;
        mostRecentCardBuilt = cardBeingBuilt;
        if (!currentPlayer.getToolBar().getCards().get(GizmoType.CONVERTOR).isEmpty()) {
            displayPromptChoice = 2;
            setPrompt("Do you want to use a convertor?");
            yesButtonVisible = true;
            noButtonVisible = true;
            repaint();
            waitForYesOrNoClick();

            while (yesButtonClicked)
            {
                convertSectionClicked = false;
                convertSectionClickable = true;
                yesButtonVisible = false;
                noButtonVisible = false;
                setPrompt("Please choose a convertor to activate");
                repaint();
                waitForConvertorCardChoice();
//                    waitForSeconds(0.5);
                while (cardClicked.isTriggered())
                {
                    setPrompt("You already used this card");
                    repaint();
                    waitForSeconds(0.5);
                    setPrompt("Please pick another convertor");
                    repaint();
                    waitForSeconds(0.5);
                    convertSectionClicked = false;
                    waitForConvertorCardChoice();
                }
                setPrompt("You are using this convertor");
                repaint();
                waitForSeconds(0.5);
                if (cardClicked.getEffectType().equals("convert1ToAny"))
                {
                    currentPlayer.setConvertMethod(1);
                    oneColorToAny(1);
                }
                else if  (cardClicked.getEffectType().equals("convert1Or2ToAny"))
                {
                    currentPlayer.setConvertMethod(2);
                    oneColor1Or2ToAny();
                }
                else if (cardClicked.getEffectType().equals("convertAnyToAny"))
                {
                    currentPlayer.setConvertMethod(3);
                    anyToAny();
                }
                else if (cardClicked.getEffectType().equals("convert1to2"))
                    oneColorToTwo(1);

                else if (cardClicked.getEffectType().equals("convert1or2to2"))
                    oneOrTwoColorsToTwo();

                boolean cardsAllTriggered = true;
                for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.CONVERTOR).size(); i++)
                    if (!currentPlayer.getToolBar().getCards().get(GizmoType.CONVERTOR).get(i).isTriggered()) {
                        cardsAllTriggered = false; break;
                    }

                if (!cardsAllTriggered) {
                    setPrompt("Do you want to use another convertor?");
                    yesButtonVisible = true;
                    noButtonVisible = true;
                    yesButtonClicked = false;
                    noButtonClicked = false;
                    repaint();

                    waitForYesOrNoClick();
                    yesButtonVisible = false;
                    noButtonVisible = false;

                }
                else
                    break;
            }

        }
        waitForSeconds(0.5);
        yesButtonVisible = false;
        noButtonVisible = false;
        int count = 0;
        MarbleColor colorOfMarble = cardBeingBuilt.getColorOfCost();
        int cost = cardBeingBuilt.getCost();
        for (int i = 0; i < currentPlayer.getEnergyRing().size(); i++)
            if (currentPlayer.getEnergyRing().get(i).getNewColor() == colorOfMarble)
                count++;

        if (count >= cost) {
            currentPlayer.build(cardBeingBuilt, cost, colorOfMarble, energyDispenser);
            setPrompt("You built the gizmo!");
            built = true;
            repaint();
            waitForSeconds(1);
            if (tier1SectionClicked && !tier1CoverClicked)
                deck.addCardToTier1(tierCardClickedIndex);
            else if (tier2SectionClicked && !tier2CoverClicked)
                deck.addCardToTier2(tierCardClickedIndex);
            else if (tier3SectionClicked && !tier3CoverClicked)
                deck.addCardToTier3(tierCardClickedIndex);
            currentPlayer.getArchive().remove(cardBeingBuilt);
            repaint();
        }
        else if (count < cost)
        {
            setPrompt("You do not have enough marbles to build that gizmo");
            repaint();
            waitForSeconds(1);
        }
        for (int i = 0; i < currentPlayer.getEnergyRing().size(); i++) {
            MarbleColor newColor = currentPlayer.getEnergyRing().get(i).getNewColor();
            MarbleColor oldColor = currentPlayer.getEnergyRing().get(i).getOldColor();
            if (newColor != oldColor)
                currentPlayer.getEnergyRing().get(i).setNewColor(oldColor);
            if (currentPlayer.getEnergyRing().get(i).getExtraAdded()) {
                currentPlayer.getEnergyRing().remove(currentPlayer.getEnergyRing().get(i));
                i--;
            }
        }
        if (cardBeingBuilt.getType() == GizmoType.UPGRADE)
        {
            upgradeEffect(cardBeingBuilt.getEffectType());
        }
        tier1SectionClickable = false;
        tier2SectionClickable = false;
        tier3SectionClickable = false;
        archiveSectionClickable = false;
    }

    public void upgradeEffect(String effect)
    {
        int index = 0;
        int energyRingUpgrade = 0;
        int fileUpgrade = 0;
        int researchUpgrade = 0;
        if (effect.contains("e")) {
            index = effect.indexOf("e");
            String str = effect.substring(index+1, index+2);
            energyRingUpgrade = Integer.parseInt(str);
            currentPlayer.upgrade(UpgradeType.RING, energyRingUpgrade);
        }
        if (effect.contains("f")) {
            index = effect.indexOf("f");
            fileUpgrade = Integer.parseInt(effect.substring(index+1, index+2));
            currentPlayer.upgrade(UpgradeType.ARCHIVE, fileUpgrade);
        }
        if (effect.contains("r")) {
            index = effect.indexOf("r");
            researchUpgrade = Integer.parseInt(effect.substring(index+1, index+2));
            currentPlayer.upgrade(UpgradeType.RESEARCH, researchUpgrade);
        }

    }

    public void oneColorToAny(int colorNum)
    {
        boolean colorFound = false;
        MarbleColor colorConvertingFrom;
        colorConvertingFrom = cardClicked.getColor1();


        for (int i = 0; i < currentPlayer.getEnergyRing().size(); i++) {
            if (currentPlayer.getEnergyRing().get(i).getNewColor() == colorConvertingFrom) {
                colorFound = true;
                break;
            }
        }

        if (!colorFound || currentPlayer.getEnergyRing().isEmpty()){
            setPrompt("You cannot activate this convertor effect");
            repaint();
            waitForSeconds(0.5);
        }
        else
        {
            setPrompt("Choose a color to convert the marble into");
            displayPromptChoice = 3;
            currentPlayer.setConvertMethod(1);
            marblesVisible = true;
            marbleColorClicked = false;
            repaint();
            waitForConvertColorChoice();
            marblesVisible = false;
            for (int i = 0; i < currentPlayer.getEnergyRing().size();i++) {
                if (currentPlayer.getEnergyRing().get(i).getNewColor() == colorConvertingFrom) {
                    currentPlayer.getEnergyRing().get(i).setNewColor(colorClicked);
                    break;
                }
            }
            setPrompt("You converted your marble!");
            repaint();
            waitForSeconds(1);
        }
        cardClicked.setTriggered(true);
    }

    public void oneColor1Or2ToAny()
    {
        oneColorToAny(1);

        setPrompt("Do you want to activate this converter again?");
        displayPromptChoice = 2;
        yesButtonVisible = true;
        noButtonVisible = true;
        yesButtonClicked = false;
        noButtonClicked = false;
        marblesVisible = false;
        repaint();
        waitForYesOrNoClick();
        waitForSeconds(1);
        if (yesButtonClicked)
            oneColorToAny(2);
        cardClicked.setTriggered(true);
    }

    public void anyToAny()
    {
        if (currentPlayer.getEnergyRing().isEmpty()){
            setPrompt("You cannot activate this convertor effect");
            repaint();
            waitForSeconds(0.5);
        }
        else
        {
            setPrompt("Pick a color to convert");
            displayPromptChoice = 3;
            drawOwnedMarbleOnlyForConvert = true;
            marblesVisible = true;
            currentPlayer.setConvertMethod(3);
            marblesVisible = true;
            colorClicked = null;
            repaint();
            waitForConvertColorChoice();
            MarbleColor colorConvertFrom = colorClicked;

            setPrompt("Pick a color to convert your marble into");
            colorClicked = null;
            drawOwnedMarbleOnlyForConvert = false;
            marbleColorClicked = false;
            repaint();

            waitForConvertColorChoice();
            waitForSeconds(1);
            marblesVisible = false;
            repaint();
            MarbleColor colorConvertTo = colorClicked;
            for (int i = 0; i < currentPlayer.getEnergyRing().size(); i++)
                if (currentPlayer.getEnergyRing().get(i).getNewColor() == colorConvertFrom)
                    currentPlayer.getEnergyRing().get(i).setNewColor(colorConvertTo);
        }
        cardClicked.setTriggered(true);
    }

    public void oneColorToTwo(int colorChoice)
    {
        MarbleColor color;
        if (colorChoice == 1)
            color = cardClicked.getColor1();
        else
            color = cardClicked.getColor2();

        boolean colorFound = false;
        for (int i = 0; i < currentPlayer.getEnergyRing().size(); i++)
            if (currentPlayer.getEnergyRing().get(i).getNewColor() == color)
                colorFound = true;

        if (!colorFound || currentPlayer.getEnergyRing().size() == currentPlayer.getPlayerRingClass().getEnergyRingMax()) {
            setPrompt("You cannot activate this convertor effect");
            repaint();
            waitForSeconds(0.5);
        }
        else
        {
            Marble marble = new Marble(color);
            marble.setExtraAdded(true);
            currentPlayer.getEnergyRing().add(marble);
            setPrompt("Marble successfully converted!");
            repaint();
            waitForSeconds(0.5);
        }

        cardClicked.setTriggered(true);
    }

    public void oneOrTwoColorsToTwo()
    {
        setPrompt("Do you want to activate the first convertor effect?");
        yesButtonVisible = true;
        noButtonVisible = true;
        yesButtonClicked = false;
        noButtonClicked = false;
        repaint();
        waitForYesOrNoClick();

        if (yesButtonClicked) {
            oneColorToTwo(1);
            yesButtonVisible = false;
            noButtonVisible = false;
            setPrompt("Do you want to activate the second convertor effect?");
            yesButtonVisible = true;
            noButtonVisible = true;
            yesButtonClicked = false;
            noButtonClicked = false;
            repaint();
            waitForYesOrNoClick();
            if (yesButtonClicked) {
                oneColorToTwo(2);
                yesButtonVisible = false;
                noButtonVisible = false;
            }
        }
        else if (noButtonClicked)
        {
            setPrompt("Do you want to activate the second convertor effect?");
            yesButtonVisible = true;
            noButtonVisible = true;
            yesButtonClicked = false;
            noButtonClicked = false;
            repaint();
            waitForYesOrNoClick();
            if (yesButtonClicked) {
                oneColorToTwo(2);
                yesButtonVisible = false;
                noButtonVisible = false;
                repaint();
                waitForSeconds(0.5);
            }
        }

        cardClicked.setTriggered(true);
        yesButtonClicked = false;
        noButtonClicked = false;
        yesButtonVisible = false;
        noButtonVisible = false;
    }
    public void fileGet1VictoryPoint(){
        setPrompt("You have added 1 bonus victory point to your score!");
        currentPlayer.addBonusVPTokens(new BonusVictoryPoint(1));
        repaint();
        waitForSeconds(1);
    }
    public void filePick1From6()
    {
        if (currentPlayer.getEnergyRing().size() < currentPlayer.getPlayerRingClass().getEnergyRingMax()) {
            setPrompt("Please pick a marble from the first 6 marbles");
            first6MarbleClickable = true;
            waitForPickMarbleClick();
            picked = true;
            mostRecentMarblePicked = energyDispenser.getMarbles().get(marbleClickedIndex);
            currentPlayer.pickFrom6(marbleClickedIndex, energyDispenser.getMarbles());
            setPrompt("You added that marble to your energy ring!");
            repaint();
            waitForSeconds(1);
        }
        else
        {
            setPrompt("You do not have enough space to add a marble:(");
            repaint();
            waitForSeconds(1);
        }


    }
    public void filePick3Random()
    {
        for (int i = 0; i < 3; i++)
        {
            if (currentPlayer.getEnergyRing().size() < currentPlayer.getPlayerRingClass().getEnergyRingMax()) {
                currentPlayer.pickRandom(energyDispenser);
                setPrompt("Drawing a random marble....");
                repaint();
                waitForSeconds(1);
                setPrompt("Random marble added!");
                repaint();
                waitForSeconds(1);
            }
            else {
                setPrompt("You do not have enough space to add a marble:(");
                repaint();
                waitForSeconds(1);
                break;
            }
        }
        cardClicked.setTriggered(true);
    }
    public void filePick1Random()
    {
        currentPlayer.pickRandom(energyDispenser);
        setPrompt("You picked a random marble!");
        repaint();
        waitForSeconds(0.5);
        cardClicked.setTriggered(true);

    }

    public HashSet<GizmoCard> getAllTriggeredCards()
    {
        HashSet<GizmoCard> activatableCards = new HashSet<GizmoCard>();
        HashSet<GizmoCard> allPlayerCards = currentPlayer.getToolBar().getCards().values().stream().flatMap(Collection::stream).collect(Collectors.toCollection(HashSet::new));
        if (filed)
        {
            activatableCards = allPlayerCards.stream().filter(c -> c.getType().equals(GizmoType.FILE)).collect(Collectors.toCollection(HashSet::new));
            activatableCards.removeIf(GizmoCard::isTriggered);
            activatableCards.removeIf(c -> c.getLevel().equals(GizmoLevel.LEVEL0));
            out.println(activatableCards.size());
        }
        if (picked) {
            for (GizmoCard c : allPlayerCards)
                if ((c.getType().equals(GizmoType.PICK)) && (!c.isTriggered()) && (mostRecentMarblePicked.getNewColor() == c.getColor1() || mostRecentMarblePicked.getNewColor() == c.getColor2()))
                    activatableCards.add(c);
        }
        if (built) {
            for (GizmoCard c : allPlayerCards)
                if ((c.getType().equals(GizmoType.BUILD)) && (!c.isTriggered()) && (mostRecentCardBuilt.getColorOfCost() == c.getColor1() || mostRecentCardBuilt.getColorOfCost() == c.getColor2()))
                    activatableCards.add(c);
        }

        return activatableCards;
    }

//    returns red by default
    public MarbleColor marbleClickIndexToColor(int x)
    {
        switch (x)
        {
            case 0: return MarbleColor.RED;
            case 1: return MarbleColor.BLUE;
            case 2: return MarbleColor.BLACK;
            case 3: return MarbleColor.YELLOW;
        }
        return MarbleColor.RED;
    }



        /**
         * This method sets the order and returns the current player, and if its the last player
         * then it starts back to 1 and will go through 1-4
         */
    public void setNextPlayer() {
        currentPlayer = playerList.get((playerList.indexOf(currentPlayer) + 1) % playerList.size());
    }

    /**
     * What happens after the game ends, calls the calculate scores method, and returns an arrayList of the ranking, and tiebreakers
     */
    public ArrayList<Player> endGame() {
        ArrayList<Player> ranking = new ArrayList<>(playerList);
        ranking.sort(Comparator.comparing(Player::getScore).reversed());
        //If any players have the same two scores, sort only those players by their number of cards
        for (int i = 0; i < ranking.size() - 1; i++) {
            if (ranking.get(i).getScore() == ranking.get(i + 1).getScore()) {
                int personOneCards = ranking.get(i).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                int personTwoCards = ranking.get(i + 1).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                if (personOneCards < personTwoCards)
                    Collections.swap(ranking, i, i + 1);

            }
        }
        //If any players have the same score and number of cards, sort only those players by the number of marbles in their reserve
        for (int i = 0; i < ranking.size() - 1; i++) {
            if (ranking.get(i).getScore() == ranking.get(i + 1).getScore()) {
                int personOneCards = ranking.get(i).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                int personTwoCards = ranking.get(i + 1).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                if (personOneCards == personTwoCards) {
                    int personOneMarbles = ranking.get(i).getEnergyRing().size();
                    int personTwoMarbles = ranking.get(i + 1).getEnergyRing().size();
                    if (personOneMarbles < personTwoMarbles)
                        Collections.swap(ranking, i, i + 1);
                }
            }
        }
        //If finally, any players are still tied, compare only those players using the compareTo method in the Player class. THe greater number is ranked above
        for (int i = 0; i < ranking.size() - 1; i++) {
            if (ranking.get(i).getScore() == ranking.get(i + 1).getScore()) {
                int personOneCards = ranking.get(i).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                int personTwoCards = ranking.get(i + 1).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                if (personOneCards == personTwoCards) {
                    int personOneMarbles = ranking.get(i).getEnergyRing().size();
                    int personTwoMarbles = ranking.get(i + 1).getEnergyRing().size();
                    if (personOneMarbles == personTwoMarbles) {
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
    public boolean endGameConditions() {
        //Check all players and their cards. Return true if any player has at least four cards where the GizmoLevel is LEVEL3 or 16 total gizmos built including first one
        for (Player p : playerList) {
            Toolbar playerToolbar = p.getToolBar();
            //The getCards method returns a treemap of arraylists of gizmocards. Use streams to accumulate all the values in all of the arraylists into one arraylist
            ArrayList<GizmoCard> allCards = playerToolbar.getCards().values().stream().flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));
            if (allCards.size() >= 16) return true;
        }
        //if all tier cards run out also end game
        if (deck.isDeckEmpty()) return true;
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
     * takes in a player and switches it to their corresponding image
     * @param player
     * @return a BufferedImage that represents the player's number
     */
    public BufferedImage playerNumberToImage(Player player) {
        switch (player.getPlayerNumber()) {
            case 1:
                return playerOne;
            case 2:
                return playerTwo;
            case 3:
                return playerThree;
            case 4:
                return playerFour;
        }
        return playerOne;
    }


    public void paint(Graphics g) {
//        super.paint(g);
//        Menu Screen
        if (!gameStart)
            g.drawImage(start, 0, 0, 1600, 900, null);

//        Rules downloaded
        if (rulesButtonClicked)
            drawRulesDownloaded(g);

        int lengthBetween = smallTitleLength + (int) (smallCardWidth * 4.2);
//        Game Screen
        if (gameStart) {
            //always draw
            g.drawImage(gameScreen, 0, 0, 1600, 900, null);


            //prompt
            g.drawImage(promptBox, promptBoxX, promptBoxY, promptBoxWidth, promptBoxLength, null);
            g.drawImage(prompt, promptX, promptY, promptWidth, promptLength, null);
            Font f = new Font("Serif", Font.BOLD, 20);
            g.setFont(f);
            g.setColor(Color.BLACK);
            g.drawString(promptStr, promptX + 3, promptY + 20);

            //end turn button
            if (endTurnButtonVisible)
                g.drawImage(endTurnButton, promptX + (int) (cardWidth * 4.0), promptY + 37, (int) (cardWidth * 1.2), (int) (cardWidth * 0.4), null);

            //Current player number and score
            f = new Font("Serif", Font.BOLD, 32);
            g.setFont(f);
            g.setColor(Color.WHITE);
            g.drawString(""+currentPlayer.getScore(), 1080, 25);
            g.drawImage(playerNumberToImage(currentPlayer), archTitleX + 140, archTitleY + 2, 20, 25, null);
            //other player numbers and scores
            f = new Font("Serif", Font.BOLD, 25);
            g.setFont(f);
            g.setColor(Color.WHITE);
            int x12 = smallTitleX + (int) (smallCardWidth * 2) - 15;
            int y12 = smallTitleY;
            for (int i = 0; i < 4; i++) {
                g.drawImage(playerNumberToImage(playerList.get(i)), x12, y12, 18, 23, null);
                g.drawString(": "+playerList.get(i).getScore(), x12+20, y12+ 20);
                y12 += lengthBetween;
            }


            //Energy Ring
            g.drawImage(energyRing, erX, erY, erWidth, erWidth, null);

            //Energy Ring marbles
            int x4 = erMarbleR1X;
            int y4 = erMarbleR1Y;
            int z = 0;

            while (z < currentPlayer.getEnergyRing().size())
            {
                Marble tempMarble = currentPlayer.getEnergyRing().get(z);
                g.drawImage(marbleToColor(tempMarble.getNewColor()), x4, y4, erMarbleWidth, erMarbleWidth, null);
                x4 += erMarbleWidth;

                if (z % 4 == 3)
                {
                    x4 = erMarbleR1X;
                    y4 += erMarbleWidth;
                }
                z++;
            }

            //EnergyDispenser marbles
            int x7 = first6X;
            int y7 = first6Y + (int)(first6Width*5);
            for (int i = 0; i < energyDispenser.getFirstSix().size(); i++) {
                g.drawImage(marbleToColor(energyDispenser.getFirstSix().get(i).getNewColor()), x7, y7, first6Width, first6Width, null);
                y7 -= first6Width;
            }


            //Victory point tokens
            int x8 = VPX;
            int y8 = VPY;
            int x9 = VPX;
            int y9 = VPY + VPlength;
            for (int i = 0; i < currentPlayer.getBonusVictoryPoints().size(); i++) {
                if (i < 6) {
                    g.drawImage(numberToBonusVPToken(currentPlayer.getBonusVictoryPoints().get(i).getValue()), x8, y8, VPWidth, VPlength, null);
                    x8 += VPWidth;
                }
                if (i >= 6) {
                    g.drawImage(numberToBonusVPToken(currentPlayer.getBonusVictoryPoints().get(i).getValue()), x9, y9, VPWidth, VPlength, null);
                    x9 += VPWidth;
                }
            }

            //Archive
            int temp = archX;
            for (int i = 0; i < currentPlayer.getArchive().size(); i++) {
                g.drawImage(currentPlayer.getArchive().get(i).getImage(), temp, archY, archWidth, archWidth, null);
                temp += cardWidth;
            }

            //Toolbar
            if (currentPlayer.getPlayerNumber() == 1)
                g.drawImage(firstPlayerTB, tbX, tbY, tbWidth, tbLength, null);
            else
                g.drawImage(otherPlayerTB, tbX, tbY, tbWidth, tbLength, null);

            //Cards in toolbar
            int y = tbY + tbLength;
            for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.UPGRADE).size(); i++) {
                g.drawImage(currentPlayer.getToolBar().getCards().get(GizmoType.UPGRADE).get(i).getImage(), tbX, y, cardWidth, cardWidth, null);
                y += ownedCardsHeaderLength;
            }

            int x = tbX + tbSectionWidth;
            y = tbY + tbLength;
            for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.CONVERTOR).size(); i++) {
                g.drawImage(currentPlayer.getToolBar().getCards().get(GizmoType.CONVERTOR).get(i).getImage(), x, y, cardWidth, cardWidth, null);
                y += ownedCardsHeaderLength;
            }

            x = tbX + tbSectionWidth * 2;
            y = tbY + tbLength;
            for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.FILE).size(); i++) {
                g.drawImage(currentPlayer.getToolBar().getCards().get(GizmoType.FILE).get(i).getImage(), x, y, cardWidth, cardWidth, null);
                y += ownedCardsHeaderLength;
            }

            x = tbX + tbSectionWidth * 3;
            y = tbY + tbLength;
            for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.PICK).size(); i++) {
                g.drawImage(currentPlayer.getToolBar().getCards().get(GizmoType.PICK).get(i).getImage(), x, y, cardWidth, cardWidth, null);
                y += ownedCardsHeaderLength;
            }

            x = tbX + tbSectionWidth * 4;
            y = tbY + tbLength;
            for (int i = 0; i < currentPlayer.getToolBar().getCards().get(GizmoType.BUILD).size(); i++) {
                g.drawImage(currentPlayer.getToolBar().getCards().get(GizmoType.BUILD).get(i).getImage(), x, y, cardWidth, cardWidth, null);
                y += ownedCardsHeaderLength;
            }


            //Tier cards
            g.drawImage(Rank1, tierX, tierY, cardWidth, cardWidth, null);
            int x1 = tierX + cardWidth;
            int y1 = tierY;
            for (int i = 0; i < deck.getTier1().length; i++) {
                g.drawImage(deck.getTier1()[i].getImage(), x1, y1, cardWidth, cardWidth, null);
                x1 += cardWidth;
            }

            g.drawImage(Rank2, tierX, tierY + cardWidth, cardWidth, cardWidth, null);
            x1 = tierX + cardWidth;
            y1 = tierY + cardWidth;
            for (int i = 0; i < deck.getTier2().length; i++) {
                g.drawImage(deck.getTier2()[i].getImage(), x1, y1, cardWidth, cardWidth, null);
                x1 += cardWidth;
            }

            g.drawImage(Rank3, tierX, tierY + cardWidth * 2, cardWidth, cardWidth, null);
            x1 = tierX + cardWidth;
            y1 = tierY + cardWidth * 2;
            for (int i = 0; i < deck.getTier3().length; i++) {
                g.drawImage(deck.getTier3()[i].getImage(), x1, y1, cardWidth, cardWidth, null);
                x1 += cardWidth;
            }

            //promptBox
            //Buttons
            //4 main actions
            int x3 = promptROrBCardRow1X;
            int y3 = promptROrBCardRow1Y + (int) (cardWidth / 4.0);
            if (fileButtonVisible)
                g.drawImage(fileButton, x3, y3, (int) (cardWidth * 1.2), (int) (cardWidth * 0.4), null);

            x3 += (int) (cardWidth * 2);

            if (pickButtonVisible)
                g.drawImage(pickButton, x3, y3, (int) (cardWidth * 1.2), (int) (cardWidth * 0.4), null);

            x3 = promptROrBCardRow1X;
            y3 += cardWidth;

            if (buildButtonVisible)
                g.drawImage(buildButton, x3, y3, (int) (cardWidth * 1.2), (int) (cardWidth * 0.4), null);

            x3 += (int) (cardWidth * 2);

            if (researchButtonVisible)
                g.drawImage(researchButton, x3, y3, (int) (cardWidth * 1.2), (int) (cardWidth * 0.4), null);


            //Building
            if ((buildButtonClicked && cardIsClicked))
                g.drawImage(cardClicked.getImage(), promptROrBCardRow1X + (int) (cardWidth * 4), promptROrBCardRow1Y + (int) (cardWidth / 4.0), cardWidth, cardWidth, null);

            //if have convertors draw yes and no button
            int x2 = promptROrBCardRow1X;
            int y2 = promptROrBCardRow1Y + (int) (cardWidth / 4.0);
            if (yesButtonVisible)
                g.drawImage(yesButton, x2, y2, (int) (cardWidth * 1.2), (int) (cardWidth * 0.4), null);
            x2 += (int) (cardWidth * 2);
            if (noButtonVisible)
                g.drawImage(noButton, x2, y2, (int) (cardWidth * 1.2), (int) (cardWidth * 0.4), null);

            //converting in prompt
            if (marblesVisible) {

                //one color to any // 1 or  2 to any
                if (!drawOwnedMarbleOnlyForConvert) {
                    x2 = promptROrBCardRow1X;
                    y2 = promptROrBCardRow1Y + (int) (cardWidth / 2.0);
                    g.drawImage(redMarble, x2, y2, (int) (cardWidth / 2.0), (int) (cardWidth / 2.0), null);
                    x2 += cardWidth;

                    g.drawImage(blueMarble, x2, y2, (int) (cardWidth / 2.0), (int) (cardWidth / 2.0), null);
                    x2 += cardWidth;

                    g.drawImage(blackMarble, x2, y2, (int) (cardWidth / 2.0), (int) (cardWidth / 2.0), null);
                    x2 += cardWidth;

                    g.drawImage(yellowMarble, x2, y2, (int) (cardWidth / 2.0), (int) (cardWidth / 2.0), null);
                }
                else if (drawOwnedMarbleOnlyForConvert)
                {
                    x2 = promptROrBCardRow1X;
                    y2 = promptROrBCardRow1Y + (int) (cardWidth / 2.0);
                    for (int i = 0; i < getDistinctColorsInRing().size(); i++)
                    {
                        if (getDistinctColorsInRing().get(i) == MarbleColor.RED)
                            g.drawImage(redMarble, x2, y2, (int) (cardWidth / 2.0), (int) (cardWidth / 2.0), null);
                        else if (getDistinctColorsInRing().get(i) == MarbleColor.BLUE)
                            g.drawImage(blueMarble, x2, y2, (int) (cardWidth / 2.0), (int) (cardWidth / 2.0), null);
                        else if (getDistinctColorsInRing().get(i) == MarbleColor.BLACK)
                            g.drawImage(blackMarble, x2, y2, (int) (cardWidth / 2.0), (int) (cardWidth / 2.0), null);
                        else if (getDistinctColorsInRing().get(i) == MarbleColor.YELLOW)
                            g.drawImage(yellowMarble, x2, y2, (int) (cardWidth / 2.0), (int) (cardWidth / 2.0), null);

                        x2 += cardWidth;
                    }
                }
            }

            //researching in prompt
            if (drawTier1Cards || drawTier2Cards || drawTier3Cards) {
                z = 0;
                x2 = promptROrBCardRow1X;
                x3 = promptROrBCardRow2X;

                Iterator<GizmoCard> iter = currentPlayer.getCardsResearching().iterator();
                while (z < currentPlayer.getCardsResearching().size()) {
                    GizmoCard tempCard = iter.next();
                    if (z < 5) {
                        g.drawImage(tempCard.getImage(), x2, promptROrBCardRow1Y, cardWidth, cardWidth, null);
                        x2 += cardWidth;
                    } else if (z >= 5) {
                        g.drawImage(tempCard.getImage(), x3, promptROrBCardRow2Y, cardWidth, cardWidth, null);
                        x3 += cardWidth;
                    }
                    z++;
                }
            }

            if (drawResearchingCards) {
                x4 = promptROrBCardRow1X;
                y4 = promptROrBCardRow1Y + (int) (cardWidth / 4.0);
                g.drawImage(cardResearching.getImage(), promptROrBCardRow1X + (int) (cardWidth * 4), promptROrBCardRow1Y + (int) (cardWidth / 4.0), cardWidth, cardWidth, null);
                g.drawImage(buildButton, x4, y4, (int) (cardWidth * 1.2), (int) (cardWidth * 0.4), null);
                x4 += (int)(cardWidth*2);
                g.drawImage(fileButton, x4, y4, (int) (cardWidth * 1.2), (int) (cardWidth * 0.4), null);
            }
            //OtherPlayer toolbars
            int playerSmallTitleY = smallTitleY;
            int playerSmallERY = smallERY;
            int playerSmallTBY = smallTBY;
            int playerSmallEMR1Y = smallEMR1Y;
            int playerSmallVPY = smallVPY;
            for (int h = 0; h < 4; h++) {
                Player otherPlayer = playerList.get(h);
                if (otherPlayer != currentPlayer) {
                    int x10 = smallTitleX;
                    int y10 = playerSmallTitleY + smallTitleLength;

                    //energyRing
                    g.drawImage(energyRing, smallERX, playerSmallERY, smallERWidth, smallERWidth, null);

                    //toolbar
                    if (otherPlayer.getPlayerNumber() == 1)
                        g.drawImage(firstPlayerTB, smallTBX, playerSmallTBY, smallTBWidth, smallTBLength, null);
                    else
                        g.drawImage(otherPlayerTB, smallTBX, playerSmallTBY, smallTBWidth, smallTBLength, null);

                    //Archive
                    for (int k = 0; k < otherPlayer.getArchive().size(); k++) {
                        g.drawImage(otherPlayer.getArchive().get(k).getImage(), x10, y10, smallCardWidth, smallCardWidth, null);
                        x10 += smallCardWidth;
                        if (k % 3 == 2) {
                            x10 = smallTitleX;
                            y10 += smallCardWidth;
                        }
                    }

                    //cards in toolbar
                    x = smallTBX;
                    y = playerSmallTBY + smallTBLength;
                    for (int i = 0; i < otherPlayer.getToolBar().getCards().get(GizmoType.UPGRADE).size(); i++) {
                        g.drawImage(otherPlayer.getToolBar().getCards().get(GizmoType.UPGRADE).get(i).getImage(), x, y, smallCardWidth, smallCardWidth, null);
                        y += smallCardHeaderLength;
                    }

                    x = smallTBX + smallTBSectionWidth;
                    y = playerSmallTBY + smallTBLength;
                    for (int i = 0; i < otherPlayer.getToolBar().getCards().get(GizmoType.CONVERTOR).size(); i++) {
                        g.drawImage(otherPlayer.getToolBar().getCards().get(GizmoType.CONVERTOR).get(i).getImage(), x, y, smallCardWidth, smallCardWidth, null);
                        y += smallCardHeaderLength;
                    }

                    x = smallTBX + smallTBSectionWidth * 2;
                    y = playerSmallTBY + smallTBLength;
                    for (int i = 0; i < otherPlayer.getToolBar().getCards().get(GizmoType.FILE).size(); i++) {
                        g.drawImage(otherPlayer.getToolBar().getCards().get(GizmoType.FILE).get(i).getImage(), x, y, smallCardWidth, smallCardWidth, null);
                        y += smallCardHeaderLength;
                    }

                    x = smallTBX + smallTBSectionWidth * 3;
                    y = playerSmallTBY + smallTBLength;
                    for (int i = 0; i < otherPlayer.getToolBar().getCards().get(GizmoType.PICK).size(); i++) {
                        g.drawImage(otherPlayer.getToolBar().getCards().get(GizmoType.PICK).get(i).getImage(), x, y, smallCardWidth, smallCardWidth, null);
                        y += smallCardHeaderLength;
                    }

                    x = smallTBX + smallTBSectionWidth * 4;
                    y = playerSmallTBY + smallTBLength;
                    for (int i = 0; i < otherPlayer.getToolBar().getCards().get(GizmoType.BUILD).size(); i++) {
                        g.drawImage(otherPlayer.getToolBar().getCards().get(GizmoType.BUILD).get(i).getImage(), x, y, smallCardWidth, smallCardWidth, null);
                        y += smallCardHeaderLength;
                    }

                    //small energy ring marbles
                    x4 = smallEMR1X;
                    y4 = playerSmallEMR1Y;
                    z = 0;
                    while (z < otherPlayer.getEnergyRing().size()) {
                        Marble tempMarble = otherPlayer.getEnergyRing().get(z);

                        g.drawImage(marbleToColor(tempMarble.getNewColor()), x4, y4, smallEMWidth, smallEMWidth, null);
                        x4 += smallEMWidth;

                        if (z % 4 == 3) {
                            x4 = smallEMR1X;
                            y4 += smallEMWidth;
                        }
                        z++;
                    }

                    //small victory point tokens
                    x8 = smallVPX;
                    y8 = playerSmallVPY;

                    for (int j = 0; j < otherPlayer.getBonusVictoryPoints().size(); j++) {
                        g.drawImage(numberToBonusVPToken(otherPlayer.getBonusVictoryPoints().get(j).getValue()), x8, y8, smallVPWidth, smallVPlength, null);
                        x8 += smallVPWidth;

                        if (j % 3 == 2) {
                            x8 = smallVPX;
                            y8 += smallVPlength;
                        }
                    }
                }
                playerSmallTitleY += lengthBetween;
                playerSmallERY += lengthBetween;
                playerSmallTBY += lengthBetween;
                playerSmallEMR1Y += lengthBetween;
                playerSmallVPY += lengthBetween;
            }

            if (chainReactionStart && chainReactionCardsVisible)
            {
                z = 0;
                x2 = promptROrBCardRow1X;
                x3 = promptROrBCardRow2X;

                Iterator<GizmoCard> iter = getAllTriggeredCards().iterator();
                while (z < getAllTriggeredCards().size()) {
                    GizmoCard tempCard = iter.next();
                    if (z < 6) {
                        g.drawImage(tempCard.getImage(), x2, promptROrBCardRow1Y, cardWidth, cardWidth, null);
                        x2 += cardWidth;
                    } else if (z >= 6) {
                        g.drawImage(tempCard.getImage(), x3, promptROrBCardRow2Y, cardWidth, cardWidth, null);
                        x3 += cardWidth;
                    }
                    z++;
                }
            }

        }
        if (gameEnd)
        {
            Font f = new Font("Serif", Font.BOLD, 200);
            g.setFont(f);
            g.setColor(Color.RED);
            g.drawString("GAME OVER!", 150, 400);
            ArrayList<Player> finalRankings = endGame();
            int x = promptX+cardWidth;
            int y = promptY+promptLength;
            f = new Font("Serif", Font.BOLD, 30);
            g.setFont(f);
            g.setColor(Color.BLUE);
            for (int i = 1; i <= finalRankings.size(); i++)
            {
                g.drawImage(rankToImage(i), x, y+15, 50, 50, null);
                g.drawString(" = Player "+finalRankings.get(i-1).getPlayerNumber(), x + 55, y+50);
                y += 50;
            }
        }


    }

    public BufferedImage rankToImage(int x)
    {
        switch (x)
        {
            case 1: return first;
            case 2: return second;
            case 3: return third;
            case 4: return fourth;
        }
        return first;
    }

    /**
     * sets all points for all images when being drawn
     */
    public void initializeAllXY() {
        cardWidth = 105;
        erX = 0;
        erY = 0;
        erWidth = 150;
        archTitleY = 0;
        archTitleLength = 30;
        first6X = 1020;
        first6Y = 440;
        VPTitleX = 950;
        VPTitleLength = 20;
        VPWidth = 30;
        VPlength = 45;
        promptBoxX = (int) (cardWidth * 5);
        smallTitleX = 1150;
        smallTitleY = 0;
        smallTBY = 0;
        smallERY = 0;

        smallCardWidth = (int) (cardWidth / 2.5);
        ownedCardsHeaderLength = (int) (cardWidth * 2.0 / 7);
        erMarbleR1X = (int) (erWidth / 8.0 * 2);
        erMarbleR1Y = (int) (erWidth / 8.0 * 3);
        erMarbleWidth = (int) (erWidth / 8.0);
        archTitleX = erX + erWidth;
        archX = erX + erWidth;
        archY = archTitleLength;
        archWidth = cardWidth;
        tbX = erX;
        tbY = erY + erWidth;
        tbSectionWidth = cardWidth;
        tbWidth = (int) (cardWidth * (5 + (4.0 / 7)));
        tbLength = (int) (cardWidth * (3.0 / 7));
        first6Width = erMarbleWidth;
        tierX = tbX;
        tierY = tbY + tbLength + 9 * ownedCardsHeaderLength + cardWidth;
        VPTitleY = tbY;
        VPX = VPTitleX;
        VPY = VPTitleY + VPTitleLength;

        promptBoxY = tierY;
        promptBoxWidth = (int) (1135 - promptBoxX);
        promptBoxLength = cardWidth * 3;
        promptX = promptBoxX + (int) (cardWidth / 4.0);
        promptY = promptBoxY + (int) (cardWidth / 4.0);
        promptWidth = promptBoxWidth - (int) (cardWidth * 0.25) - 30;
        promptLength = cardWidth - 70;
        promptROrBCardRow1X = promptBoxX + (int) (cardWidth / 2.0);
        promptROrBCardRow1Y = tierY + cardWidth;
        promptROrBCardRow2X = promptROrBCardRow1X;
        promptROrBCardRow2Y = promptROrBCardRow1Y + cardWidth;

        smallTitleWidth = smallCardWidth * 3 + 20;
        smallTitleLength = smallCardWidth + 5;
        smallTBX = smallTitleX + smallTitleWidth;
        smallTBSectionWidth = smallCardWidth;
        smallTBWidth = (int) (smallCardWidth * (5 + (5.0 / 7)));
        smallTBLength = (int) (smallCardWidth * (3.0 / 7));

        smallCardHeaderLength = (int) (smallCardWidth * 2.0 / 7);
        smallERX = smallTBX + smallTBWidth;
        smallERWidth = (int) (erWidth / 2.25);
        smallEMR1X = smallERX + (int) (smallERWidth / 8.0 * 2);
        smallEMR1Y = (int) (smallERWidth / 8.0 * 3);
        smallEMWidth = (int) (smallERWidth / 8.0);
        smallVPX = smallERX;
        smallVPY = smallERY + smallERWidth;
        smallVPWidth = (int) (VPWidth / 1.5);
        smallVPlength = (int) (VPlength / 1.5);

//        testDrawPopulate(1);
//        testDrawPopulate(2);
//        testDrawPopulate(3);
//        testDrawPopulate(4);

    }



//    public int getDisplayPromptChoice(int displayPromptChoice)
//    {
//        switch (displayPromptChoice)
//        {
//            case 1: ;
//            case 2: ;
//            case 3: ;
//            case 4: ;
//            case 5: ;
//            case 6: ;
//        }
//    }

    /**
     * adds the coordinates of the top left and bottom right of each large section that the player clicks on
     * Sections include: archive, toolbar cards, 3 different tiers including the tier card, first 6 marbles, and different things that pop up in the prompt
     *
     * @param map places all of the points in a string map of int array
     *            0 = top left x
     *            1 = top left y
     *            3 = bottom right x
     *            3 = bottom right y
     */
    public void initializeAllSectionCoord(TreeMap<String, int[]> map) {
        //archive
        int[] coord = new int[4];
        coord[0] = archX;
        coord[1] = archY;
        coord[2] = archX + (int) (cardWidth * 9);
        coord[3] = archY + cardWidth;
        map.put("Archive", coord);

        //upgrade cards
        coord = new int[4];
        coord[0] = tbX;
        coord[1] = tbY + tbLength;
        coord[2] = tbX + tbSectionWidth;
        coord[3] = tbY + (int) (ownedCardsHeaderLength * 9) + cardWidth;
        map.put("UpgradeCards", coord);

        //convert cards
        coord = new int[4];
        coord[0] = tbX + tbSectionWidth;
        coord[1] = tbY + tbLength;
        coord[2] = tbX + tbSectionWidth * 2;
        coord[3] = tbY + (int) (ownedCardsHeaderLength * 9) + cardWidth;
        map.put("ConvertCards", coord);

        //File cards
        coord = new int[4];
        coord[0] = tbX + tbSectionWidth * 2;
        coord[1] = tbY + tbLength;
        coord[2] = tbX + tbSectionWidth * 3;
        coord[3] = tbY + (int) (ownedCardsHeaderLength * 9) + cardWidth;
        map.put("FileCards", coord);

        //Pick cards
        coord = new int[4];
        coord[0] = tbX + tbSectionWidth * 3;
        coord[1] = tbY + tbLength;
        coord[2] = tbX + tbSectionWidth * 4;
        coord[3] = tbY + (int) (ownedCardsHeaderLength * 9) + cardWidth;
        map.put("PickCards", coord);

        //Build cards
        coord = new int[4];
        coord[0] = tbX + tbSectionWidth * 4;
        coord[1] = tbY + tbLength;
        coord[2] = tbX + tbSectionWidth * 5;
        coord[3] = tbY + (int) (ownedCardsHeaderLength * 9) + cardWidth;
        map.put("BuildCards", coord);

        //Tier1 cards
        coord = new int[4];
        coord[0] = tierX;
        coord[1] = tierY;
        coord[2] = tierX + cardWidth * 5;
        coord[3] = tierY + cardWidth;
        map.put("Tier1Cards", coord);

        //Tier2 cards
        coord = new int[4];
        coord[0] = tierX;
        coord[1] = tierY + cardWidth;
        coord[2] = tierX + cardWidth * 4;
        coord[3] = tierY + cardWidth * 2;
        map.put("Tier2Cards", coord);

        //Tier3 cards
        coord = new int[4];
        coord[0] = tierX;
        coord[1] = tierY + cardWidth * 2;
        coord[2] = tierX + cardWidth * 3;
        coord[3] = tierY + cardWidth * 3;
        map.put("Tier3Cards", coord);

        //First 6 marbles
        coord = new int[4];
        coord[0] = first6X;
        coord[1] = first6Y;
        coord[2] = first6X + first6Width;
        coord[3] = first6Y + (int) (first6Width * 6);
        map.put("First6Marbles", coord);

        //Button1 - top left
        coord = new int[4];
        coord[0] = promptROrBCardRow1X;
        coord[1] = promptROrBCardRow1Y + (int) (cardWidth / 4.0);
        coord[2] = coord[0] + (int) (cardWidth * 1.2);
        coord[3] = coord[1] + (int) (cardWidth * 0.4);
        map.put("Button1", coord);

        //Button2 - top Right
        coord = new int[4];
        coord[0] = promptROrBCardRow1X + (int) (cardWidth * 2);
        coord[1] = promptROrBCardRow1Y + (int) (cardWidth / 4.0);
        coord[2] = coord[0] + (int) (cardWidth * 1.2);
        coord[3] = coord[1] + (int) (cardWidth * 0.4);
        map.put("Button2", coord);

        //Button3 - bottom left
        coord = new int[4];
        coord[0] = promptROrBCardRow1X;
        coord[1] = promptROrBCardRow1Y + (int) (cardWidth / 4.0) + cardWidth;
        coord[2] = coord[0] + (int) (cardWidth * 1.2);
        coord[3] = coord[1] + (int) (cardWidth * 0.4);
        map.put("Button3", coord);

        //Button4 - bottom left
        coord = new int[4];
        coord[0] = promptROrBCardRow1X + (int) (cardWidth * 2);
        coord[1] = promptROrBCardRow1Y + (int) (cardWidth / 4.0) + cardWidth;
        coord[2] = coord[0] + (int) (cardWidth * 1.2);
        coord[3] = coord[1] + (int) (cardWidth * 0.4);
        map.put("Button4", coord);

        //Button5 - end turn
        coord = new int[4];
        coord[0] = promptX + (int) (cardWidth * 4.0);
        coord[1] = promptY + 37;
        coord[2] = coord[0] + (int) (cardWidth * 1.2);
        coord[3] = coord[1] + (int) (cardWidth * 0.4);
        map.put("EndTurn", coord);


    }

    /**
     * index returned is 0 based
     * -100 MEANS THE PLAYER DID NOT CLICK ON A CARD!
     *
     * @param x
     * @param currentPlayer
     * @return the index in the archive section that the player clicks on
     */
    public int getArchiveCardIndexClicked(int x, Player currentPlayer) {
        int index = (int) ((x - generalSectionCoord.get("Archive")[0]) / cardWidth);
        if (index + 1 > currentPlayer.getArchive().size())
            return -100;
        return index;
    }

    /**
     * index returned is 0 based
     * -100 MEANS THE PLAYER DID NOT CLICK ON A CARD!
     *
     * @param y             mouseListener y
     * @param currentPlayer
     * @param mapCardType   section on the screen that the player clicks on, which will be the different secitons of the toolbar
     * @param type          the type of card like Converter, upgrade, pick, build, file
     * @return the index which the player clicks on in each section
     */
    public int getToolbarCardIndexClicked(int y, Player currentPlayer, String mapCardType, GizmoType type) {
        int topLeftYCoord = generalSectionCoord.get(mapCardType)[1];
        int numOfCards = currentPlayer.getToolBar().getCards().get(type).size();
        int distance = y - topLeftYCoord;
        int cardsOwnedLength = ownedCardsHeaderLength * (numOfCards - 1) + cardWidth;
        int index;


        if (distance <= ownedCardsHeaderLength * numOfCards) {
            index = (int) ((y - topLeftYCoord) / ownedCardsHeaderLength);
            return index;
        }
        else if (distance <= cardsOwnedLength)
            return numOfCards - 1;
        else
            return -100;

    }

    /**
     * index returned is 0 based
     * Don't need to check if out of bounds and return -100 bc it is an array with a fixed length
     *
     * @param x           mouseListener x
     * @param mapCardType type of section on the gameBoard screen. Will only be one of the three tiers that the player can click
     * @return the index of the different card in the speicifc tier clicked by the player. returns -1 if the player clicks on the tier card when researching
     */
    public int getTierCardIndexClicked(int x, String mapCardType) {
        int index = (int) ((x - generalSectionCoord.get(mapCardType)[0]) / cardWidth) - 1;
        return index;
    }

    public int getFirst6MarbleIndexClicked(int y, String mapCardType) {
        int index = 5 - (int) ((y - generalSectionCoord.get(mapCardType)[1]) / first6Width);
        out.println("y0=" + generalSectionCoord.get(mapCardType)[1] + "; first6Width = " + first6Width + "; Index = " + index);

        return index;
    }

    /**
     * @param x             mouseListener y
     * @param y             mouseListener x
     * @param currentPlayer
     * @returns the index of which card that the player is researching is clicked in the prompt section
     */
    public int getResearchCardIndexClicked(int x, int y, Player currentPlayer) {
        int numOfResearchCards = currentPlayer.getCardsResearching().size();
        if (numOfResearchCards <= 5 && y >= promptROrBCardRow1Y && y <= promptROrBCardRow1Y + cardWidth) {
            int index = (int) ((x - promptROrBCardRow1X) / cardWidth);
            if (index < 5)
                return index;
        } else if (numOfResearchCards > 5 ) {
            int index = (int) ((x - promptROrBCardRow2X) / cardWidth);

            if (y >= promptROrBCardRow1Y && y <= promptROrBCardRow1Y + cardWidth)
                if (index < 5)
                    return index;
            else if (y >= promptROrBCardRow2Y && y <= promptROrBCardRow2Y + cardWidth)
                if (index < 5)
                    return index + 5;
        }
        return -100;
    }

    public int getChainReactionCardIndexClicked(int x, int y) {
        int numOfChainReactionCards = getAllTriggeredCards().size();
        if (numOfChainReactionCards <= 5 && y >= promptROrBCardRow1Y && y <= promptROrBCardRow1Y + cardWidth) {
            int index = (int) ((x - promptROrBCardRow1X) / cardWidth);
            if (index < 5)
                return index;
        } else if (numOfChainReactionCards > 5 ) {
            int index = (int) ((x - promptROrBCardRow2X) / cardWidth);

            if (y >= promptROrBCardRow1Y && y <= promptROrBCardRow1Y + cardWidth)
                if (index < 5)
                    return index;
                else if (y >= promptROrBCardRow2Y && y <= promptROrBCardRow2Y + cardWidth)
                    if (index < 5)
                        return index + 5;
        }
        return -100;
    }

    /**
     * is 0 based
     *
     * @param x             mouseListener x
     * @param y             mouseListener y
     * @return returns the index of which marble is clicked in the prompt
     */
    public int getConvertMarbleClicked(int x, int y) {
        int colorOfMarblesSize = getDistinctColorsInRing().size();
        if (y >= promptROrBCardRow1Y + (int) (cardWidth / 2.0) && y <= promptROrBCardRow1Y + cardWidth) {
            int index = (int) ((x - promptROrBCardRow1X) / (int) (cardWidth / 2.0));
            if (index % 2 == 0)
                if (drawOwnedMarbleOnlyForConvert && index < colorOfMarblesSize * 2 - 1)
                    return index;
                else if (!drawOwnedMarbleOnlyForConvert)
                    return index;
        }
        return -100;
    }

    /**
     * Gets only the different types of colors in a persons energy ring
     *
     * @return an arrayList of only different colors
     */
    public ArrayList<MarbleColor> getDistinctColorsInRing() {
        ArrayList<MarbleColor> list = new ArrayList<MarbleColor>();
        boolean sameColorFound;
        for (int i = 0; i < currentPlayer.getEnergyRing().size(); i++) {
            sameColorFound = false;
            for (int j = 0; j < list.size(); j++) {
                if (currentPlayer.getEnergyRing().get(i).getNewColor() == list.get(j)) {
                    sameColorFound = true;
                    break;
                }
            }
            if (sameColorFound == false)
                list.add(currentPlayer.getEnergyRing().get(i).getNewColor());
        }
        return list;
    }


    /**
     * sets which large section is clicked: archive, toolbar card sections, different tiers, first6 marbles, and the buttons, researching cards, and different marbles
     * in the prompt area.
     * Also grabs the specific item that is clicked whether it is card clicked or marble clicked
     *
     * @param x mouseListener x
     * @param y mouseListener y
     */
    public void getObjectClicked(int x, int y) {
        int index;

        // Archive Section
        if (archiveSectionClickable == true && x >= generalSectionCoord.get("Archive")[0] && y >= generalSectionCoord.get("Archive")[1] && x <= generalSectionCoord.get("Archive")[2] && y <= generalSectionCoord.get("Archive")[3]) {
            index = getArchiveCardIndexClicked(x, currentPlayer);
            if (index != -100) {
                cardIsClicked = true;
                archiveSectionClicked = true;
                cardClicked = currentPlayer.getArchive().get(index);
                builtFromArchive = true;
                archiveSectionClickable = false;
            }
        }
//        // Upgrade Section
//        else if (x >= generalSectionCoord.get("UpgradeCards")[0] && y >= generalSectionCoord.get("UpgradeCards")[1] && x <= generalSectionCoord.get("UpgradeCards")[2] && y <= generalSectionCoord.get("UpgradeCards")[3])
//        {
//            int index = getToolbarCardIndexClicked(y, currentPlayer, "UpgradeCards", GizmoType.UPGRADE);
//            if (index != -100) {
//                upgradeSectionClicked = true;
//                cardClicked = currentPlayer.getToolBar().getCards().get(GizmoType.UPGRADE).get(index);
//                upgradeSectionClickable = false;
//            }
//        }
        // Convert Section
        else if (convertSectionClickable == true && x >= generalSectionCoord.get("ConvertCards")[0] && y >= generalSectionCoord.get("ConvertCards")[1] && x <= generalSectionCoord.get("ConvertCards")[2] && y <= generalSectionCoord.get("ConvertCards")[3]) {
            index = getToolbarCardIndexClicked(y, currentPlayer, "ConvertCards", GizmoType.CONVERTOR);
            if (index != -100) {
                cardIsClicked = true;
                convertSectionClicked = true;
                cardClicked = currentPlayer.getToolBar().getCards().get(GizmoType.CONVERTOR).get(index);
                convertSectionClickable = false;
            }
        }
        // File Section
        else if (fileSectionClickable == true && x >= generalSectionCoord.get("FileCards")[0] && y >= generalSectionCoord.get("FileCards")[1] && x <= generalSectionCoord.get("FileCards")[2] && y <= generalSectionCoord.get("FileCards")[3]) {
            index = getToolbarCardIndexClicked(y, currentPlayer, "FileCards", GizmoType.FILE);
            if (index != -100) {
                cardIsClicked = true;
                fileSectionClicked = true;
                cardClicked = currentPlayer.getToolBar().getCards().get(GizmoType.FILE).get(index);
                fileSectionClickable = false;
            }
        }
        // Pick Section
        else if (pickSectionClickable == true && x >= generalSectionCoord.get("PickCards")[0] && y >= generalSectionCoord.get("PickCards")[1] && x <= generalSectionCoord.get("PickCards")[2] && y <= generalSectionCoord.get("PickCards")[3]) {
            index = getToolbarCardIndexClicked(y, currentPlayer, "PickCards", GizmoType.PICK);
            if (index != -100) {
                cardIsClicked = true;
                pickSectionClicked = true;
                cardClicked = currentPlayer.getToolBar().getCards().get(GizmoType.PICK).get(index);
                pickSectionClickable = false;
            }
        }
        // Build Section
        else if (buildSectionClickable == true && x >= generalSectionCoord.get("BuildCards")[0] && y >= generalSectionCoord.get("BuildCards")[1] && x <= generalSectionCoord.get("BuildCards")[2] && y <= generalSectionCoord.get("BuildCards")[3]) {
            index = getToolbarCardIndexClicked(y, currentPlayer, "BuildCards", GizmoType.BUILD);
            if (index != -100) {
                cardIsClicked = true;
                buildSectionClicked = true;
                cardClicked = currentPlayer.getToolBar().getCards().get(GizmoType.BUILD).get(index);
                buildSectionClickable = false;
            }
        }
        // Tier 1 Section
        else if (tier1SectionClickable && x >= generalSectionCoord.get("Tier1Cards")[0] && y >= generalSectionCoord.get("Tier1Cards")[1] && x <= generalSectionCoord.get("Tier1Cards")[2] && y <= generalSectionCoord.get("Tier1Cards")[3]) {
            tier1SectionClicked = true;
            int index1 = getTierCardIndexClicked(x, "Tier1Cards");
            if (index1 == -1 && tier1CoverClickable) {
                tier1CoverClicked = true;
                drawTier1Cards = true;
            }
            else {
                cardIsClicked = true;
                tierCardClickedIndex = index1;
                cardClicked = deck.getTier1()[index1];
                tier1SectionClickable = false;
            }
        }
        // Tier 2 Section
        else if (tier2SectionClickable && x >= generalSectionCoord.get("Tier2Cards")[0] && y >= generalSectionCoord.get("Tier2Cards")[1] && x <= generalSectionCoord.get("Tier2Cards")[2] && y <= generalSectionCoord.get("Tier2Cards")[3]) {
            tier2SectionClicked = true;
            int index1 = getTierCardIndexClicked(x, "Tier2Cards");
            if (index1 == -1 && tier2CoverClickable) {
                tier2CoverClicked = true;
                drawTier2Cards = true;
            }
            else {
                cardIsClicked = true;
                cardClicked = deck.getTier2()[index1];
                tierCardClickedIndex = index1;
                tier2SectionClickable = false;
            }
        }
        // Tier 3 Section
        else if (tier3SectionClickable && x >= generalSectionCoord.get("Tier3Cards")[0] && y >= generalSectionCoord.get("Tier3Cards")[1] && x <= generalSectionCoord.get("Tier3Cards")[2] && y <= generalSectionCoord.get("Tier3Cards")[3]) {
            tier3SectionClicked = true;
            int index1 = getTierCardIndexClicked(x, "Tier3Cards");
            if (index1 == -1 && tier3CoverClickable) {
                tier3CoverClicked = true;
                drawTier3Cards = true;
            }
            else {
                cardIsClicked = true;
                cardClicked = deck.getTier3()[index1];
                tierCardClickedIndex = index1;
                tier3SectionClickable = false;
            }
        }
        // first 6 marbles section
        else if (first6MarbleClickable && x >= generalSectionCoord.get("First6Marbles")[0] && y >= generalSectionCoord.get("First6Marbles")[1] && x <= generalSectionCoord.get("First6Marbles")[2] && y <= generalSectionCoord.get("First6Marbles")[3]) {
            index = getFirst6MarbleIndexClicked(y, "First6Marbles");
            if (index >= 0 && index < 6) {
                first6Clicked = true;
                marbleClickedIndex = index;
                first6MarbleClickable = false;
//                out.println("index of marble clicked = "+ index);
                cardIsClicked = false;
            }
        }
        //Prompt 4 main action
        else if (displayPromptChoice == 1) {
            if (fileButtonVisible && x >= generalSectionCoord.get("Button1")[0] && y >= generalSectionCoord.get("Button1")[1] && x <= generalSectionCoord.get("Button1")[2] && y <= generalSectionCoord.get("Button1")[3])
                fileButtonClicked = true;
            else if (pickButtonVisible && x >= generalSectionCoord.get("Button2")[0] && y >= generalSectionCoord.get("Button2")[1] && x <= generalSectionCoord.get("Button2")[2] && y <= generalSectionCoord.get("Button2")[3])
                pickButtonClicked = true;
            else if (buildButtonVisible && x >= generalSectionCoord.get("Button3")[0] && y >= generalSectionCoord.get("Button3")[1] && x <= generalSectionCoord.get("Button3")[2] && y <= generalSectionCoord.get("Button3")[3]) {
                buildButtonClicked = true;
            } else if (researchButtonVisible && x >= generalSectionCoord.get("Button4")[0] && y >= generalSectionCoord.get("Button4")[1] && x <= generalSectionCoord.get("Button4")[2] && y <= generalSectionCoord.get("Button4")[3])
                researchButtonClicked = true;
            if (fileButtonClicked || pickButtonClicked || buildButtonClicked || researchButtonClicked) {
                buildButtonVisible = false;
                pickButtonVisible = false;
                researchButtonVisible = false;
                fileButtonVisible = false;
            }
            cardIsClicked = false;
        }
        // building do you want to convert yes or no buttons
        else if (displayPromptChoice == 2 && yesButtonVisible && noButtonVisible) {
            if (x >= generalSectionCoord.get("Button1")[0] && y >= generalSectionCoord.get("Button1")[1] && x <= generalSectionCoord.get("Button1")[2] && y <= generalSectionCoord.get("Button1")[3])
                yesButtonClicked = true;
            else if (x >= generalSectionCoord.get("Button2")[0] && y >= generalSectionCoord.get("Button2")[1] && x <= generalSectionCoord.get("Button2")[2] && y <= generalSectionCoord.get("Button2")[3])
                noButtonClicked = true;

            yesButtonVisible = false;
            noButtonVisible = false;
            cardIsClicked = false;
        }
        // Converting marbles color
        // don't reset bc convert method choice might need to redraw marbles for player to click on again like any to any
        else if (displayPromptChoice == 3 && marblesVisible) {
            int index1 = getConvertMarbleClicked(x, y);
            if (index1 != -100 && drawOwnedMarbleOnlyForConvert)
                colorClicked = getDistinctColorsInRing().get(index1);
            else if (index1 != -100 && !drawOwnedMarbleOnlyForConvert)
            {
                switch (index1)
                {
                    case 0: colorClicked = MarbleColor.RED; break;
                    case 2: colorClicked = MarbleColor.BLUE; break;
                    case 4: colorClicked = MarbleColor.BLACK; break;
                    case 6: colorClicked = MarbleColor.YELLOW; break;
                }
            }
            marbleColorClicked = true;
        }


        // Research card clicked
        else if (displayPromptChoice == 4 && researchingCardsVisible) {
            index = getResearchCardIndexClicked(x, y, currentPlayer);
            if (index != -100) {
                researchingCardsClicked = true;
                researchingCardsVisible = false;
                cardIsClicked = false;
                cardClicked = currentPlayer.getCardsResearching().get(index);
                cardResearching = cardClicked;
                drawResearchingCards = true;
            }
        }
        //Prompt research build and file button
        else if (displayPromptChoice == 5) {
            if (x >= generalSectionCoord.get("Button1")[0] && y >= generalSectionCoord.get("Button1")[1] && x <= generalSectionCoord.get("Button1")[2] && y <= generalSectionCoord.get("Button1")[3])
                buildButtonClicked = true;
            else if (x >= generalSectionCoord.get("Button2")[0] && y >= generalSectionCoord.get("Button2")[1] && x <= generalSectionCoord.get("Button2")[2] && y <= generalSectionCoord.get("Button2")[3])
                fileButtonClicked = true;

            fileButtonVisible = false;
            buildButtonVisible = false;
        }
        else if (displayPromptChoice == 6 && endTurnButtonVisible) {
            if (x >= generalSectionCoord.get("EndTurn")[0] && y >= generalSectionCoord.get("EndTurn")[1] && x <= generalSectionCoord.get("EndTurn")[2] && y <= generalSectionCoord.get("EndTurn")[3])
                endTurnButtonClicked = true;
        }

        // Chain reaction cards clicked
        else if (displayPromptChoice == 7 && chainReactionCardsVisible) {
            index = getChainReactionCardIndexClicked(x, y);
            if (index != -100) {
                chainReactionCardsVisible = false;
                chainReactionCardClicked = true;
                cardIsClicked = false;
                cardResearching = cardClicked;
                Iterator<GizmoCard> iter = getAllTriggeredCards().iterator();
                int count = 0;
                while (iter.hasNext())
                {
                    GizmoCard temp = iter.next();
                    if (count == index)
                        cardClicked = temp;
                    count++;
                }
            }
        }

    }

    /**
     * gets the type of convert method that converts something to any that the player chooses. Can only be 1-3
     * 1: one color to any
     * 2: 1 or 2 colors to any
     * 3: any to any
     *
     * @param currentPlayer current player that is using the convert method
     * @return the choice from 1-3
     */
    public int getConvertMethodInPrompt(Player currentPlayer) {
        return currentPlayer.getConvertMethod();
    }


    /**
     * takes in a marble color and returns it as its corresponding image
     *
     * @param color color of the marble that is being changed
     * @return image of the corresponding color
     */
    public BufferedImage marbleToColor(MarbleColor color) {
        switch (color) {
            case RED:
                return redMarble;
            case BLUE:
                return blueMarble;
            case BLACK:
                return blackMarble;
            case YELLOW:
                return yellowMarble;
        }
        return redMarble;
    }

    /**
     * takes in the number of the bonus victory token and returns it as its image
     *
     * @param num the number of the bonus victory point token
     * @return the corresponding image
     */
    public BufferedImage numberToBonusVPToken(int num) {
        switch (num) {
            case 1:
                return victoryPoint1;
            case 5:
                return victoryPoint5;
        }
        return victoryPoint1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x >= 566 && x <= 1052 && y >= 734 && y <= 805 && gameStart == false) {
            rulesButtonClicked = true;
            //System.out.println("Downloading Rules");
            repaint();
            System.out.println("Downloaded Rules");
            downloadRules();
        }
        if (x >= 514 && x <= 1086 && y >= 586 && y <= 721 && gameStart == false) {
            System.out.println("Game Started!");
            gameStart = true;
            startGameButtonClicked = true;
            displayPromptChoice = 1;
        }
        getObjectClicked(x, y);


        out.println("( " + x + ", " + y + " )");
        // player clicks on one of the 3 tiers and then clicks on a card to research
        repaint();
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

    /**
     * draws a string that says rules downloaded. Doesn't work fully because should display the moment u click on the how to play button
     * @param g used to draw
     */
    public void drawRulesDownloaded(Graphics g) {
        Font f = new Font("Serif", Font.BOLD, 35);
        g.setFont(f);
        g.setColor(Color.WHITE);
        g.drawString("Rules successfully downloaded! Loading it for you right now...", 300, 50);
    }

    /**
     * sets the prompt for paint to display
     * @param str the prompt
     */
    public void setPrompt(String str) {
        promptStr = str;
    }

    /**
     * a method that can pause the game for a number of seconds to allow the player to read directions or see their result
     * @param seconds the number of seconds this method waits
     */
    public void waitForSeconds(double seconds) {
        try {
            Thread.sleep((int) (seconds * 1000));
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void waitFor4ActionClick() {
        while (!fileButtonClicked && !pickButtonClicked && !researchButtonClicked && !buildButtonClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void waitForFileCardChoice() {
        while (!tier1SectionClicked && !tier2SectionClicked && !tier3SectionClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void waitForPickMarbleClick() {
        while (!first6Clicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void waitForEndTurnClick() {
        while (!endTurnButtonClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void waitForBuildCardChoice() {
        while (!tier1SectionClicked && !tier2SectionClicked && !tier3SectionClicked && !archiveSectionClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void waitForYesOrNoClick() {
        while (!yesButtonClicked && !noButtonClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void waitForConvertorCardChoice() {
        while (!convertSectionClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void waitForConvertColorChoice()
    {
        while (!marbleColorClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void waitForResearchTierChoice()
    {
        while (!tier1CoverClicked && !tier2CoverClicked && !tier3CoverClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void waitForResearchCardChoice()
    {
        while (!researchingCardsClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void waitForChainReactionCardChoice()
    {
        while (!chainReactionCardClicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static void downloadUsingStream(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
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

    private void downloadRules() {
        String url = "https://cmon-files.s3.amazonaws.com/pdf/assets_item/resource/126/Rulebook_Gizmos.pdf";
        String home = System.getProperty("user.home");
        System.out.println("Downloading Rules");
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://cmon-files.s3.amazonaws.com/pdf/assets_item/resource/126/Rulebook_Gizmos.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * test data for drawing all images in their right spots
//     *
//     * @param
//     */
//    public void testDrawPopulate(int playerNumber) {
//        //prompt status
////        playerList.get(playerNumber-1).setActionPicked(1);
////        playerList.get(playerNumber-1).setConvertMethodClicked(3);
////        playerList.get(playerNumber-1).set1Or2ConvertChoice(2);
////        try {
////            cardClicked = new GizmoCard(MarbleColor.YELLOW, 5,5, GizmoType.BUILD, "buildthisorthatfile", 5, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow5Rank3.png"))));
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////        cardIsClicked = true;
////        startOfPlayerTurn = false;
//
//        //Archive
//        ArrayList<GizmoCard> arrList = new ArrayList<GizmoCard>();
//        playerList.get(playerNumber - 1).setArchive(arrList);
//        for (int i = 0; i < 8; i++) {
//            try {
//                playerList.get(playerNumber - 1).getArchive().add(new GizmoCard(MarbleColor.YELLOW, 1, 1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow9Rank1.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        //Cards in Toolbar
//        ArrayList<GizmoCard> list = new ArrayList<GizmoCard>();
//        for (int i = 0; i < 10; i++) {
//            try {
//                list.add(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black9Rank1.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        for (int i = 0; i < 10; i++)
//            playerList.get(playerNumber - 1).getToolBar().getCards().put(GizmoType.UPGRADE, list);
//
//        list = new ArrayList<GizmoCard>();
//        for (int i = 0; i < 10; i++) {
//            try {
//                list.add(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.CONVERTOR, "convert1toany", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black7Rank1.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        for (int i = 0; i < 10; i++)
//            playerList.get(playerNumber - 1).getToolBar().getCards().put(GizmoType.CONVERTOR, list);
//
//        list = new ArrayList<GizmoCard>();
//        for (int i = 0; i < 10; i++) {
//            try {
//                list.add(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.FILE, "filepick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black3Rank1.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        for (int i = 0; i < 10; i++)
//            playerList.get(playerNumber - 1).getToolBar().getCards().put(GizmoType.FILE, list);
//
//        list = new ArrayList<GizmoCard>();
//        for (int i = 0; i < 10; i++) {
//            try {
//                list.add(new GizmoCard(MarbleColor.BLUE, 2, 2, GizmoType.PICK, "pickthisorthatpickrandom", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue1Rank2.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        for (int i = 0; i < 10; i++)
//            playerList.get(playerNumber - 1).getToolBar().getCards().put(GizmoType.PICK, list);
//
//
//        list = new ArrayList<GizmoCard>();
//        for (int i = 0; i < 10; i++) {
//            try {
//                list.add(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.BUILD, "buildpick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black2Rank1.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        for (int i = 0; i < 10; i++)
//            playerList.get(playerNumber - 1).getToolBar().getCards().put(GizmoType.BUILD, list);
//
//        playerList.get(playerNumber - 1).setCardsResearching(new ArrayList<GizmoCard>());
//        for (int i = 0; i < 10; i++) {
//            try {
//                playerList.get(playerNumber - 1).addCardsResearching(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.BUILD, "buildpick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black2Rank1.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        // Energy Ring Marble
//        playerList.get(playerNumber - 1).getPlayerRingClass().setEnergyRing(new ArrayList<Marble>());
//        for (int i = 0; i < 12; i++)
//            playerList.get(playerNumber - 1).getPlayerRingClass().addMarble(new Marble(MarbleColor.RED));
//
//        //Victory point tokens
//        playerList.get(playerNumber - 1).setBonusVP(new ArrayList<BonusVictoryPoint>());
//        playerList.get(playerNumber - 1).addBonusVPTokens(new BonusVictoryPoint(5));
//        playerList.get(playerNumber - 1).addBonusVPTokens(new BonusVictoryPoint(5));
//        playerList.get(playerNumber - 1).addBonusVPTokens(new BonusVictoryPoint(5));
//        playerList.get(playerNumber - 1).addBonusVPTokens(new BonusVictoryPoint(5));
//        playerList.get(playerNumber - 1).addBonusVPTokens(new BonusVictoryPoint(1));
//        playerList.get(playerNumber - 1).addBonusVPTokens(new BonusVictoryPoint(1));
//        playerList.get(playerNumber - 1).addBonusVPTokens(new BonusVictoryPoint(1));
//        playerList.get(playerNumber - 1).addBonusVPTokens(new BonusVictoryPoint(1));
//
//        //small cards in toolbar
//        list = new ArrayList<GizmoCard>();
//        for (int i = 0; i < 10; i++) {
//            try {
//                list.add(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black9Rank1.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        for (int i = 0; i < 10; i++)
//            playerList.get(playerNumber - 1).getToolBar().getCards().put(GizmoType.UPGRADE, list);
//
//        list = new ArrayList<GizmoCard>();
//        for (int i = 0; i < 10; i++) {
//            try {
//                list.add(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.CONVERTOR, "convert1toany", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black7Rank1.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        for (int i = 0; i < 10; i++)
//            playerList.get(playerNumber - 1).getToolBar().getCards().put(GizmoType.CONVERTOR, list);
//
//        list = new ArrayList<GizmoCard>();
//        for (int i = 0; i < 10; i++) {
//            try {
//                list.add(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.FILE, "filepick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black3Rank1.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        for (int i = 0; i < 10; i++)
//            playerList.get(playerNumber - 1).getToolBar().getCards().put(GizmoType.FILE, list);
//
//        list = new ArrayList<GizmoCard>();
//        for (int i = 0; i < 10; i++) {
//            try {
//                list.add(new GizmoCard(MarbleColor.BLUE, 2, 2, GizmoType.PICK, "pickthisorthatpickrandom", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue1Rank2.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        for (int i = 0; i < 10; i++)
//            playerList.get(playerNumber - 1).getToolBar().getCards().put(GizmoType.PICK, list);
//
//
//        list = new ArrayList<GizmoCard>();
//        for (int i = 0; i < 10; i++) {
//            try {
//                list.add(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.BUILD, "buildpick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black2Rank1.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        for (int i = 0; i < 10; i++)
//            playerList.get(playerNumber - 1).getToolBar().getCards().put(GizmoType.BUILD, list);
//
//        //SmallArchive
//        ArrayList<GizmoCard> arrayList = new ArrayList<GizmoCard>();
//        playerList.get(playerNumber - 1).setArchive(arrayList);
//        for (int h = 0; h < 8; h++) {
//            try {
//                playerList.get(playerNumber - 1).getArchive().add(new GizmoCard(MarbleColor.YELLOW, 1, 1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow9Rank1.png")))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
}

