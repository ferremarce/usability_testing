/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesisweb.controller.experimento;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;
import tesisweb.controller.frontend.LoginManager;
import tesisweb.controller.general.PreferenceController;
import tesisweb.ejb.experimento.entity.OrdenExposicionMuGrupo;
import tesisweb.util.JSFutil;

/**
 *
 * @author root
 */
@Named(value = "ExperimentoController")
@SessionScoped
public class ExperimentoController implements Serializable {

    private int indexFormActual;
@Inject
LoginManager loginManager;
    /**
     * Creates a new instance of ExperimentoController
     */
    public ExperimentoController() {
    }

    public String gotoFirstForm() {
        this.indexFormActual = -1;
        this.loginManager.setContadorPRhabilitado(Boolean.TRUE);
        return this.gotoNextForm();
    }

    public String gotoNextForm() {
        this.indexFormActual++;
        if (indexFormActual < 3) {
            OrdenExposicionMuGrupo orden = JSFutil.getUsuarioConectado().getIdGrupoExperimental().getOrdenExposicionMuGrupoList().get(indexFormActual);
            switch (orden.getIdMu().getIdMu()) {
                case 1: //PREFERENCE
                    return "/experimento/tareaPR?faces-redirect=true";
                case 2: //ABORT OPERATION
                    return "/experimento/tareaAB?faces-redirect=true";
                case 3: //PROGRESS FEEDBACK
                    return "/experimento/tareaFB?faces-redirect=true";
                default:
                    return "";
            }
        } else if (indexFormActual == 3) {
            return "/experimento/cuestionarioFinal?faces-redirect=true";
        } else {
            return "/experimento/agradecimiento";
        }
    }
}
