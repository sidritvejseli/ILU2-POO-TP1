package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		
		marche = new Marche(nbEtals);
	
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		this.marche.utiliserEtal(marche.trouverEtalLibre(), vendeur, produit, nbProduit);
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " +nbProduit + " " + produit );
		
		
		return null;
	}
	
	
	
	
	
	public static class Marche {
		private Etal[] etals;
		public Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for (int i = 0; i < nbEtal; i++) {
				etals[i] = new Etal();
			}
		}
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			

			Etal etal_courant = etals[indiceEtal];
			etal_courant.occuperEtal(vendeur, produit, nbProduit);
			
		}
		private int trouverEtalLibre(){
			
			for (int i = 0; i <etals.length; i++) {
				if(!etals[i].isEtalOccupe()) {
					
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int taille = 0;
			Etal[] etals_produits;
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					taille++;
				}
			}
			
			etals_produits = new Etal[taille];
			int indice = 0;
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].contientProduit(produit)) {
					etals_produits[indice] = etals[i];
					indice++;
				}
			}
			
			return etals_produits;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			Etal etal_vendeur;
			for(int i = 0; i < etals.length; i++) {
				if(etals[i].getVendeur().equals(gaulois)) {
					etal_vendeur = etals[i];
					return etal_vendeur;
				}
			}
			return null;
		}
		
		private void afficherMarche(){
			int nb_occupe = 0;
			for (int i = 0; i < etals.length; i ++) {
				if(etals[i].isEtalOccupe()) {
					nb_occupe++;
				}
			}
			for(int i = 0; i<nb_occupe; i ++) {
				etals[i].afficherEtal();
			}
			System.out.println("Il reste "+ (etals.length-nb_occupe) + "etals non utilises dans le marche");
		}
	}
}