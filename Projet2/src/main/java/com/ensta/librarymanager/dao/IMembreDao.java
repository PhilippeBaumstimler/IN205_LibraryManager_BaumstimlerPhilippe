package com.ensta.librarymanager.dao;

import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Membre;

public interface IMembreDao {
	public List<Membre> getList() throws DaoException;
	public Membre getById(int id) throws DaoException;
	public int create(Membre membre) throws DaoException;
	public void update(Membre membre) throws DaoException;
	public void delete(Membre membre) throws DaoException;
	public int count() throws DaoException;
}
