package Classes;

public class PickCardEffects
{
    public PickCardEffects()
    {

    }

    //method 1
    public void pickPickRandom()
    {

    }

    //method 2
    public void pickThisOrThatPickRandom()
    {

    }

    public void activateEffect(int methodNum)
    {
        switch (methodNum)
        {
            case 1: pickPickRandom(); break;
            case 2: pickThisOrThatPickRandom(); break;

        }
    }
}
