import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import DAO.Connexion;
import DAO.CreneauDAO;
import DAO.IntClientDao;
import DAO.IntCreneauDao;
import Models.ClientModel;
import Models.CreneauModel;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class Creneaux extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JLabel lblNewLabel;
	private JLabel lblTelephone;
	private JLabel lblAdresse;
	private JTextField textID;
	private JTextField textVersion;
	private JTextField textHDebut;
	IntCreneauDao CDAO ;
	Connection cnx = null;
	//Statement stmt=null;
	PreparedStatement prepared = null;
	ResultSet rs = null;
	private JTextField textFieldSearch;
	private JLabel lblRechercher;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JTextField textMDebut;
	private JTextField textHfin;
	private JLabel lblMfin;
	private JLabel lblMedecin;
	private JTextField textMfin;
	private JComboBox comboBox;
	private JLabel lblGestionCreneaux;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Creneaux frame = new Creneaux();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public void ComboMed() {
		String sql="select * from medecins";
		try {
			prepared=cnx.prepareStatement(sql);
			rs=prepared.executeQuery();
			while(rs.next()) {
				String nom=rs.getString("NOM").toString();
				String id=rs.getString("ID").toString();
				comboBox.addItem(nom+"-"+id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void updateTable() {
		
			table.setModel(DbUtils.resultSetToTableModel(CDAO.getCreneau()));
			
		
	}
	public void Clear() {
		textID.setText("");
		textVersion.setText("");
		textHDebut.setText("");
		textMDebut.setText("");
		textHfin.setText("");
		textMfin.setText("");
	}
	public Creneaux() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 747, 519);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = Connexion.getInstance().ConnexionDb();
		CDAO=new CreneauDAO();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 402, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int ligne = table.getSelectedRow();
				String id =table.getModel().getValueAt(ligne, 0).toString();
				String sql="select * from creneaux where ID='" +id+"'";
				
				try {
					prepared = cnx.prepareStatement(sql);
					rs= prepared.executeQuery();
					if(rs.next()) {
						textID.setText(rs.getString("ID"));
						textVersion.setText(rs.getString("VERSION"));
						textHDebut.setText(rs.getString("HDEBUT"));
						textMDebut.setText(rs.getString("MDEBUT"));
						textHfin.setText(rs.getString("HFIN"));
						textMfin.setText(rs.getString("MFIN"));
						comboBox.setSelectedItem(rs.getString("ID_MEDECIN"));
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("ID :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(459, 71, 84, 14);
		contentPane.add(lblNewLabel);
		
		lblTelephone = new JLabel("VERSION :");
		lblTelephone.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelephone.setBounds(459, 122, 96, 14);
		contentPane.add(lblTelephone);
		
		lblAdresse = new JLabel("HDEBUT :");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdresse.setBounds(459, 177, 84, 14);
		contentPane.add(lblAdresse);
		
		textID = new JTextField();
		textID.setBounds(565, 68, 111, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		textVersion = new JTextField();
		textVersion.setColumns(10);
		textVersion.setBounds(565, 119, 111, 20);
		contentPane.add(textVersion);
		
		textHDebut = new JTextField();
		textHDebut.setColumns(10);
		textHDebut.setBounds(565, 174, 111, 20);
		contentPane.add(textHDebut);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				String id =table.getModel().getValueAt(ligne, 0).toString();
				CDAO.DeleteCreneau(Integer.parseInt(id));
				updateTable();
				Clear();
			}
		});
		btnSupprimer.setBounds(98, 319, 97, 35);
		contentPane.add(btnSupprimer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				String id =table.getModel().getValueAt(ligne, 0).toString();
				CreneauModel c = new CreneauModel(Integer.parseInt(textID.getText()), Integer.parseInt(textVersion.getText()), Integer.parseInt(textHDebut.getText()), Integer.parseInt(textMDebut.getText()), Integer.parseInt(textHfin.getText()),Integer.parseInt(textMfin.getText()),Integer.parseInt(((String) comboBox.getSelectedItem()).split("-")[1]));
				CDAO.UpdateCreneau(c);
				updateTable();
				Clear();
				
			}
		});
		btnModifier.setBounds(217, 319, 105, 35);
		contentPane.add(btnModifier);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreneauModel c = new CreneauModel(Integer.parseInt(textID.getText()), Integer.parseInt(textVersion.getText()), Integer.parseInt(textHDebut.getText()), Integer.parseInt(textMDebut.getText()), Integer.parseInt(textHfin.getText()),Integer.parseInt(textMfin.getText()),Integer.parseInt(((String) comboBox.getSelectedItem()).split("-")[1]));
				CDAO.AddCreneau(c);
				Clear();
				updateTable();
			
			}
		});
		btnAjouter.setBounds(473, 436, 89, 34);
		contentPane.add(btnAjouter);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clear();
			}
		});
		btnClear.setBounds(579, 436, 89, 34);
		contentPane.add(btnClear);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(textFieldSearch.getText().trim()));
			}
		});
		textFieldSearch.setBounds(313, 22, 86, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		lblRechercher = new JLabel("Rechercher :");
		lblRechercher.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRechercher.setBounds(192, 25, 111, 14);
		contentPane.add(lblRechercher);
		
		lblNom = new JLabel("MDEBUT :");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNom.setBounds(459, 226, 96, 14);
		contentPane.add(lblNom);
		
		lblPrenom = new JLabel("HFIN :");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrenom.setBounds(459, 281, 84, 14);
		contentPane.add(lblPrenom);
		
		textMDebut = new JTextField();
		textMDebut.setColumns(10);
		textMDebut.setBounds(565, 223, 111, 20);
		contentPane.add(textMDebut);
		
		textHfin = new JTextField();
		textHfin.setColumns(10);
		textHfin.setBounds(565, 278, 111, 20);
		contentPane.add(textHfin);
		
		lblMfin = new JLabel("MFIN :");
		lblMfin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMfin.setBounds(459, 325, 96, 14);
		contentPane.add(lblMfin);
		
		lblMedecin = new JLabel("MEDECIN :");
		lblMedecin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMedecin.setBounds(459, 380, 84, 14);
		contentPane.add(lblMedecin);
		
		textMfin = new JTextField();
		textMfin.setColumns(10);
		textMfin.setBounds(565, 322, 111, 20);
		contentPane.add(textMfin);
		
		comboBox = new JComboBox();
		comboBox.setBounds(565, 379, 111, 20);
		contentPane.add(comboBox);
		
		lblGestionCreneaux = new JLabel("Gestion Creneaux:");
		lblGestionCreneaux.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblGestionCreneaux.setBounds(420, 22, 290, 35);
		contentPane.add(lblGestionCreneaux);
		ComboMed();
		updateTable();
		
		
	}
}
