import java.awt.geom.Point2D;
import java.util.ArrayList;

//wrapper around abstractPolygon which has the division behavior
public class Face {
    abstractPolygon polygon;
    ArrayList<Edge> edges;
    ArrayList<Face> neighbors;
    ArrayList<Face> component_faces;
    ArrayList<Edge> component_edges;

    //default constructor  so subclass constructors don't complain
    public Face(){

    }

    public Face(abstractPolygon polygon){
        this.polygon = polygon;
        edges = new ArrayList<>();
        //create the edges?
    }

    public Face(abstractPolygon polygon, ArrayList<Edge> edges){
        this.polygon = polygon;
        this.edges = edges;
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

    public ArrayList<Edge> makeEdges(){
        ArrayList<Edge> edges = new ArrayList<>();
        int npoints = getNpoints();
        for (int i = 0; i<npoints; i = (i+1) % npoints){
            abstractPoint point1 = getPoint(i);
            abstractPoint point2 = getPoint((i+1) % npoints);
            Face neighbor = neighbors.get(i);
            Edge edge = new Edge(this, neighbor, point1, point2);
            edges.add(edge);
        }
        this.edges = edges;
        return edges;
    }

    public void split_edges(){

    }

    public void split_inside(){

    }

}
