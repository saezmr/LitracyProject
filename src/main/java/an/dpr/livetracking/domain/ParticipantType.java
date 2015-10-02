package an.dpr.livetracking.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ParticipantType {

    private Long id;
    private String name;
    private String description;


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Column
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Column
    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
}