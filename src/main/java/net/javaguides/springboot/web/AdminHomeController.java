package net.javaguides.springboot.web;

import net.javaguides.springboot.cart.ItemService;
import net.javaguides.springboot.messages.Message;
import net.javaguides.springboot.messages.MessageNotFoundException;
import net.javaguides.springboot.messages.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class AdminHomeController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private ItemService itemService;
    @GetMapping("/admin-home")
    public String showMessageList(Model model) {
        List<Message> listMessages = messageService.listAll();
        model.addAttribute("listMessages", listMessages);
        return "admin-home";
    }
    @GetMapping("/admin-home/new")
    public String showNewForm(Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("pageTitle", "Add new flower");
        return "message_from";
    }
    @PostMapping("/admin-home/save")
    public String saveMessage(Message message, RedirectAttributes ra) {
        messageService.save(message);
        ra.addFlashAttribute("msg", "The message has been saved successfully");
        return "redirect:/admin-home";
    }
    @GetMapping("/admin-home/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Message message = messageService.get(id);
            model.addAttribute("message", message);
            model.addAttribute("pageTitle", "Edit message (ID: " + id + ")");
            return "message_from";

        } catch (MessageNotFoundException e) {
            ra.addFlashAttribute("msg", "the message has been saved successfully");
            return "redirect:/admin-home";
        }
    }
    @GetMapping("/admin-home/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            messageService.delete(id);
            ra.addFlashAttribute("msg", "the user ID " + id + "has been deleted.");
        } catch (MessageNotFoundException e) {
            ra.addFlashAttribute("msg", e.getMessage());
        }

        return "redirect:/admin-home";
    }
}
