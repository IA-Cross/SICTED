package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tcorrection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TcorrectionRepository extends JpaRepository<Tcorrection,Integer>{
}
