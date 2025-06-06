package com.backend.estoquerelogios.service;

import com.backend.estoquerelogios.dto.CreateUserDTO;
import com.backend.estoquerelogios.entities.Role;
import com.backend.estoquerelogios.entities.User;
import com.backend.estoquerelogios.exception.NaoExistenteException;
import com.backend.estoquerelogios.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(CreateUserDTO infoUser){


        userRepository.findByUsername(infoUser.getUsername()).ifPresentOrElse(
                users -> {throw new NaoExistenteException("Usuário com o username: " + infoUser.getUsername() + " não encontrado!");},
                () -> {
                    var user = new User();
                    user.setUsername(infoUser.getUsername());
                    user.setSenha(passwordEncoder.encode(infoUser.getSenha()));
                    user.setRoles(Role.BASICO);
                    userRepository.save(user);
                }
        );
    }

    public User userEspecifico(String username){
        var userep = userRepository.findByUsername(username);
        if(userep.isPresent()){
            return userep.get();
        }else{
            throw new NaoExistenteException("Usuário não encontrado!");
        }
    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(String username, JwtAuthenticationToken token){

        var user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new NaoExistenteException("Usuário não encontrado!");
        }
        userRepository.delete(user.get());
    }
}
