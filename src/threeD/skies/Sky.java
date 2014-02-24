package threeD.skies;

import threeD.Vect;
import threeD.Color;

public interface Sky
{
    public Color getColor(Vect direction);
    public Vect [] getLight();
}
