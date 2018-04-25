package net.martincharlesworth.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.martincharlesworth.data.AbstractDBTest;
import net.martincharlesworth.data.entity.Item;
import net.martincharlesworth.data.entity.Order;
import net.martincharlesworth.web.mvc.LogController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
// @TransactionConfiguration (defaultRollback=false) //Stops the transaction
// being rolled back at the end of the test.
public class OrderDaoTest extends AbstractDBTest {
    
    private Logger logger = LoggerFactory.getLogger(OrderDaoTest.class);
    
    @Autowired
    private SessionFactory sf;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    @Transactional
    public void find() {
        OrderDao dao = new OrderDao(sf);
        Order order = dao.findById(9001);

        assertNotNull(order);
        assertEquals(Integer.valueOf(1234), order.getOrderNumber());
    }

    @Test
    @Transactional
    public void findWithOrders() {
        OrderDao dao = new OrderDao(sf);
        Order order = dao.getOrdersWithItems(9001);

        assertNotNull(order);
        assertEquals(Integer.valueOf(1234), order.getOrderNumber());
        assertEquals(2, order.getItems().size());
        Set<Item> items = order.getItems();

        for (Item item : items) {
            logger.info(item.getItemName());
        }
    }

}
