package com.gamesapiexternal.service.games.interfaces.rest.transform;

import com.gamesapiexternal.service.games.domain.model.aggregates.Game;
import com.gamesapiexternal.service.games.interfaces.rest.resources.GameResource;

public class GameResourceAssembler {
    public static GameResource toResource(Game game) {
        return new GameResource(
                game.getId(),
                game.getTitle(),
                game.getDescription(),
                game.getEmbedCode(),
                game.getImage(),
                game.getRules(),
                game.getTopic()
        );
    }
}
