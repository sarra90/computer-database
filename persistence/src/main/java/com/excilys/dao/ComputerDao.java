package com.excilys.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.excilys.model.Computer;
@Repository
public interface ComputerDao extends JpaRepository<Computer, Long>{

    @Query("SELECT c FROM Computer c WHERE c.name like ?1%")
	List<Computer> findByName(String name);
	
    @Query("SELECT count (*) FROM Computer c WHERE c.name like ?1%")
    long countByName(String name);
    
    Page<Computer> findAll(Pageable pageRequest);
}
