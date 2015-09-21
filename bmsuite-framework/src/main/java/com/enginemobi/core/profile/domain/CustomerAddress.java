package com.enginemobi.core.profile.domain;

import com.enginemobi.common.persistence.Status;
import com.enginemobi.core.generic.domain.BmSuiteEntity;

 public interface CustomerAddress extends BmSuiteEntity<Long, CustomerAddress>, Status {

     void setId(Long id);

     Long getId();

     void setAddressName(String addressName);

     String getAddressName();

     Customer getCustomer();

     void setCustomer(Customer customer);

     Address getAddress();

     void setAddress(Address address);
    
   
    

}
