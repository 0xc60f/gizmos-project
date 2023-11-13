package Classes;

public class BuildEffects
{

    public BuildEffects()
    {

    }

    //method 1
    public void buildGet1VictoryPoint()
    {

    }

    //method 2
    public void buildPick1From6()
    {

    }

    //method 3
    public void buildThisOrThatGet1VictoryPoint()
    {

    }
    //method 4
    public void buildThisOrThatPick1From6()
    {

    }

    //method 5
    public void buildThisOrThatFile()
    {

    }
    //method 6
    public void buildThisOrThatGet2VictoryPoints()
    {

    }

    //method 7
    public void buildThisOrThatResearch()
    {

    }
    //method 8
    public void buildThisOrThatBuildTier1For0()
    {

    }

    //method 9
    public void buildTier2Pick2From6()
    {

    }

    public void activateEffect(int methodNum)
    {
        switch (methodNum)
        {
            case 1: buildGet1VictoryPoint(); break;
            case 2: buildPick1From6(); break;
            case 3: buildThisOrThatGet1VictoryPoint(); break;
            case 4: buildThisOrThatPick1From6(); break;
            case 5: buildThisOrThatFile(); break;
            case 6: buildThisOrThatGet2VictoryPoints(); break;
            case 7: buildThisOrThatResearch(); break;
            case 8: buildThisOrThatBuildTier1For0(); break;
            case 9: buildTier2Pick2From6(); break;
        }
    }

}
