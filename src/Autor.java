
public class Autor {
	
	private String ime, prezime, ss;
	Institucija institucija;
	int procenat, id;
	boolean dupli, reprint;
	
	public boolean isReprint() {
		return reprint;
	}

	public void setReprint(boolean reprint) {
		this.reprint = reprint;
	}

	public boolean isDupli() {
		return dupli;
	}

	public void setDupli(boolean dupli) {
		this.dupli = dupli;
	}
	
	public int getProcenat() {
		return procenat;
	}

	public void setProcenat(int procenat) {
		this.procenat = procenat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Institucija getInstitucija() {
		return institucija;
	}

	public void setInstitucija(Institucija institucija) {
		this.institucija = institucija;
	}	


	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}
	
	
	public int istiAutoriB(Autor a1, Autor a2){
	
		int isti = 0;
		
		// svi podaci isti
		if ((a1.ss != null && !a1.ss.isEmpty()) && (a2.ss != null && !a2.ss.isEmpty()) && (a1.ime.length()>1) &&
				a1.ime.equalsIgnoreCase(a2.ime) && a1.ss.equalsIgnoreCase(a2.ss) && a1.prezime.equalsIgnoreCase(a2.prezime)){
			isti = 100;			
		}
		
		//imaju inicijale umesto imena, ali imaju ss
		else if ((a1.ime.length() == 1 && a2.ime.length() == 1) && (a1.ss != null && !a1.ss.isEmpty()) && (a2.ss != null && !a2.ss.isEmpty())){
			if (a1.ime.equalsIgnoreCase(a2.ime) && a1.ss.equalsIgnoreCase(a2.ss) && a1.prezime.equalsIgnoreCase(a2.prezime)){
				isti = 95;			
			}
		}
		// ako jedan ima srednje ime, a drugi srednje slovo
		// ako jedan ima ime, a drugi inicijale
		
		
		// jedan/oba nemaju srednje slovo/ime
		else if (((a1.ss == null || a1.ss.isEmpty()) || (a2.ss == null || a2.ss.isEmpty())) && (a1.ime.length()>1)){
			if (a1.ime.equalsIgnoreCase(a2.ime) && a1.prezime.equalsIgnoreCase(a2.prezime)){
				isti = 85;			
			}
		}
		//oba imaju inicijale umesto imena, ali nemaju ni ss
		else if (((a1.ss == null || a1.ss.isEmpty()) || (a2.ss == null || a2.ss.isEmpty()))){ //&& (a1.ime.length()==1)&& (a2.ime.length()==1)){
			if ((a1.ime.equalsIgnoreCase(a2.ime)) && (a1.prezime.equalsIgnoreCase(a2.prezime))){
				isti = 80;			
			}
		}
				
		return isti;
	}
	
	public int istiAutoriE(Autor a1, Autor a2){
		int isti = 0;
		
		// svi podaci isti
		if ((a1.ss != null && !a1.ss.isEmpty()) && (a2.ss != null && !a2.ss.isEmpty()) && (a1.ime.length()>1) &&
				a1.ime.equalsIgnoreCase(a2.ime) && a1.ss.equalsIgnoreCase(a2.ss) && a1.prezime.equalsIgnoreCase(a2.prezime)){
			isti = 100;			
		}
		
		//imaju inicijale umesto imena, ali imaju ss
		else if ((a1.ime.length() == 1 && a2.ime.length() == 1) && (a1.ss != null && !a1.ss.isEmpty()) && (a2.ss != null && !a2.ss.isEmpty())){
			if (a1.ime.equalsIgnoreCase(a2.ime) && a1.ss.equalsIgnoreCase(a2.ss) && a1.prezime.equalsIgnoreCase(a2.prezime)){
				isti = 95;			
			}
		}
		// ako jedan ima srednje ime, a drugi srednje slovo
		// ako jedan ima ime, a drugi inicijale
		
		else if((a1.ss != null && !a1.ss.isEmpty()) && (a2.ss != null && !a2.ss.isEmpty()) && (a1.ime.length()>1) &&
				a1.ime.substring(0, 1).equalsIgnoreCase(a2.ime.substring(0, 1)) && a1.ss.equalsIgnoreCase(a2.ss) && 
				a1.prezime.equalsIgnoreCase(a2.prezime)){
			isti = 80;
		}
				
		// jedan/oba nemaju srednje slovo/ime
		// oba imaju inicijale
		else if ((a1.ss == null || a1.ss.isEmpty()) || (a2.ss == null || a2.ss.isEmpty())){
			
			if (a1.ime.length()>1){
		
				if (a2.ime.length()>1){
					if (a1.ime.equalsIgnoreCase(a2.ime) && a1.prezime.equalsIgnoreCase(a2.prezime)){
						isti = 95;			
					}
				} else { // prvi ima ime, a drugi inicijal
					if ((a1.ime.substring(0, 1).equalsIgnoreCase(a2.ime)) && (a1.prezime.equalsIgnoreCase(a2.prezime))){
						isti = 80;			
					}
				}
			} else if (a2.ime.length()> 1) { // prvi ima inicijal, drugi ime
				if ((a1.ime.equalsIgnoreCase(a2.ime.substring(0, 1))) && (a1.prezime.equalsIgnoreCase(a2.prezime))){
					isti = 80;			
				}	
				
			} else {// samo inicijali
				if (a1.ime.equalsIgnoreCase(a2.ime) && a1.prezime.equalsIgnoreCase(a2.prezime)){
					isti = 75;			
				}
			}
		}
				
		return isti;
	}
	
}
