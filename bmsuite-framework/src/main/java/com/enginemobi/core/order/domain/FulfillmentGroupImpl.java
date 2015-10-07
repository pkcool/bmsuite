package com.enginemobi.core.order.domain;

import com.enginemobi.common.money.Money;
import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.generic.domain.BmSuiteEntityImpl;
import com.enginemobi.core.offer.domain.FulfillmentGroupAdjustment;
import com.enginemobi.core.order.service.type.FulfillmentGroupStatusType;
import com.enginemobi.core.order.service.type.FulfillmentType;
import com.enginemobi.core.profile.domain.Address;
import com.enginemobi.core.profile.domain.AddressImpl;
import com.enginemobi.core.profile.domain.Phone;
import com.enginemobi.core.profile.domain.PhoneImpl;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "FULFILLMENT_GROUP", schema = SchemaConstant.BMSUITEDB_SCHEMA)
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
public class FulfillmentGroupImpl extends BmSuiteEntityImpl<Long, FulfillmentGroup> implements FulfillmentGroup {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "FULFILLMENT_GROUP_ID" )
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "FULFILLMENT_GROUP_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    protected Long id;

    @Column(name = "REFERENCE_NUMBER")
    @Index(name="FG_REFERENCE_INDEX", columnNames={"REFERENCE_NUMBER"})
    protected String referenceNumber;

    @Column(name = "METHOD")
    @Index(name="FG_METHOD_INDEX", columnNames={"METHOD"})
    @Deprecated
    protected String method;

    @Column(name = "SERVICE")
    @Index(name="FG_SERVICE_INDEX", columnNames={"SERVICE"})
    @Deprecated
    protected String service;

    @Column(name = "RETAIL_PRICE", precision=19, scale=5)
    protected BigDecimal retailFulfillmentPrice;

    @Column(name = "SALE_PRICE", precision=19, scale=5)
    protected BigDecimal saleFulfillmentPrice;

    @Column(name = "PRICE", precision=19, scale=5)
    protected BigDecimal fulfillmentPrice;

    @Column(name = "TYPE")
    protected String type;

    @Column(name = "TOTAL_TAX", precision=19, scale=5)
    protected BigDecimal totalTax;

    @Column(name = "TOTAL_ITEM_TAX", precision=19, scale=5)
    protected BigDecimal totalItemTax;

    @Column(name = "TOTAL_FEE_TAX", precision=19, scale=5)
    protected BigDecimal totalFeeTax;

    @Column(name = "TOTAL_FG_TAX", precision=19, scale=5)
    protected BigDecimal totalFulfillmentGroupTax;

    @Column(name = "DELIVERY_INSTRUCTION")
    protected String deliveryInstruction;

    @Column(name = "IS_PRIMARY")
    @Index(name="FG_PRIMARY_INDEX", columnNames={"IS_PRIMARY"})
    protected boolean primary = false;

    @Column(name = "MERCHANDISE_TOTAL", precision=19, scale=5)
    protected BigDecimal merchandiseTotal;

    @Column(name = "TOTAL", precision=19, scale=5)
    protected BigDecimal total;

    @Column(name = "STATUS")
    @Index(name="FG_STATUS_INDEX", columnNames={"STATUS"})
    protected String status;

    @Column(name = "SHIPPING_PRICE_TAXABLE")
    protected Boolean isShippingPriceTaxable = Boolean.FALSE;

    @ManyToOne(targetEntity = FulfillmentOptionImpl.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "FULFILLMENT_OPTION_ID")
    protected FulfillmentOption fulfillmentOption;

    @ManyToOne(targetEntity = OrderImpl.class, optional=false)
    @JoinColumn(name = "ORDER_ID")
    @Index(name="FG_ORDER_INDEX", columnNames={"ORDER_ID"})
    protected Order order;

    @Column(name = "FULFILLMENT_GROUP_SEQUNCE")
    protected Integer sequence;

    @ManyToOne(targetEntity = AddressImpl.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ADDRESS_ID")
    @Index(name="FG_ADDRESS_INDEX", columnNames={"ADDRESS_ID"})
    protected Address address;

    /**
     * @deprecated uses the phonePrimary property on AddressImpl instead
     */
    @ManyToOne(targetEntity = PhoneImpl.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PHONE_ID")
    @Index(name="FG_PHONE_INDEX", columnNames={"PHONE_ID"})
    @Deprecated
    protected Phone phone;


    @OneToMany(mappedBy = "fulfillmentGroup", targetEntity = FulfillmentGroupItemImpl.class, cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
    protected List<FulfillmentGroupItem> fulfillmentGroupItems = new ArrayList<FulfillmentGroupItem>();

    @OneToMany(mappedBy = "fulfillmentGroup", targetEntity = FulfillmentGroupFeeImpl.class, cascade = { CascadeType.ALL },
            orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "blOrderElements")
    protected List<FulfillmentGroupFee> fulfillmentGroupFees = new ArrayList<FulfillmentGroupFee>();


//    @OneToMany(mappedBy = "fulfillmentGroup", targetEntity = FulfillmentGroupAdjustmentImpl.class, cascade = { CascadeType.ALL },
  //          orphanRemoval = true)
    @Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
    protected List<FulfillmentGroupAdjustment> fulfillmentGroupAdjustments = new ArrayList<FulfillmentGroupAdjustment>();

    @OneToMany(fetch = FetchType.LAZY, targetEntity = TaxDetailImpl.class, cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinTable(name = "BLC_FG_FG_TAX_XREF", joinColumns = @JoinColumn(name = "FULFILLMENT_GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAX_DETAIL_ID"))
    @Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blOrderElements")
    protected List<TaxDetail> taxes = new ArrayList<TaxDetail>();

    @Column(name = "SHIPPING_OVERRIDE")
    protected Boolean shippingOverride;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public FulfillmentOption getFulfillmentOption() {
        return fulfillmentOption;
    }

    public void setFulfillmentOption(FulfillmentOption fulfillmentOption) {
        this.fulfillmentOption = fulfillmentOption;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public List<FulfillmentGroupItem> getFulfillmentGroupItems() {
        return fulfillmentGroupItems;
    }


    public void setFulfillmentGroupItems(List<FulfillmentGroupItem> fulfillmentGroupItems) {
        this.fulfillmentGroupItems = fulfillmentGroupItems;
    }

    public void addFulfillmentGroupItem(FulfillmentGroupItem fulfillmentGroupItem) {
        if (this.fulfillmentGroupItems == null) {
            this.fulfillmentGroupItems = new Vector<FulfillmentGroupItem>();
        }
        this.fulfillmentGroupItems.add(fulfillmentGroupItem);

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @deprecated use the phonePrimary property on the related Address instead
     */
    @Deprecated
    public Phone getPhone() {
        return phone;
    }

    /**
     * @deprecated use the phonePrimary property on the related Address instead
     */
    @Deprecated
    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Deprecated
    public String getMethod() {
        return method;
    }

    @Deprecated
    public void setMethod(String fulfillmentMethod) {
        this.method = fulfillmentMethod;
    }

    public Money getRetailFulfillmentPrice() {
        return null;
    }

    public void setRetailFulfillmentPrice(Money retailFulfillmentPrice) {
        //this.retailFulfillmentPrice = Money.toAmount(retailFulfillmentPrice);

    }

    public Money getRetailShippingPrice() {
        return getRetailFulfillmentPrice();
    }

    public void setRetailShippingPrice(Money retailShippingPrice) {
        setRetailFulfillmentPrice(retailShippingPrice);
    }

    public FulfillmentType getType() {
        return FulfillmentType.getInstance(type);
    }

    public void setType(FulfillmentType type) {
        this.type = type == null ? null : type.getType();
    }

    public List<FulfillmentGroupAdjustment> getFulfillmentGroupAdjustments() {
        return this.fulfillmentGroupAdjustments;
    }

    public Money getFulfillmentGroupAdjustmentsValue() {
        return null;
    }

    public void removeAllAdjustments() {
        if (fulfillmentGroupAdjustments != null) {
            for (FulfillmentGroupAdjustment adjustment : fulfillmentGroupAdjustments) {
                adjustment.setFulfillmentGroup(null);
            }
            fulfillmentGroupAdjustments.clear();
        }
    }

    public void setFulfillmentGroupAdjustments(List<FulfillmentGroupAdjustment> fulfillmentGroupAdjustments) {
        this.fulfillmentGroupAdjustments = fulfillmentGroupAdjustments;
    }

    public Money getSaleFulfillmentPrice() {
        return null;
    }

    public void setSaleFulfillmentPrice(Money saleFulfillmentPrice) {
    }

    public Money getSaleShippingPrice() {
        return getSaleFulfillmentPrice();
    }

    public void setSaleShippingPrice(Money saleShippingPrice) {
        setSaleFulfillmentPrice(saleShippingPrice);
    }

    public Money getFulfillmentPrice() {
        return null;
    }

    public void setFulfillmentPrice(Money fulfillmentPrice) {
    }

    public Money getShippingPrice() {
        return getFulfillmentPrice();
    }

    public void setShippingPrice(Money shippingPrice) {
        setFulfillmentPrice(shippingPrice);
    }

    public List<TaxDetail> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<TaxDetail> taxes) {
        this.taxes = taxes;
    }

    public Money getTotalTax() {
        return null;
    }

    public void setTotalTax(Money totalTax) {
    }

    public Money getTotalItemTax() {
        return null;
    }

    public void setTotalItemTax(Money totalItemTax) {
    }

    public Money getTotalFeeTax() {
        return null;
    }

    public void setTotalFeeTax(Money totalFeeTax) {
    }

    public Money getTotalFulfillmentGroupTax() {
        return null;
    }

    public void setTotalFulfillmentGroupTax(Money totalFulfillmentGroupTax) {

    }

    public String getDeliveryInstruction() {
        return deliveryInstruction;
    }

    public void setDeliveryInstruction(String deliveryInstruction) {
        this.deliveryInstruction = deliveryInstruction;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public Money getMerchandiseTotal() {
        return null;
    }

    public void setMerchandiseTotal(Money merchandiseTotal) {
    }

    public Money getTotal() {
        return null;
    }

    public void setTotal(Money orderTotal) {
    }

    public FulfillmentGroupStatusType getStatus() {
        return FulfillmentGroupStatusType.getInstance(status);
    }

    public void setStatus(FulfillmentGroupStatusType status) {
        this.status = status.getType();
    }

    public List<FulfillmentGroupFee> getFulfillmentGroupFees() {
        return fulfillmentGroupFees;
    }

    public void setFulfillmentGroupFees(List<FulfillmentGroupFee> fulfillmentGroupFees) {
        this.fulfillmentGroupFees = fulfillmentGroupFees;
    }

    public void addFulfillmentGroupFee(FulfillmentGroupFee fulfillmentGroupFee) {
        if (fulfillmentGroupFees == null) {
            fulfillmentGroupFees = new ArrayList<FulfillmentGroupFee>();
        }
        fulfillmentGroupFees.add(fulfillmentGroupFee);
    }

    public void removeAllFulfillmentGroupFees() {
        if (fulfillmentGroupFees != null) {
            fulfillmentGroupFees.clear();
        }
    }

    public Boolean isShippingPriceTaxable() {
        return isShippingPriceTaxable;
    }

    public void setIsShippingPriceTaxable(Boolean isShippingPriceTaxable) {
        this.isShippingPriceTaxable = isShippingPriceTaxable;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getSequence() {
        return this.sequence;
    }

    @Deprecated
    public String getService() {
        return service;
    }

    @Deprecated
    public void setService(String service) {
        this.service = service;
    }

    public String getCurrencyCode() {
        if (getOrder().getCurrency() != null) {
        }
        return null;
    }

    public Boolean getShippingOverride() {
        return shippingOverride == null ? false : shippingOverride;
    }

    public void setShippingOverride(Boolean shippingOverride) {
        this.shippingOverride = shippingOverride;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((fulfillmentGroupItems == null) ? 0 : fulfillmentGroupItems.hashCode());
        return result;
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
        FulfillmentGroupImpl other = (FulfillmentGroupImpl) obj;

        if (id != null && other.id != null) {
            return id.equals(other.id);
        }

        if (address == null) {
            if (other.address != null) {
                return false;
            }
        } else if (!address.equals(other.address)) {
            return false;
        }
        if (fulfillmentGroupItems == null) {
            if (other.fulfillmentGroupItems != null) {
                return false;
            }
        } else if (!fulfillmentGroupItems.equals(other.fulfillmentGroupItems)) {
            return false;
        }
        return true;
    }


}
