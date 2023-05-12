package com.futebol.escalacao.service;

import com.futebol.escalacao.dto.PlayerWebName;
import com.futebol.escalacao.entity.PositionEntity;
import com.futebol.escalacao.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public List<PlayerWebName> listPlayersByPosition(PositionEntity position ){
        return playerRepository.findByPosition(position);
    }
}
