package Classes;

public class UpgradeCardEffects
{
    public UpgradeCardEffects()
    {

    }

    //method 1
    public void upgrade(int energyRingSize, int archiveSize, int researchSize)
    {
        //addGizmos
    }

    //method 2
    public void upgrade1LessEnergyToBuildFromFile()
    {

    }
    //method 3
    public void upgrade1LessEnergyToBuildTier2()
    {

    }

    //method 4
    public void upgrade1LessEnergyToBuildFromResearch()
    {

    }
    //method 5
    public void upgradeNoFiling()
    {

    }

    //method 6
    public void upgradeNoResearching()
    {

    }
    //method 7
    public void upgradeEqualToNumOfMarbles()
    {

    }

    //method 8
    public void upgradeEqualToNumOfBonusVictoryPts()
    {

    }

    public void activateEffect(int methodNum, int energyRingSize, int archiveSize, int researchSize)
    {
        switch (methodNum)
        {
            case 1: upgrade(energyRingSize, archiveSize, researchSize); break;
            case 2: upgrade1LessEnergyToBuildFromFile(); break;
            case 3: upgrade1LessEnergyToBuildTier2(); break;
            case 4: upgrade1LessEnergyToBuildFromResearch(); break;
            case 5: upgradeNoFiling(); break;
            case 6: upgradeNoResearching(); break;
            case 7: upgradeEqualToNumOfMarbles(); break;
            case 8: upgradeEqualToNumOfBonusVictoryPts(); break;
    }
    }

}
