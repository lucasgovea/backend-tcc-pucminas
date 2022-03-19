package br.com.tcc.pucminas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.tcc.pucminas.model.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long>, JpaSpecificationExecutor<Profissional> {

}
