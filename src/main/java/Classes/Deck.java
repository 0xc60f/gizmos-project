package Classes;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.Collections;

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
        Collections.shuffle(level1);
        level2 = cards.get(GizmoLevel.LEVEL2);
        Collections.shuffle(level2);
        level3 = cards.get(GizmoLevel.LEVEL3);
        Collections.shuffle(level3);

        tier1 = new GizmoCard[4];
        tier2 = new GizmoCard[3];
        tier3 = new GizmoCard[2];
        IntStream.range(0, 4).forEach(i -> tier1[i] = cards.get(GizmoLevel.LEVEL1).pop());
        IntStream.range(0, 3).forEach(i -> tier2[i] = cards.get(GizmoLevel.LEVEL2).pop());
        IntStream.range(0, 2).forEach(i -> tier3[i] = cards.get(GizmoLevel.LEVEL3).pop());

    }
    private void addAllCards() throws IOException{
        level1.push(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.BUILD, "build1get1victorypoint", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black1Rank1.png"))), MarbleColor.RED, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLACK, 2, 2, GizmoType.PICK, "pickthisorthatpickrandom", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black1Rank2.png"))), MarbleColor.RED, MarbleColor.YELLOW, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLACK, 5, 5, GizmoType.CONVERTOR, "convert1or2to2", 5, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black1Rank3.png"))), MarbleColor.BLUE, MarbleColor.YELLOW, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.BUILD, "buildpick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black2Rank1.png"))), MarbleColor.BLUE, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLACK, 3, 3, GizmoType.UPGRADE, "e2f1r2", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black2Rank2.png"))), GizmoLevel.LEVEL2));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1, 1, GizmoType.FILE, "filepick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black3Rank1.png"))), GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLACK, 3, 3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black3Rank2.png"))), MarbleColor.RED, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLACK, 5, 5, GizmoType.BUILD, "buildthisorthatget2victorypoints", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black3Rank3.png"))), MarbleColor.RED, MarbleColor.BLUE, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black4Rank1.png"))), MarbleColor.BLUE, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLACK, 2, 2, GizmoType.CONVERTOR, "convert1Or2ToAny", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black4Rank2.png"))), MarbleColor.BLUE, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLACK, 5,5, GizmoType.BUILD, "buildthisorthatfile", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black4Rank3.png"))), MarbleColor.BLUE, MarbleColor.YELLOW, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black5Rank1.png"))), MarbleColor.YELLOW, GizmoLevel.LEVEL1));
        level3.push(new GizmoCard(MarbleColor.BLACK, 6,6, GizmoType.BUILD, "buildtier2pick2from6", 9, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black5Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1,1, GizmoType.CONVERTOR, "convert1ToAny", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black6Rank1.png"))), MarbleColor.YELLOW, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLACK, 3,3, GizmoType.BUILD, "buildfromfilepick2from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black6Rank2.png"))), GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLACK, 6,6, GizmoType.UPGRADE, "upgrade1lessenergytobuildfromresearch", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black6Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1,1, GizmoType.CONVERTOR, "convert1ToAny", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black7Rank1.png"))), MarbleColor.RED, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLACK, 2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black7Rank2.png"))), MarbleColor.RED, MarbleColor.YELLOW, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLACK, 4,8, GizmoType.UPGRADE, "upgradeNoResearching", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black7Rank3.png"))), GizmoLevel.LEVEL3));
        level2.push(new GizmoCard(MarbleColor.BLACK, 2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black8Rank2.png"))), MarbleColor.BLUE, MarbleColor.RED, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLACK, 4,4, GizmoType.UPGRADE, "e4", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black8Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLACK, 1,1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black9Rank1.png"))), GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLACK, 3,3, GizmoType.BUILD, "buildthisorthatget1victorypoint", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Black9Rank2.png"))), MarbleColor.YELLOW, MarbleColor.BLUE, GizmoLevel.LEVEL2));
        level1.push(new GizmoCard(MarbleColor.BLUE, 1,1, GizmoType.BUILD, "build1get1victorypoint", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue1Rank1.png"))), MarbleColor.BLACK, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLUE, 2,2, GizmoType.PICK, "pickthisorthatpickrandom", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue1Rank2.png"))), MarbleColor.YELLOW, MarbleColor.BLACK, GizmoLevel.LEVEL2));

        level3.push(new GizmoCard(MarbleColor.BLUE, 5,5, GizmoType.CONVERTOR, "convert1to2", 5, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue1Rank3.png"))), MarbleColor.RED, MarbleColor.BLACK, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.BUILD, "buildpick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue2Rank1.png"))), MarbleColor.YELLOW, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLUE, 1,1, GizmoType.UPGRADE, "e2f1r2", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue2Rank2.png"))), GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLUE,4,4, GizmoType.FILE, "filepick3random", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue2Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.FILE, "filepick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue3Rank1.png"))), GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLUE,3,3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue3Rank2.png"))), MarbleColor.RED, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLUE,7,7, GizmoType.BUILD, "buildthisorthatresearch", 7, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue3Rank3.png"))), MarbleColor.YELLOW, MarbleColor.RED, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue4Rank1.png"))), MarbleColor.RED, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLUE, 2,2, GizmoType.CONVERTOR, "convert1Or2ToAny", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue4Rank2.png"))), MarbleColor.BLACK, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLUE, 6,6, GizmoType.BUILD, "buildthisorthatbuildtier1for0", 8, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue4Rank3.png"))), MarbleColor.YELLOW, MarbleColor.RED, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue5Rank1.png"))), MarbleColor.BLACK, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLUE, 3,3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue5Rank2.png"))), MarbleColor.YELLOW, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLUE, 5,5, GizmoType.UPGRADE, "upgrade1lessenergytobuildfromresearch", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue5Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.CONVERTOR, "convert1ToAny", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue6Rank1.png"))), MarbleColor.RED, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLUE, 3,3, GizmoType.BUILD, "buildfromfilepick2from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue6Rank2.png"))), GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLUE, 4,6, GizmoType.UPGRADE, "upgradeNoFiling", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue6Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLUE,1,1, GizmoType.CONVERTOR, "convert1ToAny", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue7Rank1.png"))), MarbleColor.YELLOW, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLUE,2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue7Rank2.png"))), MarbleColor.BLACK, MarbleColor.YELLOW, GizmoLevel.LEVEL2));
        level1.push(new GizmoCard(MarbleColor.BLUE, 1,1, GizmoType.UPGRADE, "e1r1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue8Rank1.png"))), GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLUE, 2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue8Rank2.png"))), MarbleColor.YELLOW, MarbleColor.RED, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.BLUE, 5,5, GizmoType.UPGRADE, "upgrade1lessenergytobuildtier2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue8Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.BLUE, 1,1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue9Rank1.png"))), GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.BLUE, 3,3, GizmoType.BUILD, "buildthisorthatget1victorypoint", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Blue9Rank2.png"))), MarbleColor.BLACK, MarbleColor.RED, GizmoLevel.LEVEL2));

        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.BUILD, "build1get1victorypoint",1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red1Rank1.png"))), MarbleColor.YELLOW, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.RED, 2, 2, GizmoType.PICK, "pickthisorthatpickrandom", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red1Rank2.png"))), MarbleColor.BLUE, MarbleColor.BLACK, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.RED, 6, 6, GizmoType.BUILD, "buildtier2pick2from6", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red1Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.BUILD, "build1pick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red2Rank1.png"))), MarbleColor.BLACK, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.RED, 3, 3, GizmoType.UPGRADE, "e2f1r2", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red2Rank2.png"))), GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.RED, 4, 4, GizmoType.CONVERTOR, "convertAnyToAny", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red2Rank3.png"))), MarbleColor.ANY, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.FILE, "filepick1from6", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red3Rank1.png"))), GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.RED, 3, 3, GizmoType.CONVERTOR, "convert1Or2ToAny", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red3Rank2.png"))), MarbleColor.BLACK, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.RED, 4, 4, GizmoType.FILE, "fileget1victorypoint", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red3Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red4Rank1.png"))), MarbleColor.BLUE, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.RED, 2, 2, GizmoType.CONVERTOR, "", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red4Rank2.png"))), MarbleColor.YELLOW, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.RED, 5, 5, GizmoType.BUILD, "buildfromfileget2victorypoints", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red4Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.PICK, "pickthispickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red5Rank1.png"))), MarbleColor.YELLOW, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.RED, 3, 3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red5Rank2.png"))), MarbleColor.BLUE, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.RED, 5, 5, GizmoType.BUILD, "buildthisorthatget2victorypoints", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red5Rank3.png"))), MarbleColor.YELLOW, MarbleColor.BLACK, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.CONVERTOR, "convert1ToAny", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red6Rank1.png"))), MarbleColor.BLUE, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.RED, 3, 3, GizmoType.BUILD, "buildFromFilePick2From6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red6Rank2.png"))), GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.RED, 7, 7, GizmoType.BUILD, "buildThisOrThatResearch", 7, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red6Rank3.png"))), MarbleColor.BLUE, MarbleColor.BLACK, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.CONVERTOR, "convert1ToAny", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red7Rank1.png"))), MarbleColor.BLACK, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.RED, 2, 2, GizmoType.BUILD, "buildThisOrThatPick1From", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red7Rank2.png"))), MarbleColor.BLUE, MarbleColor.BLACK, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.RED, 5, 5, GizmoType.UPGRADE, "upgrade1LessEnergyToBuildFromFile", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red7Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red8Rank1.png"))), GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.RED, 2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red8Rank2.png"))), MarbleColor.YELLOW, MarbleColor.BLUE, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.RED, 4, 7, GizmoType.UPGRADE, "upgradeNoFiling", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red8Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.RED, 1, 1, GizmoType.UPGRADE, "e1r1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red9Rank1.png"))), GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.RED, 3,3, GizmoType.BUILD, "buildthisorthatget1victorypoint", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Red9Rank2.png"))), MarbleColor.YELLOW, MarbleColor.BLACK, GizmoLevel.LEVEL2));

        level1.push(new GizmoCard(MarbleColor.YELLOW,1,1,GizmoType.BUILD, "buildget1victorypoint", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow1Rank1.png"))), MarbleColor.BLUE, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.YELLOW,2,2, GizmoType.PICK, "pickthisorthatpickrandom",2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow1Rank2.png"))), MarbleColor.RED, MarbleColor.BLUE, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.YELLOW,4,4, GizmoType.CONVERTOR, "convertAnyToAny", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow1Rank3.png"))), MarbleColor.ANY, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.YELLOW,4,4, GizmoType.BUILD, "build1pick1from6",2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow2Rank1.png"))), MarbleColor.RED, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 3,3, GizmoType.RESEARCH, "e2f1r2", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow2Rank2.png"))), GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 4,4, GizmoType.FILE, "filepick3random", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow2Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.YELLOW,1,1, GizmoType.FILE, "filepick1from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow3Rank1.png"))), GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 3,3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow3Rank2.png"))), MarbleColor.BLUE, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 5,5, GizmoType.BUILD, "buildthisorthatget2victorypoints", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow3Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.YELLOW, 1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow4Rank1.png"))), MarbleColor.BLACK, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 2,2, GizmoType.CONVERTOR, "convert1Or2ToAny", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow4Rank2.png"))), MarbleColor.RED, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 6,6, GizmoType.BUILD, "buildthisorthatfile", 8, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow4Rank3.png"))), MarbleColor.BLUE, MarbleColor.BLACK, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.YELLOW, 1,1, GizmoType.PICK, "pickpickrandom", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow5Rank1.png"))), MarbleColor.RED, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 3,3, GizmoType.CONVERTOR, "convert1to2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow5Rank2.png"))), MarbleColor.BLACK, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 5,5, GizmoType.BUILD, "buildthisorthatfile", 5, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow5Rank3.png"))), MarbleColor.BLACK, MarbleColor.RED, GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.YELLOW, 1,1, GizmoType.CONVERTOR, "convert1ToAny", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow6Rank1.png"))), MarbleColor.BLACK, GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.YELLOW,3,3, GizmoType.BUILD, "buildfromfilepick2from6", 2, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow6Rank2.png"))), GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 6,6, GizmoType.UPGRADE, "upgrade1lessenergytobuildfromresearch", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow6Rank3.png"))), GizmoLevel.LEVEL3));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 2,2, GizmoType.BUILD, "buildthisorthatpick1from6", 4, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow7Rank2.png"))), MarbleColor.BLUE, MarbleColor.BLACK, GizmoLevel.LEVEL2));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 4,8, GizmoType.UPGRADE, "upgradeNoResearching", 6, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow7Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.YELLOW,1,1, GizmoType.UPGRADE, "e1r1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow8Rank1.png"))), GizmoLevel.LEVEL1));
        level3.push(new GizmoCard(MarbleColor.YELLOW, 5,5, GizmoType.UPGRADE, "upgrade1lessenergytobuildtier2", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow8Rank3.png"))), GizmoLevel.LEVEL3));
        level1.push(new GizmoCard(MarbleColor.YELLOW, 1,1, GizmoType.UPGRADE, "e1f1", 1, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow9Rank1.png"))), GizmoLevel.LEVEL1));
        level2.push(new GizmoCard(MarbleColor.YELLOW, 3,3, GizmoType.BUILD, "buildthisorthatget1victorypoint", 3, ImageIO.read(Objects.requireNonNull(Deck.class.getResource("/Images/GizmoCardsTrimmed/Yellow9Rank2.png"))), MarbleColor.RED, MarbleColor.BLUE, GizmoLevel.LEVEL2));

        level3.subList(16, level3.size()).clear();
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

    public void addCardToTier1(int cardIndexTaken)
    {
        tier1[cardIndexTaken] = level1.pop();
    }

    public void addCardToTier2(int cardIndexTaken)
    {
        tier2[cardIndexTaken] = level2.pop();
    }

    public void addCardToTier3(int cardIndexTaken)
    {
        tier3[cardIndexTaken] = level3.pop();
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

    public Stack<GizmoCard> getDeck1()
    {
        return level1;
    }

    public Stack<GizmoCard> getDeck2()
    {
        return level2;
    }

    public Stack<GizmoCard> getDeck3()
    {
        return level3;
    }
}
