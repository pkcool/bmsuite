package com.enginemobi.core.profile.domain;

import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.generic.domain.BmSuiteEntityImpl;
import org.hibernate.annotations.Index;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "CUSTOMER_PHONE", uniqueConstraints = @UniqueConstraint(name="CSTMR_PHONE_UNIQUE_CNSTRNT", columnNames = { "CUSTOMER_ID", "PHONE_NAME" }))
public class CustomerPhoneImpl extends BmSuiteEntityImpl<Long, Customer> implements CustomerPhone{

    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "CUSTOMER_PHONE_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    @Column(name = "CUSTOMER_PHONE_ID")
    protected Long id;

    @Column(name = "PHONE_NAME")
    protected String phoneName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = CustomerImpl.class, optional=false)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = PhoneImpl.class, optional=false)
    @JoinColumn(name = "PHONE_ID")
    @Index(name="CUSTPHONE_PHONE_INDEX", columnNames={"PHONE_ID"})
    protected Phone phone;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((phoneName == null) ? 0 : phoneName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        CustomerPhoneImpl other = (CustomerPhoneImpl) obj;

        if (id != null && other.id != null) {
            return id.equals(other.id);
        }

        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (phoneName == null) {
            if (other.phoneName != null)
                return false;
        } else if (!phoneName.equals(other.phoneName))
            return false;
        return true;
    }

}
