package com.java8.study.collections;

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
        List<Seat> seatCopy = new ArrayList<>(theatre.seats);
        printList(seatCopy);

        seatCopy.get(1).reserve();
        if(theatre.reserveSeat("A02")) {
            System.out.println("Please pay for A02");
        } else {
            System.out.println("Seat already reserved");
        }

        //Collections.reverse(seatCopy);
        Collections.shuffle(seatCopy);
        System.out.println("Printing seatCopy");
        printList(seatCopy);
        System.out.println("Printing theatre.seats");
        printList(theatre.seats);

        //Theatre.Seat minSeat = Collections.min(seatCopy);
        //Theatre.Seat maxSeat = Collections.max(seatCopy);
        Comparator<Seat> seatComparator = Comparator
                .comparing(seat -> seat.getSeatNumber().toUpperCase());
        Seat minSeat = findMin(seatCopy, seatComparator);
        Seat maxSeat = findMax(seatCopy, seatComparator);
        System.out.println("Min seat number is " + minSeat.getSeatNumber());
        System.out.println("Max seat number is " + maxSeat.getSeatNumber());

        sortList(seatCopy);
        printList(seatCopy);
    }

    public static Seat findMax(List<Seat> list, Comparator<Seat> comparator) {
        return findMin(list, comparator.reversed());
    }

    public static Seat findMin(List<Seat> list, Comparator<Seat> comparator) {
        return list.stream()
                .collect(minBy(comparator))
                .get();
    }

    public static void printList(List<Seat> list) {
        String strings = list.stream()
                .map(seat -> " " + seat.getSeatNumber())
                .collect(Collectors.joining());
        System.out.println(strings);
        System.out.println();
        System.out.println("============================================");
    }

    public static void sortList(List<? extends Seat> list) {
        for(int i=0; i<list.size()-1; i++) {
            for(int j=i+1; j<list.size(); j++) {
                if(list.get(i).compareTo(list.get(j)) >0) {
                    Collections.swap(list, i, j);
                }
            }
        }
    }

}
