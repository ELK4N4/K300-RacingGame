package Client.Backend;

public class Converter {

    private int width;
    private int height;

    public Converter(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public double getAxisX(double x){
        return x - (width / 2.0);
    }

    public double getAxisY(double y){
        return  - (y - (height / 2.0));
    }

    public double getFrameX(double x) {
        return (width / 2.0) + x;
    }

    public double getFrameY(double y) {
        return (height / 2.0) - y;
    }
}
