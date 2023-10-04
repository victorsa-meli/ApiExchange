package com.mercadolivre.conversor.core.repository;


import com.mercadolivre.conversor.core.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultasRepository extends JpaRepository<Consulta, Long> {
}
