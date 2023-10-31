package org.provtools.provone.vanilla;

import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.interop.Outputer;
import org.openprovenance.prov.model.ProvFactory;

/*
 * Outputer for ProvOne documents.
 * 
 * Allows setting custom Serializers that can serialize ProvOne elements.
 */

public class ProvOneOutputer extends Outputer {

    public ProvOneOutputer(InteropFramework interopFramework, ProvFactory pFactory) {
        super(interopFramework, pFactory);
        //TODO Auto-generated constructor stub
    }
    
}
