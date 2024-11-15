package com.nt.entity;

import java.util.Collection;
import java.util.HashSet;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	
	private String email;
	private String password;
    
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
	        name = "User_Roles", joinColumns = 
	        @JoinColumn(name="user_id",referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id")
	    )
	private Collection<Roles> roles = new HashSet<Roles>();

}
