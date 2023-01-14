import javax.swing.*;

public class GameButton extends JButton {
    private int value;

    GameButton(int value){
        this.value = value;
        this.setText("" + value);
        //TODO: Value to string to set text
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
