package com.oneeyedmen.okeydoke.college;

import com.oneeyedmen.okeydoke.junit.ApprovalsRule;
import org.junit.Rule;
import org.junit.Test;


import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class CollegeTutorTest
{
    // college tutor
    // takes a set of poems
    // and gives each a mark


    // marks them radonamly,
    // check aoutput, looks good?
    // improve or
    // or next version fo aglrotihm
    // start by generating random marks

    @Rule
    public final ApprovalsRule approver = ApprovalsRule.fileSystemRule("src/test/java");

    @Test
    public void it_should_grade_poems() throws IOException
    {
        approver.assertApproved(this.grade());
    }

    private String grade()
    {
        int totalGrades = 20;
        int[] lowGrades = IntStream.generate(() -> new Random().nextInt(35))
                .limit((int)((float) totalGrades / 100 * 20)).toArray();

        int[] averageGrads = IntStream.generate(() -> new Random().nextInt(55) + 35)
                .limit((int)((float) totalGrades / 100 * 60)).toArray();

        int[] highGrades = IntStream.generate(() -> new Random().nextInt(10) + 90)
                .limit((int)((float) totalGrades / 100 * 20)).toArray();

        int[] allGrades = new int[lowGrades.length + highGrades.length + averageGrads.length];
        System.arraycopy(lowGrades, 0, allGrades, 0, lowGrades.length);
        System.arraycopy(highGrades, 0, allGrades, lowGrades.length, highGrades.length);
        System.arraycopy(averageGrads, 0, allGrades, lowGrades.length + highGrades.length, averageGrads.length);

        Arrays.sort(allGrades);

        return Arrays.toString(allGrades);
    }
}
