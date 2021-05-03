package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.TcorrectionDates;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TcorrectionDatesRepository extends JpaRepository<TcorrectionDates,Integer>{
}
