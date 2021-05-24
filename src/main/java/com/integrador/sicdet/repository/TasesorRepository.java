package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tasesor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasesorRepository extends JpaRepository<Tasesor,Integer>{
    List<Tasesor> findAllActive(Pageable pageable);
}
