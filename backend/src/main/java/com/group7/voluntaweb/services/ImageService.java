package com.group7.voluntaweb.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Service
@Configuration
public class ImageService implements WebMvcConfigurer {
	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");

	private Path createFilePath(Path folder) {
		Date date = new Date();
		String fileName = "image-" + date.getTime() + ".jpg";
		return folder.resolve(fileName);
	}

	private Path retrieveFilePath(String name, Path folder) {

		return folder.resolve(name);

	}

	public Path saveImage(String folderName, MultipartFile image) throws IOException {
		Path folder = FILES_FOLDER.resolve(folderName);
		if (!Files.exists(folder)) {
			Files.createDirectories(folder);
		}

		Path newFile = createFilePath(folder);
		image.transferTo(newFile);
		return newFile;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
				.addResourceLocations("file:" + FILES_FOLDER.toAbsolutePath().toString() + "/");
	}

	public ResponseEntity<Object> createResponseFromImage(String folderName, String name) throws MalformedURLException {
		Path folder = FILES_FOLDER.resolve(folderName);
		Resource file = new UrlResource(retrieveFilePath(name, folder).toUri());
		if (file.exists()) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(file);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}