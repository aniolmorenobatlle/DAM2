package Part2;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.io.IOException;

public class Ex2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		

        System.out.println("0. Sortir");
        System.out.println("1. Crear departament");
        System.out.println("2. Crear directori");
        System.out.println("3. Crear empleat");
        System.out.println("4. Llistar empleats");
        System.out.println("5. Buscar empleat per codi");
        System.out.println("6. Modificar dades de l'empleat/a");
        System.out.println("7. Eliminar empleat");
        
       
        
		System.out.print("Selecciona l'opció que vols utilitzar: ");
        int option = sc.nextInt();
        System.out.println();
        
		switch(option) {
			case 0:
                System.out.println("Sortint...");
                break;
                
            case 1:
            	createDept();
				break;
				
			case 2:
				createDirectory();
				break;
				
			case 3:
				createEmployee();
				break;
				
			case 4:
				listEmployees();
				break;
			
			case 5:
				searchEmployeeByCode();
				break;
				
			case 6:
				modifyEmployee();
				break;
				
			case 7:
				deleteEmployee();
				break;
		}
		
		sc.close();
		
	}
	
	
    public static void createDept() {    
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.print("Introdueix el nom del departament: ");
    	String departmentName = sc.nextLine();
    
    	
    	String path = "src/Part2/" + departmentName;
    	
    	File directory = new File(path);
    	
		if (!directory.exists() && !departmentName.contains(" ")) {
			boolean created = directory.mkdir();
			
			if (created) {
				System.out.println("El directori s'ha creat correctament.");
			} else {
				System.out.println("Error en crear el departament!!!");
			}
			
		} else if (directory.exists()) {
			System.out.println("El directori ja existeix.");
		} else if (departmentName.contains(" ")) {
            System.out.println("El nom del departament no pot contenir espais!!!");
        }
		
		sc.close();
    	
    }
    
    public static void createDirectory() {
    	Scanner sc = new Scanner(System.in);
    	
    	File[] departments = listDepartments();
    	
    	System.out.println();
    	
    	if (departments != null && departments.length > 0 ) {
    		System.out.print("Selecciona el departament on vols crear el directori: ");
    		int option = sc.nextInt();
    		
    		if (option > 0 && option <= departments.length) {
    			String path = departments[option-1].getName();
    			
    			System.out.println("Has seleccionat el departament: " + path);
    			
    			System.out.println();
    			
    			System.out.print("Introdueix el nom del directori: ");
    			String directoryName = sc.next();
    			
    			File directory = new File("src/Part2/" + path + "/" + directoryName);
    			
				if (!directory.exists()) {
					boolean created = directory.mkdir();
					
					if (created) {
						System.out.println("El directori s'ha creat a la ruta: " + path + "/" + directoryName);
						
						try {
							File file = new File(directory, "treballadors.txt");
							file.createNewFile();
							
						} catch (IOException e) {
							System.out.println("Error en crear el fitxer!!!");
							e.printStackTrace();
						}
						
					} else {
						System.out.println("Error en crear el directori!!!");
						
					}
					
				} else {
					System.out.println("El directori ja existeix.");
					
				}
    		}
    	}
    	
    	sc.close();
    	
    }
    
	public static void createEmployee() {
	
		Scanner sc = new Scanner(System.in);
    	
    	File[] departments = listDepartments();
    	
    	System.out.println();
    	
    	if (departments != null && departments.length > 0 ) {
    		System.out.print("Selecciona el departament on vols afegir el treballador: ");
    		int option = sc.nextInt();
    		
    		if (option > 0 && option <= departments.length) {
    			String path = departments[option-1].getName();
    			
    			System.out.println("Has seleccionat el departament: " + path);
    			
    			System.out.println();
    			
    			System.out.println("Introdueix les dades de l'empleat: ");
    			
    			System.out.println();
    			
    			System.out.print("Nom: ");
    			String name = sc.next();
    			
    			System.out.print("Codi treballador: ");
    			int code = sc.nextInt();
    			
    			System.out.print("Càrrec: ");
    			String position = sc.next();
    			
    			System.out.print("Salari: ");
    			int salary = sc.nextInt();
    			
    			String resume = name + ", " + code +  ", " + position + ", " + salary;    			
    			
    			try {
    				String file = "src/Part2/" + path + "/treballadors.txt";
    				
    				FileWriter fw = new FileWriter(file, true);
    				fw.write(resume + "\n");
    				
    				fw.close();
    				
    			    
    			    System.out.println("Empleat creat correctament.");
    				
    			} catch (IOException e) {
					System.out.println("Error en crear el fitxer!!!");
					e.printStackTrace();
    			}
    		
    		}
    	}
		
    	
    	sc.close();
	}

    public static void listEmployees() {
    	Scanner sc = new Scanner(System.in);
    	
    	File[] departments = listDepartments();
    	
    	System.out.println();
    	
    	if (departments != null && departments.length > 0 ) {
    		System.out.print("Selecciona el departament on vols veure les dades del/s treballador: ");
    		int option = sc.nextInt();
    		
    		if (option > 0 && option <= departments.length) {
    			String path = departments[option-1].getName();
    			
    			System.out.println("Has seleccionat el departament: " + path);
    			
    			System.out.println();
    			
    			try (BufferedReader br = new BufferedReader(new FileReader("src/Part2/" + path + "/treballadors.txt"))) {
    				String line;
    				
    				while ((line = br.readLine()) != null) {
    					System.out.println(line);
    				}
    				    				
    			} catch (IOException e) {
    				System.out.println("Error en llegir el fitxer!!!");
    				e.printStackTrace();
    			}
    			
    			System.out.println();
    		
    		}
    	}
    	
    	sc.close();
    }

    public static void searchEmployeeByCode() {
    	Scanner sc = new Scanner(System.in);
    	
    	File[] departments = listDepartments();
    	
    	System.out.println();
    	
    	if (departments != null && departments.length > 0 ) {
    		System.out.print("Selecciona el departament on vols veure les dades del pel seu codi treballador: ");
    		int option = sc.nextInt();
    		
    		if (option > 0 && option <= departments.length) {
    			String path = departments[option-1].getName();
    			
    			System.out.println("Has seleccionat el departament: " + path);
    			
    			System.out.println();
    			
    			System.out.print("Introdueix el codi del treballador: ");
    			int code = sc.nextInt();
    			
    			// Trobar el treballador pel codi
				try (RandomAccessFile raf = new RandomAccessFile("src/Part2/" + path + "/treballadors.txt", "r")) {
					String info;

					while ((info = raf.readLine()) != null) {
						String[] data = info.split(",");

						if (Integer.parseInt(data[1].trim()) == code) {
							System.out.println("Informació del treballador seleccionat: ");
							System.out.println();
							System.out.println("--------------------------------------------------");
							System.out.println(info);
							System.out.println("--------------------------------------------------");
							System.out.println();
							
							break;
							
						} else if (raf.getFilePointer() == raf.length()) {
							System.out.println("No s'ha trobat cap treballador amb aquest codi.");
						}
					}

				} catch (IOException e) {
					System.out.println("Error en llegir el fitxer!!!");
					e.printStackTrace();
				}
    			
    		}
    	}
    	
    	sc.close();
    }

    public static void modifyEmployee() {
    	Scanner sc = new Scanner(System.in);
    	
    	File[] departments = listDepartments();
    	
    	System.out.println();
    	
    	if (departments != null && departments.length > 0 ) {
    		System.out.print("Selecciona el departament on vols editar les dades d'un/a treballador/a: ");
    		int option = sc.nextInt();
    		
    		if (option > 0 && option <= departments.length) {
    			String path = departments[option-1].getName();
    			
    			System.out.println("Has seleccionat el departament: " + path);
    			
    			System.out.println();
    			
    			System.out.print("Introdueix el codi del treballador: ");
    			int code = sc.nextInt();
    			
    			// Trobar el treballador pel codi
				try (RandomAccessFile raf = new RandomAccessFile("src/Part2/" + path + "/treballadors.txt", "r")) {
					String info;

					while ((info = raf.readLine()) != null) {
						String[] data = info.split(",");

						if (Integer.parseInt(data[1].trim()) == code) {
							System.out.println("Informació del treballador seleccionat: ");
							System.out.println();
							System.out.println("--------------------------------------------------");
							System.out.println(info);
							System.out.println("--------------------------------------------------");
							System.out.println();
							
							
							System.out.println("Introdueix les noves dades de l'empleat: ");
							System.out.println();

							System.out.print("Nom: ");
							String name = sc.next();

							System.out.print("Càrrec: ");
							String position = sc.next();

							System.out.print("Salari: ");
							int salary = sc.nextInt();

							String resume = name + ", " + code + ", " + position + ", " + salary;

							raf.seek(raf.getFilePointer() - info.length() - 2);
							raf.writeBytes(resume + "\n");
							
							System.out.println("Les dades de l'empleat s'han modificat correctament.");
							
							break;
							
						} else if (raf.getFilePointer() == raf.length()) {
							System.out.println("No s'ha trobat cap treballador amb aquest codi.");
						}
					}

				} catch (IOException e) {
					System.out.println("Error en llegir el fitxer!!!");
					e.printStackTrace();
				}
    			
    		}
    	}
    	
    	sc.close();
    }

    public static void deleteEmployee() {
        Scanner sc = new Scanner(System.in);

        File[] departments = listDepartments();

        System.out.println();

        if (departments != null && departments.length > 0) {
            System.out.print("Selecciona el departament on vols eliminar el treballador/a: ");
            int option = sc.nextInt();

            if (option > 0 && option <= departments.length) {
                String path = departments[option - 1].getName();

                System.out.println("Has seleccionat el departament: " + path);

                System.out.println();

                System.out.print("Introdueix el codi del treballador: ");
                int code = sc.nextInt();

                File originalFile = new File("src/Part2/" + path + "/treballadors.txt");
                File tempFile = new File("src/Part2/" + path + "/temp.txt");

                // Crear un fitxer temporal per copiar les linies que no s'han d'eliminar
                try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                    String line;
                    boolean found = false;

                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(",");

                        // Comprovar si el codi coincideix
                        if (Integer.parseInt(data[1].trim()) == code) {
                            System.out.println("Informació del treballador seleccionat: ");
                            System.out.println();
                            System.out.println("--------------------------------------------------");
                            System.out.println(line);
                            System.out.println("--------------------------------------------------");
                            System.out.println();

                            System.out.print("Estàs segur que vols eliminar aquest treballador? (S/N): ");
                            String answer = sc.next();

                            if (answer.equalsIgnoreCase("S")) {
                                System.out.println("L'empleat s'ha eliminat correctament.");
                                found = true;
                                
                            } else {
                                System.out.println("No s'ha eliminat cap treballador.");
                                // Escriure la línia de nou si no es vol eliminar
                                writer.write(line);
                                writer.newLine();
                            }
                        } else {
                            // Si el codi no coincideix, escriure la línia de nou
                            writer.write(line);
                            writer.newLine();
                        }
                    }

                    if (!found) {
                        System.out.println("No s'ha trobat cap treballador amb aquest codi.");
                    }

                } catch (IOException e) {
                    System.out.println("Error en llegir o escriure el fitxer!!!");
                    e.printStackTrace();
                }

                // Eliminar fitxer original i canviar el nom del fitxer temporal
                if (originalFile.delete()) {
                    tempFile.renameTo(originalFile);
                    
                } else {
                    System.out.println("Error en eliminar el fitxer original.");
                }
            }
        }

        sc.close();
    }

    
    
    
    
    public static File[] listDepartments() {
        String path = "src/Part2";
        File directory = new File(path);

        // Comprovar si el directori existeix i si es un directori
        if (directory.exists() && directory.isDirectory()) {
            // Llistar els fitxers del directori
            File[] files = directory.listFiles();

            // Mirem si la carpeta es buida o no
            if (files.length == 0) {
                System.out.println("Encara no hi ha cap departament creat.");
                return null;
                
            } else {
                System.out.println("Departaments creats:");

                // Nomes retornem els departaments
                int index = 1;
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        System.out.println("    " + index + ". " + files[i].getName());
                        index++;
                    }
                }
                
                return files;
                
            }
        }

        return null;
	}
    

}
