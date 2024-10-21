package com.bhan.ked.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="society_member")
public class SocietyMemberEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "flat_no")
    private String flatNo;

    @Column(name = "primary_phone")
    private String primaryMobileNo;

    @Column(name = "alternate_phone")
    private String alternateMobileNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getPrimaryMobileNo() {
        return primaryMobileNo;
    }

    public void setPrimaryMobileNo(String primaryMobileNo) {
        this.primaryMobileNo = primaryMobileNo;
    }

    public String getAlternateMobileNo() {
        return alternateMobileNo;
    }

    public void setAlternateMobileNo(String alternateMobileNo) {
        this.alternateMobileNo = alternateMobileNo;
    }
}
