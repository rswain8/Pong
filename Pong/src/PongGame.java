import java.awt.*;
public class PongGame {


        //hitrp isnt getting triggered in setAngle

    boolean justScored=false;

    static final int PLAYING=0;
    static final int PLAYER_1_WINS=1;
    static final int PLAYER_2_WINS=2;

    Paddle p1;
    Paddle p2;

    Rectangle topWall;
    Rectangle bottomWall;

    Ball ball;

    int status;
    int playerOneScore;
    int playerTwoScore;

    int direction=1;


    public PongGame(){
        ball=new Ball(500,250);
        p1=new Paddle(0,250);
        p2=new Paddle(980,250);
        reset();
        System.out.println(ball.angle);
    }
    public void reset(){
        status=PLAYING;
        ball.reset();
        playerOneScore=0;
        playerTwoScore=0;
        p1.setX(0);
        p1.setY(250);
        p2.setX(990);
        p2.setY(250);
    }
    public void update(){
        int x=(int)ball.getX();
        int y=(int)ball.getY();
        p1.update();
        p2.update();
    double leftOverDistance=0;
        if(hitRightPaddle())
            direction=-1;
        else if(hitLeftPaddle())
            direction=1;
        moveBall(ball.getSpeed());
/*
        if(hitRightPaddle())
        {
            setAngle();
           int nx=(int)ball.getX();
           int ny=(int)ball.getY();

           int bigHeight=y-ny;
           int bigWidth=x-nx;
            double hy;
            int hx=1000-p2.WIDTH;

            if(bigWidth!=0)
             hy=(bigHeight*((hx-x)/bigWidth))-y;


           int XTraveledBeforeHit=hx-x;
           int YTraveledBeforeHit=y+(int)hy;
           //System.out.println(XTraveledBeforeHit);
          // System.out.println(YTraveledBeforeHit);
            //System.out.println((Math.sqrt((XTraveledBeforeHit*XTraveledBeforeHit)+(YTraveledBeforeHit*YTraveledBeforeHit))));
           //System.out.println(ball.getSpeed()+" "+(Math.sqrt((bigHeight*bigHeight)+(bigWidth*bigWidth))));
          leftOverDistance=ball.getSpeed()-(Math.sqrt((XTraveledBeforeHit*XTraveledBeforeHit)+(YTraveledBeforeHit*YTraveledBeforeHit)));
          System.out.println(hy);
           ball.setY(-hy);




        }
        moveBall(leftOverDistance);*/
    }
    public boolean hitTopWall(){
        if(ball.getY()<=20)
            return true;
        else
        return false;
    }
    public boolean hitBottomWall(){
        if(ball.getY()>=470)
            return true;
        else
            return false;
    }
    public boolean hitLeftPaddle(){
        if(ball.getX()<=p1.getX()+10 && ball.getY()>=p1.getY() && ball.getY()<=p1.getY()+60)
            return true;
        else
            return false;
    }
    public boolean hitRightPaddle(){
        if(ball.getX()>=p2.getX() && ball.getY()>=p2.getY() && ball.getY()<=p2.getY()+60 && ball.getX()<=p2.getX()+100)
            return true;
        else
            return false;
    }
    public void moveBall(double distance){
        double distanceTraveled=ball.getX()+distance*Math.cos(Math.toRadians(ball.getAngle()))+ball.getY()+distance*Math.sin(Math.toRadians(ball.getAngle()));
        double distanceLeftOver=distance-distanceTraveled;

        setAngle();
        if(ball.getX()>1015){
            p1.score++;
            ball.reset();
        justScored=true;}
        else if(ball.getX()<-5){
            p2.score++;
            ball.reset();
        justScored=true;}
        else
            justScored=false;

        if(p1.score==7)
        status=1;
        if(p2.score==7)
            status=2;

        //double distanceTraveled=ball.getX()+distance*Math.cos(Math.toRadians(ball.getAngle()))+ball.getY()+distance*Math.sin(Math.toRadians(ball.getAngle()));
        //double distanceLeftOver=distance-distanceTraveled;

        ball.setX(ball.getX()+distance*Math.cos(Math.toRadians(ball.getAngle())));
        ball.setY(ball.getY()+distance*Math.sin(Math.toRadians(ball.getAngle())));
    }
    public void setAngle(){

        if(hitTopWall()){
            ball.setY(21);
            ball.setAngle(360-ball.getAngle()%360);
            //System.out.println(ball.angle);
            //ball.setSpeed(ball.getSpeed()+20);

        }
        if(hitBottomWall()){
            ball.setY(479);
           // ball.setAngle(180+ball.angle+((90-ball.angle)*2));
            ball.setAngle(360-ball.getAngle()%360);
            if(ball.getAngle()>=180-8 && ball.getAngle()<=180+8)
                if(ball.angle>90 && ball.getAngle()<270)
                    ball.setAngle(165);
            if(ball.getAngle()>=-8 && ball.getAngle()<=8)
                ball.setAngle(345);

            //ball.setSpeed(ball.getSpeed()+20);

        }
        if(hitLeftPaddle()){
                ball.setX(11);
            double pFromMid=100*((ball.getY()-p1.getY()+30)/30);
            //very top
            if(ball.getY()>=p1.getY()-10&& ball.getY()<=p1.getY())
                ball.setAngle(280);
            //top
            if(ball.getY()<p1.getY()+25)
                ball.setAngle(360-(pFromMid*70));
            if(ball.getY()<p1.getY()+35 && ball.getY()>p1.getY()+25)
                ball.setAngle(0);
            //bottom
            if(ball.getY()<p1.getY()+60 && ball.getY()>p1.getY()+35)
                ball.setAngle(pFromMid*70);
            //very bottom
            if(ball.getY()<=p1.getY()+70 && ball.getY()>p1.getY()+60)
                ball.setAngle(80);


        }
        if(hitRightPaddle()){
            System.out.println("hitrp");
            ball.setX(989);
            double pFromMid=(Math.abs(ball.getY()-(p2.getY()+30))/30);
            //very top
            if(ball.getY()>=p2.getY()-10 && ball.getY()<=p2.getY()){
                ball.setX(989);
                System.out.println("verytop");
                ball.setAngle(-350);}
            //top
            else if(ball.getY()<p2.getY()+25){
                ball.setAngle(180+(pFromMid*70));
                System.out.println("top portion");

                ball.setX(989);}
            //center
            else if(ball.getY()==p2.getY()+25){
                ball.setAngle(180);
                System.out.println("center");
                ball.setX(989);}
            //bottom
            else if(ball.getY()<p2.getY()+60){
                ball.setAngle(180-pFromMid*70);
                System.out.println("bottom");
                ball.setX(989);}
            //very bottom
            else if(ball.getY()<=p2.getY()+70){
                ball.setAngle(100);
                System.out.println("very bottom");
                ball.setX(989);}
            //wwwball.setSpeed(20);
           // System.out.println(ball.angle);
        }


    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
