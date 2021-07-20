package br.com.home.dtos;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.home.models.DadUser;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DadUserDto {

	private long id;

	private boolean accountActive;
	private boolean emailActive;

	private String username;

	private String name;
	private String photo;

	private String token;

	private List<String> roles;

	public static DadUserDto create(DadUser d) {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration().setAmbiguityIgnored(true);

		DadUserDto dto = modelMapper.map(d, DadUserDto.class);

		dto.roles = d.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());

		return dto;
	}

	public static DadUserDto create(DadUser d, String token) {
		DadUserDto dto = create(d);
		dto.token = token;
		return dto;
	}

	public String toJson() throws JsonProcessingException {
		ObjectMapper m = new ObjectMapper();
		return m.writeValueAsString(this);
	}
}
