package com.kaidash.mobileapplicationsservice.entity;

import java.util.List;

public class ApplicationIdsComparisonRequest {

    private List<Integer> id = null;

    public ApplicationIdsComparisonRequest(){}

    public ApplicationIdsComparisonRequest(List<Integer> id) {
        this.id = id;
    }

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

}
