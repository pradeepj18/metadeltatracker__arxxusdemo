package metadataPOJO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WorkFlowAlert {
	private String id;
	private String fullName;
	private String lastModifiedDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
