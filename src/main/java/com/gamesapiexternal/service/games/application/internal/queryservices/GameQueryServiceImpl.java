package com.gamesapiexternal.service.games.application.internal.queryservices;

import com.gamesapiexternal.service.games.domain.model.aggregates.Game;
import com.gamesapiexternal.service.games.domain.model.queries.GetAllGamesQuery;
import com.gamesapiexternal.service.games.domain.model.queries.GetGameByIdQuery;
import com.gamesapiexternal.service.games.domain.model.queries.GetGameByTitleQuery;
import com.gamesapiexternal.service.games.domain.services.GameQueryService;
import com.gamesapiexternal.service.games.infrastructure.persistance.jpa.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameQueryServiceImpl implements GameQueryService {
    private final GameRepository gameRepository;

    @Autowired
    public GameQueryServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> handle(GetAllGamesQuery query) {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> handle(GetGameByIdQuery query) {
        return gameRepository.findById(query.id());
    }

    @Override
    public Optional<Game> handle(GetGameByTitleQuery query) {
        return gameRepository.findByTitle(query.title());
    }
}
