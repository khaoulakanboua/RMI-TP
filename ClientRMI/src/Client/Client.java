/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Config;

/**
 *
 * @author Lachgar
 */
public class Client {

    public static void main(String[] args) {
        try {
            IDao<Machine> ms = (IDao<Machine>) Naming.lookup("rmi://" + Config.ip + ":" + Config.port + "/" + "dao");
            IDao<Salle> salle = (IDao<Salle>) Naming.lookup("rmi://" + Config.ip + ":" + Config.port + "/" + "dao1");

            /*ms.create(new Machine("RE44", "HP", 2000));
            ms.create(new Machine("RE54", "DELL", 5000));
            ms.create(new Machine("RE74", "LENOV", 6000));*/
            
            salle.create(new Salle("TP1"));
            salle.create(new Salle("TP2"));
            salle.create(new Salle("LR"));

            for (Machine m : ms.findAll()) {
                System.out.println(m);
            }

            for (Salle s : salle.findAll()) {
                System.out.println(s);
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
