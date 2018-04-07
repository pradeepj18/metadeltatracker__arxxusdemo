package metadataPOJO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WorkFlowRule {
	private String id;
	private String name;
	private String lastModifiedDate;
	private String tableEnumOrId;
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
	public String getTableEnumOrId() {
		return tableEnumOrId;
	}
	public void setTableEnumOrId(String tableEnumOrId) {
		this.tableEnumOrId = tableEnumOrId;
	}

}
