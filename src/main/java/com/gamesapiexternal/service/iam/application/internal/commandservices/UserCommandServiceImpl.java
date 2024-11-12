package com.gamesapiexternal.service.iam.application.internal.commandservices;

import com.gamesapiexternal.service.iam.application.internal.outboundservices.hashing.HashingService;
import com.gamesapiexternal.service.iam.application.internal.outboundservices.tokens.TokenService;
import com.gamesapiexternal.service.iam.domain.model.aggregates.User;
import com.gamesapiexternal.service.iam.domain.model.commands.SignInCommand;
import com.gamesapiexternal.service.iam.domain.model.commands.SignUpCommand;
import com.gamesapiexternal.service.iam.domain.services.UserCommandService;
import com.gamesapiexternal.service.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User command service implementation
 * <p>
 *     This class implements the {@link UserCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 * @version 1.0.0
 */

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    /**
     * Handle the sign-in command
     * <p>
     *     This method handles the {@link SignInCommand} command and returns the user and the token.
     *     If the user is not found or the password is invalid, a {@link RuntimeException} will be thrown.
     * </p>
     * @param command the sign-in command containing the username and password
     * @return and optional containing the user matching the username and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if(user.isEmpty())
            throw new RuntimeException("User not found");

        if(!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");

        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and creates a new user.
     * </p>
     * @param command the sign-up command containing the username and password
     * @return an optional containing the created user
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if(userRepository.existsByUsername(command.username())) {
            throw new RuntimeException("Username already exists");
        }
        var user = new User(command.username(), hashingService.encode(command.password()));
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }
}
