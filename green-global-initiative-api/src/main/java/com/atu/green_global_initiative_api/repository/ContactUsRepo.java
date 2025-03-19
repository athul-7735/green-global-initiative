package com.atu.green_global_initiative_api.repository;

import com.atu.green_global_initiative_api.model.dao.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for handling CRUD operations related to the {@link ContactUs} entity.
 * This interface extends {@link JpaRepository} and provides methods to interact with the database
 * for the {@link ContactUs} model. It provides built-in methods for common database operations such
 * as saving, deleting, and finding records, along with the ability to define custom queries.
 * <p>
 * The {@link ContactUs} entity represents a record of user-submitted contact information or queries.
 * The primary key of the entity is of type {@code Integer}.
 * </p>
 *
 * @see JpaRepository
 * @see ContactUs
 */
@Repository
public interface ContactUsRepo extends JpaRepository<ContactUs, Integer> {

}
