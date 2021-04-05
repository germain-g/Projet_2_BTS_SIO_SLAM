package logiciel;

import base_de_donnees.Connexion_bdd;
import base_de_donnees.Requete_bdd;
import base_de_donnees.ResultSetTableModel;

import java.sql.ResultSet;
import javax.swing.JOptionPane;

//****
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Produit extends JFrame {

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
	  
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produit frame = new Produit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void table() 
	{
        String a[] = {"id","code_produit","client","titre","rangement"};
        rs = db.querySelect(a, "produit");
        table_prod.setModel(new ResultSetTableModel(rs));
    }
	
	 void actualiser()
	 {
	        txt_co.setText("");
	        txt_cl.setText("");
	        txt_ti.setText("");
	        txt_ra.setText("");
	        txt_re.setText("");
	 }

	
	public Produit() 
	{
	  db = new Requete_bdd(new Connexion_bdd().HOST_DB, new Connexion_bdd().USERNAME_DB, new Connexion_bdd().PASSWORD_DB,
			                   new Connexion_bdd().IPHOST, new Connexion_bdd().PORT);
	        initComponents();
	        table();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Produits en Stock");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		JScrollPane scrollPane = new JScrollPane();
		
		table_prod = new JTable();
		table_prod.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table_prod.setModel(new DefaultTableModel(
			new Object[][]
					{
			        },
			new String[] 
					{
				      "id", "code_produit", "client", "titre", "rangement"
			        }
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table_prod);
		
		JButton btnSupprimer = new JButton("supprimer");
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnActualliser = new JButton("actualiser");
		btnActualliser.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnModifier_1 = new JButton("modifier");
		btnModifier_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnAjouter = new JButton("ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			    if (txt_co.getText().equals("") || txt_cl.getText().equals("") || txt_ti.getText().equals("")
		                || txt_ra.getText().equals("") || txt_re.getText().equals("") 
		           )
			    {
		            JOptionPane.showMessageDialog( this, "Veuillez entrer les informations complete");
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
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblClient = new JLabel("client :");
		lblClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTitre = new JLabel("titre :");
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblRangement = new JLabel("rangement :");
		lblRangement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txt_co = new JTextField();
		txt_co.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_co.setColumns(10);
		
		txt_cl = new JTextField();
		txt_cl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_cl.setColumns(10);
		
		txt_ti = new JTextField();
		txt_ti.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_ti.setColumns(10);
		
		txt_ra = new JTextField();
		txt_ra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_ra.setColumns(10);
		
		txt_re = new JTextField();
		txt_re.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_re.setColumns(10);
		
		JButton btnRecherche = new JButton("recherche");
		btnRecherche.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblRechercheParC = new JLabel("recherche par cat\u00E9gorie :");
		lblRechercheParC.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"id", "code_produit", "client", "titre", "rangement"}));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(344)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(87)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 774, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addComponent(btnAjouter, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnModifier_1, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnSupprimer, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnActualliser, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txt_co, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addGap(188)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRechercheParC, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(lblClient, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(txt_cl, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addGap(100)
					.addComponent(btnRecherche, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txt_re, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(lblTitre, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(txt_ti, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(lblRangement, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(txt_ra, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(25)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAjouter, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnModifier_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSupprimer, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnActualliser, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(txt_co, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblRechercheParC, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClient, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_cl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(btnRecherche, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(txt_re, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitre, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_ti, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRangement, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_ra, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
