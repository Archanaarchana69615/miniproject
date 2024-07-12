package com.adminpanel.miniproject.repository;

import com.adminpanel.miniproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);
    User findById(int id);

    List<User> findAllByRole(String role);

    List<User> findAllByRoleAndNameStartingWithIgnoreCase(String role,String name);



    User deleteById(int id);
}
