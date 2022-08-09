package bg.softuni.oix.annotations;

import bg.softuni.oix.model.entity.UserRoleEntity;
import bg.softuni.oix.model.enums.UserRoleEnum;
import bg.softuni.oix.model.user.OixUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        UserRoleEntity user = new UserRoleEntity();
        user.setUserRole(UserRoleEnum.USER);
        UserRoleEntity admin = new UserRoleEntity();
        admin.setUserRole(UserRoleEnum.ADMIN);
        List<UserRoleEntity> roles = new ArrayList<>();
        roles.add(user);
        roles.add(admin);

        OixUserDetails principal =
                new OixUserDetails(
                        1L, "password", "AAAAAA@AAAAAA", "AAAAAA", "AAAAAA",
                        roles.stream().map(this::map).collect(Collectors.toList()));

        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }

    private GrantedAuthority map(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getUserRole().name());
    }
}

