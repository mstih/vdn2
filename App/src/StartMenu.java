import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StartMenu implements ActionListener{
    JFrame startframe;
    JPanel settingsPanel, startPanel;
    Font font;
    JButton fiveButton, sevenButton, tenButton, easyButton, mediumButton, hardButton, startButton;
    JButton[] sizeButtons, difficultyButtons;
    int[] gridSize = {5, 7, 10};
    int[] target = {85, 230, 420};
    int[] difficulty = {20, 15, 10};
    boolean sizeChoosen, difficultyChoosen;
    int sizeIndex, difficultyIndex;
    
    StartMenu(){
        sizeChoosen = false;
        difficultyChoosen = false;

        //Okno
        startframe = new JFrame();
        startframe.setTitle("Start Menu");
        startframe.setResizable(false);
        startframe.setLayout(new BorderLayout());
        startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startframe.setLocationRelativeTo(null);
        startframe.getContentPane().setBackground(Color.LIGHT_GRAY);

        //Font
        font = new Font("Helvetica", Font.BOLD, 20);

        //Panel za nastavitve igre
        settingsPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        settingsPanel.setFont(font);

        //Panel za start gumb
        startPanel = new JPanel();
        
        //Gumbi za velikost
        sizeButtons = new JButton[3];
        fiveButton = new JButton("5x5");
        sizeButtons[0] = fiveButton;
        sizeButtons[0].addActionListener(this);
        sizeButtons[0].setFont(font);

        sevenButton = new JButton("7x7");
        sizeButtons[1] = sevenButton;
        sizeButtons[1].addActionListener(this);
        sizeButtons[1].setFont(font);

        tenButton = new JButton("10x10");
        sizeButtons[2] = tenButton;
        sizeButtons[2].addActionListener(this);
        sizeButtons[2].setFont(font);

        //Gumbi za tezavnost
        difficultyButtons = new JButton[3];
        easyButton = new JButton("Easy");
        difficultyButtons[0] = easyButton;
        difficultyButtons[0].addActionListener(this);
        difficultyButtons[0].setFont(font);

        mediumButton = new JButton("Medium");
        difficultyButtons[1] = mediumButton;
        difficultyButtons[1].addActionListener(this);
        difficultyButtons[1].setFont(font);

        hardButton = new JButton("Hard");
        difficultyButtons[2] = hardButton;
        difficultyButtons[2].addActionListener(this);
        difficultyButtons[2].setFont(font);

        //Start gumb
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        startButton.setFont(font);

        //Prva vrsta - gumbi z velikostjo polja
        settingsPanel.add(fiveButton);
        settingsPanel.add(sevenButton);
        settingsPanel.add(tenButton); 

        //Druga vrstra - gumbi z tezavnostjo
        settingsPanel.add(easyButton); 
        settingsPanel.add(mediumButton); 
        settingsPanel.add(hardButton); 

        //Panel za start gumb
        startPanel.add(startButton);

        //Izklopi start gumb, da ga ni mozno pritisniti pred ostalimi
        startButton.setEnabled(false);

        startframe.add(settingsPanel, BorderLayout.NORTH);
        startframe.add(startPanel, BorderLayout.SOUTH);

        startframe.pack();
        startframe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //pregleda, kateri gumb za velikost je bil pritisnjen
        if(e.getSource() == sizeButtons[0]){
            for (int i = 0; i < sizeButtons.length; i++) {
                sizeButtons[i].setEnabled(false);
            }
            sizeButtons[0].setEnabled(true);
            sizeChoosen = true;
            sizeIndex = 0;
            checkStart();
        }

        if(e.getSource() == sizeButtons[1]){
            for (int i = 0; i < sizeButtons.length; i++) {
                sizeButtons[i].setEnabled(false);
            }
            sizeButtons[1].setEnabled(true);
            sizeChoosen = true;
            sizeIndex = 1;
            checkStart();
        }
        
        if(e.getSource() == sizeButtons[2]){
            for (int i = 0; i < sizeButtons.length; i++) {
                sizeButtons[i].setEnabled(false);
            }
            sizeButtons[2].setEnabled(true);
            sizeChoosen = true;
            sizeIndex = 2;
            checkStart();
        }

        //pregleda katera tezavnost je bila izbrana
        if(e.getSource() == difficultyButtons[0]){
            for (int i = 0; i < difficultyButtons.length; i++) {
                difficultyButtons[i].setEnabled(false);
            }
            difficultyButtons[0].setEnabled(true);
            difficultyChoosen = true;
            difficultyIndex = 0;
            checkStart();
        }

        if(e.getSource() == difficultyButtons[1]){
            for (int i = 0; i < difficultyButtons.length; i++) {
                difficultyButtons[i].setEnabled(false);
            }
            difficultyButtons[1].setEnabled(true);
            difficultyChoosen = true;
            difficultyIndex = 1;
            checkStart();
        }

        if(e.getSource() == difficultyButtons[2]){
            for (int i = 0; i < difficultyButtons.length; i++) {
                difficultyButtons[i].setEnabled(false);
            }
            difficultyButtons[2].setEnabled(true);
            difficultyChoosen = true;
            difficultyIndex = 2;
            checkStart();
        }

        //Ce je bil pritisnjen start button se igra zacne in zapre meni
        if(e.getSource() == startButton){
            GUI game = new GUI(gridSize[sizeIndex], gridSize[sizeIndex], target[sizeIndex], difficulty[difficultyIndex]);
            startframe.setVisible(false);
        }
    }

    //preverja, ce smo sploh zbrali velikost in tezavnost igre preden jo lahko zazenemo
    public void checkStart(){
        if(sizeChoosen && difficultyChoosen){
            startButton.setEnabled(true);
        }
    }
}
