package net.javaguides.springboot.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository catalog;

    public List<Item> listAll(){
        return (List<Item>) catalog.findAll();
    }


    public String save(Item message) {
        catalog.save(message);

        return "redirect:/messages";
    }

    public Item get(Integer id) throws ItemNotFoundException {
        Optional<Item> result = catalog.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ItemNotFoundException("Could not find any message with ID " + id);
    }

    public void delete(Integer id) throws ItemNotFoundException {
        Long count = catalog.countById(id);
        if(count == null || count == 0){
            throw new ItemNotFoundException("Could not find any message with ID " + id);
        }
        catalog.deleteById(id);
    }


}