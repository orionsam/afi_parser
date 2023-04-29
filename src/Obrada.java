
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Obrada {
	
	public static void main() {
		try {
			obradaE("test",true, true, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	Parsiranje pom = new Parsiranje();
	Uparivanja pomU = new Uparivanja();
	
	public static void obradaE(String filePath, boolean oblast, boolean autori, boolean kljucne) throws Exception{
		System.out.println(filePath);
		filePath = "C:\\Users\\irena.mitrovic\\rad\\afi\\test.xlsx";
		FileOutputStream out = new FileOutputStream(new File("C:\\Users\\irena.mitrovic\\rad\\afi\\testR.xlsx"));
		FileInputStream file = new FileInputStream(new File(filePath));
		XSSFWorkbook wb = new XSSFWorkbook(file); // ulazni excel
		XSSFWorkbook wbE = new XSSFWorkbook(); //izlazni excel
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFSheet sheetE = wbE.createSheet("Rezultati");
		
		int w=0; // iterator za izlaz
		
		Row rowE = sheetE.createRow(w++);
		
		 Cell cellE = rowE.createCell(0);
		 cellE.setCellValue("Br.");

		 cellE = rowE.createCell(1);
		 cellE.setCellValue("Ime");
		 
		 cellE = rowE.createCell(2);
		 cellE.setCellValue("S");
		 
		 cellE = rowE.createCell(3);
		 cellE.setCellValue("Prezime");
		 
		 cellE = rowE.createCell(4);
		 cellE.setCellValue("Institucija");
		 
		 cellE = rowE.createCell(5);
		 cellE.setCellValue("Drzava");
		 
		 cellE = rowE.createCell(6);
		 cellE.setCellValue("Prvi");
		 
		 cellE = rowE.createCell(7);
		 cellE.setCellValue("Br. autora");
		 
		 cellE = rowE.createCell(8);
		 cellE.setCellValue("Reprint");
		
		 cellE = rowE.createCell(9);
		 cellE.setCellValue("Godina");
		 
		 cellE = rowE.createCell(10);
		 cellE.setCellValue("Faktor");
		 
		 cellE = rowE.createCell(11);
		 cellE.setCellValue("Procenti");
		 
		Iterator<Row> rowI = sheet.iterator();
		System.out.println("petljam");
		rowI.next();

		 while (rowI.hasNext()) {
			 try {
				 Row row = rowI.next();
				
				 System.out.println("R " + (row.getRowNum()));
				 // izdvajanje autora iz colone AF
	             List<Autor> autoriOrg = pom.parsAutoraS(row.getCell(5).toString());
	             autoriOrg = pom.parsAutoraF(autoriOrg);
	             
	          // obrada reprint autora
				boolean imaRP = false;
				 Autor repAutor = new Autor();
				 
				 Cell cell = row.getCell(23);
				 if (cell == null) {
					// System.out.println("RP nema"); 
					 imaRP = false;
				 }else{
					 repAutor = pom.izvuciAutoraRP(row.getCell(23).toString());
					 imaRP = true;
					 System.out.println("RP: "+ repAutor.getIme() + " : " + repAutor.getSs() + " : "+ repAutor.getPrezime() + " inst: " 
							+ repAutor.institucija.naziv + repAutor.institucija.podnaziv1
							+ " drzava - " + repAutor.institucija.drzava );
				 }

                String naslov = row.getCell(8).toString();
//                System.out.println(naslov);
//	            System.out.println( row.getCell(22).toString());
	             
	             // uparivanje autora iz AF sa nizom autora iz C1 i povlacenje institucija koje su navedene
				cell = row.getCell(22);
				
				if (!(cell == null)){
		            pomU.upariAC1(autoriOrg, row.getCell(22).toString());
		            int i=0;// broji autore
		            int d=0; // broji duple autore
	
		            // obrada autora i institucija, broj autora i da li je prvi autor
		            for (Iterator<Autor> iterator = autoriOrg.iterator(); iterator.hasNext();) {
		            	
					Autor autor = (Autor) iterator.next();
					
//					System.out.println((i) +":: " + autor.getIme() + " : " + autor.getSs() + " : "+ autor.getPrezime());
						if (autor.institucija != null){
//								System.out.println(autor.getIme() + " : " + autor.getSs() + " : "+ autor.getPrezime() + " inst: " + autor.institucija.naziv
//										+ " drzava - " + autor.institucija.drzava );
								
								 rowE = sheetE.createRow(w++);
								
								 cellE = rowE.createCell(0);
								 cellE.setCellValue(row.getRowNum()+1);
	
								 cellE = rowE.createCell(1);
								 cellE.setCellValue(autor.getIme());
								 
								 cellE = rowE.createCell(2);
								 cellE.setCellValue(autor.getSs());
								 
								 cellE = rowE.createCell(3);
								 cellE.setCellValue(autor.getPrezime());
								 
								 cellE = rowE.createCell(4);
								 cellE.setCellValue(autor.institucija.naziv + ", " + autor.institucija.podnaziv1);
								 
								 cellE = rowE.createCell(5);
								 cellE.setCellValue(autor.institucija.drzava.trim());
								 
								 //za potrebe analize
								 cellE = rowE.createCell(14);
								 cellE.setCellValue(autor.institucija.naziv);
								 
								 cellE = rowE.createCell(15);
								 cellE.setCellValue(autor.institucija.podnaziv1);
								 
								 cellE = rowE.createCell(16);
								 cellE.setCellValue(autor.institucija.podnaziv2);
								 
								 cellE = rowE.createCell(17);
								 cellE.setCellValue(autor.institucija.podnaziv3);
								 
								 cellE = rowE.createCell(18);
								 cellE.setCellValue(autor.institucija.podnaziv4);
								
								 cellE = rowE.createCell(19);
								 cellE.setCellValue(autor.institucija.grad.trim());
								 
								 cellE = rowE.createCell(20);
								 cellE.setCellValue(autor.institucija.drzava.trim());

								 
								 cellE = rowE.createCell(6);
								 if ((i==0)&&(d==0)) {
									 cellE.setCellValue(1);
								 } else {
									 cellE.setCellValue(0);
								 } 
								 
								if (pomU.upariABP(autor) > 0){
									autor.setProcenat(pomU.upariABP(autor));
								}
								
								if(pomU.upariInstBP(autor.institucija) > 0){
									autor.institucija.procenat = pomU.upariInstBP(autor.institucija);
									//boolean imaZemlja = pomU.upariZemlju(autor.institucija);
									boolean imaGrad = pomU.upariGrad(autor.institucija);
									
									cellE = rowE.createCell(22);
							//		cellE.setCellValue(imaZemlja);
									cellE = rowE.createCell(21);
									cellE.setCellValue(imaGrad);
									 
									int autorUInst = pomU.upariAInst(autor);
									
//									System.out.println(autor.getIme() + " : " + autor.getSs() + " : "+ autor.getPrezime() + " inst- " + autor.institucija.naziv
//											+ " # (" + autor.getProcenat() + "," + autor.getInstitucija().procenat + "," + autorUInst + ")");
//									
									cellE = rowE.createCell(11);
									cellE.setCellValue(autor.getProcenat() + "," + autor.getInstitucija().procenat + "," + autorUInst);
									
									if ( (autor.institucija.procenat <= 10) && (autor.institucija.naziv.contains("Univ"))
											&& (autor.institucija.podnaziv1.contains("Fac")
												|| autor.institucija.podnaziv1.contains("Sch")
												|| autor.institucija.podnaziv1.contains("Inst")
												)
										){
											if (!pomU.postojiLiVUGrad(autor.institucija, "Univ")){
												cellE = rowE.createCell(29);
												//postoji li Univ sa takvim nazivom u Drzavi!!!
												
												if (pomU.unesiInstituciju(autor.institucija)){
														cellE.setCellValue("insert into");
													} else {
													cellE.setCellValue("vec postoji");
												}
											} else {
												cellE = rowE.createCell(29);
												cellE.setCellValue("vec postoji VUUU");
											}
										
									}	
									
									
								}
	
						} else {
						//	System.out.println("nije uparen" + autor.getIme() + " : " + autor.getSs() + " : "+ autor.getPrezime());
							 rowE = sheetE.createRow(w++);
							
							 cellE = rowE.createCell(0);
							 cellE.setCellValue(row.getRowNum()+1);
	
							 cellE = rowE.createCell(1);
							 cellE.setCellValue(autor.getIme());
							 
							 cellE = rowE.createCell(2);
							 cellE.setCellValue(autor.getSs());
							 
							 cellE = rowE.createCell(3);
							 cellE.setCellValue(autor.getPrezime());
							 
							 cellE = rowE.createCell(4);
							 cellE.setCellValue("-");
							 
							 cellE = rowE.createCell(5);
							 cellE.setCellValue("-");
							 
							 cellE = rowE.createCell(6);
							 if ((i==0)&&(d==0)) {
								 cellE.setCellValue(1);
							 } else {
								 cellE.setCellValue(0);
							 } 
						}
						
						if (!autor.isDupli()) {
							 cellE = rowE.createCell(10);
							 cellE.setCellValue(1);
							i++;
						}else{
							 cellE = rowE.createCell(10);
							 cellE.setCellValue(0.5);
							 d++;
						}
						
						if(imaRP){
							pomU.upariReprintAutorOrg(autor, repAutor);
						}
						
			            cellE = rowE.createCell(8);
			            if (autor.isReprint()){
			            	cellE.setCellValue(1);	
			            } else {
			            	cellE.setCellValue(0);	
			            }
			            cellE = rowE.createCell(9); //pazi kolonu 42
						cellE.setCellValue(row.getCell(42).getNumericCellValue());	
					}
		            cellE = rowE.createCell(7);
		         	cellE.setCellValue(i+ (int)(Math.ceil(d/2)));		
		         	
		         	if (!row.getCell(22).toString().contains("[")){
		         		if (row.getCell(22).toString().contains(";")){
		         			String[] siroviNiz =  row.getCell(22).toString().split(";");
		         			for (int j = 0; j < siroviNiz.length; j++) {
		         			//	pom.parsInst(siroviNiz[j]).stampajInst();
		         				rowE = sheetE.createRow(w++);
									
								cellE = rowE.createCell(0);
								cellE.setCellValue(row.getRowNum()+1);
								
								cellE = rowE.createCell(4);
								if (!(pom.parsInst(siroviNiz[j]).podnaziv1 == null)){
									cellE.setCellValue((pom.parsInst(siroviNiz[j]).naziv.trim()) + ", " + (pom.parsInst(siroviNiz[j]).podnaziv1.trim()));
								} else {
									cellE.setCellValue((pom.parsInst(siroviNiz[j]).naziv.trim()));									
								}
								cellE = rowE.createCell(5);
								cellE.setCellValue(pom.parsInst(siroviNiz[j]).drzava.trim());
								 
								cellE = rowE.createCell(9);
								cellE.setCellValue(row.getCell(42).getNumericCellValue());	
							}
		         		} 
		         	}
					
				} else{
					 for (Iterator<Autor> iterator = autoriOrg.iterator(); iterator.hasNext();) {
		            	
						Autor autor = (Autor) iterator.next();
									
						rowE = sheetE.createRow(w++);
						
						cellE = rowE.createCell(0);
						cellE.setCellValue(row.getRowNum()+1);

						cellE = rowE.createCell(1);
						cellE.setCellValue(autor.getIme());
						 
						cellE = rowE.createCell(2);
						cellE.setCellValue(autor.getSs());
						 
						cellE = rowE.createCell(3);
						cellE.setCellValue(autor.getPrezime());
						 
						cellE = rowE.createCell(4);
						cellE.setCellValue("-");
						 
						cellE = rowE.createCell(5);
						cellE.setCellValue("-");
					 }
				}
	            
			 }catch (Exception e) {
				 System.out.println(e.toString());
			 }
							    
		 }
		 try {
             
             wbE.write(out);
             out.close();
             System.out.println("Excel written successfully..");
              
         } catch (Exception e) {
             e.printStackTrace();
         } 
	
	}

}
