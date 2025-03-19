package com.atu.green_global_initiative_api.model.dao.request;
/**
 * This class represents the request body for creating a query in the Green Global Initiative API.
 * It contains the details provided by the user for submitting an inquiry or request, such as their contact information and message.
 */
public class QueryCreateRequest {
    /**
     * The unique identifier for the query.
     */
    private int queryId;
    /**
     * The name of the person submitting the query.
     */
    private String name;
    /**
     * The email address of the person submitting the query.
     */
    private String email;
    /**
     * The phone number of the person submitting the query.
     */
    private String phone;
    /**
     * The message or inquiry submitted by the person.
     */
    public String message;
    /**
     * Gets the unique identifier for the query.
     *
     * @return the query ID
     */
    public int getQueryId() {
        return queryId;
    }
    /**
     * Sets the unique identifier for the query.
     *
     * @param queryId the new query ID
     */
    public void setQueryId(int queryId) {
        this.queryId = queryId;
    }
    /**
     * Gets the name of the person submitting the query.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the person submitting the query.
     *
     * @param name the new name of the person
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the email address of the person submitting the query.
     *
     * @return the email address of the person
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the email address of the person submitting the query.
     *
     * @param email the new email address of the person
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets the phone number of the person submitting the query.
     *
     * @return the phone number of the person
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Sets the phone number of the person submitting the query.
     *
     * @param phone the new phone number of the person
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * Gets the message or inquiry submitted by the person.
     *
     * @return the message or inquiry
     */
    public String getMessage() {
        return message;
    }
    /**
     * Sets the message or inquiry submitted by the person.
     *
     * @param message the new message or inquiry
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
