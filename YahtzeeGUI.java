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
	private JPanel panel, mainMenuPanel, instrPnl1, instrPnl2;
	
	public YahtzeeGUI() {
		createWindow(1000, 600);
		//addMainMenuPanel();
		//addInstrPnl1();
		addInstrPnl2();
		
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
		//instrPnl1 = new JPanel();
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
	  
	    // Play button
	    createGenBtn("Play", 305, 500, mainMenuPanel);
	    
		// Leaderboard button
	    createGenBtn("Leaderboard", 445, 500, mainMenuPanel);
	    
	    // Instructions button
	    createGenBtn("Instructions", 725, 500, mainMenuPanel);
		 
		 
		
	
		
	}
	
	public void createGenBtn(String btnName, int btnWidth, int btnHeight, JPanel panelAdd) {
		JButton btn = new JButton(btnName);
		btn.setFont(new Font("Verdana",1,30));
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
		
		
		
	  
	   // Instructions title
	    createGenLabel("Instructions", 200, 200, 40, Color.black, instrPnl1);
	    
		// Next button
	    createGenBtn("Next", 560, 500, instrPnl1);
	    
	    // Main Menu button
	    createGenBtn("Main Menu", 725, 500, instrPnl1);
	    
	    
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
	    createGenBtn("Next", 560, 500, instrPnl2);
	    
	    // Main Menu button
	    createGenBtn("Main Menu", 725, 500, instrPnl2);
	    
	    // Roster image
	    createGenImg("RosterScreenshot.png", 465, 465, 450, 25, instrPnl2);
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