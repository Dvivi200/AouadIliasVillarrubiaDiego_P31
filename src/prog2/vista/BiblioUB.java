/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2.vista;

import java.util.List;
import java.util.Scanner;
import prog2.adaptador.Adaptador;

/**
 *
 * @author dortiz
 */
public class BiblioUB {
    
    // Declarem les constants del menu principal
    static private enum OpcionsMenuPrincipal {
        MENU_PRINCIPAL_EXEMPLARS,
        MENU_PRINCIPAL_USUARIS,
        MENU_PRINCIPAL_PRESTECS,
        MENU_PRINCIPAL_SAVE,
        MENU_PRINCIPAL_LOAD,
        MENU_PRINCIPAL_EXIT};
    
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuPrincipal={"Gestió Exemplars",
                                               "Gestió Usuaris",
                                               "Gestió Prestecs",
                                               "Guardar Dades",
                                               "Recuperar Dades",
                                               "Sortir"};

    static private enum OpcionsMenuGestioExemplars {
        MENU_GESTIO_EXEMPLARS_ADD,
        MENU_GESTIO_EXEMPLARS_VIEW,
        MENU_GESTIO_EXEMPLARS_EXIT
    };
    
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuGestioExemplars ={"Afegir Exemplar",
                                                      "Visualitzar Exemplars",
                                                      "Sortir"};

    static private enum OpcionsMenuGestioClients {
        MENU_GESTIO_USUARIS_ADD,
        MENU_GESTIO_USUARIS_VIEW,
        MENU_GESTIO_USUARIS_EXIT
    };
    
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuGestioUsuaris ={"Afegir Usuari",
                                                    "Visualitzar Usuaris",
                                                    "Sortir"};

    static private enum OpcionsMenuGestioPrestecs {
        MENU_GESTIO_PRESTECS_ADD,
        MENU_GESTIO_PRESTECS_REMOVE,
        MENU_GESTIO_PRESTECS_VIEW,
        MENU_GESTIO_PRESTECS_VIEW_URG,
        MENU_GESTIO_PRESTECS_EXIT
    };
    
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuGestioPrestecs ={"Afegir Prestec",
                                                     "Retornar Prestec",
                                                     "Visualitzar Prestecs",
                                                     "Visualitzar Prestecs no Retornats",
                                                     "Sortir"};

    
    /** Adaptador de l'aplicació */
    private Adaptador adaptador;
    
    /* Constructor*/
    public BiblioUB() {
        adaptador = new Adaptador();
    }
     
    public void gestioBiblioUB() {
        // Creem un objecte per llegir des del teclat
        Scanner sc = new Scanner(System.in);
        
        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("Menu principal", OpcionsMenuPrincipal.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuPrincipal);
        
        OpcionsMenuPrincipal opcio;
        do {
            // Mostrem les opcions del menú i demanem una opció
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries per a la opció triada
            switch(opcio) {
                case MENU_PRINCIPAL_EXEMPLARS:
                    // Mostra el menú per a la gestió d'exemplars
                    menuGestioExemplars(sc);
                    break;

                case MENU_PRINCIPAL_USUARIS:
                    // Mostra el menú per a la gestió d'usuaris
                    menuGestioUsuaris(sc);
                    break;

                case MENU_PRINCIPAL_PRESTECS:
                    // Mostra el menú per a la gestió de prestecs
                    menuGestioPrestecs(sc);
                    break;

                case MENU_PRINCIPAL_SAVE:
                    // Guardar dades
                    String dstFile = getFilePath(sc,false); // Obtenir el fitxer de sortida
                    if(dstFile != null) {
                        // Guardar les dades al fitxer triat
                        try {
                             this.adaptador.guardaDades(dstFile);
                             System.err.println("Dades guardades");
                        } catch (BiblioException ex) {
                            System.out.println("Error guardant les dades: " + ex.getMessage());
                        }
                    }                   
                    break;
                case MENU_PRINCIPAL_LOAD:
                    // Carregar dades                   
                    String srcFile = getFilePath(sc,false); // Obtenir el fitxer d'entrada
                    if(srcFile != null) {
                        // Carregar les dades del fitxer triat
                        try {
                             this.adaptador.carregaDades(srcFile);
                             System.err.println("Dades carregades");
                        } catch(BiblioException ex) {
                            System.out.println("Error carregant les dades." + ex.getMessage());
                        }
                    }     
                    break;
                case MENU_PRINCIPAL_EXIT:
                    // Sortir      1
                    System.err.println("Sortint de l'aplicació...");
                    break;
                default:
                    System.err.println("Opcio no valida, torna-la a posar");
                    break;
            }
        } while(opcio != OpcionsMenuPrincipal.MENU_PRINCIPAL_EXIT);
    }
    
