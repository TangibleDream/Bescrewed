package screwcore;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class grid extends JPanel{
	private screwels[] screwy;
	private boolean initial = true;
	public grid() {		
		screwy = new screwels[64];
		for (int i = 0; i < 64; i ++){
    		screwy[i] = new screwels();
    		screwy[i].setMColor1(0);}
	}
	public void paintComponent(Graphics g) {
		int count = 0;
		Random rand;
		long now;
		int s = 40;
		BufferedImage black = null;
	    BufferedImage red = null;
	    BufferedImage blue = null;
	    BufferedImage yellow = null;
	    BufferedImage green = null;
	    BufferedImage gold = null;
	    BufferedImage exp = null;
	    BufferedImage rage = null;
	    BufferedImage ired = null;
	    BufferedImage iblue = null;
	    BufferedImage iyellow = null;
	    BufferedImage igreen = null;
	    BufferedImage igold = null;
	    BufferedImage iexp = null;
	    BufferedImage irage = null;
		try {
			black = ImageIO.read(new File("black.jpg"));
			red = ImageIO.read(new File("bigred.jpg"));
			blue = ImageIO.read(new File("blue.jpg"));
			yellow = ImageIO.read(new File("yellow.jpg"));
			green = ImageIO.read(new File("green.jpg"));
			gold = ImageIO.read(new File("gold.jpg"));
			exp = ImageIO.read(new File("purple.jpg"));
			rage = ImageIO.read(new File("skull.gif"));
			ired = ImageIO.read(new File("ibigred.jpg"));
			iblue = ImageIO.read(new File("iblue.jpg"));
			iyellow = ImageIO.read(new File("iyellow.jpg"));
			igreen = ImageIO.read(new File("igreen.jpg"));
			igold = ImageIO.read(new File("igold.jpg"));
			iexp = ImageIO.read(new File("ipurple.jpg"));
			irage = ImageIO.read(new File("iskull.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    g.setColor(Color.black);
	    for (int r = 0; r < 8; r ++){
	    	for (int c = 0; c < 8; c ++){
	    		g.drawRect(s * c, s * r, s, s);
	    	}
	    }
	    now = new Date().getTime();
		rand = new Random(now);
	    for (int r = 0; r < 8; r ++){
	    	for (int c = 0; c < 8; c ++){
	    		/*if (initial){
	    		screwy[count] = new screwels();
	    		screwy[count].setMColor1(0);}*/
	    		switch(screwy[count].getMColor1()){
	    		case -1:
	    			g.drawImage(black, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(-1);
	    			break;
	    		case 0:
	    			g.drawImage(red, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(0);
	    			break;
	    		case 1:
	    			g.drawImage(yellow, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(1);
	    			break;
	    		case 2:
	    			g.drawImage(green, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(2);
	    			break;
	    		case 3:
	    			g.drawImage(blue, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(3);
	    			break;
	    		case 4:
	    			g.drawImage(gold, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(4);
	    			break;
	    		case 5:
	    			g.drawImage(exp, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(5);
	    			break;
	    		case 6:
	    			g.drawImage(rage, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(6);
	    			break;
	    		case 7:
	    			g.drawImage(ired, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(7);
	    			break;
	    		case 8:
	    			g.drawImage(iyellow, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(8);
	    			break;
	    		case 9:
	    			g.drawImage(igreen, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(9);
	    			break;
	    		case 10:
	    			g.drawImage(iblue, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(10);
	    			break;
	    		case 11:
	    			g.drawImage(igold, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(11);
	    			break;
	    		case 12:
	    			g.drawImage(iexp, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(12);
	    			break;
	    		case 13:
	    			g.drawImage(irage, s * c, s * r, s, s, null);
	    			screwy[count].setMColor1(13);
	    			break;
	    		}
	    		count ++;
	    	}
	    }
	 
	    //g.clearRect(0,0,320,320);
	    //g.drawImage(red, 0, 0, 40, 40,  null);
	    //g.drawImage(blue, 40, 0, 40, 40,  null);
	    //g.drawImage(yellow, 80, 0, 40, 40,  null);
	    //g.drawImage(green, 120, 0, 40, 40,  null);
	  }
	 public void inverse(int value){
		 boolean done = false;
		 if (screwy[value].getMColor1() == 0 && done == false){
			 screwy[value].setMColor1(7);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 1 && done == false){
			 screwy[value].setMColor1(8);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 2 && done == false){
			 screwy[value].setMColor1(9);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 3 && done == false){
			 screwy[value].setMColor1(10);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 4 && done == false){
			 screwy[value].setMColor1(11);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 5 && done == false){
			 screwy[value].setMColor1(12);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 6 && done == false){
			 screwy[value].setMColor1(13);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 7 && done == false){
			 screwy[value].setMColor1(0);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 8 && done == false){
			 screwy[value].setMColor1(1);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 9 && done == false){
			 screwy[value].setMColor1(2);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 10 && done == false){
			 screwy[value].setMColor1(3);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 11 && done == false){
			 screwy[value].setMColor1(4);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 12 && done == false){
			 screwy[value].setMColor1(5);
			 done = true;
		 }
		 if (screwy[value].getMColor1() == 13 && done == false){
			 screwy[value].setMColor1(6);
			 done = true;
		 }  	
		 initial = false;
	 }
	 public void setJewel(int jn, int cn){
		 screwy[jn].setMColor1(cn);
	 }
	 public int getJewel(int jn){
		 int value = 0;
		 value = screwy[jn].getMColor1();
		 return value;
	 }
	 public boolean isInverse(){
		 boolean value = false;
		 for(int i = 0; i < 64; i ++){
			 if(getJewel(i) > 6){
				 value = true;
			 }
		 }
		 return value;
	 }
}
