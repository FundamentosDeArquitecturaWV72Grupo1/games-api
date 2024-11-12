package com.gamesapiexternal.service.games.domain.model.commands;

public record RegisterGameCommand(
        String title,
        String description,
        String embedCode,
        String image,
        String rules,
        String topic) { }
