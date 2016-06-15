package presenter;

import java.io.File;
import java.io.Serializable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Shahar
 * @version 1.0
 * @since 09.06.16
 *
 */

@SuppressWarnings("serial")
public class Properties implements Serializable {
	
	/** The Max treads. */
	String maxThreads;
	
	/** The Solving algorithm. */
	String solvingAlgorithm;
	
	/** The Generate algorithm. */
	String generateAlgorithm;
	
	/** The Solutions Cache Path. */
	String cachingPath;
	
	/** The view type. */
	String view;
	
	
	/**
	 * Default Ctor.
	 */
	public Properties() {
		super();
	}


	/**
	 * Ctor
	 *
	 * @param path - the properties file path
	 */
	public Properties(String path) {

    try {

	File xmlFile = new File(path);
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	Document doc = builder.parse(xmlFile);
				
	doc.getDocumentElement().normalize();
	
	NodeList nList = doc.getElementsByTagName("data");
			
		Node nNode = nList.item(0);
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			
			setCachingPath(eElement.getElementsByTagName("caching_path").item(0).getTextContent());
			setMaxThreads(eElement.getElementsByTagName("max_threads").item(0).getTextContent());
			setSolvingAlgorithm(eElement.getElementsByTagName("solving_algorithm").item(0).getTextContent());
			setGenerateAlgorithm(eElement.getElementsByTagName("generate_algorithm").item(0).getTextContent());
			setView(eElement.getElementsByTagName("view").item(0).getTextContent());
			}
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }


	/**
	 * This method will return the max threads
	 * @return String
	 */
	public String getMaxThreads() {
		return maxThreads;
	}

	
	/**
	 * This method will set max threads
	 * @param maxThreads - The maximum threads we allow.
	 */
	public void setMaxThreads(String maxThreads) {
		this.maxThreads = maxThreads;
	}


	/**
	 * This method will return the solving algorithm
	 * @return String
	 */
	public String getSolvingAlgorithm() {
		return solvingAlgorithm;
	}


	/**
	 * This method will set the solving algorithm.
	 * @param maxThreads - The solving algorithm.
	 */
	public void setSolvingAlgorithm(String solvingAlgorithm) {
		this.solvingAlgorithm = solvingAlgorithm;
	}

	
	/**
	 * This method will return the generate algorithm
	 * @return String
	 */
	public String getGenerateAlgorithm() {
		return generateAlgorithm;
	}


	/**
	 * This method will set generate algorithm.
	 * @param maxThreads - The generate algorithm.
	 */
	public void setGenerateAlgorithm(String generateAlgorithm) {
		this.generateAlgorithm = generateAlgorithm;
	}

	
	/**
	 * This method will return the max threads
	 * @return String
	 */
	public String getCachingPath() {
		return cachingPath;
	}


	/**
	 * This method will set caching path.
	 * @param maxThreads - The caching path.
	 */
	public void setCachingPath(String cachingPath) {
		this.cachingPath = cachingPath;
	}

	/**
	 * This method will return the view type.
	 * @return String
	 */
	public String getView() {
		return view;
	}

	/**
	 * This method will set the view type.
	 * @param view - The view type.
	 */
	public void setView(String view) {
		this.view = view;
	}
	
	
}
