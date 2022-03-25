package br.com.tcc.pucminas.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tcc.pucminas.model.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>, JpaSpecificationExecutor<Agendamento> {

	List<Agendamento> findByProfissionalIdAndMarcacao(Long idProfissional, LocalDateTime horario);
	
	@Query(value = "SELECT * FROM AGENDAMENTO WHERE date_trunc('day', ?1) = date_trunc('day', marcacao)", nativeQuery = true)
	List<Agendamento> buscarAgendamentosPeloDia(LocalDate diaQueDesejaConfirmar);
	
	@Query(value = "SELECT a FROM Agendamento a WHERE a.paciente.id = ?1 and a.marcacao > CURRENT_TIMESTAMP order by a.marcacao")
	List<Agendamento> buscarProximosAgendamentoDoPaciente(Long idPaciente);
}
