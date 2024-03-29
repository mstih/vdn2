import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//posebej dopolnjen gumb za stevila, z lazji zapis in izpis texta na gumbu in pozicijo gumba v dvo dimenzionalnem sistemu igralnega polja
public class GameButton extends JButton implements ActionListener {
    private int x, y;
    private GUI gui;


    GameButton(int value, int x, int y, GUI gui) {
        this.x = x;
        this.y = y;
        this.setText("" + value);
        this.gui = gui;
        addActionListener(this);
    }

    public int getValue() {
        return Integer.parseInt(getText());
    }

    public void setValue(int value) {
        this.setText("" + value);
    }

    //action listener ob pritisku zazene funkcijo 
    @Override
    public void actionPerformed(ActionEvent e) {
        gui.buttonPressed(x, y);
    }

    public int returnX() {
        return x;
    }

    public int returnY() {
        return y;
    }
}
