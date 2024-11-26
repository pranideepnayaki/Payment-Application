package com.cn.cnpayment.dal;

import com.cn.cnpayment.entity.Orders;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderDalImpl implements OrderDal {

    // Autowire the EntityManager object
    @Autowired
    private EntityManager entityManager;

    /**
     * Fetch an Order by its ID.
     * @param id The ID of the Order to retrieve.
     * @return The fetched Order.
     */
    @Override
    public Orders getById(int id) {
        // Unwrap the session from the EntityManager
        Session session = entityManager.unwrap(Session.class);
        // Fetch the Order by ID
        return session.get(Orders.class, id);
    }

    /**
     * Save an Order to the database.
     * @param order The Order entity to save.
     */
    @Override
    public void save(Orders order) {
        // Unwrap the session from the EntityManager
        Session session = entityManager.unwrap(Session.class);
        // Save the Order entity
        session.save(order);
    }

    /**
     * Delete an Order from the database by ID.
     * @param id The ID of the Order to delete.
     */
    @Override
    public void delete(int id) {
        // Unwrap the session from the EntityManager
        Session session = entityManager.unwrap(Session.class);
        // Fetch the Order entity to delete
        Orders order = session.get(Orders.class, id);
        session.delete(order);
    }

    /**
     * Fetch all Orders from the database.
     * @return A list of all Orders.
     */
    @Override
    public List<Orders> getAllOrders() {
        // Unwrap the session from the EntityManager
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("SELECT o FROM Orders o", Orders.class).getResultList();
    }
}
