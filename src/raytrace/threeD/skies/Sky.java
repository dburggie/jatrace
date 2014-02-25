package raytrace.threeD.skies;

import raytrace.threeD.Vect;
import raytrace.threeD.Color;

public interface Sky
{
    public Color getColor(Vect direction);
    public Vect [] getLight();
}
