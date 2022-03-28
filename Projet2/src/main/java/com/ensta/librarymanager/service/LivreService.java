package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;

public class LivreService implements ILivreService {

	private LivreDao livreDao = LivreDao.getInstance();
	static LivreService instance;
	
	public static LivreService getInstance() {
		if(instance==null) {
			instance = new LivreService();
		}
		return instance;
	}
	
	@Override
	public List<Livre> getList() throws ServiceException {
		try {
			return this.livreDao.getList();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException {
		try {
			EmpruntService empruntService = EmpruntService.getInstance();
			List<Livre> livres = this.livreDao.getList();
			List<Livre> livresDispo = new ArrayList<Livre>();
			for(Livre livre : livres) {
				if(empruntService.isLivreDispo(livre.getId())) {
					livresDispo.add(livre);
				}
			}
			return livresDispo;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public Livre getById(int id) throws ServiceException {
		try {
			return this.livreDao.getById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public int create(Livre livre) throws ServiceException {
		try {
			if(livre.getTitre()==null) {
				throw new ServiceException();
			}
			return this.livreDao.create(livre);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public void update(Livre livre) throws ServiceException {
		try {
			if(livre.getTitre()==null) {
				throw new ServiceException();
			}
			this.livreDao.update(livre);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public void delete(Livre livre) throws ServiceException {
		try {
			this.livreDao.delete(livre);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public int count() throws ServiceException {
		try {
			return this.livreDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}


}
