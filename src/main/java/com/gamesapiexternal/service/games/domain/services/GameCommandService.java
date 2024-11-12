package com.gamesapiexternal.service.games.domain.services;

import com.gamesapiexternal.service.games.domain.model.aggregates.Game;
import com.gamesapiexternal.service.games.domain.model.commands.RegisterGameCommand;

public interface GameCommandService {
    Game handle(RegisterGameCommand command);
}
