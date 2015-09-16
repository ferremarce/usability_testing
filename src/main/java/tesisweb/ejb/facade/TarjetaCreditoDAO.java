/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesisweb.ejb.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tesisweb.ejb.entity.TarjetaCredito;

/**
 *
 * @author root
 */
@Stateless
public class TarjetaCreditoDAO extends AbstractFacade<TarjetaCredito> {
    @PersistenceContext(unitName = "tesisweb_tesisweb_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetaCreditoDAO() {
        super(TarjetaCredito.class);
    }
    
}
