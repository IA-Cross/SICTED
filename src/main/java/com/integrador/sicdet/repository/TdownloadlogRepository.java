package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tdownloadlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TdownloadlogRepository extends JpaRepository<Tdownloadlog,Integer>{
}
