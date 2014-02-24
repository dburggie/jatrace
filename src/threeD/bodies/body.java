package threeD.bodies;

import threeD.Ray;
import threeD.Color;
import threeD.Vect;

public interface body
{
    public Color getColor(Vect point);
    public Vect getNormal(Vect point);
    public double getReflectivity(Vect point);
    public double getOpacity();
    public double getIndex();
    public double intersection(Ray ray);
}
