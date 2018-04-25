package net.martincharlesworth.web.mvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring-mvc-context.xml" })
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void userSearchGet() throws Exception {
        UserController controller = new UserController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/user/search")).andExpect(view().name("UserSearch"))
                .andExpect(model().attributeExists("userBean"));
    }

    @Test
    public void userSearchPost() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(post("/user/search").param("username", "ysam")).andExpect(view().name("UserSearch"))
                .andExpect(model().attributeExists("userBean"));
    }

    @Test
    public void registerGet() throws Exception {
        UserController controller = new UserController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/user/register")).andExpect(view().name("Register"))
                .andExpect(model().attributeExists("registerBean"));
    }

    @Test
    public void registerPost() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(post("/user/register").param("forename", "J").param("surname", "Bauer")
                .param("username", "jbauer").param("password", "24hours"))
                .andExpect(model().attributeExists("registerBean")).andExpect(view().name("Register"));
    }

    @Test
    public void registerPostGoodData() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(post("/user/register").param("forename", "Jack").param("surname", "Bauer")
                .param("username", "jbauer").param("password", "24hours")).andExpect(view().name("Thanks"));
    }
}
