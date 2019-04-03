package core;

import jdk.jshell.spi.ExecutionControl;

import java.awt.*;
import java.util.ArrayList;

//wrapper around abstractPolygon which has the division behavior
public class Face {
    protected abstractPolygon polygon;
    protected ArrayList<Edge> edges;
    //ArrayList<Face> neighbors; do we want list of neighbors, or just reference through edges?
    protected ArrayList<Face> component_faces;
    protected ArrayList<Edge> component_edges;

    protected ArrayList<abstractPoint>[] edgePoints;
    protected ArrayList<Edge>[] edgeEdges;

    protected int depth;
    //protected abstractPoint center;

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

        edgePoints = new ArrayList[getNpoints()];
        edgeEdges = new ArrayList[getNpoints()];
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

    public abstractPoint getCenter(){
        return polygon.getCenter();
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Edge getEdge(int i){
        return edges.get(i);
    }

    public ArrayList<Edge> makeEdges(){
        ArrayList<Edge> edges = new ArrayList<>();
        int npoints = getNpoints();
        for (int i = 0; i<npoints; i ++){
            abstractPoint point1 = getPoint(i);
            abstractPoint point2 = getPoint((i+1) % npoints);
            //Face neighbor = neighbors.get(i);
            //Edge edge = new Edge(this, neighbor, point1, point2);
            //Edge edge = new Edge(this, null, point1, point2);
            edges.add(makeEdge(this, null, point1, point2));
        }
        this.edges = edges;
        return edges;
    }

    //subclasses can override this to make an Edge subclass instead
    public Edge makeEdge(Face side1, Face side2, abstractPoint point1, abstractPoint point2){
        return new Edge(side1, side2, point1, point2);
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

    public ArrayList<abstractPoint>[] getEdgePoints() {
        return edgePoints;
    }

    public ArrayList<Edge>[] getEdgeEdges() {
        return edgeEdges;
    }

    public void addFace(Face face){
        face.setDepth(depth+1);
        component_faces.add(face);
    }

    //for recurrences like golden triangles where depth step is variable
    public void addFace(Face face, int new_depth){
        face.setDepth(new_depth);
        component_faces.add(face);
    }

    //set split information corresponding to the edge
    public void setEdgeSplit(Edge edge, ArrayList<abstractPoint> splitPoints, ArrayList<Edge> splitEdges){
        int index = edges.indexOf(edge);
        if ((index < 0) || (index >= edges.size())){
            throw new RuntimeException("edge was not in the edges list for that face");
        }
        //edgePoints[index] = (abstractPoint[]) splitPoints.toArray();
        //abstractPoint[] points = (abstractPoint[]) splitPoints.toArray();
        edgePoints[index] = splitPoints;
        edgeEdges[index] = splitEdges;
    }



    public Color getColor(){
        return null;
    }




}
