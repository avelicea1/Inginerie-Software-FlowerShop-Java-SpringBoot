package net.javaguides.springboot.cart;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    public Long countById(Integer id);
}