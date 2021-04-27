package ru.grigan.baraban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grigan.baraban.domain.User;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
