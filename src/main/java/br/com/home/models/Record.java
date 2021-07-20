package br.com.home.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Record {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private char gender;

	private Date birthDate;

	private double height;

	private double weight;

	@OneToOne
	private Client client;

}
