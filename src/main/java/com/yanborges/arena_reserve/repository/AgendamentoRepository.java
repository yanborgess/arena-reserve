package com.yanborges.arena_reserve.repository;

import com.yanborges.arena_reserve.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository // ✨ PADRÃO REPOSITORY: Abstrai o acesso à tabela de Agendamentos
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // Consulta customizada para verificar se o horário solicitado se choca com outro agendamento ativo
    @Query("SELECT a FROM Agendamento a WHERE a.quadra.id = :quadraId AND a.status = 'AGENDADO' AND " +
            "((:inicio >= a.dataHoraInicio AND :inicio < a.dataHoraFim) OR " +
            "(:fim > a.dataHoraInicio AND :fim <= a.dataHoraFim) OR " +
            "(a.dataHoraInicio >= :inicio AND a.dataHoraInicio < :fim))")
    List<Agendamento> buscarAgendamentosConflitantes(
            @Param("quadraId") Long quadraId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);
}