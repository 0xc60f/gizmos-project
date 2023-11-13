package Classes;

public class ConvertCardEffects
{
    public ConvertCardEffects()
    {

    }

    //method 1
    public void convert1ToAny()
    {

    }

    //method 2
    public void convert1Or2ToAny()
    {

    }

    //method 3
    public void convert1To2()
    {

    }

    //method 4
    public void convertAnyToAny()
    {

    }

    //method 5
    public void convert1Or2To2()
    {

    }

    public void activateEffect(int methodNum)
    {
        switch (methodNum)
        {
            case 1: convert1ToAny(); break;
            case 2: convert1Or2ToAny(); break;
            case 3: convert1To2(); break;
            case 4: convertAnyToAny(); break;
            case 5: convert1Or2To2(); break;
        }
    }

}
