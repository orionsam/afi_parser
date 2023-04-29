import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Uparivanja {
	
	Connection PostgresC;  
	Upiti upiti;

	public Uparivanja() {
		super();
		try {
			PostgresC = ConnectionPostgre.getConnection();
			this.upiti  = new Upiti(PostgresC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void upariAC1(List<Autor> autoriOrg, String c1){
		Parsiranje pom = new Parsiranje();
		List<List> institAutS = new LinkedList<List>();
		institAutS = pom.parsInstAutGrub(c1);

		int i=0;
		for (Iterator<List> iterator1 = institAutS.iterator(); iterator1.hasNext();) {
			List <String> red = iterator1.next();
			i++;
			// ukoliko ima autora u C1
			if (red.size() == 2){
				
			//	System.out.println("institucija " + red.get(0));
				Institucija institucijaP = pom.parsInst(red.get(0));
				
				//institucijaP.stampajInst();
//				System.out.println("autori " + red.get(1));
				List<Autor> autoriPF = pom.parsAutoraS(red.get(1));
				List<Autor> autoriF = pom.parsAutoraF(autoriPF);
				
				for (Iterator<Autor> iteratorF = autoriF.iterator(); iteratorF.hasNext();) {
					Autor autorC1 = (Autor) iteratorF.next();
				//	System.out.println("F " + autorC1.getIme() + " : " + autorC1.getSs() + " : "+ autorC1.getPrezime());
					
					List<Autor> autorDuplaInstitucija = new LinkedList<Autor>();
					for (ListIterator<Autor> iteratorA = autoriOrg.listIterator(); iteratorA.hasNext();) {
						Autor autorA = (Autor) iteratorA.next();
						// menjati verovatnocu !!!
						if ((autorA.istiAutoriE(autorC1, autorA) >= 75)){
							if (institucijaP.brS==1){
								// autor je potpisao dve institucije jednu za drugom
								if (institucijaP.naziv.contains(";")){
									String[] institAutora = institucijaP.naziv.split(";");
									//System.out.println(institAutora.length);
									switch (institAutora.length) {
									case 2: 
										autorA.setInstitucija(pom.parsInst(institAutora[0]));
										autorA.setDupli(true);
									//	System.out.println("Prva" +institAutora[0]);
										Autor autorDI = new Autor();
										autorDI.setIme(autorA.getIme());
										autorDI.setSs(autorA.getSs());
										autorDI.setPrezime(autorA.getPrezime());
										autorDI.setInstitucija(pom.parsInst(institAutora[1].trim()));
										autorDI.setDupli(true);
									//	System.out.println("druga" +institAutora[1]);
										autorDuplaInstitucija.add(autorDI);
										break;
									default:
										autorA.setInstitucija(pom.parsInst(institAutora[0]));
										break;
									}
								}	
							} else {
								if (autorA.getInstitucija()== null){
									autorA.setInstitucija(institucijaP);
								} else {
									//System.out.println("vec ima instituciju!!!");
									
									// proveriti da nije ista institucija?
									
									if (!(autorA.getInstitucija().ista(institucijaP))){
//										System.out.println("prva institucija");
//										autorA.getInstitucija().stampajInst();
//										System.out.println("druga institucija");
//										institucijaP.stampajInst();
										
										// da li je vec dodat u listu duplih?
										boolean ima = false;
										int n = 0;
										

										for (Iterator<Autor> iteratorADupli = autorDuplaInstitucija.iterator(); iteratorADupli.hasNext();){
											Autor autorDA = (Autor) iteratorADupli.next();
											n++;
											
											if ((autorDA.getInstitucija().ista(institucijaP)) && (autorDA.istiAutoriB(autorA, autorDA)>=75)){
												ima = true;
											}
										}
										// ako nije dodat dodaje se
										if (!ima){
											Autor autorDI = new Autor();
											autorDI.setIme(autorA.getIme());
											autorDI.setSs(autorA.getSs());
											autorDI.setPrezime(autorA.getPrezime());
											autorDI.setInstitucija(institucijaP);
											autorDI.setDupli(true);
											autorDuplaInstitucija.add(autorDI);
											
											autorA.setDupli(true);
											
										}
										
									} 
								}
							
						//	System.out.println("Pov " +autorA.getPrezime() + " inst " + autorA.getInstitucija().naziv);
							}
						}
						//System.out.println("O " +autorA.getIme() + " : " + autorA.getSs() + " : "+ autorA.getPrezime());
						//System.out.println("isti " + autorA.istiAutori(autorC1, autorA));
					}
					
					autoriOrg.addAll(autorDuplaInstitucija);
				}
			} else if (red.size() == 1){
				if (i == 1){
				//System.out.println("institucija11 " + red.get(0));
					// ovde zavrsi niz institucija bez autora ili jedna institucija
				
					if (institAutS.size()==1){
						Institucija institucijaP = pom.parsInst(red.get(0));
						for (Iterator<Autor> iteratorA = autoriOrg.iterator(); iteratorA.hasNext();) {
							Autor autorA = (Autor) iteratorA.next();
							autorA.setInstitucija(institucijaP);
						//	System.out.println("Pov0 " +autorA.getPrezime() + " inst " + autorA.getInstitucija().naziv);
						}
					} else {
						for (Iterator<Autor> iteratorA = autoriOrg.iterator(); iteratorA.hasNext();) {
							Autor autorA = (Autor) iteratorA.next();
							autorA.setInstitucija(pom.parsInst("###, ###, ###, ###"));//+red.get(0)));
						//	System.out.println("Pov 1" +autorA.getPrezime() + " inst " + autorA.getInstitucija().naziv);
						}
						
					}
				//institucijaP.stampajInst();
				} 
			}

			//System.out.println(autor.getIme() + " : " + autor.getSs() + " : "+ autor.getPrezime());
		}
		
		
	}

	public int upariABP(Autor autor) throws Exception {
		
		int isti = 0;
		
		if (((autor.getIme() != null)&& !autor.getIme().isEmpty() && (autor.getIme().length() > 1))
			&&((autor.getPrezime() != null)&& !autor.getPrezime().isEmpty())){
			
		// Jelica Z Protic
			if ((autor.getSs() != null)&& !autor.getSs().isEmpty()){
				isti = upiti.istiAutorUBazi(autor);
		// Jelica Protic		
			} else {
				isti = upiti.istiAutorUBaziIP(autor);
			}
		} // J Z Protic
		else if ((autor.getSs() != null)&& !autor.getSs().isEmpty()){ 
			
			isti = upiti.istiAutorInicijalS(autor);
			
		} else { // J Protic
			isti = upiti.istiAutorInicijal(autor);
		}
		
	//	PostgresC.close();
		return isti;
	}

	public int upariInstBP(Institucija institucija) throws Exception {
		int isti = 0;
		int [] kontrola;
		kontrola = upiti.imaLiZemlja(institucija.drzava.trim());
		if(kontrola[0] == 1) {
			if (upiti.jeLiSrbija(institucija.drzava.trim())){
				//System.out.println("jeste Serbia");
				institucija.grad = upariGrad(institucija.grad.trim());
				institucija.drzavaId = kontrola[1];
				switch (institucija.brS) {
				case 1:
					//sns - polomiti na reci pa traziti u nazivima i alternativama
					break;
				
				case 3:
					isti = upiti.nadjiInstitucijuSrbija(institucija, institucija.naziv.trim(), institucija.grad.trim());
					
					break;
				case 4:
					
					try {
						isti = upiti.nadjiInstituciju1Srbija(institucija, institucija.naziv.trim(), institucija.podnaziv1.trim(), institucija.grad.trim());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
					break;
				
				case 5:
					try {
						isti = upiti.nadjiInstituciju2Srbija(institucija, institucija.naziv.trim(), institucija.podnaziv1.trim(),institucija.podnaziv2.trim(), institucija.grad.trim());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
					break;	
				case 6:
					try {
						isti = upiti.nadjiInstituciju3Srbija(institucija, institucija.naziv.trim(), institucija.podnaziv1.trim(),institucija.podnaziv2.trim(),
								institucija.podnaziv3.trim(), institucija.grad.trim());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
					break;
				default:
					break;
				}
				isti = isti + 10; // zemlja je srbija
			} else { // ovde sad treba da ide analiza ako nije Srbija
				upariGrad(institucija);
				
				institucija.drzavaId = kontrola[1];
				
				switch (institucija.brS) {
				case 1:
					//sns - polomiti na reci pa traziti u nazivima i alternativama
					break;
				
				case 3:
					isti = upiti.nadjiInstitucijuStrano(institucija, institucija.naziv.trim(), institucija.grad.trim());
					
					break;
				case 4:
					
					try {
						isti = upiti.nadjiInstituciju1Strano(institucija, institucija.naziv.trim(), institucija.podnaziv1.trim(), institucija.grad.trim());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
					break;
				
				case 5:
					try {
						isti = upiti.nadjiInstituciju2Strano(institucija, institucija.naziv.trim(), institucija.podnaziv1.trim(),institucija.podnaziv2.trim(), institucija.grad.trim());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
					break;	
				case 6:
					try {
						isti = upiti.nadjiInstituciju3Strano(institucija, institucija.naziv.trim(), institucija.podnaziv1.trim(),institucija.podnaziv2.trim(),
								institucija.podnaziv3.trim(), institucija.grad.trim());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
					break;
				default:
					break;
				}
				isti = isti + upiti.upariDrzavu(institucija, isti);	
			}
		}
		return isti;
	}

	public int upariAInst(Autor autor) throws SQLException {
		int jeste = 0;
		
		//System.out.println(autor.getId() + "  --  " +  autor.getInstitucija().id);
		jeste = upiti.autorUInstB(autor.getId(), autor.getInstitucija().id);
		return jeste;
	}

	public void upariReprintAutorOrg(Autor autor, Autor repAutor) {
		
		if (autor.istiAutoriE(autor, repAutor)>=75){
			autor.setReprint(true);
		}
	}

	public boolean upariZemlju(Institucija institucija) throws Exception {
	
		boolean imali = false;
		if (!upiti.imaLiInstitucijaDrzava(institucija)){
			
			int[] broj = upiti.imaLiZemlja(institucija.drzava.trim());
			imali =  (broj[0] == 0)? false : true;
			if (imali) {
				institucija.drzavaId = broj[1];
			}	else {
				institucija.drzavaId = 0;
			}
		} else {
			imali = true;
		}
		if (institucija.drzava.contains("USA")){
			//System.out.println("USA"); zakucan id za USA!!! promeniti
			imali = true;
			institucija.drzavaId = 7;
			institucija.drzava = "USA";
		}
		return imali;
	}

	public String upariGrad(String grad) throws Exception {
		String imali = "";
		
		if(!upiti.imaLiGrad(grad)) {
		
			if(grad.contains(" ")){
				String[] gradSplit = grad.split(" ");
				switch (gradSplit.length) {
				case 2:
				
					if (!upiti.imaLiGrad(gradSplit[0])){
						if (upiti.imaLiGrad(gradSplit[1])){
							imali = gradSplit[1];
						}
					} else {
						imali = gradSplit[0];
					}
					
					break;
				case 3:
					
					boolean imaliG = upiti.imaLiGrad(gradSplit[0]);
					if (!imaliG) {
						imaliG = upiti.imaLiGrad(gradSplit[1]);
						if (!imaliG) {
							imaliG = upiti.imaLiGrad(gradSplit[2]);
							if (!imaliG) {
								imaliG = upiti.imaLiGrad(gradSplit[0]+" "+gradSplit[1]);
								if(!imaliG){
									if (upiti.imaLiGrad(gradSplit[1]+" "+gradSplit[2])){
										imali = gradSplit[1]+" "+ gradSplit[2];
									}
								}else {
									imali = gradSplit[0]+" "+ gradSplit[1];
								}
							} else {
								imali = gradSplit[2];
							}
						} else {
							imali = gradSplit[1];
						}
					} else {
						imali = gradSplit[0];
					}					
					break;
				
				default:
					break;
				}
			}	
		} else {
				imali = grad;
		}
					
		return imali;
	}

	public boolean upariGradSirovo(Institucija institucija) throws Exception {
		boolean imali = false;
		
		if (institucija.drzavaId == 7){
			imali = upariAmerickiGrad(institucija);
		} else {
			imali = upariGrad(institucija);
		}
		
		return imali;
	}

	public boolean upariGrad(Institucija institucija) throws Exception {
		boolean imali = false;
		
		if(upariGrad(institucija.grad.trim()).isEmpty()){
			if((institucija.podnaziv4 != null) && !upariGrad(institucija.podnaziv4.trim()).isEmpty()){
				if((institucija.podnaziv3 != null) && !upariGrad(institucija.podnaziv3.trim()).isEmpty()){
					if((institucija.podnaziv2 != null) && !upariGrad(institucija.podnaziv2.trim()).isEmpty()){
						if((institucija.podnaziv1!= null) && !upariGrad(institucija.podnaziv1.trim()).isEmpty()){
							imali = false;
						}else {
							if (institucija.podnaziv1 != null){
								institucija.grad = upariGrad(institucija.podnaziv1.trim());
							}	
						}
					}else {
						if (institucija.podnaziv2 != null){
							institucija.grad = upariGrad(institucija.podnaziv2.trim());
						}
					}
				} else {
					if (institucija.podnaziv3 != null){
						institucija.grad = upariGrad(institucija.podnaziv3.trim());
					}
				}
			} else {
				if (institucija.podnaziv4 != null){
					institucija.grad = upariGrad(institucija.podnaziv4.trim());
				}
			}
			
		} else {

			imali = true;
			institucija.grad = upariGrad(institucija.grad.trim());
		}
		if (imali){
			upiti.idGrad(institucija);
		}
		
		return imali;
	}
	private boolean upariAmerickiGrad(Institucija institucija) throws Exception {
		// TODO Auto-generated method stub
		boolean imali = false;
		String sDrzava = "";
		String aGrad = "";
		
		if (!institucija.grad.isEmpty()){
			sDrzava = upariGrad(institucija.grad);
			
			// to je verovatno bila savezna drzava, sad da nadjemo i grad
			if ((aGrad = upariGrad(institucija.podnaziv4.trim())).isEmpty()){
				if((aGrad = upariGrad(institucija.podnaziv3.trim())).isEmpty()){
					if ((aGrad = upariGrad(institucija.podnaziv2.trim())).isEmpty()){
						if(!(aGrad = upariGrad(institucija.podnaziv1.trim())).isEmpty()){
							upiti.idGradRezervni(institucija, 2);
						} 
					} else {
						upiti.idGradRezervni(institucija, 3);
					}
				} else {
					upiti.idGradRezervni(institucija, 4);
				}
			} else {
				upiti.idGradRezervni(institucija, 5);
			}
			
		}
		if (!sDrzava.isEmpty() || !aGrad.isEmpty()){
			imali = true;
		}
		return imali;
	}

	public boolean unesiInstituciju(Institucija institucija) throws SQLException {
		// TODO Auto-generated method stub
		boolean uneto;
		if (upiti.jeLiSrbija(institucija.drzava)) {
			int idInstitB = upiti.imaLiInstitucijaNazivS(institucija);
			if (idInstitB == 0){
				upiti.unesiNazivInstitucijeS(institucija);
				idInstitB = upiti.imaLiInstitucijaNazivS(institucija);
				upiti.unesiOstatkeInstitucije(institucija, idInstitB);
				uneto = true;
			} else {
				uneto = false;
			}
		} else {
			int idInstitB = upiti.imaLiInstitucijaNaziv(institucija);
			if (idInstitB == 0){
				upiti.unesiNazivInstitucije(institucija);
				idInstitB = upiti.imaLiInstitucijaNaziv(institucija);
				upiti.unesiOstatkeInstitucije(institucija, idInstitB);
				uneto = true;
			} else {
				uneto = false;
			}
		}
		return uneto;
	}

	public boolean postojiLiVUGrad(Institucija institucija, String ustanova) throws SQLException {
		// TODO Auto-generated method stub
		boolean ima = false;
		ima = upiti.imaLiVUGrad(institucija, ustanova);
		return ima;
	}

}
