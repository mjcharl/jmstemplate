package net.martincharlesworth.web.mvc;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.martincharlesworth.business.UserFacade;
import net.martincharlesworth.data.entity.User;
import net.martincharlesworth.web.mvc.beans.UserBean;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("registerBean", new UserBean());
        return "Register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional
    public String register(Model model, @ModelAttribute("registerBean") @Valid UserBean registerBean,
            BindingResult bidingResult) {
        User user = new User();
        user.setForename(registerBean.getForename());
        user.setSurname(registerBean.getSurname());
        user.setPassword(registerBean.getPassword());
        user.setUsername(registerBean.getUsername());
        // Default to enabled
        user.setEnabled(true);
        userFacade.saveUser(user);

        if (bidingResult.hasErrors()) {
            model.addAttribute("registerBean", registerBean);
            return "Register";
        }

        return "Thanks";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String userSearchForm(Model model) {
        model.addAttribute("userBean", new UserBean());
        return "UserSearch";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String userSearch(@ModelAttribute UserBean userBean, Model model) {
        model.addAttribute("userBean", userBean);

        logger.info("****** Searhing for: " + userBean.getUsername());

        User user = userFacade.findUserByUsername(userBean.getUsername());

        if (user != null) {
            logger.info("****** " + user.getForename() + " " + user.getSurname());
        } else {
            user = new User();
        }

        // We should really add a view specific User bean rather than the
        // Hibernate object
        // Always add a user, even if it's empty
        model.addAttribute("user", user);

        return "UserSearch";
    }

}
