package com.futebol.escalacao.repository;

import com.futebol.escalacao.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
}
