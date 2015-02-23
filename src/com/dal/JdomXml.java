package com.dal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.bll.enums.Categories;


/**
 * Class to create, parse and remove xml file
 */
public class JdomXml {
	static Document document;
	
	/**
	 * Create xml file according to category
	 * @param cat : (Categories) a member of Enum Categories
	 */
	public static void xmlCreate(Categories cat){
		Database	db	= 	new Database(); 	
		ResultSet	rs;
		
		// Create document
		Element 	root = new Element(cat.toString());
		Element		item = null;
		document = new Document(root);
		
		// Get database entries and create document content
		db.connect();
		rs = getData(cat.toString(), db);
		try {
			while(rs.next()){
				item = new Element("item");
				item.setAttribute(new org.jdom2.Attribute("num", Integer.toString(rs.getInt(cat.getColumnName()))));
				item.setText(rs.getString("label"));
				root.addContent(item);
				item = null;
			}
		} catch (SQLException e) {
			System.out.println("[Error JdomXml.constructor]" +e.getMessage());
		}
		xmlSave(cat.toString());
	}
	
	/**
	 * Get all category entries from database
	 * @param rootName : (String) category.toString() | name of table in database
	 * @param odbCon : (Database) connection being used
	 * @return
	 */
	public static ResultSet getData(String rootName, Database odbCon){
		ResultSet	rs 		= 	null;
		String		query	= 	"SELECT * "+
								"FROM 	" +rootName;
		
		rs = odbCon.executeQuery(query);
		return rs;
	}
	
	/**
	 * Display current document (before the actual xmlFile is created)
	 */
	public static void xmlDisplay() {
		try {
			XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
			output.output(document, System.out);
		} catch (java.io.IOException e){
			System.out.println("[Error JdomXml.displayXml()]" +e.getMessage());
		}
	}
	
	/**
	 * Check in res/xml if file already exists or not
	 * @param rootName : (String) name of xml file (without extension)
	 * @return (boolean) : true (file already exists) or false (files doesn't exist) 
	 */
	public static boolean xmlExists(String rootName){
		String 	filePath 	= new String("res/xml/" +rootName +".xml");
		File	f			= new File(filePath);
		
		return (f.exists() && !f.isDirectory());
	}
	
	/**
	 * Get label from selected item
	 * @param rootName : (String) name of xml file (without extension)
	 * @param numItem : (int) item's num
	 * @return
	 */
	public static String xmlGetLabel(String rootName, int numItem) {
		SAXBuilder 	sxb 	= new SAXBuilder();
		String		label 	= null;
		Element		root;
		List<?>		items;
		Iterator<?>	i;
		
		// Get xml file and build Jdom
		try {
			document = sxb.build(new File("res/xml/" +rootName +".xml")); 
		} catch (IOException e) {
			System.out.println("[Error IOException - JdomXml.xmlGetLabel]" +e.getMessage());
		} catch (JDOMException e) {
			System.out.println("[Error JDOMException - JdomXml.xmlGetLabel]" +e.getMessage());
		}
		
		// Get all items and find the one with attribute num = numItem
		root = document.getRootElement();
		items = root.getChildren("item");
		i = items.iterator();
		while (i.hasNext()){
			Element item = (Element) i.next();
			if (item.getAttribute("num").getValue().equals(Integer.toString(numItem))){
				label = item.getText();
			}
		}
		return (label == null ? "" : label);
	}
	
	/**
	 * Remove selected xml
	 * @param rootName : (String) name of xml file (without extension)
	 * @return
	 */
	public static boolean xmlRemove(String rootName) {
		String 	filePath 	= new String("res/xml/" +rootName +".xml");
		File	f			= new File(filePath);
		
		return (f.delete());
	}
	
	/**
	 * Save xml file into res/xml/rootName.xml
	 * @param rootName : name of file (without extension)
	 */
	public static void xmlSave(String rootName) {
		String file = new String("res/xml/" +rootName +".xml");
		try {
		    XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
		    output.output(document, new FileOutputStream(file));
		} catch (java.io.IOException e){
			System.out.println("[Error JdomXml.saveXml()]" +e.getMessage());
		}
	}
}
