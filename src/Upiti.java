import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import yu.ac.bg.rcub.utilspub.*;



public class Upiti {

	public Connection PostgresC;

	
	
	public Upiti(Connection postgresC) {
		super();
		PostgresC = postgresC;
	}


	public int istiAutorUBazi(Autor autor) throws Exception {

		int isti = 0;

		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_autor" +
																" FROM autor" +
																" WHERE ime = ?" +
																" and srednje_ime  = ?" +
																" and prezime = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
															    ResultSet.CONCUR_READ_ONLY );
		
		pstmtS.setString(1, Converter.lat2cyr(autor.getIme().trim()));
		pstmtS.setString(2, Converter.lat2cyr(autor.getSs().trim()));
		pstmtS.setString(3, Converter.lat2cyr(autor.getPrezime().trim()));
		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		    // Process the row.  
		    rowCount++;  
		}  
		
		if (rowCount == 0){			
			isti = 1;
		}else if (rowCount == 1){
			result.first();
			autor.setId(result.getInt(1));
			isti = 97;
		} else {
			isti = 200;
		}
		
		if (isti==1){
			// da probamo osisano
			pstmtS = PostgresC.prepareStatement(" SELECT id_autor" +
					" FROM autor_alter" +
					" WHERE ime = ?" +
					" and srednje_ime  = ?" +
					" and prezime = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
				    ResultSet.CONCUR_READ_ONLY );
					
					
			pstmtS.setString(1, autor.getIme().trim());
			pstmtS.setString(2, autor.getSs().trim());
			pstmtS.setString(3, autor.getPrezime().trim());
			
			result = pstmtS.executeQuery();
			

			while ( result.next() )  
			{  
			    // Process the row.  
			    rowCount++;  
			}  
			
			if (rowCount == 0){			
				isti = 2;
			}else if (rowCount == 1){
				result.first();
				autor.setId(result.getInt(1));
				isti = 96;
				System.out.println("There were PP");
			} else {
				isti = 200;
			}
			
		}
		   
		//System.out.println("There were " + rowCount + " records.");  
	
		result.close();
		pstmtS.close();

		return isti;

	}


	public int istiAutorUBaziIP(Autor autor) throws Exception {
		
		int isti = 0;
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_autor " +
				" FROM autor" +
				" WHERE ime = ?" +
				" and prezime = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY  );

		pstmtS.setString(1, Converter.lat2cyr(autor.getIme().trim()));
		pstmtS.setString(2, Converter.lat2cyr(autor.getPrezime().trim()));
	//	System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		// Process the row.  
		rowCount++;  
		}  
		
		if (rowCount == 0){
			isti = 1;
		}else if (rowCount == 1){
			result.first();
			autor.setId(result.getInt(1));
			isti = 85;
		} else {
			isti = 200;
		}
		
		if (isti==1){
			// da probamo osisano
			pstmtS = PostgresC.prepareStatement(" SELECT id_autor" +
					" FROM autor_alter" +
					" WHERE ime = ?" +
					" and prezime = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
				    ResultSet.CONCUR_READ_ONLY );					
					
			pstmtS.setString(1, autor.getIme().trim());
			pstmtS.setString(2, autor.getPrezime().trim());
			
			result = pstmtS.executeQuery();
		//	System.out.println(pstmtS.toString());

			while ( result.next() )  
			{  
			    // Process the row.  
			    rowCount++;  
			}  
			
			if (rowCount == 0){			
				isti = 2;
			}else if (rowCount == 1){
				result.first();
				autor.setId(result.getInt(1));
				isti = 84;
			//	System.out.println("There were PP");
			} else {
				isti = 200;
			}
			
		}
		
		result.close();
		pstmtS.close();

		return isti;

	}


	public int istiAutorInicijal(Autor autor) throws Exception {
		int isti = 0;
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_autor" +
				" FROM autor" +
				" WHERE ime LIKE ?" +
				" and prezime = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY  );

		pstmtS.setString (1, Converter.lat2cyr(autor.getIme().trim() + "%"));
		pstmtS.setString(2, Converter.lat2cyr(autor.getPrezime().trim()));
		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		// Process the row.  
		rowCount++;  
		}  
		
		if (rowCount == 0){
			isti = 1;
		}else if (rowCount == 1){
			result.first();
			autor.setId(result.getInt(1));
			isti = 55;
		} else {
			isti = 200;
		}
		
		result.close();
		pstmtS.close();

		return isti;
	}


	public int istiAutorInicijalS(Autor autor) throws Exception {
		int isti = 0;
		
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_autor" +
																" FROM autor" +
																" WHERE ime LIKE ?" +
																" and srednje_ime  = ?" +
																" and prezime = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
															    ResultSet.CONCUR_READ_ONLY  );
		
		pstmtS.setString(1, Converter.lat2cyr(autor.getIme().trim() + "%"));
		pstmtS.setString(2, Converter.lat2cyr(autor.getSs().trim()));
		pstmtS.setString(3, Converter.lat2cyr(autor.getPrezime().trim()));
		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		    // Process the row.  
		    rowCount++;  
		}  
		
		if (rowCount == 0){
			isti = 1;
		}else if (rowCount == 1){
			result.first();
			autor.setId(result.getInt(1));
			isti = 65;
		} else {
			isti = 200;
		}
		
		result.close();
		pstmtS.close();

		return isti;
	}


	public int nadjiInstituciju1Srbija(Institucija institucija, String naziv, String podnaziv1, String grad) throws Exception {
		int isti =  0;
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT institucija_skracenica.id_institucija" +
																" FROM institucija_skracenica, institucija_drzava" +
																" WHERE skracenica = ? " +
																" AND institucija_drzava.id_institucija = institucija_skracenica.id_institucija " +
																" AND institucija_drzava.id_drzava = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
															    ResultSet.CONCUR_READ_ONLY);
		
		pstmtS.setString(1, naziv);
		pstmtS.setInt(2, institucija.drzavaId);

		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while (result.next())  
		{  
		    // Process the row.  
		    rowCount++;  
		}
		
		result.beforeFirst();
	
		if (naziv.contains("Univ")){
			if (rowCount >= 1){
				isti = 35;
			}
		} else {
			 if (rowCount >= 1){
				isti = 50;
			 } 
		}
		
		PreparedStatement pstmtS1 = PostgresC.prepareStatement(" SELECT institucija_skracenica.id_institucija" +
				" FROM institucija_skracenica, institucija_drzava" +
				" WHERE skracenica = ?"+
				" AND institucija_drzava.id_institucija = institucija_skracenica.id_institucija " +
				" AND institucija_drzava.id_drzava = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

		pstmtS1.setString(1, podnaziv1);
		pstmtS1.setInt(2, institucija.drzavaId);		

		//System.out.println(pstmtS1.toString());
		ResultSet result1 = pstmtS1.executeQuery();
		
		int rowCount1 = 0;  
		while ( result1.next() )  
		{  
		// Process the row.  
		rowCount1++;  
		}
		result1.beforeFirst();
		int max = 0;
		if (rowCount1 > 0) {
			 if (rowCount > 0) {
				while ((result.next()) && max <80){
					while (result1.next()) {

						if (result1.getInt(1) == result.getInt(1)){
							institucija.id = result.getInt(1);
							max =  80;
							break;
						} 
					}
					result1.beforeFirst();
				}
			 }else {
				 isti = 35;
			 }
		} 
		if (max == 80){
			isti = max;
		}
		result.beforeFirst();
		result1.beforeFirst();
		
		// grad
		
		PreparedStatement pstmtS2 = PostgresC.prepareStatement(" SELECT institucija_grad.id_institucija " +
			" FROM institucija_grad, sif_grad " +
			" WHERE institucija_grad.id_grad = sif_grad.id_grad " +
			" and sif_grad.naziv = ? " , ResultSet.TYPE_SCROLL_SENSITIVE,
		    ResultSet.CONCUR_READ_ONLY);
		
	//	System.out.println("id " + institucija.id);
	
		
		pstmtS2.setString(1, grad);
//		System.out.println(pstmtS2.toString());
		ResultSet result2 = pstmtS2.executeQuery();
		
		int rowCount2 = 0;  
		while ( result2.next() )  
		{  
		// Process the row.  
		rowCount2++;  
		}	
		result2.beforeFirst();
		max = 0;
		if (rowCount2 > 0){
			switch (isti) {
			case 0:
				isti = 10;
				break;
			case 20:
				while (result2.next()){
					while (result1.next()) {
						if (result2.getInt(1) == result1.getInt(1)){
							institucija.rezervniId = result2.getInt(1);
							max =  30;
							break;
						} 
					}
					result1.beforeFirst();
				}
				break;
			case 35:
				while (result2.next() && max <45){
					while (result.next()) {
						if (result2.getInt(1) == result.getInt(1)){
							institucija.rezervniId = result2.getInt(1);
							max = 45;
							break;
						} 
					}
					result.beforeFirst();
				}
				break;
			case 50:
				while (result2.next() && max < 60){
					while (result.next()) {
						if (result2.getInt(1) == result.getInt(1)){
							institucija.rezervniId = result2.getInt(1);
							max = 60;
							break;
						} 
					}
					result.beforeFirst();
				}
				break;
			case 80:
				while (result2.next()){	
					if (result2.getInt(1) == institucija.id){
						System.out.println(result2.getInt(1));
						
						max = 90;
						break;
					} 
				// ako se ispostavi da je pogresan grad treba sve ponova !!!
					

				}
				if (max < 90) {
					int [] nije = new int [1];
					nije[0] = institucija.id;
					//nadjiDruguInstituciju1Srbija(institucija, nije);
					System.out.println("kako da uparim kada imam vise");						
				}
				break;
			default:
				break;
				
			}
			if (max > isti ){ 
				isti = max;
			}
		} else {
			isti = 0;
		}
		//System.out.println(rowCount);
				
		result.close();
		pstmtS.close();
		result1.close();
		pstmtS1.close();
		pstmtS2.close();
		result2.close();
		return isti;
	}


	private boolean SNS(Institucija institucija, String naziv) {
		// TODO Auto-generated method stub
		return false;
	}
	



	public boolean imaLiInstitucijaDrzava(Institucija institucija) throws Exception {
		
		boolean isti = false;
		
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT institucija_drzava.id_drzava" +
				" FROM institucija_drzava, sif_drzava" +
				" WHERE institucija_drzava.id_drzava = sif_drzava.id_drzava " +
				" and sif_drzava.naziv  = ?");

		pstmtS.setString(1, institucija.drzava.trim());
		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		// Process the row.  
		rowCount++;  
		}  
		
		
		if (rowCount == 1){
			result.first();
			institucija.drzavaId = result.getInt(1);
			isti = true;
		} else if (rowCount > 1){
			isti = true;
		}
		
		result.close();
		pstmtS.close();
		
		return isti;
		
	}


	public int autorUInstB(int idAuor, int idInstitucija) throws SQLException {
		int ima = 0;
		
	
		
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_autor" +
																" FROM autor_institucija" +
																" WHERE id_autor = ?" +
																" and id_institucija  = ?");
		
		pstmtS.setInt(1,idAuor);
		pstmtS.setInt(2, idInstitucija);
		
