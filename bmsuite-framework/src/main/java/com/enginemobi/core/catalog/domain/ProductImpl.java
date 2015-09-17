package com.enginemobi.core.catalog.domain;

import com.enginemobi.core.common.domain.audit.AuditSection;
import com.enginemobi.core.common.domain.audit.Auditable;
import com.enginemobi.core.generic.domain.BmSuiteBaseEntityImpl;

/**
 * Created by pkcool on 9/17/15.
 */
public abstract class ProductImpl extends BmSuiteBaseEntityImpl<Long, Product> implements Product, Auditable {
    public AuditSection getAuditSection() {
        return null;
    }

    public void setAuditSection(AuditSection audit) {

    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
