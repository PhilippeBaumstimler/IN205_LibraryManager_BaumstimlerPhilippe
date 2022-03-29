package com.ensta.librarymanager.main;

import java.time.LocalDate;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

public class Main {
	public static void main(String[] args) {
		MembreService membreService = MembreService.getInstance();
		LivreService livreService = LivreService.getInstance();
		EmpruntService empruntService = EmpruntService.getInstance();
		Membre membre = new Membre(6,"BALthAZAR","cyprien","42 rue du Clair Bocage", "rbalthazar@email.com", "0654821968",Abonnement.PREMIUM);
		try {
			System.out.println(membreService.getById(1));
			System.out.println(membreService.count());
			System.out.println(membreService.getList());
			membreService.update(membre);
			System.out.println(membreService.getList());
			System.out.println(livreService.count());
			System.out.println(empruntService.getList());
			Emprunt emprunt = new Emprunt(6, 4, 8, LocalDate.parse("2019-03-11"), LocalDate.now());
			empruntService.returnBook(emprunt);
			System.out.println(empruntService.getList());
			System.out.println(empruntService.getById(6));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
