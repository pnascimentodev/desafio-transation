package com.pndev.transacao_simpls.infra.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://util.devi.tools/api/v2/authorize ", name = "authorization")
public interface ClientAuthorization {

    @GetMapping
    AuthorizationDTO getAuthorization();
}
