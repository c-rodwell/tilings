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
        if (wasSplit){
            return;
        }
        double phi = (1.0 +Math.sqrt(5.0))/2.0;
        double[] proportions = {phi, 1.0, phi};
        Edge[] pieces = splitByRatio(proportions);

        Edge edge1 = pieces[0];
        Edge middlePart = pieces[1]; //middle part won't be added as an Edge, just used to make rhombus and points
        Edge edge2 = pieces[2];
        abstractPoint[] bothPoints = middlePart.bisectingPointsAtAngle(Math.PI/5.0); //ends of the rhombus
        abstractPoint rightPoint = bothPoints[0];
        abstractPoint leftPoint = bothPoints[1];

        //fist part to add is always edge1
//        splitEdgesRight.add(edge1);
//        splitEdgesLeft.add(edge1);
        addEdge(edge1, true, true);
        splitPointsLeft.add(middlePart.getEnd1());
        splitPointsRight.add(middlePart.getEnd1());

        //cases for middle part:
            //two pentagons - make a Rhombus
            //pentagon and other shape - no rhombus, other shape gets the sticking out part
            //two other shapes - should not happen - could do arbitrary thing or throw an error
            //null and other shape - still give other shape the sticking out part, no rhombus
            //pentagon and null - could make the rhombus, or ok not to.
            //two null - should not happen
        //so, can treat as two cases: (null/pentagon) vs other shape

        boolean rightSticksOut = ((rightSide != null) && (rightSide.getClass() != Pentagon.class));
        boolean leftSticksOut = ((leftSide != null) && (leftSide.getClass() != Pentagon.class));

        if (rightSticksOut && leftSticksOut){
            throw new RuntimeException("Illegal pattern: two non-pentagon shapes touching");
        } else if (rightSticksOut){
            throw new RuntimeException("haven't implemented pentagon to non-pentagon yet");
        } else if (leftSticksOut){
            throw new RuntimeException("haven't implemented pentagon to non-pentagon yet");
        } else {
            Rhombus edgeRhombus = Rhombus.fromLongDiag(rightPoint, leftPoint);
            addFace(edgeRhombus);//splitFaces.add(edgeRhombus);
            edgeRhombus.makeEdges();

            addEdge(edgeRhombus.getEdge(3), false, true);
            addEdge(edgeRhombus.getEdge(0), false, true);
            addEdge(edgeRhombus.getEdge(2), true, false);
            addEdge(edgeRhombus.getEdge(1), true, false);

//            splitEdgesRight.add(edgeRhombus.getEdge(3));
//            splitEdgesRight.add(edgeRhombus.getEdge(0));
//            splitEdgesLeft.add(edgeRhombus.getEdge(2));
//            splitEdgesLeft.add(edgeRhombus.getEdge(1));

            splitPointsLeft.add(leftPoint);
            splitPointsRight.add(rightPoint);
        }

        addEdge(edge2, true, true);
        //splitEdgesRight.add(edge2);
        //splitEdgesLeft.add(edge2);
        splitPointsLeft.add(middlePart.getEnd2());
        splitPointsRight.add(middlePart.getEnd2());
        giveSplitsToFaces();
        wasSplit = true;
    }
}
