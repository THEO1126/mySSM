package cn.edu.guet.bean;

import java.io.Serializable;

/**
 * @Author liwei
 * @Date 2023/1/8 17:16
 * @Version 1.0
 */
public class Book implements Serializable {
    private int id;
    private String bookName;
    private String author;
    private Float price;
    private String publisher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
