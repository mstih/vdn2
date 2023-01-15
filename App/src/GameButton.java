import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButton extends JButton implements ActionListener {
    private int x, y;
    private GUI gui;


    GameButton(int value, int x, int y, GUI gui){
        this.x = x;
        this.y = y;
        this.setText("" + value);
        this.gui = gui;
        addActionListener(this);
        //TODO: Value to string to set text
    }

    public int getValue() {
        return Integer.parseInt(getText());
    }

    public void setValue(int value) {
        this.setText("" + value);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.buttonPressed(x, y);
    }
}
