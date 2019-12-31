import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JLayeredPane implements MouseMotionListener {
    public static boolean exit;
    Image backgroundImg;
    Image peaImg;
    Image peaShooterImg;
    Image freezePeaImg;
    Image freezePeaShooterImg;
    Image sunImg;
    Image sunFlowerImg;

    Image normalZombieImg;
    Image coneHeadZombieImg;
    Collider[] colliders;

    ArrayList<ArrayList<Zombie>> laneZombie;
    ArrayList<ArrayList<Pea>> lanePea;
    ArrayList<Sun> activeSun;

    JLabel SunScoreBoard;
    Timer redrawTimer;
    Timer advancerTimer;
    Timer sunProducer;
    Timer zombieProducer;

    GameWindow.PlantType activePlantBrushing= GameWindow.PlantType.None;

    private int SunScore;

    public int getSunScore() {
        return SunScore;
    }

    public void setSunScore(int SunScore) {
        this.SunScore = SunScore;
        SunScoreBoard.setText(String.valueOf(SunScore));
    }

    public GamePanel(JLabel SunScoreBoard) {
        setSize(1015, 785);
        setLayout(null);
        addMouseMotionListener(this);
        this.SunScoreBoard = SunScoreBoard;
        setSunScore(1000);

        backgroundImg = new ImageIcon(this.getClass().getResource("images/mainBG.png")).getImage();

        peaShooterImg = new ImageIcon(this.getClass().getResource("images/plants/peashooter.gif")).getImage();
        freezePeaShooterImg = new ImageIcon(this.getClass().getResource("images/plants/freezepeashooter.gif")).getImage();
        sunFlowerImg = new ImageIcon(this.getClass().getResource("images/plants/sunflower.gif")).getImage();

        peaImg = new ImageIcon(this.getClass().getResource("images/pea.png")).getImage();
        freezePeaImg = new ImageIcon(this.getClass().getResource("images/freezepea/png")).getImage();
        sunImg = new ImageIcon(this.getClass().getResource("images/sun.png")).getImage();

        coneHeadZombieImg = new ImageIcon(this.getClass().getResource("images/zombies/zombie2.png")).getImage();
        normalZombieImg = new ImageIcon(this.getClass().getResource("images/zombies/zombie1.png")).getImage();


        laneZombie = new ArrayList<>();
        laneZombie.add(new ArrayList<>());
        laneZombie.add(new ArrayList<>());
        laneZombie.add(new ArrayList<>());
        laneZombie.add(new ArrayList<>());
        laneZombie.add(new ArrayList<>());

        lanePea = new ArrayList<>();
        lanePea.add(new ArrayList<>());
        lanePea.add(new ArrayList<>());
        lanePea.add(new ArrayList<>());
        lanePea.add(new ArrayList<>());
        lanePea.add(new ArrayList<>());

        colliders = new Collider[45];
        for (int i = 0; i <= 45; i++) {
            Collider collider = new Collider();
            collider.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
            collider.setAction(new PlantActionListener(i % 9, i / 9));
            colliders[i] = collider;
            add(collider, Integer.valueOf(0));
        }


        activeSun = new ArrayList<>();
        redrawTimer = new Timer(25, (ActionEvent event) -> {
            repaint();
        });

        redrawTimer.start();

        advancerTimer = new Timer(60,(ActionEvent e) -> advance());
        advancerTimer.start();

        sunProducer = new Timer(5000, (ActionEvent event) -> {
            Random rnd = new Random();
            Sun star = new Sun(this, rnd.nextInt(800) + 100, 0, rnd.nextInt(300) + 200);
            activeSun.add(star);
        });
        zombieProducer = new Timer(5000,(ActionEvent e) -> {
            Random rnd = new Random();
            Level lvl = new Level();
            String [] Level = lvl.Level[Integer.parseInt(lvl.lvl)-1];
            int [][] LevelValue = lvl.LevelValue[Integer.parseInt(lvl.lvl)-1];
            int l = rnd.nextInt(5);
            int t = rnd.nextInt(100);
            Zombie z = null;
            for(int i = 0;i<LevelValue.length;i++) {
                if(t>=LevelValue[i][0]&&t<=LevelValue[i][1]) {
                    z = Zombie.getZombies(Level[i],GamePanel.this,l);
                }
            }
            laneZombie.get(l).add(z);
        });

        sunProducer.start();

        zombieProducer = new Timer(5000, (ActionEvent event) -> {
            Random rnd = new Random();


        });
    }


    private void advance() {
        for (int i = 0; i <= 5; i++) {
            for (Zombie z : laneZombie.get(i)) {
                z.slow();
            }
            for(int j=0;j<=lanePea.get(i).size();j++) {
                Pea p= lanePea.get(i).get(j);
                p.object();
            }

        }
        for(int i=0;i<=activeSun.size();i++){
            activeSun.get(i).advance();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, null);

        for (int i = 0; i < 45; i++) {
            Collider c = colliders[i];
            if (c.assignedPlant != null) {
                Plant p = c.assignedPlant;
                if (p instanceof PeaShooter) {
                    g.drawImage(peaShooterImg, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if (p instanceof FreezePeaShooter) {
                    g.drawImage(freezePeaImg, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if (p instanceof SunFlower) {
                    g.drawImage(sunFlowerImg, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (Zombie z : laneZombie.get(i)) {
                if (z instanceof NormalZombie) {
                    g.drawImage(normalZombieImg, z.posX, 109 + (i * 120), null);
                } else if (z instanceof ConeHeadZombie) {
                    g.drawImage(coneHeadZombieImg, z.posX, 109 + (i * 120), null);
                }
            }

            for (int j = 0; j < lanePea.get(i).size(); j++) {
                Pea p = lanePea.get(i).get(j);
                if (p instanceof FreezePea) {
                    g.drawImage(freezePeaImg, p.x, 130 + (i * 120), null);
                } else {
                    g.drawImage(peaImg, p.x, 130 + (i * 120), null);
                }
            }

        }
    }









    class PlantActionListener implements ActionListener {
        int x;
        int y;

        public PlantActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (activePlantBrushing == GameWindow.PlantType.SunFlower) {
                if (getSunScore() >= 50) {
                    colliders[x + y + 9].setPlant(new SunFlower(GamePanel.this, x, y));
                    setSunScore(getSunScore()-50);
                }
            }
            if (activePlantBrushing== GameWindow.PlantType.PeaShooter){
                if(getSunScore() >=100){
                    colliders[x+y+9].setPlant(new PeaShooter(GamePanel.this,x,y));
                    setSunScore(getSunScore()-100);
                }
            }

            if (activePlantBrushing== GameWindow.PlantType.FreezePeaShooter){
                if (getSunScore() >=175){
                    colliders[x+y+9].setPlant(new FreezePeaShooter(GamePanel.this,x,y));
                    setSunScore(getSunScore()-175);
                }
            }

            activePlantBrushing = GameWindow.PlantType.None;

        }
    }



    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Object mouseX = mouseEvent.getX();
        Object mouseY = mouseEvent.getY();

    }
    static int progress = 0;
    public static void setProgress(int num){
        progress = progress + num;
        System.out.println(progress);
        if(progress>=150) {
            if("1".equals(Level.lvl)) {
                JOptionPane.showMessageDialog(null,"Level Completed !!!" + '\n' + "Starting next Level");
                GameWindow.gw.dispose();
                Level.write("2");
                GameWindow.gw = new GameWindow();
            }  else {
                JOptionPane.showMessageDialog(null,"Level Completed !!!" + '\n' + "More Levels will come soon !!!" + '\n' + "Resetting data");
                Level.write("1");
                System.exit(0);
            }
            progress = 0;
        }

    }

}
