package net.javaguides.springboot;

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
class OurFlowerShopMessageTests {

	private MessageService messageService;

	@Mock
	private MessageRepository messageRepository;

	@Mock
	private MessageRepository messageRepository1;

	@BeforeEach
	public void setUp() {
		messageService = new MessageService(messageRepository,messageRepository1);
	}


	@Test
	void contextLoads() {
	}

	@Test
	void getMessageTest() {
		Integer id = 1;
		Message m = new Message();
		m.setId(id);
		m.setText("text");
		m.setPrice(12);
		m.setStock(1);
		System.out.println(m);
		when(messageRepository.findById(id)).thenReturn(Optional.of(m));

		try {
			Message m1 = messageService.get(id);
			System.out.println(m1);
			assertEquals(m.getId(), m1.getId());
		}  catch (MessageNotFoundException e) {
			throw new RuntimeException(e);
		}

		verify(messageRepository, times(1)).findById(id);
	}

	@Test
	void listAllTest() {
		Integer id = 1;
		Message m = new Message();
		m.setId(id);
		m.setText("text");
		m.setPrice(12);
		m.setStock(1);
		List<Message> list = List.of(m);

		System.out.println(list);
		when(messageRepository.findAll()).thenReturn(List.of(m));

		List<Message> list1 = messageService.listAll();

		System.out.println(list1);

		assertTrue(list1.contains(m));

		verify(messageRepository, times(1)).findAll();
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
		when(messageRepository.countById(id)).thenReturn(Long.valueOf(list.size()+""));
		try {
			messageService.delete(id);
		}  catch (MessageNotFoundException e) {
			throw new RuntimeException(e);
		}

		verify(messageRepository, times(1)).countById(id);
		verify(messageRepository, times(1)).deleteById(id);
	}

}
