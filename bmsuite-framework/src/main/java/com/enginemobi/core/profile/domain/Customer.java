package com.enginemobi.core.profile.domain;

import com.enginemobi.core.common.domain.audit.Auditable;
import com.enginemobi.core.generic.domain.BmSuiteEntity;

import java.util.List;
import java.util.Map;

 public interface Customer extends BmSuiteEntity<Long, Customer>, Auditable {

     Long getId();

     void setId(Long id);

     String getUsername();

     void setUsername(String username);

     String getPassword();

     void setPassword(String password);

     boolean isPasswordChangeRequired();

     void setPasswordChangeRequired(boolean passwordChangeRequired);

     String getFirstName();

     void setFirstName(String firstName);

     String getLastName();

     void setLastName(String lastName);

     String getEmailAddress();

     void setEmailAddress(String emailAddress);

     ChallengeQuestion getChallengeQuestion();

     void setChallengeQuestion(ChallengeQuestion challengeQuestion);

     String getChallengeAnswer();

     void setChallengeAnswer(String challengeAnswer);

     String getUnencodedPassword();

     void setUnencodedPassword(String unencodedPassword);

     boolean isReceiveEmail();

     void setReceiveEmail(boolean receiveEmail);

     boolean isRegistered();

     void setRegistered(boolean registered);

     String getUnencodedChallengeAnswer();

     void setUnencodedChallengeAnswer(String unencodedChallengeAnswer);



     void setCookied(boolean cookied);

     boolean isCookied();

     void setLoggedIn(boolean loggedIn);

     boolean isLoggedIn();

     void setAnonymous(boolean anonymous);

     boolean isAnonymous();


    /**
     * Returns true if this user has been deactivated.
     * Most implementations will not allow the user to login if they are deactivated.
     *
     * @return
     */
     boolean isDeactivated();

    /**
     * Sets the users deactivated status.
     *
     * @param deactivated
     */
     void setDeactivated(boolean deactivated);

     List<CustomerAddress> getCustomerAddresses();

     void setCustomerAddresses(List<CustomerAddress> customerAddresses);

     List<CustomerPhone> getCustomerPhones();

     void setCustomerPhones(List<CustomerPhone> customerPhones);

     List<CustomerPayment> getCustomerPayments();

     void setCustomerPayments(List<CustomerPayment> customerPayments);

    /**
     * The code used by an external system to determine if the user is tax exempt and/or what specific taxes the user is
     * exempt from.
     * @return the code for this user's tax exemption reason, usually to just be passed to an external system
     * @see {@link #isTaxExempt()}
     */
     String getTaxExemptionCode();

    /**
     * Associates a tax exemption code to this user to notate tax exemption status. Default behavior in the
     * {@link org.broadleafcommerce.core.pricing.service.tax.provider.SimpleTaxProvider} is that if this is set to
     * any value then this customer is tax exempt.
     *
     * @param exemption the tax exemption code for the customer
     * @see {@link #isTaxExempt()}
     */
     void setTaxExemptionCode(String exemption);

    /**
     * <p>
     * Convenience method to represent if this customer should be taxed or not when pricing their {@link Order}. Default
     * behavior in the {@link org.broadleafcommerce.core.pricing.service.tax.provider.SimpleTaxProvider} is that if there
     * is anything in {@link #getTaxExemptionCode()} then the customer is exempt.
     *
     * <p>
     * If you assign special meaning to the {@link #getTaxExemptionCode()} then this might be different and you should
     * determine specific tax exemption based on {@link #getTaxExemptionCode()}
     *
     * @return whether or not this customer is exempt from tax calculations
     */
     boolean isTaxExempt();

    /**
     * This returns a non-null map of transient properties that are not
     * persisted to the database.
     *
     * @return
     */
     Map<String, Object> getTransientProperties();

}
