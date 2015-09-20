package com.enginemobi.core.reference.country.dao;

import com.enginemobi.core.generic.dao.BmSuiteEntityDaoImpl;
import com.enginemobi.core.reference.country.domain.Country;
import org.springframework.stereotype.Repository;


@Repository
public class CountryDaoImpl extends BmSuiteEntityDaoImpl<Integer, Country> implements CountryDao {

}
