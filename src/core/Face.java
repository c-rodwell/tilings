package core;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;

//wrapper around abstractPolygon which has the division behavior
public class Face {
    protected abstractPolygon polygon;
    protected ArrayList<Edge> edges;
    //ArrayList<Face> neighbors; do we want list of neighbors, or just reference through edges?
    protected ArrayList<Face> component_faces;
    protected ArrayList<Edge> component_edges;
    protected int depth;

    //default constructor  so subclass constructors don't complain
    public Face(){
        //System.out.println("Face constructor with no params");
    }

    public Face(abstractPolygon polygon){
        //System.out.println("Face constructor with polygon = "+polygon);
        //this.polygon = polygon;
        setPolygon(polygon);
        edges = null; //create the edges here?
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
        return "Face: polygon = "+polygon;
    }

    public static Face regularPolygonFace(int degree, double radius, double center_x, double center_y, double rotation){
        //abstractPolygon.regularPolygon(degree, radius, center_x, center_y, rotation);
        return new Face(abstractPolygon.regPolyOnCircle(degree, radius, center_x, center_y, rotation));
    }

    public int getNpoints(){
        return polygon.getNpoints();
    }

    public abstractPoint getPoint(int i){
        return polygon.getPoint(i);
    }

    public abstractPoint getcenter(){
        return polygon.getCenter();
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
            //Face neighbor = neighbors.get(i);
            //Edge edge = new Edge(this, neighbor, point1, point2);
            Edge edge = new Edge(this, null, point1, point2);
            edges.add(edge);
        }
        this.edges = edges;
        return edges;
    }

    public void split_all_edges(){
        if (edges == null){
            makeEdges();
        }
        for (Edge edge : getEdges()){
            split_edge(edge);
        }
    }

    //leave these blank - if not implemented in subclass, does nothing
    public void split_edge(Edge edge){
    }

    public void split_inside(){
    }

    public ArrayList<Face> getComponentFaces(){
        return component_faces;
    }

    public ArrayList<Edge> getComponentEdges(){
        return component_edges;
    }

    public void addFace(Face face){
        face.setDepth(depth+1);
        component_faces.add(face);
    }

    public void addFace(Face face, int new_depth){
        face.setDepth(new_depth);
        component_faces.add(face);
    }




}
