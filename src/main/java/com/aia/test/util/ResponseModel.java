package com.aia.test.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseModel {
    @JsonProperty("message")
    private String responseMessage;

    @JsonProperty("status")
    private int status;

    @JsonProperty("data")
    private Object data;


    public ResponseModel(int status, String responseMessage, Object data) {
        this.status = status;
        this.responseMessage = responseMessage;
        this.data = data;

    }
}
