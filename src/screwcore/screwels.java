package screwcore;

public class screwels {
	private String type = "null";
	private int gridcoord = -1;
	private static int xcoord;
	private static int ycoord = -1;
	private boolean toRemove = false;
	
	
	
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
	//Getters
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
	// calculations
	public void calcXY(int gn){
		int x,y  = -1;
		//calc x
		x = gn / 8;
		//calc y
		y = gn-(8*(gn/8));
		xcoord = x;
		ycoord = y;
	}

}
