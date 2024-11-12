package com.gamesapiexternal.service.games.interfaces.rest.resources;

public record GameResource(
        Long id,
        String title,
        String description,
        String embedCode,
        String image,
        String rules,
        String topic
) { }
