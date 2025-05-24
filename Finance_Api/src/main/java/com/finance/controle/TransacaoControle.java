package com.finance.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.modelo.TransacaoModelo;
import com.finance.repositorio.TransacaoRepositorio;

@CrossOrigin("*")
@RestController
@RequestMapping("/transacao")
public class TransacaoControle {

	@Autowired
	TransacaoRepositorio server;
	
    @PostMapping("salvar")
    public ResponseEntity<TransacaoModelo> criarTransacao(@RequestBody TransacaoModelo transacao) {
        // Aqui você pode validar se o userId está presente e válido
        if (transacao.getUserId() == null) {
            return ResponseEntity.badRequest().build();
        }

        TransacaoModelo novaTransacao = server.save(transacao);
        return ResponseEntity.ok(novaTransacao);
    }

    // Listar transações de um usuário específico
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<TransacaoModelo>> listarPorUsuario(@PathVariable Long userId) {
        List<TransacaoModelo> transacoes = server.findByUserId(userId);
        return ResponseEntity.ok(transacoes);
    }
    
    @DeleteMapping("deletar/{id}")
    public void deletar(@PathVariable Long id) {
    	server.deleteById(id);
    }
    
    @GetMapping("/saldo/{userId}")
    public ResponseEntity<Double> obterSaldo(@PathVariable Long userId) {
        Double receitas = server.somarReceitasPorUsuario(userId);
        Double despesas = server.somarDespesasPorUsuario(userId);
        Double saldoTotal = receitas - despesas;
        return ResponseEntity.ok(saldoTotal);
    }
    
	
}
