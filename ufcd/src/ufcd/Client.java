/*
 * Cria��o da base de dados
 * create table if not exists Cliente
  				(id_cliente smallint not null auto_increment,
				nomeCliente varchar(255) not null,
				moradaCliente varchar(255) not null,
				cidadeCliente varchar(255) not null,
				teleCliente varchar(9) not null,
				emailCliente varchar(255) not null,
				nifCliente varchar(255) not null,
				estadoCliente ENUM('Activo' , 'Inactivo'),
				primary key(id_cliente))
				engine = innodb;
	create table if not exists Avaria
				(id_avaria smallint not null auto_increment,
				id_cliente smallint not null,
				tipoAvaria varchar(255) not null,
				descricaoAvaria varchar(255) not null,
				precoAvaria varchar(255) not null,
				estadoAvaria ENUM('Activo' , 'Inactivo'),
				primary key(id_avaria))
				engine = innodb;
	create table if not exists Equipamentos
				(id_equipamentos smallint not null auto_increment,
				id_colaboradores smallint not null,
				tipoEquipamento varchar(255) not null,
				localEquipamento varchar(255) not null,
				primary key(id_equipamentos))
				engine = innodb;
	create table if not exists Utilizadores
				(id_utilizador smallint not null auto_increment,
				loginUtilizador varchar(25) not null,
				passUtilizador varchar(25) not null,
				nomeUtilizador varchar(255) not null,
				moradaUtilizador varchar(255) not null,
				cidadeUtilizador varchar(255) not null,
				nifUtilizador varchar(255) not null,
				tipoUtilizador ENUM('IT','Colaboradores'),
				primary key(id_utilizador))
				engine = innodb;
 * 
 * 
 * 
*/
package ufcd;

