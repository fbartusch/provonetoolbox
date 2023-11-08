package org.provtools.provone.model;

import org.openprovenance.prov.model.Statement;

/**
 * Interface for a PROV Statement ({@link Statement}) (a unit of provenance description) or a Bundle ({@link Bundle}) (a named set of provenance statements)
 * @author lavm
 *
 */
public interface ProvOneStatementOrBundle extends Statement {

	/** Gets the type of a ProvOne provenance statement
	 * @return {@link ProvOneKind}
	 */
	ProvOneKind getProvOneKind();

	/** Enumerated type for each type of provenance statement or bundle. */
	enum ProvOneKind {
		PROVONE_PROGRAM,
		PROVONE_PORT,
		PROVONE_HASINPORT
	}

}