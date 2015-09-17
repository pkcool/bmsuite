package com.enginemobi.core.common.domain.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by pkcool on 9/17/15.
 */
public class AuditListener {

    @PrePersist
    @PreUpdate
    public void onSaveOrUpdate(Object o) {
        if (o instanceof Auditable) {
            Auditable audit = (Auditable) o;
            AuditSection auditSection = audit.getAuditSection();

            auditSection.setDateModified(new Date());
            if (auditSection.getDateCreated() == null) {
                auditSection.setDateCreated(new Date());
            }
            audit.setAuditSection(auditSection);
        }
    }
}
