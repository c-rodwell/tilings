package patterns.PentagonAndStar;

import core.*;

public class PSEdge extends core.Edge {

    public PSEdge(Face side1, Face side2, abstractPoint end1, abstractPoint end2){
        super(side1, side2, end1, end2);
    }

    @Override
    public String toString() {
        return "Pentagon and Star "+super.toString();
    }

    public void split(){
        double phi = (1.0 +Math.sqrt(5.0))/2.0;
        double[] proportions = {phi, 1.0, phi};
        Edge[] pieces = splitByRatio(proportions);

        Edge edge1 = pieces[0];
        splitEdgesRight.add(edge1);
        splitEdgesLeft.add(edge1);
        splitPointsRight.add(end1);
        splitPointsLeft.add(end1);

        //cases for middle part:
            //two pentagons - make a Rhombus
            //pentagon and other shape - no rhombus, other shape gets the sticking out part
            //two other shapes - should not happen - could do arbitrary thing or throw an error
            //null and other shape - still give other shape the sticking out part, no rhombus
            //pentagon and null - could make the rhombus, or ok not to.
            //two null - should not happen
        //so, can treat as two cases: (null/pentagon) vs other shape

        boolean rightSticksOut = ((rightSide != null) && (rightSide.getClass() != Pentagon.class));
        boolean leftSticksOut = ((rightSide != null) && (rightSide.getClass() != Pentagon.class));

        abstractPoint[] bothPoints = pieces[1].bisectingPointsAtAngle(Math.PI/5.0);
        abstractPoint leftPoint = bothPoints[0];
        abstractPoint rightPoint = bothPoints[1];

        if (rightSticksOut && leftSticksOut){
            throw new RuntimeException("Illegal pattern: two non-pentagon shapes touching");
        } else if (rightSticksOut){

        } else if (leftSticksOut){

        } else {
            Rhombus edgeRhombus = Rhombus.fromLongDiag(leftPoint, rightPoint);
            splitFaces.add(edgeRhombus);
            edgeRhombus.makeEdges();

            splitEdgesRight.add(edgeRhombus.getEdges().get(1));
            splitEdgesRight.add(edgeRhombus.getEdges().get(2));
            splitEdgesLeft.add(edgeRhombus.getEdges().get(3));
            splitEdgesLeft.add(edgeRhombus.getEdges().get(0));
        }

        Edge edge2 = pieces[2];
        splitEdgesRight.add(edge2);
        splitEdgesLeft.add(edge2);
        splitPointsRight.add(end2);
        splitPointsLeft.add(end2);
    }
}
