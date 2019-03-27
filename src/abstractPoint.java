//mathematical point in 2D space, not tied to pixel numbers
public class abstractPoint {
    double x;
    double y;

    public abstractPoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static double distance(abstractPoint p1, abstractPoint p2){
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public abstractPoint add(abstractVector vector){
        return new abstractPoint(this.x + vector.dx, this.y + vector.dy);
    }

    public abstractVector differenceFrom(abstractPoint point){
        return new abstractVector(x - point.x, y - point.y);
    }

    @Override
    public String toString() {
        return "abstractPoint: x = "+x+", y = "+y;
    }


}
