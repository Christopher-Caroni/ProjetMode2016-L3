package bddTest;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Test;

import main.BaseDeDonnees;
import result.BDDResultEnum;
import result.BasicResultEnum;

/**
 * Classe de test de fonctionnement de la classe BaseDeDonnees
 * 
 * @author L3
 *
 */
public class BaseDeDonneesTest {

	@Test
	public void test_no_args() {
		String[] args = new String[0];
		assertEquals(BasicResultEnum.NO_ARGUMENTS, BaseDeDonnees.executeDB(args, true, true, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	@Test
	public void test_conflicting_args() {
		String[] args = new String[] { "--all", "--name", "galleon" };
		assertEquals(BasicResultEnum.CONFLICTING_ARGUMENTS,
				BaseDeDonnees.executeDB(args, true, true, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	/**
	 * Vérifie que la commande --all liste correctement des modèles avec une base vide et remplie
	 */
	@Test
	public void test_all_base_init_remplie() {
		String[] args = new String[] { "--all" };
		assertEquals(BDDResultEnum.SHOW_ALL_SUCCESSFUL,
				BaseDeDonnees.executeDB(args, true, true, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	@Test
	public void test_all_base_init_vide() {
		String[] args = new String[] { "--all" };
		assertEquals(BDDResultEnum.EMPTY_DB, BaseDeDonnees.executeDB(args, true, false, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	/**
	 * Vérifie que la commande --name répond correctement avec un modèle existant ainsi qu'un non existant
	 */
	@Test
	public void test_name_recherche_valide() {
		String[] args = new String[] { ("--name"), ("weathervane") };
		assertEquals(BDDResultEnum.SHOW_NAME_SUCCESSFUL,
				BaseDeDonnees.executeDB(args, true, true, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	@Test
	public void test_name_base_vide() {
		String[] args = new String[] { ("--name"), ("weathervane") };
		assertEquals(BDDResultEnum.EMPTY_DB, BaseDeDonnees.executeDB(args, true, false, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	@Test
	public void test_name_recherche_invalide() {
		String[] args = new String[] { ("--name"), ("hello") };
		assertEquals(BDDResultEnum.MODEL_NOT_FOUND, BaseDeDonnees.executeDB(args, false, true, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	/**
	 * Vérifie que la commande --find répond correctement avec des mot clés existant ainsi que non existants
	 */
	@Test
	public void test_find_recherche_invalide() {
		String[] args = new String[] { ("--find"), ("hello") };
		assertEquals(BDDResultEnum.MODEL_NOT_FOUND, BaseDeDonnees.executeDB(args, true, true, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	@Test
	public void test_find_base_vide() {
		String[] args = new String[] { ("--find"), ("mot") };
		assertEquals(BDDResultEnum.EMPTY_DB, BaseDeDonnees.executeDB(args, true, false, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	@Test
	public void test_find_tous_ont_mot_cle() {
		String[] args = new String[] { ("--find"), ("mot") };
		assertEquals(BDDResultEnum.FIND_SUCCESSFUL, BaseDeDonnees.executeDB(args, true, true, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	@Test
	public void test_find_recherche_valide() {
		String[] args3 = new String[] { ("--find"), ("mes mots") };
		assertEquals(BDDResultEnum.FIND_SUCCESSFUL, BaseDeDonnees.executeDB(args3, false, false, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	/**
	 * Vérifie que la commande --delete répond correctement
	 */
	@Test
	public void test_delete_base_vide() {

		// la base est vide
		String[] args = new String[] { ("--delete"), ("weathervane") };
		assertEquals(BDDResultEnum.EMPTY_DB, BaseDeDonnees.executeDB(args, true, false, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();
	}

	@Test
	public void test_delete_base_remplie_et_venant_detre_suppr() {
		String[] args = new String[] { ("--delete"), ("weathervane") };
		assertEquals(BDDResultEnum.DELETE_SUCCESSFUL, BaseDeDonnees.executeDB(args, true, true, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();

		assertEquals(BDDResultEnum.MODEL_NOT_FOUND, BaseDeDonnees.executeDB(args, false, false, true, Paths.get("test-data/test.sqlite")).getCode());
		BaseDeDonnees.closeConnection();

	}

}
