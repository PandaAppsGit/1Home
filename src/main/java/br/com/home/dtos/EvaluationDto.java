package br.com.home.dtos;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.home.models.Evaluation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EvaluationDto {

	private Long id;

	private String body;

	private Date date;

	@JsonIgnoreProperties({ "evaluations" })
	private ClientDto client;

	@JsonIgnoreProperties({ "email", "cellphone" })
	private PhysiotherapistDto physiotherapist;

	public static EvaluationDto create(Evaluation e) {
		ModelMapper modelMapper = new ModelMapper();

		EvaluationDto dto = modelMapper.map(e, EvaluationDto.class);

		return dto;
	}

}
