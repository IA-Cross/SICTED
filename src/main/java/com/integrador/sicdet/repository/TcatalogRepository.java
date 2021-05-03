package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tcatalog;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TcatalogRepository extends JpaRepository<Tcatalog,Integer>{
	List<Tcatalog> findDegree();
	List<Tcatalog> findCatalogByCode(String code);
}
