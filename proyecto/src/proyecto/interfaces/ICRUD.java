package proyecto.interfaces;

import java.util.List;
import proyecto.Excepciones.DatosInvalidosException;

// Requisito: Interfaces que implementen polimorfismo
public interface ICRUD<T> {

    // CRUD Básico
    public void agregar(T entidad) throws DatosInvalidosException, Exception;
    void modificar(T entidad) throws DatosInvalidosException, Exception;
    void eliminar(int id) throws DatosInvalidosException, Exception;
    
    // Búsqueda y Filtrado
    List<T> buscarTodo() throws Exception;
    
    // Requisito: Sobrecarga de Métodos
    T buscar(int id) throws DatosInvalidosException, Exception;
    List<T> buscar(String nombre) throws Exception; 

}