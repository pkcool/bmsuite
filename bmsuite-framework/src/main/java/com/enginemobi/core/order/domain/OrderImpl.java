package com.enginemobi.core.order.domain;

import com.enginemobi.common.money.Money;
import com.enginemobi.common.persistence.PreviewStatus;
import com.enginemobi.common.persistence.Previewable;
import com.enginemobi.core.common.domain.audit.AuditListener;
import com.enginemobi.core.common.domain.audit.AuditSection;
import com.enginemobi.core.common.domain.audit.Auditable;
import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.offer.domain.*;
import com.enginemobi.core.order.service.type.OrderStatus;
import com.enginemobi.core.payment.domain.OrderPayment;
import com.enginemobi.core.payment.domain.OrderPaymentImpl;
import com.enginemobi.core.profile.domain.Customer;
import com.enginemobi.core.profile.domain.CustomerImpl;
import com.enginemobi.core.reference.currency.domain.CurrencyImpl;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Index;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.*;

@Entity
@EntityListeners(value = AuditListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ORDER")
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
public class OrderImpl implements Order, Auditable, Previewable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "ORDER_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    @Column (name ="ORDER_ID" , unique=true , nullable=false )
    protected Long id;

    @Embedded
    protected AuditSection auditSection = new AuditSection();

    @Embedded
    protected PreviewStatus previewable = new PreviewStatus();

    @Column(name = "NAME")
    @Index(name="ORDER_NAME_INDEX", columnNames={"NAME"})
    protected String name;

    @ManyToOne(targetEntity = CustomerImpl.class, optional=false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @Index(name="ORDER_CUSTOMER_INDEX", columnNames={"CUSTOMER_ID"})
    protected Customer customer;

    @Column(name = "ORDER_STATUS")
    @Index(name="ORDER_STATUS_INDEX", columnNames={"ORDER_STATUS"})
    protected String status;

    @Column(name = "TOTAL_TAX", precision=19, scale=5)
    protected BigDecimal totalTax;

    @Column(name = "TOTAL_SHIPPING", precision=19, scale=5)
    protected BigDecimal totalFulfillmentCharges;

    @Column(name = "ORDER_SUBTOTAL", precision=19, scale=5)
    protected BigDecimal subTotal;

    @Column(name = "ORDER_TOTAL", precision=19, scale=5)
    protected BigDecimal total;

    @Column(name = "SUBMIT_DATE")
    protected Date submitDate;

    @Column(name = "ORDER_NUMBER")
    @Index(name="ORDER_NUMBER_INDEX", columnNames={"ORDER_NUMBER"})
    private String orderNumber;

    @Column(name = "EMAIL_ADDRESS")
    @Index(name="ORDER_EMAIL_INDEX", columnNames={"EMAIL_ADDRESS"})
    protected String emailAddress;

    @OneToMany(mappedBy = "order", targetEntity = OrderItemImpl.class, cascade = {CascadeType.ALL})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
    protected List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @OneToMany(mappedBy = "order", targetEntity = FulfillmentGroupImpl.class, cascade = {CascadeType.ALL})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
    protected List<FulfillmentGroup> fulfillmentGroups = new ArrayList<FulfillmentGroup>();

    @OneToMany(mappedBy = "order", targetEntity = OrderAdjustmentImpl.class, cascade = { CascadeType.ALL },
            orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
    protected List<OrderAdjustment> orderAdjustments = new ArrayList<OrderAdjustment>();

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = OfferCodeImpl.class)
    @JoinTable(name = "BLC_ORDER_OFFER_CODE_XREF", joinColumns = @JoinColumn(name = "ORDER_ID",
            referencedColumnName = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "OFFER_CODE_ID",
            referencedColumnName = "OFFER_CODE_ID"))
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
    protected List<OfferCode> addedOfferCodes = new ArrayList<OfferCode>();

    @OneToMany(mappedBy = "order", targetEntity = CandidateOrderOfferImpl.class, cascade = { CascadeType.ALL },
            orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
    protected List<CandidateOrderOffer> candidateOrderOffers = new ArrayList<CandidateOrderOffer>();

    @OneToMany(mappedBy = "order", targetEntity = OrderPaymentImpl.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
    protected List<OrderPayment> payments = new ArrayList<OrderPayment>();

    @ManyToMany(targetEntity=OfferInfoImpl.class)
    @JoinTable(name = "BLC_ADDITIONAL_OFFER_INFO", joinColumns = @JoinColumn(name = "BLC_ORDER_ORDER_ID",
            referencedColumnName = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "OFFER_INFO_ID",
            referencedColumnName = "OFFER_INFO_ID"))
    @MapKeyJoinColumn(name = "OFFER_ID")
    @MapKeyClass(OfferImpl.class)
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
    @BatchSize(size = 50)
    protected Map<Offer, OfferInfo> additionalOfferInformation = new HashMap<Offer, OfferInfo>();

    @OneToMany(mappedBy = "order", targetEntity = OrderAttributeImpl.class, cascade = { CascadeType.ALL },
            orphanRemoval = true)
    @Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
    @MapKey(name="name")
    protected Map<String,OrderAttribute> orderAttributes = new HashMap<String,OrderAttribute>();

    @ManyToOne(targetEntity = CurrencyImpl.class)
    @JoinColumn(name = "CURRENCY_CODE")
    protected Currency currency;

    @Column(name = "TAX_OVERRIDE")
    protected Boolean taxOverride;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return false;
    }

    public AuditSection getAuditable() {
        return auditSection;
    }

    public void setAuditable(AuditSection auditSection) {
        this.auditSection = auditSection;
    }

    public Money getSubTotal() {
        return null;
    }

    public void setSubTotal(Money subTotal) {
    }

    public Money calculateSubTotal() {
        return null;
    }

    public void assignOrderItemsFinalPrice() {
        for (OrderItem orderItem : orderItems) {
            orderItem.assignFinalPrice();
        }
    }

    public Money getTotal() {
        return null;
    }

    public Money getTotalAfterAppliedPayments() {
        Money myTotal = getTotal();
        if (myTotal == null) {
            return null;
        }
        return null;
    }

    public void setTotal(Money orderTotal) {
    }

    public Boolean getPreview() {
        if (previewable == null) {
            previewable = new PreviewStatus();
        }
        return previewable.getPreview();
    }

    public void setPreview(Boolean preview) {
        if (previewable == null) {
            previewable = new PreviewStatus();
        }
        previewable.setPreview(preview);
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public OrderStatus getStatus() {
        return OrderStatus.getInstance(status);
    }


    public void setStatus(OrderStatus status) {
        this.status = status.getType();
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }


    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }


    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }


    public List<FulfillmentGroup> getFulfillmentGroups() {
        return fulfillmentGroups;
    }


    public void setFulfillmentGroups(List<FulfillmentGroup> fulfillmentGroups) {
        this.fulfillmentGroups = fulfillmentGroups;
    }


    public void setCandidateOrderOffers(List<CandidateOrderOffer> candidateOrderOffers) {
        this.candidateOrderOffers = candidateOrderOffers;
    }


    public List<CandidateOrderOffer> getCandidateOrderOffers() {
        return candidateOrderOffers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getTotalTax() {
        return null;
    }

    public void setTotalTax(Money totalTax) {
    }

    public Money getTotalShipping() {
        return getTotalFulfillmentCharges();
    }

    public void setTotalShipping(Money totalShipping) {
        setTotalFulfillmentCharges(totalShipping);
    }

    public Money getTotalFulfillmentCharges() {
        return null;
    }

    public void setTotalFulfillmentCharges(Money totalFulfillmentCharges) {
    }

    public List<OrderPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<OrderPayment> payments) {
        this.payments = payments;
    }

    public boolean hasCategoryItem(String categoryName) {
        for (OrderItem orderItem : orderItems) {
            if(orderItem.isInCategory(categoryName)) {
                return true;
            }
        }
        return false;
    }

    public List<OrderAdjustment> getOrderAdjustments() {
        return this.orderAdjustments;
    }

    protected void setOrderAdjustments(List<OrderAdjustment> orderAdjustments) {
        this.orderAdjustments = orderAdjustments;
    }


    public List<OfferCode> getAddedOfferCodes() {
        return addedOfferCodes;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getFulfillmentStatus() {
        return null;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Map<Offer, OfferInfo> getAdditionalOfferInformation() {
        return additionalOfferInformation;
    }

    public void setAdditionalOfferInformation(Map<Offer, OfferInfo> additionalOfferInformation) {
        this.additionalOfferInformation = additionalOfferInformation;
    }

    public Money getItemAdjustmentsValue() {
        return null;
    }

    public Money getFulfillmentGroupAdjustmentsValue() {
        return null;
    }

    public Money getOrderAdjustmentsValue() {
        return null;
    }

    public Money getTotalAdjustmentsValue() {
        Money totalAdjustmentsValue = getItemAdjustmentsValue();
        return totalAdjustmentsValue;
    }

    public boolean updatePrices() {
        boolean updated = false;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.updateSaleAndRetailPrices()) {
                updated = true;
            }
        }
        return updated;
    }

    public boolean finalizeItemPrices() {
        boolean updated = false;
        for (OrderItem orderItem : orderItems) {
            orderItem.finalizePrice();
        }
        return updated;
    }

    public Map<String, OrderAttribute> getOrderAttributes() {
        return orderAttributes;
    }


    public void setOrderAttributes(Map<String, OrderAttribute> orderAttributes) {
        this.orderAttributes = orderAttributes;
    }


    @Deprecated
    public void addAddedOfferCode(OfferCode offerCode) {
        addOfferCode(offerCode);
    }


    public void addOfferCode(OfferCode offerCode) {
        getAddedOfferCodes().add(offerCode);
    }


    public com.enginemobi.core.reference.currency.domain.Currency getCurrency() {
        return null;
    }

    public void setCurrency(com.enginemobi.core.reference.currency.domain.Currency currency) {

    }


    public void setCurrency(Currency currency) {
    }



    public Boolean getTaxOverride() {
        return taxOverride == null ? false : taxOverride;
    }


    public void setTaxOverride(Boolean taxOverride) {
        this.taxOverride = taxOverride;
    }


    public int getItemCount() {
        int count = 0;
        return count;
    }


    public boolean getHasOrderAdjustments() {
        return false;
    }

    public String getMainEntityName() {
        String customerName = null;
        String orderNumber = getOrderNumber();
        if (!StringUtils.isEmpty(getCustomer().getFirstName()) && !StringUtils.isEmpty(getCustomer().getLastName())) {
            customerName = getCustomer().getFirstName() + " " + getCustomer().getLastName();
        }
        if (!StringUtils.isEmpty(orderNumber) && !StringUtils.isEmpty(customerName)) {
            return orderNumber + " - " + customerName;
        }
        if (!StringUtils.isEmpty(orderNumber)) {
            return orderNumber;
        }
        if (!StringUtils.isEmpty(customerName)) {
            return customerName;
        }
        return "";
    }

    public String getCurrencyCode() {
        return null;
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
        OrderImpl other = (OrderImpl) obj;

        if (id != null && other.id != null) {
            return id.equals(other.id);
        }

        if (customer == null) {
            if (other.customer != null) {
                return false;
            }
        } else if (!customer.equals(other.customer)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        return result;
    }

    public AuditSection getAuditSection() {
        return null;
    }

    public void setAuditSection(AuditSection audit) {

    }

    public int compareTo(Order o) {
        return 0;
    }
}
