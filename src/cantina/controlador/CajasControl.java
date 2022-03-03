/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.controlador;

import cantina.datos.CajasDAO;

/**
 *
 * @author Lucas
 */
public class CajasControl {
    public Boolean buscarCaja (Integer id){
        return new CajasDAO().buscarCaja(id);
    }
}
