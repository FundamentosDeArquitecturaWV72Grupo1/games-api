package com.gamesapiexternal.service.iam.interfaces.rest.transform;

import com.gamesapiexternal.service.iam.domain.model.commands.SignUpCommand;
import com.gamesapiexternal.service.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.email(), resource.password());
    }
}
