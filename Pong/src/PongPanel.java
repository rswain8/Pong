import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class PongPanel extends JPanel implements KeyListener, Runnable{

    PongGame game;
    //BufferedImage buffer;
    int UPS=30;
    int FPS=30;
    Thread t=new Thread(this);
    boolean jScored=false;

    public PongPanel(){
        super();
        setSize(1000,500);
        addKeyListener(this);
        game=new PongGame();

        t.start();
    }
    public void update(){

        game.update();
        jScored=game.justScored;
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,500);
        g.setColor(Color.WHITE);
        g.fillRect((int)game.p1.x,(int)game.p1.y,10,60);
        g.fillRect((int)game.p2.x,(int)game.p2.y,10,60);
        g.fillOval((int)game.ball.getX(),(int)game.ball.getY(),10,10);
        g.fillRect(0,0,1000,20);
        g.fillRect(0,480,1000,20);
        //lines down middle
        g.fillRect(497,5,7,45);
        g.fillRect(497,55,7,45);
        g.fillRect(497,105,7,45);
        g.fillRect(497,155,7,45);
        g.fillRect(497,205,7,45);
        g.fillRect(497,255,7,45);
        g.fillRect(497,305,7,45);
        g.fillRect(497,355,7,45);
        g.fillRect(497,405,7,45);
        g.fillRect(497,455,7,45);

        g.setColor(Color.BLUE);
        g.drawRect((int)game.p1.x,(int)game.p1.y,10,60);
        g.drawString(game.p1.score+"",100,75);
        g.setColor(Color.RED);
        g.drawRect((int)game.p2.x,(int)game.p2.y,10,60);
        g.drawString(game.p2.score+"",900,75);
        g.setFont(new Font("Times New Roman",0,24));
        if(game.status==1){
            g.drawString("Player Blue has won! Press n for new game",100,300);
            t.suspend();
        }
        if(game.status==2)
        {
            g.drawString("Red Player has won! Press n for new game",100,300);
            t.suspend();
        }


    }
    public void addNotify(){
        super.addNotify();
        requestFocus();
    }
    public void run(){
        int sTime=1000/FPS;
        while(true) {
            update();

            repaint();
            try {
                if(jScored){
                    Thread.sleep(2000);
                    jScored=false;
                }
                Thread.sleep(sTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void keyPressed(KeyEvent e){
        char c=e.getKeyChar();
        int k=e.getKeyCode();

        if(c=='w')
            game.p1.setUpPressed(true);
        if(c=='s')
            game.p1.setDownPressed(true);
        if(k==KeyEvent.VK_UP)
            game.p2.setUpPressed(true);
        if(k==KeyEvent.VK_DOWN)
            game.p2.setDownPressed(true);

    }
    public void keyReleased(KeyEvent e){
        char c=e.getKeyChar();
        int k=e.getKeyCode();
        if(c=='w')
            game.p1.setUpPressed(false);
        if(c=='s')
            game.p1.setDownPressed(false);
        if(k==KeyEvent.VK_UP)
            game.p2.setUpPressed(false);
        if(k==KeyEvent.VK_DOWN)
            game.p2.setDownPressed(false);


    }
    public void keyTyped(KeyEvent e){
        if(e.getKeyChar()=='n'){
            t.resume();


            game=new PongGame();
    }}
}
