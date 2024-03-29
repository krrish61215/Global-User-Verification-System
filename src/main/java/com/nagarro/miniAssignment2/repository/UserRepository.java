package com.nagarro.miniAssignment2.repository;


import com.nagarro.miniAssignment2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
