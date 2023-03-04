package com.example.managenment.repository;

import com.example.managenment.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("SELECT u from Role  u where u.name= ?1")
    Role findByName(String name);
    @Query(value = "SELECT u From Role u where u.id=?1")
    List<Role> findId(Integer id);
    @Modifying
    @Query(value = "DELETE p FROM privilege_role p INNER JOIN privilege r ON p.privilege_id= r.id WHERE p.role_id=?1 and r.name=?2 ",nativeQuery = true)
    public void deleteRoleId(Integer id,String name);
}
