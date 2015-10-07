package com.enginemobi.common.config.domain;

import com.enginemobi.common.config.service.type.ModuleConfigurationType;

import java.io.Serializable;
import java.util.Date;

public interface ModuleConfiguration extends Serializable {

    public Long getId();

    public void setId(Long id);

    public String getModuleName();

    public void setModuleName(String name);

    public void setActiveStartDate(Date startDate);

    public Date getActiveStartDate();

    public void setActiveEndDate(Date startDate);

    public Date getActiveEndDate();

    public void setIsDefault(Boolean isDefault);

    public Boolean getIsDefault();

    public boolean isDefault();

    public void setPriority(Integer priority);

    public Integer getPriority();

    public ModuleConfigurationType getModuleConfigurationType();

    //public void setAuditable(Auditable auditable);

    //public Auditable getAuditable();

}
