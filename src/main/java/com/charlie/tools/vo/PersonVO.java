package com.charlie.tools.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by charlie on 16/07/2017.
 */
public class PersonVO implements Serializable{
    private BigDecimal id;
    private String name;
    private Date dob;

    public PersonVO() {
        super();
    }

    public PersonVO(String name) {
        this.name = name;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }



    @Override
    public String toString() {
        return "PersonVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
