package com.bookstore.model;

import java.util.Date;
import java.util.List;

public class Order {
    private String id; // 订单编号
    private double money; // 订单总价
    private String receiverAddress; // 送货地址
    private String receiverName; // 收货人姓名
    private String receiverPhone; // 收货人电话
    private int paystate; // 订单状态
    private Date ordertime; // 下单时间

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    private List<OrderItem> items;//设置订单的具体的内容
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", money=" + money +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", paystate=" + paystate +
                ", ordertime=" + ordertime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getPaystate() {
        return paystate;
    }

    public void setPaystate(int paystate) {
        this.paystate = paystate;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }
}
