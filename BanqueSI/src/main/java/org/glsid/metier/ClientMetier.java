package org.glsid.metier;

import org.glsid.entities.Client;

import java.util.List;

public interface ClientMetier {
public Client saveClient(Client c);

public List<Client> listClient();

}