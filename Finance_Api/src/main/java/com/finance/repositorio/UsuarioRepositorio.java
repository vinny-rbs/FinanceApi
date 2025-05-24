package com.finance.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finance.modelo.UsuarioModelo;

public interface UsuarioRepositorio extends JpaRepository<UsuarioModelo, Long> {
	@Query("SELECT u FROM UsuarioModelo u WHERE u.email = :email AND u.password = :password")
	Optional<UsuarioModelo> findByEmailAndSenha(@Param("email") String email, @Param("password") String password);
}
