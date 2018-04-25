package net.martincharlesworth.web.mvc;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TilesController {

    private Logger logger = LoggerFactory.getLogger(TilesController.class);

    @RequestMapping("/tilestest")
    public String tilesTest(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
            HttpServletRequest req, Model model) {

        return "homepage";
    }

}
