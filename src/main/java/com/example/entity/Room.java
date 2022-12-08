package com.example.entity;

public class Room {
    private int id;
    private int apartment_id;
    private String name;
    private int floor;
    private int member_max;
    private int rental;
    private int status;

    public Room() {
    }

    public Room(int id, int apartment_id, String name, int floor, int member_max, int rental, int status) {
        this.id = id;
        this.apartment_id = apartment_id;
        this.name = name;
        this.floor = floor;
        this.member_max = member_max;
        this.rental = rental;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(int apartment_id) {
        this.apartment_id = apartment_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getMember_max() {
        return member_max;
    }

    public void setMember_max(int member_max) {
        this.member_max = member_max;
    }

    public int getRental() {
        return rental;
    }

    public void setRental(int rental) {
        this.rental = rental;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "room{" +
                "id=" + id +
                ", apartment_id=" + apartment_id +
                ", name='" + name + '\'' +
                ", floor=" + floor +
                ", member_max=" + member_max +
                ", rental=" + rental +
                ", status=" + status +
                '}';
    }
}
