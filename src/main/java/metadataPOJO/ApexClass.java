package metadataPOJO;



import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ApexClass {
	private String Id;
	private String LastModifiedDate;
	private String Name;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getLastModifiedDate() {
		return LastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@Override
	public String toString() {
		return "ApexClass [Id=" + Id + ", LastModifiedDate=" + LastModifiedDate + ", Name=" + Name + "]";
	}
	
	

}
