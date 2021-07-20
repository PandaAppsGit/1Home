package br.com.home.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Client extends DadUser {

	@OneToOne(mappedBy = "client")
	@Cascade(CascadeType.ALL)
	private Record record;

	@OneToMany(mappedBy = "client")
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();

	@ManyToOne
	private Doctor doctor;

	@ManyToOne
	private Physiotherapist physiotherapist;

	@OneToMany(mappedBy = "client")
	private List<Section> sections = new ArrayList<Section>();

	@OneToMany(mappedBy = "client")
	@Cascade(CascadeType.ALL)
	private List<Bill> bills = new ArrayList<Bill>();

}
