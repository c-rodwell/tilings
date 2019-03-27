public class quartering_Square extends Face{

    public quartering_Square(abstractPolygon square){
        super(square);
    }

    public quartering_Square(double radius, double center_x, double center_y, double rotation){
        new quartering_Square( abstractPolygon.regPolyOnCircle(4, radius, center_x, center_y, rotation));
    }

    public void split_edges(){

    }

    public void split_inside(){

    }
}
