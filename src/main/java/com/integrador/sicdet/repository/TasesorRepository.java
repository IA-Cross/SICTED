package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tasesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasesorRepository extends JpaRepository<Tasesor,Integer>{
}
