package FlooringMasterDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import FloorMasterDto.Product;

public class ProductFileDaoImpl implements ProductDao {

    private static final String Product_FILE = "Products.txt";
    private static final String DELIMITER = ",";

    @Override
    public List<Product> getAllProducts(Product productType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Product getSingleProduct(String productType) {
        List<Product> products = loadProducts();
        if (products == null) {
            return null;
        } else {
            Product chosenProduct = products.stream()
                    .filter(p -> p.getProductType().equalsIgnoreCase(productType))
                    .findFirst().orElse(null);
            return chosenProduct;
        }

    }

    private List<Product> loadProducts() {
        List productsList = new ArrayList();
        try {

            File ItemFile = new File(Product_FILE);

            if (!ItemFile.exists()) {
                ItemFile.createNewFile();
            }

            Scanner scan = new Scanner(
                    new BufferedReader(
                            new FileReader(ItemFile)));

            while (scan.hasNextLine()) {

                String line = scan.nextLine();
                String[] cells = line.split(",");
                Product product = new Product();
                product.setProductType(cells[0]);
                product.setCostPerSquareFoot(new BigDecimal(cells[1]));
                product.setLaborCostPerSqFt(new BigDecimal(cells[2]));
                productsList.add(product);
            }
        } catch (FileNotFoundException ex) {
            productsList = null;
        } catch (IOException ex) {
            productsList = null;
        }

        return productsList;
    }

}
