package br.com.home.dtos;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.home.models.Section;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SectionDto {

	private Long id;

	private Date date;
	private char status;

	@JsonIgnoreProperties({ "sections", "cellphone", "email", "clients" })
	private PhysiotherapistDto physiotherapist;

	@JsonIgnoreProperties({ "sections", "physiotherapist", "doctor", "evaluations", "record", "bills" })
	private ClientDto client;

	public static SectionDto create(Section s) {
		ModelMapper modelMapper = new ModelMapper();

		SectionDto dto = modelMapper.map(s, SectionDto.class);

		return dto;
	}

}
