import java.awt.*;

public class Paddle {

    public int score;

    static final int WIDTH=10;
    static final int HEIGHT=60;

    boolean upPressed;
    boolean downPressed;

    double x;
    double y;
    double speed=10;

    public Paddle(double x, double y){
        score=0;
        this.x=x;
        this.y=y;
        upPressed=false;
        downPressed=false;
    }
    public void update(){
        if(y<=20)
            y=20;
        if(y>=420)
            y=420;

        if(upPressed && y>20)
            y-=speed;
        else if(downPressed && y<420)
            y+=speed;

    }
    public Rectangle getRectangle(){
        return new Rectangle((int)x,(int)y,WIDTH,HEIGHT);
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
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
        this.speed = speed;
    }
}
