package com.cn.cnpayment.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private Integer quantity;

    @Column
    private Integer amount;

    // Define many-to-many relationship with Payment and use a JoinTable
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "order_payment", 
        joinColumns = @JoinColumn(name = "order_id"), 
        inverseJoinColumns = @JoinColumn(name = "payment_id")
    )
    private List<Payment> payments;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }

    // Constructors

    public Orders(String name, String category, Integer quantity, Integer amount) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Orders() {
    }
}
