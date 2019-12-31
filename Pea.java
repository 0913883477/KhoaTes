import java.awt.*;

public class Pea {
    public int x;
    public int peaLane;
    protected GamePanel gp;

    public Pea(GamePanel gp,int peaLane,int x) {
        this.gp=gp;
        this.peaLane=peaLane;
        this.x =x;
    }

    public void object() {
        Rectangle peaRect=new Rectangle(x,130+peaLane*120,28,28);
        for(int i=0; i<gp.lanePea.get(peaLane).size(); i++) {
            Zombie z=gp.laneZombie.get(peaLane).get(i);
            Rectangle zomRect=new Rectangle(z.posX,109+peaLane*120,400,120);
            if(peaRect.intersects(zomRect)){
                z.health-=300;
                boolean exit =false;
                if(z.health < 0){
                    System.out.println("");
                    gp.laneZombie.get(peaLane).remove(i);
                    GamePanel.
                    exit =true;
                }
                gp.lanePea.get(peaLane).remove(i);
                if(exit) break;


            }
        }
        x +=15;

    }
}
