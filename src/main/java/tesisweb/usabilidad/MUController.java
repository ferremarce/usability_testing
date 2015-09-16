/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesisweb.usabilidad;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author root
 */
@Named(value = "MUController")
@SessionScoped
public class MUController implements Serializable {

    private Boolean muPreference;
    private Boolean muAbortOperation;
    private Boolean muProgressFeedBack;

    /**
     * Creates a new instance of MUController
     */
    public MUController() {
    }

    public Boolean getMuPreference() {
        return muPreference;
    }

    public void setMuPreference(Boolean muPreference) {
        this.muPreference = muPreference;
    }

    public Boolean getMuAbortOperation() {
        return muAbortOperation;
    }

    public void setMuAbortOperation(Boolean muAbortOperation) {
        this.muAbortOperation = muAbortOperation;
    }

    public Boolean getMuProgressFeedBack() {
        return muProgressFeedBack;
    }

    public void setMuProgressFeedBack(Boolean muProgressFeedBack) {
        this.muProgressFeedBack = muProgressFeedBack;
    }

}