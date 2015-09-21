package com.enginemobi.core.profile.domain;

import com.enginemobi.core.generic.domain.BmSuiteEntity;

public interface Role extends BmSuiteEntity<Long, Role>{

    Long getId();

    void setId(Long id);

    String getRoleName();

    void setRoleName(String roleName);

}
