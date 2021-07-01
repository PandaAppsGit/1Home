package br.com.home.dtos;

import org.modelmapper.ModelMapper;

import br.com.home.models.Physiotherapist;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhysiotherapistDto {

	private Long id;

	private boolean active;

	private String crefito;

	private String name;
	private String email;
	private String cellphone;

	public static PhysiotherapistDto create(Physiotherapist p) {
		ModelMapper modelMapper = new ModelMapper();

		PhysiotherapistDto dto = modelMapper.map(p, PhysiotherapistDto.class);

		return dto;
	}

}
