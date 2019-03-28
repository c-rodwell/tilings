package core;

import java.util.ArrayList;

//wrapper around core.abstractPolygon which has the division behavior
public class Face {
    protected abstractPolygon polygon;
    protected ArrayList<Edge> edges;
    //ArrayList<core.Face> neighbors; do we want list of neighbors, or just reference through edges?
    protected ArrayList<Face> component_faces;
    protected ArrayList<Edge> component_edges;
    protected int depth;

    //default constructor  so subclass constructors don't complain
    public Face(){
        System.out.println("core.Face constructor with no params");
    }


    public Face(abstractPolygon polygon){
        System.out.println("core.Face constructor with polygon = "+polygon);
        //this.polygon = polygon;
        setPolygon(polygon);
        edges = new ArrayList<>();
        //create the edges?
        component_faces = new ArrayList<>();
        component_edges = new ArrayList<>();
    }

    public Face(abstractPolygon polygon, ArrayList<Edge> edges){
        this.polygon = polygon;
        this.edges = edges;
    }

    public void setPolygon(abstractPolygon polygon){
        this.polygon = polygon;
    }

    public abstractPolygon getPolygon(){
        return polygon;
    }

    public void setDepth(int depth){
        this.depth = depth;
    }

    public int getDepth(){
        return depth;
    }

    @Override
    public String toString() {
        return "core.Face: polygon = "+polygon;
    }

    public static Face regularPolygonFace(int degree, double radius, double center_x, double center_y, double rotation){
        //core.abstractPolygon.regularPolygon(degree, radius, center_x, center_y, rotation);
        return new Face(abstractPolygon.regPolyOnCircle(degree, radius, center_x, center_y, rotation));
    }

    public int getNpoints(){
        return polygon.getNpoints();
    }

    public abstractPoint getPoint(int i){
        return polygon.getPoint(i);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<Edge> makeEdges(){
        ArrayList<Edge> edges = new ArrayList<>();
        int npoints = getNpoints();
        for (int i = 0; i<npoints; i ++){
            abstractPoint point1 = getPoint(i);
            abstractPoint point2 = getPoint((i+1) % npoints);
            //core.Face neighbor = neighbors.get(i);
            //core.Edge edge = new core.Edge(this, neighbor, point1, point2);
            Edge edge = new Edge(this, null, point1, point2);
            edges.add(edge);
        }
        this.edges = edges;
        return edges;
    }

    public void split_edges(){

    }

    public void split_inside(){

    }

    public ArrayList<Face> getComponentFaces(){
        return component_faces;
    }

    public ArrayList<Edge> getComponentEdges(){
        return component_edges;
    }




}
