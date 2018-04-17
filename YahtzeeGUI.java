package yahtzee;
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
	private JPanel panel, mainMenuPanel, instrPnl1, instrPnl2, instrPnl3, instrPnl4, instrPnl5;
	
	public YahtzeeGUI() {
		createWindow(1000, 600);
		
		
		//***PANEL TESTS***
		//addMainMenuPanel();
		//addInstrPnl1();
		//addInstrPnl2();
		//addInstrPnl3();
		//addInstrPnl4();
		addInstrPnl5();
		
		//frame.add(panel);
		//frame.setVisible(true);

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
		
		instrPnl1 = new JPanel();
		instrPnl2 = new JPanel();
		instrPnl3 = new JPanel();
		instrPnl4 = new JPanel();
		instrPnl5 = new JPanel();
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
	  
	    // Roster Photo
	    createGenImg("1718PosterPhoto.jpg", 900, 400, 50, 80, mainMenuPanel);
	    
	    // Play button
	    createGenBtn("Play", 305, 500, Color.black, mainMenuPanel);
	    
		// Leaderboard button
	    createGenBtn("Leaderboard", 445, 500, Color.black, mainMenuPanel);
	    
	    // Instructions button
	    createGenBtn("Instructions", 725, 500, Color.black, mainMenuPanel);
		 
		 
		
	
		
	}
	
	public void createGenBtn(String btnName, int btnWidth, int btnHeight, Color textColor, JPanel panelAdd) {
		JButton btn = new JButton(btnName);
		btn.setFont(new Font("Verdana",1,30));
		//btn.setBackground(Color.yellow);
		btn.setForeground(textColor);
		panelAdd.add(btn);
		Dimension sizeBtn = btn.getPreferredSize();
		btn.setBounds(btnWidth, btnHeight, sizeBtn.width, sizeBtn.height);
		//btnPlay.setLocation(500, 200);
	}
	
	public void addInstrPnl1()
	{
		instrPnl1.setLayout(null);
		//instrPnl1.setLayout(new BorderLayout());
		instrPnl1.setBackground(new Color(145, 200, 255));
		panel.add(instrPnl1, BorderLayout.CENTER);
		
		
		
		
		//createGenImg("MACEmptyTrsp.png", 1000, 600, 0, 0, instrPnl1);
	  
	   // Instructions title
	    createGenLabel("Instructions", 540, 200, 60, Color.black, instrPnl1);
	    
		// Next button
	    createGenBtn("Next", 560, 500, Color.black, instrPnl1);
	    
	    // Main Menu button
	    createGenBtn("Main Menu", 725, 500, Color.black, instrPnl1);
	    
	    
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
	    createGenBtn("Next", 560, 500, Color.black, instrPnl2);
	    
	    // Main Menu button
	    createGenBtn("Main Menu", 725, 500, Color.black, instrPnl2);
	    
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
	    createGenBtn("Next", 560, 500, Color.black, instrPnl3);
	    
	    // Main Menu button
	    createGenBtn("Main Menu", 725, 500, Color.black, instrPnl3);
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
	    createGenBtn("Next", 560, 500, Color.black, instrPnl4);
	    
	    // Main Menu button
	    createGenBtn("Main Menu", 725, 500, Color.black, instrPnl4);
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
	    createGenBtn("Next", 560, 500, Color.black, instrPnl5);
	    
	    // Main Menu button
	    createGenBtn("Main Menu", 725, 500, Color.black, instrPnl5);
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