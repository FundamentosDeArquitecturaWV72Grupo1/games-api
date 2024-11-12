package com.gamesapiexternal.service.iam.interfaces.rest.transform;

import com.gamesapiexternal.service.iam.domain.model.aggregates.User;
import com.gamesapiexternal.service.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getId(), user.getUsername());
    }
}
