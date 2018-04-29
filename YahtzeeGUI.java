/**
 * This file, YahtzeeGUI.java, implements the Graphical User Interface (GUI).
 * This file is responsible for driving the entire game.
 *
 * 
 * CPSC 224-02, Spring 2018
 * Group Project: Gonzaga Men's Basketball Yahtzee (Group 1)

 * @author Ben Bladow
 * @author Eugene Krug
 * @author Brandon Niblock
 * @version v9.0 4/28/18 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
INTERNAL PANEL KEY:
1: The game!
2: Leaderboard
3: Instructions - Opening
4: Main Menu
5: Instructions - Dice
6: Instructions - How to Win
7: Instructions - How to Play
8: Instructions - Scoring
9: Instruction - Upper Section 
10: Instructions - Lower Section
11: Instructions - Scorecard
12: 1-player game
13: 2-player game
14: 3-player game
15: 4-player game
16: player name panel
18: first roll
19: roll again
20: finishTurn
21: next on scorecard panel
*/
/*
INTERNAL GEN PIC BUTTON KEY:
1: first roll
4: Player 1 image
5: Player 2 image
6: Player 3 image
7: Player 4 image
8: Player 5 image 
*/

public class YahtzeeGUI extends JFrame{
	private static JFrame frame;
	private JPanel panel, mainMenuPanel, instrPnl1, instrPnl2,instrPnl3,instrPnl4,instrPnl5, instrPnl6, instrPnl7, instrPnl8, ldrPnl, playNumPnl, firstRollPnl, otherRollPnl;
	private JPanel playNamePnl, rollPnl, scorePnl;
	private int numberOfPlayers;
    private int playerChoice;    
	private Player[] players;
	final int NUMBER_OF_ROUNDS = 22;
	final int NUMBER_OF_ROLLS = 6;
	private int playerCounter = 1;
	private int scorecardPlayerCounter = 1;
	private int rollRound = 1;
	private int gameRound = 1;
	private char[] keep = keepString(Die.numberOfDie, "n").toCharArray(); //setup to roll all dice in the first roll
	/**
    * Default constructor will create a window, and add the main menu panel
	*
	* @param N/A
    * @returns N/A
    * @throw N/A
    */
	public YahtzeeGUI() {
		createWindow(1000, 600);
		addMainMenuPanel();
		frame.add(panel);
		frame.setVisible(true);
	}
	
	/**
    * Creates the games window, and makes all of the panels new JPanels 
	*
	* @param The windows width, and the windows height
    * @returns N/A
	* @throws N/A
    */
	public void createWindow(int width, int height)
	{
		frame = new JFrame();
		frame.setTitle("Gonzaga Men's Basketball Yahtzee");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setResizable(false);
		panel = new JPanel(new BorderLayout());
		mainMenuPanel = new JPanel();
		instrPnl2 = new JPanel();
		instrPnl1 = new JPanel();
		instrPnl3 = new JPanel();
		instrPnl4 = new JPanel();
		instrPnl5 = new JPanel();
		instrPnl6 = new JPanel();
		instrPnl7 = new JPanel();
		instrPnl8 = new JPanel();
		ldrPnl = new JPanel();
		playNumPnl = new JPanel();
		playNamePnl = new JPanel();
		firstRollPnl = new JPanel();
		otherRollPnl = new JPanel();
		rollPnl = new JPanel();
        scorePnl = new JPanel();
	}
	
	/**
    * Creates the main menu panel 
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void addMainMenuPanel()
	{
		mainMenuPanel.setLayout(null);
		mainMenuPanel.setBackground(new Color(145, 200, 255));
		panel.add(mainMenuPanel, BorderLayout.CENTER);
		JLabel jlabel = new JLabel("Gonzaga Men's Basketball Yahtzee");
	    jlabel.setFont(new Font("Verdana",1,40));
	    mainMenuPanel.add(jlabel);
	    Dimension size = jlabel.getPreferredSize();
	    jlabel.setBounds(100, 100, size.width, size.height);
	    mainMenuPanel.setSize(400, 400);
	    jlabel.setLocation(105, 20);
	  
	    createGenImg("Graphics/1718PosterPhoto.jpg",900,400,50,80, mainMenuPanel);
	    // Play button
	    createGenBtn play = new createGenBtn("Play", 1, 305, 500, mainMenuPanel);
	    play.setup();
		// Leaderboard button
	    createGenBtn leaderboard = new createGenBtn("Leaderboard", 2, 445, 500, mainMenuPanel);
	    leaderboard.setup();
	    // Instructions button
	    createGenBtn instructions = new createGenBtn("Instructions", 3, 725, 500, mainMenuPanel);
		instructions.setup(); 
	}
	
	/**
    * Creates a generic button picture that implements an action listener 
	*
	* @param String name, int action, int width, int height, int x, int y, JPanel add
    * @returns N/A
	* @throws N/A
    */
	public class createGenBtnPic implements ActionListener{	
		String iconName;
		int picWidth;
		int picHeight; 
		int locX; 
		int locY;
		JButton genBtn;
		int desiredAction;
		JPanel panelAdd;
		
		public createGenBtnPic(String name, int action, int width, int height, int x, int y,JPanel add) {
			iconName = name;
			picWidth = width;
			picHeight = height;
			locX = x;
			locY = y;
			desiredAction = action;
			panelAdd = add;
		}
	
		/**
		*sets up the parameters so that the code will be implemented on the panel 
		*
		* @param N/A
		* @returns N/A
		* @throws N/A
		*/
		public void setup() {
			ImageIcon imageIcon = new ImageIcon(iconName); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(picWidth, picHeight,  java.awt.Image.SCALE_SMOOTH); 
			imageIcon = new ImageIcon(newimg);  // transform it back
			genBtn = new JButton(imageIcon);	
			panelAdd.add(genBtn);  
			Dimension sizeBtn = genBtn.getPreferredSize();
			genBtn.setBounds(locX, locY, sizeBtn.width, sizeBtn.height);
			genBtn.setBackground(new Color(145, 200, 255));
			genBtn.setOpaque(true);
			genBtn.setBorderPainted(false);
			genBtn.addActionListener(this);
	}
		
