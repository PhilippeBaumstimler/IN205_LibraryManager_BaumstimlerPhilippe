package com.ensta.librarymanager.service;

import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;

public interface IEmpruntService {
	public List<Emprunt> getList() throws ServiceException;
	public List<Emprunt> getListCurrent() throws ServiceException;
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException;
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException;
	public Emprunt getById(int id) throws ServiceException;
	public void create(Emprunt emprunt) throws ServiceException;
	public void returnBook(Emprunt emprunt) throws ServiceException;
	public int count() throws ServiceException;
	public boolean isLivreDispo(int idLivre) throws ServiceException;
	public boolean isEmpruntPossible(Membre membre) throws ServiceException ;
}
