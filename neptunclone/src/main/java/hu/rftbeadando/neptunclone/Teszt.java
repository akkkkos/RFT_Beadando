package hu.rftbeadando.neptunclone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Teszt {

    @GetMapping("/teszt")
    public String tesztFuggveny(@RequestParam(name="name", required=false, defaultValue="World") String name,
                                @RequestParam(value="fruit", required=false, defaultValue="alma") String fruit,
                                Model model) {
        model.addAttribute("name", name);
        model.addAttribute("fruit", fruit);
        return "teszt";
    }
}
