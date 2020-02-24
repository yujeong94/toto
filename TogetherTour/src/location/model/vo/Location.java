package location.model.vo;

public class Location {
	private int kind;
	private String country;
	private String city;
	
	public Location() {}

	public Location(int kind, String country, String city) {
		super();
		this.kind = kind;
		this.country = country;
		this.city = city;
	}
	
	public Location(String country, String city) {
		this.country = country;
		this.city = city;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	} 
}
