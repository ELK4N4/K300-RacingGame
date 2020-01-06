package Client.Backend;

public class Track {

    private double bigA;
    private double bigB;
    private double bigC;
    private double smallA;
    private double smallB;
    private double smallC;
    private int width;
    private int height;

    public Track(double bigA, double bigB,double smallA, double smallB, int width, int height) {
        Converter converter = new Converter(width, height);
        this.width = width;
        this.height = height;
        this.bigA = converter.getAxisX(this.bigA);
        System.out.println(this.bigA);
        this.bigB = converter.getAxisY(this.bigB);
        this.bigC = getC(this.bigA, this.bigB);
        this.smallA = converter.getAxisX(this.smallA);
        this.smallB = converter.getAxisY(this.smallB);
        this.smallC = getC(this.smallA, this.smallB);
    }

    private double getC(double a, double b){
        return Math.sqrt((a * a) - (b * b));
    }

    private double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    public boolean onTheEllipse(double x, double y){
        double distanceBig1 = distance(this.smallC, 0, x, y);
        double distanceBig2 = distance(-this.smallC, 0, x, y);
        double distanceSmall1 = distance(this.bigC, 0, x, y);
        double distanceSmall2 = distance(-this.bigC, 0, x, y);
        System.out.println(distanceBig1 + distanceBig2);
        System.out.println(bigA);
        System.out.println(distanceSmall1 + distanceSmall2);
        System.out.println(smallA);
        return (distanceBig1 + distanceBig2 < 2 * this.bigA) && (distanceSmall1 + distanceSmall2 > 2 * smallA);
    }
}
