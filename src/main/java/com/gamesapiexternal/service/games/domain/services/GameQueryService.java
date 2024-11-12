package com.gamesapiexternal.service.games.domain.services;

import com.gamesapiexternal.service.games.domain.model.aggregates.Game;
import com.gamesapiexternal.service.games.domain.model.queries.GetAllGamesQuery;
import com.gamesapiexternal.service.games.domain.model.queries.GetGameByIdQuery;
import com.gamesapiexternal.service.games.domain.model.queries.GetGameByTitleQuery;

import java.util.List;
import java.util.Optional;

public interface GameQueryService {
    List<Game> handle(GetAllGamesQuery query);
    Optional<Game> handle(GetGameByIdQuery query);
    Optional<Game> handle(GetGameByTitleQuery query);
}
