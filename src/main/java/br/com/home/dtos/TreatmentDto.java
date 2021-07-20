package br.com.home.dtos;

import org.modelmapper.ModelMapper;

import br.com.home.models.Treatment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TreatmentDto {

	private Long id;

	private String name;
	private String description;
	private String image;

	public static TreatmentDto create(Treatment t) {
		ModelMapper modelMapper = new ModelMapper();

		TreatmentDto dto = modelMapper.map(t, TreatmentDto.class);

		return dto;
	}

}
