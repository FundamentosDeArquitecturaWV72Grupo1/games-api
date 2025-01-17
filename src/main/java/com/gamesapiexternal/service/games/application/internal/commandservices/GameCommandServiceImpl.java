package com.gamesapiexternal.service.games.application.internal.commandservices;

import com.gamesapiexternal.service.games.domain.model.aggregates.Game;
import com.gamesapiexternal.service.games.domain.model.commands.RegisterGameCommand;
import com.gamesapiexternal.service.games.domain.model.events.GameCreatedEvent;
import com.gamesapiexternal.service.games.domain.services.GameCommandService;
import com.gamesapiexternal.service.games.infrastructure.persistance.jpa.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class GameCommandServiceImpl implements GameCommandService {
    private final GameRepository gameRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public GameCommandServiceImpl(GameRepository gameRepository, ApplicationEventPublisher eventPublisher) {
        this.gameRepository = gameRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Game handle(RegisterGameCommand command) {
        Game game = new Game(
                command.title(),
                command.description(),
                command.embedCode(),
                command.image(),
                command.rules(),
                command.topic()
        );
        Game savedGame = gameRepository.save(game);
        eventPublisher.publishEvent(new GameCreatedEvent(this, savedGame));
        return savedGame;
    }
}
