package com.enginemobi.core.catalog.domain;

import com.enginemobi.common.money.Money;
import com.enginemobi.common.persistence.ArchiveStatus;
import com.enginemobi.common.persistence.Status;
import com.enginemobi.common.util.DateUtil;
import com.enginemobi.core.common.domain.audit.AuditListener;
import com.enginemobi.core.common.domain.audit.AuditSection;
import com.enginemobi.core.common.domain.audit.Auditable;
import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.generic.domain.BmSuiteBaseEntityImpl;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The Class ProductImpl is the default implementation of {@link Product}. A
 * product is a general description of an item that can be sold (for example: a
 * hat).
 * If you want to add fields specific to your implementation of
 * BroadLeafCommerce you should extend this class and add your fields. If you
 * need to make significant changes to the ProductImpl then you should implement
 * your own version of {@link Product}. <br>
 * <br>
 * This implementation uses a Hibernate implementation of JPA configured through
 * annotations.
 *
 * @author bc
 * @see {@link Product}
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "PRODUCT", schema = SchemaConstant.BMSUITEDB_SCHEMA,
        indexes={
                @Index(name="ACTIVE_START_DATE_INDEX", columnList="ACTIVE_START_DATE")})
public class ProductImpl extends BmSuiteBaseEntityImpl<Long, Product> implements Product, Status, Auditable {
    /** fields **/
    @Id
    @Column(name = "PRODUCT_ID", unique = true, nullable = false)
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "PRODUCT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    protected Long id;

    @Embedded
    protected AuditSection auditSection = new AuditSection();

    @Column(name = "NAME")
    protected String name;

    @Column(name = "DESCRIPTION")
    protected String description;

    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    @Column(name = "LONG_DESCRIPTION", length = Integer.MAX_VALUE - 1)
    protected String longDescription;

    @Column(name = "ACTIVE_START_DATE")
    protected Date activeStartDate;


    @Column(name = "ACTIVE_END_DATE")
    protected Date activeEndDate;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name = "SKU")
    protected String sku;

    @ManyToOne(targetEntity = CategoryImpl.class)
    @JoinColumn(name = "DEFAULT_CATEGORY_ID")
    protected Category defaultCategory;

    @Column(name = "PRODUCT_VIRTUAL")
    protected boolean productVirtual = false;

    @Column(name = "PRODUCT_SHIP")
    protected boolean productShippable = false;


    @Column(name = "PRODUCT_FREE")
    protected boolean productIsFree = false;

    @Embedded
    protected Dimension dimension = new Dimension();

    @Embedded
    protected Weight weight = new Weight();

    @Column(name = "IS_FEATURED_PRODUCT", nullable = false)
    protected Boolean isFeaturedProduct = false;

    @Transient
    protected String promoMessage;

    @Column(name = "URL")
    protected String url;

    @Column(name = "OVERRIDE_GENERATED_URL")
    protected Boolean overrideGeneratedUrl = false;

    @Column(name = "URL_KEY")
    protected String urlKey;

    @Column(name = "DISPLAY_TEMPLATE")
    protected String displayTemplate;

    @Embedded
    protected ArchiveStatus archiveStatus = new ArchiveStatus();

    @Embedded
    protected ProductAvailability productAvailability = new ProductAvailability();
    /** end of fields **/


    /** methods **/

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public AuditSection getAuditSection() {
        return this.auditSection;
    }
    public void setAuditSection(AuditSection audit) {
        this.auditSection = audit;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;

    }

    public boolean getProductIsShippable() {
        return productShippable;
    }

    public void setProductIsShippable(boolean productShipeable) {
        this.productShippable = productShipeable;
    }

    public boolean getProductIsVirtual() {
        return productVirtual;
    }

    public void setProductIsVirtual(boolean productVirtual) {
        this.productVirtual = productVirtual;
    }

    public boolean getProductIsFree() {
        return productIsFree;
    }

    public void setProductIsFree(boolean productIsFree) {
        this.productIsFree = productIsFree;
    }

    public Date getActiveStartDate() {
        return activeStartDate;
    }

    public void setActiveStartDate(Date activeStartDate) {
        this.activeStartDate = activeStartDate;

    }

    public Date getActiveEndDate() {
        return activeEndDate;
    }

    public void setActiveEndDate(Date activeEndDate) {
        this.activeEndDate = activeEndDate;

    }

    public Character getArchived() {
        ArchiveStatus temp;
        if (archiveStatus == null) {
            temp = new ArchiveStatus();
        } else {
            temp = archiveStatus;
        }
        return temp.getArchived();
    }

    public void setArchived(Character archived) {
        if (archiveStatus == null) {
            archiveStatus = new ArchiveStatus();
        }
        archiveStatus.setArchived(archived);
    }

    public boolean isActive() {
        return DateUtil.isActive(getActiveStartDate(), getActiveEndDate(), true) && 'Y' != getArchived();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;

    }

    public Category getCategory() {
        return defaultCategory;
    }

    public void setCategory(Category category) {
        this.defaultCategory = category;

    }

    public ProductAvailability getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(ProductAvailability productAvailability) {
        this.productAvailability = productAvailability;

    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public BigDecimal getWidth() {
        return dimension.getWidth();
    }

    public void setWidth(BigDecimal width) {
        dimension.setWidth(width);
    }

    public BigDecimal getHeight() {
        return dimension.getHeight();
    }

    public void setHeight(BigDecimal height) {
        dimension.setHeight(height);
    }

    public BigDecimal getDepth() {
        return dimension.getDepth();
    }

    public void setDepth(BigDecimal depth) {
        dimension.setDepth(depth);
    }

    public BigDecimal getGirth() {
        return dimension.getGirth();
    }

    public void setGirth(BigDecimal girth) {
        dimension.setGirth(girth);
    }

    public String getDimensionString() {
        return dimension.getDimensionString();
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;

    }

    public List<RelatedProduct> getCrossSaleProducts() {
        return null;
    }

    public void setCrossSaleProducts(List<RelatedProduct> crossSaleProducts) {

    }

    public List<RelatedProduct> getUpSaleProducts() {
        return null;
    }

    public void setUpSaleProducts(List<RelatedProduct> upSaleProducts) {

    }

    public boolean isFeaturedProduct() {
        return isFeaturedProduct;
    }

    public void setFeaturedProduct(boolean isFeaturedProduct) {
        this.isFeaturedProduct = isFeaturedProduct;

    }

    public String getPromoMessage() {
        return promoMessage;
    }

    public void setPromoMessage(String promoMessage) {
        this.promoMessage = promoMessage;
    }

    public List<ProductOption> getProductOptions() {
        return null;
    }

    public void setProductOptions(List<ProductOption> productOptions) {

    }

    public Map<String, Set<String>> getProductOptionValuesMap() {
        return null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getOverrideGeneratedUrl() {
        return overrideGeneratedUrl;
    }

    public void setOverrideGeneratedUrl(Boolean overrideGeneratedUrl) {
        this.overrideGeneratedUrl = overrideGeneratedUrl;

    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;

    }

    public String getDisplayTemplate() {
        return displayTemplate;
    }

    public void setDisplayTemplate(String displayTemplate) {
        this.displayTemplate = displayTemplate;

    }

    public String getGeneratedUrl() {
        return null;
    }

    public List<Category> getAllParentCategories() {
        return null;
    }

    public void setAllParentCategories(List<Category> allParentCategories) {

    }

    public String getTaxCode() {
        return null;
    }

    public void setTaxCode(String taxCode) {

    }

    public Money getRetailPrice() {
        return null;
    }

    public Money getSalePrice() {
        return null;
    }
}
