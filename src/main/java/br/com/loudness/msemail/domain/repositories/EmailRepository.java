package br.com.loudness.msemail.domain.repositories;

import br.com.loudness.msemail.domain.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
