package jatrace.threeD.skies;

import jatrace.threeD.Vect;
import jatrace.threeD.Color;

public interface Sky
{
    public Color getColor(Vect direction);
    public Vect [] getLight();
}
