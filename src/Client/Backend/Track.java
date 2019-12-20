package Client.Backend;

public class Track {
    private int bigA;
    private int bigB;
    private int bigC;
    private int smallA;
    private int smallB;
    private int smallC;
    private int width;
    private int height;

    public Track(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public boolean onTheEllipse(int x, int y){
        double distance1 = distance(c, 0, x, y);
        double distance2 = distance(-c, 0, x, y);
        if((distance1 + distance2 < 2*a) && ) {
            return true;
        }
        return false;
    }
}
