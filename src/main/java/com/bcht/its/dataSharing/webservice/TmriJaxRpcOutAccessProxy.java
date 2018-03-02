package com.bcht.its.dataSharing.webservice;

public class TmriJaxRpcOutAccessProxy implements com.bcht.its.dataSharing.webservice.TmriJaxRpcOutAccess {
  private String _endpoint = null;
  private com.bcht.its.dataSharing.webservice.TmriJaxRpcOutAccess tmriJaxRpcOutAccess = null;
  
  public TmriJaxRpcOutAccessProxy() {
    _initTmriJaxRpcOutAccessProxy();
  }
  
  public TmriJaxRpcOutAccessProxy(String endpoint) {
    _endpoint = endpoint;
    _initTmriJaxRpcOutAccessProxy();
  }
  
  private void _initTmriJaxRpcOutAccessProxy() {
    try {
      tmriJaxRpcOutAccess = (new com.bcht.its.dataSharing.webservice.TmriJaxRpcOutAccessServiceLocator()).getTmriOutAccess();
      if (tmriJaxRpcOutAccess != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)tmriJaxRpcOutAccess)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)tmriJaxRpcOutAccess)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (tmriJaxRpcOutAccess != null)
      ((javax.xml.rpc.Stub)tmriJaxRpcOutAccess)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bcht.its.dataSharing.webservice.TmriJaxRpcOutAccess getTmriJaxRpcOutAccess() {
    if (tmriJaxRpcOutAccess == null)
      _initTmriJaxRpcOutAccessProxy();
    return tmriJaxRpcOutAccess;
  }
  
  public java.lang.String queryObjectOut(java.lang.String xtlb, java.lang.String jkxlh, java.lang.String jkid, java.lang.String UTF8XmlDoc) throws java.rmi.RemoteException{
    if (tmriJaxRpcOutAccess == null)
      _initTmriJaxRpcOutAccessProxy();
    return tmriJaxRpcOutAccess.queryObjectOut(xtlb, jkxlh, jkid, UTF8XmlDoc);
  }
  
  public java.lang.String writeObjectOut(java.lang.String xtlb, java.lang.String jkxlh, java.lang.String jkid, java.lang.String UTF8XmlDoc) throws java.rmi.RemoteException{
    if (tmriJaxRpcOutAccess == null)
      _initTmriJaxRpcOutAccessProxy();
    return tmriJaxRpcOutAccess.writeObjectOut(xtlb, jkxlh, jkid, UTF8XmlDoc);
  }
  
  
}