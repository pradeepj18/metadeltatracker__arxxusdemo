package metadataResources;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlDocumetRes {
	public static File xmlDocEnd(Document doc, Element xmlroot) throws TransformerConfigurationException  {
		File targetFile =null;
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("types");
		if (nList.getLength() != 0) {
			/*Attr attrType = doc.createAttribute("xmlns");
			attrType.setValue("http://soap.sforce.com/2006/04/metadata");
			xmlroot.setAttributeNode(attrType);*/
			try {
			targetFile = new File("E:\\IPL2018\\metadataTrack\\metadataxml\\metadata_"
					+ (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml");
			StreamResult result = new StreamResult(targetFile);
			transformer.transform(source, result);
			return targetFile;
			}
			catch(Exception e)
			{
				System.out.println("File Path Not Found - "+e);
				return targetFile;
			}
		}
		return targetFile;
	}

}
