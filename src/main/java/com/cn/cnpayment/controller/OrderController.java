package com.cn.cnpayment.controller;

import com.cn.cnpayment.entity.Orders;
import com.cn.cnpayment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    // Autowire OrderService to handle business logic
    @Autowired
    private OrderService orderService;

    /**
     * POST "/order/save"
     * Saves an order in the database.
     *
     * @param order The order object to save.
     */
    @PostMapping("/save")
    public void saveOrder(@RequestBody Orders order) {
        orderService.saveOrder(order);
    }

    /**
     * GET "/order/id/{id}"
     * Fetches an order for a specific id.
     *
     * @param id The ID of the order to fetch.
     * @return The order with the specified ID.
     */
    @GetMapping("/id/{id}")
    public Orders getOrder(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    /**
     * DELETE "/order/id/{id}"
     * Deletes an order for a specific id.
     *
     * @param id The ID of the order to delete.
     */
    @DeleteMapping("/id/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.delete(id);
    }

    /**
     * GET "/order/allOrders"
     * Fetches the list of all orders from the database.
     *
     * @return A list of all orders.
     */
    @GetMapping("/allOrders")
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }
}
