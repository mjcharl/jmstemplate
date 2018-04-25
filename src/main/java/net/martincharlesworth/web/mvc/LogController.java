package net.martincharlesworth.web.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogController {

    private Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping(value = "/logoutpage", method = RequestMethod.GET)
    public String register(Model model) {
        return "LogoutPage";
    }
}
