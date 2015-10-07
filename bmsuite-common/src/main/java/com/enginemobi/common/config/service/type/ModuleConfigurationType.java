package com.enginemobi.common.config.service.type;

import com.enginemobi.common.BmSuiteEnumerationType;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;


public class ModuleConfigurationType implements BmSuiteEnumerationType, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Map<String, ModuleConfigurationType> TYPES = new LinkedHashMap<String, ModuleConfigurationType>();

    public static final ModuleConfigurationType FULFILLMENT_PRICING = new ModuleConfigurationType("FULFILLMENT_PRICING", "Fulfillment Pricing Module");
    public static final ModuleConfigurationType TAX_CALCULATION = new ModuleConfigurationType("TAX_CALCULATION", "Tax Calculation Module");
    public static final ModuleConfigurationType ADDRESS_VERIFICATION = new ModuleConfigurationType("ADDRESS_VERIFICATION", "Address Verification Module");
    public static final ModuleConfigurationType PAYMENT_PROCESSOR = new ModuleConfigurationType("PAYMENT_PROCESSOR", "Payment Processor Module");
    public static final ModuleConfigurationType CDN_PROVIDER = new ModuleConfigurationType("CDN_PROVIDER", "Content Delivery Network Module");
    public static final ModuleConfigurationType SITE_MAP = new ModuleConfigurationType("SITE_MAP", "Site Map Generator");

    public static ModuleConfigurationType getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String friendlyType;

    public ModuleConfigurationType() {
        //do nothing
    }

    public ModuleConfigurationType(final String type, final String friendlyType) {
        this.friendlyType = friendlyType;
        setType(type);
    }

    public String getType() {
        return type;
    }

    public String getFriendlyType() {
        return friendlyType;
    }

    private void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        ModuleConfigurationType other = (ModuleConfigurationType) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
