package com.java8.study.collection;

import com.java8.study.collection.Theatre.Seat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.minBy;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-12 오후 6:31
 **/


public class UdemyCollection {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("Olympian", 8, 12);
        printList((List<Seat>) theatre.getSeats());
    }


    public static void printList(List<Seat> list) {
        String strings = list.stream()
                .map(seat -> " " + seat.getSeatNumber() +" " + seat.getPrice())
                .collect(Collectors.joining());
        System.out.println(strings);
        System.out.println();
        System.out.println("============================================");
    }


}
