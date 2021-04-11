package logiciel;

import base_de_donnees.Connexion_bdd;
import base_de_donnees.Requete_bdd;
import base_de_donnees.ResultSetTableModel;

import java.sql.ResultSet;
import javax.swing.JOptionPane;

//import par défaut de java swing
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Produit extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_prod;
	private JTextField txt_co;
	private JTextField txt_cl;
	private JTextField txt_ti;
	private JTextField txt_ra;
	private JTextField txt_re;
    
	// Déclaration des variables
	   ResultSet rs;
	   Requete_bdd db;
	   
		 public Produit() 
		 {
			 db = new Requete_bdd(new Connexion_bdd().HOST_DB, new Connexion_bdd().USERNAME_DB, new Connexion_bdd().PASSWORD_DB,
	                 new Connexion_bdd().IPHOST, new Connexion_bdd().PORT);
			 initComponents();
	         table();
		 }
		 
		 public void table() 
	     {
	         String t[] = {"id","code_produit","client","titre","rangement"};
	         rs = db.querySelect(t, "produit");
	         table_prod.setModel(new ResultSetTableModel(rs));
	     }
	   
	   
  	  public void actualiser()
  	   {
  	        txt_co.setText("");
  	        txt_cl.setText("");
  	        txt_ti.setText("");
  	        txt_ra.setText("");      
  	   }

	
	 @SuppressWarnings("serial")
	private void initComponents()
	 {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Produits en Stock");
		lblNewLabel.setBounds(349, 34, 264, 27);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(81, 94, 777, 155);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				 txt_co.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 1)));
			     txt_cl.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 2)));
			     txt_ti.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 3)));
			     txt_ra.setText(String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 4)));
			}
		});
		
		table_prod = new JTable();
		table_prod.setBorder(null);
		table_prod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table_prod.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"id", "code_produit", "client", "titre", "rangement"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_prod.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table_prod);
		
		JButton btnSupprimer = new JButton("supprimer");
		btnSupprimer.setBounds(340, 293, 134, 36);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String id = String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 0));
