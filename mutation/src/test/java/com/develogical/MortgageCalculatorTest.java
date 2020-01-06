package com.develogical;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MortgageCalculatorTest
{

    @Test
    public void initialFixedRate()
    {
        double result = new MortgageCalculator().calculateRepayment(6, 25 * 12, 400000);
        assertThat(result, is(1359.66));
    }

    @Test
    public void laterVariableRate()
    {
        double result = new MortgageCalculator().calculateRepayment(26, 20 * 12, 200000);

        assertThat(result, is(866.32));
    }

    @Test
    public void it_calculate_repayment_for_the_24th_month()
    {
        double result = new MortgageCalculator().calculateRepayment(24, 20 * 12, 2000);

        assertThat(result, is(8.32));
    }
}
