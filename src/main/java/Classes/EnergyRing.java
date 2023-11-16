package Classes;
import java.sql.Array;
import java.util.ArrayList;
public class EnergyRing
{
    private ArrayList<Marble> energyRing;
    private int numOfRed, numOfBlue, numOfBlack, numOfYellow;

    public EnergyRing()
    {
        energyRing = new ArrayList<Marble>();
        numOfRed = 0;
        numOfBlack = 0;
        numOfBlue = 0;
        numOfYellow = 0;
    }

    public ArrayList<Marble> getRing()
    {
        return energyRing;
    }

    public int getNumOfRed()
    {
        int count = 0;
        for (Marble m: energyRing)
            if (m.getColor() == MarbleColor.RED)
                count++;
        return count;
    }

    public int getNumOfBlue()
    {
        int count = 0;
        for (Marble m: energyRing)
            if (m.getColor() == MarbleColor.BLUE)
                count++;
        return count;
    }

    public int getNumOfBlack()
    {
        int count = 0;
        for (Marble m: energyRing)
            if (m.getColor() == MarbleColor.BLACK)
                count++;
        return count;
    }

    public int getNumOfYellow()
    {
        int count = 0;
        for (Marble m: energyRing)
            if (m.getColor() == MarbleColor.YELLOW)
                count++;
        return count;
    }

    public void addMarble(Marble m)
    {
        energyRing.add(m);
    }

    public void removeMarble(Marble m)
    {
        energyRing.remove(m);
    }


}
