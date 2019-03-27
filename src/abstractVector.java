//class for a vector in abstract mathematical 2D space, using double values
//it looks a lot like abstractPoint, but conceptually different
public class abstractVector {
    double dx;
    double dy;

    public abstractVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public double length(){
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public abstractVector scale(double scale){
        return new abstractVector(dx *scale, dy*scale);
    }

    public abstractVector normalize(){
        return scale(1.0/length());
    }

    //a vector perpendicular to this one.
    // note scaling by any factor, including negative, is also perpendicular
    public abstractVector perpendicular(){
        return new abstractVector(-dy, dx); //could also use (dy, -dx) or others
    }

    public double toAngle(){
        return Math.atan2(dy, dx);
    }

    //unit vector from angle
    public static abstractVector fromAngle(double theta){
        return new abstractVector(Math.cos(theta), Math.sin(theta));
    }


    @Override
    public String toString() {
        return "Vector: dx = "+dx+", y = "+dy;
    }
}
