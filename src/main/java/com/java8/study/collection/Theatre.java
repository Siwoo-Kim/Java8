package com.java8.study.collection;

import java.util.*;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-12 오후 6:34
 **/

public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<>();

    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;
        int lastRow = 'A' + (numRows - 1);
        for(char row='A'; row<=lastRow; row++) {
            for(int seatNum=1; seatNum<=seatsPerRow; seatNum++) {
                double price = calcuatePrice(row, seatNum);
                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat);
            }
        }
    }

    public double calcuatePrice(char row, int seatNum) {
        double price = 12.;
        if((row < 'D') && (seatNum >=4 && seatNum <=9)) {
            price = 14.;
        } else if((row > 'F') || seatNum <4 || seatNum >9) {
            price = 7.;
        }
        return price;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public boolean reserveSeat(String seatNumber) {
        Seat requestedSeat = new Seat(seatNumber, 0);
        int foundSeat = Collections.binarySearch(seats, requestedSeat,
                Comparator.comparing(seat -> seat.getSeatNumber().toUpperCase()));
        if(foundSeat>=0) {
            return seats.get(foundSeat).reserve();
        } else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }
    }

    // for testing
    public Collection<Seat> getSeats() {
        return seats;
    }

    class Seat implements Comparable<Seat>{
        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        public boolean reserve() {
            if(!reserved) {
                reserved = true;
                System.out.println("Seat " + seatNumber + " reserved");
                return true;
            } else {
                return false;
            }
        }

        public boolean cancel() {
            if(this.reserved) {
                this.reserved = false;
                System.out.println("Reservation of saet " + seatNumber + " cancelled");
                return true;
            } else {
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public int compareTo(Seat seat) {
            return seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }
    }
}
























