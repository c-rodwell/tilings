package core;

//class for a vector in abstract mathematical 2D space, using double values
//it looks a lot like abstractPoint, but conceptually different
public class abstractVector {
    double dx;
    double dy;

    public abstractVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    //unit vector from angle
    public static abstractVector fromAngle(double theta){
        return new abstractVector(Math.cos(theta), Math.sin(theta));
    }

    //(x,y) vector from r and theta
    public static abstractVector fromPolar(double r, double theta){
        return fromAngle(theta).scale(r);
    }

    public double length(){
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public double angle(){
        return Math.atan2(dy, dx);
    }

    public abstractVector scale(double scale){
        return new abstractVector(dx *scale, dy*scale);
    }

    public abstractVector normalize(){
        return scale(1.0/length());
    }

    //a vector perpendicular to this one, with same length
    // note scaling by any factor, including negative, is also perpendicular
    public abstractVector perpendicular(){
        return new abstractVector(-dy, dx); //could also use (dy, -dx)
    }

    //perpendicular vector with length 1. remember the negative of this is also a normal vector.
    public abstractVector normalVector(){
        return perpendicular().normalize();
    }

    public abstractVector rotate(double theta){
        return fromPolar(length(), angle()+theta);
    }

    @Override
    public String toString() {
        return "Vector: dx = "+dx+", y = "+dy;
    }
}
