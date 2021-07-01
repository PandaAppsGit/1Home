package br.com.home.dtos;

import org.modelmapper.ModelMapper;

import br.com.home.models.Doctor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorDto {

	private Long id;

	private boolean active;

	private String crm;

	private String name;
	private String email;
	private String cellphone;

	public static DoctorDto create(Doctor d) {
		ModelMapper modelMapper = new ModelMapper();

		DoctorDto dto = modelMapper.map(d, DoctorDto.class);

		return dto;
	}

}
