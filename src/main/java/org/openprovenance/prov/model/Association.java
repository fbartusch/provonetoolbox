package org.openprovenance.prov.model;

/**
 * <p>Interface for the PROV-O Association complex type.
 * 
 * <p><a href="https://www.w3.org/TR/prov-o/#Association">PROV-O Definition for Association</a>: An activity association
 * is an assignment of responsibility to an agent for an activity, indicating that the agent had a role in the activity. It further
 * allows for a plan to be specified, which is the plan intended by the agent to achieve some goals in the context of this activity.
 *
 *
 * <p><span class="strong">Relevant Factory Methods:</span>
 * <ul>
 * <li> {@link ProvFactory#newAgent(QualifiedName)}
 * <li> {@link ProvFactory#newAgent(QualifiedName, java.util.Collection)}
 * <li> {@link ObjectFactory#createAgent()}
 * </ul>
 * 
 * <p>The following schema fragment specifies the expected content contained within this type.
 * 
 * <pre>
 * &lt;complexType name="Agent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.w3.org/ns/prov#}label" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.w3.org/ns/prov#}location" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.w3.org/ns/prov#}type" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute ref="{http://www.w3.org/ns/prov#}id"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 * @see <a href="http://www.w3.org/TR/prov-dm/#term-agent">PROV-DM Agent</a>
 * @see <a href="http://www.w3.org/TR/prov-o/#Agent">PROV-O Agent</a>
 * @see <a href="http://www.w3.org/TR/prov-n/#expression-Agent">PROV-N Agent</a>
 * @see <a href="http://www.w3.org/TR/prov-xml/#term-Agent">PROV-XML Agent</a>
 * 
 */

public interface Association extends Identifiable, HasLabel, HasType, HasLocation, HasOther, HasRole, Statement, Element  {
    
}