import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.ibm.icu.util.CaseInsensitiveString;


public class Parsiranje {
	
	// iz stringa autora izdvoji niz autora na osnovu separatora
	public List<Autor> parsAutoraS(String svi){
		String rec = "", prezime = "";
		List<Autor> autori = new LinkedList<Autor>(); 
		int kraj = svi.length();
		for(int i = 0; i < kraj + 2; i++) {

			 if (i == kraj + 1 ){	
				 //prazan hod
			 } else if (i == kraj ){
				 Autor trenutni = new Autor();
	        	 trenutni.setIme(rec);
	        	 trenutni.setPrezime(prezime);
	        	 autori.add(trenutni);
	        	 rec = "";	        	 
	         } else if (svi.substring(0,1).toCharArray()[0]== ';'){
	        	 Autor trenutni = new Autor();
	        	 trenutni.setIme(rec);
	        	 trenutni.setPrezime(prezime);
	        	 rec = "";
	        	 svi = svi.substring(1);
	        	 autori.add(trenutni);
	        	 
	         } else if (svi.substring(0,1).toCharArray()[0] == ','){
	        	// imeF = true;
	        	 prezime = rec;
	        	 rec = "";
	        	 svi = svi.substring(1);
	         } else {
	        	 rec = rec.concat(svi.substring(0,1));
	        	 svi = svi.substring(1);
	         }
	      }
//		System.out.println(autori.size());
//		for (Iterator<Autor> iterator = autori.iterator(); iterator.hasNext();) {
//			Autor autor = (Autor) iterator.next();
//			System.out.println(autor.getIme() + " : " + autor.getPrezime());
//		}
	return autori;	
		
	}

	public List<Autor> parsAutoraF(List<Autor> autori){
		
		for (Iterator<Autor> iterator = autori.iterator(); iterator.hasNext();) {
			Autor autor = (Autor) iterator.next();
			// obrada prezimena
			autor.setPrezime(autor.getPrezime().trim());
			
			// obrada imena
			autor.setIme(autor.getIme().trim());
			
			String org = autor.getIme();
			int duzina = org.length();
			int brVS = 0, brT = 0, brP = 0;

			PomocneMetode pomM = new PomocneMetode();			
			brVS = pomM.brojVS(org);
			brT = pomM.brojT(org);
			brP = pomM.brojP(org);
			
			// sve velika slova
			if ((brVS == duzina) && (duzina == 1)){
				autor.setIme(org);
			} else if ((brVS == duzina) && (duzina == 2)){
				autor.setIme(org.substring(0, 1));
				autor.setSs(org.substring(1));
			} else if ((brVS == duzina) && (duzina == 3)){
				autor.setIme(org.substring(0, 1));
				autor.setSs(org.substring(1,2));
			}/*else {
				autor.setIme("test");
			}*/
			
			// sa tackama
			if ((brT == 1) && (duzina == 2)){
				autor.setIme(org.substring(0, 1));
			} else if ((brT == 2) && (duzina == 5)){
				autor.setIme(org.substring(0, 1));
				autor.setSs(org.substring(3,4));
			} else if ((brT == 1) && (brVS == 1) && (duzina == 3)){
				autor.setIme(autor.getIme().substring(0, 2).trim());
			} else if ((brT == 3) && (brP > 1) && (brVS > 3)) {
				String[] niz = org.split("[ ]",2);
				autor.setIme(niz[0]);
				autor.setSs(niz[1]);
				//autor.setTacnost(false);
			} else if ((brT == 3) && (brVS == 3) && (duzina == 8)) {
				autor.setIme(org.substring(0, 1));
				autor.setSs(org.substring(3));
			//	autor.setTacnost(false);
			}
			
			//lepo napisano ime
			if ((brVS == 1) && (duzina > 2) && (brP == 0) && (brT == 0)){
				autor.setIme(org);
			}
			
			//sa spejsovima
			if (brP == 1){
				String[] niz = org.split("[ ]");
				
				if ((pomM.brojT(niz[0]) == 0)&& (niz[0].length() > 2)){
					autor.setIme(niz[0]);
					
					if ((pomM.brojT(niz[1]) == 1)&& (niz[1].length() == 2)){
						autor.setSs(niz[1].substring(0, 1));						
					} else if ((pomM.brojT(niz[1]) == 0) && (pomM.brojVS(niz[1]) == 1) && (niz[1].length() > 2)){
						autor.setPrezime(niz[1]+ " " + autor.getPrezime());	
					}
				} else if ((pomM.brojT(niz[0]) == 1)&& (niz[0].length() == 2)){
					autor.setIme(niz[0].substring(0, 1));
					
					if ((pomM.brojT(niz[1]) == 0) && (pomM.brojVS(niz[1]) == 1) && (niz[1].length() > 2) && !(niz[1].startsWith("-"))){
						if (niz[1].endsWith("-")){
							autor.setPrezime(niz[1]+ autor.getPrezime());
							//autor.setTacnost(false);
						} else {
							autor.setPrezime(niz[1]+ " " + autor.getPrezime());
							//autor.setTacnost(false);
						}
					} else if ((pomM.brojT(niz[1]) == 1) && (niz[1].startsWith("-"))){
						autor.setSs(niz[1]);
						//autor.setTacnost(false);
						
					}
				} 
				//System.out.println(niz[0] + " niz " + niz[1]);
			}
			
			if (brP == 2){
				String[] niz = org.split("[ ]");
				
				if ((pomM.brojT(niz[0]) == 0) && (niz[0].length() > 2) && (pomM.brojT(niz[1]) == 1) && (pomM.brojT(niz[2]) == 1)){
					autor.setIme(niz[0]);
					autor.setSs(niz[1] + " " + niz[2]);
				} else if ((pomM.brojT(niz[0]) == 0) && (niz[0].length() > 2)
						&& (pomM.brojT(niz[1]) == 1) &&
						(pomM.brojT(niz[2]) == 0) && niz[2].length() >2 ){
					autor.setIme(niz[0]);
					autor.setSs(niz[1].substring(0, 1));
					autor.setPrezime(niz[2]+ " " + autor.getPrezime());
				}
			}
			//System.out.println(autor.getIme() + " : " + autor.getSs() + " : "+ autor.getPrezime());	
		}
		return autori;
		
	}

