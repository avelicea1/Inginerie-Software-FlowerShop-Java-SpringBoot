package net.javaguides.springboot.messages;

public class MessageNotFoundException extends Throwable {
    public MessageNotFoundException(String message) {
        super(message);
    }
}