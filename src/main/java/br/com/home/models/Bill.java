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
public class Bill {

	@Id
	@GeneratedValue
	private Long id;

	private String pdf;

	private double price;

	private Date date;

	private char status;

	@ManyToOne
	private Client client;

}
