package Classes;

public class BuildFromFileEffects
{
    public BuildFromFileEffects()
    {

    }

    //method 1
    public void buildFromFileGet2VictoryPoints()
    {

    }

    //method 2
    public void buildFromFilePick2From6()
    {

    }

    public void activateEffect(int methodNum)
    {
        switch (methodNum)
        {
            case 1: buildFromFileGet2VictoryPoints(); break;
            case 2:buildFromFilePick2From6(); break;
        }
    }
}