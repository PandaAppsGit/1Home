package br.com.home.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

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
public class Physiotherapist extends DadUser {

	private String crefito;

	@OneToMany(mappedBy = "physiotherapist")
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();

	@OneToMany(mappedBy = "physiotherapist")
	private List<Client> clients = new ArrayList<Client>();

	@OneToMany(mappedBy = "physiotherapist")
	private List<Section> sections = new ArrayList<Section>();

}
