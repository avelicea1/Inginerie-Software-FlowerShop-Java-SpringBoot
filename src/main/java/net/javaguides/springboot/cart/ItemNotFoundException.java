package net.javaguides.springboot.cart;

public class ItemNotFoundException extends Throwable {
    public ItemNotFoundException(String message) {
        super(message);
    }
}