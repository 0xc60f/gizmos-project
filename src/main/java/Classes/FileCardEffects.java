package Classes;

public class FileCardEffects
{
    public FileCardEffects()
    {

    }

    //method 1
    public void fileGet1VictoryPoint()
    {

    }

    //method 2
    public void filePick1From6()
    {

    }

    //method 3
    public void filePick3Random()
    {

    }

    public void activateEffect(int methodNum)
    {
        switch (methodNum)
        {
            case 1: fileGet1VictoryPoint(); break;
            case 2: filePick1From6(); break;
            case 3: filePick3Random(); break;
        }
    }

}
