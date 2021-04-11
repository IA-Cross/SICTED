package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Ttesista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TtesistaRepository extends JpaRepository<Ttesista,Integer>{
    Ttesista findByName(@Param("name") String name);
}
