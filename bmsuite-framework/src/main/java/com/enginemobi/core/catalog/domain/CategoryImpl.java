package com.enginemobi.core.catalog.domain;

import com.enginemobi.common.persistence.ArchiveStatus;
import com.enginemobi.common.persistence.Status;
import com.enginemobi.common.util.DateUtil;
import com.enginemobi.core.common.domain.audit.AuditListener;
import com.enginemobi.core.common.domain.audit.AuditSection;
import com.enginemobi.core.common.domain.audit.Auditable;
import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.generic.domain.BmSuiteEntityImpl;
import com.enginemobi.core.store.domain.Store;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.annotation.Nonnull;
import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "CATEGORY", schema= SchemaConstant.BMSUITEDB_SCHEMA)
public class CategoryImpl extends BmSuiteEntityImpl<Long, Category> implements Category, Status, Auditable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CATEGORY_ID", unique = true, nullable = false)
    @TableGenerator(name = SchemaConstant.TABLE_GENERATOR_NAME, table = SchemaConstant.TABLE_GENERATOR_TABLE_NAME,
            pkColumnName = SchemaConstant.TABLE_GENERATOR_PKCOLUMN_NAME, valueColumnName = SchemaConstant.TABLE_GENERATOR_VALUE_COLUMN_NAME,
            pkColumnValue = "CATEGORY_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SchemaConstant.TABLE_GENERATOR_NAME)
    protected Long id;

    @Column(name = "NAME", nullable=false)
    @org.hibernate.annotations.Index(name="CATEGORY_NAME_INDEX", columnNames={"NAME"})
    protected String name;

    @Column(name = "VISIBLE")
    private boolean visible;

    @Column(name = "DEPTH")
    private Integer depth;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    protected Category parent;

    @Column(name = "URL")
    @org.hibernate.annotations.Index(name="CATEGORY_URL_INDEX", columnNames={"URL"})
    protected String url;


    @Column(name = "DESCRIPTION")
    protected String description;

    @Column(name = "TAX_CODE")
    protected String taxCode;

    @Column(name = "ACTIVE_START_DATE")
    protected Date activeStartDate;

    @Column(name = "ACTIVE_END_DATE")
    protected Date activeEndDate;

    @Column(name = "DISPLAY_TEMPLATE")
    protected String displayTemplate;

    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    @Column(name = "LONG_DESCRIPTION", length = Integer.MAX_VALUE - 1)
    protected String longDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STORE_ID", nullable=false)
    private Store merchantStore;

    @OneToMany(mappedBy = "category", targetEntity = FeaturedProductImpl.class, cascade = {CascadeType.ALL})
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blCategories")
    @OrderBy(value="sequence")
    @BatchSize(size = 50)
    protected List<FeaturedProduct> featuredProducts = new ArrayList<FeaturedProduct>(10);

    @Embedded
    protected ArchiveStatus archiveStatus = new ArchiveStatus();

    @Embedded
    private AuditSection auditSection = new AuditSection();


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public boolean isVisible() {
        return visible;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getUrl() {
        // TODO: if null return
        // if blank return
        // if startswith "/" return
        // if contains a ":" and no "?" or (contains a ":" before a "?") return
        // else "add a /" at the beginning
        if(url == null || url.equals("") || url.startsWith("/")) {
            return url;
        } else if ((url.contains(":") && !url.contains("?")) || url.indexOf('?', url.indexOf(':')) != -1) {
            return url;
        } else {
            return "/" + url;
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getActiveStartDate() {
        if ('Y'==getArchived()) {
            return null;
        }
        return activeStartDate;
    }

    public void setActiveStartDate(Date activeStartDate) {
        this.activeStartDate = (activeStartDate == null) ? null : new Date(activeStartDate.getTime());
    }

    public Date getActiveEndDate() {
        return activeEndDate;
    }

    public void setActiveEndDate(Date activeEndDate) {
        this.activeEndDate = (activeEndDate == null) ? null : new Date(activeEndDate.getTime());
    }

    public boolean isActive() {
        return DateUtil.isActive(activeStartDate, activeEndDate, true) && 'Y'!=getArchived();
    }

    public String getDisplayTemplate() {
        return displayTemplate;
    }

    public void setDisplayTemplate(String displayTemplate) {
        this.displayTemplate = displayTemplate;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    @Nonnull
    public List<FeaturedProduct> getFeaturedProducts() {
        return null;
    }

    public void setFeaturedProducts(@Nonnull List<FeaturedProduct> featuredProducts) {

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

    public Category getParentCategory() {
        return parent;
    }

    public void setParentCategory(Category category) {
        this.parent = category;
    }

    public Store getStore() {
        return merchantStore;
    }

    public void setStore(Store merchantStore) {
        this.merchantStore = merchantStore;
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

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (url == null ? 0 : url.hashCode());
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
        CategoryImpl other = (CategoryImpl) obj;

        if (id != null && other.id != null) {
            return id.equals(other.id);
        }

        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (url == null) {
            if (other.url != null) {
                return false;
            }
        } else if (!url.equals(other.url)) {
            return false;
        }
        return true;
    }


    public String getTaxCode() {
        return this.taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public AuditSection getAuditSection() {
        return auditSection;
    }

    public void setAuditSection(AuditSection auditSection) {
        this.auditSection = auditSection;
    }


}
