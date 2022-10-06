package com.dxc.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class Trainer {
    @Id
    @GeneratedValue(generator = "TId")
	@GenericGenerator(name="TId", strategy = "com.dxc.util.TrainerIdGenerator")
    private int trainerid;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;

    public int getTrainerid() {
		return trainerid;
	}

	public void setTrainerid(int trainerid) {
		this.trainerid = trainerid;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

