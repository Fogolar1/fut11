package com.futebol.escalacao.repository;

import com.futebol.escalacao.dto.PlayerWebName;
import com.futebol.escalacao.entity.PlayerEntity;
import com.futebol.escalacao.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    List<PlayerWebName> findByPosition(PositionEntity position);
}
