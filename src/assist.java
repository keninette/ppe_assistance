
import com.bll.Employee;
import com.bll.enums.Categories;
import com.dal.JdomXml;
import com.gui.window.LoginWindow;


public class assist {
	public Employee user;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create xml for all categories (if not already created)
		Categories[] cat = Categories.values();
		for (Categories c : cat){
			if (! JdomXml.xmlExists(c.toString())){
				JdomXml.xmlCreate(c);
			}	
		}
		
		// Create login window
		new LoginWindow();
	}
}
