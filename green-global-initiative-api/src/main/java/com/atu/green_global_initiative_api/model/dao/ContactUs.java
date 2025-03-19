package com.atu.green_global_initiative_api.model.dao;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Primary;
/**
 * This class represents the entity for the "contact_us" table in the Green Global Initiative API database.
 * It stores the details of a query submitted by a user through the "Contact Us" form, including the user's contact information
 * and the message they have submitted.
 */
@Entity
@Data
@Table(name="contact_us")
public class ContactUs {
    /**
     * The unique identifier for the contact query.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="query_id", unique = true, nullable = false)
    private int queryId;
    /**
     * The name of the person submitting the query.
     */
    @Column(name="name",nullable = false)
    private String name;
    /**
     * The email address of the person submitting the query.
     */
    @Column(name="email", nullable = false)
    private String email;
    /**
     * The phone number of the person submitting the query.
     */
    @Column(name="phone", nullable = false)
    private String phone;
    /**
     * The message or inquiry submitted by the person.
     */
    @Column(name="message", nullable = false)
    private String message;
}
