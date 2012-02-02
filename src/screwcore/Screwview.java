package screwcore;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Screwview {
	
	private JFrame frame;
	private JPanel panel, selectionpanel, heroaddpanel, columnpanel, statpanel, cologridpanel, sidepanel, newselectpanel;
	private Container contentPane;
	private long now;
	private int rightcount, leftcount, topcount, bottomcount = 0;
	Random c, d;
	private boolean done;
	private screwels[] screwel;
	private int jewel1 = -1;
	private int jewel2 = -1;
	private int combocnt = 0;
	private Player rob;
	private JLabel namelabel, statred, statblue, statyellow, statgreen, statrage, statexp, statgold, titlelabel;
	private JButton exitbutton, newbutton, selectbutton, submitbutton;
	private JTextField heronamefield;
	private screwels tracker;
	private JComboBox playcombo;
	private String hero;
	/*
	 *  Application width and height
	 *  
	 * */
	private int appWidth = 715;
	private int appHeight = 534;
	final grid gridly = new grid();
	
	
	public Screwview(){
	}
	/*
	 * Section One:  M A I N   V I E W
	 * 
	 */
	public void screwForm(){
		tracker = new screwels();
		tracker.setThrottle(0);
		tracker.setThrottlerate(10);
		rob = new Player();
		
		/*
		 * 
		 * 1.1 Grid Action listener
		 * 
		 * */
		
		gridly.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//JOptionPane.showMessageDialog(frame, "X = " + e.getX() + ", Y = " + e.getY());
				//JOptionPane.showMessageDialog(frame, "grid number is = " + jewelnumber(40,e.getX(),e.getY()));
				int winsize = 62;
				if(!gridly.isInverse()){
				gridly.inverse(jewelnumber(winsize,e.getX(),e.getY()));
				gridly.repaint();
				jewel1 = jewelnumber(winsize,e.getX(),e.getY());}
				else{
					if(jewelnumber(winsize,e.getX(),e.getY()) == jewel1){
						gridly.inverse(jewelnumber(winsize,e.getX(),e.getY()));
						jewel1 = -1;
						gridly.repaint();
					}
					else{
	        			jewel2 = jewelnumber(winsize,e.getX(),e.getY());
	        			//JOptionPane.showMessageDialog(frame, "Jewel2 code is running" + fint );
	        			combocnt = 0;
	        			gridly.inverse(jewel1);
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
	        			updatescore();
	        			}
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
	            			jewel1 = -1;
	            			jewel2 = -2;
	        			}
	        		}
					
				}
					
				
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
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		screwel = new screwels[64];
		for (int i = 0;i < 64; i ++){
			screwel[i] = new screwels();
		}
		frame = new JFrame("Bescrewed");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        // Gabe note: add a layout to "panel"
    	contentPane = (JPanel)frame.getContentPane();
    	contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
    	contentPane.setBackground(Color.black);
        //GRID BUTTONS (IS-1)
          
		
		now = new Date().getTime();
		c = new Random(now);
		d = new Random(now);
		
		//Fix for exit button (CO-6)
		sidepanel = new JPanel();
		sidepanel.setBackground(Color.black);
		sidepanel.setLayout(new BoxLayout(sidepanel, BoxLayout.Y_AXIS));
		cologridpanel = new JPanel();
		cologridpanel.setBackground(Color.black);
		cologridpanel.setLayout(new BoxLayout(cologridpanel, BoxLayout.X_AXIS));
		//Jewel Layout (IS-3)
		columnpanel = new JPanel();
		columnpanel.setBackground(Color.black);
		columnpanel.setLayout(new BoxLayout(columnpanel, BoxLayout.Y_AXIS));
		namelabel = new JLabel("Player Level 0");
		titlelabel = new JLabel("Player Statistics");
		JLabel redlabel = new JLabel("Red Mana:");
		JLabel bluelabel = new JLabel("Blue Mana:");
		JLabel yellowlabel = new JLabel("Yellow Mana:");
		JLabel greenlabel = new JLabel("Green Mana:");
		JLabel ragelabel = new JLabel("Rage");
		JLabel explabel = new JLabel("Exp");
		JLabel goldlabel = new JLabel("Gold");
		namelabel.setForeground(Color.white);
		namelabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 14));
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
		columnpanel.add(namelabel);
		columnpanel.add(titlelabel);
		columnpanel.add(redlabel);
		columnpanel.add(bluelabel);
		columnpanel.add(yellowlabel);
		columnpanel.add(greenlabel);
		columnpanel.add(ragelabel);
		columnpanel.add(explabel);
		columnpanel.add(goldlabel);
		
		statpanel = new JPanel();
		statpanel.setBackground(Color.black);
		statpanel.setLayout(new BoxLayout(statpanel, BoxLayout.Y_AXIS));
		statred = new JLabel("0");
		statblue = new JLabel("0");
		statyellow = new JLabel("0");
		statgreen = new JLabel("0");
		statrage = new JLabel("0");
		statexp = new JLabel("0");
		statgold = new JLabel("0");
		statred.setForeground(Color.white);
		statblue.setForeground(Color.white);
		statyellow.setForeground(Color.white);
		statgreen.setForeground(Color.white);
		statrage.setForeground(Color.white);
		statexp.setForeground(Color.white);
		statgold.setForeground(Color.white);
		exitbutton = new JButton("Exit");
		exitbutton.setBackground(Color.BLACK);
		exitbutton.setForeground(Color.white);
		exitbutton.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
		exitbutton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
            {
        		//JOptionPane.showMessageDialog(frame, "x = " + frame.getWidth() + " y = " + frame.getHeight());	
                System.exit(0);
            }
        });
		statpanel.add(new JLabel("____"));
		statpanel.add(new JLabel("____"));
		statpanel.add(statred);
		statpanel.add(statblue);
		statpanel.add(statyellow);
		statpanel.add(statgreen);
		statpanel.add(statrage);
		statpanel.add(statexp);
		statpanel.add(statgold);
		/*
		 *1.2 Hero Selection
		 * 
		 * */
		selectionpanel = new JPanel(); //(CW-8)
		selectionpanel.setBackground(Color.black);
        selectionpanel.setLayout(new BoxLayout(selectionpanel, BoxLayout.Y_AXIS));
        playcombo = new JComboBox();
        Dimension width = new Dimension();
        width.setSize(10, 10);
        playcombo.setPreferredSize(width);
        for(int i = 0; i <= Player.getPlayerCnt(); i ++){
        	playcombo.addItem(Player.getPlayer(i));
        }
        selectbutton = new JButton("Select");
        selectbutton.setBackground(Color.BLACK);
        selectbutton.setForeground(Color.white);
        selectbutton.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
        selectbutton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
            {
        		if(playcombo.getSelectedItem().toString() != ""){
        		namelabel.setText(playcombo.getSelectedItem().toString() + " - Level " + Player.getLevel(playcombo.getSelectedItem().toString()) + " - Life 20");
        		//gridpanel.setVisible(true);
        		gridly.setVisible(true);
                selectionpanel.setVisible(false);
                hero = playcombo.getSelectedItem().toString();
                statexp.setText(Player.getDBExp(hero));
                rob.setMana(5, Integer.parseInt(Player.getDBExp(hero).trim()));
                statgold.setText(Player.getDBGold(hero));
                rob.setMana(4, Integer.parseInt(Player.getDBGold(hero).trim()));
            }}
        });
        newbutton = new JButton("New Hero");
        newbutton.setBackground(Color.BLACK);
        newbutton.setForeground(Color.white);
        newbutton.setFont(new Font("Monotype Corsiva", Font.PLAIN, 14));
        newbutton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
            {
        		heroaddpanel.setVisible(true);
                selectionpanel.setVisible(false);
            }
        });
        selectionpanel.add(playcombo);
        newselectpanel = new JPanel();
        newselectpanel.setBackground(Color.black);
        newselectpanel.setLayout(new BoxLayout(newselectpanel, BoxLayout.X_AXIS));
        newselectpanel.add(newbutton);
        newselectpanel.add(selectbutton);
        selectionpanel.add(newselectpanel);
        /*
		 *1.3 Hero Creation
		 * 
		 * */
        heroaddpanel = new JPanel(); //(CW-8)
        heroaddpanel.setLayout(new BoxLayout(heroaddpanel, BoxLayout.Y_AXIS));
        heronamefield = new JTextField("Add hero name");
        submitbutton = new JButton("Submit");
        submitbutton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
            {
        		Player.heroadd(heronamefield.getText().toString());
        		rob.setHero(heronamefield.getText().toString());
        		rob.setLevel(1);
        		//gridpanel.setVisible(true);
        		gridly.setVisible(true);
        		heroaddpanel.setVisible(false);
        		namelabel.setText(rob.getHero() + " - Level 1 - Life 20");
        		hero = rob.getHero();
        		
            }
        });
        heroaddpanel.add(heronamefield);
        heroaddpanel.add(submitbutton);
        //selectionpanel.setVisible(false); // temporary.
        //end here
        //panel.add(canvaspanel);
		panel.add(selectionpanel);
		panel.add(heroaddpanel);
		heroaddpanel.setVisible(false);
        cologridpanel.add(columnpanel);
        cologridpanel.add(statpanel);
        sidepanel.add(cologridpanel);
        sidepanel.add(exitbutton);
        panel.add(sidepanel);
        contentPane.add(gridly);
        gridly.setVisible(false);
        for(int i = 0; i < 64; i ++){
        	gridly.setJewel(i, c.nextInt(7));
        }
        contiguouscheck(true);
        blackify();
        while (isBlack()){
    		gravityfeed();
    		contiguouscheck(true);
    		blackify();
    		}
        gridly.repaint();
        contentPane.add(panel);
        //frame.add(jewelgrid);
        frame.setSize(appWidth, appHeight); //867,455 
        frame.setVisible(true);
	}

