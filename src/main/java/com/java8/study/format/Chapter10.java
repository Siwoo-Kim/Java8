package com.java8.study.format;

import com.sun.media.sound.SoftTuning;
import org.junit.Test;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-12 오전 10:59
 **/


public class Chapter10 {

    @Test
    public void testDecimalFormat() {
        double number = 1234567.89;
        DecimalFormat decimalFormat = new DecimalFormat("#.000");
        System.out.println(decimalFormat.format(number));

        String[] patterns = {
                "0",
                "#",
                "0.0",
                "#.#",
                "\u00A4 #,###",
                "#.#%",
                "'#'#,###"
        };

        Arrays.stream(patterns)
                .map(DecimalFormat::new)
                .forEach(formatter -> System.out.println(formatter.format(number)));
    }

    @Test
    public void pasreStringThenFormatNumber() {
        DecimalFormat decimalFormat1 = new DecimalFormat("#,###,##");
        DecimalFormat decimalFormat2 = new DecimalFormat("#.###E0");

        String wannaBeingParsed = "1,234,567,89";
        try {
            Number number = decimalFormat1.parse(wannaBeingParsed);
            double value = number.doubleValue();
            System.out.println(decimalFormat2.format(value));
        }catch (ParseException e) {
            System.out.println("Unalbe to parse the String ");
        }
    }

    @Test
    public void simpleDateFormat() {
        Date today = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(today));

        List<SimpleDateFormat> formatList = Arrays.asList(
                new SimpleDateFormat("''yy년 MM월 dd일 E요일"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a"),
                new SimpleDateFormat("오늘은 이 달의 W번째 주입니다.")
        );

        formatList.stream()
                .map(formatter -> formatter.format(today))
                .forEach(System.out::println);

        Calendar calendar = Calendar.getInstance();
        calendar.set(1989,02, 04);
        Date birthDay = calendar.getTime();

        formatList.stream()
                .map(formatter -> formatter.format(birthDay))
                .forEach(System.out::println);
    }

    @Test
    public void parseStringToDateUsingSimpleDateFormat() {
        String value = "2019/09/09 03:20:30";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            System.out.println(simpleDateFormat.parse(value));
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String pattern = "yyyy/MM/dd";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Scanner scanner = new Scanner(System.in);
        Date inDate = null;
        System.out.println("날짜를 " + pattern + "의 형태로 입력해주세요.(ex:2015/12/31)");

        while (scanner.hasNextLine()) {
            try {
                inDate = dateFormat.parse(scanner.nextLine());
                break;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inDate);
        Calendar today = Calendar.getInstance();
        long day = (calendar.getTimeInMillis() - today.getTimeInMillis()) / (60 * 60 * 1000);
        System.out.println("입력하신 날짜는 현재와 " + day + "차이가 있습니다.");
    }
}
