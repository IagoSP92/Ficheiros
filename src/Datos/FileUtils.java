package Datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Datos.model.Event;
import Datos.model.NegObj;
import Datos.model.Person;

public class FileUtils {
	
	
	public static Boolean comprobarExistenciaFichero (String fichero)
			throws IOException {
		
		File toFile = new File(fichero);
		
		if(toFile.exists()) {
			return true;
		} else {
			return toFile.createNewFile();
		}
	}
	
	public static Boolean comprobarExistenciaDirectorio(String uploadPath) {
        // creates the directory if it does not exist       
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            if(!uploadDir.mkdirs()) {
            	return false;
            }
            return true;
        }
        return true;		
	}

	//	List<Ob negocioa> agrir (string f)
	//	
	//	guardar (list <obxetonegocio> data, string  f)
	//	
	//	opcional: add(fii f, obxetonegocio o)
	//


	public static List<NegObj> abrir (String file) throws IOException {

		File fromFile = new File(file);
		
		if(fromFile.exists()){

			List<NegObj> lista = new ArrayList<NegObj>();
			
			FileReader fr = new FileReader(fromFile);
			BufferedReader br = new BufferedReader(fr);

			String linea = br.readLine();
			char separador = ',';
			int count = 0;

			for (int i = 0; i < linea.length(); i++) {
				if (linea.charAt(i) == separador) {
					count++;
				}
			}			

			String[] objeto = new String[count+1];
			NegObj negobj = null;
			Event event = null;
			Person person = null;

			while ((linea= br.readLine())!=null) {
				objeto=linea.split(",");
				event= new Event();
				person= new Person();
				negobj= new NegObj();

				event.setTitle(objeto[4]);
				event.setDescription(objeto[5]);
				negobj.setEvent(event);
				person.setName(objeto[8]);
				person.setEmail(objeto[9]);
				person.setPhone(objeto[10]);
				negobj.setPerson(person);

				lista.add(negobj);
			}			

			br.close();
			fr.close();
			return lista;
		}

		return null;
	}

	

	
	public static void guardar (List<NegObj> lista, String ficheiro, Boolean crear) throws IOException {

		File toFile = new File(ficheiro);

		if(!toFile.exists() && crear){
			toFile.createNewFile();
		}

		FileWriter fw = new FileWriter(toFile);
		//BufferedWriter br = new BufferedWriter(fw);

		StringBuilder sb = new StringBuilder();
		sb.append("Event Title,Event Description,Contact,Contact Email,Contact Phone");

		for(NegObj o:lista) {
			sb.append("\n");				
			sb.append(o.getEvent().getTitle().toString());
			sb.append(",");
			sb.append(o.getEvent().getDescription().toString());
			sb.append(",");
			sb.append(o.getPerson().getName().toString());
			sb.append(",");
			sb.append(o.getPerson().getEmail().toString());
			sb.append(",");
			sb.append(o.getPerson().getPhone().toString());
		}
		String cadena = sb.toString();
		fw.write(cadena, 0, cadena.length());
		fw.close(); 
	}

	
	public static void add  (String ficheiro, NegObj elemento, Boolean crear) throws IOException {
		File toFile = new File(ficheiro);

		StringBuilder sb = new StringBuilder();
//		if(!toFile.exists() && crear){
//			toFile.createNewFile();
//			sb.append("Event Title,Event Description,Contact,Contact Email,Contact Phone");
//		}

		FileWriter fw = new FileWriter(toFile, true);
		//BufferedWriter br = new BufferedWriter(fw);

		sb.append("\n");				
		sb.append(elemento.getEvent().getTitle().toString());
		sb.append(",");
		sb.append(elemento.getEvent().getDescription().toString());
		sb.append(",");
		sb.append(elemento.getPerson().getName().toString());
		sb.append(",");
		sb.append(elemento.getPerson().getEmail().toString());
		sb.append(",");
		sb.append(elemento.getPerson().getPhone().toString());

		String cadena = sb.toString();

		fw.write(cadena, 0, cadena.length());
		fw.close();

	}


}
