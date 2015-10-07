package com.enginemobi.core.offer.domain;


import com.enginemobi.common.money.Money;
import com.enginemobi.core.order.domain.Order;

import java.io.Serializable;

public interface CandidateOrderOffer extends Serializable {

    public Money getDiscountedPrice();

    public void setDiscountedPrice(Money discountedPrice);

    public Long getId();

    public void setId(Long id);

    public Order getOrder();

    public void setOrder(Order order);

    public void setOffer(Offer offer);

    public Offer getOffer();

    public int getPriority();
}
