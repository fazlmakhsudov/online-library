package net.codejava.javaee.entity;

/**
 * Book.java
 * This is a model class represents a book entity
 *
 * @author www.codejava.net
 */
public class Book {
    protected int id;
    protected String title;
    protected String author;
    protected float price;
    private int user_id;

    public Book(int id, String title, String author, float price, int user_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Book() {
    }

    public Book(int id) {
        this.id = id;
    }

    public Book(String title, String author, float price, int user_id) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}