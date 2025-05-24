package com.finance.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.modelo.UsuarioModelo;
import com.finance.repositorio.UsuarioRepositorio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuario")
public class UsuarioControle {

    @Autowired
    private UsuarioRepositorio server;

    @GetMapping("/listar")
    public List<UsuarioModelo> listar() {
        return server.findAll();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioModelo> buscar(@PathVariable Long id) {
        return server.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/salvar")
    public UsuarioModelo salvar(@RequestBody UsuarioModelo entity) {
        return server.save(entity);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioModelo usuario) {
        Optional<UsuarioModelo> encontrado = server.findByEmailAndSenha(
            usuario.getEmail(), usuario.getPassword()
        );

        if (encontrado.isPresent()) {
            return ResponseEntity.ok(encontrado.get()); // 200 com os dados
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }

	
}
