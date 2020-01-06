package com.develogical;

public class MortgageCalculator
{

    private static final double FIXED_RATE = 0.02;
    private static final double VARIABLE_RATE = 0.04;

    private Logger logger = new Logger();

    public double calculateRepayment(int month, int totalMonths, int totalBorrowed)
    {
        logWork();

        double result = 100;

        if (month < 24) {
            result = (totalBorrowed / totalMonths) * (1 + FIXED_RATE);
        } else if (month < totalMonths - 24) {
            result = ((totalBorrowed - (totalBorrowed / totalMonths) * 24) / (totalMonths - 24)) * (1 + VARIABLE_RATE);
        } else {
            result = ((totalBorrowed - (totalBorrowed / totalMonths) * 24) / 24 * (1 + VARIABLE_RATE));
        }

        return result;
    }

    private void logWork()
    {
        logger.log("working...");
    }
}

