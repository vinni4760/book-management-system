package com.nt.entity;

import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
      private Integer id;
      private String role;
      @JsonIgnore
      @ManyToMany(mappedBy="roles")
      private Collection<User> users = new HashSet<User>();
      
      public Roles(String name) {
    	  this.role = name;
      }
}
