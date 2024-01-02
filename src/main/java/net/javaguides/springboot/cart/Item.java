package net.javaguides.springboot.cart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

    @Id
    private Integer id;
    @Column(nullable = false, unique = true, length = 50)
    private String text;

    @Column(nullable = false, unique = false, length = 50)
    private float price;

    @Column(nullable = false, unique = false, length = 50)
    private float quantity;

    public Item() {

    }

    public Item(int id, String text, float price, float quantity) {
        this.id = id;
        this.text = text;
        this.price = price;
        this.quantity = quantity;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
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
                '}';
    }
}
