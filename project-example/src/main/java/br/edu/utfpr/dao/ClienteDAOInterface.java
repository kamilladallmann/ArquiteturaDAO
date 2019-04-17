package br.edu.utfpr.dao;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.negocio.ClienteNegocio;

import java.util.List;

public interface ClienteDAOInterface {

    public boolean insereCliente(ClienteDTO cliente);

    public ClienteDTO buscaCliente(int id);

    public boolean updateCliente(ClienteDTO cliente);

    public boolean deletaCliente(ClienteDTO cliente);

    public List<ClienteDTO> listaTodos();



}
