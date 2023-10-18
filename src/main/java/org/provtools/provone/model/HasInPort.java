package org.provtools.provone.model;

import java.util.List;

import org.provtools.provone.vanilla.Port;

/**
 * <p>Interface for ProvONE objects that have inPort.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#hasinport-specification">hasInPort</a> 
 * specifies the Ports of a particular Program that are used as input ports.
 *
 */
public interface HasInPort {

    void addInPort(Port p);

    List<Port> getInPorts();
} 