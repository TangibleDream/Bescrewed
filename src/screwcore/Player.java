package screwcore;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Player {
	private String hero;
	private int level = 0;
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
	
	public void setHero(String name){
		hero = name;
	}
	
	public void setLevel(int value){
		level = value;
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
	public String getHero(){
		return hero;
	}
	public int getLevel(){
		return level;
	}
	//JDBC
	public static Connection ConnectSQL(String db, String loc)
	{
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/player";
			Properties props = new Properties();
			props.setProperty("user","razrob");
			props.setProperty("password","password");
			conn = DriverManager.getConnection(url, props);
		}
		catch(ClassNotFoundException g)
		{
			ExceptionCL(g, loc);
		}
		catch(SQLException g)
		{
			ExceptionSQL(g, loc);
		}
	
		return conn;
	}
	public static void ExceptionCL (ClassNotFoundException f, String loc) /*2079 > 1982 (86 lines saved) */
	{
		Component frame = null;
		JOptionPane.showMessageDialog(frame,
				loc + " " + f.getStackTrace() + ", " + f.toString(),
	            "Error",
	            JOptionPane.ERROR_MESSAGE);
	}
	public static void ExceptionSQL(SQLException f, String loc) /*2193 > 2114 (79 lines saved)*/
	{
			Component frame = null;
			JOptionPane.showMessageDialog(frame,
	    			loc + " "+ f.getErrorCode() + ", " + f.toString(),
	                "Error",
	                JOptionPane.ERROR_MESSAGE);   
	}
	public static void ExceptionNP(NullPointerException f, String loc) /*2114 > 2079 (35 lines saved)*/
	{
		Component frame = null;
		JOptionPane.showMessageDialog(frame,
				loc + " " + f.getStackTrace() + ", " + f.toString(),
	            "Error",
	            JOptionPane.ERROR_MESSAGE);
	}
	public static int getPlayerCnt(){
		int count = 0;
		{
			try{
		    	Connection conn = ConnectSQL("player", "getPlayerCnt");
		    	PreparedStatement st = conn.prepareStatement("select count(*) from playerstat;");
		        ResultSet rs = st.executeQuery();
		        while (rs.next()) {
		        	count = rs.getInt(1);
		        }
		        rs.close();
		        st.close();
		        }
			    catch(SQLException e)
		        {
		        	ExceptionSQL(e, "getPlayerCnt");
		        }
		        catch(NullPointerException e)
		        {
		        	ExceptionNP(e, "getPlayerCnt");
		        }	
		}
		return count;
	}
	public static void heroadd(String name){
		try{
	    	Connection conn = ConnectSQL("player", "heroadd");
	    	PreparedStatement st = conn.prepareStatement("insert into playerstat values (null, '" + name + "', 0, 0);");
	        st.execute();
	        st.close();
	        }
		    catch(SQLException e)
	        {
	        	ExceptionSQL(e, "heroadd");
	        }
	        catch(NullPointerException e)
	        {
	        	ExceptionNP(e, "heroadd");
	        }
	}
	public static String getPlayer(int value){
		String streetvalue = "";
		try{
	    	Connection conn = ConnectSQL("player", "getPlayer");
	    	PreparedStatement st = conn.prepareStatement("select playername from playerstat where playernum = "+ value +";");
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	        	streetvalue = rs.getString(1) + " ";
	        }
	        rs.close();
	        st.close();
	        }
		    catch(SQLException e)
	        {
	        	ExceptionSQL(e, "getPlayer");
	        }
	        catch(NullPointerException e)
	        {
	        	ExceptionNP(e, "getPlayer");
	        }	
		
		return streetvalue;
	}
	public static String getLevel(String value){
		String streetvalue = "";
		try{
	    	Connection conn = ConnectSQL("player", "getPlayer");
	    	PreparedStatement st = conn.prepareStatement("select level from playerstat where playername = '"+ value +"';");
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	        	streetvalue = rs.getString(1) + " ";
	        }
	        rs.close();
	        st.close();
	        }
		    catch(SQLException e)
	        {
	        	ExceptionSQL(e, "getPlayer");
	        }
	        catch(NullPointerException e)
	        {
	        	ExceptionNP(e, "getPlayer");
	        }	
		
		return streetvalue;
	}
	public static String getDBExp(String value){
		String streetvalue = "";
		try{
	    	Connection conn = ConnectSQL("player", "getDBExp");
	    	PreparedStatement st = conn.prepareStatement("select experience from playerstat where playername = '"+ value +"';");
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	        	streetvalue = rs.getString(1) + " ";
	        }
	        rs.close();
	        st.close();
	        }
		    catch(SQLException e)
	        {
	        	ExceptionSQL(e, "getDBExp");
	        }
	        catch(NullPointerException e)
	        {
	        	ExceptionNP(e, "getDBExp");
	        }	
		
		return streetvalue;
	}
	public static String getDBGold(String value){
		String streetvalue = "";
		try{
	    	Connection conn = ConnectSQL("player", "getDBGold");
	    	PreparedStatement st = conn.prepareStatement("select gold from playerstat where playername = '"+ value +"';");
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	        	streetvalue = rs.getString(1) + " ";
	        }
	        rs.close();
	        st.close();
	        }
		    catch(SQLException e)
	        {
	        	ExceptionSQL(e, "getDBGold");
	        }
	        catch(NullPointerException e)
	        {
	        	ExceptionNP(e, "getDBGold");
	        }	
		
		return streetvalue;
	}
	public static void setDBexp(String name, int value){
		String streetvalue = "";
		try{
	    	Connection conn = ConnectSQL("player", "setDBexp");
	    	PreparedStatement st = conn.prepareStatement("update `playerstat` set `experience` = " + value + " where `playername` = '"+name +"';");
	        st.execute();
	        st.close();
	        }
		    catch(SQLException e)
	        {
	        	ExceptionSQL(e, "setDBexp");
	        }
	        catch(NullPointerException e)
	        {
	        	ExceptionNP(e, "setDBexp");
	        }
	}
	public static void setDBgold(String name, int value){
		String streetvalue = "";
		try{
	    	Connection conn = ConnectSQL("player", "setDBgold");
	    	PreparedStatement st = conn.prepareStatement("update `playerstat` set `gold` = " + value + " where `playername` = '"+name +"';");
	        st.execute();
	        st.close();
	        }
		    catch(SQLException e)
	        {
	        	ExceptionSQL(e, "setDBgold");
	        }
	        catch(NullPointerException e)
	        {
	        	ExceptionNP(e, "setDBgold");
	        }
	}

}
