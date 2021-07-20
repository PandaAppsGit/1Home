package br.com.home.dtos;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.home.models.Doctor;
import br.com.home.models.Indication;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorDto {

	private Long id;

	private String crm;

	private String name;
	private String email;
	private String cellphone;

	private String photo;

	@JsonIgnoreProperties({ "doctor" })
	private List<Indication> indications;

	@JsonIgnoreProperties({ "doctor", "sections", "bills" })
	private List<ClientDto> clients;

	public static DoctorDto create(Doctor d) {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration().setAmbiguityIgnored(true);

		DoctorDto dto = modelMapper.map(d, DoctorDto.class);

		return dto;
	}

}
