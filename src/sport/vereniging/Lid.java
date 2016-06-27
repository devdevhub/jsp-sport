package sport.vereniging;

import java.util.Date;

public class Lid {
    private String spelerscode, roepnaam, 
        tussenvoegsels, achternaam, email;
    private Date geb_datum;
    
    public Lid() {
        this.spelerscode = "";
        this.roepnaam = "";
        this.tussenvoegsels = "";
        this.achternaam = "";
        this.email = "";
        this.geb_datum = new Date();
    }

    public Lid (String roepnaam,
                String tussenvoegsels, 
                String achternaam,
                String email,
                Date geb_datum) {
        
    	this.spelerscode = email;
        this.roepnaam = roepnaam;
        this.tussenvoegsels = tussenvoegsels;
        this.achternaam = achternaam;
        this.email = email;
        this.geb_datum = geb_datum;
    }
    
    /********* GETTERS EN SETTERS ************/
    
	public String getSpelerscode() {
		return spelerscode;
	}

	public void setSpelerscode(String spelerscode) {
		this.spelerscode = spelerscode;
	}

	public String getRoepnaam() {
		return roepnaam;
	}

	public void setRoepnaam(String roepnaam) {
		this.roepnaam = roepnaam;
	}

	public String getTussenvoegsels() {
		return tussenvoegsels;
	}

	public void setTussenvoegsels(String tussenvoegsels) {
		this.tussenvoegsels = tussenvoegsels;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	
	public String getNaam() {
        String naam;
        if (tussenvoegsels.equals("")) {
            naam = roepnaam + " " + achternaam;
        } else {
            naam = roepnaam + " " + tussenvoegsels + " " + achternaam;
        }
        return naam;
    }
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getGeb_datum() {
		return geb_datum;
	}
	
	public void setGeb_datum(Date geb_datum) {
		this.geb_datum = geb_datum;
	}

}