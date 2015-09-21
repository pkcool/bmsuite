package com.enginemobi.core.profile.domain;

import com.enginemobi.core.common.domain.audit.AuditListener;
import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.generic.domain.BmSuiteEntityImpl;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(value = { AuditListener.class, CustomerPersistedEntityListener.class })
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "USER")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "blCustomerElements")
public class UserImpl extends BmSuiteEntityImpl<Long, User> implements User{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USER_ID")
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "USER_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    protected Long id;

    @ManyToMany(fetch= FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "USER_ROLE", schema= SchemaConstant.BMSUITEDB_SCHEMA, joinColumns = {
            @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }
            ,
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID",
                    nullable = false, updatable = false) }
    )
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE

    })
    protected List<Role> roles = new ArrayList<Role>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Customer customer;

    @NotEmpty
    @Column(name = "USER_NAME")
    protected String username;

    @Column(name = "PASSWORD")
    protected String password;

    @Column(name = "EMAIL_ADDRESS")
    @Index(name = "CUSTOMER_EMAIL_INDEX", columnNames = { "EMAIL_ADDRESS" })
    protected String emailAddress;





    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }
}
