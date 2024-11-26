package com.cn.cnpayment.service;

import com.cn.cnpayment.dal.OrderDal;
import com.cn.cnpayment.dal.PaymentDAL;
import com.cn.cnpayment.entity.Orders;
import com.cn.cnpayment.entity.Payment;
import com.cn.cnpayment.exception.NotFoundException;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Complete the OrderService class as mentioned below:
 *
 * a. Autowire OrderDal
 *
 * b. Complete the following methods:
 *  1. getOrderById(int id): This method fetches an Order for a specific Id.
 *  2. getAllOrders(): This method fetches the list of Orders.
 *  3. saveOrder(Orders newOrder): This method saves an Order.
 *  4. delete(int id): This method deletes an Order for a specific Id.
 **/

@Service
public class OrderService {

    // Autowire the OrderDal object
    @Autowired
    private OrderDal orderDal;
    
    @Autowired
    PaymentDAL paymentDAL;
    /**
     * Fetches an order for a specific id.
     * @param id The id of the order to fetch.
     * @return The fetched order.
     */
    @Transactional
    public Orders getOrderById(int id) {
        // Fetch and return the order by id using OrderDal
        Orders orders = orderDal.getById(id);
        if(orders == null) {
        	throw new NotFoundException("orders not found");
        }
        return orders;
    }

    /**
     * Fetches all orders.
     * @return The list of all orders.
     */
    @Transactional
    public List<Orders> getAllOrders() {
        // Fetch and return all orders using OrderDal
        List<Orders> allOrders = orderDal.getAllOrders();
        if(allOrders == null) {
        	throw new NotFoundException("no order found");
        }
        return allOrders;
    }

    /**
     * Saves a new order.
     * @param newOrder The order to save.
     */
    @Transactional
    public void saveOrder(Orders newOrder) {
        // Save the new order using OrderDal
    	List<Payment> payments = new ArrayList<>();
    	for(Payment payment : newOrder.getPayments()) {
    		Payment existingPayment = paymentDAL.getById(payment.getId());
    		payments.add(existingPayment);
    	}
    	newOrder.setPayments(payments);
        orderDal.save(newOrder);
    }

    /**
     * Deletes an order for a specific id.
     * @param id The id of the order to delete.
     */
    @Transactional
    public void delete(int id) {
        // Delete the order by id using OrderDal
        orderDal.delete(id);
    }
}
