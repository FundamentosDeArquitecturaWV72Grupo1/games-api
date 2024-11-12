package com.gamesapiexternal.service.games.domain.model.aggregates;

import com.gamesapiexternal.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "games")
public class Game extends AuditableAbstractAggregateRoot<Game> {
    private String title;
    private String description;
    private String embedCode;
    private String image;
    private String rules;
    private String topic;

    public Game(String title, String description, String embedCode, String image, String rules, String topic) {
        this.title = title;
        this.description = description;
        this.embedCode = embedCode;
        this.image = image;
        this.rules = rules;
        this.topic = topic;
    }
}