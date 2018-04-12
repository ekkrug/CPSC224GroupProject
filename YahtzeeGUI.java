import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.*;



public class YahtzeeGUI extends JFrame{
	private static JFrame frame;
	private JPanel panel, mainMenuPanel, instructionsPanel;
	
	public YahtzeeGUI() {
		createWindow(1000, 600);
		addMainMenuPanel();
		
		
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
		instructionsPanel = new JPanel();
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
	    JButton btnPlay = new JButton("Play");
	    btnPlay.setFont(new Font("Verdana",1,30));
	    mainMenuPanel.add(btnPlay);
	    Dimension sizeBtnPlay = btnPlay.getPreferredSize();
	    btnPlay.setBounds(305, 500, sizeBtnPlay.width, sizeBtnPlay.height);
		//btnPlay.setLocation(400, 400);
		
		// Leaderboard button
		JButton btnLdBd = new JButton("Leaderboard");
	    btnLdBd.setFont(new Font("Verdana",1,30));
	    mainMenuPanel.add(btnLdBd);
	    Dimension sizeBtnLdBd = btnLdBd.getPreferredSize();
	    btnLdBd.setBounds(445, 500, sizeBtnLdBd.width, sizeBtnLdBd.height);
		//btnPlay.setLocation(200, 200);
		
		// Instructions button
		JButton btnInstr = new JButton("Instructions");
		btnInstr.setFont(new Font("Verdana",1,30));
		mainMenuPanel.add(btnInstr);
		Dimension sizeBtnInstr = btnInstr.getPreferredSize();
		btnInstr.setBounds(725, 500, sizeBtnInstr.width, sizeBtnInstr.height);
		//btnPlay.setLocation(500, 200);
		
	}
	
	public void addInstructionsPanel()
	{
		instructionsPanel.setLayout(null);
		//mainMenuPanel.setLayout(new BorderLayout());
		instructionsPanel.setBackground(new Color(145, 200, 255));
		panel.add(instructionsPanel, BorderLayout.CENTER);
		
		
		JLabel lblInstructions = new JLabel("Instructions");
	    lblInstructions.setFont(new Font("Verdana",1,40));
	    instructionsPanel.add(lblInstructions);
	    Dimension sizeTitleInstr = lblInstructions.getPreferredSize();
	    lblInstructions.setBounds(100, 100, sizeTitleInstr.width, sizeTitleInstr.height);
	    instructionsPanel.setSize(400, 400);
	    instructionsPanel.setLocation(105, 20);
	  
	
		
		// Next button
		JButton btnNext = new JButton("Next");
	    btnNext.setFont(new Font("Verdana",1,30));
	    instructionsPanel.add(btnNext);
	    Dimension sizeBtnNext = btnNext.getPreferredSize();
	    btnNext.setBounds(445, 500, sizeBtnNext.width, sizeBtnNext.height);
		//btnPlay.setLocation(200, 200);
		
		// Instructions button
		JButton btnMainMenu = new JButton("Instructions");
		btnMainMenu.setFont(new Font("Verdana",1,30));
		mainMenuPanel.add(btnMainMenu);
		Dimension sizeBtnMainMenu = btnMainMenu.getPreferredSize();
		btnMainMenu.setBounds(725, 500, sizeBtnMainMenu.width, sizeBtnMainMenu.height);
		//btnPlay.setLocation(500, 200);
	}
}