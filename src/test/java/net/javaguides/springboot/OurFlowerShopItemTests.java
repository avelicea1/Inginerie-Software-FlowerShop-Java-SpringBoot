package net.javaguides.springboot;

import net.javaguides.springboot.cart.Item;
import net.javaguides.springboot.cart.ItemNotFoundException;
import net.javaguides.springboot.cart.ItemRepository;
import net.javaguides.springboot.cart.ItemService;
import net.javaguides.springboot.messages.Message;
import net.javaguides.springboot.messages.MessageNotFoundException;
import net.javaguides.springboot.messages.MessageRepository;
import net.javaguides.springboot.messages.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class OurFlowerShopItemTests {

    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;


    @BeforeEach
    public void setUp() {
        itemService = new ItemService(itemRepository);
    }


    @Test
    void contextLoads() {
    }

    @Test
    void getMessageTest() {
        Integer id = 1;
        Item m = new Item();
        m.setId(id);
        m.setText("text");
        m.setPrice(12);
        m.setQuantity(1);
        System.out.println(m);
        when(itemRepository.findById(id)).thenReturn(Optional.of(m));

        try {
            Item m1 = itemService.get(id);
            System.out.println(m1);
            assertEquals(m.getId(), m1.getId());
        }  catch (ItemNotFoundException e) {
            throw new RuntimeException(e);
        }

        verify(itemRepository, times(1)).findById(id);
    }

    @Test
    void listAllTest() {
        Integer id = 1;
        Item m = new Item();
        m.setId(id);
        m.setText("text");
        m.setPrice(12);
        m.setQuantity(1);
        List<Item> list = List.of(m);

        System.out.println(list);
        when(itemRepository.findAll()).thenReturn(List.of(m));

        List<Item> list1 = itemService.listAll();

        System.out.println(list1);

        assertTrue(list1.contains(m));

        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void deleteMesssageTest(){
        Integer id = 1;
        Message m = new Message();
        m.setId(id);
        m.setText("text");
        m.setPrice(12);
        m.setStock(1);
        List<Message> list = List.of(m);
        when(itemRepository.countById(id)).thenReturn(Long.valueOf(list.size()+""));
        try {
            itemService.delete(id);
        }  catch (ItemNotFoundException e) {
            throw new RuntimeException(e);
        }

        verify(itemRepository, times(1)).countById(id);
        verify(itemRepository, times(1)).deleteById(id);
    }

}
