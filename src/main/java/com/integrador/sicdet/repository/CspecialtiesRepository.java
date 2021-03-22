package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Cspecialties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CspecialtiesRepository extends JpaRepository<Cspecialties,Integer>{
}
