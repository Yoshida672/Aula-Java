package br.com.fiap.rabbitdemo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
    private MessageProducer messageProducer;
    @GetMapping
    public String home(Model model){
        model.addAttribute("form",new FormDTO());
        return "home";
    }
    @GetMapping("/enviar")
    public String enviar(Model model){
        messageProducer.sendMessage("TEST MESSAGE");
        return "home";
    }
    @PostMapping("/postar")
    public String postar(Model model,FormDTO formDTO){
        messageProducer.sendMessage(formDTO.getMessage());
        return "home";
    }
}
