package com.enginemobi.core.offer.domain;


import com.enginemobi.core.order.domain.Order;

public interface OrderAdjustment extends Adjustment {

    public Order getOrder();

    public void init(Order order, Offer offer, String reason);

    public void setOrder(Order order);

}
