package br.com.cadastroConta.domain;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Account extends PanacheEntity {

	private Long bank;
	private Long agency;
	private Long account;

}
