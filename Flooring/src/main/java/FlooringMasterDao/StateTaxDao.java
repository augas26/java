package FlooringMasterDao;

import FloorMasterDto.Tax;
import java.util.List;

/**
 *
 * @author abdisamadugas
 */
public interface StateTaxDao {

    List<Tax> getAllTaxes();

    Tax getStateTax(String tax);

}
