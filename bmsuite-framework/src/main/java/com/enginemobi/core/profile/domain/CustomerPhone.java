package com.enginemobi.core.profile.domain;

import java.io.Serializable;

 public interface CustomerPhone extends Serializable {

     void setId(Long id);

     Long getId();

     void setPhoneName(String phoneName);

     String getPhoneName();

     Customer getCustomer();

     void setCustomer(Customer customer);

     Phone getPhone();

     void setPhone(Phone phone);

}
