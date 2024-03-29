package net.javaguides.springboot.messages;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    public Long countById(Integer id);
}
