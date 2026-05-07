package punto4;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class GeneradorHTML {
    
    private File xmlEntrada;
    private File xslPlantilla;
    private File htmlSalida;

    public GeneradorHTML() {
        // Define las rutas absolutas de los archivos
        String rutaDirectorio = System.getProperty("user.home") + "\\Documents\\LABOO\\tp2\\punto4\\";
        String rutaArchivoXML = rutaDirectorio + File.separator + "seleccion.xml"+ File.separator;
        this.xmlEntrada = new File(rutaArchivoXML);
        this.xslPlantilla = new File(rutaDirectorio + File.separator + "seleccion.xsl");
        this.htmlSalida = new File(rutaDirectorio + File.separator + "salida_seleccion.html");
    }

    public void realizarTransformacion() throws Exception {
        // 1. Prepara las fuentes
        StreamSource fuenteXML = new StreamSource(xmlEntrada);
        StreamSource fuenteXSL = new StreamSource(xslPlantilla);
        
        // 2. Prepara el destino (el output)
        StreamResult resultado = new StreamResult(htmlSalida);

        // 3. Usa la factoría para crear el transformador
        TransformerFactory factoria = TransformerFactory.newInstance();
        Transformer transformador = factoria.newTransformer(fuenteXSL);

        // 4. Ejecuta la transformación
        transformador.transform(fuenteXML, resultado);
    }
}