// if (JOptionPane.showConfirmDialog(this, "Confirmer la suppression ?","Action irréversible !", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
                if (JOptionPane.showConfirmDialog(null, this, "Confirmer la suppression ?", DISPOSE_ON_CLOSE) == JOptionPane.OK_OPTION)		        
		        {
		            db.queryDelete("produit", "id=" + id);
		        }
		        else
		        {
		            return;
		        }
		        table();
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnActualliser = new JButton("actualiser");
		btnActualliser.setBounds(484, 293, 134, 36);
		btnActualliser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				actualiser(); 
				table(); 
			}
		});
		btnActualliser.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnModifier_1 = new JButton("modifier");
		btnModifier_1.setBounds(195, 293, 134, 36);
		btnModifier_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if ( txt_co.getText().equals("") || txt_cl.getText().equals("") || txt_ti.getText().equals("")
		                || txt_ra.getText().equals("") )
			    {
		            JOptionPane.showMessageDialog( null, this, "Veuillez renseigner tous les champs", DISPOSE_ON_CLOSE);
			    }
				else 
				{
			         String[] colon = {"code_produit","client","titre","rangement"};
			         String[] inf = { txt_co.getText(), txt_cl.getText(), txt_ti.getText(),txt_ra.getText() };
			         String id = String.valueOf(table_prod.getValueAt(table_prod.getSelectedRow(), 0));
			         System.out.println(db.queryUpdate("produit", colon, inf, "id='" + id + "'"));
			         table();
			         actualiser();
			    }
			}
		});
		btnModifier_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnAjouter = new JButton("ajouter");
		btnAjouter.setBounds(51, 293, 134, 36);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			    if (txt_co.getText().equals("") || txt_cl.getText().equals("") || txt_ti.getText().equals("")
		                || txt_ra.getText().equals("") )
			    {
		            JOptionPane.showMessageDialog( null, this, "Veuillez renseigner tous les champs", 8);
		        }
			    else 
			    {
		            String[] colon = { "code_produit","client","titre","rangement" };
		            String[] inf = { txt_co.getText(), txt_cl.getText(), txt_ti.getText(),txt_ra.getText() };
		            System.out.println(db.queryInsert("produit", colon, inf));
		            table();
		            actualiser();
		        }
			}
		});
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel_1 = new JLabel("code produit :");
		lblNewLabel_1.setBounds(32, 362, 96, 27);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblClient = new JLabel("client :");
		lblClient.setBounds(32, 424, 84, 27);
		lblClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTitre = new JLabel("titre :");
		lblTitre.setBounds(32, 488, 77, 27);
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblRangement = new JLabel("rangement :");
		lblRangement.setBounds(32, 550, 84, 27);
		lblRangement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txt_co = new JTextField();
		txt_co.setBounds(138, 362, 184, 27);
		txt_co.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_co.setColumns(10);
		
		txt_cl = new JTextField();
		txt_cl.setBounds(138, 424, 184, 27);
		txt_cl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_cl.setColumns(10);
		
		txt_ti = new JTextField();
		txt_ti.setBounds(138, 488, 184, 27);
		txt_ti.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_ti.setColumns(10);
		
		txt_ra = new JTextField();
		txt_ra.setBounds(138, 550, 184, 27);
		txt_ra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_ra.setColumns(10);
		
		txt_re = new JTextField();
		txt_re.setBounds(566, 450, 184, 36);
		txt_re.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_re.setColumns(10);
		
		JComboBox com_rech = new JComboBox();
		com_rech.setBounds(520, 387, 160, 27);
		contentPane.add(com_rech);
		com_rech.setFont(new Font("Tahoma", Font.PLAIN, 16));
		com_rech.setModel(new DefaultComboBoxModel(new String[] {"id", "code_produit", "client", "titre", "rangement"}));
		
		JButton btnRecherche = new JButton("recherche");
		btnRecherche.setBounds(422, 450, 134, 36);
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (txt_re.getText().equals("")) 
				{
				            JOptionPane.showMessageDialog(null, this, "Veuillez saisir quelque chose !",DISPOSE_ON_CLOSE );
				} 
			    else
				{
			    	
					if (com_rech.getSelectedItem().equals("code_produit")) 
				    {
				       rs = db.querySelectAll("produit", "code_produit LIKE '%" + txt_re.getText() + "%' ");
				       table_prod.setModel(new ResultSetTableModel(rs));
				    } 
				    else if (com_rech.getSelectedItem().equals("client"))
				    {
				       rs = db.querySelectAll("produit", "client LIKE '%" + txt_re.getText() + "%' ");
				       table_prod.setModel(new ResultSetTableModel(rs));
				    } 
				    else if (com_rech.getSelectedItem().equals("titre"))
				    {
				       rs = db.querySelectAll("produit", "titre LIKE '%" + txt_re.getText() + "%' ");
				       table_prod.setModel(new ResultSetTableModel(rs));
				    } 
				    else if (com_rech.getSelectedItem().equals("rangement")) 
				    {
				       rs = db.querySelectAll("produit", "rangement LIKE '%" + txt_re.getText() + "%' ");
				       table_prod.setModel(new ResultSetTableModel(rs));
				    }
				    else if (com_rech.getSelectedItem().equals("id"))
				    {
				       rs = db.querySelectAll("produit", "id LIKE '%" + txt_re.getText() + "%' ");
				       table_prod.setModel(new ResultSetTableModel(rs));
				    }
				         
			    }
			}
		});
			
		btnRecherche.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblRechercheParC = new JLabel("recherche par cat\u00E9gorie :");
		lblRechercheParC.setBounds(510, 350, 193, 27);
		lblRechercheParC.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(scrollPane);
		contentPane.add(btnAjouter);
		contentPane.add(btnModifier_1);
		contentPane.add(btnSupprimer);
		contentPane.add(btnActualliser);
		contentPane.add(lblNewLabel_1);
		contentPane.add(txt_co);
		contentPane.add(lblRechercheParC);
		contentPane.add(lblClient);
		contentPane.add(txt_cl);
		contentPane.add(btnRecherche);
		contentPane.add(txt_re);
		contentPane.add(lblTitre);
		contentPane.add(txt_ti);
		contentPane.add(lblRangement);
		contentPane.add(txt_ra);
		
		
	} //Fin du constructeur "initComponents"
	 
	 
	
	public static void main(String[] args) //Class principale
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Produit frame = new Produit();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	} //Fin de la Class principale
	
	
} //Fin de la Class Produit
