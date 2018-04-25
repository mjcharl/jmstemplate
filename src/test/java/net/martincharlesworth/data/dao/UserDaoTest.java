package net.martincharlesworth.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import net.martincharlesworth.data.AbstractDBTest;
import net.martincharlesworth.data.entity.Item;
import net.martincharlesworth.data.entity.Order;
import net.martincharlesworth.data.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
// @TransactionConfiguration (defaultRollback=false,
// transactionManager="txManager") //Stops the transaction
// being rolled back at the end of the test.
public class UserDaoTest extends AbstractDBTest {

    private Logger logger = LoggerFactory.getLogger(AbstractDBTest.class);

    @Autowired
    private SessionFactory sf;

    @Autowired
    RollbackTestBean rtb;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rtb.deleteTestData();
    }

    @Test
    @Transactional
    public void find() {
        UserDao dao = new UserDao(sf);
        User user = dao.findUserByUsername("araine");

        assertNotNull(user);
        assertEquals("Aldo", user.getForename());
    }

    @Test
    @Transactional
    public void findWithOrders() {
        UserDao dao = new UserDao(sf);
        User user = dao.getUserWithOrders(9999);

        assertNotNull(user);
        assertEquals("Aldo", user.getForename());
        assertEquals(2, user.getOrders().size());
        Set<Order> orders = user.getOrders();

        for (Order order : orders) {
            logger.info(order.getOrderNumber().toString());
        }
    }

    @Test
    @Transactional
    public void findWithOrdersAndItems() {
        UserDao dao = new UserDao(sf);
        User user = dao.getUserWithOrdersAndItems(9999);

        assertNotNull(user);
        assertEquals("Aldo", user.getForename());
        assertEquals(2, user.getOrders().size());
        Set<Order> orders = user.getOrders();

        for (Order order : orders) {
            logger.info(order.getOrderNumber().toString());

            Set<Item> items = order.getItems();

            for (Item item : items) {
                logger.info(item.getItemName());
            }
        }
    }

    @Test
    @Transactional
    public void addOrder() {
        UserDao dao = new UserDao(sf);
        User user = dao.getUserWithOrders(9999);

        // Create an order
        Order order = new Order();
        order.setOrderNumber(234);
        order.setUser(user);

        // Add an item
        Item item = new Item();
        item.setItemName("Car");
        item.setOrder(order);
        item.setPrice(4.50);
        item.setQuantity(2);
        order.getItems().add(item);

        // Add the order to the user's orders
        user.getOrders().add(order);

        dao.save(user);
    }

    // This class calls another bean because:
    // In proxy mode (which is the default), only external method calls coming
    // in through the
    // proxy are intercepted. This means that self-invocation, in effect, a
    // method within the target
    // object calling another method of the target object, will not lead to an
    // actual transaction
    // at runtime even if the invoked method is marked with @Transactional.
    // Also, the proxy must be fully initialized to provide the expected
    // behaviour so you should
    // not rely on this feature in your initialization code, i.e.
    // @PostConstruct.
    @Test
    @Transactional
    public void testRollback() {
        rtb.createUserSuccessful();
        try {
            rtb.createUserRollback();
        } catch (Exception e) {
            logger.error("Expected exeption for test", e);
        }

        UserDao dao = new UserDao(sf);
        User user1 = dao.findUserByUsername("mcharlesworth");
        User user2 = dao.findUserByUsername("mcharlesworth2");
        Assert.assertNotNull(user1);
        Assert.assertNull(user2);

    }

}
