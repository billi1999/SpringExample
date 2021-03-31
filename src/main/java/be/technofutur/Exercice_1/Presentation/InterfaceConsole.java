package be.technofutur.Exercice_1.Presentation;

import be.technofutur.Exercice_1.Exceptions.ElementAlreadyExistsException;
import be.technofutur.Exercice_1.Exceptions.ElementNotFoundException;
import be.technofutur.Exercice_1.Service.CrudService;
import be.technofutur.Exercice_1.dto.SectionDTO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
@Profile("console")
public class InterfaceConsole implements InitializingBean {
    private final Scanner sc;
    private CrudService<SectionDTO, Integer> service;

    public InterfaceConsole(Scanner sc, CrudService service) {
        this.sc = sc;
        this.service = service;
    }

    public void start(){
        int choix=0;
        while (choix!=6){
            showMenu();
            choix=getChoix();
            mapChoix(choix);
        }
    }
    public void showMenu(){
        System.out.println("1 - Ajouter une section");
        System.out.println("2 - Afficher toute les sections");
        System.out.println("3 - Afficher une section");
        System.out.println("4 - Supprimer une section");
        System.out.println("5 - Mettre à jour");
        System.out.println("6 - Quitter");

    }
    public int getChoix(){
        int choix=-1;
        System.out.println("Veuillez entrer un choix :");
        try {
            choix= sc.nextInt();
        }catch (InputMismatchException e){
            //System.out.println("Entrée invalide");
            sc.nextLine();
        }

        return choix;
    }
    public void mapChoix(int choix){

        switch (choix){
            case 1:
                addSection();
                break;
            case 2:
                getAllSection();
                break;
            case 3:
                getOneSection();
                break;
            case 4:
                deleteSection();
                break;
            case 5:
                updateSection();
                break;
            case 6:
                System.out.println("aurevoir");
                break;
            default:
                System.out.println("Choix invalide");

                break;

        }

    }

    private void getAllSection() {
        service.getAll().forEach(System.out::println);
    }

    public void addSection(){
        //Obligé de faire ça car la bdd dbslide n'est pas relationelle
        //Pas de PK/FK/AUTO-INCREMENT
        try {
            System.out.println("Entrer la section_id :");
            int section_id = sc.nextInt();
            sc.nextLine();
            System.out.println("Entrer la valeur de la section (String):");
            String section_name = sc.nextLine();
            System.out.println("Entrer la valeur du délégé (int):");
            int delegate_id = sc.nextInt();

            SectionDTO s = SectionDTO.builder()
                    .id(section_id)
                    .delegate_id(delegate_id)
                    .section_name(section_name)
                    .build();

            service.addOne(s);
        }catch (InputMismatchException e){
            sc.nextLine();
            System.out.println("Entrée invalide");
        } catch (ElementAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
    public void deleteSection(){
        System.out.println("Entrer un id à supprimer");

        try {
            int response=sc.nextInt();
            service.delete(response);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }catch (InputMismatchException e){
            System.out.println("Mauvaise entrée");
            sc.nextLine();
        }

    }
    public void getOneSection(){
        System.out.println("Entrer un ID :");
            try {
                int id = sc.nextInt();
                System.out.println(service.getOne(id));

            }catch(InputMismatchException e){
                System.out.println("Mauvaise entrée");
                sc.nextLine();
            }

            catch (ElementNotFoundException e) {
                System.out.println(e.getMessage());
            }

        System.out.println("Entrer un id à retrouver dans Section :");
        int response= sc.nextInt();
        SectionDTO sectionDTO= null;
        try {
            sectionDTO = service.getOne(response);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(sectionDTO);

    }
    public void updateSection(){
        try {
            System.out.println("Entrer la section_id :");
            int section_id = sc.nextInt();
            sc.nextLine();

            SectionDTO old=service.getOne(section_id);

            System.out.println("Entrer la valeur ["+old.getSection_name()+"] de la section (String):");
            String section_name = sc.nextLine();
            System.out.println("Entrer la valeur ["+old.getDelegate_id()+"] du délégé (int):");
            int delegate_id = sc.nextInt();

            SectionDTO s = SectionDTO.builder()
                    .id(section_id)
                    .delegate_id(delegate_id)
                    .section_name(section_name)
                    .build();

            service.update(section_id,s);
        }catch (InputMismatchException e){
            sc.nextLine();
            System.out.println("Entrée invalide");
        } catch (ElementNotFoundException e){
            System.out.println("Pas trouvé");
        }
        /*getAllSection();
        System.out.println("Entrer l'ID de la section à modifier");
        int section_id= sc.nextInt();
        sc.nextLine();
        System.out.println("Entrer le nouveau section_name");
        String section_name=sc.nextLine();
        System.out.println("Entrer le nouveau delegate_id");
        int delegate_id=sc.nextInt();
        SectionDTO sectionDTO=SectionDTO.builder()
                .section_id(section_id)
                .section_name(section_name)
                .delegate_id(delegate_id)
                .build();
        service.update(section_id,sectionDTO);*/
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }
}
