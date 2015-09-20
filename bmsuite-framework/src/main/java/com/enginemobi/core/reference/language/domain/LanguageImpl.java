package com.enginemobi.core.reference.language.domain;

import com.enginemobi.core.common.domain.audit.AuditListener;
import com.enginemobi.core.common.domain.audit.AuditSection;
import com.enginemobi.core.common.domain.audit.Auditable;
import com.enginemobi.core.constants.SchemaConstant;
import com.enginemobi.core.generic.domain.BmSuiteEntityImpl;
import com.enginemobi.core.store.domain.Store;
import com.enginemobi.core.store.domain.StoreImpl;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "LANGUAGE", schema= SchemaConstant.BMSUITEDB_SCHEMA)
@Cacheable
public class LanguageImpl extends BmSuiteEntityImpl<Integer, Language> implements Auditable, Language {
    private static final long serialVersionUID = -7676627812941330669L;



    @Id
    @Column(name="LANGUAGE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "LANG_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Integer id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Column(name="CODE", nullable = false)
    private String code;

    @Column(name="SORT_ORDER")
    private Integer sortOrder;

    @SuppressWarnings("unused")
    @OneToMany(mappedBy = "defaultLanguage", targetEntity = StoreImpl.class)
    private List<Store> storesDefaultLanguage;

    @SuppressWarnings("unused")
    @ManyToMany(mappedBy = "languages", targetEntity = StoreImpl.class)
    private List<Store> stores = new ArrayList<Store>();

    public LanguageImpl() {
    }

    public LanguageImpl(String code) {
        this.setCode(code);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public AuditSection getAuditSection() {
        return auditSection;
    }

    public void setAuditSection(AuditSection auditSection) {
        this.auditSection = auditSection;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof Language)) {
            return false;
        } else {
            Language language = (Language) obj;
            return (this.id == language.getId());
        }
    }
}
