package com.nt.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	
	private String email;
	private String password;
    
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
	        name = "Users_Roles", joinColumns =
	        @JoinColumn(name="user_id",referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id")
	    )
	private Collection<Roles> roles = new HashSet<Roles>();


	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
			name = "User_Books",
			joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "book_id",referencedColumnName = "id")
	)
	private Set<Book> books = new HashSet<>();

}
