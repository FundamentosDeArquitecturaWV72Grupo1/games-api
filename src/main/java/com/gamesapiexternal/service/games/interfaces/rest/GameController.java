package com.gamesapiexternal.service.games.interfaces.rest;

import com.gamesapiexternal.service.games.application.internal.commandservices.GameCommandServiceImpl;
import com.gamesapiexternal.service.games.application.internal.queryservices.GameQueryServiceImpl;
import com.gamesapiexternal.service.games.domain.model.aggregates.Game;
import com.gamesapiexternal.service.games.domain.model.commands.RegisterGameCommand;
import com.gamesapiexternal.service.games.domain.model.queries.GetAllGamesQuery;
import com.gamesapiexternal.service.games.domain.model.queries.GetGameByIdQuery;
import com.gamesapiexternal.service.games.interfaces.rest.resources.GameResource;
import com.gamesapiexternal.service.games.interfaces.rest.transform.GameResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/games-external", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    private final GameCommandServiceImpl gameCommandService;
    private final GameQueryServiceImpl gameQueryService;

    @Autowired
    public GameController(GameCommandServiceImpl gameCommandService, GameQueryServiceImpl gameQueryService) {
        this.gameCommandService = gameCommandService;
        this.gameQueryService = gameQueryService;
    }

    @PostMapping("/create")
    public ResponseEntity<GameResource> createGame(@RequestBody RegisterGameCommand command) {
        Game createdGame = gameCommandService.handle(command);
        GameResource resource = GameResourceAssembler.toResource(createdGame);
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameResource>> getAllGames() {
        List<Game> games = gameQueryService.handle(new GetAllGamesQuery());
        List<GameResource> resources = games.stream()
                .map(GameResourceAssembler::toResource)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResource> getGameById(@PathVariable Long id) {
        Optional<Game> game = gameQueryService.handle(new GetGameByIdQuery(id));
        return game.map(g -> ResponseEntity.ok(GameResourceAssembler.toResource(g)))
                .orElse(ResponseEntity.notFound().build());
    }
}
