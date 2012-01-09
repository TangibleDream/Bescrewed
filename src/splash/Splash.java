package splash;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * @Author Robert Hicks
 * @Version 1.0
 * @Date 04/02/10
 * 
 * Just an Initial Welcome screen this will be kludged into any future 
 * applications before the meat of the app begins, saying what it is, 
 * and its build date.
 * 
 */
public class Splash
{
	private JFrame frame;
	private String aname, aversion, abuild;
	public Splash(String name, String version, String build)
	{
		aname = name;
		aversion = version;
		abuild = build;
    }
	public void Splashout(){
		JOptionPane.showMessageDialog(frame, aname + "\nVersion: " + aversion + "\nBuild " + abuild + "\n\nCopyright © 2012 Robert Hicks All Rights Reserved \n\n To find out more about " + aname + " or other applications, email me at darclan28@yahoo.com", "About " + aname , JOptionPane.PLAIN_MESSAGE);
	}
}
