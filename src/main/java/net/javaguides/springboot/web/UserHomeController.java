package net.javaguides.springboot.web;

import net.javaguides.springboot.cart.Item;
import net.javaguides.springboot.cart.ItemNotFoundException;
import net.javaguides.springboot.cart.ItemService;
import net.javaguides.springboot.messages.Message;
import net.javaguides.springboot.messages.MessageNotFoundException;
import net.javaguides.springboot.messages.MessageService;
import net.javaguides.springboot.monitors.Monitor;
import net.javaguides.springboot.recommenderSystem.Personality;
import net.javaguides.springboot.recommenderSystem.Recommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


//import java.io.FileWriter;
import java.util.List;


@Controller
public class UserHomeController {

    public Monitor monitor = new Monitor();
    @Autowired
    private MessageService messageService;
    @Autowired
    private ItemService itemService;
    @GetMapping("/user-home")
    public String showMessageList(Model model) {
        List<Message> listMessages = messageService.listAll();
        model.addAttribute("listMessages", listMessages);
        return "user-home";
    }
    @GetMapping("/cart")
    public String showCartList(Model model) {
        List<Item> listItems = itemService.listAll();
        model.addAttribute("listItems", listItems);
        double totalPrice = calculateTotalPrice(listItems);
        model.addAttribute("totalPrice", totalPrice);

        return "items";

    }
    private double calculateTotalPrice(List<Item> listItems) {
        double totalPrice = 0;
        for (Item item : listItems) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }
    @GetMapping("/user-home/recommender")
    public String showRecommenderForm(Model model) {
        model.addAttribute("message", new Personality());
        model.addAttribute("pageTitle", " Our recommendation ");
        return "recommender";
    }
    @PostMapping("/user-home/recommender/generate")
    public String showRecommenderResult(Personality p, RedirectAttributes ra) {
        ra.addFlashAttribute("msg", "The flowers we recommend based on your personality characteristics is: "
                + Recommender.knn(p, 1) + ", "
                + Recommender.knn(p, 2) + ", "
                + Recommender.knn(p, 3)
        );
        return "redirect:/user-home/recommender";
    }
    @GetMapping("/user-home/addToCart/{id}")
    public String addToCartUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {

            Message m = messageService.get(id);
            if(m.getStock() > 0) {
                m.setStock(m.getStock() - 1);
                messageService.save(m);
                Item i = new Item(m.getId(), m.getText(), m.getPrice(), 1);
                List<Item> l = itemService.listAll();
                int found = -1;
                for (Item item : l) {
                    if (item.getText().equals(i.getText())) {
                        found = item.getId();
                    }
                }
                if (found != -1) {
                    i = itemService.get(found);
                    itemService.delete(found);
                    i.setQuantity(i.getQuantity() + 1);
                }

                itemService.save(i);

                monitor.addItemSuccess(id);

                ra.addFlashAttribute("msg", "the item ID " + id + " has been added to your cart.");
            }
            else
            {
                monitor.addItemFail(id);

                ra.addFlashAttribute("msg", "the item ID " + id + " has not been added to your cart. --- INSUFFICIENT STOCK");
            }
        }catch (MessageNotFoundException e) {
            ra.addFlashAttribute("msg", e.getMessage());
        } catch (ItemNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch(Exception e) {}

        return "redirect:/cart";
    }
    @GetMapping("/cart/delete/{id}")
    public String deleteUserC(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            Item m = itemService.get(id);


            monitor.deletedItem(id,m.getQuantity());

            itemService.delete(id);
            ra.addFlashAttribute("msg", "the user ID " + id + "has been deleted.");
        } catch (ItemNotFoundException e) {
            ra.addFlashAttribute("msg", e.getMessage());
        }
        catch(Exception e) {}

        return "redirect:/cart";
    }
}
