package Classes;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

/**
 * A class which represents a deck of cards. This is a singleton class, as there should only be one deck of cards in the game.
 * @author 0xc60f
 */
public class Deck {
    private EnumMap<GizmoLevel, Stack<GizmoCard>> cards;
    private Stack<GizmoCard> level1;
    private Stack<GizmoCard> level2;
    private Stack<GizmoCard> level3;
    private GizmoCard[] tier1;
    private GizmoCard[] tier2;
    private GizmoCard[] tier3;

    /**
     * Initializes the deck of cards. This should only be called once.
     * This will create the decks of cards and move the top 5 cards from each deck into the tier arrays.
     */
    public Deck() throws IOException {
        level1 = new Stack<>();
        level2 = new Stack<>();
        level3 = new Stack<>();
        cards = new EnumMap<>(GizmoLevel.class);
        cards.put(GizmoLevel.LEVEL1, level1);
        cards.put(GizmoLevel.LEVEL2, level2);
        cards.put(GizmoLevel.LEVEL3, level3);
        addAllCards();
        level1 = cards.get(GizmoLevel.LEVEL1);
        level2 = cards.get(GizmoLevel.LEVEL2);
        level3 = cards.get(GizmoLevel.LEVEL3);

        tier1 = new GizmoCard[4];
        tier2 = new GizmoCard[3];
        tier3 = new GizmoCard[2];
        IntStream.range(0, 4).forEach(i -> tier1[i] = cards.get(GizmoLevel.LEVEL1).pop());
        IntStream.range(0, 3).forEach(i -> tier2[i] = cards.get(GizmoLevel.LEVEL2).pop());
        IntStream.range(0, 2).forEach(i -> tier3[i] = cards.get(GizmoLevel.LEVEL3).pop());

    }
    private void addAllCards() throws IOException{
        level1.push(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.BUILD, "build1get1victorypoint", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black1Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLACK, 2, 2, GizmoType.PICK, "pickthisorthatpickrandom", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black1Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLACK, 5, 5, GizmoType.CONVERTOR, "convert1or2to2", 5, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black1Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.BUILD, "buildpick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black2Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLACK, 3, 3, GizmoType.UPGRADE, "e2f1r2", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black2Rank2.png")))));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.FILE, "filepick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black3Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLACK, 3, 3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black3Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLACK, 5, 5, GizmoType.BUILD, "buildthisorthatget2victorypoints", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black3Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black4Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLACK, 2, 2, GizmoType.CONVERTOR, "convert1or2toany", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black4Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLACK, 5,5, GizmoType.BUILD, "buildthisorthatfile", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black4Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black5Rank1.png")))));
        level3.push(new GizmoCard(MarbleColor.BLACK, 6,6, GizmoType.BUILD, "buildtier2pick2from6", 9, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black5Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1,1, GizmoType.CONVERTOR, "convert1toany", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black6Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLACK, 3,3, GizmoType.BUILD, "buildfromfilepick2from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black6Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLACK, 6,6, GizmoType.UPGRADE, "upgrade1lessenergytobuildfromresearch", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black6Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1,1, GizmoType.CONVERTOR, "convert1toany", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black7Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLACK, 2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black7Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLACK, 4,8, GizmoType.UPGRADE, "upgradenoresearching", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black7Rank3.png")))));
        level2.push(new GizmoCard(MarbleColor.BLACK, 2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black8Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLACK, 4,4, GizmoType.UPGRADE, "e4", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black8Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1,1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black9Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLACK, 3,3, GizmoType.BUILD, "buildthisorthatget1victorypoint", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Black9Rank2.png")))));
        level1.push(new GizmoCard(MarbleColor.BLUE, 1,1, GizmoType.BUILD, "build1get1victorypoint", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue1Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLUE, 2,2, GizmoType.PICK, "pickthisorthatpickrandom", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue1Rank2.png")))));

        level3.push(new GizmoCard(MarbleColor.BLUE, 5,5, GizmoType.CONVERTOR, "convert1to2", 5, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue1Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.BUILD, "buildpick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue2Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLUE, 1,1, GizmoType.UPGRADE, "e2f1r2", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue2Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLUE,4,4, GizmoType.FILE, "filepick3random", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue2Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.FILE, "filepick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue3Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLUE,3,3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue3Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLUE,7,7, GizmoType.BUILD, "buildthisorthatresearch", 7, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue3Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue4Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLUE, 2,2, GizmoType.CONVERTOR, "convert1or2toany", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue4Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLUE, 6,6, GizmoType.BUILD, "buildthisorthatbuildtier1for0", 8, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue4Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue5Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLUE, 3,3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue5Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLUE, 5,5, GizmoType.UPGRADE, "upgrade1lessenergytobuildfromresearch", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue5Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.CONVERTOR, "convert1toany", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue6Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLUE, 3,3, GizmoType.BUILD, "buildfromfilepick2from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue6Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLUE, 4,6, GizmoType.UPGRADE, "upgradenoresearching", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue6Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.CONVERTOR, "convert1toany", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue7Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLUE,2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue7Rank2.png")))));
        level1.push(new GizmoCard(MarbleColor.BLUE, 1,1, GizmoType.UPGRADE, "e1r1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue8Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLUE, 2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue8Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.BLUE, 5,5, GizmoType.UPGRADE, "upgrade1lessenergytobuildtier2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue8Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.BLUE, 1,1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue9Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.BLUE, 3,3, GizmoType.BUILD, "buildthisorthatget1victorypoint", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Blue9Rank2.png")))));

        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.BUILD, "build1get1victorypoint",1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red1Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.RED, 2, 2, GizmoType.PICK, "pickthisorthatpickrandom", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red1Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.RED, 6, 6, GizmoType.BUILD, "buildtier2pick2from6", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red1Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.BUILD, "build1pick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red2Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.RED, 3, 3, GizmoType.UPGRADE, "e2f1r2", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red2Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.RED, 4, 4, GizmoType.CONVERTOR, "convert1to1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red2Rank3.png")))));
        level1.push((new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.FILE, "filepick1from6", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red3Rank1.png"))))));
        level2.push((new GizmoCard(MarbleColor.RED, 3, 3, GizmoType.CONVERTOR, "convert1to2any", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red3Rank2.png"))))));
        level3.push(new GizmoCard(MarbleColor.RED, 4, 4, GizmoType.FILE, "fileget1victorypoint", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red3Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red4Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.RED, 2, 2, GizmoType.CONVERTOR, "", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red4Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.RED, 5, 5, GizmoType.BUILD, "buildfromfileget2victorypoints", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red4Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.PICK, "pickthispickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red5Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.RED, 3, 3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red5Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.RED, 5, 5, GizmoType.BUILD, "buildthisorthatget2victorypoints", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red5Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.CONVERTOR, "convert1toany", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red6Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.RED, 3, 3, GizmoType.BUILD, "buildFromFilePick2From6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red6Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.RED, 7, 7, GizmoType.BUILD, "buildThisOrThatResearch", 7, ImageIO.read(Objects.requireNonNull((Deck.class.getResource("/Images/GizmoCards/Red6Rank3.png"))))));
        level1.push((new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.CONVERTOR, "convert1toany", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red7rank1.png"))))));
        level2.push(new GizmoCard(MarbleColor.RED, 2, 2, GizmoType.BUILD, "buildThisOrThatPick1From", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red7Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.RED, 5, 5, GizmoType.UPGRADE, "upgrade1LessEnergyToBuildFromFile", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red7Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red8Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.RED, 2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red8Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.RED, 4, 7, GizmoType.UPGRADE, "upgradenoresearching", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red8Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.UPGRADE, "e1r1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red9Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.RED, 3,3, GizmoType.BUILD, "buildthisorthatget1victorypoint", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Red9Rank2.png")))));

        level1.push(new GizmoCard(MarbleColor.YELLOW,1,1,GizmoType.BUILD, "buildget1victorypoint", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow1Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.YELLOW,2,2, GizmoType.PICK, "pickthisorthatpickrandom",2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow1Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.YELLOW,4,4, GizmoType.CONVERTOR, "convertanytoany", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow1Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.YELLOW,4,4, GizmoType.BUILD, "build1pick1from6",2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow2Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 3,3, GizmoType.RESEARCH, "e2f1r2", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow2Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 4,4, GizmoType.FILE, "filepick3random", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow2Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.YELLOW,1,1, GizmoType.FILE, "filepick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow3Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 3,3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow3Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 5,5, GizmoType.BUILD, "buildthisorthatget2victorypoints", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow3Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.YELLOW, 1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow4Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 2,2, GizmoType.CONVERTOR, "convert1or2toany", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow4Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 6,6, GizmoType.BUILD, "buildthisorthatfile", 8, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow4Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.YELLOW, 1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow5Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 3,3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow5Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 5,5, GizmoType.BUILD, "buildthisorthatfile", 5, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow5Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.YELLOW, 1,1, GizmoType.CONVERTOR, "convert1toany", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow6Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.YELLOW,3,3, GizmoType.BUILD, "buildfromfilepick2from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow6Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 6,6, GizmoType.UPGRADE, "upgrade1lessenergytobuildfromresearch", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow6Rank3.png")))));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow7Rank2.png")))));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 4,8, GizmoType.UPGRADE, "upgradenoresearching", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow7Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.YELLOW,1,1, GizmoType.UPGRADE, "e1r1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow8Rank1.png")))));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 5,5, GizmoType.UPGRADE, "upgrade1lessenergytobuildtier2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow8Rank3.png")))));
        level1.push(new GizmoCard(MarbleColor.YELLOW, 1,1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow9Rank1.png")))));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 3,3, GizmoType.BUILD, "buildthisorthatget1victorypoint", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCards/Yellow9Rank2.png")))));

    }

    /**
     * Gets the face-up cards for the given level.
     * @param level The level to get the face-up cards for as a <code>GizmoLevel</code> enum.
     * @return The face-up cards for the given level as an array of <code>GizmoCard</code>s.
     */
    public GizmoCard[] getFaceUp(GizmoLevel level) {
        return switch (level) {
            case LEVEL1 -> tier1;
            case LEVEL2 -> tier2;
            case LEVEL3 -> tier3;
            default -> null;
        };
    }

    /**
     * Puts the given card on the bottom of the deck for the given level.
     * @param card The card to put on the bottom of the deck as a <code>GizmoCard</code>.
     * @param level The level to put the card on the bottom of the deck for as a <code>GizmoLevel</code> enum.
     */
    public void addToBottom(GizmoCard card, GizmoLevel level) {
        cards.get(level).add(0, card);
    }

    /**
     * Gets the top card from the deck for the given level.
     * @param level The level to get the top card from as a <code>GizmoLevel</code> enum.
     * @return The top card from the deck for the given level as a <code>GizmoCard</code>.
     */
    public GizmoCard drawGizmo(GizmoLevel level) {
        return cards.get(level).pop();
    }

    /**
     * Researches the given amount of cards for the given level.
     * @param level The level to research cards for as a <code>GizmoLevel</code> enum.
     * @param amount The amount of cards to research as an <code>int</code>.
     * @return The researched cards as an <code>ArrayList</code> of <code>GizmoCard</code>s.
     */
    public ArrayList<GizmoCard> research(GizmoLevel level, int amount) {
        ArrayList<GizmoCard> cardList = new ArrayList<>();
        IntStream.range(0, amount).forEach(i -> cards.get(level).add(cards.get(level).pop()));
        return cardList;
    }

    /**
     * Checks to see if the given level is empty.
     * @param level The level to check as a <code>GizmoLevel</code> enum.
     * @return <code>true</code> if the level is empty, <code>false</code> otherwise.
     */
    public boolean isLevelEmpty(GizmoLevel level) {
        return cards.get(level).isEmpty();
    }

    /**
     * Checks to see if the deck is empty.
     * @return <code>true</code> if the deck is empty, <code>false</code> otherwise.
     */
    public boolean isDeckEmpty() {
        return cards.get(GizmoLevel.LEVEL1).isEmpty() && cards.get(GizmoLevel.LEVEL2).isEmpty() && cards.get(GizmoLevel.LEVEL3).isEmpty();
    }


    /**
     * returns the tier1 cards on the field
     */
    public GizmoCard[] getTier1()
    {
        return tier1;
    }

    /**
     * returns the tier2 cards on the field
     */
    public GizmoCard[] getTier2()
    {
        return tier2;
    }

    /**
     * returns the tier3 cards on the field
     */
    public GizmoCard[] getTier3()
    {
        return tier3;
    }
}
