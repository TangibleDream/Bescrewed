package screwcore;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;


public class Screwview {
	
	private JFrame frame;
	private JPanel panel, gridpanel, columnpanel, statpanel, cologridpanel, sidepanel;
	private Container contentPane;
	private long now;
	private int rightcount, leftcount, topcount, bottomcount = 0;
	Random c, d;
	private boolean done;
	private screwels[] screwel;
	private JButton[] pbutton;
	private int jewel1 = -1;
	private int jewel2 = -1;
	private int combocnt = 0;
	private Player rob;
	private ImageIcon red, green, blue, yellow;
	private JLabel stattitle, statred, statblue, statyellow, statgreen, statrage, statexp, statgold, gwsmAddHoc;
	private JButton exitbutton;
	private screwels tracker;
	
	
	
	public void screwForm(){
		//final int SIZE = 75;	
		tracker = new screwels();
		tracker.setThrottle(0);
		tracker.setThrottlerate(3);
		rob = new Player();
		screwel = new screwels[64];
		pbutton = new JButton[64];
		ImageIcon red = new ImageIcon("red.jpg");
		ImageIcon blue = new ImageIcon("blue.jpg");
		ImageIcon green = new ImageIcon("green.jpg");
		ImageIcon yellow = new ImageIcon("yellow.jpg");
		for (int i = 0;i < 64; i ++){
			screwel[i] = new screwels();
		}
		frame = new JFrame("Bescrewed");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(568, 455); //574,455 
        //frame.setResizable(false);
        panel = new JPanel();
        panel.setBackground(Color.black);
    	contentPane = (JPanel)frame.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        gridpanel = new JPanel();
        gridpanel.setLayout(new GridLayout(8,8));
        //gridpanel.setPreferredSize(new Dimension(8 * SIZE, 8 * SIZE)); 
        //GRID BUTTONS (IS-1)
		for (int i = 0; i < 64; i ++){
			final int fint = i;
			pbutton[i] = new JButton();
			pbutton[i].addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
            {
        		//JOptionPane.showMessageDialog(frame, "This is okay, Jewel1 = " + jewel1);
        		if (jewel1 == -1){
        			jewel1 = fint;
        			//JOptionPane.showMessageDialog(frame, "You selected " + fint );
        			Border softBevelBorder = new SoftBevelBorder(BevelBorder.RAISED, Color.RED, Color.RED.darker(),
        			        Color.PINK, Color.PINK.brighter());
        			pbutton[fint].setBorder(softBevelBorder);
        		}
        		else if (jewel1 == fint){
        			jewel1 = -1;
        			Border lineBorder = new LineBorder(Color.gray);
        			pbutton[fint].setBorder(lineBorder);
        		}
        		else{
        			jewel2 = fint;
        			//JOptionPane.showMessageDialog(frame, "Jewel2 code is running" + fint );
        			combocnt = 0;
        			switchout();
        			contiguouscheck(false);
        			if (isSwitchable()){
        			blackify();
        			while (isBlack()){
        			gravityfeed();
        			//JOptionPane.showMessageDialog(frame, "Combo number #" + combocnt );
        			combocnt ++;
        			contiguouscheck(false);
        			blackify();
        			/*JOptionPane.showMessageDialog(frame, "Rob's Score \n" +
       					 "Red Mana:     " + rob.getMana(Color.red) + "\n" +
       					 "Blue Mana:    " + rob.getMana(Color.blue) + "\n" +
       					 "Yellow Mana:  " + rob.getMana(Color.yellow) + "\n" +
       					 "Green Mana:   " + rob.getMana(Color.green) + "\n" +
       					 "Rage:  		" + rob.getMana(Color.white) + "\n" +
       					 "Exp:  		" + rob.getMana(Color.magenta) + "\n" +
       					 "Gold: 		" + rob.getMana(Color.orange));*/
        			updatescore();
        			}
        			Border lineBorder = new LineBorder(Color.gray);
        			pbutton[jewel1].setBorder(lineBorder);
        			jewel1 = -1;
        			jewel2 = -2;
        			if (!isMannable()){
        				JOptionPane.showMessageDialog(frame, "Mana Drain");
        				manadrain();
        			}
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(frame, "Poor choice");
        				int h = jewel1;
        				jewel1 = jewel2;
        				jewel2 = h;
        				switchout();
        				Border lineBorder = new LineBorder(Color.gray);
            			pbutton[jewel2].setBorder(lineBorder);
            			jewel1 = -1;
            			jewel2 = -2;
        			}
        		}
            }

			

			private void switchout() {
				// TODO Auto-generated method stub
				Color holdcolor = pbutton[jewel1].getBackground();
				String holdtext = pbutton[jewel1].getText();
				pbutton[jewel1].setBackground(pbutton[jewel2].getBackground());
				pbutton[jewel1].setText(pbutton[jewel2].getText());
				pbutton[jewel2].setBackground(holdcolor);
				pbutton[jewel2].setText(holdtext);
			}});          
		}
		///Hackety Hack
				BufferedImage img = null;
				try {
					img = ImageIO.read(Screwview.class.getResource("red.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				//int width = pbutton[0].getWidth();
				int width = 1000;
				//int height = pbutton[0].getHeight();
				int height = 1000;
				BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);             
					Graphics2D graphics2D = scaledImage.createGraphics();            
					graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);            
					graphics2D.drawImage(img, 0, 0, width, height, null);           
					graphics2D.dispose(); 
					ImageIcon myIcon = new ImageIcon(scaledImage);
					red = myIcon;
				//hack end
		now = new Date().getTime();
		c = new Random(now);
		d = new Random(now);
		for (int i = 0; i < 64; i ++){
			gridpanel.add(pbutton[i]);
			jewelcolor(i);
			
			//pbutton[i].setBackground(Color.black);
		}
		//JOptionPane.showMessageDialog(frame, "This is okay");
		//Initial check for 3+ in a row/column
		contiguouscheck(true);
		blackify();
		while (isBlack()){
		gravityfeed();
		contiguouscheck(true);
		blackify();
		}
		//Fix for exit button (CO-6)
		JPanel sidepanel = new JPanel();
		sidepanel.setBackground(Color.black);
		sidepanel.setLayout(new BoxLayout(sidepanel, BoxLayout.Y_AXIS));
		JPanel cologridpanel = new JPanel();
		cologridpanel.setBackground(Color.black);
		cologridpanel.setLayout(new BoxLayout(cologridpanel, BoxLayout.X_AXIS));
		//Jewel Layout (IS-3)
		JPanel columnpanel = new JPanel();
		columnpanel.setBackground(Color.black);
		columnpanel.setLayout(new BoxLayout(columnpanel, BoxLayout.Y_AXIS));
		JLabel titlelabel = new JLabel("Player Statistics");
		JLabel redlabel = new JLabel("Red Mana:");
		JLabel bluelabel = new JLabel("Blue Mana:");
		JLabel yellowlabel = new JLabel("Yellow Mana:");
		JLabel greenlabel = new JLabel("Green Mana:");
		JLabel ragelabel = new JLabel("Rage");
		JLabel explabel = new JLabel("Exp");
		JLabel goldlabel = new JLabel("Gold");
		titlelabel.setForeground(Color.white);
		titlelabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 14));
		redlabel.setForeground(Color.white);
		redlabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
		bluelabel.setForeground(Color.white);
		bluelabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
		yellowlabel.setForeground(Color.white);
		yellowlabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
		greenlabel.setForeground(Color.white);
		greenlabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
		ragelabel.setForeground(Color.white);
		ragelabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
		explabel.setForeground(Color.white);
		explabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
		goldlabel.setForeground(Color.white);
		goldlabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
		columnpanel.add(titlelabel);
		columnpanel.add(redlabel);
		columnpanel.add(bluelabel);
		columnpanel.add(yellowlabel);
		columnpanel.add(greenlabel);
		columnpanel.add(ragelabel);
		columnpanel.add(explabel);
		columnpanel.add(goldlabel);
		
		JPanel statpanel = new JPanel();
		statpanel.setBackground(Color.black);
		statpanel.setLayout(new BoxLayout(statpanel, BoxLayout.Y_AXIS));
		statred = new JLabel("0000");
		statblue = new JLabel("0000");
		statyellow = new JLabel("0000");
		statgreen = new JLabel("0000");
		statrage = new JLabel("0000");
		statexp = new JLabel("0000");
		statgold = new JLabel("0000");
		statred.setForeground(Color.white);
		statblue.setForeground(Color.white);
		statyellow.setForeground(Color.white);
		statgreen.setForeground(Color.white);
		statrage.setForeground(Color.white);
		statexp.setForeground(Color.white);
		statgold.setForeground(Color.white);
		JButton exitbutton = new JButton("Exit");
		exitbutton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
		statpanel.add(new JLabel("____"));
		statpanel.add(statred);
		statpanel.add(statblue);
		statpanel.add(statyellow);
		statpanel.add(statgreen);
		statpanel.add(statrage);
		statpanel.add(statexp);
		statpanel.add(statgold);
		
        //end here
        panel.add(gridpanel);
        cologridpanel.add(columnpanel);
        cologridpanel.add(statpanel);
        sidepanel.add(cologridpanel);
        sidepanel.add(exitbutton);
        panel.add(sidepanel);
        contentPane.add(panel);
        frame.setVisible(true);
	}



	private boolean isBlack() {
		// TODO Auto-generated method stub
		boolean rval = false;
		for(int i = 0; i < 64; i ++){
			if (pbutton[i].getBackground() == Color.black){
				rval = true;
			}
		}
		return rval;
	}

	

	private void gravityfeed() {
		// TODO Auto-generated method stub
		done = false;
		//JOptionPane.showMessageDialog(frame, "done = " + done);
		int blackcount = 0;
		while(done == false){
			blackcount = 0;
			for(int i = 0; i < 64; i ++){
				if (pbutton[i].getBackground() == Color.black){
					if (i < 8){
						//JOptionPane.showMessageDialog(frame, "Random at top " + i);
						//now =  new Date().getTime();
						//c = new Random(now);
							switch  (c.nextInt(8))
							{
							case 0:
								pbutton[i].setBackground(Color.red);
								screwel[i].setType("red");
								break;
							case 1:
								pbutton[i].setBackground(Color.blue);
								screwel[i].setType("blue");
								break;
							case 2:
								pbutton[i].setBackground(Color.green);
								screwel[i].setType("green");
								break;
							case 3:
								pbutton[i].setBackground(Color.yellow);
								screwel[i].setType("yellow");
								break;
							case 4:
								pbutton[i].setBackground(Color.magenta); //experience
								screwel[i].setType("magenta");
								break;
							case 5:
								pbutton[i].setBackground(Color.white); //rage
								if (d.nextInt(10) < 2){
									pbutton[i].setText("5");
								}
								screwel[i].setType("white");
								break;
							case 6:
								pbutton[i].setBackground(Color.orange); //gold
								screwel[i].setType("orange");
								break;
							case 7:
								if (tracker.getThrottle() == tracker.getThrottlerate()){
									pbutton[i].setBackground(Color.cyan); //multiplier
									int x = d.nextInt(3)+2;
									pbutton[i].setText("X" + x);
									screwel[i].setType("cyan");
									screwel[i].setMulti(x);
									tracker.setThrottle(0);
								}
								else {
									tracker.setThrottle(tracker.getThrottle()+1);
									i = i - 1;
								}
								break;
							}
					}
					else{
						//JOptionPane.showMessageDialog(frame, "Dropdown " + i);
						screwel[i].setType(screwel[i-8].getType());
						pbutton[i].setBackground(pbutton[i-8].getBackground());
						pbutton[i].setText(pbutton[i-8].getText());
						screwel[i-8].setType("black");
						pbutton[i-8].setBackground(Color.black);
						pbutton[i-8].setText("");
					}
					blackcount ++;
					//JOptionPane.showMessageDialog(frame, "done = " + done + " blackcount = " + blackcount);
				}
				
			}
			if (blackcount == 0){
				done = true;
				//JOptionPane.showMessageDialog(frame, "done = " + done + " blackcount = " + blackcount);
			}
		/*while black
		 * move jewels into black spots
		*/
		}
	}



	private void blackify() {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < 64; i ++){
		if (screwel[i].ToRemove()){
			pbutton[i].setBackground(Color.black);
			pbutton[i].setText("");
			screwel[i].setToRemove(false);
			/*frame.pack();
			frame.setSize(431, 341);*/
		}
	}
		/*try

		{
			Thread.sleep(1000);
		} catch (InterruptedException ie)

		{

			ie.printStackTrace();

		}*/ //Doesn't Work
		//JOptionPane.showMessageDialog(frame, "See Black! ");	
	}



	private void contiguouscheck(boolean value) { //boolean is for CL-2
		// TODO Auto-generated method stub
		for (int i = 0; i <64; i ++){  //The process is repeated for each button.
			leftcount = 0;			   //1st Resets counters for contiguous count
			rightcount = 0;
			topcount = 0;
			bottomcount = 0;
			done = false;
			screwel[i].calcXY(i);	  //2nd Calculates XY coordinate.
			while (done == false){
			if ((screwels.getY()+rightcount) < 7){  //3rd determine how many contiguous jewels to the right?
				if (pbutton[i].getBackground() == pbutton[i+1+rightcount].getBackground() || pbutton[i+1+rightcount].getBackground() == Color.cyan){
					rightcount ++;
					//JOptionPane.showMessageDialog(frame, "X = " + screwels.getX() + ",Y = " + screwels.getY()+",Right Count = "+ rightcount + " " + pbutton[i].getBackground());
				}
				else{
				done = true;}
			}
			else{
				done = true;}
			}
			done = false;
			while (done == false){
			if ((screwels.getY()-leftcount) > 0){ //4th determine how many contiguous jewels to the left?
				if (pbutton[i].getBackground() == pbutton[(i-1)-leftcount].getBackground() || pbutton[(i-1)-leftcount].getBackground() == Color.cyan){
					leftcount ++;
					//JOptionPane.showMessageDialog(frame, "X = " + screwels.getX() + ",Y = " + screwels.getY()+",Left Count = "+ leftcount);
				}
				else{
					done = true;}
			}
			else{
				done = true;}
			}
			done = false;
			while (done == false){
			if ((screwels.getX()-topcount) > 0){ //5th determine how many contiguous jewels above?
				if (pbutton[i].getBackground() == pbutton[(i-8)-(8*topcount)].getBackground() || pbutton[(i-8)-(8*topcount)].getBackground() == Color.cyan){
					topcount ++;
					//JOptionPane.showMessageDialog(frame, "X = " + screwels.getX() + ",Y = " + screwels.getY()+",Top Count = "+ topcount);
				}
				else{
					done = true;}
			}
			else{
				done = true;}
			}
			done = false;
			while (done == false){
			if ((screwels.getX()+bottomcount) < 7){ //6th determine how many contiguous jewels below?
				if (pbutton[i].getBackground() == pbutton[(i+8)+(8*bottomcount)].getBackground() || pbutton[i+8+(8*bottomcount)].getBackground() == Color.cyan){
					bottomcount ++;
					//JOptionPane.showMessageDialog(frame, "X = " + screwels.getX() + ",Y = " + screwels.getY()+",Bottom Count = "+ bottomcount);
				}
				else{
					done = true;}
			}
			else{
				done = true;}
			}
			if (bottomcount + topcount > 1 || leftcount + rightcount > 1){ //7th determine if enough contiguous jewels to remove
				screwel[i].setToRemove(true);
				if (pbutton[i].getText()=="5"){
					if (screwels.getY() > 0) screwel[i-1].setToRemove(true);
					if (screwels.getY() < 7) screwel[i+1].setToRemove(true);
					if (screwels.getX() > 0) screwel[i-8].setToRemove(true);
					if (screwels.getX() < 7) screwel[i+8].setToRemove(true);
					if (screwels.getY() > 0 && screwels.getX() > 0) screwel[i-9].setToRemove(true);
					if (screwels.getY() < 7 && screwels.getX() < 7) screwel[i+9].setToRemove(true);
					if (screwels.getY() > 0 && screwels.getX() < 7) screwel[i+7].setToRemove(true);
					if (screwels.getY() < 7 && screwels.getX() > 0) screwel[i-7].setToRemove(true);
				}
				/*Add 5 Bomb Logic here
				 * 
				 * */
				if (value == false){  //CL-2
					rob.setMana(pbutton[i].getBackground(), rob.getMana(pbutton[i].getBackground()) + 1); //8th attribute mana for removed jewels
					if (pbutton[i].getText()=="5"){ //9th allow for 5 rage explosive jewels
						rob.setMana(Color.white, (rob.getMana(Color.white) + 4));
					}
				}
			}
		}
		
	}

	private void jewelcolor(int i) {
		// TODO Auto-generated method stub
		switch  (c.nextInt(7))
		{
		case 0:
			pbutton[i].setBackground(Color.red);
			//pbutton[i].setIcon(red);
			break;
		case 1:
			pbutton[i].setBackground(Color.blue);
			break;
		case 2:
			pbutton[i].setBackground(Color.green);
			break;
		case 3:
			pbutton[i].setBackground(Color.yellow);
			break;
		case 4:
			pbutton[i].setBackground(Color.magenta); //experience
			break;
		case 5:
			pbutton[i].setBackground(Color.white); //rage
			break;
		case 6:
			pbutton[i].setBackground(Color.orange); //gold
			break;
		}
	}
	
	private boolean isMannable() { //For CO-12, CO-13
		boolean value = false;
		int count = 0;
		while (value == false && count < 64){ //1. For each button...
			screwels.calcXY(count);
			if (screwels.getY() < 5)		  //2. Right check
			{
				if (pbutton[count].getBackground() == pbutton[count+1].getBackground() && pbutton[count].getBackground() == pbutton[count+3].getBackground()){
					value = true;			 // 2.1 Flat Across One
				}
				if (pbutton[count].getBackground() == pbutton[count+2].getBackground() && pbutton[count].getBackground() == pbutton[count+3].getBackground()){
					value = true;			 // //2.2 Flat Across Two
				}
				if (screwels.getX() > 0){	 // 2.3 Check for frown.
					if (pbutton[count].getBackground() == pbutton[count-7].getBackground() && pbutton[count].getBackground() == pbutton[count+2].getBackground()){
						value = true;
					}
					if (pbutton[count].getBackground() == pbutton[count-7].getBackground() && pbutton[count].getBackground() == pbutton[count-6].getBackground()){
						value = true;		// 2.4 Backward 7 (BUG?)
					}
					if (pbutton[count].getBackground() == pbutton[count+1].getBackground() && pbutton[count].getBackground() == pbutton[count-6].getBackground()){
						value = true;		// 2.5 Backward L
					}
					
				}
				if (screwels.getX() < 7){	 // 2.6 Check for smile.
					if (pbutton[count].getBackground() == pbutton[count+9].getBackground() && pbutton[count].getBackground() == pbutton[count+2].getBackground()){
						value = true;
					}
					if (pbutton[count].getBackground() == pbutton[count+1].getBackground() && pbutton[count].getBackground() == pbutton[count+10].getBackground()){
						value = true;	     // 2.7 Check 7.
					}
					if (pbutton[count].getBackground() == pbutton[count+9].getBackground() && pbutton[count].getBackground() == pbutton[count+10].getBackground()){
						value = true;	     // 2.8 Check L. (BUG?)
					}
				}
			}
			
			if (screwels.getX() < 5)		  //3. Bottom check
			{
				if (pbutton[count].getBackground() == pbutton[count+8].getBackground() && pbutton[count].getBackground() == pbutton[count+24].getBackground()){
					value = true; // 3.1 Flat Down one
				}
				if (pbutton[count].getBackground() == pbutton[count+16].getBackground() && pbutton[count].getBackground() == pbutton[count+24].getBackground()){
					value = true; // 3.2 Flat Down Two
				}
				if (screwels.getY() <7){
					if (pbutton[count].getBackground() == pbutton[count+9].getBackground() && pbutton[count].getBackground() == pbutton[count+16].getBackground()){
						value = true; // 3.3 Close Parenthesis
					}
					if (pbutton[count].getBackground() == pbutton[count+8].getBackground() && pbutton[count].getBackground() == pbutton[count+17].getBackground()){
						value = true; // 3.4 Long L
					}
					if (pbutton[count].getBackground() == pbutton[count+9].getBackground() && pbutton[count].getBackground() == pbutton[count+17].getBackground()){
						value = true; // 3.5 Long 7
					}
				}
				if (screwels.getY() > 0){
					if (pbutton[count].getBackground() == pbutton[count+7].getBackground() && pbutton[count].getBackground() == pbutton[count+16].getBackground()){
						value = true; // 3.6 Open Parenthesis
					}
					if (pbutton[count].getBackground() == pbutton[count+7].getBackground() && pbutton[count].getBackground() == pbutton[count+15].getBackground()){
						value = true; // 3.7 Long Backwards 7
					}
					if (pbutton[count].getBackground() == pbutton[count+8].getBackground() && pbutton[count].getBackground() == pbutton[count+15].getBackground()){
						value = true; // 3.8 J
					}
				}
			}
			
		count ++;
		}
		return value;
	}
	private void manadrain(){
		for (int i = 0; i < 64; i ++){
			pbutton[i].setBackground(Color.black);
		}
		contiguouscheck(false);
		if (isSwitchable()){
		blackify();
		while (isBlack()){
		gravityfeed();
		//JOptionPane.showMessageDialog(frame, "Combo number #" + combocnt );
		combocnt ++;
		contiguouscheck(false);
		blackify();
		rob.setMana(Color.red, 0);
		rob.setMana(Color.blue, 0);
		rob.setMana(Color.yellow, 0);
		rob.setMana(Color.green, 0);
		updatescore();
	}
		}}
		private boolean isSwitchable() {
			// TODO Auto-generated method stub
			boolean sval = false;
			for(int i = 0; i < 64; i ++){
				if (screwel[i].ToRemove()){
					sval = true;
				}
			}
			return sval;
	}
		public void updatescore(){
			statred.setText(String.valueOf(rob.getMana(Color.red)));
			statblue.setText(String.valueOf(rob.getMana(Color.blue)));
			statyellow.setText(String.valueOf(rob.getMana(Color.yellow)));
			statgreen.setText(String.valueOf(rob.getMana(Color.green)));
			statrage.setText(String.valueOf(rob.getMana(Color.white)));
			statexp.setText(String.valueOf(rob.getMana(Color.magenta)));
			statgold.setText(String.valueOf(rob.getMana(Color.orange)));
		}
}
