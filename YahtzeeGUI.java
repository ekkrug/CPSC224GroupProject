import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;




public class YahtzeeGUI extends JFrame{
	private static JFrame frame;
	private JPanel panel, mainMenuPanel, instrPnl1, instrPnl2,instrPnl3,instrPnl4,instrPnl5, instrPnl6, instrPnl7, instrPnl8;
	
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
	  
	    createGenImg("1718PosterPhoto.jpg",900,400,50,80,mainMenuPanel);
	    
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
	    		}
	    		//open leaderboard window
	    		else if(desiredAction == 2) {
	    			//the leaderboard window
	    			
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
}



