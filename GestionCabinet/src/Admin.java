import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;

public class Admin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 367);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGestionDeClients = new JButton("Gestion de Clients");
		btnGestionDeClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Client obj=new Client();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		btnGestionDeClients.setBounds(51, 129, 170, 47);
		contentPane.add(btnGestionDeClients);
		
		JButton btnGestionDeMedecins = new JButton("Gestion de Medecins");
		btnGestionDeMedecins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Medecins med=new Medecins();
				med.setVisible(true);
				med.setLocationRelativeTo(null);
			}
		});
		btnGestionDeMedecins.setBounds(51, 206, 170, 47);
		contentPane.add(btnGestionDeMedecins);
		
		JButton btnGestionDeRendezvous = new JButton("Gestion de Rendez-vous");
		btnGestionDeRendezvous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rv rv=new Rv();
				rv.setVisible(true);
				rv.setLocationRelativeTo(null);
			}
		});
		btnGestionDeRendezvous.setBounds(386, 129, 170, 47);
		contentPane.add(btnGestionDeRendezvous);
		
		JButton btnGestionDeCreneaux = new JButton("Gestion de Creneaux");
		btnGestionDeCreneaux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Creneaux cr=new Creneaux();
				cr.setVisible(true);
				cr.setLocationRelativeTo(null);
			}
		});
		btnGestionDeCreneaux.setBounds(386, 206, 170, 47);
		contentPane.add(btnGestionDeCreneaux);
		
		JLabel lblPanelAdministrateur = new JLabel("Panel Administrateur");
		lblPanelAdministrateur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 45));
		lblPanelAdministrateur.setBounds(61, 38, 501, 47);
		contentPane.add(lblPanelAdministrateur);
	}
}
