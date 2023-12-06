package Classes;
import java.sql.Array;
import java.util.ArrayList;
public class EnergyRing
{
    private ArrayList<Marble> energyRing;
    private int maxEnergy;
    private int numOfRed, numOfBlue, numOfBlack, numOfYellow;

    public EnergyRing()
    {
        energyRing = new ArrayList<Marble>();
        numOfRed = 0;
        numOfBlack = 0;
        numOfBlue = 0;
        numOfYellow = 0;
        maxEnergy = 5;
    }

    public ArrayList<Marble> getRing()
    {
        return energyRing;
    }

    /**
     * returns the limit of the player's ring
     */
    public int getEnergyRingMax()
    {
        return maxEnergy;
    }

    public void updateEnergyRingMax(int x)
    {
        maxEnergy += x;
    }

    public int getNumOfRed()
    {
        int count = 0;
        for (Marble m: energyRing)
            if (m.getOldColor() == MarbleColor.RED)
                count++;
        return count;
    }

    public int getNumOfBlue()
    {
        int count = 0;
        for (Marble m: energyRing)
            if (m.getOldColor() == MarbleColor.BLUE)
                count++;
        return count;
    }

    public int getNumOfBlack()
    {
        int count = 0;
        for (Marble m: energyRing)
            if (m.getOldColor() == MarbleColor.BLACK)
                count++;
        return count;
    }

    public int getNumOfYellow()
    {
        int count = 0;
        for (Marble m: energyRing)
            if (m.getOldColor() == MarbleColor.YELLOW)
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

    public void setEnergyRing(int x)
    {
        maxEnergy = x;
    }

}
