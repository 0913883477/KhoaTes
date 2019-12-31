public abstract class Plant {
    public int health = 100;
    public int x, y;

    public GamePanel gp;
    public Plant(GamePanel gp,int x, int y) {
        this.x = x;
        this.y = y;
        this.gp=gp;
    }

    public void Stop() {
    }

    public abstract void stop();
}