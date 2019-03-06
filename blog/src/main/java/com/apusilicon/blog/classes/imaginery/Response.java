package com.apusilicon.blog.classes.imaginery;

import com.google.gson.Gson;

/**
 *
 * @author earlcameron
 */
public class Response {

    String success, message, error, status, payload;

    public Response() {
    }

    @Override
    public String toString() {
        return "Response{" + "success=" + success + ", message=" + message + ", error=" + error + ", status=" + status + ", payload=" + payload + '}';
    }

    public String send() {
        return new Gson().toJson(this);
    }

    public String getSuccess() {
        return success;
    }

    public Response setSuccess(String success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getError() {
        return error;
    }

    public Response setError(String error) {
        this.error = error;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Response setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getPayload() {
        return payload;
    }

    public Response setPayload(String payload) {
        this.payload = payload;
        return this;
    }

}
