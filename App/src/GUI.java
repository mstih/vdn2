import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GUI extends JFrame implements ActionListener{
    JFrame frame;
    JPanel upperPanel, buttonPanel, lowerPanel, rightPanel; //paneli za napise in gumbe
    JLabel targetScoreLabel, currentScoreLabel, movesLeftLabel, currentOperationLabel;//labeli za napise
    int gridSizeM, gridSizeN, targetScore, currentScore, movesLeft;
    GameButton previouslyPressed;
    GameButton[][] buttons;
    JButton additionButton, subtractionButton, divisionButton, multiplicationButton; //dodatni gumbi za operacije
    JButton operatorButtons[];
    char currentOperation; //shranjuje trenutno operacijo
    Font font, fontBigger; //prvi za vse razen za gumbe z operatorji


    GUI(int gridSizeM, int gridSizeN){

        currentOperation = '+';
        targetScore = 420;
        movesLeft = 25;
        this.gridSizeM = gridSizeM;
        this.gridSizeN = gridSizeN;

        //Frame za igro
        frame = new JFrame("More or less less is more");
        //frame.setTitle("More or less less is more");
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        //Font za vse napise
        font = new Font("Helvetica", Font.BOLD, 16);
        fontBigger = new Font("Helvetica", Font.BOLD, 25);

        //Label za targetscore
        targetScoreLabel = new JLabel("TARGET SCORE: " + targetScore);
        targetScoreLabel.setFont(font);
        targetScoreLabel.setVisible(true);

        //Label za current score
        currentScoreLabel = new JLabel("CURRENT SCORE: " + currentScore);
        currentScoreLabel.setFont(font);
        currentScoreLabel.setVisible(true);

        //Label za move
        movesLeftLabel = new JLabel("MOVES LEFT: " + movesLeft);
        movesLeftLabel.setFont(font);
        movesLeftLabel.setVisible(true);

        //Label za trenutno operacijo
        currentOperationLabel = new JLabel("CURRENT OPERATION: " + currentOperation);
        currentOperationLabel.setFont(font);
        currentOperationLabel.setVisible(true);

        //Gumbi za operacijo
        additionButton = new JButton("+");
        subtractionButton = new JButton("-");
        divisionButton = new JButton("/");
        multiplicationButton = new JButton("*");
        operatorButtons = new JButton[4];
        operatorButtons[0] = additionButton;
        operatorButtons[1] = subtractionButton;
        operatorButtons[2] = divisionButton;
        operatorButtons[3] = multiplicationButton;
        for (int i = 0; i < operatorButtons.length; i++) {
            operatorButtons[i].addActionListener(this);
            operatorButtons[i].setFont(fontBigger);
            operatorButtons[i].setFocusable(false);
        }

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
        frame.add(rightPanel, BorderLayout.EAST);

        //Panel za gumbe
        buttons = new GameButton[gridSizeM][gridSizeN];
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(new GridLayout(gridSizeM, gridSizeN, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setFont(font);
        for (int i = 0; i < gridSizeM; i++) {
            for (int j = 0; j < gridSizeN; j++) {
                int randInt = new Random().nextInt(10);
                buttons[i][j] = new GameButton(randInt, i, j, this);
                buttons[i][j].setFocusable(false);
                buttonPanel.add(buttons[i][j]);
            }
        }
        buttonPanel.setVisible(true);
        frame.add(buttonPanel, BorderLayout.CENTER);

        currentScoreLabel.setText("CURRENT SCORE: " + calculateScore());


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 4; i++) {
            if(e.getSource() == operatorButtons[i]){
                currentOperation = operatorButtons[i].getText().charAt(0);
                System.out.println(currentOperation);
                currentOperationLabel.setText("CURRENT OPERATION: " + operatorButtons[i].getText());
                //operatorButtons[i].setEnabled(false);
            }
        }


    }

    int calculateScore(){
        currentScore = 0;
        for (int i = 0; i < gridSizeM; i++) {
            for (int j = 0; j < gridSizeN; j++) {
                String strValue = buttons[i][j].getText();
                int value = Integer.parseInt(strValue);
                currentScore += value;
            }
        }
        return currentScore;
    }


    public void buttonPressed(int x, int y) {
        if(previouslyPressed != null){
            int num2 = buttons[x][y].getValue();
            int result = 0;
            switch (currentOperation){
                case '+':
                    result = (previouslyPressed.getValue() + num2) % 10;
                    break;
                case '-':
                    result = Math.abs(previouslyPressed.getValue() - num2) % 10;
                    break;
                case '/':
                    result = (previouslyPressed.getValue() / num2) % 10;
                    break;
                case '*':
                    result = (previouslyPressed.getValue() * num2) % 10;
                    break;
            }
            previouslyPressed.setValue(result);
            currentScoreLabel.setText("CURRENT SCORE: " + calculateScore());

        }
        lockButtons(x, y);
        previouslyPressed = buttons[x][y];
    }


    public void lockButtons(int x, int y){
        for (int i = 0; i < gridSizeM; i++) {
            for (int j = 0; j < gridSizeN; j++) {
                if(i == x && j == y ){
                    buttons[i][j].setEnabled(false);
                }else if(i==x || j==y){
                    buttons[i][j].setEnabled(true);
                    if(currentOperation == '/' && buttons[i][j].getValue() == 0){
                        buttons[i][j].setEnabled(false);
                    }

                }else{
                    buttons[i][j].setEnabled(false);

                }
            }
        }
    }
}
