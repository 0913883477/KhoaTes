import java.awt.*;

public class FreezePea extends Pea {
    public FreezePea(GamePanel gp, int peaLane, int x) {
        super(gp, peaLane, x);
    }
    public void advance(){
        Rectangle pRect = new Rectangle(x,130+peaLane*120,28,28);
        for (int i = 0; i < gp.laneZombie.get(peaLane).size(); i++) {
            Zombie z = gp.laneZombie.get(peaLane).get(i);
            Rectangle zRect = new Rectangle(z.posX,109 + peaLane*120,400,120);
            if(pRect.intersects(zRect)){
                z.health -= 300;
                z.slow();
                boolean exit = false;
                if(z.health < 0){
                    System.out.println("ZOMBIE DIE");
                    GamePanel.setProgress(10);
                    gp.laneZombie.get(peaLane).remove(i);
                    exit = true;
                }
                gp.lanePea.get(peaLane).remove(this);
                if(exit) break;
            }
        }
        /*if(posX > 2000){
            gp.lanePeas.get(myLane).remove(this);
        }*/
        x += 15;
    }
}
