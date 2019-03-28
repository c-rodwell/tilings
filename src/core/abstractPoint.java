package core;

import java.util.ArrayList;

//mathematical point in 2D space, not tied to pixel numbers
public class abstractPoint {
    double x;
    double y;

    //INSTANCE METHODS
    public abstractPoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstractPoint add(abstractVector vector){
        return new abstractPoint(this.x + vector.dx, this.y + vector.dy);
    }

    public abstractVector differenceFrom(abstractPoint point){
        return new abstractVector(x - point.x, y - point.y);
    }

    @Override
    public String toString() {
        return "core.abstractPoint: x = "+x+", y = "+y;
    }

    //STATIC METHODS
    public static double distance(abstractPoint p1, abstractPoint p2){
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    //return the average point of some list of points
    public static abstractPoint averagePoint(ArrayList<abstractPoint> points){
        double sum_x = 0;
        double sum_y = 0;
        double numPoints = (double) points.size();
        for (abstractPoint point: points){
            sum_x += point.x;
            sum_y += point.y;
        }
        return new abstractPoint(sum_x / numPoints, sum_y / numPoints);
    }


}
