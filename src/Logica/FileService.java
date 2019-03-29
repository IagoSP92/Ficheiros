package Logica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Datos.FileUtils;
import Datos.model.NegObj;

public class FileService {//	2: //non gardan en disco, tood e en memoeria -> non tgen file por ningun lado

	
//	obxetonegocio get (string  pseudopk)
//	
//	list obxetonegocio/mapa    getBy ( par1, par2 ,... )
//	
//	add (obxetonegocio o)
	
	public static NegObj get (String pseudoPK, String path)throws IOException {
		
		List<NegObj> lista = new ArrayList<NegObj>();		
		lista = FileUtils.abrir(path);
		
		for(NegObj o:lista) {			
			if(o.getEvent().getDescription()== pseudoPK) {
				return o;
			}						
		}
		return null;		
	}
	
	public static List<NegObj> getBy(String file, String client, String eventTitle){
		
		List<NegObj> lista = new ArrayList<NegObj>();
		List<NegObj> lista2 = new ArrayList<NegObj>();
		
		try {
			lista = FileUtils.abrir(file);
			for(NegObj o: lista) {
				if( o.getPerson().getName().toString()== client &&
					o.getEvent().getTitle().toString()== eventTitle	) {
					lista2.add(o);
				}
			}
			return lista2;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void add (String ficheiro, NegObj elemento, Boolean crear) {
		
		try {
			FileUtils.add (ficheiro , elemento, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
