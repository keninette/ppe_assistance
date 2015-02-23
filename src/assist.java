
import com.bll.Employee;
import com.bll.enums.Categories;
import com.dal.JdomXml;
import com.gui.window.LoginWindow;
import com.gui.window.Window;


public class assist {
	public Employee user;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create xml for all categories (if not already created
		Categories[] categories = Categories.values();
		for (Categories c : categories){
			if (! JdomXml.xmlExists(c.toString())){
				JdomXml.xmlCreate(c);
			}	
		}
		
		// Create login window
		Window window = new LoginWindow();
	}
}
