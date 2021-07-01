package br.com.home.dtos;

import org.modelmapper.ModelMapper;

import br.com.home.models.Client;
import lombok.Data;

@Data
public class ClientDto {

	private Long id;

	private boolean active;

	private String name;
	private String email;
	private String cellphone;

	public static ClientDto create(Client c) {
		ModelMapper modelMapper = new ModelMapper();

		ClientDto dto = modelMapper.map(c, ClientDto.class);

		return dto;
	}

}
