import splash.Splash;
import screwcore.*;


public class bw1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				Splash splash = new Splash("Bescrewed", "1.0", "01/01/2012");
				splash.Splashout();
				Screwview sv = new Screwview();
				sv.screwForm();
	}

}
