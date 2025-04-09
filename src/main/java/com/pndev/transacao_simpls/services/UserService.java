package com.pndev.transacao_simpls.services;


import com.pndev.transacao_simpls.exceptions.UserNotFound;
import com.pndev.transacao_simpls.infra.entity.User;
import com.pndev.transacao_simpls.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User findUser(Long id) {
        return  repository.findById(id)
                .orElseThrow(()
                        -> new UserNotFound("Usuário não encontrado"));
    }
}
