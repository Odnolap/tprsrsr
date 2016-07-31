package ru.odnolap.tprstst.model;

public class Product {
    private String article; // Артикул товара
    private String name; // Наименование товара

    public Product(String article, String name) {
        this.article = article;
        this.name = name;
    }

    public Product() {}

    public String getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setName(String name) {
        this.name = name;
    }
}
