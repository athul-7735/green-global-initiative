package com.atu.green_global_initiative_api.model.dao.request;

/**
 * Request DTO for creating a new query in the Green Global Initiative API.
 * This class contains the necessary information to submit a query.
 */
public class QueryCreateRequest {

    /** Query ID */
    private int queryId;

    /** Name of the person submitting the query */
    private String name;

    /** Email address of the person submitting the query */
    private String email;

    /** Phone number of the person submitting the query */
    private String phone;

    /** The message or content of the query */
    public String message;

    /**
     * Gets the query ID.
     *
     * @return the query ID
     */
    public int getQueryId() {
        return queryId;
    }

    /**
     * Sets the query ID.
     *
     * @param queryId the query ID to set
     */
    public void setQueryId(int queryId) {
        this.queryId = queryId;
    }

    /**
     * Gets the name of the person submitting the query.
     *
     * @return the name of the person submitting the query
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person submitting the query.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the person submitting the query.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the person submitting the query.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the person submitting the query.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the person submitting the query.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the message or content of the query.
     *
     * @return the query message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message or content of the query.
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
