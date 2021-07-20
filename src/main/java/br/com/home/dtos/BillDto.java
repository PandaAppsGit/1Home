package br.com.home.dtos;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.home.models.Bill;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BillDto {

	private Long id;

	private double price;

	private String pdf;

	private Date date;

	private char status;

	@JsonIgnoreProperties({ "bills", "evaluations", "doctor", "physiotherapist", "sections", })
	private ClientDto client;

	public static BillDto create(Bill b) {
		ModelMapper modelMapper = new ModelMapper();

		BillDto dto = modelMapper.map(b, BillDto.class);

		return dto;
	}

}
