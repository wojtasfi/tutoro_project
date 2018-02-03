package com.tutoro.web;

/**
 * Created by wojci on 4/28/2017.
 */
public class DuplicateUserError {
    private boolean error;
    private final String errorMessage = "User with this login already exists. Please choose another one.";

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
