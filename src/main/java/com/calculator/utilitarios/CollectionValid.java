package pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.utilitarios;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.Optional;



public class CollectionValid {

	public static Boolean validOpt(Optional<?> opt) {
		return (!(isNull(opt) || !opt.isPresent()));
	}

	public static Boolean isContent(List<?> lst) {
		return (!(isNull(lst) || lst.isEmpty()));

	}

}