import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class Client extends JFrame implements ActionListener {

	 /**
	 * variaveis que importao ai proprio programa
	 */
	private static final long serialVersionUID = 1L;
	String tipo, nome, pass;
	/*
	 * Variaveis para o MenuBar
	 */
	JMenuBar menuBar;
	JMenu mnOpcoes;
	JMenuItem mniNCliente, mniNAvaria;
	
	/*
	 * Variaveis para o painel Login
	 */
	JPanel login;
	
	JLabel lblNome, lblPass;
	JTextField tfNome, tfPass;
	JButton btLogin;
	
	
	/*
	 * Variaveis para o conteudo tabbed
	 */
	JTabbedPane conteudo;
	//Painel Cliente
	JPanel cliente;
	
	//Painel Avarias
	JPanel avarias, selAvarias;
	JLabel lbl;
	JScrollPane panAvaria;
	JTable tabelaAvaria;
	JComboBox<Object> cbProavarias;
	JTextField tfSel;
	JButton btSel;
	String [] titulo = {"Nr de Cliente","Nome","Nif","Codigo Postal","Email","Morada"};
	String [][] Data = new String[1][6];
	
	//Painel NovoUtilizador
	JPanel novoUtilizador;
	JLabel lblNUNome, lblNUPass;
	JTextField tfNUNome, tfNUPass; 
	JButton btNURegisto;
	
	/*
	 * Variaveis para a conec��o
	 */
	static Connection con = null;
	 
	 
	
	
	public Client() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		menuBar = new JMenuBar();
		
		mnOpcoes = new JMenu("Op��es");
		menuBar.add(mnOpcoes);
		
		mniNCliente = new JMenuItem("Novo Cliente");
		mniNCliente.addActionListener(this);
		mnOpcoes.add(mniNCliente);
		
		mniNAvaria = new JMenuItem("Nova Avaria");
		mnOpcoes.add(mniNAvaria);
		
		//Painel LOGIN
		login = new JPanel();
		login.setLayout(null);
		getContentPane().add(login);
		
		lblNome = new JLabel("Nome de Utilizador");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNome.setBounds(525,150, 150, 20);
		lblNome.setLabelFor(tfNome);
		login.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setHorizontalAlignment(SwingConstants.CENTER);
		tfNome.setBounds(535, 180, 130, 20);
		tfNome.setColumns(10);
		login.add(tfNome);
		
		lblPass = new JLabel("Palavra-Passe");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setFont(new Font("Arial", Font.PLAIN, 17));
		lblPass.setBounds(535, 250, 130, 20);
		lblPass.setLabelFor(tfPass);
		login.add(lblPass);
		
		tfPass = new JTextField();
		tfPass.setHorizontalAlignment(SwingConstants.CENTER);
		tfPass.setBounds(535, 280, 130, 20);
		tfPass.setColumns(10);
		login.add(tfPass);
		
		btLogin = new JButton("Entrar!");
		btLogin.setBounds(535, 337, 130, 35);
		btLogin.addActionListener(this);
		login.add(btLogin);
		///Fim do Login
		
		//Inicio do tabed conteudo.
		conteudo = new JTabbedPane();
		
		/*
		 *Avarias
		 */
		avarias = new JPanel();
		avarias.setLayout(new BorderLayout());
		conteudo.addTab("Avarias", avarias);
	
		selAvarias = new JPanel();
		lbl = new JLabel("Prourar por");
		selAvarias.add(lbl);
		cbProavarias = new JComboBox<Object>();
		cbProavarias.setModel(new DefaultComboBoxModel<Object>(new String[] {"Cliente", "Colaborador", "Tipo de Equipamento", "Mes"}));
		selAvarias.add(cbProavarias);
		tfSel = new JTextField();
		tfSel.setColumns(10);
		selAvarias.add(tfSel);
		btSel = new JButton("Procurar");
		selAvarias.add(btSel);
		avarias.add(selAvarias, BorderLayout.NORTH);
		
		tabelaAvaria = new JTable(Data, titulo);
		panAvaria = new JScrollPane(tabelaAvaria);
		
		avarias.add(panAvaria, BorderLayout.CENTER);
		/*
		 *CLientes 
		 */
		cliente = new JPanel();
		conteudo.addTab("Clientes",cliente);
		
		//o painel de registo
		novoUtilizador = new JPanel();
		novoUtilizador.setLayout(null);
		
		lblNUNome = new JLabel("Nome de Utilizador");
		lblNUNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNUNome.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNUNome.setBounds(525,150, 150, 20);
		novoUtilizador.add(lblNUNome);
		
		tfNUNome = new JTextField();
		tfNUNome.setHorizontalAlignment(SwingConstants.CENTER);
		tfNUNome.setBounds(535, 180, 130, 20);
		tfNUNome.setColumns(10);
		novoUtilizador.add(tfNUNome);
		
		
		lblNUPass = new JLabel("Palavra-Passe");
		lblNUPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblNUPass.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNUPass.setBounds(535, 250, 130, 20);
		novoUtilizador.add(lblNUPass);
		
		tfNUPass = new JTextField();
		tfNUPass.setHorizontalAlignment(SwingConstants.CENTER);
		tfNUPass.setBounds(535, 280, 130, 20);
		tfNUPass.setColumns(10);
		novoUtilizador.add(tfNUPass);

		
		btNURegisto = new JButton("Registo");
		btNURegisto.setBounds(535, 337, 130, 35);
		btNURegisto.addActionListener(this);
		novoUtilizador.add(btNURegisto);	
		setBounds(100, 100, 1200, 750);
	}


	public static void main(String[] args) throws SQLException 
	{
		//Criar();
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btLogin)
		{
			try {
				Login(tfNome.getText(), tfPass.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == mniNCliente)
		{
			this.remove(conteudo);
			this.add(novoUtilizador);
			this.doLayout();
			this.validate();
			//repaint();
			//novoUtilizador.validate();
			
		}
		if(e.getSource() == btNURegisto)
		{
			try {
				NovoUtilizador(tfNUNome.getText(), tfNUPass.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/*
	 * Codigo para a cria��o de um novo utilizador
	 */
	public void NovoUtilizador(String N, String P) throws SQLException
	{
		Conect();//Abir a conec�ao
		Statement st = null;
		ResultSet rs = null;
		st = con.createStatement();
		String str_QUERY = "SELECT id_utilizador FROM Utilizadores where loginUtilizador like '"+ N +"';" ;
		rs = st.executeQuery(str_QUERY);
		if(rs.next())
		{
			//custom title, warning icon
			JOptionPane.showMessageDialog(this,
			    "Esse utilizador ja se encontra registado",
			    "Erro no Registo",
			    JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			
		}
		
	}
	
	/*
	 * Codigo para verificar o login.
	 */
	public void Login(String N, String P) throws SQLException
	{
		Conect();//Abir a conec�ao
		Statement st = null;
		ResultSet rs = null;
		st = con.createStatement();
	    String str_QUERY = "SELECT id_utilizador, tipoUtilizador FROM Utilizadores where loginUtilizador like '"+ N +"' AND passUtilizador like'"+ P +"';";
	    rs = st.executeQuery(str_QUERY);//executa a query
	    //se houver resultados apaga o painel login, fecha a conec�ao.
		if(rs.next())
		{
			tipo = rs.getString(2);
			login.setVisible(false);
			con.close();
			con = null;
			this.add(conteudo);
			this.remove(login);
			this.setJMenuBar(menuBar);
		}
		else
		{
			con.close();
			con = null;
		} 
	}
	
	
		
	public void Conect() throws SQLException
	{
		String drive = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/Ticket";
		try {
		     Class.forName(drive);
		     con = DriverManager.getConnection(url, "root","");
		     if (con != null)
		       System.out.println("\n Sucesso na liga��o a BD Dados \n");
		 }catch( ClassNotFoundException e) {e.printStackTrace();}
	}
	
	/*public static void Criar() throws SQLException, ClassNotFoundException
	{
		String drive = "com.mysql.jdbc.Driver";
		Class.forName(drive);
		con = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
		Statement s = con.createStatement();
		if ( s.executeUpdate("create database if not exists Ticket default character set latin1 default collate latin1_bin;") == 1)
		{
			s.executeUpdate("use Ticket;");
			s.executeUpdate("create table if not exists Cliente"+
				"(id_cliente smallint not null auto_increment,"+
				"nomeCliente varchar(255) not null,"+
				"moradaCliente varchar(255) not null,"+
				"cidadeCliente varchar(255) not null,"+
				"teleCliente varchar(9) not null,"+
				"emailCliente varchar(255) not null,"+
				"nifCliente varchar(255) not null,"+
				"estadoCliente ENUM('Activo','Inactivo'))"+
				"engine = innodb;");
			s.executeUpdate("create table if not exists Avaria"+
				"(id_avaria smallint not null auto_increment,"+
				"id_cliente smallint not null,"+
				"tipoAvaria varchar(255) not null,"+
				"descricaoAvaria varchar(255) not null,"+
				"precoAvaria varchar(255) not null,"+
				"estadoAvaria ENUM('Activo' , 'Inactivo'))"+
				"engine = innodb;");
			s.executeUpdate("create table if not exists Equipamentos"+
				"(id_equipamentos smallint not null auto_increment,"+
				"id_colaboradores smallint not null,"+
				"tipoEquipamento varchar(255) not null,"+
				"localEquipamento varchar(255) not null)"+
				"engine = innodb;");
			s.executeUpdate("create table if not exists Utilizadores"+
				"(id_utilizador smallint not null auto_increment,"+
				"loginUtilizador varchar(25) not null,"+
				"passUtilizador varchar(25) not null,"+
				"nomeUtilizador varchar(255) not null"+
				"moradaUtilizador varchar(255) not null"+
				"cidadeUtilizador varchar(255) not null"+
				"nifUtilizador varchar(255) not null"+
				"tipoUtilizador ENUM('IT','Colaboradores'))"+
				"engine = innodb;");
		}
		else
		{
			con.close();
			return;
		}
		
	}*/
}