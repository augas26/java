package FlooringMasterUI;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface UserIO {

    public void print(String msg);

    public String readString(String prompt);

    public double readDouble(String prompt);

    public double readDouble(String prompt, double min, double max);

    public float readFloat(String prompt);

    public float readFloat(String prompt, float min, float max);

    public int readInt(String prompt);

    public int readInt(String prompt, int min, int max);

    public long readLong(String prompt);

    public long readLong(String prompt, long min, long max);

    public BigDecimal readMoney(String prompt);

    LocalDate readDate(String prompt);

    LocalDate readDate(String prompt, LocalDate min, LocalDate max);

}
