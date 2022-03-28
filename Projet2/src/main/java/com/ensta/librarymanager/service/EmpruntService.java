package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;

public class EmpruntService implements IEmpruntService {

	private EmpruntDao empruntDao = EmpruntDao.getInstance();
	static EmpruntService instance;
	
	public static EmpruntService getInstance() {
		if(instance==null) {
			instance = new EmpruntService();
		}
		return instance;
	}
	
	@Override
	public List<Emprunt> getList() throws ServiceException {
		try {
			return this.empruntDao.getList();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrent() throws ServiceException {
		try {
			return this.empruntDao.getListCurrent();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
		try {
			return this.empruntDao.getListCurrentByMembre(idMembre);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
		try {
			return this.empruntDao.getListCurrentByLivre(idLivre);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public Emprunt getById(int id) throws ServiceException {
		try {
			return this.empruntDao.getById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public void create(Emprunt emprunt) throws ServiceException {
		try {
			this.empruntDao.create(emprunt);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public void returnBook(Emprunt emprunt) throws ServiceException {
		try {
			Emprunt e = new Emprunt(emprunt.getId(), emprunt.getIdMembre(), emprunt.getIdLivre(), emprunt.getDateEmprunt(), LocalDate.now());
			this.empruntDao.update(e);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public int count() throws ServiceException {
		try {
			return this.empruntDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		try {
			return this.empruntDao.getListCurrentByLivre(idLivre).isEmpty();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		try {
			int n = this.empruntDao.getListCurrentByMembre(membre.getId()).size();
			if(membre.getAbonnement()==Abonnement.BASIC) {
				return n<2;
			}else if(membre.getAbonnement()==Abonnement.PREMIUM) {
				return n<5;
			}else if(membre.getAbonnement()==Abonnement.VIP) {
				return n<20;
			}
			return false;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

}
