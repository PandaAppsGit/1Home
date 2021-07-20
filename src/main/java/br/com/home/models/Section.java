package br.com.home.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Section {

	@Id
	@GeneratedValue
	private Long id;

	private Date date;

	private char status;

	@ManyToOne
	private Physiotherapist physiotherapist;

	@ManyToOne
	private Client client;
}
