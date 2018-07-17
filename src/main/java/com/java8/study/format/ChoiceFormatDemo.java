package com.java8.study.format;

import org.junit.Test;

import java.text.ChoiceFormat;
import java.util.Arrays;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-17 오후 3:06
 **/


public class ChoiceFormatDemo {
    private  static int[] scores = {100, 95, 88, 80, 60, 70, 52, 60, 70};
    @Test
    public void mappingNumbersToStringsByChoiceFormat() {
        double[] limits = {60, 70, 80, 90};
        String[] grades = {"D", "C", "B", "A"};

        ChoiceFormat choiceFormat = new ChoiceFormat(limits, grades);

        Arrays.stream(scores)
                .forEach(score -> System.out.printf(choiceFormat.format(score) + ", "));
    }

    @Test
    public void mappingNumbersUsingPattern() {
        String pattern = "60#D|70#C|80<B|90#A";
        ChoiceFormat choiceFormat = new ChoiceFormat(pattern);

        Arrays.stream(scores)
                .forEach(score -> System.out.printf(score +": " + choiceFormat.format(score) +", "));
    }
}
