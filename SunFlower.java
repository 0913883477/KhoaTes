import java.awt.event.ActionEvent;
import javax.swing.*;

public class SunFlower extends Plant {
    Timer sunProduceTimer;
    public SunFlower(GamePanel gp,int x, int y) {
        super(gp,x,y);
        sunProduceTimer = new Timer(2000,(ActionEvent e)-> {
            Sun star = new Sun(gp,60+100*x,100+y*120,130+y*120);
            gp.activeSun.add(star);
            gp.add(star,new Integer(1));
        });
            sunProduceTimer.start();
        }

    @Override
    public void stop() {

    }
}