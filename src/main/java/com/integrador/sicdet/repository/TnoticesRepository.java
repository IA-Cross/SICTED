package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tnotices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TnoticesRepository extends JpaRepository<Tnotices,Integer>{
}
