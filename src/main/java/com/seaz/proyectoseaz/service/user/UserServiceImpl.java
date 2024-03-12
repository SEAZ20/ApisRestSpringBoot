package com.seaz.proyectoseaz.service.user;

import com.seaz.proyectoseaz.entity.User;
import com.seaz.proyectoseaz.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService{


    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
       userRepository.deleteById(id);
    }
    @Override
    public User update(Long id, User user){
        log.info("Ingresa a update persona con id : {}", id);
        Optional<User> oUser = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada con id: " + id)));
        oUser.get().setName(user.getName());
        oUser.get().setLastName((user.getLastName()));
        oUser.get().setEmail(user.getEmail());
        return userRepository.save(oUser.get());
    }
}
