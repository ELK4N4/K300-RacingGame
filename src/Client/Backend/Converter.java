package Client.Backend;

public class Converter {

    private int width;
    private int height;

    public Converter(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private double getAxisX(double x){
        return (double) x - (width/2);
    }

    private double getAxisY(double y){
        return (double) -(y - (height/2));
    }

    private double getFrameX(double x)
    {
        return (double)(width/2 )+x;
    }
    private double getFrameY(double y)
    {
        return (double) (height/2)-y;
    }
}
