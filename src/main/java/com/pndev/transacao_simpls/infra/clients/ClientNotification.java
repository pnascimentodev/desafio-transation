package com.pndev.transacao_simpls.infra.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "https://util.devi.tools/api/v1/notify", name = "notification")
public interface ClientNotification {

    @PostMapping
    void sendNotification();
}

