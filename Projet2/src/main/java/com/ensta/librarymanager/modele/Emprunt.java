package com.ensta.librarymanager.modele;

import java.time.LocalDate;
import java.util.Objects;

public class Emprunt {
	private int id;
	private int idMembre;
	private int idLivre;
	private LocalDate dateEmprunt;
	private LocalDate dateRetour;
	
	public Emprunt(int i, int idm, int idl, LocalDate de, LocalDate dr) {
		this.id = i;
		this.idMembre = idm;
		this.idLivre = idl;
		this.dateEmprunt = de;
		this.dateRetour = dr;
	}
	
	public Emprunt(int idm, int idl, LocalDate de, LocalDate dr) {
		this.idMembre = idm;
		this.idLivre = idl;
		this.dateEmprunt = de;
		this.dateRetour = dr;
	}
		
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdMembre() {
		return idMembre;
	}
	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}
	
	public int getIdLivre() {
		return idLivre;
	}
	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}
	
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	
	public LocalDate getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}

	@Override
	public String toString() {
		return "Emprunt [id=" + id + ", idMembre=" + idMembre + ", idLivre=" + idLivre + ", dateEmprunt=" + dateEmprunt
				+ ", dateRetour=" + dateRetour + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateEmprunt, dateRetour, id, idLivre, idMembre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprunt other = (Emprunt) obj;
		return Objects.equals(dateEmprunt, other.dateEmprunt) && Objects.equals(dateRetour, other.dateRetour)
				&& id == other.id && idLivre == other.idLivre && idMembre == other.idMembre;
	}
}
