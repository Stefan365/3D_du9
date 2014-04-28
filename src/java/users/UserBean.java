package users;

/**
 *
 * @author Jaroslav Srp / Stefan Veres
 */
public class UserBean {
  	private String first_name;
    private String last_name;
    private String birth_year;
    
    
    //getters:
    public String getFirstname() {
		return first_name;
	}

    public String getLastname() {
		return this.last_name;
	}

    public String getBirthyear() {
		return this.birth_year;
	}
    
    
    //setters:
  	public void setFirstname(String fn) {
		this.first_name = fn;
	}

	public void setLastname(String ln) {
		this.last_name = ln;
	}

	public void setBirthyear(String by) {
		this.birth_year  = by;
	}
    
    
    
   	
    //others:
	
    /**
     * Skontroluje, jestli su vyplnene vsetky policka formulara.
     *
     * @return (true/false) jestli bol formular vyplneny spravne.
     */
    public boolean hasValidData() {
		return (!"".equals(this.first_name) && !"".equals(this.last_name) && !"".equals(this.birth_year) && 
            this.first_name != null && this.last_name != null && this.birth_year != null);
        
	}

    
    public boolean isEmpty() {
		return (this.first_name == null && this.last_name == null && this.birth_year == null);
	}
}
