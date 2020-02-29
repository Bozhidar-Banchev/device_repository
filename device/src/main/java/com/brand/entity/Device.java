package com.brand.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.brand.dto.StatusDevice;

@Entity
@Table(name = "devices")
public class Device implements Serializable {

    private static final long serialVersionUID = 4576459650440490570L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "UID_number", nullable = false)
    private Long identicalNumber;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "created")
    @CreationTimestamp
    private Date created;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusDevice status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_device_id", referencedColumnName = "id")
    private MasterDevice parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public MasterDevice getParent() {
        return parent;
    }

    public void setParent(MasterDevice parent) {
        this.parent = parent;
    }

}
