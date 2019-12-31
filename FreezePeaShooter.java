import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeaShooter extends Plant {

    public Timer shootTimer;


    public FreezePeaShooter(GamePanel parent,int x,int y) {
        super(parent,x,y);
        shootTimer = new Timer(2000,(ActionEvent e) -> {
            //System.out.println("SHOOT");
            if(gp.laneZombie.get(y).size() > 0) {
                gp.lanePea.get(y).add(new FreezePea(gp, y, 103 + this.x * 100));
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop(){
        shootTimer.stop();
    }

}
