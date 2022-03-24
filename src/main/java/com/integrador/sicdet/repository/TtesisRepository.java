package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Ttesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TtesisRepository extends JpaRepository<Ttesis,Integer>{
    List<Ttesis> findAllActive();
    List<Ttesis> searchTesis(@Param("title")  String title, @Param("advisor")  int advisor);
    List<Ttesis> findByIdActive(@Param("id") int id);
    List<Ttesis> findByAdvisor(@Param("id") int id);
    List<Ttesis> findByTitle(@Param("title") String title);
    Ttesis findById(@Param("id") int id);
    Ttesis searchTesisByTitle(@Param("titulo") String titulo);
    List<Ttesis> findByIdAsesor(@Param("id") int id);
}
