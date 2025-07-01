package com.fajar.usecase;

import com.fajar.domain.model.UserData;
import com.fajar.infrastructure.repository.UserDataRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SaveUserDataUseCase {

    @Inject
    UserDataRepository repository;

    public void execute(UserData userData) {
        userData.setName(userData.getName().toUpperCase());
        repository.persistAndFlush(userData);
    }
}
