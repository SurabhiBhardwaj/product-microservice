package com.example.productMS.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	// how to use entity graph
	// flyway implementation
	// Junits for controller(Mock MVC), service layer(Mockito)
	// integration-tests

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int cid;

	@Column(name = "category_name")
	String categoryName;

	@ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<Product> products = new HashSet<>();

	public void addProduct(Product product) {
		products.add(product);
		product.getCategories().add(this);
	}

}
