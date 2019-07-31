package com.trl.users.exceptions;

public class UserNotHaveBankDataException extends Exception {

    public UserNotHaveBankDataException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public UserNotHaveBankDataException(String message) {
        super(message);
    }

}
