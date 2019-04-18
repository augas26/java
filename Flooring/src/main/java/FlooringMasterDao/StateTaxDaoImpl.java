package FlooringMasterDao;

import FloorMasterDto.Tax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Thread.State;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StateTaxDaoImpl implements StateTaxDao {

    private static final String STATES_FILE = "Taxes.txt";
    private static final String DELIMITER = ",";
    Map<String, Tax> Taxes = new HashMap();

    @Override
    public List<Tax> getAllTaxes() {
        loadTaxes();
        return new ArrayList<Tax>(Taxes.values());

    }

    @Override
    public Tax getStateTax(String stateAbbr) {
        List<Tax> stateTax = loadTaxes();
        if (stateTax == null) {
            return null;
        } else {
            Tax chosenState = stateTax.stream()
                    .filter(s -> s.getStateAbbr().equalsIgnoreCase(stateAbbr))
                    .findFirst().orElse(null);
            return chosenState;
        }

    }

    private List<Tax> loadTaxes() {
        List<Tax> taxList = new ArrayList<Tax>();
        try {

            File ItemFile = new File(STATES_FILE);

            if (!ItemFile.exists()) {
                ItemFile.createNewFile();
            }

            Scanner scan = new Scanner(
                    new BufferedReader(
                            new FileReader(ItemFile)));

            while (scan.hasNextLine()) {

                String line = scan.nextLine();
                String[] cells = line.split(",");
                Tax tax = new Tax();
                tax.setStateAbbr(cells[0]);
                tax.setTaxRate(new BigDecimal(cells[1]));
                taxList.add(tax);
            }
        } catch (FileNotFoundException ex) {
            taxList = null;
        } catch (IOException ex) {
            taxList = null;
        }

        return taxList;
    }

}