	@SuppressWarnings("rawtypes")
	// iz kolone c1 parsira niz institucija i na njega nakaci niz autora ukoliko postoje
	public List<List> parsInstAutGrub(String c1){
		
		
		List<List> institS = new LinkedList<List>();
		String autori;
		String sirovInst;
		if (c1.contains("[") && c1.startsWith("[")){
			c1 = c1.substring(1);// skida se prva zagrada koja ostane na kraju
			String[] pomNiz = c1.split(";\\s\\[");
			for (int i = 0; i < pomNiz.length; i++) {
			//	System.out.println("niz -" + i +" " + pomNiz[i].toString());
				autori = pomNiz[i].split("\\]\\s")[0];
				sirovInst = pomNiz[i].split("\\]\\s")[1];
			//	System.out.println("autori - " + autori);
				List<String> autorInst = new LinkedList<String>();
				autorInst.add(sirovInst);
				autorInst.add(autori);
				institS.add(autorInst);		
			}
		} else if (c1.contains("[") && !c1.startsWith("[")){ // kada c1 ne pocinje nizom autora, nego tek kasnije
		//	System.out.println("mogu da skinem [ " + c1.indexOf("["));
			c1= c1.substring(c1.indexOf("[")+1);
			String[] pomNiz = c1.split(";\\s\\[");
			for (int i = 0; i < pomNiz.length; i++) {
			//	System.out.println("niz -" + i +" " + pomNiz[i].toString());
				autori = pomNiz[i].split("\\]\\s")[0];
				sirovInst = pomNiz[i].split("\\]\\s")[1];
			//	System.out.println("autori - " + autori);
				List<String> autorInst = new LinkedList<String>();
				autorInst.add(sirovInst);
				autorInst.add(autori);
				institS.add(autorInst);		
			}
		
		}else if (c1.split(";").length - 1 > 0){
			//System.out.println("; " + (c1.split(";").length - 1));
			String[] pomNizAI =  c1.split(";");
			for (int i = 0; i < pomNizAI.length; i++) {
				List<String> autorInst = new LinkedList<String>();
				autorInst.add(pomNizAI[i]);// niz institucija kada nema autora
				institS.add(autorInst);
		//		autorInst.clear();
			}
		} else {
			List<String> autorInst = new LinkedList<String>();
			autorInst.add(c1);
			institS.add(autorInst);
			//System.out.println("nema zagrada i ;");
		}
		
		return institS;
	}

	public Institucija parsInst(String institNiz) {
		Institucija institF;
			String[] institSplit = institNiz.split(",");
			//System.out.println(institSplit.length);
		switch (institSplit.length) {
		case 3:
			institF = new Institucija(institSplit[0],institSplit[1],institSplit[2]);
			break;
		case 4:
			institF = new Institucija(institSplit[0],institSplit[1],institSplit[2],institSplit[3]);
			break;
		case 5:
			institF = new Institucija(institSplit[0],institSplit[1],institSplit[2],institSplit[3],institSplit[4]);
			break;
		case 6:
			institF = new Institucija(institSplit[0],institSplit[1],institSplit[2],institSplit[3],institSplit[4],institSplit[5]);
			break;
		case 7:
			institF = new Institucija(institSplit[0],institSplit[1],institSplit[2],institSplit[3],institSplit[4],institSplit[5],institSplit[6]);
			break;
		default:
			institF = new Institucija(institNiz);
			break;
		}
		
		return institF;
	}
	
	public boolean SNSS(String imeK, String imeD){
		boolean sadrzi = false;
		
		
		return sadrzi;
	}

	public Autor izvuciAutoraRP(String siroviRP) {
		
		String[] reprintSplit = siroviRP.split("\\(corresponding author\\), ");
		
		//(corresponding author), (reprint author) 2013, ima po dva autora?
		
		List <Autor> rpAutori = new LinkedList<Autor>();
		rpAutori = parsAutoraS(reprintSplit[0]);
		rpAutori = parsAutoraF(rpAutori);
		
		Institucija rpInstitucija = parsInst(reprintSplit[1]);
		
		if (rpAutori.size()==1){
			rpAutori.get(0).setInstitucija(rpInstitucija);
		} else {
			System.out.println("greska");
		}
		return rpAutori.get(0);
	}
}
