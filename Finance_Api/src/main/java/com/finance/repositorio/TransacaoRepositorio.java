package com.finance.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finance.modelo.TransacaoModelo;

public interface TransacaoRepositorio extends JpaRepository<TransacaoModelo, Long> {
	List<TransacaoModelo> findByUserId(Long userId);
	
	@Query("SELECT COALESCE(SUM(t.amount), 0) FROM TransacaoModelo t WHERE t.userId = :userId AND t.type = 'INCOME'")
	Double somarReceitasPorUsuario(@Param("userId") Long userId);

	@Query("SELECT COALESCE(SUM(t.amount), 0) FROM TransacaoModelo t WHERE t.userId = :userId AND t.type = 'EXPENSE'")
	Double somarDespesasPorUsuario(@Param("userId") Long userId);

}
