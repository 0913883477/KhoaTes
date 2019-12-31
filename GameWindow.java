import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame {
    enum PlantType{
        SunFlower,PeaShooter,FreezePeaShooter,None
    }

    public GameWindow(){
        setSize(1015, 785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel Sun= new JLabel("Sun");
        Sun.setLocation(37,80);
        Sun.setSize(60,20);

        GamePanel gp=new GamePanel(Sun);
        setLocation(0,0);
        getLayeredPane().add(gp,new Integer(0));

        PlantCard SunFlower = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_sunflower.png")).getImage());
        SunFlower.setLocation(110,8);
        SunFlower.setAction( (ActionEvent e ) ->{
            gp.activePlantBrushing = PlantType.SunFlower;
        });
        getLayeredPane().add(SunFlower,new Integer(3));


        PlantCard peaShooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/cards_peashooter.png/")).getImage());
        peaShooter.setLocation(175,8);
        peaShooter.setAction((ActionEvent e) -> {
            gp.activePlantBrushing = PlantType.PeaShooter;
        });
        getLayeredPane().add(peaShooter,new Integer(3));


        PlantCard freezePeaShooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/cards_freezepeashooter.png")).getImage());
        freezePeaShooter.setLocation(240,8);
        freezePeaShooter.setAction((ActionEvent e) -> {
            gp.activePlantBrushing = PlantType.FreezePeaShooter;
        });
        getLayeredPane().add(freezePeaShooter,new Integer(3));


        getLayeredPane().add(Sun,new Integer(2));
        setResizable(false);
        setVisible(true);


    }
    public GameWindow(boolean b) {
        Menu menu = new Menu();
        menu.setLocation(0,0);
        setSize(1012,785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getLayeredPane().add(menu,new Integer(0));
        menu.repaint();
        setResizable(false);
        setVisible(true);
    }

    static GameWindow gw;
    public static void begin() {
        gw.dispose();
        gw = new GameWindow();
    }
    public static void main(String[] args) {
        gw = new GameWindow(true);
    }

}
