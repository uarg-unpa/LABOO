package punto6;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Source;
import javax.xml.transform.Result;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class generadorDOM {
	
	private Document docXML;
	
	public generadorDOM () throws Exception {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();
		docXML = builder.newDocument();
	}
	
	public void generarDocumento() {
		Element alumnos = docXML.createElement("alumnos");
		docXML.appendChild(alumnos);
		
		// primer alumno
		Element alumno = docXML.createElement("alumno");
		alumnos.appendChild(alumno);
		alumno.setAttribute("codigo", "C001");
		Element nombre = docXML.createElement("nombre");
		alumno.appendChild(nombre);
		nombre.appendChild(docXML.createTextNode("Cintia"));
		Element apellido = docXML.createElement("apellido");
		alumno.appendChild(apellido);
		apellido.appendChild(docXML.createTextNode("Hernandez"));
	
		// segundo alumno
		alumno = docXML.createElement("alumno");
		alumnos.appendChild(alumno);
		alumno.setAttribute("codigo", "C002");
		nombre = docXML.createElement("nombre");
		alumno.appendChild(nombre);
		nombre.appendChild(docXML.createTextNode("Hugo"));
		apellido = docXML.createElement("apellido");
		alumno.appendChild(apellido);
		apellido.appendChild(docXML.createTextNode("Frey"));
		
		// tercer alumno
		alumno = docXML.createElement("alumno");
		alumnos.appendChild(alumno);
		alumno.setAttribute("codigo", "C003");
		nombre = docXML.createElement("nombre");
		alumno.appendChild(nombre);
		nombre.appendChild(docXML.createTextNode("Fabricio"));
		apellido = docXML.createElement("apellido");
		alumno.appendChild(apellido);
		apellido.appendChild(docXML.createTextNode("Nuniez"));
	}
	
	public void generarXML() throws Exception {
		Source origen = new DOMSource(docXML);
		File ruta = new File("./alumnos.xml");
		FileWriter fw = new FileWriter(ruta);
		PrintWriter pw = new PrintWriter(fw);
		Result resultado = new StreamResult(pw);
		
		// genera el XML
		TransformerFactory factoria = TransformerFactory.newInstance();
		Transformer transfomer = factoria.newTransformer();
		transfomer.transform(origen, resultado);
	}

}