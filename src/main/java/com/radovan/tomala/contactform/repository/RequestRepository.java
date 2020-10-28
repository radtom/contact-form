package com.radovan.tomala.contactform.repository;

import com.radovan.tomala.contactform.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository providing methods for database access for Request Entity
 */
public interface RequestRepository extends JpaRepository<Request, Long> {
}
