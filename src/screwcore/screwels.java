package screwcore;

import java.awt.Color;

public class screwels {
	private String type = "null";
	private int gridcoord = -1;
	private static int xcoord;
	private static int ycoord = -1;
	private boolean toRemove = false;
	private int multiplier = 1;
	private Color mcolor2, mcolor3 = Color.gray;
	private int throttlenumber, mcolor1 = 0;
	private int throttlerate = 10;
	
	
	
	//Setters
	
	public void setType(String value){
		if (value.equals("red")){
			type = "red";
		}
		if (value.equals("blue")){
			type = "blue";
		}
		if (value.equals("green")){
			type = "green";
		}
		if (value.equals("yellow")){
			type = "yellow";
		}
		if (value.equals("gold")){
			type = "gold";
		}
		if (value.equals("wisdom")){
			type = "wisdom";
		}
		if (value.equals("rage")){
			type = "rage";
		}
		if (value.equals("black")){ //(CW-2)
			type = "black";
		}
	} 
	public void setGridNum(int value){
		if (value >= 0 && value < 64){
			gridcoord = value;
		}
	}
	public void setXYcoord(int v1, int v2){
		xcoord = v1;
		ycoord = v2;
	}
	public void setToRemove(boolean choice){
		toRemove = choice;
	}
	public void setMulti(int value){
		multiplier = value;
	}
	public void setMColor1(int value){
		mcolor1 = value;
	}
	public void setMColor2(Color value){
		mcolor2 = value;
	}
	public void setMColor3(Color value){
		mcolor3 = value;
	}
	public void setThrottle(int value){ //CO-18
		throttlenumber = value;
	}
	public void setThrottlerate(int value){//CO-18
		throttlerate = value;
	}
	//Getters
	public int getMColor1(){
		return mcolor1;
	}
	public Color getMColor2(){
		return mcolor2;
	}
	public Color getMColor3(){
		return mcolor3;
	}
	public String getType(){
		return type;
	}
	public int getGridNum(){
		return gridcoord;
	}
	public static int getX(){
		return xcoord;
	}
	public static int getY(){
		return ycoord;
	}
	public boolean ToRemove(){
		return toRemove;
	}
	public int getMulti(){
		return multiplier;
	}
	public int getThrottle(){//CO-18
		return throttlenumber;
	}
	public int getThrottlerate(){//CO-18
		return throttlerate;
	}
	// calculations
	public static void calcXY(int gn){
		int x,y  = -1;
		//calc x
		x = gn / 8;
		//calc y
		y = gn-(8*(gn/8));
		xcoord = x;
		ycoord = y;
	}
}
