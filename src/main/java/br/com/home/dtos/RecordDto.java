package br.com.home.dtos;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.home.models.Record;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordDto {

	private Long id;

	private char gender;

	private Date birthDate;

	private double height;

	private double weight;

	@JsonIgnoreProperties({ "record", "bills" })
	private ClientDto client;

	public static RecordDto create(Record r) {
		ModelMapper modelMapper = new ModelMapper();

		RecordDto dto = modelMapper.map(r, RecordDto.class);

		return dto;
	}

}
