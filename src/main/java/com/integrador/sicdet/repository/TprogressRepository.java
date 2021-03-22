package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tprogress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TprogressRepository extends JpaRepository<Tprogress,Integer>{
}
