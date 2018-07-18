package com.java8.study.udemy;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-17 오후 7:25
 **/


public class MapDemo {

    public static void main(String[] args) {
        Map<String, String> languages = new HashMap<>();

        if(languages.containsKey("Java")) {
            System.out.println("Java already exists");
            fail();
        } else {
            languages.put("Java", "a compiled high level, object-oriented, platform independent language");
        }
        languages.put("Python", "an interpreted, object-oriented, high-level programming language with dynamic semantics");
        languages.put("Algol", "an algorithmic language");
        assertNull(languages.put("BASIC", "Beginners All Purposes Symbolic Instruction Code"));
        assertNull(languages.put("Lisp", "Therein lies madness"));

//        doWork((map) -> System.out.println(map.get("Java")), languages);
//        String javaValue = languages.get("Java");
//        assertEquals(javaValue, languages.put("Java", "this course is about Java")); //return old value
//        doWork((map) -> System.out.println(map.get("Java")), languages); //Overwritten

        if(languages.containsKey("Java")) {
            System.out.println("Java is already in the map");
        } else {
            languages.put("Java", "this course is about Java");
            fail();
        }

        System.out.println("==================================================");
        for(String key: languages.keySet()) {
            System.out.println(key + ", " + languages.get(key));
        }
        System.out.println("==================================================");

        //languages.remove("Lisp");
        if (languages.remove("Algol", "a family of algorithmic languages")) {   //key and value must be same with existing data
            fail();
        } else {
            System.out.println("Algol not removed, key/value pair not found");
        }

        String algolValue = languages.get("Algol");
        if (languages.remove("Algol", algolValue)) {   //key and value must be same with existing data
            System.out.println("Algol removed");
        } else {
            fail();
        }

        if(languages.replace("Lisp","This will not work", "a functional programming language with imperative features")) {
            fail();
        } else {
            System.out.println("Lisp not replaced");
        }

        String lispValue = languages.get("Lisp");
        if(languages.replace("Lisp",lispValue, "a functional programming language with imperative features")) {
            System.out.println("Lisp replaced");
        } else {
            fail();
        }

        assertNull(languages.replace("Scala","this will not be added"));
        languages.entrySet()
                .stream()
                .forEach(entry -> System.out.println(entry.getKey() + ", " + entry.getValue()));

    }

    public static void doWork(Consumer<Map> mapConsumer, Map map) {
        mapConsumer.accept(map);
    }
}
