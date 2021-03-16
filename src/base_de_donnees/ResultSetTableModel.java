
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
    
    // Nombres de rangé //*****A enlever !!!
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        if (rowIndex < 0 || rowIndex > getRowCount() || columnIndex < 0 || columnIndex > getColumnCount())
        {
            return null;
        }
        try 
        {
            if (rs == null)
            {
                return null;
            } 
            else 
            {
                rs.absolute(rowIndex + 1);
                return rs.getObject(columnIndex + 1);
            }
        } 
        catch (SQLException e)
        {
            System.out.println("Erreur de rangé");
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    // Nom de colonne //*****A enlever !!!
    @Override
    public String getColumnName(int columnIndex) 
    {
        try 
        {
            return rs.getMetaData().getColumnName(columnIndex + 1);
        } 
        catch (SQLException e)
        {
            System.out.println("Erreur de nom de colonne");
            System.out.println(e.getMessage());
        }
        return super.getColumnName(columnIndex);
    }
	
    
}
