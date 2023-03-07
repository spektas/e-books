package com.getir.ebooks;

import com.getir.ebooks.security.entity.User;
import com.getir.ebooks.security.service.JpaUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component public class AppEvents {

    private final JpaUserDetailsService userDetailsService;

    @EventListener(ApplicationReadyEvent.class)
    public void startApp() {
        User user = userDetailsService.save();
    }
}