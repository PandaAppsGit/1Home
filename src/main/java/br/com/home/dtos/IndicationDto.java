package br.com.home.dtos;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.home.models.Indication;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IndicationDto {

	private Long id;

	private String clientName;
	private String clientContact;
	private String diagnostic;
	private String observation;

	private String referralPhoto;

	@JsonIgnoreProperties({ "active", "email", "cellphone", "photo", "indications" })
	private DoctorDto doctor;

	public static IndicationDto create(Indication i) {
		ModelMapper modelMapper = new ModelMapper();

		IndicationDto dto = modelMapper.map(i, IndicationDto.class);

		return dto;
	}

}
