package com.enginemobi.core.catalog.domain;

import com.enginemobi.common.money.Money;
import com.enginemobi.common.util.DateUtil;
import com.enginemobi.core.common.domain.audit.AuditListener;
import com.enginemobi.core.common.domain.audit.AuditSection;
import com.enginemobi.core.common.domain.audit.Auditable;
import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.generic.domain.BmSuiteBaseEntityImpl;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
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
public class ProductImpl extends BmSuiteBaseEntityImpl<Long, Product> implements Product, Auditable {
    /** fields **/
    @Id
    @Column(name = "PRODUCT_ID", unique = true, nullable = false)
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "PRODUCT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

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
    private String sku;

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

    public boolean isActive() {
        return DateUtil.isActive(getActiveStartDate(), getActiveEndDate(), true);
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;

    }

    public Category getCategory() {
        return null;
    }

    public void setCategory(Category category) {

    }

    public Dimension getDimension() {
        return null;
    }

    public void setDimension(Dimension dimension) {

    }

    public BigDecimal getWidth() {
        return null;
    }

    public void setWidth(BigDecimal width) {

    }

    public BigDecimal getHeight() {
        return null;
    }

    public void setHeight(BigDecimal height) {

    }

    public BigDecimal getDepth() {
        return null;
    }

    public void setDepth(BigDecimal depth) {

    }

    public BigDecimal getGirth() {
        return null;
    }

    public void setGirth(BigDecimal girth) {

    }

    public String getDimensionString() {
        return null;
    }

    public Weight getWeight() {
        return null;
    }

    public void setWeight(Weight weight) {

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
        return false;
    }

    public void setFeaturedProduct(boolean isFeaturedProduct) {

    }

    public String getPromoMessage() {
        return null;
    }

    public void setPromoMessage(String promoMessage) {

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
        return null;
    }

    public void setUrl(String url) {

    }

    public Boolean getOverrideGeneratedUrl() {
        return null;
    }

    public void setOverrideGeneratedUrl(Boolean overrideGeneratedUrl) {

    }

    public String getUrlKey() {
        return null;
    }

    public void setUrlKey(String url) {

    }

    public String getDisplayTemplate() {
        return null;
    }

    public void setDisplayTemplate(String displayTemplate) {

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
