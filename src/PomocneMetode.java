
public class PomocneMetode {
	
	public int brojVS(String org){
		String pom = org;
		int brVS = 0;
		int duzina = org.length();
		for (int i = 0; i < duzina; i++){
			 if (Character.isUpperCase(pom.toCharArray()[0])) {
			        brVS++;
			    }
			 pom = pom.substring(1);
		}
		return brVS;
	}
	
	public int brojT(String org){
		String pom = org;
		int brT = 0;
		int duzina = org.length();
		for (int i = 0; i < duzina; i++){
			 if (pom.toCharArray()[0] == '.') {
			        brT++;
			    }
			 pom = pom.substring(1);
		}
		return brT;
	}
	
	public int brojP(String org){
		String pom = org;
		int brP = 0;
		int duzina = org.length();
		for (int i = 0; i < duzina; i++){
			 if (pom.toCharArray()[0] == ' ') {
			        brP++;
			    }
			 pom = pom.substring(1);
		}
		return brP;
	}

}
