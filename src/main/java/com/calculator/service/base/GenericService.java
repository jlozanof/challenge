package pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.service.base;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, R> { // T,K,V,S,ID.

	 List<T> list(T t) throws ServiceException;
	 
	 Optional<T> findById(T t) throws ServiceException;
	
	 R add(T t) throws ServiceException; //-1,0,1
	
	 R update(T t) throws ServiceException;
	 
	 R delete(T t) throws ServiceException;

}
