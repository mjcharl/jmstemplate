package net.martincharlesworth.data.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import net.martincharlesworth.data.entity.Order;
import net.martincharlesworth.data.entity.User;

public class OrderDao {

    private SessionFactory sf;

    public OrderDao() {
        super();
    }

    public OrderDao(SessionFactory sf) {
        super();
        this.sf = sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public Order findById(Integer id) {
        Order order = (Order) sf.getCurrentSession().load(Order.class, id);

        return order;
    }

    public Order getOrdersWithItems(Integer id) {
        Query query = sf.getCurrentSession().getNamedQuery("ordersWithItems");
        query.setInteger("id", id);

        List<Order> orders = query.list();

        if (!orders.isEmpty()) {
            return orders.get(0);
        }
        return null;
    }

    public void save(User user) {
        sf.getCurrentSession().saveOrUpdate(user);
    }
}
