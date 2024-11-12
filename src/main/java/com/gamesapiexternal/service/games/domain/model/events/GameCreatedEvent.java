package com.gamesapiexternal.service.games.domain.model.events;

import com.gamesapiexternal.service.games.domain.model.aggregates.Game;
import org.springframework.context.ApplicationEvent;

public class GameCreatedEvent extends ApplicationEvent {
    private final Game game;

    public GameCreatedEvent(Object source, Game game) {
        super(source);
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
