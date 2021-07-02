package br.com.home.dtos;

import org.modelmapper.ModelMapper;

import br.com.home.models.Client;
import lombok.Data;

@Data
public class AdminDto {

	private Long id;

	private String name;
	private String email;
	private String cellphone;
	private String photo;

	public static ClientDto create(Client c) {
		ModelMapper modelMapper = new ModelMapper();

		ClientDto dto = modelMapper.map(c, ClientDto.class);

		return dto;
	}

}
