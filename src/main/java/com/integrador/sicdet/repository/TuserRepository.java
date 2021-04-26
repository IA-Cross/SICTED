package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuserRepository extends JpaRepository<Tuser,Integer>{
    boolean existsByEmail(String email);
    Tuser findByEmail(String email);
    Tuser findUserById(int id);
    List<Tuser> searchByName(@Param("name") String name);
}
