package FloorMasterDto;

import java.math.BigDecimal;

public class Product {

    public String productType;
    public BigDecimal costPerSquareFoot;
    public BigDecimal laborCostPerSqFt;

    public Product() {
    }

    public Product(String productType, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSqFt) {
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

}
