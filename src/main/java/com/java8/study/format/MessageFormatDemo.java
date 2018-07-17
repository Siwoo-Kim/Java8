package com.java8.study.format;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-17 오후 3:17
 **/


public class MessageFormatDemo {
    private static final String USER_INFO_FORMAT = "Name: {0}, Tel: {1}, Age {2}, BirthDay {3}";
    private static final String INSERT_STATEMENT = "INSERT INTO {0} VALUES ( ''{0}'', ''{1}'', ''{2}'' ) ";
    @Test
    public void formatMessageDemo1ByMessageFormat() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1989,03,04);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd EE");
        Object[] arguments = {"Siwoo Kim", "333-444-5555", 30, simpleDateFormat.format(calendar.getTime())};

        System.out.println(MessageFormat.format(USER_INFO_FORMAT, arguments));
    }

    @Test
    public void formatMessageDemo2ByMessageFormat() throws ParseException {
        final String tableName = "cust_info";

        Object[][] arguments = {
                {tableName, "Siwoo Kim", "111-222-3333", 30},
                {tableName, "IU", "222-222-3333", 25},
        };

        List<String> results = new ArrayList<>();
        for(int i=0; i<arguments.length; i++) {
            results.add(MessageFormat.format(INSERT_STATEMENT, arguments[i]));
        }

        MessageFormat messageFormat = new MessageFormat(INSERT_STATEMENT);
        results.stream()
                .forEach(string -> {
                    try {
                        System.out.println(Arrays.toString(messageFormat.parse(string)));
                    }catch (Exception e) {
                        System.out.println("Parse Exception occurred");
                    }
                });
    }

    @Test
    public void formatTextFileDemoByMessageFormat() throws FileNotFoundException, ParseException {
        File file = Paths.get(".","src/main/java/com/java8/study/format/data.txt").toFile();
        Scanner scanner = new Scanner(file);

        String pattern = "{0},{1},{2}";
        MessageFormat messageFormat = new MessageFormat(pattern);

        while (scanner.hasNextLine()) {
            Object[] args = messageFormat.parse(scanner.nextLine());
            ArrayList<Object> result = new ArrayList<Object>();
            result.add("CUST_INFO");

            Arrays.stream(args)
                    .forEach(result::add);
            System.out.println(MessageFormat.format(INSERT_STATEMENT, result.toArray()));
        }
    }
}





























