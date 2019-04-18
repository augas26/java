package FlooringMasterDao;

import FloorMasterDto.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderDao {

    Map<String, Order> getOrders(LocalDate date);

    Order getSingleOrder(LocalDate dateInfo, String orderNumber);

    Order addOrder(Order ord);

    Order editOrder(Order editedOrder);

    Order removeOrder(Order ord, LocalDate date);

    Map<String, Order> loadOrderByDate(LocalDate date);

    String getMode();

}
