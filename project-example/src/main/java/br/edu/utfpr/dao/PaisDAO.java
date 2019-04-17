package br.edu.utfpr.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;
import lombok.extern.java.Log;

@Log
public class PaisDAO implements PaisDAOInterface{

    private Connection conn = null;
    private Statement query;
    private String sql;

    // Responsável por criar a tabela País no banco
    public PaisDAO() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela pais ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE pais (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_pais_pk PRIMARY KEY," +
						"nome varchar(255)," +
						"sigla varchar(3)," + 
						"codigoTelefone int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean inserePais(PaisDTO pais) {
        sql = "INSERT INTO pais VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, pais.getIdPais());
            query.setString(2, pais.getNome());
            query.setString(3, pais.getSigla());
            query.setInt(4, pais.getCodigoTelefone());

            query.execute();
            query.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PaisDTO buscaPais(int idPais) {

        PaisDTO pais = new PaisDTO();

        sql = "SELECT * FROM pais WHERE idPais = " + idPais;

        try{
            query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while(rs.next()){
                pais.setIdPais(rs.getInt("idPais"));
                pais.setNome(rs.getString("nome"));
                pais.setSigla(rs.getString("sigla"));
                pais.setCodigoTelefone(rs.getInt("codigoTelefone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pais;
    }

    @Override
    public boolean updatePais(PaisDTO pais) {
        sql = "UPDATE pais SET idPais = ?, nome = ?, sigla = ?, codigoTelefone = ? WHERE idPais = ?";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, pais.getIdPais());
            query.setString(2, pais.getNome());
            query.setString(3, pais.getSigla());
            query.setInt(4, pais.getCodigoTelefone());
            query.setInt(5, pais.getIdPais());

            query.execute();
            query.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletaPais(PaisDTO pais) {
        sql = "DELETE FROM pais WHERE idPais = ?";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setInt(1, pais.getIdPais());
            query.execute();
            query.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<PaisDTO> listaTodos() {
        PaisDTO pais = new PaisDTO();
        List<PaisDTO> listaPais = new ArrayList<PaisDTO>();

        sql = "SELECT * FROM pais";

        try{
            query = conn.createStatement();
            ResultSet rs = query.executeQuery(sql);

            while(rs.next()){
                pais.setIdPais(rs.getInt("idPais"));
                pais.setNome(rs.getString("nome"));
                pais.setSigla(rs.getString("sigla"));
                pais.setCodigoTelefone(rs.getInt("codigoTelefone"));

                listaPais.add(pais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPais;
    }
}