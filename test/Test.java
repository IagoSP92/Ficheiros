import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Datos.FileUtils;
import Datos.model.Event;
import Datos.model.NegObj;
import Datos.model.Person;
import Logica.FileService;

public class Test {

	public static void main(String[] args) throws IOException {

		String path = "C://Users/Alumno/Documents/OneHourParentTeacherConferenceSampleImportFile.csv";

		List<NegObj> lista = new ArrayList<NegObj>();

		lista = testAbrir(lista, path);

		path = "C://Users/Alumno/Documents/creado.csv";

		testGuardar(lista, path);
		testAdd(path);
		
		testSvc(path);

	}


	public static List<NegObj> testAbrir (List<NegObj> lista, String path) {

		try {
			lista = FileUtils.abrir(path);
			for(NegObj s:lista) {
				System.out.println("Event title: "+s.getEvent().getTitle());
				System.out.println("Event description: "+s.getEvent().getDescription());				
				System.out.println("Person name: "+s.getPerson().getName());
				System.out.println("Person Phone: "+s.getPerson().getPhone());
				System.out.println("Person Email: "+s.getPerson().getEmail());
				System.out.println();
			}
			return lista;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void testGuardar (List<NegObj> lista, String path) {

		try {
			FileUtils.guardar (lista, path , true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 


	public static void testAdd (String path) {

		NegObj negobj = new NegObj();
		Event event = new Event();
		Person person = new Person();


		event.setTitle("Titletest");
		event.setDescription("Descriptiontest");
		negobj.setEvent(event);
		person.setName("Nametest");
		person.setEmail("Emailtest");
		person.setPhone("Phonetest");
		negobj.setPerson(person);
		
		try {
			FileUtils.add (path , negobj, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testSvc (String path) throws IOException {
		
		NegObj negobj = new NegObj();
		Event event = new Event();
		Person person = new Person();
		event.setTitle("Titletest2");
		event.setDescription("Descriptiontest2");
		negobj.setEvent(event);
		person.setName("Nametest2");
		person.setEmail("Emailtest2");
		person.setPhone("Phonetest2");
		negobj.setPerson(person);
		
		FileService.add( path , negobj, true);
		
		negobj= new NegObj();
		negobj= FileService.get ("Descriptiontest2", path);
		
		System.out.println("BUSCO PK");
		System.out.println("Event title: "+negobj.getEvent().getTitle());
		System.out.println("Event description: "+negobj.getEvent().getDescription());				
		System.out.println("Person name: "+negobj.getPerson().getName());
		System.out.println("Person Phone: "+negobj.getPerson().getPhone());
		System.out.println("Person Email: "+negobj.getPerson().getEmail());
		System.out.println();
		
		
		FileService.getBy (path, "Nametest2","Titletest2");
		
		System.out.println("BUSCO 2");
		System.out.println("Event title: "+negobj.getEvent().getTitle());
		System.out.println("Event description: "+negobj.getEvent().getDescription());				
		System.out.println("Person name: "+negobj.getPerson().getName());
		System.out.println("Person Phone: "+negobj.getPerson().getPhone());
		System.out.println("Person Email: "+negobj.getPerson().getEmail());
		System.out.println();
		
	}

}
