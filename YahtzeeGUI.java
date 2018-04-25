// BBQ is 2morrow

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
17: get name
18: first roll
19: roll again
20: finishTurn
21: next on scorecard panel
*/

/*
INTERNAL GEN PIC BUTTON KEY:
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
	final int NUMBER_OF_ROUNDS = 2;
	final int NUMBER_OF_ROLLS = 6; //its actually 5 but our code is all sorts of FUCKED UP --remove comment b4 turning in.
	private int playerCounter = 1;
	private int scorecardPlayerCounter = 1;
	
	private int rollRound = 1;
	private int gameRound = 1;
	private char[] keep = keepString(Die.numberOfDie, "n").toCharArray(); //setup to roll all dice in the first roll
	public YahtzeeGUI() {
		createWindow(1000, 600);
		addMainMenuPanel();
		//addInstrPnl1();
		//addInstrPnl2();
		
		frame.add(panel);
		frame.setVisible(true);

	}
	
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
		//frame.setJMenuBar(addMenuBar());
		//define panels
	}
	
	
	
	public void addMainMenuPanel()
	{
		mainMenuPanel.setLayout(null);
		//mainMenuPanel.setLayout(new BorderLayout());
		mainMenuPanel.setBackground(new Color(145, 200, 255));
		panel.add(mainMenuPanel, BorderLayout.CENTER);
		
		
		JLabel jlabel = new JLabel("Gonzaga Men's Basketball Yahtzee");
	    jlabel.setFont(new Font("Verdana",1,40));
	    mainMenuPanel.add(jlabel);
	    Dimension size = jlabel.getPreferredSize();
	    jlabel.setBounds(100, 100, size.width, size.height);
	    mainMenuPanel.setSize(400, 400);
	    jlabel.setLocation(105, 20);
	  
	    createGenImg("1718PosterPhoto.jpg",900,400,50,80, mainMenuPanel);
	    
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
	public void setup() {
		ImageIcon imageIcon = new ImageIcon(iconName); // load the image to a imageIcon
	    Image image = imageIcon.getImage(); // transform it 
	    Image newimg = image.getScaledInstance(picWidth, picHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
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
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == genBtn) {

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
				else if(desiredAction == 2){
					//exit game panel
				}
				
				else if(desiredAction == 4){
					//TO-DO: actually implement
					keep[0] = 'y';
					System.out.println("P1 clicked");
				}
				
				else if(desiredAction == 5){
					//TO-DO: actually implement
					keep[1] = 'y';
					System.out.println("P2 clicked");
				}
				
				else if(desiredAction == 6){
					//TO-DO: actually implement
					keep[2] = 'y';
					System.out.println("P3 clicked");
				}
				
				else if(desiredAction == 7){
					//TO-DO: actually implement
					keep[3] = 'y';
					System.out.println("P4 clicked");
				}
				
				else if(desiredAction == 8){
					//TO-DO: actually implement
					keep[4] = 'y';
					System.out.println("P5 clicked");
				}
				else {
					
				}
				
				
			}
			
		}
	}
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
			  btn.setBounds(500, 500, sizeBtn.width, sizeBtn.height);
		  }
		  btn.addActionListener(this);

		 }
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method tub
			if(e.getSource() == btn) {
				if(desiredAction == 0) {
					players[playSpot - 1].setName(textField.getText());
				}
				else if (desiredAction == 1){
					String pC = textField.getText();
                    System.out.println(pC);
                    
                    playerChoice = Integer.parseInt(pC);
				}
				else {
					
				}
			}
			
		}
    }
	
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
		public void setup() {
			btn = new JButton(btnName);
			btn.setFont(new Font("Verdana",1,30));
			panelAdd.add(btn);
			Dimension sizeBtn = btn.getPreferredSize();
			btn.setBounds(btnWidth, btnHeight, sizeBtn.width, sizeBtn.height);
			btn.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btn) {
				//open play window
	    		if(desiredAction == 1) {
	    			//the actual game window
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
						// TODO Auto-generated catch block
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
	    		//open How to play window
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
	    		//the game
	    		else  if(desiredAction == 16) {
	    			frame.getContentPane().removeAll();
	    			frame.getContentPane().invalidate();
	    			
	    			addPlayNamePnl();
	    			frame.getContentPane().add(playNamePnl);
	    			frame.getContentPane().revalidate();
	    		}
	    		else  if(desiredAction == 17) {
	    		
	    		}
	    		
	    		else if(desiredAction == 18) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().invalidate();
					
                    firstRollPnl(gameRound, playerCounter, rollRound);
                    frame.getContentPane().add(firstRollPnl);
                    frame.getContentPane().revalidate();
	    		}
	    		else if(desiredAction == 19) {
	    			frame.getContentPane().removeAll();
                    frame.getContentPane().invalidate();
                    modifySettings();
                    //RESET keep array to roll all dice
                    keep = keepString(Die.numberOfDie, "n").toCharArray(); //Bladow added
					otherRollPnl(gameRound, playerCounter, rollRound);
					frame.getContentPane().add(otherRollPnl);
                    frame.getContentPane().revalidate();
	    		}
	    		//TO-DO: send to scoring first
	    		else if(desiredAction == 20) {
	    			frame.getContentPane().removeAll();
                    frame.getContentPane().invalidate();
                    
                    //If finishTurn is clicked...
                    rollRound = 6;
                    
                    //RESET keep array to roll all dice
                    keep = keepString(Die.numberOfDie, "n").toCharArray();   
                    
                    //Might need to sort the ArrayList<Die> before calculating shit
                    //TO-DO: should we display their sorted hand?
                    sortArray(players[scorecardPlayerCounter - 1].hand, Die.numberOfDie);
                    
                    scorePnl();
                    
					frame.getContentPane().add(scorePnl);
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
	    		}
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
                        rollPnl();
                        frame.getContentPane().add(rollPnl);
                    }
                    else{
                        rollRound = 1;
                        if((playerCounter == numberOfPlayers))
                        {
                            playerCounter = 1;
                            if(gameRound == NUMBER_OF_ROUNDS) {
                            	//game over
                            }else {
                            	 gameRound++;
                            }
             
                        }
                        else
                        {
                            playerCounter++;
                        }
                        
                        //RESET keep array to roll all dice
                      //  keep = keepString(Die.numberOfDie, "n").toCharArray();
                        
						firstRollPnl(gameRound, playerCounter, rollRound);
						frame.getContentPane().add(firstRollPnl);
                    }
					frame.getContentPane().revalidate();
                }
	    		//another window
	    		else {
	    			//another window
	    		}
	    	}
			
		}
	}	
	public void addInstrPnl1()
	{
		instrPnl1.setLayout(null);
		//instrPnl1.setLayout(new BorderLayout());
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
	
	public void addInstrPnl2()
	{
		instrPnl2.setLayout(null);
		//instrPnl2.setLayout(new BorderLayout());
		instrPnl2.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl2, BorderLayout.CENTER);
		
		// Instructions title
	    createGenLabel("Dice", 100, 50, 40, Color.black, instrPnl2);
	    
	    // ....
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
	    createGenImg("RosterScreenshot.png", 465, 465, 450, 25, instrPnl2);
	}
	public void addInstrPnl3()
	{
		instrPnl3.setLayout(null);
		//instrPnl2.setLayout(new BorderLayout());
		instrPnl3.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl3, BorderLayout.CENTER);
		
		//instrPnl3.setOpaque(true);
		
		//createGenImg("LkrRmTrsp.png", 1000, 600, 0, 0, instrPnl3);
		
		// How to Win title
	    createGenLabel("How to Win", 340, 30, 50, Color.black, instrPnl3);
		
	    // Text
	    createGenLabel("*Roll dice 5 times to get the highest score", 25, 200, 15, Color.black, instrPnl3);
	    createGenLabel("  after all 22 rounds.", 25, 225, 15, Color.black, instrPnl3);
	    
	    // Next button
	    createGenBtn  Next3 = new createGenBtn("Next", 7, 560, 500, instrPnl3);
	    Next3.setup();
	    
	    // Main Menu button
	    createGenBtn mainMenu3 = new createGenBtn("Main Menu", 4, 725, 500, instrPnl3);
	    mainMenu3.setup();
	}
	
	public void addInstrPnl4()
	{
		instrPnl4.setLayout(null);
		//instrPnl2.setLayout(new BorderLayout());
		instrPnl4.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl4, BorderLayout.CENTER);
		
		// How to Play title
	    createGenLabel("How to Play", 340, 30, 50, Color.black, instrPnl4);
	    
	    // Text
	    createGenLabel("To start, you will roll the dice by clicking Roll. After rolling you can either score the current roll on the", 25, 100, 15, Color.black, instrPnl4);
	    createGenLabel("scorecard or re-roll any or all of the dice*. You may only roll the dice a total of five times. After the", 25, 115, 15, Color.black, instrPnl4);
	    createGenLabel("fifth roll you must choose a category on the scorecard to score your hand in. You must choose a", 25, 130, 15, Color.black, instrPnl4);
	    createGenLabel("different category each time i.e., you can NOT score your hand in a category you have already scored.", 25, 145, 15, Color.black, instrPnl4);
	    //createGenLabel("text here", 25, 160, 15, Color.black, instrPnl4);
	    createGenLabel("To keep a die value enter ‘y’ in the same order that the die appeared. Enter ‘n’ to re-roll. For example,", 25, 175, 15, Color.black, instrPnl4);
	    createGenLabel("if the roll is Jones, Hachimura, Norvell, Tillie, Perkins to keep Jones, Norvell, and Tillie and re-roll the", 25, 190, 15, Color.black, instrPnl4);
	    createGenLabel("others you would enter: ‘ynyyn’.", 25, 205, 15, Color.black, instrPnl4);
	    //createGenLabel("text here", 25, 220, 15, Color.black, instrPnl4);
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
	
	public void addInstrPnl5()
	{
		instrPnl5.setLayout(null);
		//instrPnl2.setLayout(new BorderLayout());
		instrPnl5.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl5, BorderLayout.CENTER);
		
		//instrPnl3.setOpaque(true);
		
		//createGenImg("LkrRmTrsp.png", 1000, 600, 0, 0, instrPnl3);
		
		// How to Win title
	    createGenLabel("Scoring", 340, 30, 50, Color.black, instrPnl5);
		
	    // Text
	    createGenLabel("*The scorecard is split into upper and lower", 25, 200, 15, Color.black, instrPnl5);
	    createGenLabel("  sections.", 25, 225, 15, Color.black, instrPnl5);
	    
	    // Next button
	    createGenBtn Next5 = new createGenBtn("Next", 9, 560, 500, instrPnl5);
	    Next5.setup();
	    // Main Menu button
	    createGenBtn MainMenu5 = new createGenBtn("Main Menu", 4, 725, 500, instrPnl5);
	    MainMenu5.setup();
	}
	
	public void addInstrPnl6()
	{
		instrPnl6.setLayout(null);
		//instrPnl2.setLayout(new BorderLayout());
		instrPnl6.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl6, BorderLayout.CENTER);
		
		// Upper Section title
	    createGenLabel("Upper Section", 340, 30, 50, Color.black, instrPnl6);
	    
	    // Text
	    createGenLabel("*The upper section scores only the play ", 25, 200, 15, Color.black, instrPnl6);
	    createGenLabel("used during that turn. Their score is based ", 25, 215, 15, Color.black, instrPnl6);
	    createGenLabel("off their player number, for example, getting ", 25, 230, 15, Color.black, instrPnl6);
	    createGenLabel("Johnathon Williams 3 times, and Jesse Wade", 25, 245, 15, Color.black, instrPnl6);
	    createGenLabel("2 times, will score 9 points for the Williams", 25, 260, 15, Color.black, instrPnl6);
	    createGenLabel("section, and 20 forthe Wade section", 25, 275, 15, Color.black, instrPnl6);
	  
		// Next button
	    createGenBtn Next6 = new createGenBtn("Next", 10, 560, 500, instrPnl6);
	    Next6.setup();
	    // Main Menu button
	    createGenBtn MainMenu6 = new createGenBtn("Main Menu",4, 725, 500, instrPnl6);
	    MainMenu6.setup();
	}
	
	public void addInstrPnl7()
	{
		instrPnl7.setLayout(null);
		//instrPnl2.setLayout(new BorderLayout());
		instrPnl7.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl7, BorderLayout.CENTER);
		
		// Upper Section title
	    createGenLabel("Lower Section", 340, 30, 50, Color.black, instrPnl7);
	    
	    // Text
	    createGenLabel("*3 of a Zag ", 25, 100, 15, Color.black, instrPnl7);
	    createGenLabel("  A player must have 3 of the same player in the hand.", 25, 115, 15, Color.black, instrPnl7);
	    createGenLabel("  The score is the value of all 5-dice added up.", 25, 130, 15, Color.black, instrPnl7);
	    //createGenLabel("Johnathon Williams 3 times, and Jesse Wade", 25, 145, 15, Color.black, instrPnl7);
	    createGenLabel("*4 of a Zag", 25, 160, 15, Color.black, instrPnl7);
	    createGenLabel("  A player must have 4 of the same player in the hand.", 25, 175, 15, Color.black, instrPnl7);
	    createGenLabel("  The score is the value of all 5-dice added up.", 25, 190, 15, Color.black, instrPnl7);
	    //createGenLabel(" A player must have 4 of the same player in the hand.", 25, 205, 15, Color.black, instrPnl7);
	    createGenLabel("*Full Team", 25, 220, 15, Color.black, instrPnl7);
	    createGenLabel("  A player must have 3 Guards and 2 Forwards to accomplish this score.", 25, 235, 15, Color.black, instrPnl7);
	    createGenLabel("  The score will be 25 minutes, regardless of the players number values.", 25, 250, 15, Color.black, instrPnl7);
	   // createGenLabel("*Full Team.", 25, 265, 15, Color.black, instrPnl7);
	    createGenLabel("*The Bench Brigade", 25, 280, 15, Color.black, instrPnl7);
	    createGenLabel("  A player must have 5 players from the Bench to accomplish this score.", 25, 295, 15, Color.black, instrPnl7);
	    createGenLabel("  The score will be 30 points, regardless of players' number values.", 25, 310, 15, Color.black, instrPnl7);
	    //createGenLabel("*The Bench Brigade.", 25, 325, 15, Color.black, instrPnl7);
	    createGenLabel("*The Starters", 25, 340, 15, Color.black, instrPnl7);
	    createGenLabel("  A player must have all 5 Starters to accomplish this score.", 25, 355, 15, Color.black, instrPnl7);
	    createGenLabel("  The score will be 40 points, regardless of players' number values.", 25, 370, 15, Color.black, instrPnl7);
	    //createGenLabel(" A player must have 5 players from the Bench to accomplish this score.", 25, 385, 15, Color.black, instrPnl7);
	    createGenLabel("*The Kennel", 25, 400, 15, Color.black, instrPnl7);
	    createGenLabel("  No combos necessary. The score of this roll will be all the players'", 25, 415, 15, Color.black, instrPnl7);
	    createGenLabel("  numbers totaled together.", 25, 430, 15, Color.black, instrPnl7);
	    //createGenLabel("*The Kennel", 25, 445, 15, Color.black, instrPnl7);
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
	
	public void addInstrPnl8()
	{
		instrPnl8.setLayout(null);
		//instrPnl2.setLayout(new BorderLayout());
		instrPnl8.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl8, BorderLayout.CENTER);
		
		// Upper Section title
	    createGenLabel("Scorecard", 600, 30, 50, Color.black, instrPnl8);
	    
	    // Scorecard Screenshot
	    createGenImg("ScorecardScreenshot.png", 500, 525, 25, 20, instrPnl8);
	  
	    // Main Menu button
	    createGenBtn MainMenu8 = new createGenBtn("Main Menu", 4, 725, 500, instrPnl8);
	    MainMenu8.setup();
	}
	public void addLdrPnl() throws FileNotFoundException
	{
		ldrPnl.setLayout(null);
		//instrPnl2.setLayout(new BorderLayout());
		ldrPnl.setBackground(new Color(145, 200, 255));
		panel.add(ldrPnl, BorderLayout.CENTER);
		
		// Leaderboard title
	    createGenLabel("Leaderboard", 310, 30, 50, Color.black, ldrPnl);
	    
	    
	        //1. Get numbers and names
	        //2. Put at end of leaderboard
	        //3. Sort leaderboard
	        //4. Put in correct order
	        //5. Print leaderboard 
	        int numberOfLeaders;
	        int iterator;
	        int nextInt;
	        String nextName;
	        
	        Map<Integer, String> gameScoreNames = new HashMap<Integer, String>();
	       
			Scanner inputLeader = new Scanner(new File("leaderboard.txt"));
	        numberOfLeaders = inputLeader.nextInt();
	        iterator = numberOfLeaders;
	        
	        if(numberOfLeaders == 0) {
	        	createGenLabel("There is currenty no one on the leaderboard!", 100, 120,25,Color.black, ldrPnl);
	        	createGenLabel("Play a game to be featured!", 100, 180, 25,Color.black, ldrPnl);
	        }
	        else {
	        	while(iterator > 0)
	        	{
	            	nextInt = inputLeader.nextInt();
	            	nextName = inputLeader.nextLine();  //next?
	            	gameScoreNames.put(nextInt, nextName);
	            
	            	iterator--;
	        	}    
	        
				//SORT
	        	Map<Integer, String> sorted = new TreeMap<Integer, String>(gameScoreNames);
	        	Map<Integer,String> sortedLeaders = new TreeMap<Integer, String>(Collections.reverseOrder());
	            for(Map.Entry<Integer, String> entry : sorted.entrySet()) {
	              	sortedLeaders.put(entry.getKey(), entry.getValue());
	            }
	        	
	        	Integer i = 1;
	        	String curPlayerPosition;
	        	String PlayerPosition;
	        	
	        
	        	for(Map.Entry<Integer, String> entry : sortedLeaders.entrySet())
	        	{
	        		StringBuilder playerPositionSB = new StringBuilder();
		        	playerPositionSB.append("# ");
		        	curPlayerPosition = i.toString();
	            	playerPositionSB.append(curPlayerPosition);
	            	PlayerPosition = playerPositionSB.toString();
	            	
	            	StringBuilder playerScoreSB = new StringBuilder();
	            	String curPlayerScore = entry.getKey().toString();;
	            	playerScoreSB.append(curPlayerScore);
	            	playerScoreSB.append(" points");
	            	String playerScore = playerScoreSB.toString();
	            	
	            	
	        		createGenLabel(PlayerPosition,100,120+(i-1)*60,25,Color.black,ldrPnl);
	            	createGenLabel(entry.getValue(),400,120+(i-1)*60,25,Color.black,ldrPnl);
	            	createGenLabel(playerScore,700,120+(i-1)*60,25,Color.black,ldrPnl);
	            	i++;
	        	}
	        }
	    // Main Menu button
	    createGenBtn MainMenu9 = new createGenBtn("Main Menu", 4, 725, 500, ldrPnl);
	    MainMenu9.setup();
}
	public void addPlayNumPnl(){
	    //NIBS1 - Add players
	    playNumPnl.setLayout(null);
	    //instrPnl2.setLayout(new BorderLayout());
	    playNumPnl.setBackground(new Color(145, 200, 255));
	    panel.add(playNumPnl, BorderLayout.CENTER);
	    
	    //need to change panel links
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
	public void addPlayNamePnl(){
	    playNamePnl.setLayout(null);
	    //instrPnl2.setLayout(new BorderLayout());
	    playNamePnl.setBackground(new Color(145, 200, 255));
	    panel.add(playNamePnl, BorderLayout.CENTER);
	    // ENTER PLAYER NAMES PANEL

	    players = new Player[numberOfPlayers];
        
	    
	    
        for(int i = 1; i <= numberOfPlayers; i++)
        {
              createGenLabel("Player #" + i + ":", 150, (i-1)*45 + 175, 20, Color.black, playNamePnl);
              createGenTextField userNames = new createGenTextField(i, 0, 200, 30, 275, (i-1)*45 + 175, playNamePnl);
              userNames.setup();
              players[i-1] = new Player();
        }
        
        // Upper Section title
      createGenLabel("Enter player names", 225, 30, 50, Color.black, playNamePnl);
      createGenLabel("No spaces allowed!", 365, 100, 25, Color.black, playNamePnl);
     
        // Next button
      createGenBtn Next8= new createGenBtn("Next", 18, 725, 500, playNamePnl);
      Next8.setup();

	    
	}
	
	public void firstRollPnl(int numRound, int numPlayer, int numRoll){
		firstRollPnl.setLayout(null);
	    //instrPnl2.setLayout(new BorderLayout());
		firstRollPnl.setBackground(new Color(145, 200, 255));
		
	    panel.add(firstRollPnl, BorderLayout.CENTER);
	    
	    //keep array is all 'n'
	    
	    
	    
	    createGenModLabel("Round " + numRound + ": Roll " + numRoll + " for " + players[numPlayer - 1].getName(), 220, 80, 30, Color.black, firstRollPnl);
	    createGenLabel("Press the ball to roll!", 220, 30, 50, Color.black, firstRollPnl);
		//createGenImg("Basketball.png", 200, 200, 400, 200, firstRollPnl); 
		
	    //ImageIcon ic2 = new ImageIcon("Basketball.png");
	    
	    //START
	    createGenBtnPic basketball  = new createGenBtnPic("Basketball.png", 1,300, 300, 350, 150, firstRollPnl);
	    	basketball.setup();
	    	
	    	
	    
		//END	
		
	    
	}
	
	
	public void createGenLabel(String dispText, int textWidth, int textHeight, int textSize, Color color ,JPanel panelAdd)
	{
		JLabel text = new JLabel(dispText);
	    text.setFont(new Font("Verdana",1,textSize));
		panelAdd.add(text);
	    Dimension sizeText = text.getPreferredSize();
	    text.setBounds(textWidth, textHeight, sizeText.width, sizeText.height);
	    text.setForeground(color);
	    panelAdd.setSize(400, 400);
	    //text.setLocation(360, 20);
	}
	
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
	    //text.setLocation(360, 20);
	}
	
	public void createGenImg(String imgPath, int imgWidth, int imgHeight, int imgWidthLoc, int imgHeightLoc, JPanel panelAdd)//String imgPath, int imgWidth, int imgHeight, JPanel panelAdd) 
	{
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Image dimg = myPicture.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
		
		
		
	    JLabel picLabel = new JLabel(new ImageIcon(dimg));
	    panelAdd.add(picLabel);

	    Dimension sizePic = picLabel.getPreferredSize();
	    //picLabel.setPreferredSize(new Dimension(100, 25));
	    picLabel.setBounds(imgWidthLoc, imgHeightLoc, sizePic.width, sizePic.height);
	}
	
	public void rollPnl(){
		rollPnl.setLayout(null);
	    //instrPnl2.setLayout(new BorderLayout());
		rollPnl.setBackground(new Color(145, 200, 255));
	    panel.add(rollPnl, BorderLayout.CENTER);
		
	    
	    String output = "";
        for(int i = 0; i < numberOfPlayers; i++)
        {
            output = players[i].getName() + " scored " + players[i].totalValue() + " for this game";
            //players[i].printCompleted(players[i].hand); //TO-DO: It's 1:40am and I don't want to put in the effort to figure out how to print each person's completed scorecard
            createGenLabel(output, 300, 300 + 20 * i, 20, Color.green, rollPnl);
        }
	    
	    createGenLabel("TODD GUSE", 220, 30, 50, Color.black, rollPnl);
	    createGenLabel("BYE!", 220, 230, 50, Color.black, rollPnl);

	    
		//createGenImg("Basketball.png", 200, 200, 400, 200, firstRollPnl);
		
	    //ImageIcon ic2 = new ImageIcon("Basketball.png");
	    
	    //START
	    
		//END		    
	}
	
	public void otherRollPnl(int numRound, int numPlayer, int numRoll){
		otherRollPnl.setLayout(null);
	    //instrPnl2.setLayout(new BorderLayout());
		otherRollPnl.setBackground(new Color(145, 200, 255));
		
	    panel.add(otherRollPnl, BorderLayout.CENTER);
	    
	    
	    
	    System.out.println("Round " + numRound + ": Roll " + (numRoll-1) + " for " + players[numPlayer - 1].getName());
	    //createGenModLabel("Round " + numRound + ": Roll " + numRoll + " for " + players[numPlayer - 1].getName(), 220, 80, 30, Color.black, otherRollPnl);
	    createGenModLabel(players[numPlayer - 1].getName() + ": select the players you wish to", 30, 25, 40, Color.black, otherRollPnl);
	    createGenLabel("keep from roll " + (numRoll-1) + ":" , 30, 75, 40, Color.black, otherRollPnl);
	    //createGenLabel("Select the players you wish to", 220, 30, 50, Color.black, otherRollPnl);
	    //createGenLabel("keep from the roll " + numRoll + ":", 220, 60, 50, Color.black, otherRollPnl);
		//createGenImg("Basketball.png", 200, 200, 400, 200, firstRollPnl); 
		
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
	    
	    
	    
	    //ImageIcon ic2 = new ImageIcon("Basketball.png");
	    
	    //START
	   
        // Next button
	    if(numRoll != 6) {
	    	createGenBtn rollAgain = new createGenBtn("Roll Again", 19, 500, 500, otherRollPnl);
	    	rollAgain.setup();
	    }
        createGenBtn finishTurn = new createGenBtn("Finish Turn", 20, 725, 500, otherRollPnl);
        finishTurn.setup();	    
	}
	
    public void scorePnl()
    {
    	scorePnl.setLayout(null);
		scorePnl.setBackground(new Color(145, 200, 255));
	    panel.add(scorePnl, BorderLayout.CENTER);
        //TO-DO: Eugene look pretty
    	printUpperGUI();
    	printLowerGUI();
        
    	//output current score
    	createGenLabel("This is " + players[scorecardPlayerCounter - 1].getName() + "'s current score: " + players[scorecardPlayerCounter - 1].getCurrentScore(), 400, 2, 17, Color.cyan, scorePnl);
    	
        //TO-DO: Eugene make it look pretty (the scoreNumber field)
        createGenTextField scoreNumber = new createGenTextField(1, 1, 100, 30, 325, 500, scorePnl);
        scoreNumber.setup();
        
        //TO-DO: Is it possible to make sure they click submit before allowing them to click next? (for textField)
        //TO-DO: Also, how do we check for bad data? (like if row 0 has alrdy been chosen and they choose it again?
        
        
        createGenBtn next9 = new createGenBtn("Next", 21, 725, 500, scorePnl);
        next9.setup();
    }
    
	public static Die rollDie()
	{
		Die newDie = new Die();
		newDie.roll();
		return newDie;
	}
	
	public int modifySettings()
	{
		int desiredAction = 0;
		//if (...) go to Todd Guse slide
		if(rollRound == NUMBER_OF_ROLLS && gameRound == NUMBER_OF_ROUNDS && playerCounter == numberOfPlayers) {
			 desiredAction = 1;
		}
		//if(...) go to 
		if(rollRound == NUMBER_OF_ROLLS) 
		{
			rollRound = 1;
			desiredAction = 2;
			if(playerCounter == numberOfPlayers) 
			{
				playerCounter = 1;
				if(gameRound == NUMBER_OF_ROUNDS) 
				{
					desiredAction = 1;
				}
				else
				{
					gameRound++;
				}
			}
			else 
			{
				playerCounter++;
			}
		}
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
				//Temporary --below
				System.out.print("Roll " + rollRound + " was: ");
                for (int dieNumber = 0; dieNumber < Die.numberOfDie; dieNumber++)
                {
                    System.out.print(players[playerCounter - 1].hand.get(dieNumber).getValue() + " ");
                }
                //--above
			rollRound++;
		}
		return desiredAction;
	}
	
    public int updateFlag()
    {
        int desiredAction = 0;
		//if (...) go to Todd Guse slide
		if(rollRound >= NUMBER_OF_ROLLS && gameRound >= NUMBER_OF_ROUNDS && playerCounter >= numberOfPlayers) {
			 desiredAction = 1;
		}
		return desiredAction;
    }
    
	public static String keepString(int numberOfDie, String initializeVal)
	{
		String keeps = "";
		for(int i = 0; i < numberOfDie; i++)
		{
			keeps = keeps + initializeVal;
		}
		return keeps;
	}
	
	public String dieRoll(int dieNumber)
	{
        return players[playerCounter - 1].hand.get(dieNumber).getName() + ".jpeg";
	}
    
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
			{																										 //0 bc its same for all players.
				output = "Row " + (dieValue - 1) + ": " + "Score is " + dieValue * currentCount + " on the " + players[0].athleteNames[dieValue - 1] + " line";
				if(modLabel == 'y')
				{
					createGenModLabel(output, 50, 20*dieValue, 15, Color.black, scorePnl);
					modLabel = 'n';
				}
				else
					createGenLabel(output, 50, 20*dieValue, 15, Color.black, scorePnl);
			}
		}	
    }
    
	public void printLowerGUI()
	{
		String output = "";
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides))
		{
			if (Calculations.maxOfAZagFound(players[scorecardPlayerCounter - 1].hand) >= 3)
			{
				output = "Row " + (Die.numberOfSides) + ": " + "Score " + Calculations.totalAllDice(players[scorecardPlayerCounter - 1].hand) + " on the ";
				output = output + "3 of a Zag line";
				
			}
			else 
				output = "Row " + (Die.numberOfSides) + ": " + "Score 0 on the 3 of a Zag line";
			createGenLabel(output, 50, 320, 15, Color.black, scorePnl);
		}
		
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 1))
		{
			if (Calculations.maxOfAZagFound(players[scorecardPlayerCounter - 1].hand) >= 4)
			{
				output = "Row " + (Die.numberOfSides + 1) + ": " + "Score " + Calculations.totalAllDice(players[scorecardPlayerCounter - 1].hand) + " on the ";
				output = output + "4 of a Zag line";
			}
			else 
				output = "Row " + (Die.numberOfSides + 1) + ": " + "Score 0 on the 4 of a Zag line";
			createGenLabel(output, 50, 340, 15, Color.black, scorePnl);
		}
		
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 2))
		{
			if (Calculations.fullTeamFound(players[scorecardPlayerCounter - 1].hand))
				output = "Row " + (Die.numberOfSides + 2) + ": " + "Score 25 on the Full Team line";
			else
				output = "Row " + (Die.numberOfSides + 2) + ": " + "Score 0 on the Full House line";
			createGenLabel(output, 50, 360, 15, Color.black, scorePnl);
		}
		
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 3))
		{
			if (Calculations.benchBrigadeFound(players[scorecardPlayerCounter - 1].hand))
				output = "Row " + (Die.numberOfSides + 3) + ": " + "Score 30 on the Bench Brigade line";
			else
				output = "Row " + (Die.numberOfSides + 3) + ": " + "Score 0 on the Bench Brigade line";
			createGenLabel(output, 50, 380, 15, Color.black, scorePnl);
		}
		
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 4))
		{
			if (Calculations.startingLineupFound(players[scorecardPlayerCounter - 1].hand))
				output = "Row " + (Die.numberOfSides + 4) + ": " + "Score 40 on the Starters line";
			else
				output = "Row " + (Die.numberOfSides + 4) + ": " + "Score 0 on the Starters line";
			createGenLabel(output, 50, 400, 15, Color.black, scorePnl);
		}
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 5))
		{
			if (Calculations.maxOfAZagFound(players[scorecardPlayerCounter - 1].hand) >= 5)
				output = "Row " + (Die.numberOfSides + 5) + ": " + "Score 100 on the Zombie Nation line";
			else
				output = "Row " + (Die.numberOfSides + 5) + ": " + "Score 0 on the Zombie Nation line";
			createGenLabel(output, 50, 420, 15, Color.black, scorePnl);
		}
		if(players[scorecardPlayerCounter - 1].canPrint(Die.numberOfSides + 6))
		{
			output = "Row " + (Die.numberOfSides + 6) + ": " + "Score " + Calculations.totalAllDice(players[scorecardPlayerCounter - 1].hand) + " on the ";
			output = output + "Kennel line";
			createGenLabel(output, 50, 440, 15, Color.black, scorePnl);
		}
	}
	public void scoreCardTrackerGUI(ArrayList<Die> hand)
	{
		//Scanner scoring = new Scanner(System.in);
		//System.out.println("What row number would you like to score this round at? ");
		players[scorecardPlayerCounter - 1].scoredRowNumber = playerChoice;
		
/*		while(!players[scorecardPlayerCounter - 1].canPlaceScoreCard[players[scorecardPlayerCounter - 1].scoredRowNumber] && !players[scorecardPlayerCounter - 1].isFull())
		{
			System.out.println("That row is filled. Please enter a different number ");
			players[scorecardPlayerCounter - 1].scoredRowNumber = scoring.nextInt();
		}
*/
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
		System.out.println(players[scorecardPlayerCounter - 1].getName() + ": scored on Row: " + players[scorecardPlayerCounter - 1].scoredRowNumber);
		System.out.println("The rowValue is: " + players[scorecardPlayerCounter - 1].scoringCard[players[scorecardPlayerCounter - 1].scoredRowNumber]); 
	}
}