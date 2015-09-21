package com.enginemobi.core.profile.domain;

import com.enginemobi.core.common.domain.audit.AuditListener;
import com.enginemobi.core.common.domain.audit.AuditSection;
import com.enginemobi.core.common.domain.audit.Auditable;
import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.generic.domain.BmSuiteEntityImpl;
import com.enginemobi.core.store.domain.Store;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
@EntityListeners(value = { AuditListener.class, CustomerPersistedEntityListener.class })
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BLC_CUSTOMER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "blCustomerElements")
public class CustomerImpl extends BmSuiteEntityImpl<Long, Customer> implements Customer, Auditable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CUSTOMER_ID")
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "CUSTOMER_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    protected Long id;

    @Embedded
    protected AuditSection auditSection = new AuditSection();

    @NotEmpty
    @Column(name="ADMIN_NAME", length=100, unique=true)
    private String adminName;

    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "CUSTOMER_ROLE", schema=SchemaConstant.BMSUITEDB_SCHEMA, joinColumns = {
            @JoinColumn(name = "CUSTOMER_ID", nullable = false, updatable = false) }
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
    private List<Role> roles = new ArrayList<Role>();

    @Column(name = "USER_NAME")
    protected String username;

    @Column(name = "PASSWORD")
    protected String password;

    @Column(name = "EMAIL_ADDRESS")
    @Index(name = "CUSTOMER_EMAIL_INDEX", columnNames = { "EMAIL_ADDRESS" })
    protected String emailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STORE_ID", nullable=false)
    private Store store;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "LAST_NAME")
    protected String lastName;

    @ManyToOne(targetEntity = ChallengeQuestionImpl.class)
    @JoinColumn(name = "CHALLENGE_QUESTION_ID")
    @Index(name="CUSTOMER_CHALLENGE_INDEX", columnNames={"CHALLENGE_QUESTION_ID"})
    protected ChallengeQuestion challengeQuestion;

    @Column(name = "CHALLENGE_ANSWER")
    protected String challengeAnswer;

    @Column(name = "PASSWORD_CHANGE_REQUIRED")
    protected Boolean passwordChangeRequired = false;

    @Column(name = "RECEIVE_EMAIL")
    protected Boolean receiveEmail = true;

    @Column(name = "IS_REGISTERED")
    protected Boolean registered = false;
    
    @Column(name = "DEACTIVATED")
    protected Boolean deactivated = false;


    @OneToMany(mappedBy = "customer", targetEntity = CustomerAddressImpl.class, cascade = {CascadeType.ALL})
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
    @Where(clause = "archived != 'Y'")
    protected List<CustomerAddress> customerAddresses = new ArrayList<CustomerAddress>();

    @OneToMany(mappedBy = "customer", targetEntity = CustomerPhoneImpl.class, cascade = {CascadeType.ALL})
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
    protected List<CustomerPhone> customerPhones = new ArrayList<CustomerPhone>();

    @OneToMany(mappedBy = "customer", targetEntity = CustomerPaymentImpl.class, cascade = {CascadeType.ALL})
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
    @BatchSize(size = 50)
    protected List<CustomerPayment> customerPayments  = new ArrayList<CustomerPayment>();

    @Column(name = "TAX_EXEMPTION_CODE")
    protected String taxExemptionCode;

    @Transient
    protected String unencodedPassword;

    @Transient
    protected String unencodedChallengeAnswer;
    
    @Transient
    protected boolean anonymous;

    @Transient
    protected boolean cookied;

    @Transient
    protected boolean loggedIn;

    @Transient
    protected Map<String, Object> transientProperties = new HashMap<String, Object>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public AuditSection getAuditSection() {
        return auditSection;
    }

    public void setAuditSection(AuditSection audit) {
        auditSection = audit;

    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
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


    public boolean isPasswordChangeRequired() {
        return BooleanUtils.toBoolean(passwordChangeRequired);
    }


    public void setPasswordChangeRequired(boolean passwordChangeRequired) {
        this.passwordChangeRequired = Boolean.valueOf(passwordChangeRequired);
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    public ChallengeQuestion getChallengeQuestion() {
        return challengeQuestion;
    }


    public void setChallengeQuestion(ChallengeQuestion challengeQuestion) {
        this.challengeQuestion = challengeQuestion;
    }


    public String getChallengeAnswer() {
        return challengeAnswer;
    }


    public void setChallengeAnswer(String challengeAnswer) {
        this.challengeAnswer = challengeAnswer;
    }


    public String getUnencodedPassword() {
        return unencodedPassword;
    }


    public void setUnencodedPassword(String unencodedPassword) {
        this.unencodedPassword = unencodedPassword;
    }


    public boolean isReceiveEmail() {
        return BooleanUtils.toBoolean(receiveEmail);
    }


    public void setReceiveEmail(boolean receiveEmail) {
        this.receiveEmail = Boolean.valueOf(receiveEmail);
    }


    public boolean isRegistered() {
        return BooleanUtils.toBoolean(registered);
    }


    public void setRegistered(boolean registered) {
        this.registered = Boolean.valueOf(registered);
    }


    public String getUnencodedChallengeAnswer() {
        return unencodedChallengeAnswer;
    }


    public void setUnencodedChallengeAnswer(String unencodedChallengeAnswer) {
        this.unencodedChallengeAnswer = unencodedChallengeAnswer;
    }


    public boolean isAnonymous() {
        return anonymous;
    }


    public boolean isCookied() {
        return cookied;
    }


    public boolean isLoggedIn() {
        return loggedIn;
    }


    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
        if (anonymous) {
            cookied = false;
            loggedIn = false;
        }
    }


    public void setCookied(boolean cookied) {
        this.cookied = cookied;
        if (cookied) {
            anonymous = false;
            loggedIn = false;
        }
    }


    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
        if (loggedIn) {
            anonymous = false;
            cookied = false;
        }
    }



    public boolean isDeactivated() {
        return BooleanUtils.toBoolean(deactivated);
    }


    public void setDeactivated(boolean deactivated) {
        this.deactivated = Boolean.valueOf(deactivated);
    }


    public List<CustomerAddress> getCustomerAddresses() {
        return customerAddresses;
    }


    public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
        this.customerAddresses = customerAddresses;
    }


    public List<CustomerPhone> getCustomerPhones() {
        return customerPhones;
    }


    public void setCustomerPhones(List<CustomerPhone> customerPhones) {
        this.customerPhones = customerPhones;
    }


    public List<CustomerPayment> getCustomerPayments() {
        return customerPayments;
    }


    public void setCustomerPayments(List<CustomerPayment> customerPayments) {
        this.customerPayments = customerPayments;
    }


    public String getMainEntityName() {
        if (!StringUtils.isEmpty(getFirstName()) && !StringUtils.isEmpty(getLastName())) {
            return getFirstName() + " " + getLastName();
        }
        return String.valueOf(getId());
    }


    public Map<String, Object> getTransientProperties() {
        return transientProperties;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }
        CustomerImpl other = (CustomerImpl) obj;

        if (id != null && other.id != null) {
            return id.equals(other.id);
        }

        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }


    public String getTaxExemptionCode() {
        return this.taxExemptionCode;
    }

    public void setTaxExemptionCode(String exemption) {
        this.taxExemptionCode = exemption;
    }
    
    public boolean isTaxExempt() {
        return StringUtils.isNotEmpty(taxExemptionCode);
    }
    
}