//		System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		    // Process the row.  
		    rowCount++;  
		}  
		
		if (rowCount > 0){
			ima = 100;
		}
		return ima;
	}


	public int nadjiInstituciju2Srbija(Institucija institucija, String naziv,
			String podnaziv1, String podnaziv2, String grad) throws Exception {
		int isti =  0;
		
		int isti1 = nadjiInstituciju1Srbija(institucija, naziv, podnaziv1, grad);
		int isti2 = nadjiInstituciju1Srbija(institucija, naziv, podnaziv2, grad);
		isti =(isti1 >= isti2)? isti1 : isti2;
		return isti;

	}


	public int nadjiInstituciju3Srbija(Institucija institucija, String naziv,
			String podnaziv1, String podnaziv2, String podnaziv3, String grad) throws Exception {
		int isti =  0;
		
		int isti1 = nadjiInstituciju1Srbija(institucija, naziv, podnaziv1, grad);
		int isti2 = nadjiInstituciju1Srbija(institucija, naziv, podnaziv2, grad);
		int isti3 = nadjiInstituciju1Srbija(institucija, naziv, podnaziv3, grad);
		
		if ((isti1 >= isti2) && (isti1 >= isti3)){
			isti = isti1;
		} else if (isti2 >= isti3){
			isti = isti2;
		} else {
			isti = isti3;
		}
		return isti;
	}
	

	public int nadjiInstitucijuSrbija(Institucija institucija, String naziv, String grad) throws SQLException {
		int isti =  0;
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT institucija_skracenica.id_institucija" +
																" FROM institucija_skracenica, institucija_drzava" +
																" WHERE skracenica = ?"+
																" AND institucija_drzava.id_institucija = institucija_skracenica.id_institucija " +
																" AND institucija_drzava.id_drzava = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
															    ResultSet.CONCUR_READ_ONLY);
		pstmtS.setString(1, naziv);
		pstmtS.setInt(2, institucija.drzavaId);		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while (result.next())  
		{  
		    // Process the row.  
		    rowCount++;  
		}
		
		result.beforeFirst();
	
		if (naziv.contains("Univ")){
			if (rowCount >= 1){
				isti = 35;
			}
		} else {
			 if (rowCount >= 1){
				isti = 50;
			 } 
		}		
				// grad
		
		PreparedStatement pstmtS2 = PostgresC.prepareStatement(" SELECT institucija_grad.id_institucija " +
			" FROM institucija_grad, sif_grad " +
			" WHERE institucija_grad.id_grad = sif_grad.id_grad " +
			" and sif_grad.naziv = ? " , ResultSet.TYPE_SCROLL_SENSITIVE,
		    ResultSet.CONCUR_READ_ONLY);
		
	//	System.out.println("id " + institucija.id);
	
		pstmtS2.setString(1, grad);
		//System.out.println(pstmtS2.toString());
		ResultSet result2 = pstmtS2.executeQuery();
		
		int rowCount2 = 0;  
		while ( result2.next() )  
		{  
			// Process the row.  
			rowCount2++;  
		}	
		result2.beforeFirst();
		boolean nasao = false;
		//System.out.println(rowCount);
		if (rowCount2 > 0) {
			while (result.next()){
				while (result2.next() && !nasao){
					if (result.getInt(1)== result2.getInt(1)){
						isti = isti + 10;
						nasao = true;
						institucija.id = result.getInt(1);
						break;
					//	System.out.println("grad 1" +result.getString(1) + " string " + grad);
					}
				}
			//	System.out.println("grad " +result.getString(1) + " string " + grad);
			}
		}
		
		result.close();
		pstmtS.close();
		result2.close();
		pstmtS2.close();
		result2.close();
		return isti;
	}


	public int[] imaLiZemlja(String drzava) throws SQLException {
		int[] ima;
		// prvi kazi da li ima, a drugi koji je id
		ima = new int[2];
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_drzava" +
				" FROM sif_drzava" +
				" WHERE sif_drzava.naziv  = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

		pstmtS.setString(1, drzava);
		
//		System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		// Process the row.  
		rowCount++;  
		}  
		result.first();
		if (rowCount > 1){
		ima[0] = 1;
		} else if (rowCount == 1){
			ima[0] = 1;
			ima[1] = result.getInt(1);
		} else if (rowCount == 0){
			ima[0] = 0;
		}
		
		if ((ima[0] == 0) && (drzava.contains("USA"))){
			ima[0] = 1;
			ima[1] = 7;
		}
		
		result.close();
		pstmtS.close();
		
		return ima;
	}


	public boolean imaLiGrad(String grad) throws SQLException {
		boolean ima = false;
		
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_grad " +
				" FROM sif_grad " +
				" WHERE sif_grad.naziv  = ?");

		pstmtS.setString(1, grad);
		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		// Process the row.  
		rowCount++;  
		}  
		
		if (rowCount >= 1){
		ima = true;
		}
		
		result.close();
		pstmtS.close();
		
		return ima;
	}


	public void idZemlje(Institucija institucija) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_drzava" +
				" FROM sif_drzava" +
				" WHERE sif_drzava.naziv  = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

		pstmtS.setString(1, institucija.drzava.trim());
		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		// Process the row.  
		rowCount++;  
		}  
		result.first();
		if (rowCount > 1){
			institucija.drzavaId = 999;
		} else if (rowCount == 1){
			institucija.drzavaId = result.getInt(1);
			
		} else if (rowCount == 0){
			institucija.drzavaId = 0;
		}
		
		result.close();
		pstmtS.close();
		
	}


	public void idGrad(Institucija institucija) throws SQLException {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_grad " +
				" FROM sif_grad " +
				" WHERE sif_grad.naziv  = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

		pstmtS.setString(1, institucija.grad.trim());
		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		// Process the row.  
		rowCount++;  
		}  
		
		result.first();
		if (rowCount > 1){
			institucija.gradId = 99999;
		} else if (rowCount == 1){
			institucija.gradId = result.getInt(1);
			
		} else if (rowCount == 0){
			institucija.gradId = 0;
		}
		
		result.close();
		pstmtS.close();
		
		
	}

	public void idGradRezervni(Institucija institucija, int pozicija) throws SQLException {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_grad " +
				" FROM sif_grad " +
				" WHERE sif_grad.naziv  = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

		switch (pozicija) {
		case 2:
			pstmtS.setString(1, institucija.podnaziv1.trim());
			break;
		case 3:
			pstmtS.setString(1, institucija.podnaziv2.trim());
			break;
		case 4:
			pstmtS.setString(1, institucija.podnaziv3.trim());
			break;
		case 5:
			pstmtS.setString(1, institucija.podnaziv4.trim());
			break;			
		default:
			break;
		}
		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		// Process the row.  
		rowCount++;  
		}  
		
		result.first();
		if (rowCount > 1){
			institucija.rezervniId = 99999;
		} else if (rowCount == 1){
			institucija.rezervniId = result.getInt(1);
			
		} else if (rowCount == 0){
			institucija.rezervniId = 0;
		}
		
		result.close();
		pstmtS.close();
		
		
	}
	public int imaLiInstitucijaNaziv(Institucija institucija) throws SQLException {
		// TODO Auto-generated method stub
		int id = 0;
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_institucija " +
				" FROM institucija_i " +
				" WHERE naziv  = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

		pstmtS.setString(1, institucija.naziv.trim() + ", " + institucija.grad.trim() + ", " + institucija.drzava.trim());
		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		// Process the row.  
		rowCount++;  
		}  
		
		result.first();
		if (rowCount > 1){
			id = 999;
		} else if (rowCount == 1){
			id = result.getInt(1);
		} 
		result.close();
		pstmtS.close();
		
		return id;
		
	}


	public void unesiNazivInstitucije(Institucija institucija) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmtS = PostgresC.prepareStatement(" INSERT into institucija_i (naziv) values (?) ");

		pstmtS.setString(1, institucija.naziv.trim() + ", " + institucija.grad.trim() + ", " + institucija.drzava.trim());
		
		System.out.println(pstmtS.toString());
		try {
			pstmtS.executeUpdate();
		}catch (Exception e) {
		      e.printStackTrace();
	    } 
		
		pstmtS.close();
		
	}


	public void unesiOstatkeInstitucije(Institucija institucija, int idInstitB) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmtS = PostgresC.prepareStatement(" INSERT into institucija_skracenica (skracenica,id_institucija) values (?,?) ");

		pstmtS.setString(1, institucija.naziv.trim());
		pstmtS.setInt(2, idInstitB);
		
		System.out.println(pstmtS.toString());
		pstmtS.executeUpdate();
		
		if (institucija.podnaziv1 != null && !institucija.podnaziv1.isEmpty()){
			pstmtS = PostgresC.prepareStatement(" INSERT into institucija_skracenica (skracenica,id_institucija) values (?,?) ");

			pstmtS.setString(1, institucija.podnaziv1.trim());
			pstmtS.setInt(2, idInstitB);
			
			System.out.println(pstmtS.toString());
			pstmtS.executeUpdate();
			
			if (institucija.podnaziv2 != null &&  !institucija.podnaziv2.isEmpty()){
				pstmtS = PostgresC.prepareStatement(" INSERT into institucija_skracenica (skracenica,id_institucija) values (?,?) ");
	
				pstmtS.setString(1, institucija.podnaziv2.trim());
				pstmtS.setInt(2, idInstitB);
				
				System.out.println(pstmtS.toString());
				pstmtS.executeUpdate();
				
				if (institucija.podnaziv3 != null && !institucija.podnaziv3.isEmpty()){
					pstmtS = PostgresC.prepareStatement(" INSERT into institucija_skracenica (skracenica,id_institucija) values (?,?) ");
		
					pstmtS.setString(1, institucija.podnaziv3.trim());
					pstmtS.setInt(2, idInstitB);
					
					//System.out.println(pstmtS.toString());
					pstmtS.executeUpdate();
					
					if (institucija.podnaziv4 != null && !institucija.podnaziv4.isEmpty()){
						pstmtS = PostgresC.prepareStatement(" INSERT into institucija_skracenica (skracenica,id_institucija) values (?,?) ");
			
						pstmtS.setString(1, institucija.podnaziv4.trim());
						pstmtS.setInt(2, idInstitB);
						
						//System.out.println(pstmtS.toString());
						pstmtS.executeUpdate();
					}
				}
			}	
		}
		
		pstmtS = PostgresC.prepareStatement(" INSERT into institucija_drzava (id_institucija, id_drzava) values (?,?) ");

		pstmtS.setInt(1, idInstitB);
		pstmtS.setInt(2, institucija.drzavaId);
		
		System.out.println(pstmtS.toString());
		pstmtS.executeUpdate();
		
		pstmtS = PostgresC.prepareStatement(" INSERT into institucija_grad (id_institucija, id_grad) values (?,?) ");

		pstmtS.setInt(1, idInstitB);
		pstmtS.setInt(2, institucija.gradId);
		
		System.out.println(pstmtS.toString());
		pstmtS.executeUpdate();
		
		if ( institucija.rezervniId > 0 && institucija.rezervniId < 99999 ){
			pstmtS = PostgresC.prepareStatement(" INSERT into institucija_grad (id_institucija, id_grad) values (?,?)");

			pstmtS.setInt(1, idInstitB);
			pstmtS.setInt(2, institucija.rezervniId);
			
			//System.out.println(pstmtS.toString());
			pstmtS.executeUpdate();
		}
		
		pstmtS.close();
	}


	public boolean jeLiSrbija(String drzava) {
		// paziti za montenegro
		if (drzava.contains("Serbia")){ 
			return true;
		} else {
			return false;
		}
	}

	public int nadjiInstitucijuStrano(Institucija institucija, String naziv, String grad) throws SQLException {
		
		
		int isti =  0;
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT institucija_skracenica.id_institucija" +
																" FROM institucija_skracenica, institucija_drzava" +
																" WHERE skracenica = ?"+
																" AND institucija_drzava.id_institucija = institucija_skracenica.id_institucija " +
																" AND institucija_drzava.id_drzava = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
															    ResultSet.CONCUR_READ_ONLY);
		pstmtS.setString(1, naziv);
		pstmtS.setInt(2, institucija.drzavaId);		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while (result.next())  
		{  
		    // Process the row.  
		    rowCount++;  
		}
		
		result.beforeFirst();
		
			if (naziv.contains("Univ")){
				if (rowCount >= 1){
					isti = 50;
				}
			} else {
				 if (rowCount >= 1){
					isti = 30;
				 } 
			}		
					// grad
			
			PreparedStatement pstmtS2 = PostgresC.prepareStatement(" SELECT institucija_grad.id_institucija " +
				" FROM institucija_grad, sif_grad " +
				" WHERE institucija_grad.id_grad = sif_grad.id_grad " +
				" and sif_grad.naziv = ? " , ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);
			
		//	System.out.println("id " + institucija.id);
		
			pstmtS2.setString(1, grad);
			//System.out.println(pstmtS2.toString());
			ResultSet result2 = pstmtS2.executeQuery();
			
			int rowCount2 = 0;  
			while ( result2.next() )  
			{  
				// Process the row.  
				rowCount2++;  
			}	
			result2.beforeFirst();
			
			//System.out.println(rowCount);
			
			if (rowCount2 > 0){
				while (result.next()){
					while (result2.next()){
						if (result.getInt(1)== result2.getInt(1)){
							isti = isti + 10;
						//	System.out.println("grad 1" +result.getString(1) + " string " + grad);
						}
					}
				//	System.out.println("grad " +result.getString(1) + " string " + grad);
				}
			}
			
			result.close();
			pstmtS.close();
			result2.close();
			pstmtS2.close();
			result2.close();
			return isti;
			}


	public int nadjiInstituciju1Strano(Institucija institucija, String naziv, String podnaziv1, String grad) throws SQLException {
		int isti =  0;
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT institucija_skracenica.id_institucija" +
																" FROM institucija_skracenica, institucija_drzava" +
																" WHERE skracenica = ?"+
																" AND institucija_drzava.id_institucija = institucija_skracenica.id_institucija " +
																" AND institucija_drzava.id_drzava = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
															    ResultSet.CONCUR_READ_ONLY);
		
		pstmtS.setString(1, naziv);
		pstmtS.setInt(2, institucija.drzavaId);
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while (result.next())  
		{  
		    // Process the row.  
		    rowCount++;  
		}
		
		result.beforeFirst();
	
		if (naziv.contains("Univ")){
			if (rowCount >= 1){
				isti = 50;
			}
		} else {
			 if (rowCount >= 1){
				isti = 30;
			 } 
		}
		
		PreparedStatement pstmtS1 = PostgresC.prepareStatement(" SELECT institucija_skracenica.id_institucija" +
				" FROM institucija_skracenica, institucija_drzava" +
				" WHERE skracenica = ?"+
				" AND institucija_drzava.id_institucija = institucija_skracenica.id_institucija " +
				" AND institucija_drzava.id_drzava = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

		pstmtS1.setString(1, podnaziv1);
		pstmtS1.setInt(2, institucija.drzavaId);		

		//System.out.println(pstmtS1.toString());
		ResultSet result1 = pstmtS1.executeQuery();
		
		int rowCount1 = 0;  
		while ( result1.next() )  
		{  
		// Process the row.  
		rowCount1++;  
		}
		result1.beforeFirst();
		int max = 0;
		if (rowCount1 > 0) {
			 if (rowCount > 0) {
				while (result1.next()){
					while (result.next()) {
						if (result1.getInt(1) == result.getInt(1)){
							institucija.id = result.getInt(1);
							max = 80;
							break;
						} else {
							isti = 30;
						}
					}
				result.beforeFirst();
				}
			 } else {
				 isti = 31;
			 }
		} 
		if (max == 80){
			isti = max;
		}
		
		result.beforeFirst();
		result1.beforeFirst();
		
		// grad
		
		PreparedStatement pstmtS2 = PostgresC.prepareStatement(" SELECT institucija_grad.id_institucija " +
			" FROM institucija_grad, sif_grad " +
			" WHERE institucija_grad.id_grad = sif_grad.id_grad " +
			" and sif_grad.naziv = ? " , ResultSet.TYPE_SCROLL_SENSITIVE,
		    ResultSet.CONCUR_READ_ONLY);
		
	//	System.out.println("id " + institucija.id);
	
		
		pstmtS2.setString(1, grad);
		//System.out.println(pstmtS2.toString());
		ResultSet result2 = pstmtS2.executeQuery();
		
		int rowCount2 = 0;  
		while ( result2.next() )  
		{  
		// Process the row.  
		rowCount2++;  
		}	
		result2.beforeFirst();
		
		if (rowCount2 > 0){
			switch (isti) {
			case 0:
				isti = 10;
				break;
			case 30:
				while (result2.next()){
					while (result1.next()) {
						if (result2.getInt(1) == result1.getInt(1)){
							institucija.rezervniId = result2.getInt(1);
							max =  40;
							break;
						} 
					}
					result1.beforeFirst();
				}
				break;
			case 50:	
				while (result2.next()){
					while (result.next()) {
						if (result2.getInt(1) == result.getInt(1)){
							institucija.rezervniId = result2.getInt(1);
							max =  60;
							break;
						} 
					}
					result.beforeFirst();
				}
				break;
			case 60:
				while (result2.next()){
					while (result.next()) {
						if (result2.getInt(1) == result.getInt(1)){
							institucija.rezervniId = result2.getInt(1);
							max =  70;
							break;
						} 
					}
					result.beforeFirst();
				}
				break;
			case 80:
				while (result2.next()){	
					if (result2.getInt(1) == institucija.id){
						max =  90;
						break;
					} 
				}
				break;
			default:
				break;
			}
			if (max > isti){
				isti = max;
			}
		} else { 
			isti = 0;
		}
		//System.out.println(rowCount);
				
		result.close();
		pstmtS.close();
		result1.close();
		pstmtS1.close();
		pstmtS2.close();
		result2.close();
		return isti;
	}


	public int nadjiInstituciju2Strano(Institucija institucija, String naziv, String podnaziv1, String podnaziv2, String grad) throws SQLException {
	
		int isti =  0;
		
		int isti1 = nadjiInstituciju1Strano(institucija, naziv, podnaziv1, grad);
		int isti2 = nadjiInstituciju1Strano(institucija, naziv, podnaziv2, grad);
		isti =(isti1 >= isti2)? isti1 : isti2;
		return isti;

	}


	public int nadjiInstituciju3Strano(Institucija institucija, String naziv, String podnaziv1, String podnaziv2, String podnaziv3,
			String grad) throws Exception {
				
		int isti =  0;
		
		int isti1 = nadjiInstituciju1Strano(institucija, naziv, podnaziv1, grad);
		int isti2 = nadjiInstituciju1Strano(institucija, naziv, podnaziv2, grad);
		int isti3 = nadjiInstituciju1Strano(institucija, naziv, podnaziv3, grad);
		
		if ((isti1 >= isti2) && (isti1 >= isti3)){
			isti = isti1;
		} else if (isti2 >= isti3){
			isti = isti2;
		} else {
			isti = isti3;
		}
		return isti;
	}

	public int upariDrzavu(Institucija institucija, int isti) throws SQLException {
		int dodatak = 0;
		if (institucija.drzavaId > 0) {
			PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT institucija_drzava.id_institucija " +
					" FROM institucija_drzava " +
					" WHERE institucija_drzava.id_drzava = ? " , ResultSet.TYPE_SCROLL_SENSITIVE,
				    ResultSet.CONCUR_READ_ONLY);
							
			pstmtS.setInt(1, institucija.drzavaId);
			//System.out.println(pstmtS2.toString());
			ResultSet result = pstmtS.executeQuery();
			
			int rowCount = 0;  
			while (result.next()) {  
				// Process the row.  
				rowCount++;  
				}
			result.beforeFirst();
			
			if (rowCount > 0) {
				 switch (isti) {
				case 0:
					dodatak = 10;
					break;
				case 30:
					//ima naziv
					dodatak = proveriDrzavuZaSkracenicu(institucija, 1);
				case 31:
					//ima podnaziv
					dodatak = proveriDrzavuZaSkracenicu(institucija, 2);
				case 40:
					while (result.next()) {  
						if (result.getInt(1)==institucija.rezervniId){
							dodatak = 10;
							break;
						} 
					}
				case 60:
					while (result.next()) {  
						if (result.getInt(1)==institucija.rezervniId){
							dodatak = 10;
							break;
						}
					}
				case 80:
					while (result.next()) {  
						if (result.getInt(1)==institucija.id){
							dodatak = 10;
							break;
						}
					}
				case 90:
					while (result.next()) {  
						if (result.getInt(1)==institucija.id){
							dodatak = 10;
							break;
						}
					}
				default:
					break;
				}
			}
			
		}
		// TODO Auto-generated method stub
		return dodatak;
	}


	


	private int proveriDrzavuZaSkracenicu(Institucija institucija, int slucaj) throws SQLException {
		int dodatak = 0;
		// TODO Auto-generated method stub
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT institucija_drzava.id_drzava " +
				" FROM institucija_drzava " +
				" WHERE institucija_drzava.id_drzava = ? " , ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);
						
		pstmtS.setInt(1, institucija.drzavaId);
		//System.out.println(pstmtS2.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while (result.next()) {  
			// Process the row.  
			rowCount++;  
		}
		result.beforeFirst();
		
		if (rowCount > 0) {
			PreparedStatement pstmtS1 = PostgresC.prepareStatement(" SELECT id_institucija" +
											" FROM institucija_skracenica" +
											" WHERE skracenica = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
										    ResultSet.CONCUR_READ_ONLY);
							
			if (slucaj == 1) {
				pstmtS1.setString(1, institucija.naziv.trim());;
			} else {
				pstmtS1.setString(1, institucija.podnaziv1.trim());;
			}

			//System.out.println(pstmtS2.toString());
			ResultSet result1 = pstmtS.executeQuery();
			
			int rowCount1 = 0;  
			while (result1.next()) {  
				// Process the row.  
				rowCount1++;  
				}
			result1.beforeFirst();
			
			if (rowCount1 > 0) {
				while (result1.next()){
					while (result.next()){
						if(result.getInt(1) == result1.getInt(1)){
							dodatak = 10;
						}
					}
				}
			} else {
				dodatak = 5;
			}
			
		}
		return dodatak;
	}


	public void unesiNazivInstitucijeS(Institucija institucija) throws SQLException {
		// TODO Auto-generated method stub
		
		if (institucija.podnaziv1 != null && !institucija.podnaziv1.isEmpty()){
			PreparedStatement pstmtS = PostgresC.prepareStatement(" INSERT into institucija_i (naziv) values (?) ");
	
			pstmtS.setString(1, institucija.naziv.trim() + ", " + institucija.podnaziv1.trim() + ", " +
					institucija.grad.trim() + ", " + institucija.drzava.trim());
			
			System.out.println(pstmtS.toString());
			try {
				pstmtS.executeUpdate();
			}catch (Exception e) {
			      e.printStackTrace();
			}
			pstmtS.close();
		} else {
			PreparedStatement pstmtS = PostgresC.prepareStatement(" INSERT into institucija_i (naziv) values (?) ");
			
			pstmtS.setString(1, institucija.naziv.trim() + ", " + institucija.grad.trim() + ", " + institucija.drzava.trim());
			
			System.out.println(pstmtS.toString());
			try {
				pstmtS.executeUpdate();
			}catch (Exception e) {
			      e.printStackTrace();
			}
			pstmtS.close();
		}
		
	}


	public int imaLiInstitucijaNazivS(Institucija institucija) throws SQLException {
		int id = 0;
		PreparedStatement pstmtS = PostgresC.prepareStatement(" SELECT id_institucija " +
				" FROM institucija_i " +
				" WHERE naziv  = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

		pstmtS.setString(1, institucija.naziv.trim() + ", " + institucija.podnaziv1.trim() + ", " +
				institucija.grad.trim() + ", " + institucija.drzava.trim());
		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		int rowCount = 0;  
		while ( result.next() )  
		{  
		// Process the row.  
		rowCount++;  
		}  
		
		result.first();
		if (rowCount > 1){
			id = 999;
		} else if (rowCount == 1){
			id = result.getInt(1);
		} 
		result.close();
		pstmtS.close();
		
		return id;
	}


	public boolean imaLiVUGrad(Institucija institucija, String ustanova) throws SQLException {
		boolean ima = false;
		
		PreparedStatement pstmtS = PostgresC.prepareStatement("  SELECT institucija_grad.id_institucija " +
			" FROM institucija_grad, sif_grad " +
			" WHERE institucija_grad.id_grad = sif_grad.id_grad " +
			" and sif_grad.naziv = ? " , ResultSet.TYPE_SCROLL_SENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);

		pstmtS.setString(1, institucija.grad.trim());
		
		//System.out.println(pstmtS.toString());
		ResultSet result = pstmtS.executeQuery();
		
		
		while ( result.next() )  
		{  

			PreparedStatement pstmtS1 = PostgresC.prepareStatement(" SELECT skracenica" +
			" FROM institucija_skracenica" +
			" WHERE id_institucija = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			pstmtS1.setInt(1, result.getInt(1));
			ResultSet result1 = pstmtS1.executeQuery();//da nije drugi statement
			
			while (result1.next()){
				if (result1.getString(1).contains(ustanova)){
					ima = true;
					break;
				}
			}
			if (ima){
				break;
			
			}
						
		}  
		
		return ima;
	}

}
