package GUI_TTT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class GUI_TTT implements MouseListener,ActionListener{
	
	static final int DIV=3;
	/**offensive:
	 * o goes first
	 * 1-I'm o
	 * 2-I'm x
	 * */
	private int offensive=1;
	private int currentCount;
	
//Variables in Login Window
	JFrame lw=new JFrame();
	JPanel lwpanel=new JPanel();
	JButton lwbutton=new JButton("Login");
	boolean login_req=false;
	JTextField ipTextField=new JTextField(15);
	JTextField portTextField=new JTextField(15);
	
//Variables in Game Window	
	JFrame gw=new JFrame();
	JPanel titlePanel;
	JPanel scorePanel;
	JPanel gamePanel;
	JLabel[] gameGrid;
	ImageIcon[] oxicon;
	
	int[] mapData=new int[DIV*DIV];
	
	GUI_TTT(){
		for(int i=0;i<DIV*DIV;i++){
			mapData[i]=0;
		}
		currentCount=0;
		oxicon=new ImageIcon[3];
		oxicon[0]=new ImageIcon("src\\GUI_TTT\\blankbox_.jpg");
		oxicon[1]=new ImageIcon("src\\GUI_TTT\\obox.jpg");
		oxicon[2]=new ImageIcon("src\\GUI_TTT\\xbox.jpg");
	}
	
	public static void main(String[] args){
		GUI_TTT gt=new GUI_TTT();
		gt.go();
	}
	
	public void go(){
//Login Window
		
		lwpanel=(JPanel)lw.getContentPane();
		lw.setTitle("Find an opponant");
		lw.setSize(600, 600);
		lw.setLocation(600,100);
		lw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagLayout gbl=new GridBagLayout();
		lwpanel.setLayout(gbl);
		
		/**GridBagConstraints(
		 * int gridx,
		 * int gridy,
		 * int gridwidth,
		 * int gridheight,
		 * double weightx,
		 * double weighty,
		 * int anchor,
		 * int fill,
		 * Insets insets,
		 * int ipadx,
		 * int ipady)
        */
		GridBagConstraints lwgbc=new GridBagConstraints();
		lwgbc.gridx=0;
		lwgbc.gridy=0;
		lwpanel.add(new JLabel("IP:"),lwgbc);
		
		lwgbc.gridx=2;
		lwgbc.gridy=0;
		lwpanel.add(ipTextField,lwgbc);
		
		lwgbc.gridx=0;
		lwgbc.gridy=1;
		lwpanel.add(new JLabel("Port:"),lwgbc);
		
		lwgbc.gridx=2;
		lwgbc.gridy=1;
		lwpanel.add(portTextField,lwgbc);
		
		lwgbc.gridx=1;
		lwgbc.gridy=3;
		lwgbc.gridwidth=2;
		lwpanel.add(lwbutton, lwgbc);
		lwbutton.addActionListener(this);
		
		lw.setVisible(true);
		
//Game Window
		gw=new JFrame();
		gw.setTitle("TTT");
		gw.setSize(600,900);
		gw.setLocation(600,100);
		gw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = (JPanel)gw.getContentPane();
		contentPane.setOpaque(false);
		contentPane.setLayout(new BorderLayout());
	    
		titlePanel=getTitlePanel();
		gamePanel=getGamePanel();
		scorePanel=getScorePanel();
		
		
	    contentPane.add(titlePanel,"North");
	    contentPane.add(gamePanel,"Center");
	    contentPane.add(scorePanel,"South");
	    
	}
	
	private JPanel getTitlePanel(){
		JPanel panel=new JPanel();
		panel.setSize(600,100);
		panel.setLayout(new FlowLayout());
		ImageIcon titleimg=new ImageIcon("src\\GUI_TTT\\title_img.jpg");
		JLabel jl=new JLabel(titleimg);
		panel.add(jl);
		return panel;
	}
	
	private JPanel getGamePanel(){
	    JPanel panel = new JPanel();
	    panel.setSize(600,600);
	    GridBagLayout gb=new GridBagLayout();
	    GridBagConstraints gbc=new GridBagConstraints();
	    panel.setLayout(gb);
	    //gb.setConstraints(panel, gbc);

	    gameGrid=new JLabel[DIV*DIV];
	    for(int i=0;i<DIV*DIV;i++){
	    	gameGrid[i]=new JLabel(new ImageIcon("src\\GUI_TTT\\blankbox.jpg"));
	    	gameGrid[i].addMouseListener(this);
	    }
	    
	    //Layout of Game Panel
	    gbc.gridx=0;
	    gbc.gridy=0;
	    panel.add(new JLabel(new ImageIcon("src\\GUI_TTT\\frame01.jpg")),gbc);
	    for(int i=0;i<DIV;i++){
	    	gbc.gridx=2*i+1;
	    	gbc.gridy=0;
	    	panel.add(new JLabel(new ImageIcon("src\\GUI_TTT\\frame02.jpg")),gbc);
	    	gbc.gridx=2*i+2;
	    	gbc.gridy=0;
	    	panel.add(new JLabel(new ImageIcon("src\\GUI_TTT\\frame01.jpg")),gbc);
	    }
	    for(int i=0;i<DIV;i++){
	    	gbc.gridx=0;
	    	gbc.gridy=2*i+1;
	    	panel.add(new JLabel(new ImageIcon("src\\GUI_TTT\\frame03.jpg")),gbc);
	    	for(int j=0;j<DIV;j++){
	    		gbc.gridx=2*j+1;
	    		gbc.gridy=2*i+1;
	    		panel.add(gameGrid[j+i*DIV],gbc);
	    		gbc.gridx=2*j+2;
	    		gbc.gridy=2*i+1;
	    		panel.add(new JLabel(new ImageIcon("src\\GUI_TTT\\frame03.jpg")),gbc);
	    	}
	    	gbc.gridx=0;
	    	gbc.gridy=2*i+2;
		    panel.add(new JLabel(new ImageIcon("src\\GUI_TTT\\frame01.jpg")),gbc);
		    for(int j=0;j<DIV;j++){
		    	gbc.gridx=2*j+1;
		    	gbc.gridy=2*i+2;
		    	panel.add(new JLabel(new ImageIcon("src\\GUI_TTT\\frame02.jpg")),gbc);
		    	gbc.gridx=2*j+2;
		    	gbc.gridy=2*i+2;
		    	panel.add(new JLabel(new ImageIcon("src\\GUI_TTT\\frame01.jpg")),gbc);
		    }
	    }
	    return panel;
	}
	
	private JPanel getScorePanel(){
		JPanel panel=new JPanel();
		panel.setSize(600,100);
		panel.setLayout(new FlowLayout());
		ImageIcon titleimg=new ImageIcon("src\\GUI_TTT\\score_img.jpg");
		JLabel jl=new JLabel(titleimg);
		panel.add(jl);
		return panel;
	}

	public void mouseClicked(MouseEvent e) {
		Object obj=e.getSource();
		if(obj.getClass()==JLabel.class){
			for(int i=0;i<DIV*DIV;i++){
				if(obj==(Object)gameGrid[i]){
					if(mapData[i]==0){
						System.out.println(obj);
						mapData[i]=currentCount%2+1;
						currentCount++;
						gameGrid[i].setIcon(oxicon[mapData[i]]);
						gw.repaint();
					}
					break;
				}
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==(Object)lwbutton){
			lw.setVisible(false);
			gw.setVisible(true);			
		}
		
	}
	
	
}
