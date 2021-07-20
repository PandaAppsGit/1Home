package br.com.home.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

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
public class Doctor extends DadUser {

	private String crm;

	@OneToMany(mappedBy = "doctor")
	@Cascade(CascadeType.ALL)
	private List<Indication> indications = new ArrayList<Indication>();

	@OneToMany(mappedBy = "doctor")
	private List<Client> clients = new ArrayList<Client>();

}
