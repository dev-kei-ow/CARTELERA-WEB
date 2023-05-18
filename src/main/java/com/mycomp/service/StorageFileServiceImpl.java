package com.mycomp.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mycomp.exception.*;

@Service
public class StorageFileServiceImpl implements StorageService {

	@Value("${storage.location}")
	private String storageLocation;

	@PostConstruct // INDICA QUE ESTE METODO SE VA EJECUTAR POR CADA INSTANCIA DE ESTA CLASE O LA INTERFAZ PADRE
	@Override
	public void init() {

		try {

			/* ::CREAR EL DIRECTORIO DONDE VAMOS A ALAMACENAR LAS IMAGENES:: */
			Files.createDirectories(Paths.get(storageLocation));

		} catch (IOException e) {

			throw new StorageException("Error al inicializar la ubicacion del almacen de archivos.");
		}

	}

	@Override
	public String storage(MultipartFile file) {

		/* ::OBTENER EL NOMBRE ORIGINAL DEL ARCHIVO:: */
		String fileName = file.getOriginalFilename();

		if (file.isEmpty()) {

			throw new StorageException("No se puede almacenar un archivo vacio.");

		}

		try {

			InputStream inputStream = file.getInputStream();

			/* SI EXISTE EL ARCHIVO , SE COPIA O SE REEMPLAZA */
			Files.copy(inputStream, Paths.get(storageLocation).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {

			throw new StorageException("Error al almacenar el archivo " + fileName, e);
		}

		return fileName;

	}

	@Override
	public Path load(String filename) {

		/* OBTENER EL ARCHIVO Y CARGAMOS DEL ARCHIVO */
		return Paths.get(storageLocation).resolve(filename);

	}

	@Override
	public Resource loadAsResource(String filename) {

		try {
			/* CARGAR EL ARCHIVO */
			Path file = load(filename);

			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {

				return resource;

			} else {

				throw new FileNotFoundException("No se pudo encontrar el archivo: " + filename);

			}

		} catch (MalformedURLException e) {

			throw new FileNotFoundException("No se pudo encontrar el archivo: " + filename, e);

		}

	}

	@Override
	public void deleteFile(String filename) {
		Path file = load(filename);

		try {

			FileSystemUtils.deleteRecursively(file);

		} catch (IOException e) {

			System.out.println(e);

		}

	}

}
