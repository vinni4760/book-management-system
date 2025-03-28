package com.nt.entity;

import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
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
