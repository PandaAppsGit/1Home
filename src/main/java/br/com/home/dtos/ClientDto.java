package br.com.home.dtos;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.home.models.Client;
import lombok.Data;

@Data
public class ClientDto {

	private Long id;

	private String name;
	private String email;
	private String cellphone;
	private String photo;

	@JsonIgnoreProperties({ "client" })
	private RecordDto record;

	@JsonIgnoreProperties({ "client" })
	private List<EvaluationDto> evaluations;

	@JsonIgnoreProperties({ "clients", "indications", "cellphone", "email" })
	private DoctorDto doctor;

	@JsonIgnoreProperties({ "clients", "cellphone", "email", "sections", })
	private PhysiotherapistDto physiotherapist;

	@JsonIgnoreProperties({ "client", "physiotherapist" })
	private List<SectionDto> sections;

	@JsonIgnoreProperties({ "client", })
	private List<BillDto> bills;

	public static ClientDto create(Client c) {
		ModelMapper modelMapper = new ModelMapper();

		ClientDto dto = modelMapper.map(c, ClientDto.class);

		return dto;
	}

}
