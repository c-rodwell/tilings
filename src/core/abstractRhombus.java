package core;

public class abstractRhombus extends abstractPolygon {

    //is there any point making a subclass with no new functionality just so we can label a polygon as a rhombus?

    //make a rhombus from the two opposite points, and the angle at the two missing points
    public static abstractRhombus rhombusOnDiag(abstractPoint p0, abstractPoint p2,  double otherAngle){
        //return new Edge(null, null, p0, p2).rhombusOnEdge(otherAngle);
        return new abstractRhombus();
    }

}
