package pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.controller.base;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.controller.commons.CustomResponse;
import pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.controller.exceptions.ControllerException;
import pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.controller.exceptions.NotContentException;
// 
import static java.util.Objects.isNull;

// 
import static pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.utilitarios.CollectionValid.isContent;
import static pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.controller.constants.ApiMessageConstant.API_MSG_RESPONSE_CONSULTA;
import static pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.controller.constants.ApiMessageConstant.API_MSG_RESPONSE_REGISTRO_EXITO;
import static pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.controller.constants.ApiMessageConstant.API_MSG_RESPONSE_REGISTRO_ERROR;
import static pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.controller.constants.ApiMessageConstant.API_MSG_RESPONSE_INTERNAL_ERROR;

import static  pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.utilitarios.UtilValidator.validId;

public abstract class GenericController {

	protected ResponseEntity<CustomResponse> internalError() {
		CustomResponse customResponse = CustomResponse.builder()
				.message(API_MSG_RESPONSE_INTERNAL_ERROR)
				.httpCode(HttpStatus.INTERNAL_SERVER_ERROR.value() + "-" 
				+ HttpStatus.INTERNAL_SERVER_ERROR.name())
				.dataTime(LocalDateTime.now().toString())
				.build();
		return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	protected ResponseEntity<List<Map<String, String>>> getErrors(BindingResult result) {
		List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
			
			Map<String, String> error = new HashMap<>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;
		}
		).toList();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	protected ResponseEntity<CustomResponse> response(Object res) {
		if (isNull(res)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		CustomResponse customResponse = CustomResponse.builder()
				.message(API_MSG_RESPONSE_CONSULTA)
				.httpCode(HttpStatus.OK.value() + "-" + HttpStatus.OK.name()).dataTime(LocalDateTime.now().toString())
				.data(res).build();
		return new ResponseEntity<>(customResponse, HttpStatus.OK);
	}
	
	protected ResponseEntity<CustomResponse> created(Object data) {
		CustomResponse customResponse = CustomResponse.builder()
				.message(API_MSG_RESPONSE_REGISTRO_EXITO)
				.httpCode(HttpStatus.CREATED.value() + "-" + HttpStatus.CREATED.name())
				.dataTime(LocalDateTime.now().toString())
				.data(data).build();
		return new ResponseEntity<>(customResponse,HttpStatus.CREATED);
		
	}
	protected ResponseEntity<CustomResponse> badRequest(String message/*, Object data*/) {
		
		CustomResponse customResponse = CustomResponse.builder()
				//.message(API_MSG_RESPONSE_REGISTRO_ERROR)
				.message(message)
				.httpCode(HttpStatus.BAD_REQUEST.value() + "-" + HttpStatus.BAD_REQUEST.name())
				.dataTime(LocalDateTime.now().toString())
				.build();

		return new ResponseEntity<>(customResponse,HttpStatus.BAD_REQUEST);
		
	}
	
	protected Boolean ValidIdGen(Long id) {
		return validId(id);
	}
	protected ResponseEntity<CustomResponse> getResponse(List<?> lst) throws NotContentException {
		if (!isContent(lst)) {
			throw new NotContentException();
		}
		CustomResponse customResponse = CustomResponse.builder()
				.message(API_MSG_RESPONSE_CONSULTA + lst.size())
				.httpCode(HttpStatus.OK.value() + "-" + HttpStatus.OK.name()).dataTime(LocalDateTime.now().toString())
				.data(lst).build();
		return ResponseEntity.ok(customResponse);
	}
	
	protected ControllerException getException(Exception e) {
		return new ControllerException(e);
	}
	

}
