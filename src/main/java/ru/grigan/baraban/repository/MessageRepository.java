package ru.grigan.baraban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grigan.baraban.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
