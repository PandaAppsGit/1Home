package br.com.home.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Treatment {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String description;
	private String image;

}
