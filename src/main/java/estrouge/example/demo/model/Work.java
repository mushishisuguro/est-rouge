package estrouge.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "work")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Work implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "workname")
	private String workName;
	
	@Column(name = "startingdate")
	private Date startingDate;
	
	@Column(name = "endingdate")
	private Date endingDate;
	
	@Column(name = "status")
	private String status;
	
	@Id
	@Column(name = "workid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long workId;
	public Work(){
		workId=0;
	}
	public Work(int workId, String workName, Date startingDate, Date endingDate, String status) {
		this.workId = workId;
		this.workName = workName;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.status = status;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public Date getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getWorkId() {
		return workId;
	}
	public void setWorkId(long workId) {
		this.workId = workId;
	}
}
