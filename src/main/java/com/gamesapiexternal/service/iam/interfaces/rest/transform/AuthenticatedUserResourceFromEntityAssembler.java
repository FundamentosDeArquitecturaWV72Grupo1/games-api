package com.gamesapiexternal.service.iam.interfaces.rest.transform;

import com.gamesapiexternal.service.iam.domain.model.aggregates.User;
import com.gamesapiexternal.service.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}
