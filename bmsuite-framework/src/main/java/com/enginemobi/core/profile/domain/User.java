package com.enginemobi.core.profile.domain;

import com.enginemobi.core.generic.domain.BmSuiteEntity;

import java.util.List;

public interface User extends BmSuiteEntity<Long, User> {

    void setRoles(List<Role> roles);

    List<Role> getRoles();

    void setCustomer(Customer customer);
    Customer getCustomer();

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getEmailAddress();

    void setEmailAddress(String emailAddress);
}
