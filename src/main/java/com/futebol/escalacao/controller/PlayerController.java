package com.futebol.escalacao.controller;

import com.futebol.escalacao.dto.PlayerWebName;
import com.futebol.escalacao.entity.PositionEntity;
import com.futebol.escalacao.repository.PositionRepository;
import com.futebol.escalacao.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final PositionRepository positionRepository;

    @GetMapping("/list")
    public ResponseEntity<List<PlayerWebName>> listPlayers(@RequestParam("position") Long positionId ){
        PositionEntity position = null;
        if(Objects.nonNull(positionId)){
            position = positionRepository.findById(positionId).orElse(null);
        }
        List<PlayerWebName> players = playerService.listPlayersByPosition(position);

        return ResponseEntity.ok().body(players);
    }
}
