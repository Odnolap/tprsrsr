package ru.odnolap.tprstst.model;

public class Contragent {
    private final int id; // id контрагента
    private final String name; // наименование контрагента

    public Contragent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
