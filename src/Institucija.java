
public class Institucija {
	
	String naziv, podnaziv1, podnaziv2, podnaziv3, podnaziv4, grad, drzava;
	int brS, procenat, id, gradId, drzavaId, rezervniId;
	
	public Institucija(String naziv, String podnaziv1, String podnaziv2, String podnaziv3,
			String podnaziv4, String grad, String drzava) {
		super();
		this.naziv = naziv;
		this.podnaziv1 = podnaziv1;
		this.podnaziv2 = podnaziv2;
		this.podnaziv3 = podnaziv3;
		this.podnaziv4 = podnaziv4;
		this.grad = grad;
		this.drzava = drzava;
		this.procenat = 0;
		this.gradId = 0;
		this.drzavaId = 0;
		this.rezervniId = 0;
		this.brS = 7;
	}

	public Institucija(String naziv, String podnaziv1, String podnaziv2, String podnaziv3,
			String grad, String drzava) {
		super();
		this.naziv = naziv;
		this.podnaziv1 = podnaziv1;
		this.podnaziv2 = podnaziv2;
		this.podnaziv3 = podnaziv3;
		this.grad = grad;
		this.drzava = drzava;
		this.procenat = 0;
		this.gradId = 0;
		this.drzavaId = 0;
		this.rezervniId = 0;		
		this.brS = 6;
	}
	
	public Institucija(String naziv, String podnaziv1, String podnaziv2,
			String grad, String drzava) {
		super();
		this.naziv = naziv;
		this.podnaziv1 = podnaziv1;
		this.podnaziv2 = podnaziv2;
		this.grad = grad;
		this.drzava = drzava;
		this.procenat = 0;
		this.gradId = 0;
		this.drzavaId = 0;
		this.rezervniId = 0;
		this.brS = 5;
	}
	
	public Institucija(String naziv, String podnaziv1, 
			String grad, String drzava) {
		super();
		this.naziv = naziv;
		this.podnaziv1 = podnaziv1;
		this.grad = grad;
		this.drzava = drzava;
		this.procenat = 0;
		this.gradId = 0;
		this.drzavaId = 0;
		this.rezervniId = 0;
		this.brS = 4;
	}
	
	public Institucija(String naziv, String grad, String drzava) {
		super();
		this.naziv = naziv;
		this.grad = grad;
		this.drzava = drzava;
		this.procenat = 0;
		this.gradId = 0;
		this.drzavaId = 0;
		this.rezervniId = 0;
		this.brS = 3;
	}
	
	public Institucija(String naziv) {
		super();
		this.naziv = naziv;
		this.procenat = 0;
		this.gradId = 0;
		this.drzavaId = 0;
		this.rezervniId = 0;
		this.brS = 1;
	}
	
	public void stampajInst(){
		System.out.println("brS - " + this.brS);
		System.out.println("naziv - " + this.naziv);
		System.out.println("podnaziv1 - " + this.podnaziv1);
		System.out.println("podnaziv2 - " + this.podnaziv2);
		System.out.println("podnaziv3 - " + this.podnaziv3);
		System.out.println("podnaziv4 - " + this.podnaziv4);
		System.out.println("grad - " + this.grad);
		System.out.println("drzava - " + this.drzava);
		
	}

	public boolean ista(Institucija institucijaP) {
		// TODO Auto-generated method stub
		boolean iste = false;
		switch (institucijaP.brS) {
		case 1:
			if((this.brS == institucijaP.brS)&&(this.naziv.equals(institucijaP.naziv))){
				iste = true;
			}
			break;
		case 3:
			if((this.brS == institucijaP.brS)&&(this.naziv.equals(institucijaP.naziv))&&(this.grad.equals(institucijaP.grad))
					&&(this.drzava.equals(institucijaP.drzava))){
				iste = true;
			}			
			break;
		case 4:
			if((this.brS == institucijaP.brS)&&(this.naziv.equals(institucijaP.naziv)) && (this.podnaziv1.equals(institucijaP.podnaziv1))&&
					(this.grad.equals(institucijaP.grad)) && (this.drzava.equals(institucijaP.drzava))){
				iste = true;
			}
			break;
		case 5:
			if((this.brS == institucijaP.brS)&&(this.naziv.equals(institucijaP.naziv)) && (this.podnaziv1.equals(institucijaP.podnaziv1))&&
					(this.podnaziv2.equals(institucijaP.podnaziv2)) && (this.grad.equals(institucijaP.grad)) && (this.drzava.equals(institucijaP.drzava))){
				iste = true;
			}
			break;
		case 6:
			if((this.brS == institucijaP.brS)&&(this.naziv.equals(institucijaP.naziv)) && (this.podnaziv1.equals(institucijaP.podnaziv1)) &&
					(this.podnaziv2.equals(institucijaP.podnaziv2)) && (this.podnaziv3.equals(institucijaP.podnaziv3)) && (this.grad.equals(institucijaP.grad))
					&& (this.drzava.equals(institucijaP.drzava))){
				iste = true;
			}
			break;
		case 7:
			if((this.brS == institucijaP.brS)&&(this.naziv.equals(institucijaP.naziv)) && (this.podnaziv1.equals(institucijaP.podnaziv1)) &&
					(this.podnaziv2.equals(institucijaP.podnaziv2)) && (this.podnaziv3.equals(institucijaP.podnaziv3)) && (this.podnaziv4.equals(institucijaP.podnaziv4))
					&& (this.grad.equals(institucijaP.grad)) && (this.drzava.equals(institucijaP.drzava))){
				iste = true;
			}
	break;
		default:
			break;
		}
		return iste;
	}
}


