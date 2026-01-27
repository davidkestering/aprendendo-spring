package com.javanauta.aprendendospring.infrastructure.repository;

import com.javanauta.aprendendospring.infrastructure.entity.TipoContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipoContatoRepository extends JpaRepository<TipoContato, UUID> {

    boolean existsByTipoIgnoreCase(String tipo);
}
