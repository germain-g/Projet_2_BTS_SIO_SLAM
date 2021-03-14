
package base_de_donnees;

import java.net.Socket;
import java.sql.*;

public class Requete_bdd 
{
	// D�claration des varibles
    Connection connection;
    Statement statement;
    String SQL;
   
    String url;
    String username;
    String password;
    Socket client;
    int Port;
    String Host;

    public Requete_bdd(String url, String username, String password, String Host, int Port) 
    {    
       this.url = url;
       this.username = username;
       this.password = password;
       this.Host = Host;  
       this.Port = Port;
    }
    
    // Pour se connecter � la BDD
    public Connection connexionDatabase()
    {
    	try
    	{
    	   Class.forName("com.mysql.jdbc.Driver");          
    	   connection = DriverManager.getConnection(url, username, password);
    	} 
    	catch (Exception e) 
    	{
    	   System.err.println(e.getMessage()); // Pour afficher l'�rreur (err) et afficher son emplacement (e.getMessage)
    	}
      return connection;
    }
    
    // Pour arr�ter la connexion  � la BDD
    public Connection closeconnexion() 
    {
        try 
        {
           connection.close();
        }
        catch (Exception e) 
        {
           System.err.println(e);
        }
      return connection;
    }
  
    // Pour l'ex�cution des requ�tes SQL
    public ResultSet ex�cutionQuery(String sql)
    {
        connexionDatabase();
        ResultSet resultSet = null;
        try 
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println(sql);
        } 
        catch (SQLException ex)
        {
        	System.err.println(ex);
        }
      return resultSet;
    }
    
    // Pour l'ex�cution des requ�tes SQL "update"
    public String ex�cutionUpdate(String sql) 
    {
        connexionDatabase();
        String result = "";
        try 
        {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            result = sql;
        } 
        catch (SQLException ex) 
        {
            result = ex.toString();
        }
      return result;
    }
    
    // Pour Afficher toutes les tables de la BDD
    public ResultSet querySelectAll(String nomTable) 
    {
        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable;
        System.out.println(SQL);
        return this.ex�cutionQuery(SQL);
    }
    
    // Pour Afficher toutes les tables avec le param�tre "�tat" choisi //*****A enlever !!!
    public ResultSet querySelectAll(String nomTable, String �tat) 
    {
        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + �tat;
        return this.ex�cutionQuery(SQL);
    }
    
    // Pour S�lectionner une colonne sp�cifique //*****A enlever !!!
    public ResultSet querySelect(String[] nomColonne, String nomTable) 
    {
        connexionDatabase();
        int i;
        SQL = "SELECT ";

        for (i = 0; i <= nomColonne.length - 1; i++) 
        {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1)
            {
                SQL += ",";
            }
        }
      SQL += " FROM " + nomTable;
      return this.ex�cutionQuery(SQL);
    }
    
    // Pour Ajouter des donn�es dans la BDD
    public String queryInsert(String nomTable, String[] contenuTableau)
    {
        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable + " VALUES(";

        for (i = 0; i <= contenuTableau.length - 1; i++) 
        {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1)
            {
                SQL += ",";
            }
        }
      SQL += ")";
      return this.ex�cutionUpdate(SQL);
    }
    
    // Pour Ajouter des donn�es
    public String queryInsert(String nomTable, String[] nomColonne, String[] contenuTableau)
    {
        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable + "(";
        for (i = 0; i <= nomColonne.length - 1; i++) 
        {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) 
            {
                SQL += ",";
            }
        }
        SQL += ") VALUES(";
        for (i = 0; i <= contenuTableau.length - 1; i++) 
        {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1)
            {
                SQL += ",";
            }
        }
      SQL += ")";
      return this.ex�cutionUpdate(SQL);
    }
    
    // Pour Mettre � jour la m�thode d'�xecution d'une requ�te
    public String queryUpdate(String nomTable, String[] nomColonne, String[] contenuTableau, String �tat) 
    {
        connexionDatabase();
        int i;
        SQL = "UPDATE " + nomTable + " SET ";

        for (i = 0; i <= nomColonne.length - 1; i++) 
        {
            SQL += nomColonne[i] + "='" + contenuTableau[i] + "'";
            if (i < nomColonne.length - 1) 
            {
                SQL += ",";
            }
        }
      SQL += " WHERE " + �tat;
      return this.ex�cutionUpdate(SQL);
    }
    
    // Pour Supprimer une donn�e
    public String queryDelete(String nomtable)
    {
        connexionDatabase();
        SQL = "DELETE FROM " + nomtable;
        return this.ex�cutionUpdate(SQL);
    }
    
    // Pour Supprimer une donn�e avec param�tre //*****A enlever !!!
    public String queryDelete(String nomTable, String �tat)
    {
        connexionDatabase();
        SQL = "DELETE FROM " + nomTable + " WHERE " + �tat;
        return this.ex�cutionUpdate(SQL);
    }
    
    
}
