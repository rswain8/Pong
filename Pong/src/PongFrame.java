import javax.swing.*;
import java.applet.*;
public class PongFrame extends JFrame {

    PongPanel p=new PongPanel();

    public PongFrame(){
        super("Pong");
        setSize(1015,540);
        add(p);
        setVisible(true);
    }
    public static void main(String[]args){
        PongFrame f=new PongFrame();
    }
}

