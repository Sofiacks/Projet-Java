package fr.eni.java.projet.bll;

import fr.eni.java.projet.bo.Utilisateur;
import fr.eni.java.projet.dal.DAOFactory;
import fr.eni.java.projet.dal.UtilisateurDAO;
import fr.eni.java.projet.exceptions.BusinessException;


public class UtilisateurManager 
{

	private UtilisateurDAO utilisateurDAO;
	
	// Appeler UtilisateurManager instanciera un nouvel UtilisateurDAOJdbcImpl
	public UtilisateurManager()
	{
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	
	public Utilisateur Connecter(String username, String password)
	{
		return this.utilisateurDAO.selectByName(username);	
		
	}
	public boolean checkUser(String username)
	{
		return true;
	}
	
	public Utilisateur Recuperer(String mail) 
	{
		return this.utilisateurDAO.selectByEmail(mail);
	}

	public Utilisateur Créer(Utilisateur utilisateur) throws BusinessException {

		BusinessException exception = new BusinessException();
		
		//On appelle les méthodes qui s'assurent que la saisie est réglementaire. Ces méthodes sont définies ci-après.
		this.checkPseudoUnique(utilisateur.getPseudo(), exception);
		this.checkEmailUnique(utilisateur.getEmail(), exception);
		this.checkPseudoAlphanumerique(utilisateur.getPseudo(), exception);
		this.checkTelephoneNumerique(utilisateur.getTelephone(), exception);
		
		if (!exception.hasErreurs()) {
			this.utilisateurDAO.insert(utilisateur);
		} else {
			throw exception;
		}
		return utilisateur;
	}


	private void checkPseudoAlphanumerique(String pseudo, BusinessException exception) {
		// Vérifier que le pseudo n'accepte que les caractères alpha-numériques
		//	(je dirais de chercher si les caractères se situent entre 0 et 9 ou entre a et z, sûrement
		// en les traitant comme des arrays avec des crochets là, vous savez)
		if (pseudo.matches("[a-zA-Z0-9]+")) {
			}	else	{
				exception.ajouterErreur(CodesResultatBLL.REGLE_INSCRIPTION_PSEUDO_ALPHANUMERIQUE_ERREUR);
			}
	}
	
	
	private void checkTelephoneNumerique(String telephone, BusinessException exception) {
		// (Bonus) Vérifier que le numéro de téléphone ne comporte bien que des chiffres
		if (telephone.matches("[0-9]")) {
		}	else	{
			exception.ajouterErreur(CodesResultatBLL.REGLE_INSCRIPTION_TELEPHONE_NUMERIQUE_ERREUR);
		}
	}
	
	
	private void checkPseudoUnique(String pseudo, BusinessException exception) {
		// Vérifier que le pseudo est unique sur toute la plate-forme
		// (check dans la base de données, par un try catch ou un if je sais pas)
		
	}
	
	
	private void checkEmailUnique(String email, BusinessException exception) {
		// Vérifier que l'email est unique sur toute la plate-forme
		// (check dans la base de données, par un try catch ou un if je sais pas)
	}
	
	
}
