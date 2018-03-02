package com.bcht.its.dataSharing.webservice;


import com.bcht.its.dataSharing.beans.QueryCondition;
import com.bcht.its.dataSharing.beans.Viosurveil;
import com.bcht.its.dataSharing.utils.PropertiesUtils;
import com.bcht.its.dataSharing.utils.XstreamUtils;
import org.apache.axis.AxisFault;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class WebServiceClient {
	private static final String XML_PREFIX = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<root>\r\n";
	private static final String XML_END = "\r\n</root>";
	private static TmriOutAccessSoapBindingStub webClient = null;
	/**集中指挥平台的URL地址*/
	private static String url = PropertiesUtils.getValue("Tmri_webservice_url");
	private static String url1 = PropertiesUtils.getValue("Tmri_webservice_url1");
	private static String url2 = PropertiesUtils.getValue("Tmri_webservice_url2");
	private static String url3 = PropertiesUtils.getValue("Tmri_webservice_url3");
	/**集中指挥平台的序列号*/
	private static String serializ = PropertiesUtils.getValue("Tmri_webservice_serializ");
	/**图片服务器公安网IP*/
	private static String TP_IP1 = PropertiesUtils.getValue("TP_SERVER_GAW_IP");
	/**图片服务器专网IP*/
	private static String TP_IP2 = PropertiesUtils.getValue("TP_SERVER_ZW_IP");
	public static void initWebServiceClient(){
		try {
			webClient = new TmriOutAccessSoapBindingStub(new URL(url), null);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			try {
				webClient = new TmriOutAccessSoapBindingStub(new URL(url1), null);
			} catch (AxisFault axisFault) {
				axisFault.printStackTrace();
			} catch (MalformedURLException e1) {
				try {
					webClient = new TmriOutAccessSoapBindingStub(new URL(url2), null);
				} catch (AxisFault axisFault) {
					axisFault.printStackTrace();
				} catch (MalformedURLException e2) {
					try {
						webClient = new TmriOutAccessSoapBindingStub(new URL(url3), null);
					} catch (AxisFault axisFault) {
						axisFault.printStackTrace();
					} catch (MalformedURLException e3) {
						e3.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 客运车辆信息核查接口调用
	 * @param param
	 * @return
	 * @throws RemoteException 
	 */
	public static String queryVehBus(QueryCondition param) {
		if(webClient == null){
			initWebServiceClient();
		}
		String xml = XML_PREFIX + XstreamUtils.objectToXml(param)+XML_END;
		String resultXML = null;
		try {
			resultXML = webClient.queryObjectOut("01", serializ, "01C21", xml);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return resultXML;
		
	}

	public static String writeViosurveil(Viosurveil param){
		if(webClient == null){
			initWebServiceClient();
		}
		String xml = XML_PREFIX + XstreamUtils.objectToXml(param)+XML_END;
		String resultXML = null;
		try {
			resultXML = webClient.queryObjectOut("04", serializ, "04C52", xml);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return resultXML;
	}


}

