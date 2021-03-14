
package base_de_donnees;

import java.net.Socket;
import java.sql.*;

public class Requete_bdd 
{
	// Déclaration des varibles
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
    
    // Pour se connecter à la BDD
    public Connection connexionDatabase()
    {
    	try
    	{
    	   Class.forName("com.mysql.jdbc.Driver");          
    	   connection = DriverManager.getConnection(url, username, password);
    	} 
    	catch (Exception e) 
    	{
    	   System.err.println(e.getMessage()); // Pour afficher l'érreur (err) et afficher son emplacement (e.getMessage)
    	}
      return connection;
    }
    
    // Pour arrêter la connexion  à la BDD
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
  
    // Pour l'exécution des requêtes SQL
    public ResultSet exécutionQuery(String sql)
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
    
    // Pour l'exécution des requêtes SQL "update"
    public String exécutionUpdate(String sql) 
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
        return this.exécutionQuery(SQL);
    }
    
    // Pour Afficher toutes les tables avec le paramètre "état" choisi //*****A enlever !!!
    public ResultSet querySelectAll(String nomTable, String état) 
    {
        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + état;
        return this.exécutionQuery(SQL);
    }
    
    // Pour Sélectionner une colonne spécifique //*****A enlever !!!
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
      return this.exécutionQuery(SQL);
    }
    
    // Pour Ajouter des données dans la BDD
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
      return this.exécutionUpdate(SQL);
    }
    
    // Pour Ajouter des données
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
      return this.exécutionUpdate(SQL);
    }
    
    // Pour Mettre à jour la méthode d'éxecution d'une requête
    public String queryUpdate(String nomTable, String[] nomColonne, String[] contenuTableau, String état) 
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
      SQL += " WHERE " + état;
      return this.exécutionUpdate(SQL);
    }
    
    // Pour Supprimer une donnée
    public String queryDelete(String nomtable)
    {
        connexionDatabase();
        SQL = "DELETE FROM " + nomtable;
        return this.exécutionUpdate(SQL);
    }
    
    // Pour Supprimer une donnée avec paramètre //*****A enlever !!!
    public String queryDelete(String nomTable, String état)
    {
        connexionDatabase();
        SQL = "DELETE FROM " + nomTable + " WHERE " + état;
        return this.exécutionUpdate(SQL);
    }
    
    
}
