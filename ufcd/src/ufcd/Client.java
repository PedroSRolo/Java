package ufcd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Client extends JFrame {

	 JPanel content;
	 JTextField tfNome;
	 JTextField tfPass;
	
	Toolkit tlk;
	Dimension dim;
	
	
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
	public Client() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 750);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpcoes = new JMenu("Op��es");
		menuBar.add(mnOpcoes);
		
		JMenuItem mniNCliente = new JMenuItem("Novo Cliente");
		mnOpcoes.add(mniNCliente);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Nova Avaria");
		mnOpcoes.add(mntmNewMenuItem_1);
		content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		content.setLayout(new GridLayout(0, 1, 0, 0));
		JPanel panel = new JPanel();
		content.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome de Utilizador");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNome.setBounds(525,150, 150, 20);
		panel.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setLabelFor(tfNome);
		tfNome.setBounds(535, 180, 130, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblPass = new JLabel("Palavra-Passe");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setFont(new Font("Arial", Font.PLAIN, 17));
		lblPass.setBounds(535, 250, 130, 20);
		panel.add(lblPass);
		
		tfPass = new JTextField();
		tfPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setLabelFor(tfPass);
		tfPass.setBounds(535, 280, 130, 20);
		panel.add(tfPass);
		tfPass.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(535, 337, 130, 35);
		panel.add(btnNewButton);
	}
}
