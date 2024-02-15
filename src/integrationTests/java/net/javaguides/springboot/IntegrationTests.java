package net.javaguides.springboot;

import io.restassured.RestAssured;
import net.javaguides.springboot.cart.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    private static Item item1 = new Item();

    private static Item item2 = new Item();

    @Before
    public void setUp() {
        RestAssured.port = port;

        jdbcTemplate.execute("DELETE FROM items");

        jdbcTemplate.execute("INSERT INTO items (id, price, quantity, text) VALUES (1, 12, 3, 'trandafir')");

        item1.setId(1);
        item1.setPrice(12);
        item1.setQuantity(3);
        item1.setText("trandafir");

        item2.setId(2);
        item2.setPrice(5);
        item2.setQuantity(5);
        item2.setText("lalea");

    }

    @After
    public void tearDown() {
        jdbcTemplate.execute("DELETE FROM items");
    }

    @Test
    public void testAdminHome() {

        MvcResult result = null;
        try {
            result = mockMvc.perform(get("/admin-home"))
                    .andExpect(status().is3xxRedirection())
                    .andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCart() {

        MvcResult result = null;
        try {
            result = mockMvc.perform(get("/cart"))
                    .andExpect(status().is3xxRedirection())
                    .andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUserHome() {

        MvcResult result = null;
        try {
            result = mockMvc.perform(get("/user-home"))
                    .andExpect(status().is3xxRedirection())
                    .andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testLogin() {

        MvcResult result = null;
        try {
            result = mockMvc.perform(get("/login"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("text/html;charset=UTF-8"))
                    .andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}