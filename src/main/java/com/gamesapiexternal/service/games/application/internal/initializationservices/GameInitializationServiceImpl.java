package com.gamesapiexternal.service.games.application.internal.initializationservices;

import com.gamesapiexternal.service.games.application.internal.commandservices.GameCommandServiceImpl;
import com.gamesapiexternal.service.games.application.internal.queryservices.GameQueryServiceImpl;
import com.gamesapiexternal.service.games.domain.model.aggregates.Game;
import com.gamesapiexternal.service.games.domain.model.commands.RegisterGameCommand;
import com.gamesapiexternal.service.games.domain.model.queries.GetGameByTitleQuery;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameInitializationServiceImpl {
    private final GameCommandServiceImpl gameCommandService;
    private final GameQueryServiceImpl gameQueryService;

    @Autowired
    public GameInitializationServiceImpl(GameCommandServiceImpl gameCommandService, GameQueryServiceImpl gameQueryService) {
        this.gameCommandService = gameCommandService;
        this.gameQueryService = gameQueryService;
    }

    @PostConstruct
    public void init() {
        List<RegisterGameCommand> gamesToRegister = List.of(
                new RegisterGameCommand("Fracciones 1er grado",
                        "Relacionar Columnas",
                        "<iframe allow=\"fullscreen; autoplay;\" allowfullscreen width=\"795\" height=\"690\" frameborder=\"0\" src=\"https://es.educaplay.com/juego/12779470-potenciacion.html\"></iframe>",
                        "https://is4-ssl.mzstatic.com/image/thumb/Purple123/v4/4f/41/9d/4f419d1b-8fca-2816-1f7f-d2ecc931ea3e/source/512x512bb.jpg",
                        "Reglas del juego",
                        "Matemáticas de 5to grado Educación secundaria"),
                new RegisterGameCommand("Aritmética Básica",
                        "Suma y Resta de Números",
                        "<iframe allow=\"fullscreen; autoplay;\" allowfullscreen width=\"795\" height=\"690\" frameborder=\"0\" src=\"https://es.educaplay.com/juego/8005181-fracciones_1er_grado.html\"></iframe>",
                        "https://kidsplaypark.com/wp-content/uploads/thumbs/custom/C/Crazy-math-icon.png",
                        "Reglas del juego",
                        "Matemáticas de 5to grado Educación secundaria"),
                new RegisterGameCommand("Geometría Avanzada",
                        "Identificar Figuras",
                        "<iframe allow=\"fullscreen; autoplay;\" allowfullscreen width=\"795\" height=\"690\" frameborder=\"0\" src=\"https://es.educaplay.com/juego/7096706-identifica_numeros_pares.html\"></iframe>",
                        "https://is4-ssl.mzstatic.com/image/thumb/Purple125/v4/4f/c2/85/4fc28501-8fac-ec68-0022-ece670723216/source/512x512bb.jpg",
                        "Reglas del juego",
                        "Matemáticas de 5to grado Educación secundaria"),
                new RegisterGameCommand("Expresiones Algebraicas",
                        "Relacionar Columnas",
                        "<iframe allow=\"fullscreen; autoplay;\" allowfullscreen width=\"795\" height=\"690\" frameborder=\"0\" src=\"https://es.educaplay.com/juego/12779470-potenciacion.html\"></iframe>",
                        "https://play-lh.googleusercontent.com/5X5ygfE4xswowfdy7Yt_WRy1aJQkjMUAUMn3PZjNN3XECwFyUkljjZpBBftIHS77pg",
                        "Reglas del juego",
                        "Matemáticas de 5to grado Educación secundaria"),
                new RegisterGameCommand("Funciones trigonometricas",
                        "Suma y Resta de Números",
                        "<iframe allow=\"fullscreen; autoplay;\" allowfullscreen width=\"795\" height=\"690\" frameborder=\"0\" src=\"https://es.educaplay.com/juego/8005181-fracciones_1er_grado.html\"></iframe>",
                        "https://is5-ssl.mzstatic.com/image/thumb/Purple115/v4/b3/36/f9/b336f98e-f184-cd03-fece-1be99e0735c6/source/512x512bb.jpg",
                        "Reglas del juego",
                        "Matemáticas de 5to grado Educación secundaria"),
                new RegisterGameCommand("Probabilidad y Estadística",
                        "Identificar Figuras",
                        "<iframe allow=\"fullscreen; autoplay;\" allowfullscreen width=\"795\" height=\"690\" frameborder=\"0\" src=\"https://es.educaplay.com/juego/7096706-identifica_numeros_pares.html\"></iframe>",
                        "https://www.thegreatapps.com/application/upload/Apps/2020/09/kids-math-app-192.png",
                        "Reglas del juego",
                        "Matemáticas de 5to grado Educación secundaria")
        );

        for (RegisterGameCommand command : gamesToRegister) {
            Optional<Game> existingGame = gameQueryService.handle(new GetGameByTitleQuery(command.title()));
            if (existingGame.isEmpty()) {
                try {
                    gameCommandService.handle(command);
                } catch (RuntimeException e) {
                    System.out.println("Error registering game: " + e.getMessage());
                }
            } else {
                System.out.println("The game already exists: " + command.title());
            }
        }
    }
}
