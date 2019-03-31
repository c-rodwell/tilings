package core;

import java.util.ArrayList;
import java.util.Arrays;

public class abstractPolygon {
    //Polygon in an abstract, mathematical coordinate system, not tied to pixel coordinates
    //should be similar to a java.awt.Polygon since it will be drawn as a Polygon
    private int npoints;
//    private ArrayList<Double> xpoints;
//    private ArrayList<Double> ypoints;
    private ArrayList<abstractPoint> points;
    protected abstractPoint center;
    //private ArrayList<Edge> edges;


    public abstractPolygon(){
        npoints = 0;
//        xpoints = new ArrayList<>();
//        ypoints = new ArrayList<>();
        points = new ArrayList<>();
        center = null;
    }

    public abstractPolygon(ArrayList<abstractPoint> points, abstractPoint center){
        this.points = points;
        this.center = center;
        this.npoints = points.size();
    }

    public abstractPolygon(ArrayList<abstractPoint> points){
        this.points = points;
        this.center = abstractPoint.averagePoint(points);
        this.npoints = points.size();
    }

    @Override
    public String toString() {
        String outString = "abstractPolygon: npoints = "+npoints+", center = "+center+", points = ";
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

    public abstractPoint getCenter() {
        if (center == null){
            center = abstractPoint.averagePoint(points);
        }
        return center;
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
        //p.npoints = degree;
        p.center = new abstractPoint(center_x, center_y);
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
        double radius = edge.edgeLength / (2*Math.sin(center_angle/2.0));
        double rotation = edge.getEnd1().differenceFrom(center).angle();
        return regPolyOnCircle(degree, radius, center.x, center.y, rotation );
    }

    //isoceles triangle from the two base points and the opposite angle
    //will make clockwise point order [p1, p2, p3] if (p1, p2) is the base

//    public abstractPoint[] bisectingPointsAtAngle(double angle){
//
//        abstractPoint midPoint = midPoint();
//        double perpDistance = edgeLength / (2*Math.tan(angle / 2.0));
//        abstractVector normalVector = unitNormalVector();
//        abstractPoint perp1 = midPoint.add(normalVector.scale(-perpDistance));
//        abstractPoint perp2 = midPoint.add(normalVector.scale(perpDistance));
//        abstractPoint[] points = {perp1, perp2};
//        return points;
//    }

    //this has a lot of overlap with rhombusOnOppositePoints -
    public static abstractPolygon isocelesOnBase(abstractPoint p0, abstractPoint p1, double topangle){
        abstractPoint midPoint = abstractPoint.weightedAvg(p0, p1, 1, 1);
        double perpDistance = abstractPoint.distance(p0, p1) / (2*Math.tan(topangle / 2.0));
        abstractVector normalVector = p1.differenceFrom(p0).normalVector();
        abstractPoint p2 = midPoint.add(normalVector.scale(-perpDistance));

        ArrayList<abstractPoint> points = new ArrayList(Arrays.asList(p0, p1, p2));
        return new abstractPolygon(points);
    }

    //rhombus from the two opposite points and
    public static abstractPolygon rhombusOnOppositePoints(abstractPoint p0, abstractPoint p2, double otherangle){
        abstractPoint midPoint = abstractPoint.weightedAvg(p0, p2, 1, 1);
        double perpDistance = abstractPoint.distance(p0, p2) / (2*Math.tan(otherangle / 2.0));
        abstractVector normalVector = p2.differenceFrom(p0).normalVector();
        abstractPoint p1 = midPoint.add(normalVector.scale(-perpDistance));
        abstractPoint p3 = midPoint.add(normalVector.scale(perpDistance));

        ArrayList<abstractPoint> points = new ArrayList(Arrays.asList(p0, p1, p2, p3));
        return new abstractPolygon(points);
    }

    //rhombus from two adjacent points p0 and p1. theta is the angle at p0
    public static abstractPolygon rhombusOnAdjacentPoints(abstractPoint p0, abstractPoint p1, double theta){
        abstractVector v1 = p1.differenceFrom(p0);
        abstractVector v2 = v1.rotate(theta);
        abstractPoint p3 = p0.add(v2);
        abstractPoint p2 = p1.add(v2);

        ArrayList<abstractPoint> points = new ArrayList(Arrays.asList(p0, p1, p2, p3));
        return new abstractPolygon(points);
    }

    //parallelogram which has the 3 points as corners
    public static abstractPolygon parallelogramFromThreePoints(abstractPoint p0, abstractPoint p1, abstractPoint p3){
        abstractVector v1 = p1.differenceFrom(p0);
        //abstractVector v2 = p3.differenceFrom(p0);
        abstractPoint p2 = p3.add(v1); //or p1.add(v2)

        ArrayList<abstractPoint> points = new ArrayList(Arrays.asList(p0, p1, p2, p3));
        return new abstractPolygon(points);
    }

}
