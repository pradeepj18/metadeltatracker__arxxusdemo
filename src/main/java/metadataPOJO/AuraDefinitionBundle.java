package metadataPOJO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuraDefinitionBundle {
	private String id;
	private String developerName;
	private String LastModifiedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getLastModifiedDate() {
		return LastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}

}
