package com.brand.dto;

import java.io.Serializable;
import java.util.Date;

public class DeviceDto implements Serializable {

    private static final long serialVersionUID = 8716191160607935765L;

    private Long identicalNumber;

    private String vendor;

    private Date created;

    private StatusDevice status;

    public Long getIdenticalNumber() {
        return identicalNumber;
    }

    public void setIdenticalNumber(Long identicalNumber) {
        this.identicalNumber = identicalNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public StatusDevice getStatus() {
        return status;
    }

    public void setStatus(StatusDevice status) {
        this.status = status;
    }

}
