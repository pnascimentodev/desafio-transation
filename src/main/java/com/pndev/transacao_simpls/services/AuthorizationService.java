package com.pndev.transacao_simpls.services;

import com.pndev.transacao_simpls.infra.clients.ClientAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor

public class AuthorizationService {

    private final ClientAuthorization client;

    public boolean transferValidate(){
        if(Objects.equals(client.getAuthorization().data().authorization(), true)){
            return true;
        }
        return false;
    }
}
