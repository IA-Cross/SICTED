package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Ttesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TtesisRepository extends JpaRepository<Ttesis,Integer>{
}
