package com.enginemobi.core.generic.dao;

import com.enginemobi.core.common.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchableDao<T> {
    Page<T> search(SearchCriteria searchCriteria, Pageable pageable);
}
