package com.kaidash.mobileapplicationsservice.entity;

import java.util.List;

public class ApplicationComparisonEntity {

    private List<Integer> id = null;

    public ApplicationComparisonEntity(){}

    public ApplicationComparisonEntity(List<Integer> id) {
        this.id = id;
    }

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

}
