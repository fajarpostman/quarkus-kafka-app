package com.fajar.infrastructure.repository;

import com.fajar.domain.model.UserData;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserDataRepository implements PanacheRepository<UserData> {
}
