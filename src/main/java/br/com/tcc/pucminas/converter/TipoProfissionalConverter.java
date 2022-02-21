package br.com.tcc.pucminas.converter;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;

import br.com.tcc.pucminas.model.TipoProfissional;

public class TipoProfissionalConverter implements AttributeConverter<TipoProfissional, Integer>{

	@Override
	public Integer convertToDatabaseColumn(TipoProfissional attribute) {
		return Optional.ofNullable(attribute).map(TipoProfissional::getCodigo).orElse(null);
	}

	@Override
	public TipoProfissional convertToEntityAttribute(Integer dbData) {
		return Stream.of(TipoProfissional.values()).filter(e->e.getCodigo().equals(dbData))
				.findFirst()
				.orElse(null);
	}
}
