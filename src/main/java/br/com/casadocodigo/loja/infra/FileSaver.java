package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	private String SLASH = System.getProperty("file.separator");
	private String HOME_FOLDER = System.getProperty("user.home");

	public String salvar(String baseFolder, MultipartFile file) {
		
		String baseFolderPath = HOME_FOLDER + SLASH + baseFolder;
		String filePath = baseFolderPath + SLASH + file.getOriginalFilename();
		
		try {
			file.transferTo(new File(filePath));
			return filePath;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	

}
