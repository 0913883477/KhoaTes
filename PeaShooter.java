import java.awt.event.ActionEvent;
import javax.swing.*;

public class PeaShooter extends Plant {
    public Timer shootTime;
    public PeaShooter(GamePanel gp, int x, int y) {
        super(gp, x, y);
        shootTime= new Timer(2000, (ActionEvent event) -> {
            if (gp.laneZombie.get(y).size() >0) {
                gp.lanePea.get(y).add(new Pea(gp,y,130+this.x*120));
            }
        });
        shootTime.start();
    }
    public void stop(){
        shootTime.stop();
    }
}
