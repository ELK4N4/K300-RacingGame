package Client.Backend;

public class Converter {
    public double convertX(double x,int width , int height)
    {
        return (double)(width/2 )+x;


    }
    public double convertY(double y,int width ,int height)
    {
        return (double) (height/2)-y;
    }
}
