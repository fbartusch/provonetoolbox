package org.provtools.provone.model;

/**
 * Interface for a PROV Statement ({@link Statement}) (a unit of provenance description) or a Bundle ({@link Bundle}) (a named set of provenance statements)
 * @author lavm
 *
 */
public interface ProvOneStatementOrBundle {

	/** Gets the type of a ProvOne provenance statement
	 * @return {@link ProvOneKind}
	 */
	ProvOneKind getProvOneKind();

	/** Enumerated type for each type of provenance statement or bundle. */
	enum ProvOneKind {
        // These are "subclasses" of PROV_ENTITY
		PROVONE_PROGRAM,
	}

}
