package com.aia.test.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseModelPaginate {
    @JsonProperty("message")
    private String responseMessage;

    @JsonProperty("status")
    private int status;

    @JsonProperty("data")
    private Object data;

    @JsonProperty("current_page")
    private int currentPage;

    @JsonProperty("total_page")
    private int total_page;


    public ResponseModelPaginate(int status, String responseMessage, Object data, int currentPage, int total_page) {
        this.status = status;
        this.responseMessage = responseMessage;
        this.data = data;
        this.currentPage = currentPage;
        this.total_page = total_page;
    }
}
