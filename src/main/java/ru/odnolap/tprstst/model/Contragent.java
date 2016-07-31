package ru.odnolap.tprstst.model;

public class Contragent {
    private int id; // id контрагента
    private String name; // наименование контрагента

    public Contragent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Contragent() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
