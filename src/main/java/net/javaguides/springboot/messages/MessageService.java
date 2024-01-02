package net.javaguides.springboot.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository catalog;
    @Autowired private MessageRepository cart;

    public List<Message> listAll(){
        return (List<Message>) catalog.findAll();
    }

    public List<Message> listAllC(){
        return (List<Message>) cart.findAll();
    }

    public String save(Message message) {
        catalog.save(message);

        return "redirect:/messages";
    }



    public Message get(Integer id) throws MessageNotFoundException {
        Optional<Message> result = catalog.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new MessageNotFoundException("Could not find any message with ID " + id);
    }

    public void delete(Integer id) throws MessageNotFoundException {
        Long count = catalog.countById(id);
        if(count == null || count == 0){
            throw new MessageNotFoundException("Could not find any message with ID " + id);
        }
        catalog.deleteById(id);
    }

}