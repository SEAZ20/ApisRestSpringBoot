package com.seaz.proyectoseaz.repository.user;

import com.seaz.proyectoseaz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