	/**
    * Makes the button exectuable 
	*
	* @param ActionEvent e
    * @returns N/A
	* @throws N/A
    */
		public void actionPerformed(ActionEvent e) {
			// if the button is pressed
			if(e.getSource() == genBtn) {
				//if the button is a basketball, roll the dice and go to the roll dice slides
				if(desiredAction == 1) {
					//Increment round
					rollDice();
					rollRound++;
					frame.getContentPane().removeAll();
					frame.getContentPane().invalidate();
					otherRollPnl(gameRound, playerCounter, rollRound);
					frame.getContentPane().add(otherRollPnl);
					frame.getContentPane().revalidate();
				}
				//if picture 1 is pressed, the user wants to keep dice 1 
				else if(desiredAction == 4){
					keep[0] = 'y';		
				}	
				//if picture 2 is pressed, the user wants to keep dice 2 
				else if(desiredAction == 5){
					keep[1] = 'y';
				}
				//if picture 3 is pressed, the user wants to keep dice 3 
				else if(desiredAction == 6){
					keep[2] = 'y';
				}
				//if picture 4 is pressed, the user wants to keep dice 4 
				else if(desiredAction == 7){
					keep[3] = 'y';
				}
				//if picture 5 is pressed, the user wants to keep dice 5 
				else if(desiredAction == 8){
					keep[4] = 'y';
				}
			}
		}
	}
	
	/**
    * Creates a generic textfield that implements an action listener 
	*
	* @param int width, int action, int play, int height, int widthLoc, int heightLoc, JPanel add
    * @returns N/A
	* @throws N/A
    */
	public class createGenTextField implements ActionListener
    {
		 int textFieldWidth;
		 int textFieldHeight; 
		 int textFieldWidthLoc; 
		 int textFieldHeightLoc;
		 JTextField textField;
		 JPanel panelAdd;
		 int playSpot;
		 JButton btn;
		 int desiredAction;
		 public createGenTextField(int play, int action,int Width, int Height, int WidthLoc, int HeightLoc, JPanel Add){
			 textFieldWidth = Width;
			 textFieldHeight = Height;
			 textFieldWidthLoc = WidthLoc;
			 textFieldHeightLoc = HeightLoc;
			 panelAdd = Add;
			 playSpot = play;
			 desiredAction = action;
			 
		 }
		/**
		* sets up the parameters so that the textField will be implemented on the panel 
		*
		* @param N/A
		* @returns N/A
		* @throws N/A
		*/
		 public void setup() {
			textField = new JTextField();
			Dimension sizeTextField = textField.getPreferredSize();
			textField.setBounds(textFieldWidthLoc, textFieldHeightLoc, sizeTextField.width, sizeTextField.height);
			textField.setSize(textFieldWidth, textFieldHeight);
			panelAdd.add(textField);          
			btn = new JButton("Submit");
			btn.setFont(new Font("Verdana",1, 20));
			panelAdd.add(btn);
			Dimension sizeBtn = btn.getPreferredSize();
			if(desiredAction == 0 ) {
				btn.setBounds(500, (playSpot-1)*45 + 175, sizeBtn.width, sizeBtn.height);
			}
			else {
				btn.setBounds(660, 524, sizeBtn.width, sizeBtn.height);
			}		  
			btn.addActionListener(this);
		 }
		/**
		* Makes the button exectuable 
		*
		* @param ActionEvent e
		* @returns N/A
		* @throws N/A
		*/
		public void actionPerformed(ActionEvent e) {
			//if the button is pressed
			if(e.getSource() == btn) {
				// if the textField is attempting to get the players name
				if(desiredAction == 0) {
					players[playSpot - 1].setName(textField.getText());
				}
				// if the textField is attempting to get the row that the user wants to score 
				else if (desiredAction == 1){
					String pC = textField.getText();
                    System.out.println(pC);
                    playerChoice = Integer.parseInt(pC);
                    playerChoice = playerChoice - 1;
					//if the users choice is not valid
            		if(((playerChoice < 0) || (playerChoice > 21)))
            		{
            			pC = textField.getText();
                        playerChoice = Integer.parseInt(pC);
                        playerChoice = playerChoice - 1;
                        
            		}
					//if the users choice has already been used up
            		else if(!players[scorecardPlayerCounter - 1].canPlaceScoreCard[playerChoice])
                    {
            			pC = textField.getText();
                        playerChoice = Integer.parseInt(pC);
                        playerChoice = playerChoice - 1;
                    }
					// if the users choice is valid continue the game
            		else {
            			createGenBtn next9 = new createGenBtn("Next", 21, 850, 490, scorePnl);
            			next9.setup();      
            		}
				}
			}
		}
    }
	
