/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesisweb.controller.experimento;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Inject;
import tesisweb.controller.frontend.LoginManager;
import tesisweb.ejb.tienda.entity.CuestionarioFamiliaridad;
import tesisweb.ejb.tienda.entity.Usuario;
import tesisweb.ejb.tienda.facade.CuestionarioFamiliaridadDAO;
import tesisweb.ejb.tienda.facade.PreferenceDAO;
import tesisweb.ejb.tienda.facade.RolDAO;
import tesisweb.ejb.tienda.facade.UsuarioDAO;
import tesisweb.util.JSFutil;
import tesisweb.util.JSFutil.PersistAction;

/**
 *
 * @author root
 */
@Named(value = "CuestionarioFamiliaridadController")
@SessionScoped
public class CuestionarioFamiliaridadController implements Serializable {

    /**
     * Configuraciones varias para Log y Bundle*
     */
    private static final Logger LOG = Logger.getLogger(CuestionarioFamiliaridadController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("tesisweb.properties.bundle", JSFutil.getmyLocale());
    private final String USER_SESSION_KEY = "user";
    private final String USER_SESSION_LANGUAGE = "language";

    @Inject
    CuestionarioFamiliaridadDAO cuestionarioFamiliaridadDAO;
    @Inject
    UsuarioDAO usuarioDAO;
    @Inject
    PreferenceDAO preferenceDAO;
    @Inject
    RolDAO rolDAO;
    @Inject
    LoginManager loginManager;
    private CuestionarioFamiliaridad cuestionarioFamiliaridad;
    private List<CuestionarioFamiliaridad> listaCuestionarioFamiliaridad;

    /**
     * Creates a new instance of CuestionarioFamiliaridadController
     */
    public CuestionarioFamiliaridadController() {
        this.cuestionarioFamiliaridad = new CuestionarioFamiliaridad();
    }

    public CuestionarioFamiliaridad getCuestionarioFamiliaridad() {
        return cuestionarioFamiliaridad;
    }

    public void setCuestionarioFamiliaridad(CuestionarioFamiliaridad cuestionarioFamiliaridad) {
        this.cuestionarioFamiliaridad = cuestionarioFamiliaridad;
    }

    public List<CuestionarioFamiliaridad> getListaCuestionarioFamiliaridad() {
        return listaCuestionarioFamiliaridad;
    }

    public void setListaCuestionarioFamiliaridad(List<CuestionarioFamiliaridad> listaCuestionarioFamiliaridad) {
        this.listaCuestionarioFamiliaridad = listaCuestionarioFamiliaridad;
    }

    /**
     * Guardar un registro
     *
     * @return
     */
    public String doGuardar() {
        try {
            Usuario u = new Usuario();
            u.setCuenta(this.cuestionarioFamiliaridad.getAlias());
            u.setEsActivo(Boolean.TRUE);
            u.setIdPreference(preferenceDAO.find(0)); //PREFERENCIA SUCIA
            u.setIdRol(rolDAO.find(5)); //SUJETO EXPERIMENTAL
            u.setNombres(this.cuestionarioFamiliaridad.getAlias());
            u.setApellidos("SUJETO EXPERIMENTAL");
            //Crear el usuario autologeable
            usuarioDAO.create(u);
            this.cuestionarioFamiliaridad.setIdUsuario(u);
            //Crear el cuestionario asociado a este usuario
            cuestionarioFamiliaridadDAO.create(cuestionarioFamiliaridad);

            JSFutil.addSuccessMessage("Acceso concedido");
            JSFutil.putSessionVariable(USER_SESSION_KEY, u);
            JSFutil.putSessionVariable(USER_SESSION_LANGUAGE, u.getIdPreference().getIdioma());
            return "/experimento/tareaPR";
        } catch (EJBException ex) {
            String msg = "";
            Throwable cause = ex.getCause();
            if (cause != null) {
                msg = cause.getLocalizedMessage();
            }
            JSFutil.addErrorMessage("Se ha producido un error durante la inicialización del experimento. Favor póngase en contacto con el administrador al pie de la página");
            if (msg.length() > 0) {
                JSFutil.addErrorMessage(msg);
            } else {
                JSFutil.addErrorMessage(ex, JSFutil.getMyBundle().getString("UpdateError"));
            }
            return "";
        }
    }
}