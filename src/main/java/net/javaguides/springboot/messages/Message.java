package net.javaguides.springboot.messages;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    private Integer id;
    @Column(nullable = false, unique = true, length = 50)
    private String text;

    @Column(nullable = false, unique = false, length = 50)
    private float price;

    @Column(nullable = false, unique = false, length = 50)
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}