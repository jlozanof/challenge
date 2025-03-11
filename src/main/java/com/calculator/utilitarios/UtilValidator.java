package pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.utilitarios;

import static java.util.Objects.isNull;

public class UtilValidator {

	public static Boolean validId(Long id){
		return (isNull(id) || id<=0);
	}

}