	/**
    * Creates a generic button that implements an action listener 
	*
	* @param String name, int action, int width, int height, JPanel add
    * @returns N/A
	* @throws N/A
    */
	public class createGenBtn implements ActionListener{
		String btnName; 
		int btnWidth; 
		int btnHeight; 
		int desiredAction;
		JPanel panelAdd;
		JButton btn;
		public createGenBtn(String Name, int Action, int Width, int Height, JPanel Add){
			btnName = Name;
			btnWidth = Width;
			btnHeight = Height;
			panelAdd = Add;
			desiredAction = Action;
		}
		/**
		* sets up the parameters so that the button will be implemented on the panel 
		*
		* @param N/A
		* @returns N/A
		* @throws N/A
		*/
		public void setup() {
			btn = new JButton(btnName);
			btn.setFont(new Font("Verdana",1,30));
			panelAdd.add(btn);
			Dimension sizeBtn = btn.getPreferredSize();
			btn.setBounds(btnWidth, btnHeight, sizeBtn.width, sizeBtn.height);
			btn.addActionListener(this);
		}
		/**
		* Makes the button exectuable 
		*
		* @param ActionEvent e
		* @returns N/A
		* @throws N/A
		*/
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btn) {
				//open play window
	    		if(desiredAction == 1) {
	    			//the player name window
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			addPlayNumPnl();
	    			frame.getContentPane().add(playNumPnl);
	    			frame.getContentPane().revalidate();
	    		}
	    		//open leaderboard window
	    		else if(desiredAction == 2) {
	    			//the leaderboard window
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			try {
						addLdrPnl();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
	    			frame.getContentPane().add(ldrPnl);
	    			frame.getContentPane().revalidate();
	    			
	    		}
	    		//open instructions window #1
	    		else if(desiredAction == 3) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			addInstrPnl1();
	    			frame.getContentPane().add(instrPnl1);
	    			frame.getContentPane().revalidate();
	    		}
	    		//open main menu window
	    		else  if(desiredAction == 4) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			addMainMenuPanel();
	    			frame.getContentPane().add(mainMenuPanel);
	    			frame.getContentPane().revalidate();
	    		}
	    		//open dice window
	    		else  if(desiredAction == 5) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			addInstrPnl2();
	    			frame.getContentPane().add(instrPnl2);
	    			frame.getContentPane().revalidate();
	    		}
	    		//open how to win window
	    		else  if(desiredAction == 6) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			addInstrPnl3();
	    			frame.getContentPane().add(instrPnl3);
	    			frame.getContentPane().revalidate();
	    		}
	    		//open How to play window
	    		else  if(desiredAction == 7) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			addInstrPnl4();
	    			frame.getContentPane().add(instrPnl4);
	    			frame.getContentPane().revalidate();
	    		}
	    		//open How to score window
	    		else  if(desiredAction == 8) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			addInstrPnl5();
	    			frame.getContentPane().add(instrPnl5);
	    			frame.getContentPane().revalidate();
	    		}
	    		//open Upper Section window
	    		else  if(desiredAction == 9) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			addInstrPnl6();
	    			frame.getContentPane().add(instrPnl6);
	    			frame.getContentPane().revalidate();
	    		}
	    		//open Lower Section window
	    		else  if(desiredAction == 10) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			addInstrPnl7();
	    			frame.getContentPane().add(instrPnl7);
	    			frame.getContentPane().revalidate();
	    		}
	    		//open Scorecard window
	    		else  if(desiredAction == 11) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			addInstrPnl8();
	    			frame.getContentPane().add(instrPnl8);
	    			frame.getContentPane().revalidate();
	    		}
	    		//1-player desired
	    		else  if(desiredAction == 12) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			numberOfPlayers = 1;
	    			addPlayNamePnl();
	    			frame.getContentPane().add(playNamePnl);
	    			frame.getContentPane().revalidate();
	    		}
	    		//2-player desired
	    		else  if(desiredAction == 13) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			numberOfPlayers = 2;
	    			addPlayNamePnl();
	    			frame.getContentPane().add(playNamePnl);
	    			frame.getContentPane().revalidate();
	    		}
	    		//3-player desired
	    		else  if(desiredAction == 14) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			numberOfPlayers = 3;
	    			addPlayNamePnl();
	    			frame.getContentPane().add(playNamePnl);
	    			frame.getContentPane().revalidate();
	    		}
	    		//4-player desired
	    		else  if(desiredAction == 15) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			numberOfPlayers = 4;
	    			addPlayNamePnl();
	    			frame.getContentPane().add(playNamePnl);
	    			frame.getContentPane().revalidate();
	    		}
	    		//the player Name panel
	    		else  if(desiredAction == 16) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			addPlayNamePnl();
	    			frame.getContentPane().add(playNamePnl);
	    			frame.getContentPane().revalidate();
	    		}
				//the first roll panel
	    		else if(desiredAction == 18) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().invalidate();
                    firstRollPnl(gameRound, playerCounter, rollRound);
                    frame.getContentPane().add(firstRollPnl);
                    frame.getContentPane().revalidate();
	    		}
				// the roll again panel
	    		else if(desiredAction == 19) {
	    			frame.getContentPane().removeAll();
                    frame.getContentPane().invalidate();
                    modifySettings();
                    //RESET keep array to roll all dice
                    keep = keepString(Die.numberOfDie, "n").toCharArray();
					otherRollPnl(gameRound, playerCounter, rollRound);
					frame.getContentPane().add(otherRollPnl);
                    frame.getContentPane().revalidate();
	    		}
				// the score panel
	    		else if(desiredAction == 20) {
	    			frame.getContentPane().removeAll();
                    frame.getContentPane().invalidate();                    
                    //If finishTurn is clicked...
                    rollRound = 6;     
                    //RESET keep array to roll all dice
                    keep = keepString(Die.numberOfDie, "n").toCharArray();   
                    sortArray(players[scorecardPlayerCounter - 1].hand, Die.numberOfDie);           
                    scorePnl();                
					frame.getContentPane().add(scorePnl);
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
	    		}
				// the final scoreboard panel
	    		else if(desiredAction == 21)
                {
			        scoreCardTrackerGUI(players[scorecardPlayerCounter - 1].hand);
			        if(scorecardPlayerCounter == numberOfPlayers){
			        	scorecardPlayerCounter = 1;
                    }
			        else{
			        	scorecardPlayerCounter++;
			        }			        
                    frame.getContentPane().removeAll();
                    frame.getContentPane().invalidate();                
                    int flag = updateFlag();
                    if(flag == 1)
                    {
                    	try {
							checkLeaderboard();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
                        rollPnl();
                        frame.getContentPane().add(rollPnl);
                    }
                    else{
                        rollRound = 1;
                        if((playerCounter == numberOfPlayers))
                        {
                            playerCounter = 1;
                            if(!(gameRound == NUMBER_OF_ROUNDS)) {
								gameRound++;
                            }
                        }
                        else
                        {
                            playerCounter++;
                        }
						firstRollPnl(gameRound, playerCounter, rollRound);
						frame.getContentPane().add(firstRollPnl);
                    }
					frame.getContentPane().revalidate();
                }
				//exit game
	    		else if(desiredAction == 99) {
	    			System.exit(0);
	    		}
	    	}
		}
	}	
	
	/**
    * This method implements the first part of the instructions 
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void addInstrPnl1()
	{
		instrPnl1.setLayout(null);
		instrPnl1.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl1, BorderLayout.CENTER);
	   // Instructions title
	    createGenLabel("Instructions", 200, 200, 40, Color.black, instrPnl1);
		// Next button
	    createGenBtn next1 = new createGenBtn("Next", 5, 560, 500, instrPnl1);
	    next1.setup();
	    // Main Menu button
	    createGenBtn mainMenu1 = new createGenBtn("Main Menu", 4, 725, 500, instrPnl1);
	    mainMenu1.setup();
	}
	
	/**
    * This method implements the second part of instructions
	* the dice instructions
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void addInstrPnl2()
	{
		instrPnl2.setLayout(null);
		instrPnl2.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl2, BorderLayout.CENTER);
		//Dice title
	    createGenLabel("Dice", 100, 50, 40, Color.black, instrPnl2);
	    //Dice Instructions
	    createGenLabel("*The game uses five, fifteen-sided dice.", 25, 200, 15, Color.black, instrPnl2);
	    createGenLabel("*The sides of each die represent the GU", 25, 225, 15, Color.black, instrPnl2);
	    createGenLabel("  Men’s Basketball 2017-2018 Roster:", 25, 245, 15, Color.black, instrPnl2);
		// Next button
	    createGenBtn next2 = new createGenBtn("Next",6, 560, 500, instrPnl2);
	    next2.setup();
	    // Main Menu button
	    createGenBtn mainMenu2 = new createGenBtn("Main Menu", 4, 725, 500, instrPnl2);
	    mainMenu2.setup();
	    // Roster image
	    createGenImg("Graphics/RosterScreenshot.png", 465, 465, 450, 25, instrPnl2);
	}
	
	/**
    * This method implements the third part of instructions
	* the how to win instructions
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void addInstrPnl3()
	{
		instrPnl3.setLayout(null);
		instrPnl3.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl3, BorderLayout.CENTER);
		// How to Win title
	    createGenLabel("How to Win", 340, 30, 50, Color.black, instrPnl3);
	    // Tells user how to win
	    createGenLabel("*Roll dice 5 times to get the highest score", 25, 200, 15, Color.black, instrPnl3);
	    createGenLabel("  after all 22 rounds.", 25, 225, 15, Color.black, instrPnl3);
	    // Next button
	    createGenBtn  Next3 = new createGenBtn("Next", 7, 560, 500, instrPnl3);
	    Next3.setup();
	    // Main Menu button
	    createGenBtn mainMenu3 = new createGenBtn("Main Menu", 4, 725, 500, instrPnl3);
	    mainMenu3.setup();
	}
	
	/**
    * This method implements the fourth part of instructions
	* the how to play instructions
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void addInstrPnl4()
	{
		instrPnl4.setLayout(null);
		instrPnl4.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl4, BorderLayout.CENTER);
		// How to Play title
	    createGenLabel("How to Play", 340, 30, 50, Color.black, instrPnl4);
	    // Tells user how to play
	    createGenLabel("To start, you will roll the dice by clicking the basketball image. After rolling you can either score the current", 25, 100, 15, Color.black, instrPnl4);
	    createGenLabel("roll on the scorecard by clicking 'Finish Turn' or re-roll any or all of the dice*. You may only roll the dice a", 25, 115, 15, Color.black, instrPnl4);
	    createGenLabel("total of five times. After the fifth roll you must choose a line on the scorecard to score your hand in. ", 25, 130, 15, Color.black, instrPnl4);
	    createGenLabel("You must choose a different line each time (i.e., you CANNOT score your hand in a line you have already scored.)", 25, 145, 15, Color.black, instrPnl4);
	    createGenLabel("To keep a player, click their picture. Not clicking a picture will cause that die to be rerolled. For example,", 25, 175, 15, Color.black, instrPnl4);
	    createGenLabel("if the roll is Jones, Hachimura, Norvell, Tillie, Perkins to keep Jones, Norvell, and Tillie and re-roll the", 25, 190, 15, Color.black, instrPnl4);
	    createGenLabel("others you would: click, not click, click, click, and not click.", 25, 205, 15, Color.black, instrPnl4);
	    createGenLabel("*Note: For example, after the first roll you can keep 4 dice and re-roll the fifth die. Then you can", 25, 235, 15, Color.black, instrPnl4);
	    createGenLabel("choose to keep all of the die and end that round or keep the first 2 die and roll the next 3 and", 25, 250, 15, Color.black, instrPnl4);
	    createGenLabel("continue...", 25, 265, 15, Color.black, instrPnl4);	    
		// Next button
	    createGenBtn Next4 = new createGenBtn("Next", 8, 560, 500, instrPnl4);
	    Next4.setup();
	    // Main Menu button
	    createGenBtn MainMenu4 = new createGenBtn("Main Menu",4, 725, 500, instrPnl4);
	    MainMenu4.setup();
	}
	
	/**
    * This method implements the fifth part of instructions
	* the scoring instructions
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void addInstrPnl5()
	{
		instrPnl5.setLayout(null);
		instrPnl5.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl5, BorderLayout.CENTER);
		// Scoring title
	    createGenLabel("Scoring", 340, 30, 50, Color.black, instrPnl5);
	    // Tells user how the scorecard works
	    createGenLabel("*The scorecard is split into upper and lower", 25, 200, 15, Color.black, instrPnl5);
	    createGenLabel("  sections.", 25, 225, 15, Color.black, instrPnl5);
	    // Next button
	    createGenBtn Next5 = new createGenBtn("Next", 9, 560, 500, instrPnl5);
	    Next5.setup();
	    // Main Menu button
	    createGenBtn MainMenu5 = new createGenBtn("Main Menu", 4, 725, 500, instrPnl5);
	    MainMenu5.setup();
	}
	
	/**
    * This method implements the sixth part of instructions
	* the upper section instructions
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void addInstrPnl6()
	{
		instrPnl6.setLayout(null);
		instrPnl6.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl6, BorderLayout.CENTER);
		// Upper Section title
	    createGenLabel("Upper Section", 340, 30, 50, Color.black, instrPnl6);
	    // Tells user how the upper section is scored
	    createGenLabel("*Each line in the upper section is calculated", 25, 200, 15, Color.black, instrPnl6);
	    createGenLabel("by the number of the particular player rolled ", 25, 215, 15, Color.black, instrPnl6);
	    createGenLabel("multipled by its scaling factor (1-15) as", 25, 230, 15, Color.black, instrPnl6);
	    createGenLabel("outlined on the scorecard. For example, two", 25, 245, 15, Color.black, instrPnl6);
	    createGenLabel("Brian Pete's in a hand would be of value 24", 25, 260, 15, Color.black, instrPnl6);
	    createGenLabel("(2 x 12 = 24).", 25, 275, 15, Color.black, instrPnl6);
		// Next button
	    createGenBtn Next6 = new createGenBtn("Next", 10, 560, 500, instrPnl6);
	    Next6.setup();
	    // Main Menu button
	    createGenBtn MainMenu6 = new createGenBtn("Main Menu",4, 725, 500, instrPnl6);
	    MainMenu6.setup();
	}
	
	/**
    * This method implements the seventh part of instructions
	* the lower section instructions
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void addInstrPnl7()
	{
		instrPnl7.setLayout(null);
		instrPnl7.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl7, BorderLayout.CENTER);
		//Lower Section title
	    createGenLabel("Lower Section", 340, 30, 50, Color.black, instrPnl7);
	    //Tells user how the lower section is scored
	    createGenLabel("*3 of a Zag ", 25, 100, 15, Color.black, instrPnl7);
	    createGenLabel("  A player must have 3 of the same player in the hand.", 25, 115, 15, Color.black, instrPnl7);
	    createGenLabel("  The score is the value of all 5-dice added up.", 25, 130, 15, Color.black, instrPnl7);
	    createGenLabel("*4 of a Zag", 25, 160, 15, Color.black, instrPnl7);
	    createGenLabel("  A player must have 4 of the same player in the hand.", 25, 175, 15, Color.black, instrPnl7);
	    createGenLabel("  The score is the value of all 5-dice added up.", 25, 190, 15, Color.black, instrPnl7);
	    createGenLabel("*Full Team", 25, 220, 15, Color.black, instrPnl7);
	    createGenLabel("  A player must have 3 Guards and 2 Forwards to accomplish this score.", 25, 235, 15, Color.black, instrPnl7);
	    createGenLabel("  The score will be 25 minutes, regardless of the players number values.", 25, 250, 15, Color.black, instrPnl7);
	    createGenLabel("*The Bench Brigade", 25, 280, 15, Color.black, instrPnl7);
	    createGenLabel("  A player must have 5 players from the Bench to accomplish this score.", 25, 295, 15, Color.black, instrPnl7);
	    createGenLabel("  The score will be 30 points, regardless of players' number values.", 25, 310, 15, Color.black, instrPnl7);
	    createGenLabel("*The Starters", 25, 340, 15, Color.black, instrPnl7);
	    createGenLabel("  A player must have all 5 Starters to accomplish this score.", 25, 355, 15, Color.black, instrPnl7);
	    createGenLabel("  The score will be 40 points, regardless of players' number values.", 25, 370, 15, Color.black, instrPnl7);
	    createGenLabel("*The Kennel", 25, 400, 15, Color.black, instrPnl7);
	    createGenLabel("  No combos necessary. The score of this roll will be all the players'", 25, 415, 15, Color.black, instrPnl7);
	    createGenLabel("  numbers totaled together.", 25, 430, 15, Color.black, instrPnl7);
	    createGenLabel("*Zombie Nation", 25, 460, 15, Color.black, instrPnl7);
	    createGenLabel("  A player must have 5 of the same player to accomplish this score.", 25, 475, 15, Color.black, instrPnl7);
	    createGenLabel("  The score will be 100 points, regardless of", 25, 490, 15, Color.black, instrPnl7);
	    createGenLabel("  the players' number values.", 25, 505, 15, Color.black, instrPnl7);
		// Next button
	    createGenBtn Next7= new createGenBtn("Next", 11, 560, 500, instrPnl7);
	    Next7.setup();
	    // Main Menu button
	    createGenBtn MainMenu7 = new createGenBtn("Main Menu",4, 725, 500, instrPnl7);
	    MainMenu7.setup();
	}
	
	/**
    * This method implements the eigth part of instructions
	* the scorecard
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void addInstrPnl8()
	{
		instrPnl8.setLayout(null);
		instrPnl8.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl8, BorderLayout.CENTER);
		// Upper Section title
	    createGenLabel("Scorecard", 600, 30, 50, Color.black, instrPnl8);
	    // Scorecard Screenshot
	    createGenImg("Graphics/ScorecardScreenshot.png", 500, 525, 25, 20, instrPnl8);
	    // Main Menu button
	    createGenBtn MainMenu8 = new createGenBtn("Main Menu", 4, 725, 500, instrPnl8);
	    MainMenu8.setup();
	}
	
	/**
    * This method implements leaderboard panel
	*
	* @param N/A
    * @returns N/A
	* @throws FileNotFoundException
    */
	public void addLdrPnl() throws FileNotFoundException
	{
		ldrPnl.setLayout(null);
		ldrPnl.setBackground(new Color(145, 200, 255));
		panel.add(ldrPnl, BorderLayout.CENTER);
		// Leaderboard title
	    createGenLabel("Leaderboard", 310, 30, 50, Color.black, ldrPnl);
	    final int MAX_PLAYER_DISPLAY_LEADERBOARD = 3;
	    Scanner inputLeader = new Scanner(new File("leaderboard.txt"));
		// if there is nothing in the file, no games have been played
	    if(!inputLeader.hasNext())
	    {
	    		createGenLabel("There is currenty no one on the leaderboard!", 100, 120,25,Color.black, ldrPnl);
	    		createGenLabel("Play a game to be featured!", 100, 180, 25,Color.black, ldrPnl);
        }
		//load the file, the top scores and corresponding player names
	    else
	    {
			for(int i = 1; inputLeader.hasNext() && i <= MAX_PLAYER_DISPLAY_LEADERBOARD; i++)
			{
	    		StringBuilder playerPositionSB = new StringBuilder();
	    		playerPositionSB.append("# ");
	    		String iStr = Integer.toString(i);
        		playerPositionSB.append(iStr);
        		String curPlayerPosition = playerPositionSB.toString();
        		int curPlayerScoreInt = inputLeader.nextInt();
        		String curPlayerScoreVal = Integer.toString(curPlayerScoreInt);
        		StringBuilder curPlayerScoreSB = new StringBuilder();
        		curPlayerScoreSB.append(curPlayerScoreVal);
        		curPlayerScoreSB.append(" points");
        		String curPlayerScore = curPlayerScoreSB.toString();
        		String curPlayerName = inputLeader.next();
				//printing the players name and score onto the file
        		createGenLabel(curPlayerPosition, 100, 120+(i-1)*60, 25, Color.black, ldrPnl);
            	createGenLabel(curPlayerName, 400, 120+(i-1)*60, 25, Color.black, ldrPnl);
            	createGenLabel(curPlayerScore, 700, 120+(i-1)*60, 25, Color.black, ldrPnl);
			}
	    }
		// Main Menu button
	    createGenBtn MainMenu9 = new createGenBtn("Main Menu", 4, 725, 500, ldrPnl);
	    MainMenu9.setup();
	}
	
	/**
    * This method implements adding the amount of players that are going to play the Yahtzee game
	* The maximum number of players is 4 and the minimum is 1
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void addPlayNumPnl(){
	    playNumPnl.setLayout(null);
	    playNumPnl.setBackground(new Color(145, 200, 255));
	    panel.add(playNumPnl, BorderLayout.CENTER);
	    // how many players
	    createGenLabel("How many players?", 220, 30, 50, Color.black, playNumPnl);
	    // 1
	    createGenBtn numPlayers1 = new createGenBtn("1", 12, 300, 200, playNumPnl);
	    numPlayers1.setup();
	    // 2
	    createGenBtn numPlayers2 = new createGenBtn("2", 13, 600, 200, playNumPnl);
	    numPlayers2.setup();
	    // 3
	    createGenBtn numPlayers3 = new createGenBtn("3", 14, 300, 400, playNumPnl);
	    numPlayers3.setup();
	    // 4
	    createGenBtn numPlayers4 = new createGenBtn("4", 15, 600, 400, playNumPnl);
	    numPlayers4.setup();
	}
	
	/**
    * This method implements adding the players names that are going to play the Yahtzee game
	* The maximum number of players is 4 and the minimum is 1
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void addPlayNamePnl(){
	    playNamePnl.setLayout(null);
	    playNamePnl.setBackground(new Color(145, 200, 255));
	    panel.add(playNamePnl, BorderLayout.CENTER);
	    // ENTER PLAYER NAMES PANEL
	    players = new Player[numberOfPlayers];
		//for every player that we have...
        for(int i = 1; i <= numberOfPlayers; i++)
        {	
			//create a label that says "Player #i: "
            createGenLabel("Player #" + i + ":", 150, (i-1)*45 + 175, 20, Color.black, playNamePnl);
            //create a genTextField that asks for users name as input, and button to submit name
			createGenTextField userNames = new createGenTextField(i, 0, 200, 30, 275, (i-1)*45 + 175, playNamePnl);
            userNames.setup();
			//add player to players array
            players[i-1] = new Player();
        }
        // Upper Section title
		createGenLabel("Enter player name(s)", 200, 30, 50, Color.black, playNamePnl);
		createGenLabel("No spaces allowed!", 365, 100, 25, Color.black, playNamePnl);
        // Next button
		createGenBtn Next8= new createGenBtn("Next", 18, 725, 500, playNamePnl);
		Next8.setup();
	}
	
	/**
    * This method implements the first roll panel to the file, this is the basketball picture
	* when the basketball picture is clicked, roll the dice and go to the next slide
	*
	* @param int numRound, int numPlayer, int numRoll
    * @returns N/A
	* @throws N/A
    */
	public void firstRollPnl(int numRound, int numPlayer, int numRoll){
		firstRollPnl.setLayout(null);
		firstRollPnl.setBackground(new Color(145, 200, 255));
	    panel.add(firstRollPnl, BorderLayout.CENTER);
	    //keep array is all 'n'
	    createGenModLabel("Round " + numRound + ": Roll " + numRoll + " for " + players[numPlayer - 1].getName(), 220, 80, 30, Color.black, firstRollPnl);
	    createGenLabel("Press the ball to roll!", 220, 30, 50, Color.black, firstRollPnl);
	    //the first roll button
	    createGenBtnPic basketball  = new createGenBtnPic("Graphics/Basketball.png", 1,300, 300, 350, 150, firstRollPnl);
	    	basketball.setup();
	}
	
	/**
    * This method creates a generic label that is used throughout the program to put text onto a specific panel
	*
	* @param String dispText, int textWidth, int textHeight, int textSize, Color color, JPanel panelAdd
    * @returns N/A
	* @throws N/A
    */
	public void createGenLabel(String dispText, int textWidth, int textHeight, int textSize, Color color ,JPanel panelAdd)
	{
		JLabel text = new JLabel(dispText);
	    text.setFont(new Font("Verdana",1,textSize));
		panelAdd.add(text);
	    Dimension sizeText = text.getPreferredSize();
	    text.setBounds(textWidth, textHeight, sizeText.width, sizeText.height);
	    text.setForeground(color);
	    panelAdd.setSize(400, 400);
	}
	
	/**
    * This method creates a generic modified label that is used throughout the program to put text onto a specific panel
	* This code is modifiable so that if a panel is called more than once, and variables change, the output is not overwriting previous output
	*
	* @param String dispText, int textWidth, int textHeight, int textSize, Color color, JPanel panelAdd
    * @returns N/A
	* @throws N/A
    */
	public void createGenModLabel(String dispText, int textWidth, int textHeight, int textSize, Color color ,JPanel panelAdd)
	{
		JLabel text = new JLabel(dispText);
	    text.setFont(new Font("Verdana",1,textSize));
	    panelAdd.removeAll();
		panelAdd.invalidate();
		panelAdd.add(text);
		panelAdd.revalidate();
	    Dimension sizeText = text.getPreferredSize();
	    text.setBounds(textWidth, textHeight, sizeText.width, sizeText.height);
	    text.setForeground(color);
	    panelAdd.setSize(400, 400);
	}
	
	/**
    * This method creates a generic image that is used throughout the program to put pictures onto a specific panel 
	*
	* @param String imgPath, int imgWidth, int imgHeight, int imgWidthLoc, int imgHeightLoc, JPanel panelAdd
    * @returns N/A
	* @throws IOException
    */
	public void createGenImg(String imgPath, int imgWidth, int imgHeight, int imgWidthLoc, int imgHeightLoc, JPanel panelAdd)
	{
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = myPicture.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
	    JLabel picLabel = new JLabel(new ImageIcon(dimg));
	    panelAdd.add(picLabel);
	    Dimension sizePic = picLabel.getPreferredSize();
	    picLabel.setBounds(imgWidthLoc, imgHeightLoc, sizePic.width, sizePic.height);
	}
	
	/**
    * This method implements the final panel of the game, when the game is over, this slide displays the final scores for each player
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void rollPnl(){
		rollPnl.setLayout(null);
		rollPnl.setBackground(new Color(145, 200, 255));
	    panel.add(rollPnl, BorderLayout.CENTER);
	    createGenLabel("Thank you for playing!", 240, 30, 40, Color.black, rollPnl);
	    createGenLabel("Final game scores:", 305, 100, 35, Color.black, rollPnl);
	    createGenLabel("Player name", 318, 150, 25, Color.black, rollPnl);
	    createGenLabel("Score", 575, 150, 25, Color.black, rollPnl);
	    createGenLabel("---------------", 318, 165, 25, Color.black, rollPnl);
	    createGenLabel("-------", 575, 165, 25, Color.black, rollPnl);
	    //From player 1 to number of players write to the panel the players names and scores;
        for(int i = 0; i < numberOfPlayers; i++)
        {
            String curPlayerTotalValueStr = Integer.toString(players[i].totalValue());
            createGenLabel(players[i].getName(), 318, 190 + 35 * i, 25, Color.black, rollPnl);
            createGenLabel(curPlayerTotalValueStr, 575, 190 + 35 * i, 25, Color.black, rollPnl);
        }
        createGenLabel("To see if you made it on the leaderboard or to play again,", 90, 425, 25, Color.black, rollPnl);
	    createGenLabel("click Exit and reload the game! ", 255, 450, 25, Color.black, rollPnl);	  
	    createGenBtn Exit= new createGenBtn("Exit", 99, 850, 500, rollPnl);
	    Exit.setup();
	}
	
	/**
    * This method implements the rolling panel
	* from rolls 2 to 5
	* @param int numRound, int numPlayer, int numRoll
    * @returns N/A
	* @throws N/A
    */
	public void otherRollPnl(int numRound, int numPlayer, int numRoll){
		otherRollPnl.setLayout(null);
		otherRollPnl.setBackground(new Color(145, 200, 255));
	    panel.add(otherRollPnl, BorderLayout.CENTER);
		// if this isnt the last roll, tell the user to select players they want to keep
	    if(numRoll != 6) {
			System.out.println("Round " + numRound + ": Roll " + (numRoll-1) + " for " + players[numPlayer - 1].getName());
			createGenModLabel(players[numPlayer - 1].getName() + ", select the players you wish to", 30, 25, 40, Color.black, otherRollPnl);
			createGenLabel("keep from roll " + (numRoll-1) + ":" , 30, 75, 40, Color.black, otherRollPnl);
	    }
		//else tell the user which players they rolled
	    else
	    {
	    	createGenModLabel(players[numPlayer - 1].getName() + ", here are the players you rolled", 30, 25, 40, Color.black, otherRollPnl);
			createGenLabel("in roll 5:", 30, 75, 40, Color.black, otherRollPnl);
	    }
	    createGenBtnPic p1Img  = new createGenBtnPic(dieRoll(0), 4,162, 220, 25, 150, otherRollPnl);
	    p1Img.setup();
	    createGenBtnPic p2Img  = new createGenBtnPic(dieRoll(1), 5,162, 220, 218, 150, otherRollPnl);
	    p2Img.setup();
	    createGenBtnPic p3Img  = new createGenBtnPic(dieRoll(2), 6,162, 220, 412, 150, otherRollPnl);
	    p3Img.setup();
	    createGenBtnPic p4Img  = new createGenBtnPic(dieRoll(3), 7,162, 220, 606, 150, otherRollPnl);
	    p4Img.setup();
	    createGenBtnPic p5Img  = new createGenBtnPic(dieRoll(4), 8,162, 220, 799, 150, otherRollPnl);
	    p5Img.setup();
        //If the number of rolls is not 6 give the player the option to roll again
	    if(numRoll != 6) {
	    	createGenBtn rollAgain = new createGenBtn("Roll Again", 19, 500, 500, otherRollPnl);
	    	rollAgain.setup();
	    }
        createGenBtn finishTurn = new createGenBtn("Finish Turn", 20, 725, 500, otherRollPnl);
        finishTurn.setup();	    
	}
	
	/**
    * This method implements the scoring panel
	* the user selects which row they want to score on, after being given all the eligble scoring lines
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
    public void scorePnl()
    {
    	scorePnl.setLayout(null);
		scorePnl.setBackground(new Color(145, 200, 255));
	    panel.add(scorePnl, BorderLayout.CENTER);
    	printUpperGUI();
    	printLowerGUI();
    	//output current score
    	createGenLabel(players[scorecardPlayerCounter - 1].getName() + ", here are the values you are eligible to score by line...", 5, 2, 25, Color.black, scorePnl);
    	createGenLabel("Your current score (before scoring this round) is: " + players[scorecardPlayerCounter - 1].getCurrentScore(), 5, 25, 25, Color.black, scorePnl);
    	//output asking user to enter line 
		createGenLabel("Enter the line", 590, 460, 20, Color.red, scorePnl);
    	createGenLabel("you wish to score below:", 525, 485, 20, Color.red, scorePnl);
        createGenTextField scoreNumber = new createGenTextField(1, 1, 100, 30, 550, 525, scorePnl);
        scoreNumber.setup();
    }
    
    /**
    * This method creates a new Die and rolls the die
	*
	* @param N/A
    * @returns Die
	* @throws N/A
    */
	public static Die rollDie()
	{
		Die newDie = new Die();
		newDie.roll();
		return newDie;
	}
	
	/**
    * This method modifies the settings of the game, and if eligible will put dice into the users hand
	*
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void modifySettings()
	{
		//if roll round is 6, we need to make roll round 1 and go to firstRollPnl 
		if(rollRound == NUMBER_OF_ROLLS) 
		{
			rollRound = 1;
			//if the players number is equal to the number of players its the first players turn again
			if(playerCounter == numberOfPlayers) 
			{
				playerCounter = 1;
				// if the game round is not 22 then go to the next round
				if(!(gameRound == NUMBER_OF_ROUNDS)) 
				{
					gameRound++;
				}
			}
			// since player counter is not yet 4 its the next players turn
			else 
			{
				playerCounter++;
			}
		}
		//since roll round is not 6 add 1 to roll
		else {
			for(int dieNumber = 0; dieNumber < 5; dieNumber++)
			{
				if(keep[dieNumber] != 'y') //ROLL the dice!
				{
					if(rollRound == 1)
						players[playerCounter - 1].hand.add(dieNumber, rollDie());
					else
						players[playerCounter - 1].hand.set(dieNumber, rollDie());
				}
			}
			rollRound++;
		}
	}
	
	/**
    * This method checks to see if the game is over
	*
	* @param N/A
    * @returns 0 if game is not over 1 if game is over
	* @throws N/A
    */
    public int updateFlag()
    {
        int desiredAction = 0;
		if(rollRound >= NUMBER_OF_ROLLS && gameRound >= NUMBER_OF_ROUNDS && playerCounter >= numberOfPlayers) {
			 desiredAction = 1;
		}
		return desiredAction;
    }
    
	/**
    * This method checks to see which Dice are going to be kept
	*
	* @param int numberOfDie, String initializeVal
    * @returns String keeps
	* @throws N/A
    */
	public static String keepString(int numberOfDie, String initializeVal)
	{
		String keeps = "";
		for(int i = 0; i < numberOfDie; i++)
		{
			keeps = keeps + initializeVal;
		}
		return keeps;
	}
	
	/**
    * Returns the player that was rolled in form of a .jpeg
	*
	* @param int dieNumber
    * @returns String dieRoll
	* @throws N/A
    */
	public String dieRoll(int dieNumber)
	{
        return "Graphics/" + players[playerCounter - 1].hand.get(dieNumber).getName() + ".jpeg";
	}
	
	/**
    * Rolls the dice
	*
	* @param N/a
    * @returns N/a
	* @throws N/A
    */
    public void rollDice()
    {
        for(int dieNumber = 0; dieNumber < 5; dieNumber++)
		{
            if(keep[dieNumber] != 'y') //ROLL the dice!
			{
				if(rollRound == 1)
					players[playerCounter - 1].hand.add(dieNumber, rollDie());
				else
					players[playerCounter - 1].hand.set(dieNumber, rollDie());
			}
		}
    }
    
	/**
    * This method sorts the hand of our dice
	*
	* @param N/A
    * @returns ArrayList<Die> hand, int size
	* @throws N/A
    */
	public static void sortArray(ArrayList<Die> hand, int size)
	{
	   boolean swap;
	   Die temp = new Die();
	   do
	   {
		  swap = false;
		  for (int count = 0; count < (size - 1); count++)
		  {
			 if (hand.get(count).getValue() > hand.get(count + 1).getValue())
			 {
				try{
					temp = hand.get(count).clone();		// temp = array[count]
				}
				catch(CloneNotSupportedException e){
					e.printStackTrace();
				}
					
				hand.set(count, hand.get(count + 1)); 		// array[count] = array[count + 1]
				hand.set(count + 1, temp);
				swap = true;
			 }
		  }
	   } while (swap);
	}
	
	/**
    * Places the value to be stored in the correct place in the Upper section
	* of the ScoreCard
	* @param ArrayList<Die> has been filled with Dice.  
    * @returns N/A
	* @throws N/A
    */
    public void printUpperGUI()
    {
    	String output = "";
    	char modLabel = 'y';
	    for (int dieValue = 1; dieValue <= Die.numberOfSides; dieValue++)
		{
			int currentCount = 0;
			for (int diePosition = 0; diePosition < Die.numberOfDie; diePosition++)
			{
				if (players[scorecardPlayerCounter - 1].hand.get(diePosition).getValue() == dieValue)
					currentCount++;
			}
			if(players[scorecardPlayerCounter - 1].canPrint(dieValue - 1))
			{
				output = "Line " + (dieValue) + ": " + "Score is " + dieValue * currentCount + " on the " + players[0].athleteNames[dieValue - 1] + " line";
				if(modLabel == 'y')
				{
					createGenModLabel(output, 50, 20*dieValue + 50, 15, Color.black, scorePnl);
					modLabel = 'n';
				}
				else
					createGenLabel(output, 50, 20*dieValue + 50, 15, Color.black, scorePnl);
			}
		}	
    }
    
	/**
    * Places the value to be stored in the correct place in the Lower section
	* of the ScoreCard
	* @param ArrayList<Die> has been filled with Dice.  
    * @returns N/A
	* @throws N/A
    */
	public void printLowerGUI()
	{
		String output = "";
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides))
		{
			if (Calculations.maxOfAZagFound(players[scorecardPlayerCounter - 1].hand) >= 3)
			{
				output = "Line " + (Die.numberOfSides +1) + ": " + "Score " + Calculations.totalAllDice(players[scorecardPlayerCounter - 1].hand) + " on the ";
				output = output + "3 of a Zag line";
				
			}
			else 
				output = "Line " + (Die.numberOfSides+1) + ": " + "Score 0 on the 3 of a Zag line";
			createGenLabel(output, 50, 320 + 50, 15, Color.black, scorePnl);
		}
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 1))
		{
			if (Calculations.maxOfAZagFound(players[scorecardPlayerCounter - 1].hand) >= 4)
			{
				output = "Line " + (Die.numberOfSides + 2) + ": " + "Score " + Calculations.totalAllDice(players[scorecardPlayerCounter - 1].hand) + " on the ";
				output = output + "4 of a Zag line";
			}
			else 
				output = "Line " + (Die.numberOfSides + 2) + ": " + "Score 0 on the 4 of a Zag line";
			createGenLabel(output, 50, 340 + 50, 15, Color.black, scorePnl);
		}
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 2))
		{
			if (Calculations.fullTeamFound(players[scorecardPlayerCounter - 1].hand))
				output = "Line " + (Die.numberOfSides + 3) + ": " + "Score 25 on the Full Team line";
			else
				output = "Line " + (Die.numberOfSides + 3) + ": " + "Score 0 on the Full Team line";
			createGenLabel(output, 50, 360 + 50, 15, Color.black, scorePnl);
		}
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 3))
		{
			if (Calculations.benchBrigadeFound(players[scorecardPlayerCounter - 1].hand))
				output = "Line " + (Die.numberOfSides + 4) + ": " + "Score 30 on the Bench Brigade line";
			else
				output = "Line " + (Die.numberOfSides + 4) + ": " + "Score 0 on the Bench Brigade line";
			createGenLabel(output, 50, 380 + 50, 15, Color.black, scorePnl);
		}
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 4))
		{
			if (Calculations.startingLineupFound(players[scorecardPlayerCounter - 1].hand))
				output = "Line " + (Die.numberOfSides + 5) + ": " + "Score 40 on the Starters line";
			else
				output = "Line " + (Die.numberOfSides + 5) + ": " + "Score 0 on the Starters line";
			createGenLabel(output, 50, 400 + 50, 15, Color.black, scorePnl);
		}
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 5))
		{
			if (Calculations.maxOfAZagFound(players[scorecardPlayerCounter - 1].hand) >= 5)
				output = "Line " + (Die.numberOfSides + 6) + ": " + "Score 100 on the Zombie Nation line";
			else
				output = "Line " + (Die.numberOfSides + 6) + ": " + "Score 0 on the Zombie Nation line";
			createGenLabel(output, 50, 420 + 50, 15, Color.black, scorePnl);
		}
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 6))
		{
			output = "Line " + (Die.numberOfSides + 7) + ": " + "Score " + Calculations.totalAllDice(players[scorecardPlayerCounter - 1].hand) + " on the ";
			output = output + "Kennel line";
			createGenLabel(output, 50, 440 + 50, 15, Color.black, scorePnl);
		}
	}
	
	/**
    * Checks if the player has placed the hand in the desired scoring spot, and if not place the hand in the desired scoring spot 
	*
	* @param ArrayList<Die> has been filled with Dice.  
    * @returns N/A
	* @throws N/A
    */
	public void scoreCardTrackerGUI(ArrayList<Die> hand)
	{
		players[scorecardPlayerCounter - 1].scoredRowNumber = playerChoice;
		//Now we want to place the value!
		if((players[scorecardPlayerCounter - 1].scoredRowNumber) >= Die.numberOfSides)
		{
			players[scorecardPlayerCounter - 1].placeLower(hand);
		}
		else
		{
			players[scorecardPlayerCounter - 1].placeUpper(hand, players[scorecardPlayerCounter - 1].scoredRowNumber);
		}
		players[scorecardPlayerCounter - 1].canPlaceScoreCard[players[scorecardPlayerCounter - 1].scoredRowNumber] = false; 
	}
	
	/**
    * Updates the leaderboard and overwrites values on Leaderboard.txt
	*
	* @param N/A 
    * @returns N/A
	* @throws FileNotFoundException
    */
    public void checkLeaderboard() throws FileNotFoundException{
        int numberOfLeaders;
        int iterator = 3;
        int nextInt;
        String nextName;
        Map<Integer, String> gameScoreNames = new HashMap<Integer, String>();
        for(int i = 0; i < numberOfPlayers; i++)
        {
            gameScoreNames.put(players[i].totalValue(), players[i].getName());
        }
		Scanner inputLeader = new Scanner(new File("leaderboard.txt"));
		while(iterator > 0)
        {	
			if(inputLeader.hasNextInt()) {
				nextInt = inputLeader.nextInt();
    			if(inputLeader.hasNext()) {
    				nextName = inputLeader.nextLine();  //next?
    				gameScoreNames.put(nextInt, nextName);
    			}
    		}
            iterator--;
        }       
		//SORT
        Map<Integer, String> sorted = new TreeMap<Integer, String>(gameScoreNames);
        Map<Integer, String> sortedLeaders = new TreeMap<Integer, String>(Collections.reverseOrder());
        for(Map.Entry<Integer, String> entry : sorted.entrySet())
        {
            sortedLeaders.put(entry.getKey(), entry.getValue());
        }
		PrintWriter writer = new PrintWriter("leaderboard.txt");
		iterator = 3;
		//Overwriting values in "yahtzeeConfig.txt"
        for(Map.Entry<Integer, String> entry : sortedLeaders.entrySet())
        {
			if(iterator > 0) {
				writer.print(entry.getKey());
                writer.print(" ");
                writer.println(entry.getValue());
            }
            else {
            	writer.println();
            }
            iterator--;
        }
        writer.close();
        int i = 1;
	}	
}