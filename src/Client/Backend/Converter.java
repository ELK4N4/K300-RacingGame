package Client.Backend;

public class Converter {

    private int width;
    private int height;

    public Converter(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public double getX(){
        int x;
        x = 0;// change to get x from moshe
        return convertX(x);
    }

    public double getY() {
        int y;
        y = 0;// change to get y form moshe
        return  convertY(y);
    }

    private double convertX(double x)
    {
        return (double)(width/2 )+x;
    }
    private double convertY(double y)
    {
        return (double) (height/2)-y;
    }
}
