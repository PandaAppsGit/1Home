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
public class Evaluation {

	@Id
	@GeneratedValue
	private Long id;

	private String body;

	private Date date;

	@ManyToOne
	private Physiotherapist physiotherapist;

	@ManyToOne
	private Client client;
}
