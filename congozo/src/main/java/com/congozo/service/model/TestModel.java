package com.congozo.service.model;

public class TestModel {
    private String name;
    private int adge;
    private String number;

    public TestModel(String name, int adge, String number) {
        this.name = name;
        this.adge = adge;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdge() {
        return adge;
    }

    public void setAdge(int adge) {
        this.adge = adge;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
