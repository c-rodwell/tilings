import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class abstractPolygon {
    //Polygon in an abstract, mathematical coordinate system, not tied to pixel coordinates
    //should be similar to a java.awt.Polygon since it will be drawn as a Polygon
    private int npoints;
//    private ArrayList<Double> xpoints;
//    private ArrayList<Double> ypoints;
    private ArrayList<abstractPoint> points;
    //private ArrayList<Edge> edges;


    public abstractPolygon(){
        npoints = 0;
//        xpoints = new ArrayList<>();
//        ypoints = new ArrayList<>();
        points = new ArrayList<>();
    }

    @Override
    public String toString() {
        String outString = "abstractPolygon: npoints = "+npoints+", points = ";
        for(int i=0; i<npoints; i++){
            outString += "<"+ getPoint(i)+">";
        }
        return outString;
    }

    public void addPoint(double x, double y){
        npoints += 1;
        points.add(new abstractPoint(x, y));
    }

    public void addPoint(abstractPoint p){
        npoints += 1;
        points.add(p);
    }

    public abstractPoint getPoint(int i){
        return points.get(i);
    }

//    public void addPoint(double x, double y){
//        npoints += 1;
//        xpoints.add(x);
//        ypoints.add(y);
//    }
//
//    public ArrayList<Double> getXpoints(){
//        return (ArrayList<Double>) xpoints.clone();
//    }
//
//    public ArrayList<Double> getYpoints(){
//        return (ArrayList<Double>) ypoints.clone();
//    }

//    public Double[] getYpoints(){
//        return (Double[]) ypoints.toArray();
//    }

    public int getNpoints(){
        return npoints;
    }

    public static abstractPolygon regPolyOnCircle(int degree, double radius, double center_x, double center_y, double rotation){
        abstractPolygon p = new abstractPolygon();
        //point on a circle: x = r*cos(theta) , y = r*sin(theta)
        for (int i=0; i<degree; i++){
            double theta = (2*Math.PI *((float)i / (float)degree)) + rotation;
            //System.out.println("theta = "+theta);
            double x = ( radius * Math.cos(theta) ) + center_x;
            double y = ( radius * Math.sin(theta) ) + center_y;
            p.addPoint( x, y);
            //System.out.println("adding point: "+x+", "+y);
        }
        return p;
    }

    //build a regular polygon from 2 of its points
//    //TODO - should this take an Edge object instead of 2 points?
//    public static abstractPolygon regPolyOnEdge(int degree, abstractPoint p1, abstractPoint p2){
//        System.out.println("regPolyOnEdge: degree = "+degree+", p1 = "+p1+", p2 = "+p2);
//        abstractPolygon p = new abstractPolygon();
//        p.addPoint(p1);
//        p.addPoint(p2);
//
//        double side = abstractPoint.distance(p1, p2);
//        double inside_angle = Math.PI * (1.0 - (2.0 / degree));
//        System.out.println("side length = "+side);
//        System.out.println("inside_angle = "+inside_angle);
//
//        abstractPoint thisPoint = p2;
//        abstractPoint prevPoint = p1;
//
//        for (int i=3; i<= degree; i++){
//
//            System.out.println("\ncomputing point "+i);
//            System.out.println("prevPoint = "+prevPoint+", thisPoint = "+thisPoint);
//            double theta_cos = Math.acos((prevPoint.x - thisPoint.x) / side);
//            double theta_sin = Math.asin((prevPoint.y - thisPoint.y) / side); //I think these should be the same
//            double theta_tan = Math.atan((prevPoint.y - thisPoint.y)/(prevPoint.x - thisPoint.x));
//
//            System.out.println("angle by cosine: "+theta_cos+", angle by sine: "+theta_sin);
//
//            double x = thisPoint.x + side*Math.cos(inside_angle+theta_tan);
//            double y = thisPoint.y + side*Math.sin(inside_angle+theta_tan);
//
//            prevPoint = thisPoint;
//            thisPoint = new abstractPoint(x,y);
//            p.addPoint(thisPoint);
//            System.out.println("new point: "+thisPoint);
//        }
//
//
//        //point on a circle: x = r*cos(theta) , y = r*sin(theta)
////        for (int i=0; i<degree; i++){
////            double theta = (2*Math.PI *((float)i / (float)degree)) + rotation;
////            //System.out.println("theta = "+theta);
////            double x = ( radius * Math.cos(theta) ) + center_x;
////            double y = ( radius * Math.sin(theta) ) + center_y;
////            p.addPoint( x, y);
////            //System.out.println("adding point: "+x+", "+y);
////        }
//
//        return p;
//    }

    //try it using a different method
    public static abstractPolygon regPolyOnEdge(int degree, Edge edge, boolean chooseFirst){
        abstractPolygon aP = new abstractPolygon();
        double center_angle = 2*Math.PI/degree;
        abstractPoint[] possible_centers = edge.bisectingPointsAtAngle(center_angle);
        abstractPoint center;
        if (chooseFirst){
            center = possible_centers[0];
        } else{
            center = possible_centers[1];
        }
        double radius = edge.length / (2*Math.sin(center_angle/2.0));
        double rotation = edge.end1.differenceFrom(center).toAngle();
        return regPolyOnCircle(degree, radius, center.x, center.y, rotation );
    }

}
