package com.cloudsolv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cloudsolv.model.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
