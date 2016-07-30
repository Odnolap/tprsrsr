package ru.odnolap.tprstst.model;

public class Product {
    private final String article; // Артикул товара
    private final String name; // Наименование товара

    public Product(String article, String name) {
        this.article = article;
        this.name = name;
    }

    public String getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }
}
