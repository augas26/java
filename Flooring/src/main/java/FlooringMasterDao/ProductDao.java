/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMasterDao;

import FloorMasterDto.Order;
import FloorMasterDto.Product;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author abdisamadugas
 */
public interface ProductDao {

    List<Product> getAllProducts(Product productType);

    Product getSingleProduct(String productType);

}
