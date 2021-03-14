
package base_de_donnees;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel
{
	// Déclaration des varibles
	private ResultSet rs;
	
	public ResultSetTableModel(ResultSet rs) 
	{
	    this.rs = rs;
	    fireTableDataChanged();
	}
	
	// Compter le nombre de colonne
    public int getColumnCount() 
    {
        try 
        {
            if (rs == null)
            {
                return 0;
            } 
            else 
            {
                return  rs.getMetaData().getColumnCount();
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Erreur de comptage de colonne");
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    // Compter le nombre de ligne
    public int getRowCount() 
    {
        try
        {
            if (rs == null) 
            {
                return 0;
            } 
            else 
            {
                rs.last();
                return rs.getRow();
            }
        }
        catch (SQLException e)
        {
            System.out.println("Erreur de comptage de ligne");
            System.out.println(e.getMessage());
            return 0;
        }
    }

	
	
	
	
	
	
	
	
	
	
	
	

}
