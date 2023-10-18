package org.provtools.provone.model;

import java.util.List;

import org.provtools.provone.vanilla.Port;

/**
 * <p>Interface for ProvONE objects that have outPort.
 * <p>In ProvONE, <a href="http://jenkins-1.dataone.org/jenkins/view/Documentation%20Projects/job/ProvONE-Documentation-trunk/ws/provenance/ProvONE/v1/provone.html#hasoutport-specification">hasInPort</a> 
 * specifies the Ports of a particular Program that are used as output ports.
 *
 */
public interface HasOutPort {

    void addOutPort(Port pid);

    List<Port> getOutPorts();
} 