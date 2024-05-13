package com.example.Sales.Management.System.configuration;

import com.example.Sales.Management.System.entity.UserEnitiy;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Map;
import java.util.Optional;

public class AuditingImplemen implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // get current user name
        return Optional.ofNullable(((UserEnitiy) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName());
    }
}
