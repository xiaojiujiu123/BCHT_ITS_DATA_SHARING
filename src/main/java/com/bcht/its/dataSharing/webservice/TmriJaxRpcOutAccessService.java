/**
 * TmriJaxRpcOutAccessService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bcht.its.dataSharing.webservice;

public interface TmriJaxRpcOutAccessService extends javax.xml.rpc.Service {
    public java.lang.String getTmriOutAccessAddress();

    public com.bcht.its.dataSharing.webservice.TmriJaxRpcOutAccess getTmriOutAccess() throws javax.xml.rpc.ServiceException;

    public com.bcht.its.dataSharing.webservice.TmriJaxRpcOutAccess getTmriOutAccess(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
