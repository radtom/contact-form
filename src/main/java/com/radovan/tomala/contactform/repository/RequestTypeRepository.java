package com.radovan.tomala.contactform.repository;

import com.radovan.tomala.contactform.entity.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository providing methods for database access for RequestType Entity
 */
public interface RequestTypeRepository extends JpaRepository<RequestType, Long> {
}
