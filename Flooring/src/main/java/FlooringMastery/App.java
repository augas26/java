package FlooringMastery;

import FlooringMasterController.FloorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

//        UserIO io = new UserIOConsoleImpl();
//        FlooringView view = new FlooringView(io);
//        OrderDao dao = new OrderDaoFileImpl();
//        ProductDao product = new ProductFileDaoImpl();
//        StateTaxDao stateTax = new StateTaxDaoImpl();
//        FloorServiceLayer service = new FloorServiceLayer(dao, product,stateTax);
//        FloorController controller = new FloorController(service, view);
//        controller.run();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FloorController controller
                = ctx.getBean("controller", FloorController.class);
        controller.run();
    }
}
