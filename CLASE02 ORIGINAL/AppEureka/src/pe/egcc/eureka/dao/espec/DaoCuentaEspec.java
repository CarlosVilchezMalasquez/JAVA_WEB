package pe.egcc.eureka.dao.espec;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Gustavo Coronel
 */
public interface DaoCuentaEspec {
  
  List<Map<String,?>> conMovimientos(String cuenta);
  
  // Metodo para registrar deposito
  void registrarDeposito(String cuenta, double importe, String codEmp);
  
}
