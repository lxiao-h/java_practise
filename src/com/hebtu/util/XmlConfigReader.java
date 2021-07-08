package com.hebtu.util;

import java.io.File;

import org.dom4j.*;
import org.dom4j.io.*;

import com.hebtu.model.JDBCInfo;

public class XmlConfigReader {
	SAXReader reader = new SAXReader();	
	JDBCInfo info = new JDBCInfo();
	
	public XmlConfigReader() {	
		try {
			Document doc = reader.read(new File("src/config.xml"));
			Element root = doc.getRootElement();			
			Element dbinfo = root.element("db-info");		    
			Element driverNameElt = dbinfo.element("drivername");
			Element urlElt = dbinfo.element("url");
			Element userNameElt = dbinfo.element("username");
			Element passwordElt = dbinfo.element("password");
			
			info.setDrivername(driverNameElt.getStringValue());
			info.setUrl(urlElt.getStringValue());
			info.setUsername(userNameElt.getStringValue());
			info.setPassword(passwordElt.getStringValue());			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}	
	    public JDBCInfo getJdbcInfo(){
	        return info;
	    }
}
