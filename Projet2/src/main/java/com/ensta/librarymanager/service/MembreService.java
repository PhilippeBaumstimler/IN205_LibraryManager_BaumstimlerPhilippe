package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Membre;

public class MembreService implements IMembreService {

	private MembreDao membreDao = MembreDao.getInstance();
	static MembreService instance;
	
	public static MembreService getInstance() {
		if(instance==null) {
			instance = new MembreService();
		}
		return instance;
	}
	
	@Override
	public List<Membre> getList() throws ServiceException {
		try {
			return this.membreDao.getList();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		try {
			EmpruntService empruntService = EmpruntService.getInstance();
			List<Membre> membres = this.membreDao.getList();
			List<Membre> membresDispo = new ArrayList<Membre>();
			for(Membre membre : membres) {
				if(empruntService.isEmpruntPossible(membre)) {
					membresDispo.add(membre);
				}
			}
			return membresDispo;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public Membre getById(int id) throws ServiceException {
		try {
			return this.membreDao.getById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public int create(Membre membre) throws ServiceException {
		try {
			if(membre.getNom()==null || membre.getPrenom()==null) {
				throw new ServiceException();
			}
			membre.setNom(membre.getNom().toUpperCase());
			return this.membreDao.create(membre);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public void update(Membre membre) throws ServiceException {
		try {
			if(membre.getNom()==null || membre.getPrenom()==null) {
				throw new ServiceException();
			}
			membre.setNom(membre.getNom().toUpperCase());
			this.membreDao.update(membre);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	@Override
	public void delete(Membre membre) throws ServiceException {
		try {
			this.membreDao.delete(membre);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public int count() throws ServiceException {
		try {
			return this.membreDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

}
