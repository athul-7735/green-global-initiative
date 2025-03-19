package com.atu.green_global_initiative_api.repository;

import com.atu.green_global_initiative_api.model.dao.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link ContactUs} entities.
 * <p>This interface extends {@link JpaRepository}, providing basic CRUD operations on the "ContactUs" table.
 * It allows for operations such as saving, deleting, and retrieving contact inquiries submitted by users.</p>
 *
 * <p>As this interface extends {@link JpaRepository}, no additional query methods are necessary unless custom queries
 * are needed. Standard operations are provided out of the box.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * ContactUs contact = contactUsRepo.findById(1).orElse(null);
 * </pre>
 *
 * @author Name Here
 * @since 2025-03-19
 */

@Repository
public interface ContactUsRepo extends JpaRepository<ContactUs, Integer> {

}
