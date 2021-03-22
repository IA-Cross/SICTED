package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tcatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TcatalogRepository extends JpaRepository<Tcatalog,Integer>{
}
