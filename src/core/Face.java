package core;

import display.GraphicsPanel;
import jdk.jshell.spi.ExecutionControl;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

//wrapper around abstractPolygon which has the division behavior
public class Face implements Fractal{
    protected abstractPolygon polygon;
    //protected ArrayList<Edge> edges;
    protected Edge[] edges;
    //ArrayList<Face> neighbors; do we want list of neighbors, or just reference through edges?
    protected ArrayList<Face> component_faces;
    protected ArrayList<Edge> component_edges;

    protected ArrayList<abstractPoint>[] edgePoints;
    protected ArrayList<Edge>[] edgeEdges;

    protected int depth;
    //protected abstractPoint center;
    protected boolean wasSplit;

    //default constructor  so subclass constructors don't complain
    public Face(){
        //System.out.println("Face constructor with no params");
    }

    public Face(abstractPolygon polygon){
        //System.out.println("Face constructor with polygon = "+polygon);
        //this.polygon = polygon;
        int npoints = polygon.getNpoints();
        setPolygon(polygon);
        //edges = null;
        edges = new Edge[npoints];

        component_faces = new ArrayList<>();
        component_edges = new ArrayList<>();

        edgePoints = new ArrayList[npoints];
        edgeEdges = new ArrayList[npoints];
        wasSplit = false;
    }

    //public Face()

//    public Face(abstractPolygon polygon, ArrayList<Edge> edges){
//        this.polygon = polygon;
//        this.edges = edges;
//    }

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

    public Edge[] getEdges() {
        return edges;
    }

    public Edge getEdge(int i){
        return edges[i];
    }

//    public ArrayList<Edge> makeEdges(){
//        ArrayList<Edge> edges = new ArrayList<>();
//        int npoints = getNpoints();
//        for (int i = 0; i<npoints; i ++){
//            abstractPoint point1 = getPoint(i);
//            abstractPoint point2 = getPoint((i+1) % npoints);
//            //Face neighbor = neighbors.get(i);
//            //Edge edge = new Edge(this, neighbor, point1, point2);
//            //Edge edge = new Edge(this, null, point1, point2);
//            edges.add(makeEdge(this, null, point1, point2));
//        }
//        this.edges = edges;
//        return edges;
//    }

    //create all edges which were not already created (null if not created)
    public void makeEdges(){
        int npoints = getNpoints();
        for (int i = 0; i<npoints; i ++){
            if (edges[i] == null){
                abstractPoint point1 = getPoint(i);
                abstractPoint point2 = getPoint((i+1) % npoints);
                edges[i] = makeEdge(this, null, point1, point2);
            }
        }
    }

    //subclasses can override this to make an Edge subclass instead
    public Edge makeEdge(Face leftside, Face rightside, abstractPoint point1, abstractPoint point2){
        return new Edge(leftside, rightside, point1, point2);
    }


    public void split_all_edges(){
//        if (edges == null){
//            makeEdges();
//        }
        for (Edge edge : getEdges()){
            edge.split();
        }
    }

    public void split(){
        //implement in subclasses
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

    public void addEdge(Edge edge){
        edge.setDepth(depth+1);
        component_edges.add(edge);
    }

    //for recurrences like golden triangles where depth step is variable
    public void addEdge(Edge edge, int new_depth){
        edge.setDepth(new_depth);
        component_edges.add(edge);
    }

    //set split information corresponding to the edge
    public void setEdgeSplit(Edge edge, ArrayList<abstractPoint> splitPoints, ArrayList<Edge> splitEdges){
        //int index = edges.indexOf(edge);
        int index = Arrays.asList(edges).indexOf(edge);

        if ((index < 0) || (index >= edges.length)){
            throw new RuntimeException("edge was not in the edges list for that face");
        }
        //edgePoints[index] = (abstractPoint[]) splitPoints.toArray();
        //abstractPoint[] points = (abstractPoint[]) splitPoints.toArray();
        edgePoints[index] = splitPoints;
        edgeEdges[index] = splitEdges;
    }

    //override this in subclasses to set color of the face type. null will draw as random color
    public Color getColor(){
        return null;
    }

    public HashMap getParts(){
        HashMap<String, ArrayList> parts = new HashMap();
        parts.put("Edges", component_edges);
        parts.put("Faces", component_faces);
        return parts;
    }

    public void draw(Graphics g, GraphicsPanel gp){
        int npoints = polygon.getNpoints();
        int[] xpoints = new int[npoints];
        int[] ypoints = new int[npoints];

        for(int i=0; i<npoints; i++){
            abstractPoint point = polygon.getPoint(i);
            int[] new_point = gp.mapCoords(point);
            xpoints[i] = new_point[0];
            ypoints[i] = new_point[1];
        }
        Color color = getColor();
        if (color == null){
            Random r = new Random();
            color = Color.getHSBColor(r.nextFloat(), r.nextFloat(), r.nextFloat());
        }
        g.setColor(color);
        Polygon P = new Polygon(xpoints, ypoints, npoints);
        g.fillPolygon(P);
    }


}
