package com.mycomp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Entity
@Table(name = "peliculas")
@Data
public class Pelicula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPelicula;

	@NotNull
	@NotBlank
	private String titulo;
	private String duracion;
	private String clasificacion;
	private String idioma;
	@NotNull
	private String trailerId;
	private String formato;
	private String sinopsis;
	private String rutaPortada;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "generos_peliculas",
	joinColumns = @JoinColumn(name = "idPelicula"),
	inverseJoinColumns = @JoinColumn(name = "idGenero"))
	private List<Genero> generosList;
	
	@Transient
	private MultipartFile portada;

}
