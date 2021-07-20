package br.com.home.uploads;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/uploads")
public class UploadController {

	@Autowired
	private FirebaseStorageService uploadService;

	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity upload(@RequestBody UploadInput uploadInput) throws IOException {
		String url = uploadService.upload(uploadInput);
		return ResponseEntity.ok(new UploadOutput(url));
	}

}
