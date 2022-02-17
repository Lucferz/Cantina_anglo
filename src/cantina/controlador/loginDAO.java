package cantina.controlador;
import cantina.modelo.login;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface loginDAO {
    
    public login log(String usuario, String pass);
    
    
//    public void insertar(Producto prod);
//    public void modificar(Producto prod);
//    public void borrar(Long codigo);
//
//    public Producto buscarPorCodigo(Long codigo);
//    public List<Producto> buscarTodos();
//    public List<Producto> buscarPorCampo(String campo, String valor);
}
