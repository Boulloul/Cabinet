import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import DAO.Connexion;
import DAO.IntRvDao;
import DAO.RvDAO;
import Models.RvModel;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class Rv extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JLabel lblNewLabel;
	private JLabel lblTelephone;
	private JTextField textID;
	private JTextField textJour;
	IntRvDao CDAO;
	Connection cnx = null;
	//Statement stmt=null;
	PreparedStatement prepared = null;
	ResultSet rs = null;
	private JTextField textFieldSearch;
	private JLabel lblRechercher;
	private JLabel lblMedecin;
	private JComboBox comboCli;
	private JLabel lblCreneau;
	private JComboBox comboCre;
	private JLabel lblGestionRendezvous;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rv frame = new Rv();
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
	
	
	public void ComboCli() {
		String sql="select * from clients";
		try {
			prepared=cnx.prepareStatement(sql);
			rs=prepared.executeQuery();
			while(rs.next()) {
				String nom=rs.getString("NOM").toString();
				String id=rs.getString("ID").toString();
				comboCli.addItem(nom+"-"+id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void ComboCre() {
		String sql="select * from creneaux";
		try {
			prepared=cnx.prepareStatement(sql);
			rs=prepared.executeQuery();
			while(rs.next()) {
				
				String id=rs.getString("ID").toString();
				comboCre.addItem(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void updateTable() {
		
			table.setModel(DbUtils.resultSetToTableModel(CDAO.getRv()));
		
		
	}
	public void Clear() {
		textID.setText("");
		textJour.setText("");
		
	}
	public Rv() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 747, 475);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = Connexion.getInstance().ConnexionDb();
		CDAO=new RvDAO();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 402, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int ligne = table.getSelectedRow();
				String id =table.getModel().getValueAt(ligne, 0).toString();
				String sql="select * from rv where ID='" +id+"'";
				
				try {
					prepared = cnx.prepareStatement(sql);
					rs= prepared.executeQuery();
					if(rs.next()) {
						textID.setText(rs.getString("ID"));
						textJour.setText(rs.getString("JOUR"));
						comboCli.setSelectedItem(rs.getString("ID_CLIENT"));
						comboCre.setSelectedItem(rs.getString("ID_CRENEAU"));
						
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
		
		lblTelephone = new JLabel("JOUR :");
		lblTelephone.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelephone.setBounds(459, 122, 96, 14);
		contentPane.add(lblTelephone);
		
		textID = new JTextField();
		textID.setBounds(565, 68, 111, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		textJour = new JTextField();
		textJour.setColumns(10);
		textJour.setBounds(565, 119, 111, 20);
		contentPane.add(textJour);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				String id =table.getModel().getValueAt(ligne, 0).toString();
				CDAO.DeleteRv(Integer.parseInt(id));
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
				String JourT=textJour.getText();  
			    Date Jour = null;
				try {
					Jour = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(JourT);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				RvModel c=new RvModel(Integer.parseInt(textID.getText()),Jour , Integer.parseInt(((String) comboCli.getSelectedItem()).split("-")[1]), Integer.parseInt(((String) comboCre.getSelectedItem()).split("-")[1]));
				CDAO.UpdateRv(c);
				updateTable();
				Clear();
				
			}
		});
		btnModifier.setBounds(217, 319, 105, 35);
		contentPane.add(btnModifier);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				String JourT=textJour.getText();  
			    Date Jour = null;
				try {
					Jour = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(JourT);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				RvModel c=new RvModel(Integer.parseInt(textID.getText()),Jour , Integer.parseInt(((String) comboCli.getSelectedItem()).split("-")[1]), Integer.parseInt(((String) comboCre.getSelectedItem()).split("-")[1]));
				CDAO.AddRv(c);
				Clear();
				updateTable();
				
			}
		});
		btnAjouter.setBounds(470, 319, 89, 34);
		contentPane.add(btnAjouter);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clear();
			}
		});
		btnClear.setBounds(576, 319, 89, 34);
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
		textFieldSearch.setBounds(199, 23, 86, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		lblRechercher = new JLabel("Rechercher :");
		lblRechercher.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRechercher.setBounds(78, 26, 111, 14);
		contentPane.add(lblRechercher);
		
		lblMedecin = new JLabel("CLIENT :");
		lblMedecin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMedecin.setBounds(459, 215, 84, 14);
		contentPane.add(lblMedecin);
		
		comboCli = new JComboBox();
		comboCli.setBounds(565, 214, 111, 20);
		contentPane.add(comboCli);
		
		lblCreneau = new JLabel("CRENEAU :");
		lblCreneau.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCreneau.setBounds(459, 170, 84, 14);
		contentPane.add(lblCreneau);
		
		comboCre = new JComboBox();
		comboCre.setBounds(565, 169, 111, 20);
		contentPane.add(comboCre);
		
		lblGestionRendezvous = new JLabel("Gestion Rendez-vous:");
		lblGestionRendezvous.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblGestionRendezvous.setBounds(371, 11, 338, 35);
		contentPane.add(lblGestionRendezvous);
		ComboCli();
		ComboCre();
		updateTable();
		
		
	}
}
