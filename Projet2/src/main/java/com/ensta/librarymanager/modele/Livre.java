package com.ensta.librarymanager.modele;

import java.util.Objects;

public class Livre {
	private int id;
	private String titre;
	private String auteur;
	private String isbn;
	
	public Livre(int i, String t, String a, String is) {
		this.id = i;
		this.titre = t;
		this.auteur = a;
		this.isbn = is;
	}
	
	public Livre(String t, String a, String is) {
		this.titre = t;
		this.auteur = a;
		this.isbn = is;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", isbn=" + isbn + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(auteur, id, isbn, titre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livre other = (Livre) obj;
		return Objects.equals(auteur, other.auteur) && id == other.id && Objects.equals(isbn, other.isbn)
				&& Objects.equals(titre, other.titre);
	}

	
}
