package com.haifeng.mybatis.pojo;

public class Car {
    private Long id;
    private String car_num;
    private String brand;
    private int guide_price;
    private String produce_time;
    private String car_type;

    public Car() {
    }

    public Car(Long id, String car_num, String brand, int guide_price, String produce_time, String car_type) {
        this.id = id;
        this.car_num = car_num;
        this.brand = brand;
        this.guide_price = guide_price;
        this.produce_time = produce_time;
        this.car_type = car_type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", car_num='" + car_num + '\'' +
                ", brand='" + brand + '\'' +
                ", guide_price=" + guide_price +
                ", produce_time='" + produce_time + '\'' +
                ", car_type='" + car_type + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getGuide_price() {
        return guide_price;
    }

    public void setGuide_price(int guide_price) {
        this.guide_price = guide_price;
    }

    public String getProduce_time() {
        return produce_time;
    }

    public void setProduce_time(String produce_time) {
        this.produce_time = produce_time;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }
}
