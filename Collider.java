import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class Collider extends JPanel implements MouseListener {
    ActionListener actionListener;
    public Collider(){
        setOpaque(false);
        addMouseListener(this);
        setSize(100 ,120);
    }
    public Plant assignedPlant;
    public void setPlant(Plant p){
        assignedPlant=p;
    }
    public void removePlant(){
        assignedPlant.stop();
        assignedPlant=null;
    }

    public boolean isInsideCollider(int tx) {return (tx > getLocation().x) && (tx < getLocation().x+100); }
    public void setAction(ActionListener actionListener){this.actionListener=actionListener;}

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (actionListener!=null){
            actionListener.actionPerformed(new ActionEvent(this,ActionEvent.RESERVED_ID_MAX+1,""));
        }

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

}
