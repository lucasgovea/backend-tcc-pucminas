package br.com.tcc.pucminas.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.tcc.pucminas.dto.PacienteFilterDTO;
import br.com.tcc.pucminas.model.Paciente;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PacienteSpecification implements Specification<Paciente> {

	private static final long serialVersionUID = 8936396550772715808L;
	private PacienteFilterDTO filter;

    @Override
    public Predicate toPredicate (Root<Paciente> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    	
    	List<Predicate> predicates = new ArrayList<>();;
    	
    	if(filter != null && filter.getCpf() != null) {
    		predicates.add(builder.equal(root.get("cpf"), filter.getCpf()));
    	}
    	
    	if(filter != null && filter.getNome() != null) {
    		predicates.add(builder.like(root.<String>get("nome"), "%" + filter.getNome() + "%"));
    	}
    	
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