/*
 * Section Two:  S U P P O R T I N G   M E T H O D S
 * 
 */	

	private boolean isBlack() {
		// TODO Auto-generated method stub
		boolean rval = false;
		for(int i = 0; i < 64; i ++){
			if (gridly.getJewel(i) == -1){
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
				//if (pbutton[i].getBackground() == Color.black){
				if (gridly.getJewel(i) == -1){
					if (i < 8){
						//JOptionPane.showMessageDialog(frame, "Random at top " + i);
						//now =  new Date().getTime();
						//c = new Random(now);
							switch  (c.nextInt(8))
							{
							case 0:
								gridly.setJewel(i, 0);
								break;
							case 1:
								gridly.setJewel(i, 1);
								break;
							case 2:
								gridly.setJewel(i, 2);
								break;
							case 3:
								gridly.setJewel(i, 3);
								break;
							case 4:
								gridly.setJewel(i, 4);
								break;
							case 5:
								gridly.setJewel(i, 5);
								break;
							case 6:
								gridly.setJewel(i, 6);
								if (d.nextInt(10) < 2){
									gridly.setJewel(i, -2);
								}
								break;
							case 7:
								if (tracker.getThrottle() == tracker.getThrottlerate()){
									switch (d.nextInt(3)){
									case 0:
										gridly.setJewel(i, -3);
										screwel[i].setMulti(3);
										tracker.setThrottle(0);
										break;
									case 1:
										gridly.setJewel(i, -4);
										screwel[i].setMulti(4);
										tracker.setThrottle(0);
										break;
									case 2:
										gridly.setJewel(i, -5);
										screwel[i].setMulti(5);
										tracker.setThrottle(0);
										break;
										}
									
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
						gridly.setJewel(i, gridly.getJewel(i-8));
						gridly.setJewel(i-8,-1);
					}
					blackcount ++;
					//JOptionPane.showMessageDialog(frame, "done = " + done + " blackcount = " + blackcount);
				}
				
			}
			if (blackcount == 0){
				done = true;
				//JOptionPane.showMessageDialog(frame, "done = " + done + " blackcount = " + blackcount);
			}
			/*try

			{
				Thread.sleep(125);
			} catch (InterruptedException ie)

			{

				ie.printStackTrace();

			} //Doesn't Work
		/*while black
		 * move jewels into black spots
		*/
		}
	}



	private void blackify() {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < 64; i ++){
		if (screwel[i].ToRemove()){
			gridly.setJewel(i, -1);
			screwel[i].setToRemove(false);
		}
	}
		
			
		gridly.repaint();
		//JOptionPane.showMessageDialog(frame, "See Black! ");
	}
	
	/*
	 * 2.1 Contiguous Check
	 * 	This method runs through the board looking for completed rows or columns.  If it finds any it will mark them for removal 
	 * 	and tally manna
	 * 	Also handles behavior for 5 rage and multiplier gems.
	 * 
	 * */


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
				//JOptionPane.showMessageDialog(frame, "i = " + i + ",left = " + leftcount + ",right = " + rightcount + ",top = " + topcount + ",bottom = " + bottomcount + ",jewelcolor = " + gridly.getJewel(i));
			if ((screwels.getY()+rightcount) < 7){  //3rd determine how many contiguous jewels to the right?
				if(gridly.getJewel(i) == -2  || gridly.getJewel(i) == 6){
					if(gridly.getJewel(i+1+rightcount) == -2 || gridly.getJewel(i+1+rightcount) == 6 || gridly.getJewel(i+1+rightcount) < -2){
						rightcount ++;
						//JOptionPane.showMessageDialog(frame, "5 skull logic on the right count occurs " + rightcount);
					}
					else{
						done = true;}
					//JOptionPane.showMessageDialog(frame, "A right check for 6 did occur. right = " + rightcount);
				}
				else{
				//if (pbutton[i].getBackground() == pbutton[i+1+rightcount].getBackground() || pbutton[i+1+rightcount].getBackground() == Color.cyan){
				if (gridly.getJewel(i) == gridly.getJewel(i+1+rightcount) || gridly.getJewel(i+1+rightcount) < -2){
					rightcount ++;
					//JOptionPane.showMessageDialog(frame, "rightcount = " + rightcount);
					//JOptionPane.showMessageDialog(frame, "X = " + screwels.getX() + ",Y = " + screwels.getY()+",Right Count = "+ rightcount + " " + pbutton[i].getBackground());
				}
				else{
				done = true;}
				}}
			else{
				done = true;}
			}
			done = false;
			while (done == false){
			if ((screwels.getY()-leftcount) > 0){ //4th determine how many contiguous jewels to the left?
				if(gridly.getJewel(i)== -2  || gridly.getJewel(i)== 6){
					if (gridly.getJewel((i-1)-leftcount) == -2 || gridly.getJewel((i-1)-leftcount) == 6 || gridly.getJewel((i-1)-leftcount) < -2){
						leftcount ++;
						//JOptionPane.showMessageDialog(frame, "X = " + screwels.getX() + ",Y = " + screwels.getY()+",Left Count = "+ leftcount);
					}
					else{
						done = true;}
				}
				else{
				//if (pbutton[i].getBackground() == pbutton[(i-1)-leftcount].getBackground() || pbutton[(i-1)-leftcount].getBackground() == Color.cyan){
				if (gridly.getJewel(i) == gridly.getJewel((i-1)-leftcount) || gridly.getJewel((i-1)-leftcount) < -2){
					leftcount ++;
					//JOptionPane.showMessageDialog(frame, "X = " + screwels.getX() + ",Y = " + screwels.getY()+",Left Count = "+ leftcount);
				}
				else{
					done = true;}
			}}
			else{
				done = true;}
			}
			done = false;
			while (done == false){
			if ((screwels.getX()-topcount) > 0){ //5th determine how many contiguous jewels above?
				if(gridly.getJewel(i)== -2  || gridly.getJewel(i)== 6){
					if (gridly.getJewel((i-8)-(8*topcount)) == -2 || gridly.getJewel((i-8)-(8*topcount)) == 6 || gridly.getJewel((i-8)-(8*topcount)) < -2){
						topcount ++;
						//JOptionPane.showMessageDialog(frame, "X = " + screwels.getX() + ",Y = " + screwels.getY()+",Top Count = "+ topcount);
					}
					else{
						done = true;}
				}
				else{
				//if (pbutton[i].getBackground() == pbutton[(i-8)-(8*topcount)].getBackground() || pbutton[(i-8)-(8*topcount)].getBackground() == Color.cyan){
				if (gridly.getJewel(i) == gridly.getJewel((i-8)-(8*topcount)) || gridly.getJewel((i-8)-(8*topcount)) < -2){
					topcount ++;
					//JOptionPane.showMessageDialog(frame, "X = " + screwels.getX() + ",Y = " + screwels.getY()+",Top Count = "+ topcount);
				}
				else{
					done = true;}
			}}
			else{
				done = true;}
			}
			done = false;
			while (done == false){
			if ((screwels.getX()+bottomcount) < 7){ //6th determine how many contiguous jewels below?
				if(gridly.getJewel(i)== -2  || gridly.getJewel(i)== 6){
					if (gridly.getJewel((i+8)+(8*bottomcount)) == -2 || gridly.getJewel((i+8)+(8*bottomcount)) == 6 || gridly.getJewel((i+8)+(8*bottomcount)) < -2){
						bottomcount ++;
						//JOptionPane.showMessageDialog(frame, "X = " + screwels.getX() + ",Y = " + screwels.getY()+",Bottom Count = "+ bottomcount);
					}
					else{
						done = true;}
				}
				else{
				//if (pbutton[i].getBackground() == pbutton[(i+8)+(8*bottomcount)].getBackground() || pbutton[i+8+(8*bottomcount)].getBackground() == Color.cyan){
				if (gridly.getJewel(i) == gridly.getJewel((i+8)+(8*bottomcount)) || gridly.getJewel((i+8)+(8*bottomcount)) < -2){
					bottomcount ++;
					//JOptionPane.showMessageDialog(frame, "X = " + screwels.getX() + ",Y = " + screwels.getY()+",Bottom Count = "+ bottomcount);
				}
				else{
					done = true;}
			}}
			else{
				done = true;}
			}
			if (bottomcount + topcount > 1 || leftcount + rightcount > 1){ //7th determine if enough contiguous jewels to remove
				screwel[i].setToRemove(true);
				if (gridly.getJewel(i) == -2){
					if (screwels.getY() > 0) {
						if (!screwel[i-1].ToRemove()){
						if (value == false){  //CL-2
							rob.setMana(gridly.getJewel(i-1), rob.getMana(gridly.getJewel(i-1)) + 1);
							if (gridly.getJewel(i-1) == -2){
								rob.setMana(6, (rob.getMana(6) + 5));
							}
						}
						screwel[i-1].setToRemove(true);
						}}
					if (screwels.getY() < 7) {
						if (!screwel[i+1].ToRemove()){
						if (value == false){  //CL-2
							rob.setMana(gridly.getJewel(i+1), rob.getMana(gridly.getJewel(i+1)) + 1);
							if (gridly.getJewel(i+1) == -2){
								rob.setMana(6, (rob.getMana(6) + 5));
							}
						}
						screwel[i+1].setToRemove(true);
						}
					}
					if (screwels.getX() > 0) {
						if (!screwel[i-8].ToRemove()){
						if (value == false){  //CL-2
							rob.setMana(gridly.getJewel(i-8), rob.getMana(gridly.getJewel(i-8)) + 1);
							if (gridly.getJewel(i-8) == -2){
								rob.setMana(6, (rob.getMana(6) + 5));
							}
						}
						screwel[i-8].setToRemove(true);
						}
					}
					if (screwels.getX() < 7) {
						if (!screwel[i+8].ToRemove()){
						if (value == false){  //CL-2
							rob.setMana(gridly.getJewel(i+8), rob.getMana(gridly.getJewel(i+8)) + 1);
							if (gridly.getJewel(i+8) == -2){
								rob.setMana(6, (rob.getMana(6) + 5));
							}
						}
						screwel[i+8].setToRemove(true);}
					}
					if (screwels.getY() > 0 && screwels.getX() > 0) {
						if (!screwel[i-9].ToRemove()){
						if (value == false){  //CL-2
							rob.setMana(gridly.getJewel(i-9), rob.getMana(gridly.getJewel(i-9)) + 1);
							if (gridly.getJewel(i-9) == -2){
								rob.setMana(6, (rob.getMana(6) + 5));
							}
						}
						screwel[i-9].setToRemove(true);}
					}
					if (screwels.getY() < 7 && screwels.getX() < 7) {
						if (!screwel[i+9].ToRemove()){
						if (value == false){  //CL-2
							rob.setMana(gridly.getJewel(i+9), rob.getMana(gridly.getJewel(i+9)) + 1);
							if (gridly.getJewel(i+9) == -2){
								rob.setMana(6, (rob.getMana(6) + 5));
							}
						}
						screwel[i+9].setToRemove(true);}
					}
					if (screwels.getY() > 0 && screwels.getX() < 7) {
						if (!screwel[i+7].ToRemove()){
						if (value == false){  //CL-2
							rob.setMana(gridly.getJewel(i+7), rob.getMana(gridly.getJewel(i+7)) + 1);
							if (gridly.getJewel(i+7) == -2){
								rob.setMana(6, (rob.getMana(6) + 5));
							}
						}
						screwel[i+7].setToRemove(true);}
					}
					if (screwels.getY() < 7 && screwels.getX() > 0) {
						if (!screwel[i-7].ToRemove()){
						if (value == false){  //CL-2
							rob.setMana(gridly.getJewel(i-7), rob.getMana(gridly.getJewel(i-7)) + 1);
							if (gridly.getJewel(i-7) == -2){
								rob.setMana(6, (rob.getMana(6) + 5));
							}
						}
						screwel[i-7].setToRemove(true);}
					}
				}
				/*Add 5 Bomb Logic here
				 * 
				 * */
				if (value == false){  //CL-2
					rob.setMana(gridly.getJewel(i), rob.getMana(gridly.getJewel(i)) + 1); //8th attribute mana for removed jewels
					if (gridly.getJewel(i) == -2){ //9th allow for 5 rage explosive jewels
						rob.setMana(6, (rob.getMana(6) + 4));
					}
				}
			}
		}
		
	}
	
	/*
	 * 2.2 Is Mannable?
	 * 
	 * Indicator for future moves vs. a re randomization called manna drain.
	 * 
	 * */

	
	private boolean isMannable() { //For CO-12, CO-13
		boolean value = false;
		int count = 0;
		while (value == false && count < 64){ //1. For each button...
			screwels.calcXY(count);
			if (screwels.getY() < 5)		  //2. Right check
			{
				//if (pbutton[count].getBackground() == pbutton[count+1].getBackground() && pbutton[count].getBackground() == pbutton[count+3].getBackground()){
				if ((gridly.getJewel(count) == gridly.getJewel(count+1) || gridly.getJewel(count+1) < -2) && ((gridly.getJewel(count) == (gridly.getJewel(count+3)) || (gridly.getJewel(count+3) < -2 )))){
					value = true;			 // 2.1 Flat Across One
				}
				if ((gridly.getJewel(count) == gridly.getJewel(count+2) || gridly.getJewel(count+2) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+3) || gridly.getJewel(count+3) < -2)){
					value = true;			 // //2.2 Flat Across Two
				}
				if (screwels.getX() > 0){	 // 2.3 Check for frown.
					if ((gridly.getJewel(count) == gridly.getJewel(count-7) || gridly.getJewel(count-7) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+2) || gridly.getJewel(count+2) < -2)){
						value = true;
					}
					if ((gridly.getJewel(count) == gridly.getJewel(count-7) || gridly.getJewel(count-7) < -2) && (gridly.getJewel(count) == gridly.getJewel(count-6) || gridly.getJewel(count-6) < -2)){
						value = true;		// 2.4 Backward 7 (BUG?)
					}
					if ((gridly.getJewel(count) == gridly.getJewel(count+1) || gridly.getJewel(count+1) < -2) && (gridly.getJewel(count) == gridly.getJewel(count-6) || gridly.getJewel(count-6) < -2)){
						value = true;		// 2.5 Backward L
					}
					
				}
				if (screwels.getX() < 7){	 // 2.6 Check for smile.
					if ((gridly.getJewel(count) == gridly.getJewel(count+9) || gridly.getJewel(count+9) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+2) || gridly.getJewel(count+2) < -2)){
						value = true;
					}
					if ((gridly.getJewel(count) == gridly.getJewel(count+1) || gridly.getJewel(count+1) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+10) || gridly.getJewel(count+10) < -2)){
						value = true;	     // 2.7 Check 7.
					}
					if ((gridly.getJewel(count) == gridly.getJewel(count+9) || gridly.getJewel(count+9) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+10) || gridly.getJewel(count+10) < -2)){
						value = true;	     // 2.8 Check L. (BUG?)
					}
				}
			}
			
			if (screwels.getX() < 5)		  //3. Bottom check
			{
				if ((gridly.getJewel(count) == gridly.getJewel(count+8) || gridly.getJewel(count+8) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+24) || gridly.getJewel(count+24) < -2)){
					value = true; // 3.1 Flat Down one
				}
				if ((gridly.getJewel(count) == gridly.getJewel(count+16) || gridly.getJewel(count+16) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+24) || gridly.getJewel(count+24) < -2)){
					value = true; // 3.2 Flat Down Two
				}
				if (screwels.getY() <7){
					if ((gridly.getJewel(count) == gridly.getJewel(count+9) || gridly.getJewel(count+9) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+16) || gridly.getJewel(count+16) < -2)){
						value = true; // 3.3 Close Parenthesis
					}
					if ((gridly.getJewel(count) == gridly.getJewel(count+8) || gridly.getJewel(count+8) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+17) || gridly.getJewel(count+17) < -2)){
						value = true; // 3.4 Long L
					}
					if ((gridly.getJewel(count) == gridly.getJewel(count+9) || gridly.getJewel(count+9) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+17) || gridly.getJewel(count+17) < -2)){
						value = true; // 3.5 Long 7
					}
				}
				if (screwels.getY() > 0){
					if ((gridly.getJewel(count) == gridly.getJewel(count+7) || gridly.getJewel(count+7) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+16) || gridly.getJewel(count+16) < -2)){
						value = true; // 3.6 Open Parenthesis
					}
					if ((gridly.getJewel(count) == gridly.getJewel(count+7) || gridly.getJewel(count+7) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+15) || gridly.getJewel(count+15) < -2)){
						value = true; // 3.7 Long Backwards 7
					}
					if ((gridly.getJewel(count) == gridly.getJewel(count+8) || gridly.getJewel(count+8) < -2) && (gridly.getJewel(count) == gridly.getJewel(count+15) || gridly.getJewel(count+15) < -2)){
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
			//pbutton[i].setBackground(Color.black);
			gridly.setJewel(i, -1);
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
		rob.setMana(0, 0);
		rob.setMana(1, 0);
		rob.setMana(2, 0);
		rob.setMana(3, 0);
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
			statred.setText(String.valueOf(rob.getMana(0)));
			statblue.setText(String.valueOf(rob.getMana(3)));
			statyellow.setText(String.valueOf(rob.getMana(1)));
			statgreen.setText(String.valueOf(rob.getMana(2)));
			statrage.setText(String.valueOf(rob.getMana(6)));
			statexp.setText(String.valueOf(rob.getMana(5)));
			Player.setDBexp(hero, Integer.parseInt(statexp.getText().toString()));
			statgold.setText(String.valueOf(rob.getMana(4)));
			Player.setDBgold(hero, Integer.parseInt(statgold.getText().toString()));
		}
		
		public int jewelnumber(int size,int x, int y){
			int value = 0;
			
			value = (x/size)+(8*((y/size)));
			return value;
		}
		
		private void switchout() {
			// TODO Auto-generated method stub
			int holdcolor = gridly.getJewel(jewel1);
			gridly.setJewel(jewel1, gridly.getJewel(jewel2));
			gridly.setJewel(jewel2, holdcolor);
		}
		
}
