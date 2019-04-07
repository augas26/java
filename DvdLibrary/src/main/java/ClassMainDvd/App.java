
package ClassMainDvd;
import ClassdvdController.ClassDvdController;
import ClassDvd.dao.ClassDvdDao;
import ClassDvd.dao.ClassDvdDaoFileImpl;
import ClassDvd.ui.ClassDvdView;
import ClassDvd.ui.UserIO;
import ClassDvd.ui.UserIOConsoleImpl;


public class App {
	   public static void main(String[] args) {
	        UserIO io = new UserIOConsoleImpl();
	        ClassDvdView view = new ClassDvdView(io);
	        ClassDvdDao dao = new ClassDvdDaoFileImpl() {};
	        ClassDvdController controller = new ClassDvdController(view, dao);
	        controller.run();
	    }

}
