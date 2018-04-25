package net.martincharlesworth.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.martincharlesworth.data.AbstractDBTest;
import net.martincharlesworth.data.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
// @TransactionConfiguration (defaultRollback=false) //Stops the transaction
// being rolled back at
// the end of the test.
public class UserFacadeTest extends AbstractDBTest {

    @Autowired
    private UserFacade userFacade;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void find() {

        User user = userFacade.findUserByUsername("araine");

        assertNotNull(user);
        assertEquals("Aldo", user.getForename());
    }

    @Test
    public void saveExistingUser() {

        User user = userFacade.findUserByUsername("araine");

        assertNotNull(user);
        assertEquals("Aldo", user.getForename());

        user.setSurname("Jones");
        userFacade.saveUser(user);
    }

    @Test
    public void saveNewUser() {
        User newUser = new User();
        newUser.setForename("Joe");
        newUser.setPassword("abc");
        newUser.setSurname("Smith");
        newUser.setUsername("jsmith");
        newUser.setEnabled(true);
        userFacade.saveUser(newUser);
    }
}
