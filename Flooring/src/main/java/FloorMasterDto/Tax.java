package FloorMasterDto;

import java.math.BigDecimal;

public class Tax {

    public String stateAbbr;
    public BigDecimal taxRate;

    public Tax() {

    }

    public Tax(String stateAbbr, BigDecimal taxRate) {
        this.stateAbbr = stateAbbr;
        this.taxRate = taxRate;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

}
