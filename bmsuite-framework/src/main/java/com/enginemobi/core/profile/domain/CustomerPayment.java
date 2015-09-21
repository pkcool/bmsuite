package com.enginemobi.core.profile.domain;

import com.enginemobi.core.generic.domain.BmSuiteEntity;

import java.util.Map;

 public interface CustomerPayment extends BmSuiteEntity<Long, CustomerPayment>, AdditionalFields{

     void setId(Long id);

     Long getId();

     Customer getCustomer();

     void setCustomer(Customer customer);

     Address getBillingAddress();

     void setBillingAddress(Address billingAddress);

     String getPaymentToken();

     void setPaymentToken(String paymentToken);

     boolean isDefault();

     void setDefault(boolean isDefault);

     Map<String, String> getAdditionalFields();

     void setAdditionalFields(Map<String, String> additionalFields);

}
