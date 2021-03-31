package be.technofutur.Exercice_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Exercice1Application {

	public static void main(String[] args) {
		//Objet qui contient tout les beans

		//Est l'application context de l'application
		//Ce qui va relier toute les beans

		//ApplicationContext ctx=SpringApplication.run(Exercice1Application.class, args);

		//Va récupérer les beans d'une certaine classe
		//SectionServiceImpl service = ctx.getBean(SectionServiceImpl.class);

		/* TESTING A L'ARRACHE */
		/*SectionDTO dto = SectionDTO.builder()
				.delegate_id(1)
				.section_name("test")
				.section_id(1111)
				.build();*/
		//ADD ONE
			/*try {
				service.addOne(dto);
			} catch (ElementAlreadyExistsException e) {
				e.printStackTrace();
			}*/
		//FIND ONE
		/*try {
			service.getOne(1111);
		}catch (ElementNotFoundException e){
			System.err.println(e);
		}*/
		//GET ALL

		/*service.getAll().forEach(System.out::println);*/

		//DELETE

		/*try {
			service.update(dto.getSection_id(),dto);
		}catch (ElementNotFoundException e){
			System.out.println(e);
		}*/

		SpringApplication.run(Exercice1Application.class, args);
	}

}
