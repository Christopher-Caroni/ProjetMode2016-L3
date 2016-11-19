package bddTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bdd.BaseDeDonnees;
import bddInterface.FenetreTable;

/**
 * Classe de test de fonctionnement de la classe {@link FenetreTable}, spécifiquement de l'insertion et update
 * 
 * @author L3
 *
 */
public class FenetreTableTest {

	/**
	 * Cette connection est persistante pour toutes les méthodes de test
	 */
	Connection connection;
	/**
	 * Fenêtre qui sera utilisée pour exécuter les requêtes SQL
	 */
	FenetreTable fen;

	/**
	 * Initialise la connection JDBC vers le fichier sqlite avant chaque test
	 * 
	 */
	@Before
	public void initConnection() {
		boolean success = false;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:data/test.sqlite");

			success = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (!success) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.println(e);
				}
			}
		}
	}

	/**
	 * Ferme la connetion après chaque test
	 */
	@After
	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initialise une {@link FenetreTable} avec les données correspondantes au nom du model fourni
	 * 
	 * @param model le modèle concerné par la Fenêtre d'édition, null si veut champs vides
	 */
	public void initFenetreTableEdit(String model) {
		boolean success = false;
		if (model != null) {
			try {
				PreparedStatement st;
				st = connection.prepareStatement("select * from ply where nom = ?");
				st.setString(1, model);
				ResultSet rs = st.executeQuery();

				fen = new FenetreTable(1, 4, rs, connection);

				success = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (!success) {
					try {
						connection.close();
					} catch (SQLException e) {
						System.err.println(e);
					}
				}
			}
		} else {
			fen = new FenetreTable(1, 4, null, connection);
		}
	}

	/**
	 * Vérifie que la commande --edit permet d'éxecuter une requête SQL correctement avec des paramètres totalement inxestants, à moitié renseignés ou
	 * complets
	 */
	@Test
	public void testUpdate() {
		BaseDeDonnees.resetTable(connection);
		BaseDeDonnees.fillTable(connection);
		BaseDeDonnees.closeConnection();

		// False car aucun champ valide
		initFenetreTableEdit("weathervane");
		assertFalse(fen.updateTableAmorce("", "", "", true));

		// true car au moins un champ valide
		assertTrue(fen.updateTableAmorce("weathervane2", "", "", true));

		// true car champs valides
		initFenetreTableEdit("weathervane2"); // il faut à chaque fois créer une nouvelle fenêtre car on a changé de nom de modèle
		assertTrue(fen.updateTableAmorce("weathervane3", "weathervane3", "weathervane3", true));

		initFenetreTableEdit("weathervane3");
		assertTrue(fen.updateTableAmorce("", "", "allo", true));
	}

	/**
	 * Vérifie que la commande --add permet d'éxecuter une requête SQL correctement avec des paramètres totalement inxestants, à moitié renseignés ou
	 * avec un nom qui existe déja
	 */
	@Test
	public void testInsert() {
		BaseDeDonnees.resetTable(connection);
		BaseDeDonnees.fillTable(connection);
		BaseDeDonnees.closeConnection();
		// false car nom pas renseigné
		initFenetreTableEdit(null);
		assertFalse(fen.insertTableAmorce("", "", "", true));

		// false car nom existe déja
		initFenetreTableEdit(null);
		assertFalse(fen.insertTableAmorce("weathervane", "", "", true));

		// true car nom renseigné et n'existe pas
		initFenetreTableEdit(null);
		assertTrue(fen.insertTableAmorce("weathervane2", "", "", true));

		// true car valeurs renseignés avec nom inexistant dans la base
		initFenetreTableEdit(null);
		assertTrue(fen.insertTableAmorce("weathervane3", "allo", "allo", true));
	}

}
