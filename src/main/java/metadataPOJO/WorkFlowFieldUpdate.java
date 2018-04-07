package metadataPOJO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WorkFlowFieldUpdate {
	private String id;
	private String name;
	private String lastModifiedDate;
	private String sourceTableEnumOrId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSourceTableEnumOrId() {
		return sourceTableEnumOrId;
	}
	public void setSourceTableEnumOrId(String sourceTableEnumOrId) {
		this.sourceTableEnumOrId = sourceTableEnumOrId;
	}

}
