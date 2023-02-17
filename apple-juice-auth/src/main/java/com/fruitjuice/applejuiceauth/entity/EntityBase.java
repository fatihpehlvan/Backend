package com.fruitjuice.applejuiceauth.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@Data
public class EntityBase {
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @PrePersist
    public void onPersist() {
        this.setCreateDate(new Date());
        this.setUpdateDate(new Date());
    }

    @PreUpdate
    public void onPreUpdate() {
        this.setUpdateDate(new Date());
    }

}
