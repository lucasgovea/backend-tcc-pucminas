package br.com.tcc.pucminas.specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.tcc.pucminas.dto.AgendamentoFilterDTO;
import br.com.tcc.pucminas.model.Agendamento;
import br.com.tcc.pucminas.model.Paciente;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AgendamentoSpecification implements Specification<Agendamento> {

	private static final long serialVersionUID = 1914190650497728031L;
	private AgendamentoFilterDTO filter;
	private static final String PACIENTE = "paciente";

    @Override
    public Predicate toPredicate (Root<Agendamento> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    	
    	List<Predicate> predicates = new ArrayList<>();
    	
    	Join<Agendamento, Paciente> pacienteAgendamentoJoin = root.join(PACIENTE);
    	
    	if(filter != null && filter.getCpf() != null) {
    		predicates.add(builder.equal(pacienteAgendamentoJoin.get("cpf"), filter.getCpf()));
    	}
    	
    	if(filter != null && filter.getNome() != null) {
    		predicates.add(builder.like(pacienteAgendamentoJoin.get("nome"), "%" + filter.getNome() + "%"));
    	}
    	
    	if(filter != null && filter.getProximas() != null && filter.getProximas().booleanValue()) {
    		predicates.add(builder.greaterThan(root.get("marcacao"), LocalDateTime.now()));
    	}
    	
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
