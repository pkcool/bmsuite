package com.enginemobi.core.offer.domain;


import com.enginemobi.common.money.Money;
import com.enginemobi.core.order.domain.OrderItem;

import java.io.Serializable;

/**
 * OrderItem level offer that has been qualified for an order,
 * but may still be ejected based on additional pricing
 * and stackability concerns once the order has been processed
 * through the promotion engine.
 */
public interface CandidateItemOffer extends Serializable{

    public Long getId();

    public void setId(Long id);

    public OrderItem getOrderItem();

    public void setOrderItem(OrderItem orderItem);

    public CandidateItemOffer clone();

    public void setOffer(Offer offer);

    public int getPriority();

    public Offer getOffer();

    public Money getDiscountedPrice();

    public void setDiscountedPrice(Money discountedPrice);

}
