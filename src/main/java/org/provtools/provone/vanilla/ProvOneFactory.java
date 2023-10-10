package org.provtools.provone.vanilla;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openprovenance.prov.model.*;

/** A stateless factory for PROV and ProvONE objects. */

public class ProvOneFactory extends org.openprovenance.prov.vanilla.ProvFactory {

    static Logger logger = LogManager.getLogger(ProvOneFactory.class);

    private final static ProvOneFactory oFactory = new ProvOneFactory();

    public static ProvOneFactory getFactory() {
        return oFactory;
    }

    //TODO Implement ProvOne version of ModelConstructor
    final org.openprovenance.prov.model.ModelConstructor mc;

    // 
    final AtomConstructor ac;

    public ProvOneFactory(ObjectFactory of) {
        //super(of);
        mc=new org.openprovenance.prov.vanilla.ModelConstructor();
        ac=(AtomConstructor)mc;
    }

    public ProvOneFactory () {
        //super(null);
        mc=new org.openprovenance.prov.vanilla.ModelConstructor();
        ac=(AtomConstructor)mc;
    }

    public ProvOneFactory(ObjectFactory of, ModelConstructor mc) {
        //super(of);
        this.mc=mc;
        ac=(AtomConstructor)mc;

    }
}