package screwcore;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Player {
	private int yellowmana, redmana, bluemana, greenmana, experience, rage, gold = 0;
	private int life = 20;
	private JFrame frame;
	
	//Setters
	public void setMana(Color clr, int value){
		//JOptionPane.showMessageDialog(frame, "Manacolor = " + clr);
		if (clr == Color.red) redmana = value;
		if (clr == Color.blue) bluemana = value;
		if (clr == Color.yellow) yellowmana = value;
		if (clr == Color.green) greenmana = value;
		if (clr == Color.white) rage = value;
		if (clr == Color.magenta) experience = value;
		if (clr == Color.orange) gold = value;
	}
	
	public void setExp(int value){
		experience = value;
	}
	
	public void setRage(int value){
		rage = value;
	}
	
	public void setLife(int value){
		life = value;
	}
	//Getters
	
	public int getMana(Color clr){
		//JOptionPane.showMessageDialog(frame, "value = " + clr);
		int mana = -1;
		if (clr == Color.red) mana = redmana;
		if (clr == Color.blue) mana = bluemana;
		if (clr == Color.yellow) mana = yellowmana;
		if (clr == Color.green) mana = greenmana;
		if (clr == Color.white) mana = rage;
		if (clr == Color.magenta) mana = experience;
		if (clr == Color.orange) mana = gold;
		return mana;
	}
	
	public int getExp(){
		return experience;
	}
	
	public int getRage(){
		return rage;
	}
	
	public int getLife(){
		return life;
	}

}