    private void menuGestioExemplars(Scanner sc) {
        Menu<OpcionsMenuGestioExemplars> menu = new Menu<>("Gestió d'exemplars", OpcionsMenuGestioExemplars.values());
        menu.setDescripcions(descMenuGestioExemplars);

        OpcionsMenuGestioExemplars opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);
            switch(opcio) {
                case MENU_GESTIO_EXEMPLARS_ADD:
                    afegirExemplar(sc);
                    break;
                case MENU_GESTIO_EXEMPLARS_VIEW:
                    for(String ex : adaptador.recuperaExemplars()){
                        System.out.println(ex);
                    }
                    break;
                case MENU_GESTIO_EXEMPLARS_EXIT:
                    System.err.println("Sortint d'exemplars...");
                    break;
                default:
                    System.err.println("Opcio no valida, torna-la a posar");
                    break;
            }
        } while(opcio != OpcionsMenuGestioExemplars.MENU_GESTIO_EXEMPLARS_EXIT);
    }
    
    /**
     * Afegir un nou article
     * @param sc
     */
    
    private void afegirExemplar(Scanner sc){
        System.out.println("Introdueix l'id del exemplar:");
        String id = sc.nextLine();
        System.out.println("Introdueix el títol del exemplar:");
        String titol = sc.nextLine();
        System.out.println("Introdueix l'autor del exemplar:");
        String autor = sc.nextLine();
        System.out.println("L'exemplar admet prèstecs llargs?(true/false)");
        boolean admetPrestecLlarg = sc.nextBoolean();
        sc.nextLine();
        try {
            adaptador.afegirExemplar(id, titol, autor, admetPrestecLlarg);
            System.out.println("Exemplar afegit correctament.");
        } catch (BiblioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void menuGestioUsuaris(Scanner sc) {
        Menu<OpcionsMenuGestioClients> menu = new Menu<>("Gestió d'usuaris", OpcionsMenuGestioClients.values());
        menu.setDescripcions(descMenuGestioUsuaris);

        OpcionsMenuGestioClients opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            switch(opcio) {
                case MENU_GESTIO_USUARIS_ADD:
                    afegirUsuari(sc);
                    break;
                case MENU_GESTIO_USUARIS_VIEW:
                    for(String us : adaptador.recuperaUsuaris()){
                        System.out.println(us);
                    }
                    break;
                case MENU_GESTIO_USUARIS_EXIT:
                    System.err.println("Sortint d'usuaris...");
                    break;
                default:
                    System.err.println("Opcio no valida, torna-la a posar");
                    break;
            }
        } while (opcio != OpcionsMenuGestioClients.MENU_GESTIO_USUARIS_EXIT);
    }
    
    /**
     * Afegir un nou usuari
     * @param sc
     */
    
    private void afegirUsuari(Scanner sc){
        System.out.println("Introdueix l'id del usuari:");
        String id = sc.nextLine();
        System.out.println("Introdueix el nom del usuari:");
        String nom = sc.nextLine();
        System.out.println("Introdueix l'adreça del usuari:");
        String adreca = sc.nextLine();
        System.out.println("Es l'usuari un estudiant?(true/false)");
        boolean esEstudiant = sc.nextBoolean();
        sc.nextLine();
        try {
            adaptador.afegirUsuari(id, nom, adreca, esEstudiant);
            System.out.println("Usuari afegit correctament.");
        } catch (BiblioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void menuGestioPrestecs(Scanner sc) {
        Menu<OpcionsMenuGestioPrestecs> menu = new Menu<>("Gestió de prestecs", OpcionsMenuGestioPrestecs.values());
        menu.setDescripcions(descMenuGestioPrestecs);
        OpcionsMenuGestioPrestecs opcio;

        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);
            switch(opcio) {
                case MENU_GESTIO_PRESTECS_ADD:
                    afegirPrestec(sc);
                    break;
                case MENU_GESTIO_PRESTECS_REMOVE:
                    cancelarPrestec(sc);
                    break;
                case MENU_GESTIO_PRESTECS_VIEW:
                    for(String pr : adaptador.recuperaPrestecs()){
                        System.out.println(pr);
                    }
                    break;
                case MENU_GESTIO_PRESTECS_VIEW_URG:
                    for(String pr : adaptador.recuperaPrestecsNoRetornats()){
                        System.out.println(pr);
                    }
                    break;
                case MENU_GESTIO_PRESTECS_EXIT:
                    System.err.println("Sortint de prestecs...");
                    break;
                default:
                    System.err.println("Opcio no valida, torna-la a posar");
                    break;
            }
        } while (opcio != OpcionsMenuGestioPrestecs.MENU_GESTIO_PRESTECS_EXIT);
    }
    
    /**
     * Afegir un nou prestec
     * @param sc
     */
    
    private void afegirPrestec(Scanner sc){
        System.out.println("Introdueix la posicio del exemplar:");
        int exemplarPos = sc.nextInt();
        sc.nextLine();
        System.out.println("Introdueix la posicio del prestec:");
        int prestecPos = sc.nextInt();
        sc.nextLine();
        System.out.println("Vola agafar-ho a llarg termini?(true/false)");
        boolean esLlarg = sc.nextBoolean();
        sc.nextLine();
        try {
            adaptador.afegirPrestec(exemplarPos, prestecPos, esLlarg);
            System.out.println("Prestec afegit correctament.");
        } catch (BiblioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cancelarPrestec(Scanner sc){
        System.out.println("Introdueix la posicio del prestec:");
        int prestecPos = sc.nextInt();
        sc.nextLine();
        try {
            adaptador.retornarPrestec(prestecPos);
            System.out.println("Prestec retornat correctament.");
        } catch (BiblioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

     /**
     * Mostra una llista d'objectes
     * @param title Títol a posar com a capçalera
     * @param lines Llista d'objectes per mostrar
     */
    private void showList(String title, List<String> lines) {
        System.out.println("============================================");
        System.out.println(title);
        System.out.println("============================================");
        int i = 0;
        for(String l : lines) {
            System.out.println("\t[" + (i++) + "] " + l);
        }
        System.out.println("============================================");
    }


    /**
     * Demana el camí d'un fitxer
     * @param sc Objecte per a la lectura de dades de teclat
     * @param mustExist Exigeix que el fitxer existeixi (True) o no (False)
     * @return Ruta al fitxer entrada per l'usuari o null si s'ha cancelat
     */
    private String getFilePath(Scanner sc, boolean mustExist) {
        String filePath = null;

        if (mustExist) {
            // Mostrar el missatge demanant la entrada
            System.out.println("Entra ruta completa fitxer(o ENTER per ometre):");
            // Llegim la ruta del fitxer
            filePath = sc.nextLine();
            // Si la ruta està buida retornem un null
            if(filePath.isEmpty()) {
                return null;
            }
        }
        else {
            System.out.println("Entra el nom del fitxer a crear:");
            filePath = sc.nextLine();
            if(filePath.isEmpty()) {
                return null;
            }
        }

        return filePath;
    }

}
