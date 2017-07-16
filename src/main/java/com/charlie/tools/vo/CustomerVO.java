package com.charlie.tools.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by charlie on 15/07/2017.
 */
public class CustomerVO implements Serializable{

    private BigDecimal id;
    private String name;

    public CustomerVO() {
    }

    public CustomerVO(BigDecimal id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        return "CustomerVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
