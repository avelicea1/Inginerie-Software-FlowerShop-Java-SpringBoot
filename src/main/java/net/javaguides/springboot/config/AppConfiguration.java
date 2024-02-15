package net.javaguides.springboot.config;

import net.javaguides.springboot.cart.ItemRepository;
import net.javaguides.springboot.cart.ItemService;
import net.javaguides.springboot.messages.MessageRepository;
import net.javaguides.springboot.messages.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public MessageService messageService(MessageRepository mr1, MessageRepository mr2) {
        return new MessageService(mr1, mr2);
    }

    @Bean
    public ItemService itemService(ItemRepository ir1){
        return new ItemService(ir1);
    }
}
