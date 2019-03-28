package core;

import java.util.ArrayList;

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
        double rotation = edge.getEnd1().differenceFrom(center).toAngle();
        return regPolyOnCircle(degree, radius, center.x, center.y, rotation );
    }

}
