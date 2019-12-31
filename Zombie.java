import javax.swing.*;

public class Zombie {
    public int health=1000;
    public int speed =1;
    protected GamePanel gp;

    public int posX=1000;
    public int zomLane;
    public boolean moving=true;

    public Zombie(GamePanel gp, int zomLane){
        this.gp=gp;
        this.zomLane=zomLane;

    }
    int slowInt=0;
    public void slow(){
        slowInt=1000;
    }
    public void object(){
        if (moving){
            boolean isCollides=false;
            Collider collider=null;
            for(int i= zomLane*9;i<(zomLane+1)*9;i++){
                if(gp.colliders[i].assignedPlant != null && gp.colliders[i].isInsideCollider(posX)){
                    isCollides =true;
                    collider=gp.colliders[i];
                }
            }
            if(!isCollides){
                if(slowInt>0){
                    if(slowInt %2==0){
                        posX--;
                    }
                    slowInt--;
                }
                else posX-=1;
            }
            else {
                collider.assignedPlant.health-=10;
                if (collider.assignedPlant.health<0){
                    collider.removePlant();
                }
            }
            if(posX <0){
                moving=false;
                JOptionPane.showMessageDialog(gp,"ZOMBIES ATE YOUR BRAIN");
                GameWindow.gw.dispose();
                GameWindow.gw=new GameWindow(true);
            }

        }
    }
    public static Zombie getZombies(String type,GamePanel gp,int zomLane){
        Zombie z=new Zombie(gp,zomLane);
        switch (type){
            case "NormalZombie": z=new NormalZombie(gp,zomLane);
            break;
            case "ConeHeadZombie" : z=new ConeHeadZombie(gp,zomLane);
            break;
        }
        return z;
    }


}
