package FlooringMasterUI;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

    Scanner userInput = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        String toReturn = userInput.nextLine();
        return toReturn;
    }

    @Override
    public double readDouble(String prompt) {
        double toReturn = Double.NaN;
        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);

            try {
                toReturn = Double.parseDouble(input);

                correctInput = true;
            } catch (NumberFormatException ex) {

            }
        }
        return toReturn;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double toReturn = Double.NaN;
        boolean correctInput = false;
        while (!correctInput) {
            toReturn = readDouble(prompt);
            correctInput = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public float readFloat(String prompt) {
        float toReturn = Float.NaN;
        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);
            try {
                toReturn = Float.parseFloat(input);
                correctInput = true;

            } catch (NumberFormatException ex) {

            }
        }
        return toReturn;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float toReturn = Float.NaN;
        boolean correctInput = false;
        while (!correctInput) {
            toReturn = readFloat(prompt);
            correctInput = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public int readInt(String prompt) {
        int toReturn = Integer.MIN_VALUE;

        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);

            try {
                toReturn = Integer.parseInt(input);
                correctInput = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int toReturn = Integer.MIN_VALUE;
        boolean correctInput = false;
        while (!correctInput) {
            toReturn = readInt(prompt);
            correctInput = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public long readLong(String prompt) {
        long toReturn = Long.MIN_VALUE;
        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);

            try {
                toReturn = Long.parseLong(input);

                correctInput = true;
            } catch (NumberFormatException ex) {

            }
        }
        return toReturn;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long toReturn = Long.MIN_VALUE;
        boolean correctInput = false;
        while (!correctInput) {
            toReturn = readLong(prompt);
            correctInput = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public BigDecimal readMoney(String prompt) {
        BigDecimal toReturn = null;
        boolean isValid = false;
        while (!isValid) {
            String input = readString(prompt);
            try {
                toReturn = new BigDecimal(input, new MathContext(2, RoundingMode.HALF_UP));
                isValid = true;
            } catch (NumberFormatException ex) {

            }

        }
        return toReturn;

    }

    @Override
    public LocalDate readDate(String prompt) {

        boolean isValid = false;
        LocalDate date = null;
        do {
            String value = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                value = readString(prompt);
                date = LocalDate.parse(value, formatter);
                isValid = true;

            } catch (DateTimeParseException ex) {
                System.out.printf("The value '%s' is not a valid date. (MM-DD-YYYY)\n", value);

            }
        } while (!isValid);
        return date;
    }

    @Override
    public LocalDate readDate(String prompt, LocalDate min, LocalDate max) {
        boolean isvalid = false;
        LocalDate date = null;
        do {
            date = readDate(prompt);
            if (date.isAfter(min) && date.isBefore(max)) {
                isvalid = true;
            } else {
                System.out.printf("Please choose a date within bounds. (%s to %s)\n", min, max);
            }
        } while (!isvalid);

        return date;
    }

}
