import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GUI extends JFrame {
    public JFrame frame;
    public JPanel upperPanel, buttonPanel, lowerPanel, rightPanel; //paneli za napise in gumbe
    public JLabel targetScoreLabel, currentScoreLabel, movesLeftLabel, currentOperationLabel;//labeli za napise
    public int gridSizeM, gridSizeN, targetScore, currentScore, movesLeft;
    public GameButton button;
    public JButton additionButton, subtractionButton, divisionButton, multiplicationButton;
    public char currentOperation;
    public Font font;


    GUI(int gridSizeM, int gridSizeN){
        //Frame za igro
        frame = new JFrame();
        frame.setTitle("More or less less is more");
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        //Font za vse napise
        font = new Font("Helvetica", Font.BOLD, 16);

        //Label za targetscore
        targetScoreLabel = new JLabel();
        targetScoreLabel.setText("TARGET SCORE: " + targetScore);
        targetScoreLabel.setFont(font);
        targetScoreLabel.setVisible(true);

        //Label za current score
        currentScoreLabel = new JLabel();
        currentScoreLabel.setText("CURRENT SCORE: " + currentScore);
        currentScoreLabel.setFont(font);
        currentScoreLabel.setVisible(true);

        //Label za move
        movesLeftLabel = new JLabel();
        movesLeftLabel.setText("MOVES LEFT: " + movesLeft);
        movesLeftLabel.setFont(font);
        movesLeftLabel.setVisible(true);

        //Label za trenutno operacijo
        currentOperationLabel = new JLabel();
        currentOperationLabel.setText("CURRENT OPERATION: " + currentOperation);
        currentOperationLabel.setFont(font);
        currentOperationLabel.setVisible(true);

        //Gumbi za operacijo
        additionButton = new JButton("+");
        subtractionButton = new JButton("-");
        divisionButton = new JButton("/");
        multiplicationButton = new JButton("*");

        //zgornji panel za target score in moves
        upperPanel = new JPanel(new BorderLayout());
        upperPanel.add(targetScoreLabel, BorderLayout.CENTER);
        upperPanel.add(movesLeftLabel, BorderLayout.EAST);
        upperPanel.setBackground(Color.LIGHT_GRAY);
        upperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        frame.add(upperPanel, BorderLayout.NORTH);

        //spodnji panel za current score
        lowerPanel = new JPanel();
        lowerPanel.add(currentScoreLabel);
        lowerPanel.setBackground(Color.LIGHT_GRAY);
        frame.add(lowerPanel, BorderLayout.SOUTH);

        //desni panel za operacije
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5, 1, 10, 10));
        rightPanel.add(currentOperationLabel);
        rightPanel.add(additionButton);
        rightPanel.add(subtractionButton);
        rightPanel.add(divisionButton);
        rightPanel.add(multiplicationButton);
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        rightPanel.setFont(font);
        frame.add(rightPanel, BorderLayout.EAST);

        //Panel za gumbe
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(new GridLayout(gridSizeM, gridSizeN, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setFont(font);
        for (int i = 0; i < gridSizeM; i++) {
            for (int j = 0; j < gridSizeN; j++) {
                int randInt = new Random().nextInt(10);
                button = new GameButton(randInt);
                buttonPanel.add(button);
            }
        }
        buttonPanel.setVisible(true);
        frame.add(buttonPanel, BorderLayout.CENTER);






        frame.setVisible(true);
    }
}
