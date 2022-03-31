package br.com.tcc.pucminas.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.tcc.pucminas.dto.FuncionarioFilterDTO;
import br.com.tcc.pucminas.model.Funcionario;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FuncionarioSpecification implements Specification<Funcionario> {

	private static final long serialVersionUID = -4801787137962657660L;
	
	private FuncionarioFilterDTO filter;

    @Override
    public Predicate toPredicate (Root<Funcionario> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    	
    	List<Predicate> predicates = new ArrayList<>();;
    	
    	if(filter != null && filter.getCpf() != null) {
    		predicates.add(builder.equal(root.get("cpf"), filter.getCpf()));
    	}
    	
    	if(filter != null && filter.getNome() != null) {
    		predicates.add(builder.like(builder.lower(root.<String>get("nome")), "%" + filter.getNome().toLowerCase() + "%"));
    	}
    	
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
