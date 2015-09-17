package com.enginemobi.core.common.domain;

/**
 * Created by pkcool on 9/17/15.
 */
public interface Auditable {
    AuditSection getAuditSection();

    void setAuditSection(AuditSection audit);
}
