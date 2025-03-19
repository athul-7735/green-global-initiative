package com.atu.green_global_initiative_api.model.dao;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Primary;

@Entity
@Data
@Table(name="contact_us")
public class ContactUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="query_id", unique = true, nullable = false)
    private int queryId;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="phone", nullable = false)
    private String phone;
    @Column(name="message", nullable = false)
    private String message;
}
