package com.kaidash.mobileapplicationsservice.controller;

/**
 * Endpoints for ApplicationRestController that point the route to certain method or class.
 */
public final class EndPoints {

    private EndPoints(){}

    public static final String API = "/api";
    public static final String APPLICATIONS = "/applications";
    public static final String APPLICATION_BY_ID = "/applications/{applicationId}";
    public static final String APPLICATIONS_COMPARISON = "/applications/compare";
    public static final String APPLICATIONS_CONTENT_RATE_STATS = "/applications/content-rate-stats";
}
