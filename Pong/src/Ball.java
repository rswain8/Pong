import java.awt.*;
public class Ball {

    static final double MAX_SPEED=20;
    static final double WIDTH=10;
    static final double HEIGHT=10;

    double x;
    double y;

    double speed;
    double angle;

    public Ball(double x, double y){
        this.x=x;
        this.y=y;
        speed=50;
        angle=90;
    }
    public void reset(){
        x=500;
        y=250;
        speed=15;
        angle=(int)(Math.random()+1)*360;

    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        if(speed<=MAX_SPEED)
        this.speed = speed;
        else
            speed=MAX_SPEED;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    public Rectangle getRectangle(){
        return new Rectangle((int)x,(int)y,(int)WIDTH,(int)HEIGHT);
    }
}
