package App;

import Controller.VendingMachineController;
import com.vendingmachine.services.NoInventoryException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

    public static void main(String[] args) throws NoInventoryException {
//
//        UserIO io = new UserIOImpl();
//        VendingDao dao = new vendingDaoImpl("items.txt");
//        VendingMachineView view = new VendingMachineView(io);
//        VendingMachineService service = new VendingMachineService(dao);
//        VendingMachineController controller = new VendingMachineController(view);
//        controller.run();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }

}
