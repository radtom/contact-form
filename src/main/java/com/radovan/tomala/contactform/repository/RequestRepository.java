package com.radovan.tomala.contactform.repository;

import com.radovan.tomala.contactform.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
