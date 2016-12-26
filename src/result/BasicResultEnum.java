package result;


/**
 * Codes de résulats de base.
 * @author L3
 *
 */
public enum BasicResultEnum {
	/**
	 * Pas d'erreurs
	 */
	ALL_OK,
	/**
	 * Erreur quelconque.
	 */
	UNKNOWN_ERROR,
	BAD_ARGUMENTS,
	NO_ARGUMENTS,
	/**
	 * Il y avait soit une commande BDD en même temps qu'une commande 3D OU il y avait plusieurs commands BDD a la fois.
	 */
	CONFLICTING_ARGUMENTS,
	MULTIPLE_PLY_ARGS,
	/**
	 * L'argument n'est pas une commande ou ne fait pas partie d'une commande. Elle ne doit pas être là.
	 */
	UNKNOWN_ARG,
	NO_PLY_FILE_IN_ARG;
}
