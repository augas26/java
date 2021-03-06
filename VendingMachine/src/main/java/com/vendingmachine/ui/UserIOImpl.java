package com.vendingmachine.ui;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class UserIOImpl implements UserIO {

    Scanner scn = new Scanner(System.in);

    @Override
    public void print(String prompt) {
        System.out.print(prompt);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        String toReturn = scn.nextLine();
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
                //normally do something to log the exception
                //but this is a normal case because of user
                //stupidity
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
                //normally do something to log the exception
                //but this is a normal case because of user
                //stupidity
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
                //normally do something to log the exception
                //but this is a normal case because of user
                //stupidity
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
                //normally do something to log the exception
                //but this is a normal case because of user
                //stupidity
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
        BigDecimal money = null;

        boolean isValid = false;
        while (!isValid) {
            String userInput = readString(prompt);
            try {
                money = new BigDecimal(userInput, new MathContext(2, RoundingMode.HALF_UP));
                isValid = true;
            } catch (NumberFormatException ex) {

            }
        }

        return money;
    }

}
