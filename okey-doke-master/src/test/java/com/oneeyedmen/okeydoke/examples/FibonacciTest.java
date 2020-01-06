package com.oneeyedmen.okeydoke.examples;

import com.oneeyedmen.okeydoke.junit.ApprovalsRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

public class FibonacciTest
{
    @Rule
    public final ApprovalsRule approver = ApprovalsRule.fileSystemRule("src/test/java");

    @Test
    public void canCalculateTerms() throws IOException
    {
        approver.assertApproved(fibonacciTerm(5));
    }

    private int fibonacciTerm(int number)
    {
        if(number < 2) return 1;

        return this.fibonacciTerm(number - 1) + this.fibonacciTerm(number - 2);
    }
}
