package org.provtools.provone.model;

import org.openprovenance.prov.model.Entity;

/**
 * <p>Interface for the ProvONE Program class.
 * 
 * <p><a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#program-specification">ProvONE-DM Definition for Program</a>:
 * A Program ◊ represents a computational task that consumes and produces data
 * through its input and output ports, respectively. It can be atomic or composite, the latter case represented by a possibly nested Program.*
 *
 * <p><span class="strong">Relevant Factory Methods:</span>
 * <ul>
 * <li> {@link ProvOneFactory#newProgram(QualifiedName)}
 * <li> {@link ProvOneFactory#newProgram(QualifiedName, java.util.Collection)}
 * <li> {@link ObjectFactory#createAgent()}
 * </ul>
 * 
 * <p>
 * 
 * <pre>
 *   <owl:Class rdf:about="http://purl.dataone.org/provone/2015/01/15/ontology#Program">
 *       <rdfs:label>Program</rdfs:label>
 *       <rdfs:subClassOf rdf:resource="&prov;Entity"/>
 *       <rdfs:subClassOf rdf:resource="&prov;Plan"/>
 *       <prov:definition>A Program represents a computational task that consumes and produces data through its input and output ports, respectively. It can be atomic or composite, the latter case represented by a possibly nested Program. It is also subject to versioning by prov:wasDerivedFrom.</prov:definition>
 *       <prov:category>workflow-specification</prov:category>
 *   </owl:Class>
 * </pre>
 * 
 * 
 * @see <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#program-specification">ProvONE-DM Program</a>
 * 
 */

public interface Program extends Entity  {

}