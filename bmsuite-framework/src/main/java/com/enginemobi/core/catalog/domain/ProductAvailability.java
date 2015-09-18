package com.enginemobi.core.catalog.domain;

import com.enginemobi.core.constants.SchemaConstant;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Embeddable
public class ProductAvailability implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotNull
    @Column(name="QUANTITY")
    private Integer productQuantity = 0;

    @Column(name="REGION")
    private String region = SchemaConstant.ALL_REGIONS;

    @Column(name="REGION_VARIANT")
    private String regionVariant;

    @Column(name="FREE_SHIPPING")
    private boolean productIsAlwaysFreeShipping;

    @Column(name="QUANTITY_ORD_MIN")
    private Integer productQuantityOrderMin = 0;

    @Column(name="QUANTITY_ORD_MAX")
    private Integer productQuantityOrderMax = 0;



    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionVariant() {
        return regionVariant;
    }

    public void setRegionVariant(String regionVariant) {
        this.regionVariant = regionVariant;
    }

    public boolean getProductIsAlwaysFreeShipping() {
        return productIsAlwaysFreeShipping;
    }

    public void setProductIsAlwaysFreeShipping(boolean productIsAlwaysFreeShipping) {
        this.productIsAlwaysFreeShipping = productIsAlwaysFreeShipping;
    }

    public Integer getProductQuantityOrderMin() {
        return productQuantityOrderMin;
    }

    public void setProductQuantityOrderMin(Integer productQuantityOrderMin) {
        this.productQuantityOrderMin = productQuantityOrderMin;
    }

    public Integer getProductQuantityOrderMax() {
        return productQuantityOrderMax;
    }

    public void setProductQuantityOrderMax(Integer productQuantityOrderMax) {
        this.productQuantityOrderMax = productQuantityOrderMax;
    }



}
