import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GUI extends JFrame{
    JFrame frame;
    JPanel upperPanel, buttonPanel, lowerPanel, rightPanel; //paneli za napise in gumbe
    JLabel targetScoreLabel, currentScoreLabel, movesLeftLabel, currentOperationLabel, operatorLabel;//labeli za napise
    int gridSizeM, gridSizeN, targetScore, currentScore, movesLeft;
    GameButton previouslyPressed;
    GameButton[][] buttons;
    JButton[] operatorButtons;
    char currentOperation; //shranjuje trenutno operacijo
    Font font, fontBigger; //prvi za vse razen za gumbe z operatorji


    GUI(int gridSizeM, int gridSizeN, int targetScore, int movesLeft){
        
        //nastavi vrednosti za zacetek igre
        currentOperation = '+';
        this.targetScore = targetScore;
        this.movesLeft = movesLeft;
        this.gridSizeM = gridSizeM;
        this.gridSizeN = gridSizeN;

        //Frame za igro
        frame = new JFrame("More or less less is more");
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLocationRelativeTo(null);

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
        currentOperationLabel = new JLabel("CURRENT OPERATION: ");
        currentOperationLabel.setFont(font);
        currentOperationLabel.setVisible(true);

        //Label za operacijo
        operatorLabel = new JLabel("" + currentOperation);
        operatorLabel.setFont(fontBigger);
        operatorLabel.setVisible(true);
        operatorLabel.setHorizontalAlignment(JLabel.CENTER);

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
        rightPanel.setLayout(new GridLayout(2, 1));
        rightPanel.add(currentOperationLabel);
        rightPanel.add(operatorLabel);
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 375, 10));
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

    //izracuna trenuten score
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
        //previouslyPressed shranjuje gumb pritisnjen pred tem
        if(previouslyPressed != null){ 
            int num2 = buttons[x][y].getValue();
            int result = 0;
            switch (currentOperation){ //izracuna glede na trenutni operator
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

            //nastavi novo vrednost gumbu
            previouslyPressed.setValue(result);
            //ponovno izracuna score
            currentScoreLabel.setText("CURRENT SCORE: " + calculateScore());
            //zmanjsa stevilo potez
            movesLeft--;
            movesLeftLabel.setText("MOVES LEFT: " + movesLeft);
            //izbere nov operator
            selectOperator();

            //preveri, ce je user zmagal ali ne, izpise temu primeren text
            if(currentScore == targetScore){
                frame.remove(upperPanel);
                frame.remove(lowerPanel);
                frame.remove(rightPanel);
                frame.remove(buttonPanel);
                JLabel wonGame = new JLabel("Congratulations, you won!");
                wonGame.setHorizontalAlignment(JLabel.CENTER);
                wonGame.setFont(fontBigger);
                wonGame.setBorder(BorderFactory.createEmptyBorder(50, 25, 50, 25));
                frame.add(wonGame);

            }else if(movesLeft <= 0){
                frame.remove(upperPanel);
                frame.remove(lowerPanel);
                frame.remove(rightPanel);
                frame.remove(buttonPanel);
                int finalScore = Math.abs(targetScore - currentScore);
                JLabel label = new JLabel("You lost! Difference to Target score: " + finalScore);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(fontBigger);
                label.setBorder(BorderFactory.createEmptyBorder(50, 25, 50, 25));
                frame.add(label);
            }

        }
        lockButtons(x, y);
        //shrani trenutno pritisnjen gumb za naslednjo potezo
        previouslyPressed = buttons[x][y];
    }

    //funkcija, ki izklopi ostale gumbe, da jih ni moc pritisniti
    public void lockButtons(int x, int y){
        for (int i = 0; i < gridSizeM; i++) {
            for (int j = 0; j < gridSizeN; j++) {
                if(i == x && j == y ){ //izklopi ze pritisnjen gumb
                    buttons[i][j].setEnabled(false);
                }else if(i==x || j==y){ //pusti gumbe v isti vrstici in stolpcu vklopljene
                    buttons[i][j].setEnabled(true);
                    if(currentOperation == '/' && buttons[i][j].getValue() == 0){ //izklopi vse 0, ce je operator deljenje
                        buttons[i][j].setEnabled(false);
                    }

                }else{ //izklopi vse ostale, ki ne ustrezajo pogojem
                    buttons[i][j].setEnabled(false);

                }
            }
        }
    }

    //funkcija, ki nakljucno izbere operator za naslednjo potezo
    public void selectOperator(){
        char[] operators = {'+', '-', '/', '*'};
        int random = new Random().nextInt(4);
        currentOperation = operators[random];
        operatorLabel.setText("" + currentOperation);
    }

}
