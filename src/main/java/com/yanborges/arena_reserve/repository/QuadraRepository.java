package com.yanborges.arena_reserve.repository;

import com.yanborges.arena_reserve.model.Quadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // ✨ PADRÃO REPOSITORY: Abstrai o acesso aos dados da tabela de Quadras
public interface QuadraRepository extends JpaRepository<Quadra, Long> {
}