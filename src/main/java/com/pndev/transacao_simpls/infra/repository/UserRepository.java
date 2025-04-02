package com.pndev.transacao_simpls.infra.repository;

import com.pndev.transacao_simpls.infra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
