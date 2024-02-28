package com.aoe.restapi.utility.Status;

import com.aoe.restapi.utility.LogUtility.LogUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public class OperationStatusError extends OperationStatus {
    private String error;
    private String message;
    private List<String> warnings;

    // constructor

    public OperationStatusError(HttpStatus status) {
        super(status, false);
        message = LogUtility.getMessage();
    }

    public OperationStatusError(HttpStatus status, Exception e) {
        super(status, false);
        error = e.toString();
        message = LogUtility.getMessage();
    }

    public OperationStatusError(HttpStatus status, String customMessage) {
        super(status, false);
        message = customMessage;
    }

    public OperationStatusError(HttpStatus status, List<String> warnings) {
        super(status, false);
        this.warnings = warnings;
        message = LogUtility.getMessage();
    }

    // methods
    @Override
    public ResponseEntity<HashMap<String, Object>> getResponseEntity() {
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("status", this.getStatus());
        responseData.put("state", this.getState());
        responseData.put("message", message);
        if (warnings != null && !warnings.isEmpty()) {
            responseData.put("warnings", warnings);
        }

        return new ResponseEntity<HashMap<String, Object>>(responseData, this.getStatus());
    }

    //getter setter
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // to string
    @Override
    public String toString() {
        return super.toString() + " -> " + "OperationStatusError{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
