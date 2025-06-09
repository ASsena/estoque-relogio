package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.UserDTO;
import com.backend.estoquerelogios.exception.LoginIncorretoException;
import com.backend.estoquerelogios.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final UserRepository userepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtEncoder jwtEncoder;

    public TokenService(UserRepository userepo, BCryptPasswordEncoder bCryptPasswordEncoder, JwtEncoder jwtEncoder) {
        this.userepo = userepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    public String createToken(UserDTO userAccess) {

        var userrpository = userepo.findByUsername(userAccess.getUsername());

        if (userrpository.isEmpty() || !userrpository.get().loginIsCorrect(userAccess.getPassword(), bCryptPasswordEncoder)) {
            System.out.println(userAccess.getUsername());
            System.out.println(userAccess.getPassword());
            throw new LoginIncorretoException("Senha ou username incorretos");
        }

        var now = Instant.now();
        var expir = 30000L;


        var scopes = userrpository.get().getRoles().toString();

        var claims = JwtClaimsSet.builder()
                .issuer("login")
                .subject(userrpository.get().getUsername().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expir))
                .claim("scope", scopes)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
