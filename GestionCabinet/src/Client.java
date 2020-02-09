import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import DAO.ClientDAO;
import DAO.Connexion;
import DAO.IntClientDao;
import Models.ClientModel;
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

public class Client extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JLabel lblNewLabel;
	private JLabel lblTelephone;
	private JLabel lblAdresse;
	private JTextField textID;
	private JTextField textVersion;
	private JTextField textTitre;
	Connection cnx =  null;
	IntClientDao CDAO ;
	//Statement stmt=null;
	PreparedStatement prepared = null;
	ResultSet rs = null;
	private JTextField textFieldSearch;
	private JLabel lblRechercher;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JTextField textNom;
	private JTextField textPrenom;
	private JLabel lblGestionClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
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
	
	public void updateTable() {
		table.setModel(DbUtils.resultSetToTableModel(CDAO.getClients()));
	}
	
	public void Clear() {
		textID.setText("");
		textVersion.setText("");
		textTitre.setText("");
		textNom.setText("");
		textPrenom.setText("");
	}
	public Client() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 747, 448);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = Connexion.getInstance().ConnexionDb();
		CDAO = new ClientDAO();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 402, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int ligne = table.getSelectedRow();
				String id =table.getModel().getValueAt(ligne, 0).toString();
				String sql="select * from clients where ID='" +id+"'";
				
				try {
					prepared = cnx.prepareStatement(sql);
					rs= prepared.executeQuery();
					if(rs.next()) {
						textID.setText(rs.getString("ID"));
						textVersion.setText(rs.getString("VERSION"));
						textTitre.setText(rs.getString("TITRE"));
						textNom.setText(rs.getString("NOM"));
						textPrenom.setText(rs.getString("PRENOM"));
						
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
		lblNewLabel.setBounds(463, 96, 84, 14);
		contentPane.add(lblNewLabel);
		
		lblTelephone = new JLabel("VERSION :");
		lblTelephone.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelephone.setBounds(463, 147, 96, 14);
		contentPane.add(lblTelephone);
		
		lblAdresse = new JLabel("Titre :");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdresse.setBounds(463, 202, 84, 14);
		contentPane.add(lblAdresse);
		
		textID = new JTextField();
		textID.setBounds(569, 93, 111, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		textVersion = new JTextField();
		textVersion.setColumns(10);
		textVersion.setBounds(569, 144, 111, 20);
		contentPane.add(textVersion);
		
		textTitre = new JTextField();
		textTitre.setColumns(10);
		textTitre.setBounds(569, 199, 111, 20);
		contentPane.add(textTitre);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				String id =table.getModel().getValueAt(ligne, 0).toString();
				CDAO.DeleteClient(Integer.parseInt(id));
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
				ClientModel c = new ClientModel(Integer.parseInt(textID.getText()), Integer.parseInt(textVersion.getText()), textTitre.getText(), textNom.getText(), textPrenom.getText());
				CDAO.UpdateClient(c);
				updateTable();
				Clear();
				
			}
		});
		btnModifier.setBounds(217, 319, 105, 35);
		contentPane.add(btnModifier);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ClientModel c = new ClientModel(Integer.parseInt(textID.getText()), Integer.parseInt(textVersion.getText()), textTitre.getText(), textNom.getText(), textPrenom.getText());
				CDAO.AddClient(c);
				Clear();
				updateTable();
				
			}
		});
		btnAjouter.setBounds(491, 348, 89, 34);
		contentPane.add(btnAjouter);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clear();
			}
		});
		btnClear.setBounds(590, 348, 89, 34);
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
		
		lblNom = new JLabel("NOM :");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNom.setBounds(463, 251, 96, 14);
		contentPane.add(lblNom);
		
		lblPrenom = new JLabel("PRENOM :");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrenom.setBounds(463, 306, 84, 14);
		contentPane.add(lblPrenom);
		
		textNom = new JTextField();
		textNom.setColumns(10);
		textNom.setBounds(569, 248, 111, 20);
		contentPane.add(textNom);
		
		textPrenom = new JTextField();
		textPrenom.setColumns(10);
		textPrenom.setBounds(569, 303, 111, 20);
		contentPane.add(textPrenom);
		
		lblGestionClient = new JLabel("Gestion Client :");
		lblGestionClient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblGestionClient.setBounds(452, 22, 250, 35);
		contentPane.add(lblGestionClient);
		updateTable();
		
		
	}
}
