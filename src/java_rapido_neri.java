/* Desenvolvido por Neri Aldoir Neitzke - www.informaticon.com.br
   Coordenador do curso de Sistemas da Universidade Ulbra campus Carazinho
   mais de 1700 vídeo aulas de minha autoria: videoaulas@informaticon.com.br */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
class java_rapido_neri extends jFrame implements ActionListener, ItemListener, javax_swing
{
    configuracoes obj_conf = new configuracoes();
    JLabel lb_titulo, lb_qual_banco, lb_nomebanco, lb_usuario, lb_senha,lb_tabelas, lb_driver, lb_formato_tela;
    JLabel lb_rodape, lb_tit_tabelas, lb_tit_gera, lb_cor_botoes, lb_tit_labels, lb_labels;
    JLabel lb_imagem_formato, lb_codigo, lb_nome, lb_endereco, lb_numero, lb_bairro, lb_cidade, lb_uf, lb_fone, lb_data_nasc;
    JLabel lb_cor_frente_label, lb_cor_frente_tf, lb_cor_fundo_tf, lb_cor_formulario;
    ImageIcon imagem_formato;
    JTabbedPane painel_pai;
    JLabel lb_troca, lb_como_ficara;
    JPanel panel_bancos, panel_gera_classe,panel_tabelas, panel_ajustes, panel_escolha_tela, panel_labels;
    JComboBox cb_tipobanco,cb_tabelas,cb_cor_frente_labels, cb_cor_botoes;
    JComboBox cb_cor_frente_textfields, cb_cor_fundo_textfields, cb_cor_form;
    JTextField tf_banco, tf_usuario, tf_senha, tf_driver, tf_troca_label;
    JTextField tf_codigo, tf_nome, tf_endereco, tf_numero, tf_bairro, tf_cidade, tf_uf, tf_fone, tf_data_nasc;
    JButton bt_conecta, bt_gera_classe_java, bt_gera_todas, bt_todos, bt_selecionados,bt_voltar, bt_grava_config;
    TextArea ta_classe_gerada;
    String cor_frente_label="black", cor_frente_textfields="black", cor_fundo_textfields="white", cor_formulario="green", cor_botoes="red";
    String classe_gerada="", nome_arquivo="", diretorio="";
    ResultSet rs_java_rapido, rs_tabela;
    Statement st_java_rapido;
    Connection con_java_rapido;
    String url,driver_banco,usuario,senha, nome_tabela, campos_selecionados;
    int quant_campos, quant_campos_gerados, pos_linha=120, pos_coluna=100, linha_botoes=220;
    int indice_label;
    int [] tamanho_linha;
    DatabaseMetaData dmd_java_rapido;
    List lista_campos, lista_seraogerados, lista_labels, lista_labels_legenda;
    ButtonGroup grupo_escolha_formato_tela;
    JRadioButton horizontal, vertical, lookandfeel;
    
    ImageIcon img_primeiro_reg       = new ImageIcon("imagens/primeiro_registro.gif");
    ImageIcon img_registro_ant       = new ImageIcon("imagens/registro_anterior.gif");
    ImageIcon img_proximo_reg        = new ImageIcon("imagens/proximo_registro.gif");
    ImageIcon img_ultimo_reg         = new ImageIcon("imagens/ultimo_registro.gif");
    ImageIcon img_novo               = new ImageIcon("imagens/novo_registro.gif");
    ImageIcon img_grava              = new ImageIcon("imagens/gravar_registro.gif");
    ImageIcon img_altera             = new ImageIcon("imagens/alterar_registro.gif");
    ImageIcon img_exclui             = new ImageIcon("imagens/delete.gif");

    JButton botao_primeiro_registro  = new JButton(img_primeiro_reg);
    JButton botao_registro_anterior  = new JButton(img_registro_ant);
    JButton botao_proximo_registro   = new JButton(img_proximo_reg);
    JButton botao_ultimo_registro    = new JButton(img_ultimo_reg);
    JButton botao_novo               = new JButton(img_novo);
    JButton botao_gravar             = new JButton(img_grava);
    JButton botao_alterar            = new JButton(img_altera);
    JButton botao_excluir            = new JButton(img_exclui);
    
    

/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender nossas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/

    java_rapido_neri()
    {
         settitle("Sistema Gerador de aplicativos rápidos em java - Prof. Neri www.INFORMATICON.com.br");
         setSize(800,600);
         setLocation(150,80);
         setResizable(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         getContentPane().setBackground(Color.gray);
         getContentPane().setLayout(null);

         lb_titulo          = new JLabel("Gerador de aplicações Rápidas Java - Prof. Neri www.INFORMATICON.com.br");
         lb_qual_banco      = new JLabel("Escolha o tipo banco de dados.: ");
         lb_nomebanco       = new JLabel("Nome do banco de dados .: ");
         lb_usuario         = new JLabel("Usuário .: ");
         lb_senha           = new JLabel("Senha .: ");
         lb_tabelas         = new JLabel("Escolha a tabela do banco .: ");
         lb_labels          = new JLabel("Labels Originais.: ");
         lb_driver          = new JLabel("Driver do banco de Dados .: ");
         lb_rodape          = new JLabel("Vídeo aulas do Prof. Neri www.INFORMATICON.com.br - ULBRA");
         lb_tit_tabelas     = new JLabel("Vídeo aulas do Prof. Neri www.INFORMATICON.com.br - ULBRA");
         lb_tit_gera        = new JLabel("Vídeo aulas do Prof. Neri www.INFORMATICON.com.br");
         lb_tit_labels      = new JLabel("Vídeo aulas do Prof. Neri www.INFORMATICON.com.br");
         lb_formato_tela    = new JLabel("Escolha o formato");
         lb_codigo          = new JLabel("Código .: ");
         lb_nome            = new JLabel("Nome .: ");
         lb_endereco        = new JLabel("Endereço .: ");
         lb_numero          = new JLabel("Número .: ");
         lb_bairro          = new JLabel("Bairro .: ");
         lb_cidade          = new JLabel("Cidade .: ");
         lb_uf              = new JLabel("UF .: ");
         lb_fone            = new JLabel("Telefone .: ");
         lb_data_nasc       = new JLabel("Data de Nascimento .: ");
         lb_cor_formulario  = new JLabel("Cor de fundo do Fomulário") ;
         lb_cor_frente_label= new JLabel("Cor frente Label");
         lb_cor_frente_tf   = new JLabel("Cor frente campos");
         lb_cor_fundo_tf    = new JLabel("Cor fundo campos");
         lb_cor_botoes      = new JLabel("Cor fundo botões");
         lb_troca           = new JLabel("Trocar Legenda:");
         lb_como_ficara     = new JLabel("Como ficará:");
         
                       
         tf_banco           = new JTextField();
         tf_usuario         = new JTextField();
         tf_senha           = new JTextField();
         tf_driver          = new JTextField();
         tf_troca_label     = new JTextField();
         tf_codigo          = new JTextField("1");
         tf_nome            = new JTextField("Neri Aldoir Neitzke");
         tf_endereco        = new JTextField("Rua da Informaticon Cursos");
         tf_numero          = new JTextField("458");
         tf_bairro          = new JTextField("Centro");
         tf_cidade          = new JTextField("Carazinho");
         tf_uf              = new JTextField("RS");
         tf_fone            = new JTextField("(54)3330-1222");
         tf_data_nasc       = new JTextField("não vou contar");
         

         bt_conecta         = new JButton("Conectar com o Banco de Dados");
         bt_gera_classe_java= new JButton("Gera classe java");
         bt_todos           = new JButton(" >> ");
         bt_selecionados    = new JButton("  > ");
         bt_voltar          = new JButton(" << ");
         bt_grava_config    = new JButton("Gravar Configurações");
         bt_gera_todas      = new JButton("Gera todas");
         ta_classe_gerada   = new TextArea();
         lista_campos       = new List();
         lista_seraogerados = new List();
         lista_labels       = new List();
         lista_labels_legenda= new List();
         grupo_escolha_formato_tela = new ButtonGroup();
         horizontal         = new JRadioButton("Horizontal");
         vertical           = new JRadioButton("Vertical");
         lookandfeel        = new JRadioButton("Com LookAndFeel ?");
         grupo_escolha_formato_tela.add(horizontal);
         grupo_escolha_formato_tela.add(vertical);

         painel_pai        = new JTabbedPane();
         panel_bancos      = new JPanel();
         panel_gera_classe = new JPanel();
         panel_tabelas     = new JPanel();
         panel_ajustes     = new JPanel();
         panel_escolha_tela= new JPanel();
         panel_labels      = new JPanel();
         cb_tipobanco      = new JComboBox();
         cb_tabelas        = new JComboBox();
         
         //escolhendo as cores 
         cb_cor_frente_labels = new JComboBox();
         cb_cor_frente_labels.addItem("Preto");
         cb_cor_frente_labels.addItem("Amarelo");
         cb_cor_frente_labels.addItem("Azul");
         cb_cor_frente_labels.addItem("Vermelho");
         cb_cor_frente_labels.addItem("Branco");
         cb_cor_frente_labels.addItem("Verde");
         
         cb_cor_frente_textfields = new JComboBox();
         cb_cor_frente_textfields.addItem("Preto");
         cb_cor_frente_textfields.addItem("Amarelo");
         cb_cor_frente_textfields.addItem("Azul");
         cb_cor_frente_textfields.addItem("Vermelho");
         cb_cor_frente_textfields.addItem("Branco");
         cb_cor_frente_textfields.addItem("Verde");
        
         cb_cor_fundo_textfields = new JComboBox();
         cb_cor_fundo_textfields.addItem("Preto");
         cb_cor_fundo_textfields.addItem("Amarelo");
         cb_cor_fundo_textfields.addItem("Azul");
         cb_cor_fundo_textfields.addItem("Vermelho");
         cb_cor_fundo_textfields.addItem("Branco");
         cb_cor_fundo_textfields.addItem("Verde");
         
         cb_cor_form = new JComboBox();
         cb_cor_form.addItem("Preto");
         cb_cor_form.addItem("Amarelo");
         cb_cor_form.addItem("Azul");
         cb_cor_form.addItem("Vermelho");
         cb_cor_form.addItem("Branco");
         cb_cor_form.addItem("Verde");
         
         cb_cor_botoes = new JComboBox();
         cb_cor_botoes.addItem("Preto");
         cb_cor_botoes.addItem("Amarelo");
         cb_cor_botoes.addItem("Azul");
         cb_cor_botoes.addItem("Vermelho");
         cb_cor_botoes.addItem("Branco");
         cb_cor_botoes.addItem("Verde");
         
         // imagem_formato    = new ImageIcon("horizontal.GIF");
         // lb_imagem_formato = new JLabel(imagem_formato);
         horizontal.setSelected(true);
         lookandfeel.setSelected(true);

         cb_tipobanco.addItem("Oracle");
         cb_tipobanco.addItem("Sql Server");
         cb_tipobanco.addItem("PostgreSQL");
         cb_tipobanco.addItem("Firebird");
         cb_tipobanco.addItem("MySQL");
         cb_tipobanco.addItem("Access");

/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/

         panel_bancos     .setLayout(null);
         panel_gera_classe.setLayout(null);
         panel_tabelas    .setLayout(null);
         panel_ajustes    .setLayout(null);
         panel_escolha_tela.setLayout(null);
         panel_labels      .setLayout(null);

         lb_titulo    .setBounds(20, 20,780,30);
         lb_qual_banco.setBounds(30, 70,300,20);
         lb_nomebanco .setBounds(65, 130,300,20);
         tf_banco     .setBounds(310,130,400,20);
         lb_driver    .setBounds(65, 100,300,20);
         tf_driver    .setBounds(310,100,400,20);
         lb_usuario   .setBounds(200,160,300,20);
         tf_usuario   .setBounds(310,160,100,20);
         lb_senha     .setBounds(210,190,300,20);
         tf_senha     .setBounds(310,190,100,20);
         bt_conecta   .setBounds(80,270,400,30);
         lb_tabelas   .setBounds(30, 70,300,20);
         lb_labels    .setBounds(50, 70,300,20);
         lb_rodape    .setBounds(30,350,600,30);
         lb_tit_tabelas.setBounds(30,20,600,30);
         lb_tit_labels.setBounds(50,20,600,30);
         lb_tit_gera  .setBounds(350,4,400,30);
         panel_escolha_tela.setBounds(50,50,300,300);
         lb_formato_tela.setBounds(30,10,100,25);
         horizontal   .setBounds(30,30,100,25);
         vertical     .setBounds(30,60,100,25);
         lb_cor_frente_label.setBounds(30,100,100,25);
         cb_cor_frente_labels.setBounds(30,130,100,25);
         lb_cor_frente_tf.setBounds(30,160,160,25);
         cb_cor_frente_textfields.setBounds(30,190,100,25);
         lb_cor_fundo_tf.setBounds(30,210,150,25);
         cb_cor_fundo_textfields.setBounds(30,240,100,25);
         lb_cor_formulario.setBounds(30,270,150,25);
         cb_cor_form.setBounds(30,300,100,25);
         lb_cor_botoes.setBounds(30,330,150,25);
         cb_cor_botoes.setBounds(30,360,100,25);
         lookandfeel.setBounds(150,360,250,25);
         bt_grava_config.setBounds(420,360,250,25);
         tf_troca_label.setBounds(250,200,150,25);
         lb_troca.setBounds(270,170,150,25);
         lb_como_ficara.setBounds(500, 70,200,20);


//         lb_imagem_formato.setBounds(30,90,250,250);
         
         bt_todos       .setBounds(270,200,70,20);
         bt_selecionados.setBounds(270,240,70,20);
         bt_voltar      .setBounds(270,280,70,20);

         bt_gera_classe_java.setBounds(10,  5,150, 20);
         bt_gera_todas      .setBounds(170, 5,150, 20);

         ta_classe_gerada   .setBounds(20, 40,660,350);
         posiciona_tela_demonstrativa_horizontal();
         posiciona_botoes_demonstracao();

         //bt_todos.setToolTipText("Clique aqui para inserir todos os campos da tabela");
         //bt_selecionados.setToolTipText("Clique aqui para inserir apenas os campos selecionados");
         //bt_voltar.setToolTipText("Clique aqui para excluir todos os campos selecionados");
         
         bt_gera_classe_java.addActionListener(this);
         bt_gera_todas      .addActionListener(this);
         bt_conecta         .addActionListener(this);
         cb_tipobanco       .addActionListener(this);
         cb_tabelas         .addActionListener(this);
         bt_todos           .addActionListener(this);
         bt_selecionados    .addActionListener(this);
         bt_voltar          .addActionListener(this);
         horizontal         .addActionListener(this);
         vertical           .addActionListener(this);
         lookandfeel        .addActionListener(this);
         bt_grava_config    .addActionListener(this);
         tf_troca_label     .addActionListener(this);
         lista_labels       .addActionListener(this);
         cb_cor_frente_labels.addItemListener(this);
         cb_cor_frente_textfields.addItemListener(this);
         cb_cor_fundo_textfields.addItemListener(this);
         cb_cor_botoes.addItemListener(this);
         cb_cor_form.addItemListener(this);

         
/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/
         cb_tipobanco .setBounds(310,70,200,20);
         cb_tabelas   .setBounds(250,70,200,20);
         lista_campos .setBounds(50,110,200,280);
         lista_seraogerados.setBounds(450,110,200,280);
         lista_labels.setBounds(30,110,200,280);
         lista_labels_legenda.setBounds(450,110,200,280);
         painel_pai   .setBounds(20,80,720,450);

         lb_titulo    .setFont(new Font("Arial",Font.BOLD,20));
         lb_qual_banco.setFont(new Font("Arial",Font.BOLD,16));
         lb_nomebanco .setFont(new Font("Arial",Font.BOLD,16));
         lb_driver    .setFont(new Font("Arial",Font.BOLD,16));
         lb_usuario   .setFont(new Font("Arial",Font.BOLD,16));
         lb_senha     .setFont(new Font("Arial",Font.BOLD,16));
         lb_tabelas   .setFont(new Font("Arial",Font.BOLD,16));
         lb_labels    .setFont(new Font("Arial",Font.BOLD,16));
         lb_como_ficara.setFont(new Font("Arial",Font.BOLD,16));
         bt_conecta   .setFont(new Font("Arial",Font.BOLD,18));
         lb_rodape    .setFont(new Font("Arial",Font.BOLD,18));
         lb_tit_tabelas.setFont(new Font("Arial",Font.BOLD,18));
         lb_tit_gera   .setFont(new Font("Arial",Font.BOLD,12));
         lb_tit_labels .setFont(new Font("Arial",Font.BOLD,18));
         
         lb_titulo    .setForeground(Color.yellow);
         lb_qual_banco.setForeground(Color.blue);
         lb_nomebanco .setForeground(Color.blue);
         lb_usuario   .setForeground(Color.blue);
         lb_senha     .setForeground(Color.blue);
         lb_tabelas   .setForeground(Color.blue);
         lb_driver    .setForeground(Color.blue);
         lb_rodape    .setForeground(Color.red);
         lb_tit_tabelas.setForeground(Color.red);
         lb_tit_gera   .setForeground(Color.red);
         lb_tit_labels .setForeground(Color.red);
         lb_troca      .setForeground(Color.red);
         lb_como_ficara.setForeground(Color.red);

         painel_pai   .setBackground(new Color(0,255,255));
         panel_escolha_tela.setBackground(new Color(150,100,155));
         
         painel_pai.addTab("Definições do Banco",panel_bancos);
         painel_pai.addTab("Tabelas e Campos",panel_tabelas);
         painel_pai.addTab("Ajustes",panel_ajustes);
         painel_pai.addTab("Escolhendo nomes Labels",panel_labels);
         painel_pai.addTab("Geração da Classe",panel_gera_classe);
         
         /*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
         Ninguém está autorizado a vender essas vídeo aulas a não ser a 
         INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/

         lista_campos.setMultipleMode(true);


         panel_bancos.add(lb_qual_banco);
         panel_bancos.add(cb_tipobanco);
         panel_bancos.add(lb_nomebanco);
         panel_bancos.add(lb_usuario);
         panel_bancos.add(lb_senha);
         panel_bancos.add(tf_banco);
         panel_bancos.add(tf_usuario);
         panel_bancos.add(tf_senha);
         panel_bancos.add(bt_conecta);
         panel_bancos.add(lb_driver);
         panel_bancos.add(tf_driver);
         panel_bancos.add(lb_rodape);

         panel_tabelas.add(lb_tabelas);
         panel_tabelas.add(cb_tabelas);
         panel_tabelas.add(lista_campos);
         panel_tabelas.add(bt_todos);
         panel_tabelas.add(bt_selecionados);
         panel_tabelas.add(bt_voltar);
         panel_tabelas.add(lista_seraogerados);
         panel_tabelas.add(lb_tit_tabelas);

         panel_labels.add(lista_labels);
         panel_labels.add(tf_troca_label);
         panel_labels.add(lb_labels);
         panel_labels.add(lb_tit_labels);
         panel_labels.add(lb_troca);
         panel_labels.add(lb_como_ficara);
         panel_labels.add(lista_labels_legenda);
         
         
         panel_ajustes.add(horizontal);
         panel_ajustes.add(vertical);
         panel_ajustes.add(lb_formato_tela);
         panel_ajustes.add(lb_cor_frente_label);
         panel_ajustes.add(cb_cor_frente_labels);
         panel_ajustes.add(lb_cor_frente_tf);
         panel_ajustes.add(cb_cor_frente_textfields);
         panel_ajustes.add(lb_cor_fundo_tf);
         panel_ajustes.add(cb_cor_fundo_textfields);
         panel_ajustes.add(lb_cor_formulario);
         panel_ajustes.add(cb_cor_form);
         panel_ajustes.add(lb_cor_botoes);
         panel_ajustes.add(cb_cor_botoes);
         panel_ajustes.add(botao_primeiro_registro);
         panel_ajustes.add(botao_registro_anterior);
         panel_ajustes.add(botao_proximo_registro);
         panel_ajustes.add(botao_ultimo_registro);
         panel_ajustes.add(botao_novo);
         panel_ajustes.add(botao_gravar);
         panel_ajustes.add(botao_alterar);
         panel_ajustes.add(botao_excluir);
         panel_ajustes.add(lookandfeel);
         panel_ajustes.add(bt_grava_config);

         botao_excluir.setBackground(Color.blue);

         
         adiciona_demonstracao_panelajustes();
                  
         panel_gera_classe.add(bt_gera_classe_java);
         panel_gera_classe.add(ta_classe_gerada);
         panel_gera_classe.add(lb_tit_gera);
         panel_gera_classe.add(bt_gera_todas);


         getContentPane().add(lb_titulo);
         getContentPane().add(painel_pai);
         
         lookandfell();
         le_configuiracoes();
         Tela_de_Abertura_desse_sistema_favor_nao_alterar();
    }
    public static void main(String args[])
    {
        jFrame formulario = new java_rapido_neri();
        formulario.setVisible(true);
    }
    /*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
    Ninguém está autorizado a vender essas vídeo aulas a não ser a 
    INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/
    
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getSource() == cb_cor_frente_labels)
        {
            if (cb_cor_frente_labels.getSelectedItem().equals("Vermelho"))
                cor_frente_label = "red";
            else if (cb_cor_frente_labels.getSelectedItem().equals("Azul"))
                cor_frente_label = "blue";
            else if (cb_cor_frente_labels.getSelectedItem().equals("Amarelo"))
                cor_frente_label = "yellow";
            else if (cb_cor_frente_labels.getSelectedItem().equals("Preto"))
                cor_frente_label = "black";
            else if (cb_cor_frente_labels.getSelectedItem().equals("Branco"))
                cor_frente_label = "white";
            else if (cb_cor_frente_labels.getSelectedItem().equals("Verde"))
                cor_frente_label = "green";

            cor_frente_labels_demonstrativos();
        }
        else if (e.getSource() == cb_cor_frente_textfields)
        {
            if(cb_cor_frente_textfields.getSelectedItem().equals("Vermelho"))
                cor_frente_textfields = "red";
            else if(cb_cor_frente_textfields.getSelectedItem().equals("Verde"))
                cor_frente_textfields = "green";
            else if(cb_cor_frente_textfields.getSelectedItem().equals("Amarelo"))
                cor_frente_textfields = "yellow";
            else if(cb_cor_frente_textfields.getSelectedItem().equals("Azul"))
                cor_frente_textfields = "blue";
            else if(cb_cor_frente_textfields.getSelectedItem().equals("Preto"))
                cor_frente_textfields = "black";
            else if(cb_cor_frente_textfields.getSelectedItem().equals("Branco"))
                cor_frente_textfields = "white";
            cor_frente_textfields_demonstrativos();            

        }
        else if (e.getSource() == cb_cor_fundo_textfields)
        {
            if(cb_cor_fundo_textfields.getSelectedItem().equals("Vermelho"))
                cor_fundo_textfields = "red";
            else if(cb_cor_fundo_textfields.getSelectedItem().equals("Verde"))
                cor_fundo_textfields = "green";
            else if(cb_cor_fundo_textfields.getSelectedItem().equals("Amarelo"))
                cor_fundo_textfields = "yellow";
            else if(cb_cor_fundo_textfields.getSelectedItem().equals("Azul"))
                cor_fundo_textfields = "blue";
            else if(cb_cor_fundo_textfields.getSelectedItem().equals("Preto"))
                cor_fundo_textfields = "black";
            else if(cb_cor_fundo_textfields.getSelectedItem().equals("Branco"))
                cor_fundo_textfields = "white";
            cor_fundo_textfields_demonstrativos();
        }
        else if (e.getSource() == cb_cor_form)
        {
            if(cb_cor_form.getSelectedItem().equals("Vermelho"))
                 panel_ajustes.setBackground(Color.red);
            else if(cb_cor_form.getSelectedItem().equals("Verde"))
                panel_ajustes.setBackground(Color.green);
            else if(cb_cor_form.getSelectedItem().equals("Azul"))
                panel_ajustes.setBackground(Color.blue);
            else if(cb_cor_form.getSelectedItem().equals("Amarelo"))
                panel_ajustes.setBackground(Color.yellow);
            else if(cb_cor_form.getSelectedItem().equals("Preto"))
                panel_ajustes.setBackground(Color.black);
            else if(cb_cor_form.getSelectedItem().equals("Branco"))
                panel_ajustes.setBackground(Color.white);
            
          
            
        }
        else if (e.getSource() == cb_cor_botoes)
        {
            if(cb_cor_botoes.getSelectedItem().equals("Vermelho"))
                cor_botoes = "red";
            else if(cb_cor_botoes.getSelectedItem().equals("Verde"))
                cor_botoes = "green";
            else if(cb_cor_botoes.getSelectedItem().equals("Amarelo"))
                cor_botoes = "yellow";
            else if(cb_cor_botoes.getSelectedItem().equals("Azul"))
                cor_botoes = "blue";
            else if(cb_cor_botoes.getSelectedItem().equals("Preto"))
                cor_botoes = "black";
            else if(cb_cor_botoes.getSelectedItem().equals("Branco"))
                cor_botoes = "white";
            cor_botoes();
        }
    }
    public void gera_actionPerformed()
    {
        ta_classe_gerada.append("\n    //Acoes\n");
        ta_classe_gerada.append("      public void actionPerformed(ActionEvent acao)\n");
        ta_classe_gerada.append("      {\n");
        ta_classe_gerada.append("            if (acao.getSource() == botao_primeiro_registro)\n");
        ta_classe_gerada.append("                vai_primeiro_registro();\n"); 
        ta_classe_gerada.append("            else if (acao.getSource() == botao_proximo_registro)\n");
        ta_classe_gerada.append("                vai_proximo_registro();\n"); 
        ta_classe_gerada.append("            else if (acao.getSource() == botao_registro_anterior)\n");
        ta_classe_gerada.append("                vai_registro_anterior();\n"); 
        ta_classe_gerada.append("            else if (acao.getSource() == botao_ultimo_registro)\n");
        ta_classe_gerada.append("                vai_ultimo_registro();\n"); 
        ta_classe_gerada.append("            else if (acao.getSource() == botao_gravar)\n");
        ta_classe_gerada.append("                gravar();\n"); 
        ta_classe_gerada.append("            else if (acao.getSource() == botao_excluir)\n");
        ta_classe_gerada.append("                excluir();\n"); 
        ta_classe_gerada.append("            else if (acao.getSource() == botao_alterar)\n");
        ta_classe_gerada.append("                alterar();\n"); 
        ta_classe_gerada.append("            else if (acao.getSource() == botao_novo)\n");
        ta_classe_gerada.append("                novo_registro();\n");
        ta_classe_gerada.append("            else if (acao.getSource() == tf_pesquisa)\n");
        ta_classe_gerada.append("                pesquisadigitacao();\n");
        ta_classe_gerada.append("            else if (acao.getSource() == cb_pesquisa)\n");
        ta_classe_gerada.append("                pesquisaviacombobox();\n");
        ta_classe_gerada.append("      }\n"); 



    }
            
    public void actionPerformed(ActionEvent acao)
    {
         if (acao.getSource() == bt_gera_classe_java)
         {
              gera_texto_java();
              JOptionPane.showMessageDialog(null, "Parabéns, o arquivo "+nome_arquivo+" foi gerado com sucesso"+
                     "\nno local = "+diretorio);

         }
         else  if (acao.getSource() == bt_conecta)
         {
              efetua_conexao();
         }
         else  if (acao.getSource() == bt_gera_todas)
         {
              gera_todas_as_classes();
         }
         else  if (acao.getSource() == lista_labels)
         {
             tf_troca_label.setText(lista_labels.getSelectedItem());
             tf_troca_label.requestFocus();
             indice_label = lista_labels.getSelectedIndex();
         }
         else  if (acao.getSource() == tf_troca_label)
         {
             lista_labels_legenda.replaceItem(tf_troca_label.getText(),indice_label);
         }
         else if(acao.getSource() == cb_tipobanco)
         {
             banco_escolhido();             
             //JOptionPane.showMessageDialog(null,"driver banco = "+driver_banco);
         }
         else if(acao.getSource() == cb_tabelas)
         {
             lista_campos.removeAll();
             try
             {
                 nome_tabela = String.valueOf(cb_tabelas.getSelectedItem());
                 st_java_rapido = con_java_rapido.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                 rs_tabela = st_java_rapido.executeQuery("select * from "+nome_tabela);
                 quant_campos = rs_tabela.getMetaData().getColumnCount();
                 for(int i = 1;i <= quant_campos; i++)
                     lista_campos.add(String.valueOf(rs_tabela.getMetaData().getColumnName(i)));            
             }
             catch(SQLException erro)
             {
             }
         }
         else if(acao.getSource() == bt_todos)
         {
             listar_todos();
         }
         else if(acao.getSource() == bt_selecionados)
         {
             listar_selecionados();
         }
         else if(acao.getSource() == bt_voltar)
         {
             lista_seraogerados.removeAll();
         }
         else if(acao.getSource() == horizontal)
             posiciona_tela_demonstrativa_horizontal();
             //lb_imagem_formato.setIcon(new ImageIcon("horizontal.GIF"));
         else if(acao.getSource() == vertical)
             posiciona_tela_demonstrativa_vertical();
             //lb_imagem_formato.setIcon(new ImageIcon("vertical.GIF"));
         else if(acao.getSource() == lookandfeel)
             adiciona_ou_nao_laf();        
         else if(acao.getSource() == bt_grava_config)
         {
                  obj_conf.banco    = ""+cb_tipobanco.getSelectedItem();
                  obj_conf.driver   = tf_driver.getText();
                  obj_conf.usuario  = tf_usuario.getText();
                  obj_conf.senha    = tf_senha.getText();
                  if (horizontal.isSelected())
                      obj_conf.formato  = "horizontal";
                  else
                      obj_conf.formato  = "vertical";
                  obj_conf.cor_frente_label = ""+cb_cor_frente_labels.getSelectedItem();
                  obj_conf.cor_frente_tf    = ""+cb_cor_frente_textfields.getSelectedItem();
                  obj_conf.cor_fundo_tf     = ""+cb_cor_fundo_textfields.getSelectedItem();
                  obj_conf.cor_form         = ""+cb_cor_form.getSelectedItem();
                  obj_conf.cor_botoes       = ""+cb_cor_botoes.getSelectedItem();
                  if(lookandfeel.isSelected())
                      obj_conf.laf = true;
                  else
                      obj_conf.laf = false;

                  try
                  {
                     FileOutputStream arquivo = new FileOutputStream ("configuracoes.txt");
                     ObjectOutputStream  obj_dados  = new ObjectOutputStream(arquivo);
                     obj_dados.writeObject(obj_conf);
                     obj_dados.flush();
                     JOptionPane.showMessageDialog(null,"Parabéns, arquivo de configurações gerados com sucesso");
                   }  
                  catch(Exception erro_grava)
                  {

                  }

         }
    }
    public void adiciona_ou_nao_laf()
    {
        if (lookandfeel.isSelected())
            ta_classe_gerada.append("       lookandfeel();\n");
        else
            ta_classe_gerada.append("       //lookandfeel();\n");
            
         
    }

    public void gera_texto_java()
    { 
        ta_classe_gerada.setText("");
        ta_classe_gerada.setText(""); //
        ta_classe_gerada.append("/*\n");
        ta_classe_gerada.append("    Programa Desenvolvido por Neri Aldoir Neitzke\n");
        ta_classe_gerada.append("    www.informaticon.com.br\n");
        ta_classe_gerada.append("*/\n");
        ta_classe_gerada.setText("");
        library_detect_class();
        ta_classe_gerada.append("import java.awt.*;\n");
        ta_classe_gerada.append("import java.awt.event.*;\n");
        ta_classe_gerada.append("import java.sql.*;\n");
        ta_classe_gerada.append("import javax.swing.*;\n");
        ta_classe_gerada.append("class "+nome_tabela+" extends JFrame implements ActionListener\n");
        ta_classe_gerada.append("{\n");
        ta_classe_gerada.append("     int navega = 0;\n");
        ta_classe_gerada.append("     ResultSet resultset;\n");
        ta_classe_gerada.append("     Statement statement;\n");
        ta_classe_gerada.append("     Connection connection;\n");
        ta_classe_gerada.append("     String driver, url, usuario, senha;\n");
        gera_labels();
        gerar_botoes();
        gera_textfield();
        ta_classe_gerada.append("    JPanel panel_pesquisa = new JPanel();\n");
        ta_classe_gerada.append("    JLabel lb_pesquisa = new JLabel(\"Pesquisar.: \");\n");
        ta_classe_gerada.append("    JTextField tf_pesquisa = new JTextField();\n");
        ta_classe_gerada.append("    JComboBox cb_pesquisa = new JComboBox();\n");
        ta_classe_gerada.append("    "+nome_tabela+"()\n");
        ta_classe_gerada.append("    {\n");
        ta_classe_gerada.append("        setTitle(\"Formulário de manutenção de "+nome_tabela+" - www.informaticon.com.br - Prof Neri\");\n");
        ta_classe_gerada.append("        setSize(800,600);\n");
        ta_classe_gerada.append("        setLocation(150,80);\n");
        ta_classe_gerada.append("        setResizable(false);\n");
        ta_classe_gerada.append("        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n");
        gera_cor_form();
        ta_classe_gerada.append("        getContentPane().setLayout(null);\n");
        ta_classe_gerada.append("        panel_pesquisa.setLayout(null);\n");
        ta_classe_gerada.append("        panel_pesquisa.setBounds(100,50,600,50);\n");
        ta_classe_gerada.append("        lb_pesquisa.setBounds(10,20,100,20);\n");
        ta_classe_gerada.append("        tf_pesquisa.setBounds(90,20,200,20);\n");
        ta_classe_gerada.append("        cb_pesquisa.setBounds(330,20,200,20);\n");
/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/    
        posiciona_labels();
        fonte_cor();
        gera_cor_frente_labels();
        gera_cores_texfield();
        gera_cor_botoes();
        
        if (horizontal.isSelected())
            posiciona_labels_e_textfields_na_horizontal();
        else
            posiciona_labels_e_textfields_na_vertical();
        posiciona_botoes_e_settooltiptext();

        ta_classe_gerada.append("        panel_pesquisa.add(lb_pesquisa);\n");
        ta_classe_gerada.append("        panel_pesquisa.add(tf_pesquisa);\n");
        ta_classe_gerada.append("        panel_pesquisa.add(cb_pesquisa);\n");
     
        
        
        ta_classe_gerada.append("        getContentPane().add(panel_pesquisa);\n");
        adiciona_labels();
        adiciona_ao_getContentPane();
        adiciona_botoes_no_getContentPane();
        adiciona_ou_nao_laf();
        gera_conexao();
        ta_classe_gerada.append("    }\n");
        ta_classe_gerada.append("    public static void main(String args[])\n");
        ta_classe_gerada.append("    {\n");
        ta_classe_gerada.append("        JFrame form = new "+nome_tabela+"();\n");
        ta_classe_gerada.append("        form.setVisible(true);\n");
        ta_classe_gerada.append("    }\n");
        gera_lookandfeel();
        mostra_conteudo_nos_campos();
        gera_actionPerformed();
        gerar_acao_botoes();
        novo_registro();
        grava_registro();
        altera_registro();
        excluir();
        pesquisadigitacao();
        pesquisaviacombobox();
        ta_classe_gerada.append("}\n");
        gera_arquivo();
    }
/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/
    public void gerar_botoes()
    {
        ta_classe_gerada.append("    //Gerando as Imagens dos botoes\n");
        ta_classe_gerada.append("    ImageIcon img_primeiro_reg       = new ImageIcon(\"imagens/primeiro_registro.gif\");\n");
        ta_classe_gerada.append("    ImageIcon img_registro_ant       = new ImageIcon(\"imagens/registro_anterior.gif\");\n");
        ta_classe_gerada.append("    ImageIcon img_proximo_reg        = new ImageIcon(\"imagens/proximo_registro.gif\");\n");
        ta_classe_gerada.append("    ImageIcon img_ultimo_reg         = new ImageIcon(\"imagens/ultimo_registro.gif\");\n");
        ta_classe_gerada.append("    ImageIcon img_novo               = new ImageIcon(\"imagens/novo_registro.gif\");\n");
        ta_classe_gerada.append("    ImageIcon img_grava              = new ImageIcon(\"imagens/gravar_registro.gif\");\n");
        ta_classe_gerada.append("    ImageIcon img_altera             = new ImageIcon(\"imagens/alterar_registro.gif\");\n");
        ta_classe_gerada.append("    ImageIcon img_exclui             = new ImageIcon(\"imagens/delete.gif\");\n\n");

        ta_classe_gerada.append("    //Gerando os Botões\n");       
        ta_classe_gerada.append("    JButton botao_primeiro_registro  = new JButton(img_primeiro_reg);\n");
        ta_classe_gerada.append("    JButton botao_registro_anterior  = new JButton(img_registro_ant);\n");
        ta_classe_gerada.append("    JButton botao_proximo_registro   = new JButton(img_proximo_reg);\n");
        ta_classe_gerada.append("    JButton botao_ultimo_registro    = new JButton(img_ultimo_reg);\n");
        ta_classe_gerada.append("    JButton botao_novo               = new JButton(img_novo);\n");
        ta_classe_gerada.append("    JButton botao_gravar             = new JButton(img_grava);\n");
        ta_classe_gerada.append("    JButton botao_alterar            = new JButton(img_altera);\n");
        ta_classe_gerada.append("    JButton botao_excluir            = new JButton(img_exclui);\n");
        
    }
    public void gerar_acao_botoes()
    {
        ta_classe_gerada.append("    //acao proximo registro\n");
        ta_classe_gerada.append("    public void vai_proximo_registro()\n");
        ta_classe_gerada.append("    {\n");
        ta_classe_gerada.append("        try\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("             navega=2;\n");
        ta_classe_gerada.append("             resultset.next();\n");
        ta_classe_gerada.append("             mostra_conteudo_nos_campos();\n");
        ta_classe_gerada.append("        }\n");
        ta_classe_gerada.append("        catch(SQLException erro_sql)\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("            JOptionPane.showMessageDialog(null,\"Não mostrar os dados \"+erro_sql);\n");
        ta_classe_gerada.append("        }\n");
        ta_classe_gerada.append("    }\n");
        ta_classe_gerada.append("\n    //acao registro anterior\n");
        ta_classe_gerada.append("    public void vai_registro_anterior()\n");
        ta_classe_gerada.append("    {\n");
        ta_classe_gerada.append("        try\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("             navega=1;\n");
        ta_classe_gerada.append("             resultset.previous();\n");
        ta_classe_gerada.append("             mostra_conteudo_nos_campos();\n");
        ta_classe_gerada.append("        }\n");
        ta_classe_gerada.append("        catch(SQLException erro_sql)\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("            JOptionPane.showMessageDialog(null,\"Não mostrar os dados \"+erro_sql);\n");
        ta_classe_gerada.append("        }\n");
        ta_classe_gerada.append("    }\n");
        ta_classe_gerada.append("\n    //acao ultimo registro\n");
        ta_classe_gerada.append("    public void vai_ultimo_registro()\n");
        ta_classe_gerada.append("    {\n");
        ta_classe_gerada.append("        try\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("             resultset.last();\n");
        ta_classe_gerada.append("             mostra_conteudo_nos_campos();\n");
        ta_classe_gerada.append("        }\n");
        ta_classe_gerada.append("        catch(SQLException erro_sql)\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("            JOptionPane.showMessageDialog(null,\"Não mostrar os dados \"+erro_sql);\n");
        ta_classe_gerada.append("        }\n");
        ta_classe_gerada.append("    }\n");
        ta_classe_gerada.append("\n    //acao proximo registro\n");
        ta_classe_gerada.append("    public void vai_primeiro_registro()\n");
        ta_classe_gerada.append("    {\n");
        ta_classe_gerada.append("        try\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("             resultset.first();\n");
        ta_classe_gerada.append("             mostra_conteudo_nos_campos();\n");
        ta_classe_gerada.append("        }\n");
        ta_classe_gerada.append("        catch(SQLException erro_sql)\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("            JOptionPane.showMessageDialog(null,\"Não mostrar os dados \"+erro_sql);\n");
        ta_classe_gerada.append("        }\n");
        ta_classe_gerada.append("    }\n");


    }
    public void posiciona_botoes_e_settooltiptext()
    {
       pos_linha=pos_linha+30; pos_coluna=100;
       ta_classe_gerada.append("\n        //Posicionando os botoes com setBounds()\n");       
       ta_classe_gerada.append("        botao_primeiro_registro  .setBounds("+100+","+pos_linha+",40,30);\n");
       ta_classe_gerada.append("        botao_registro_anterior  .setBounds("+150+","+pos_linha+",40,30);\n");
       ta_classe_gerada.append("        botao_proximo_registro   .setBounds("+200+","+pos_linha+",40,30);\n");
       ta_classe_gerada.append("        botao_ultimo_registro    .setBounds("+250+","+pos_linha+",40,30);\n");
       ta_classe_gerada.append("        botao_novo               .setBounds("+300+","+pos_linha+",40,30);\n");
       ta_classe_gerada.append("        botao_gravar             .setBounds("+350+","+pos_linha+",40,30);\n");
       ta_classe_gerada.append("        botao_alterar            .setBounds("+400+","+pos_linha+",40,30);\n");
       ta_classe_gerada.append("        botao_excluir            .setBounds("+450+","+pos_linha+",40,30);\n");
       
       ta_classe_gerada.append("\n        //Mensagens dos Botoes\n");
       ta_classe_gerada.append("        botao_primeiro_registro  .setToolTipText(\"Volta para o primeiro Registro\");\n");
       ta_classe_gerada.append("        botao_registro_anterior  .setToolTipText(\"Volta para o Registro Anterior\");\n");
       ta_classe_gerada.append("        botao_proximo_registro   .setToolTipText(\"Avança para o próximo Registro\");\n");
       ta_classe_gerada.append("        botao_ultimo_registro    .setToolTipText(\"Avança para o último Registro\");\n");
       ta_classe_gerada.append("        botao_novo               .setToolTipText(\"Insere um novo registro\");\n");
       ta_classe_gerada.append("        botao_gravar             .setToolTipText(\"Grava os dados cadastrados\");\n");
       ta_classe_gerada.append("        botao_alterar            .setToolTipText(\"Alterar dados\");\n");
       ta_classe_gerada.append("        botao_excluir            .setToolTipText(\"Excluir o Registro corrente\");\n");
       
       ta_classe_gerada.append("\n        //adActionListener(this)\n");
       ta_classe_gerada.append("        botao_primeiro_registro  .addActionListener(this);\n");
       ta_classe_gerada.append("        botao_registro_anterior  .addActionListener(this);\n");
       ta_classe_gerada.append("        botao_proximo_registro   .addActionListener(this);\n");
       ta_classe_gerada.append("        botao_ultimo_registro    .addActionListener(this);\n");
       ta_classe_gerada.append("        botao_novo               .addActionListener(this);\n");
       ta_classe_gerada.append("        botao_gravar             .addActionListener(this);\n");
       ta_classe_gerada.append("        botao_alterar            .addActionListener(this);\n");
       ta_classe_gerada.append("        botao_excluir            .addActionListener(this);\n");
       ta_classe_gerada.append("        tf_pesquisa              .addActionListener(this);\n");
       ta_classe_gerada.append("        cb_pesquisa              .addActionListener(this);\n");

    }
/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/
    public void library_detect_class()
    {
        setLibrary();
        ta_classe_gerada.append(library);
        ta_classe_gerada.append(detect);
        ta_classe_gerada.append(class1);
        ta_classe_gerada.append(library_class);
    
    }
    public void adiciona_botoes_no_getContentPane()
    {
         ta_classe_gerada.append("\n        //adicionando os botoes no getContentPane()\n");
         ta_classe_gerada.append("        getContentPane().add(botao_primeiro_registro);\n");
         ta_classe_gerada.append("        getContentPane().add(botao_registro_anterior);\n");
         ta_classe_gerada.append("        getContentPane().add(botao_proximo_registro);\n");
         ta_classe_gerada.append("        getContentPane().add(botao_ultimo_registro);\n");
         ta_classe_gerada.append("        getContentPane().add(botao_novo);\n");
         ta_classe_gerada.append("        getContentPane().add(botao_gravar);\n");
         ta_classe_gerada.append("        getContentPane().add(botao_alterar);\n");
         ta_classe_gerada.append("        getContentPane().add(botao_excluir);\n");
         ta_classe_gerada.append("\n");
                           
    }
/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/    
    
    public void efetua_conexao()
    {
        driver_banco = tf_driver.getText();
        url     = tf_banco.getText();
        usuario = tf_usuario.getText();
        senha   = tf_senha.getText();
        try
        {
              Class.forName(driver_banco);
              con_java_rapido = DriverManager.getConnection(url,usuario,senha);
              JOptionPane.showMessageDialog(null,"Conexão feita com sucesso");
              dmd_java_rapido = con_java_rapido.getMetaData();
              rs_java_rapido  = dmd_java_rapido.getTables(null,null,null, new String [] { "TABLE"}); 
              cb_tabelas.removeAllItems();
              while(rs_java_rapido.next())
                  cb_tabelas.addItem(rs_java_rapido.getString(3));

        }
        catch(ClassNotFoundException erro_class)
        {
              JOptionPane.showMessageDialog(null,"Driver não localizado: "+erro_class);
        }
        catch(SQLException erro_sql)
        {
             JOptionPane.showMessageDialog(null,"Não conseguiu conectar ao banco "+erro_sql);
        }
    }
    public void gera_conexao()
    {
        ta_classe_gerada.append("\n");
        ta_classe_gerada.append("        driver       = \""+tf_driver.getText()+"\";\n");
        ta_classe_gerada.append("        url          = \""+tf_banco.getText()+"\";\n");
        ta_classe_gerada.append("        usuario      = \""+tf_usuario.getText()+"\";\n");
        ta_classe_gerada.append("        senha        = \""+tf_senha.getText()+"\";\n");
        ta_classe_gerada.append("        try\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("              Class.forName(driver);\n");
        ta_classe_gerada.append("              connection = DriverManager.getConnection(url,usuario,senha);\n");
        ta_classe_gerada.append("              statement  = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);\n");
        ta_classe_gerada.append("              resultset  = statement.executeQuery(\"select * from "+nome_tabela+"\");\n");
        ta_classe_gerada.append("              JOptionPane.showMessageDialog(null,\"Conexãoo feita com sucesso\");\n");
        ta_classe_gerada.append("              while(resultset.next())\n");
        ta_classe_gerada.append("                   cb_pesquisa.addItem(resultset.getString(\""+lista_seraogerados.getItem(1)+"\"));\n");
        ta_classe_gerada.append("              resultset.first();\n");
        ta_classe_gerada.append("              mostra_conteudo_nos_campos();\n");
        ta_classe_gerada.append("        }\n");
        ta_classe_gerada.append("        catch(ClassNotFoundException erro_class)\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("               JOptionPane.showMessageDialog(null,\"Driver não localizado: \"+erro_class);\n");
        ta_classe_gerada.append("        }\n");
        ta_classe_gerada.append("        catch(SQLException erro_sql)\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("             JOptionPane.showMessageDialog(null,\"Não conseguiu conectar ao banco \"+erro_sql);\n");
        ta_classe_gerada.append("        }\n");
    }
    
    public void gera_arquivo()
    {
        try
        {
             classe_gerada = ta_classe_gerada.getText();
             byte [] texto = classe_gerada.getBytes();
             nome_arquivo = nome_tabela+".java";
             diretorio    = "c:/java_rapido_neri_netbeans65/src/";
             FileOutputStream arquivo_gerado = new FileOutputStream(diretorio+nome_arquivo);
             arquivo_gerado.write(texto);
             arquivo_gerado.close();
        }
        catch(IOException erro_arquivo)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gravar esse arquivo: "+erro_arquivo);
        }
    }
        
    public void mostra_conteudo_nos_campos()
    {
        ta_classe_gerada.append("        public void mostra_conteudo_nos_campos()\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("               try\n");
        ta_classe_gerada.append("               {\n");
        for(int i=0;i<quant_campos_gerados;i++)
        {
           int qtd_caract = lista_seraogerados.getItem(i).length();
           String nome = lista_seraogerados.getItem(i);
           for(int q = 18;q>=qtd_caract;q--)
              nome = nome + " ";
            ta_classe_gerada.append("                   tf_"+nome+".setText(resultset.getString(\""+ lista_seraogerados.getItem(i) +"\"));\n");
        }
        ta_classe_gerada.append("\n");
        ta_classe_gerada.append("                       cb_pesquisa.setSelectedItem(tf_"+lista_seraogerados.getItem(1)+".getText());\n");
        ta_classe_gerada.append("               }\n");
        ta_classe_gerada.append("               catch(SQLException erro_sql)\n");
        ta_classe_gerada.append("               {\n");
        ta_classe_gerada.append("                   if (navega == 1) \n");
        ta_classe_gerada.append("                       JOptionPane.showMessageDialog(null,\"Não foi possível retornar pois você já está no primeiro registro da tabela\");\n");        
        ta_classe_gerada.append("                   else if (navega == 2) \n");
        ta_classe_gerada.append("                       JOptionPane.showMessageDialog(null,\"Não foi possível avançar pois você já está no último registro da tabela\");\n");        
        ta_classe_gerada.append("                   else \n");
        ta_classe_gerada.append("                       JOptionPane.showMessageDialog(null,\"Não mostrar os dados \"+erro_sql);\n");
        ta_classe_gerada.append("                   navega =0;\n");
        ta_classe_gerada.append("               }\n");
        ta_classe_gerada.append("         }\n");

    }

        public void grava_registro()
    {
        ta_classe_gerada.append("\n        //método para gravar no banco registro\n");
        ta_classe_gerada.append("        public void gravar()\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("             try\n");
        ta_classe_gerada.append("            {\n");
        ta_classe_gerada.append("                 String sql_insert = \"insert into "+nome_tabela+" ( \"+\n"); 
        for(int i=0;i<quant_campos_gerados;i++)
        {
           String nome = lista_seraogerados.getItem(i);
           if (i < (quant_campos_gerados-1))
              ta_classe_gerada.append("            \""+nome+", \"+\n");
           else
               ta_classe_gerada.append("            \""+nome+" \"+\n");
        }
        ta_classe_gerada.append("            \") values ('\"+\n");
        for(int i=0;i<quant_campos_gerados;i++)
        {
           String nome = lista_seraogerados.getItem(i);
           if (i < (quant_campos_gerados-1))
              ta_classe_gerada.append("            tf_"+nome+".getText() + \"','\"+\n");
           else 
               ta_classe_gerada.append("            tf_"+nome+".getText() + \"'\"+\n");
        }
        ta_classe_gerada.append("            \")\";\n");
        //ta_classe_gerada.append("            System.out.println(\"sql = \" + sql_insert);\n");
        ta_classe_gerada.append("              statement.executeUpdate(sql_insert);\n");
        ta_classe_gerada.append("              JOptionPane.showMessageDialog(null, \"Gravação realizada com sucesso!\");\n");
        ta_classe_gerada.append("              resultset  = statement.executeQuery(\"select * from "+nome_tabela+"\");\n");
        ta_classe_gerada.append("              resultset.last();\n");
        ta_classe_gerada.append("            }\n");
        ta_classe_gerada.append("            catch(SQLException erro)\n");
        ta_classe_gerada.append("            {\n");
        ta_classe_gerada.append("                 JOptionPane.showMessageDialog(null, \"Erro no novo registro = \"+erro);\n");
        ta_classe_gerada.append("            }\n");

        ta_classe_gerada.append("         }\n");

    }

    public void altera_registro()
    {
        ta_classe_gerada.append("\n        //método para gravar no banco registro\n");
        ta_classe_gerada.append("        public void alterar()\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("             try\n");
        ta_classe_gerada.append("            {\n");
        ta_classe_gerada.append("                 String sql_alterar = \"update "+nome_tabela+" set \"+\n"); 
        for(int i=1;i<quant_campos_gerados;i++)
        {
           String nome = lista_seraogerados.getItem(i);
           if (i < (quant_campos_gerados-1))
              ta_classe_gerada.append("                 \""+nome+" = '\"+ tf_"+nome+".getText()+\"',\"+\n");
           else
              ta_classe_gerada.append("                 \""+nome+" = '\"+ tf_"+nome+".getText()+\n");
        }
        ta_classe_gerada.append("                  \"' where "+lista_seraogerados.getItem(0)+" = \"+tf_"+lista_seraogerados.getItem(0)+".getText();\n");
        ta_classe_gerada.append("                  System.out.println(\"sql = \" + sql_alterar);\n");
        ta_classe_gerada.append("                  statement.executeUpdate(sql_alterar);\n");
        ta_classe_gerada.append("                  JOptionPane.showMessageDialog(null, \"Altração realizada com sucesso!\");\n");
        ta_classe_gerada.append("                  resultset  = statement.executeQuery(\"select * from "+nome_tabela+"\");\n");
        ta_classe_gerada.append("                  resultset.first();\n");
        ta_classe_gerada.append("            }\n");
        ta_classe_gerada.append("            catch(SQLException erro)\n");
        ta_classe_gerada.append("            {\n");
        ta_classe_gerada.append("                 JOptionPane.showMessageDialog(null, \"Erro no novo registro = \"+erro);\n");
        ta_classe_gerada.append("            }\n");

        ta_classe_gerada.append("         }\n");

    }
    public void excluir()
    {
       
        ta_classe_gerada.append("\n         // procedimento para exclusão de registro\n");
        ta_classe_gerada.append("     public void excluir()\n");
        ta_classe_gerada.append("     {\n");
        ta_classe_gerada.append("       try\n");
        ta_classe_gerada.append("       {\n");
        ta_classe_gerada.append("           String nome = \"Deletar "+nome_tabela+" : \"+tf_"+lista_seraogerados.getItem(1)+".getText()+" +"\" ?\";\n");
        ta_classe_gerada.append("           System.out.println(\"nome = \" + nome);\n");        
        ta_classe_gerada.append("           int opcao_escolhida = JOptionPane.showConfirmDialog(null,nome,\"Exclusão \",JOptionPane.YES_NO_OPTION);\n");
        ta_classe_gerada.append("           if (opcao_escolhida == JOptionPane.YES_OPTION)\n");
        ta_classe_gerada.append("           {\n");
        ta_classe_gerada.append("               String  sql = \"DELETE FROM "+nome_tabela+" Where "+lista_seraogerados.getItem(0)+" = \"+ tf_"+lista_seraogerados.getItem(0)+".getText();\n");
        ta_classe_gerada.append("               System.out.println(\"sql = \" + sql);\n");
        ta_classe_gerada.append("               int conseguiu_excluir = statement.executeUpdate(sql);\n");
        ta_classe_gerada.append("               if (conseguiu_excluir == 1)\n");
        ta_classe_gerada.append("               {\n");
        ta_classe_gerada.append("                  JOptionPane.showMessageDialog(null, \"Exclusão realizada com sucesso!\");\n");
        ta_classe_gerada.append("                  resultset  = statement.executeQuery(\"select * from "+nome_tabela+"\");\n");
        ta_classe_gerada.append("                  resultset.first();\n");
        ta_classe_gerada.append("                  mostra_conteudo_nos_campos();\n");
        ta_classe_gerada.append("               }\n");
        ta_classe_gerada.append("           }\n"); 
        ta_classe_gerada.append("           else\n");        
        ta_classe_gerada.append("             return;\n");   
        ta_classe_gerada.append("       }\n");        
        ta_classe_gerada.append("       catch(SQLException erro)\n");
        ta_classe_gerada.append("       {\n");
        ta_classe_gerada.append("             JOptionPane.showMessageDialog(null, \"Erro ao tentar excluir registro = \"+erro);\n");
        ta_classe_gerada.append("       }\n");                 
        ta_classe_gerada.append("   }\n");        
    }   

    
    public void novo_registro()
    {
        ta_classe_gerada.append("        public void novo_registro()\n");
        ta_classe_gerada.append("        {\n");
        ta_classe_gerada.append("             int ult_cod=0;\n");
        ta_classe_gerada.append("             try\n");
        ta_classe_gerada.append("            {\n");
        ta_classe_gerada.append("                 resultset.last();\n"); 
        ta_classe_gerada.append("                 String codigo = resultset.getString(\""+lista_seraogerados.getItem(0)+"\");\n");
        ta_classe_gerada.append("                 ult_cod = Integer.parseInt(codigo) + 1;\n");
        ta_classe_gerada.append("            }\n");
        ta_classe_gerada.append("            catch(SQLException erro)\n");
        ta_classe_gerada.append("            {\n");
        ta_classe_gerada.append("                 JOptionPane.showMessageDialog(null, \"Erro no novo registro = \"+erro);\n");
        ta_classe_gerada.append("            }\n");
        int qtd_caract1 = lista_seraogerados.getItem(0).length();
           String nome1 = lista_seraogerados.getItem(0);
           for(int q = 18;q>=qtd_caract1;q--)
              nome1 = nome1 + " ";
            ta_classe_gerada.append("            tf_"+nome1+".setText(\"\"+ult_cod);\n");

        for(int i=1;i<quant_campos_gerados;i++)
        {
           int qtd_caract = lista_seraogerados.getItem(i).length();
           String nome = lista_seraogerados.getItem(i);
           for(int q = 18;q>=qtd_caract;q--)
              nome = nome + " ";
            ta_classe_gerada.append("            tf_"+nome+".setText(\"\");\n");
        }
        ta_classe_gerada.append("            tf_"+lista_seraogerados.getItem(0)+".setEditable(false);\n");
        ta_classe_gerada.append("            tf_"+lista_seraogerados.getItem(1)+".requestFocus();\n");
        ta_classe_gerada.append("         }\n");

    }

    public void listar_todos()
    {
        lista_seraogerados.removeAll();
        lista_labels.removeAll();
        lista_labels_legenda.removeAll();
        for(int i = 0; i < quant_campos;i++) 
        {
            lista_seraogerados.add(lista_campos.getItem(i));
            lista_labels.add(lista_campos.getItem(i));
            lista_labels_legenda.add(lista_campos.getItem(i));
            
        }
        
    }
/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/
    public void listar_selecionados()
    {
        lista_seraogerados.removeAll();
        lista_labels.removeAll();
        lista_labels_legenda.removeAll();
        String selecionados [] = lista_campos.getSelectedItems();
        campos_selecionados = "";
        for(int i = 0; i < selecionados.length;i++)
        {
            lista_seraogerados.add(selecionados[i]);
            lista_labels.add(selecionados[i]);
            lista_labels_legenda.add(selecionados[i]);
        }
        
      
    }
    public void banco_escolhido()
    {
             if (cb_tipobanco.getSelectedItem().equals("Access"))
             {
                 tf_driver.setText("sun.jdbc.odbc.JdbcOdbcDriver");
                 tf_banco.setText("jdbc:odbc:banco_access_java_rapido");
             }
             else if (cb_tipobanco.getSelectedItem().equals("Oracle"))
             {
                 tf_driver.setText("oracle.jdbc.driver.OracleDriver");
                 tf_banco.setText("jdbc:oracle:thin:@127.0.0.1:1521:XE");
             }
             else if (cb_tipobanco.getSelectedItem().equals("Sql Server"))
             {
                 tf_driver.setText("net.sourceforge.jtds.jdbc.Driver");
                 tf_banco.setText("jdbc:jtds:sqlserver://localhost:1433/estoque");
             }
             else if (cb_tipobanco.getSelectedItem().equals("Firebird"))
             {
                 tf_driver.setText("org.firebirdsql.jdbc.FBDriver");
                 tf_banco.setText("jdbc:firebirdsql:localhost/3050:D:/videoaulas/java SE II/netbeans6/aula_neri/banco_dados/NERIFIREBIRD.FDB");
                 tf_usuario.setText("SYSDBA");
                 tf_senha.setText("masterkey");
             }
             else if (cb_tipobanco.getSelectedItem().equals("MySQL"))
             {
                 tf_driver.setText("org.gjt.mm.mysql.Driver");
                 tf_banco.setText("jdbc:mysql://localhost/estoque");
             }
             else if (cb_tipobanco.getSelectedItem().equals("PostgreSQL"))
             {
                 tf_driver.setText("org.postgresql.Driver");
                 tf_banco.setText("jdbc:postgresql://localhost/estoque");
             }

    }
/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/    
    public void lookandfell()
    {
        try
        {
            UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch(Exception erro_laf)
        {
            JOptionPane.showMessageDialog(null,"Não conseguiu setar o look and feel Liquid, mesmo assim\n"+
                    "o software irá funcionar normalmente.. Neri! erro = "+erro_laf);
        }
    }
    public void gera_lookandfeel()
        {
        	ta_classe_gerada.append("   public void lookandfeel()\n");
		ta_classe_gerada.append("   {\n");
                ta_classe_gerada.append("        try\n");
		ta_classe_gerada.append("        {\n");
		ta_classe_gerada.append("            UIManager.setLookAndFeel(\"com.birosoft.liquid.LiquidLookAndFeel\");\n");
		ta_classe_gerada.append("            SwingUtilities.updateComponentTreeUI(this);\n");			
		ta_classe_gerada.append("        }\n");
		ta_classe_gerada.append("        catch(Exception erro)\n");
		ta_classe_gerada.append("        {\n");
		ta_classe_gerada.append("            JOptionPane.showMessageDialog(null, \"Não conseguiu setar o novo LookAndFeel!!!\");\n");			
		ta_classe_gerada.append("        }\n");
                ta_classe_gerada.append("    }\n");
        }

    public void gera_labels()
    {
        ta_classe_gerada.append("    JLabel lb_titulo = new JLabel(\"Manutenção de "+nome_tabela+" - www.informaticon.com.br\");\n\n");
        ta_classe_gerada.append("    //Labels dos campos da tabela;\n");        
        quant_campos_gerados = lista_seraogerados.getItemCount();
       // JOptionPane.showMessageDialog(null, "quant_campos = "+quant_campos_gerados);
        for(int i=0;i<quant_campos_gerados;i++)
        {
           int qtd_caract = lista_seraogerados.getItem(i).length();
         //  JOptionPane.showMessageDialog(null, "qtd_caract = "+qtd_caract);
           String nome = lista_seraogerados.getItem(i);
           //JOptionPane.showMessageDialog(null, "nome = "+nome);
           for(int q = 18;q>=qtd_caract;q--)
              nome = nome + " ";
           ta_classe_gerada.append("    JLabel label_"+nome+" = new JLabel(\""+lista_labels_legenda.getItem(i)+"..: \");\n");
        }
        ta_classe_gerada.append("\n");
    }
    public void gera_cor_frente_labels()
    {
    
        ta_classe_gerada.append("\n    //Gera cor frente dos lanbels setForegroundColor()\n");
        //quant_campos_gerados = lista_seraogerados.getItemCount();
        for(int i=0;i<quant_campos_gerados;i++)
        {
           int qtd_caract = lista_seraogerados.getItem(i).length();
           String nome = lista_seraogerados.getItem(i);
           for(int q = 18;q>=qtd_caract;q--)
              nome = nome + " ";
           ta_classe_gerada.append("        label_"+nome+".setForeground(Color."+cor_frente_label+");\n");
        }
        ta_classe_gerada.append("\n");
    }
    public void gera_cores_texfield()
    {
    
        ta_classe_gerada.append("\n    //Gera cor frente dos texfields setForegroundColor()\n");
        for(int i=0;i<quant_campos_gerados;i++)
        {
           int qtd_caract = lista_seraogerados.getItem(i).length();
           String nome = lista_seraogerados.getItem(i);
           for(int q = 18;q>=qtd_caract;q--)
              nome = nome + " ";
           ta_classe_gerada.append("        tf_"+nome+".setForeground(Color."+cor_frente_textfields+");\n");
        }
        ta_classe_gerada.append("\n");
        ta_classe_gerada.append("\n    //Gera cor frente de fundo texfields setBackroundColor()\n");
        for(int i=0;i<quant_campos_gerados;i++)
        {
           int qtd_caract = lista_seraogerados.getItem(i).length();
           String nome = lista_seraogerados.getItem(i);
           for(int q = 18;q>=qtd_caract;q--)
              nome = nome + " ";
           ta_classe_gerada.append("        tf_"+nome+".setBackground(Color."+cor_fundo_textfields+");\n");
        }
        ta_classe_gerada.append("\n");

    }
/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/    
    public void gera_textfield()
    {
        ta_classe_gerada.append("    //Irá gerar todos os textfields dos campos das tabelas\n");
        for(int i=0;i<quant_campos_gerados;i++)
        {
           int qtd_caract = lista_seraogerados.getItem(i).length();
           String nome = lista_seraogerados.getItem(i);
           for(int q = 18;q>=qtd_caract;q--)
              nome = nome + " ";
            ta_classe_gerada.append("    JTextField tf_"+nome+" = new JTextField();\n");
        }
        ta_classe_gerada.append("\n");
    }

/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/
    public void posiciona_labels()
    {
        ta_classe_gerada.append("        lb_titulo.setBounds(100,20,500,30);\n");
    }

    public void adiciona_labels()
    {
        ta_classe_gerada.append("        getContentPane().add(lb_titulo);\n");
    }
    public void fonte_cor()
    {
        ta_classe_gerada.append("        lb_titulo.setFont(new Font(\"Arial\",Font.BOLD,16));\n");
        ta_classe_gerada.append("        lb_titulo.setForeground(Color."+cor_frente_textfields+");\n");
    }
    
    public void posiciona_labels_e_textfields_na_vertical()
    {
        ta_classe_gerada.append("        \n");
        ta_classe_gerada.append("        //Posicionando os componentes labels e textfields da tabela\n");
        campos_selecionados="";
        tamanho_campos();
        try
        {
             int i, comprimento_linha=0;
             for (i = 0;i<quant_campos_gerados;i++)
             {
                 int qtd_caract = lista_seraogerados.getItem(i).length();
                 String nome = lista_seraogerados.getItem(i);
                 for(int q = 15;q>=qtd_caract;q--)
                     nome = nome + " ";
                 ta_classe_gerada.append("        label_"+nome+".setBounds("+pos_coluna+","+pos_linha+",100,20);\n");
                 //comprimento_linha = rs_tabela.getMetaData().getColumnDisplaySize(i);
                 if (tamanho_linha[i+1] <= 5)
                    comprimento_linha = (tamanho_linha[i+1] * 7)+15;
                 else
                     comprimento_linha = tamanho_linha[i+1] * 7;
                 ta_classe_gerada.append("        tf_"+nome+"   .setBounds("+(pos_coluna+100)+","+pos_linha+","+comprimento_linha+",20);\n");
                 //pos_coluna = pos_coluna + 50;
                 pos_linha  = pos_linha + 30;
             }
        }
        catch(Exception erro_posicionamento)
        {
            JOptionPane.showMessageDialog(null,"Não conseguiu posicionar. Erro = "+erro_posicionamento);
        }
    }
/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/
    public void tamanho_campos()
    {
        try
        {
            tamanho_linha = new int[quant_campos_gerados+1];
            //JOptionPane.showMessageDialog(null, "quant  campos = "+quant_campos_gerados);
            for(int i = 0; i < quant_campos_gerados; i++)
                campos_selecionados = campos_selecionados + lista_seraogerados.getItem(i)+", ";
            campos_selecionados = campos_selecionados.substring(0, campos_selecionados.length()-2);
            //JOptionPane.showMessageDialog(null, "campos = "+campos_selecionados);
            String sql = "select "+campos_selecionados+" from "+nome_tabela;
            //JOptionPane.showMessageDialog(null, sql);
            rs_tabela = st_java_rapido.executeQuery(sql);
            for(int i=1;i<=quant_campos_gerados;i++)
            {
                tamanho_linha [i] = rs_tabela.getMetaData().getColumnDisplaySize(i);
                //JOptionPane.showMessageDialog(null, "i = "+i+", campo = "+rs_tabela.getMetaData().getColumnName(i)+" tamanho_Linha["+i+"] = "+tamanho_linha[i]);
            }
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Erro no método tamanho campos ="+erro);
        }
    }
    public void java_swing_api()
    {
          library_sdk();
    }    
    public void posiciona_labels_e_textfields_na_horizontal()
    {
        ta_classe_gerada.append("        \n");
        ta_classe_gerada.append("        //Posicionando os componentes labels e textfields da tabela\n");
        campos_selecionados="";
        tamanho_campos();
        try
        {
             int i,tamanho_campo=0, comprimento_linha=0;
             pos_linha=120;pos_coluna=100;
             for (i = 0;i<quant_campos_gerados;i++)
             {
                 if (((pos_coluna > 500) && (comprimento_linha > 50)) || ((pos_coluna > 150) && (comprimento_linha > 120)))
                 {
                       pos_coluna=100;
                       pos_linha  = pos_linha + 30;
                 }
                 int qtd_caract = lista_seraogerados.getItem(i).length();
                 String nome = lista_seraogerados.getItem(i);
                 for(int q = 15;q>=qtd_caract;q--)
                     nome = nome + " ";
                     
                 ta_classe_gerada.append("        label_"+nome+".setBounds("+pos_coluna+","+pos_linha+",100,20);\n");
                 //comprimento_linha = rs_tabela.getMetaData().getColumnDisplaySize(i);
                 if (tamanho_linha[i+1] <= 5)
                    comprimento_linha = (tamanho_linha[i+1] * 7)+15;
                 else
                     comprimento_linha = tamanho_linha[i+1] * 7;
                 pos_coluna = pos_coluna + 100;
                 ta_classe_gerada.append("        tf_"+nome+"   .setBounds("+(pos_coluna)+","+pos_linha+","+comprimento_linha+",20);\n");
                 pos_coluna = pos_coluna + comprimento_linha + 20;
                 
             }
        }
        catch(Exception erro_posicionamento)
        {
            JOptionPane.showMessageDialog(null,"Não conseguiu posicionar. Erro = "+erro_posicionamento);
        }
    }
    
/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/
    public void adiciona_ao_getContentPane()
    {
        ta_classe_gerada.append("        \n");
        ta_classe_gerada.append("        //Adicionando Labels no GetContenPane()\n");
        try
        {
            for(int i = 0; i < quant_campos_gerados; i++)
            {
               ta_classe_gerada.append("        getContentPane().add(label_"+lista_seraogerados.getItem(i)+");\n");
               ta_classe_gerada.append("        getContentPane().add(tf_"+lista_seraogerados.getItem(i)+");\n");
            }
        }
        catch(Exception erro_posicionamento)
        {
            JOptionPane.showMessageDialog(null,"Não conseguiu adicionar os labels no getContenPane. Erro = "+erro_posicionamento);
        }
        
    }
    public void posiciona_tela_demonstrativa_horizontal()
    {
         lb_codigo          .setBounds(200,100,80,20);
         tf_codigo          .setBounds(290,100,80,20);
         lb_nome            .setBounds(380,100,80,20);
         tf_nome            .setBounds(470,100,200,20);
         lb_endereco        .setBounds(200,130,80,20);
         tf_endereco        .setBounds(290,130,200,20);
         lb_numero          .setBounds(500,130,80,20);
         tf_numero          .setBounds(570,130,50,20);
         lb_bairro          .setBounds(200,160,60,20);
         tf_bairro          .setBounds(290,160,100,20);
         lb_cidade          .setBounds(400,160,70,20);
         tf_cidade          .setBounds(460,160,90,20);
         lb_uf              .setBounds(570,160,40,20);
         tf_uf              .setBounds(620,160,30,20);
         lb_fone            .setBounds(200,190,80,20);
         tf_fone            .setBounds(290,190,100,20);
         lb_data_nasc       .setBounds(400,190,130,20);
         tf_data_nasc       .setBounds(540,190,100,20);
         linha_botoes = 220;
         posiciona_botoes_demonstracao();

    }
    public void posiciona_tela_demonstrativa_vertical()
    {
         lb_codigo          .setBounds(200,50,80,20);
         tf_codigo          .setBounds(290,50,80,20);
         lb_nome            .setBounds(200,80,80,20);
         tf_nome            .setBounds(290,80,300,20);
         lb_endereco        .setBounds(200,110,80,20);
         tf_endereco        .setBounds(290,110,200,20);
         lb_numero          .setBounds(200,140,80,20);
         tf_numero          .setBounds(290,140,50,20);
         lb_bairro          .setBounds(200,170,60,20);
         tf_bairro          .setBounds(290,170,100,20);
         lb_cidade          .setBounds(200,200,70,20);
         tf_cidade          .setBounds(290,200,90,20);
         lb_uf              .setBounds(200,230,40,20);
         tf_uf              .setBounds(290,230,30,20);
         lb_fone            .setBounds(200,260,80,20);
         tf_fone            .setBounds(290,260,100,20);
         lb_data_nasc       .setBounds(200,290,130,20);
         tf_data_nasc       .setBounds(340,290,100,20);
         linha_botoes = 320;
         posiciona_botoes_demonstracao();

    }
/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/
    
    public void adiciona_demonstracao_panelajustes()
    {
         panel_ajustes.add(lb_codigo);
         panel_ajustes.add(tf_codigo);
         panel_ajustes.add(lb_nome);          
         panel_ajustes.add(tf_nome);        
         panel_ajustes.add(lb_endereco);      
         panel_ajustes.add(tf_endereco);    
         panel_ajustes.add(lb_numero);  
         panel_ajustes.add(tf_numero);
         panel_ajustes.add(lb_bairro);
         panel_ajustes.add(tf_bairro);
         panel_ajustes.add(lb_cidade);
         panel_ajustes.add(tf_cidade);          
         panel_ajustes.add(lb_uf);              
         panel_ajustes.add(tf_uf);              
         panel_ajustes.add(lb_fone);            
         panel_ajustes.add(tf_fone);            
         panel_ajustes.add(lb_data_nasc);       
         panel_ajustes.add(tf_data_nasc);       

    }
    public void cor_frente_textfields_demonstrativos()
    {
        if (cor_frente_textfields.equals("red"))
        {
            tf_codigo        .setForeground(Color.red);
            tf_nome          .setForeground(Color.red);
            tf_endereco      .setForeground(Color.red);
            tf_numero        .setForeground(Color.red);
            tf_bairro        .setForeground(Color.red);
            tf_cidade        .setForeground(Color.red);
            tf_uf            .setForeground(Color.red);
            tf_fone          .setForeground(Color.red);
            tf_data_nasc     .setForeground(Color.red);
        }
        else if (cor_frente_textfields.equals("blue"))
        {
            tf_codigo        .setForeground(Color.blue);
            tf_nome          .setForeground(Color.blue);
            tf_endereco      .setForeground(Color.blue);
            tf_numero        .setForeground(Color.blue);
            tf_bairro        .setForeground(Color.blue);
            tf_cidade        .setForeground(Color.blue);
            tf_uf            .setForeground(Color.blue);
            tf_fone          .setForeground(Color.blue);
            tf_data_nasc     .setForeground(Color.blue);
        }
        else if (cor_frente_textfields.equals("yellow"))
        {
            tf_codigo        .setForeground(Color.yellow);
            tf_nome          .setForeground(Color.yellow);
            tf_endereco      .setForeground(Color.yellow);
            tf_numero        .setForeground(Color.yellow);
            tf_bairro        .setForeground(Color.yellow);
            tf_cidade        .setForeground(Color.yellow);
            tf_uf            .setForeground(Color.yellow);
            tf_fone          .setForeground(Color.yellow);
            tf_data_nasc     .setForeground(Color.yellow);
        }
        else if (cor_frente_textfields.equals("white"))
        {
            tf_codigo        .setForeground(Color.white);
            tf_nome          .setForeground(Color.white);
            tf_endereco      .setForeground(Color.white);
            tf_numero        .setForeground(Color.white);
            tf_bairro        .setForeground(Color.white);
            tf_cidade        .setForeground(Color.white);
            tf_uf            .setForeground(Color.white);
            tf_fone          .setForeground(Color.white);
            tf_data_nasc     .setForeground(Color.white);
        }
        else if (cor_frente_textfields.equals("black"))
        {
            tf_codigo        .setForeground(Color.black);
            tf_nome          .setForeground(Color.black);
            tf_endereco      .setForeground(Color.black);
            tf_numero        .setForeground(Color.black);
            tf_bairro        .setForeground(Color.black);
            tf_cidade        .setForeground(Color.black);
            tf_uf            .setForeground(Color.black);
            tf_fone          .setForeground(Color.black);
            tf_data_nasc     .setForeground(Color.black);
        }
                else if (cor_frente_textfields.equals("green"))
        {
            tf_codigo        .setForeground(Color.green);
            tf_nome          .setForeground(Color.green);
            tf_endereco      .setForeground(Color.green);
            tf_numero        .setForeground(Color.green);
            tf_bairro        .setForeground(Color.green);
            tf_cidade        .setForeground(Color.green);
            tf_uf            .setForeground(Color.green);
            tf_fone          .setForeground(Color.green);
            tf_data_nasc     .setForeground(Color.green);
        }
    }
    public void cor_fundo_textfields_demonstrativos()
    {
        if (cor_fundo_textfields.equals("red"))
        {
            tf_codigo        .setBackground(Color.red);
            tf_nome          .setBackground(Color.red);
            tf_endereco      .setBackground(Color.red);
            tf_numero        .setBackground(Color.red);
            tf_bairro        .setBackground(Color.red);
            tf_cidade        .setBackground(Color.red);
            tf_uf            .setBackground(Color.red);
            tf_fone          .setBackground(Color.red);
            tf_data_nasc     .setBackground(Color.red);
        }
        else if (cor_fundo_textfields.equals("green"))
        {
            tf_codigo        .setBackground(Color.green);
            tf_nome          .setBackground(Color.green);
            tf_endereco      .setBackground(Color.green);
            tf_numero        .setBackground(Color.green);
            tf_bairro        .setBackground(Color.green);
            tf_cidade        .setBackground(Color.green);
            tf_uf            .setBackground(Color.green);
            tf_fone          .setBackground(Color.green);
            tf_data_nasc     .setBackground(Color.green);
        }
        else if (cor_fundo_textfields.equals("yellow"))
        {
            tf_codigo        .setBackground(Color.yellow);
            tf_nome          .setBackground(Color.yellow);
            tf_endereco      .setBackground(Color.yellow);
            tf_numero        .setBackground(Color.yellow);
            tf_bairro        .setBackground(Color.yellow);
            tf_cidade        .setBackground(Color.yellow);
            tf_uf            .setBackground(Color.yellow);
            tf_fone          .setBackground(Color.yellow);
            tf_data_nasc     .setBackground(Color.yellow);
        }
        else if (cor_fundo_textfields.equals("black"))
        {
            tf_codigo        .setBackground(Color.black);
            tf_nome          .setBackground(Color.black);
            tf_endereco      .setBackground(Color.black);
            tf_numero        .setBackground(Color.black);
            tf_bairro        .setBackground(Color.black);
            tf_cidade        .setBackground(Color.black);
            tf_uf            .setBackground(Color.black);
            tf_fone          .setBackground(Color.black);
            tf_data_nasc     .setBackground(Color.black);
        }
        else if (cor_fundo_textfields.equals("white"))
        {
            tf_codigo        .setBackground(Color.white);
            tf_nome          .setBackground(Color.white);
            tf_endereco      .setBackground(Color.white);
            tf_numero        .setBackground(Color.white);
            tf_bairro        .setBackground(Color.white);
            tf_cidade        .setBackground(Color.white);
            tf_uf            .setBackground(Color.white);
            tf_fone          .setBackground(Color.white);
            tf_data_nasc     .setBackground(Color.white);
        }
        else if (cor_fundo_textfields.equals("blue"))
        {
            tf_codigo        .setBackground(Color.blue);
            tf_nome          .setBackground(Color.blue);
            tf_endereco      .setBackground(Color.blue);
            tf_numero        .setBackground(Color.blue);
            tf_bairro        .setBackground(Color.blue);
            tf_cidade        .setBackground(Color.blue);
            tf_uf            .setBackground(Color.blue);
            tf_fone          .setBackground(Color.blue);
            tf_data_nasc     .setBackground(Color.blue);
        }
    }

    public void cor_frente_labels_demonstrativos()
    {
        if (cor_frente_label.equals("red"))
        {
            lb_codigo        .setForeground(Color.red);
            lb_nome          .setForeground(Color.red);
            lb_endereco      .setForeground(Color.red);
            lb_numero        .setForeground(Color.red);
            lb_bairro        .setForeground(Color.red);
            lb_cidade        .setForeground(Color.red);
            lb_uf            .setForeground(Color.red);
            lb_fone          .setForeground(Color.red);
            lb_data_nasc     .setForeground(Color.red);
        } 
        else  if (cor_frente_label.equals("blue"))
        {
            lb_codigo        .setForeground(Color.blue);
            lb_nome          .setForeground(Color.blue);
            lb_endereco      .setForeground(Color.blue);
            lb_numero        .setForeground(Color.blue);
            lb_bairro        .setForeground(Color.blue);
            lb_cidade        .setForeground(Color.blue);
            lb_uf            .setForeground(Color.blue);
            lb_fone          .setForeground(Color.blue);
            lb_data_nasc     .setForeground(Color.blue);
        }
        else  if (cor_frente_label.equals("yellow"))
        {
            lb_codigo        .setForeground(Color.yellow);
            lb_nome          .setForeground(Color.yellow);
            lb_endereco      .setForeground(Color.yellow);
            lb_numero        .setForeground(Color.yellow);
            lb_bairro        .setForeground(Color.yellow);
            lb_cidade        .setForeground(Color.yellow);
            lb_uf            .setForeground(Color.yellow);
            lb_fone          .setForeground(Color.yellow);
            lb_data_nasc     .setForeground(Color.yellow);
        }
        else  if (cor_frente_label.equals("black"))
        {
            lb_codigo        .setForeground(Color.black);
            lb_nome          .setForeground(Color.black);
            lb_endereco      .setForeground(Color.black);
            lb_numero        .setForeground(Color.black);
            lb_bairro        .setForeground(Color.black);
            lb_cidade        .setForeground(Color.black);
            lb_uf            .setForeground(Color.black);
            lb_fone          .setForeground(Color.black);
            lb_data_nasc     .setForeground(Color.black);
        }
        else  if (cor_frente_label.equals("white"))
        {
            lb_codigo        .setForeground(Color.white);
            lb_nome          .setForeground(Color.white);
            lb_endereco      .setForeground(Color.white);
            lb_numero        .setForeground(Color.white);
            lb_bairro        .setForeground(Color.white);
            lb_cidade        .setForeground(Color.white);
            lb_uf            .setForeground(Color.white);
            lb_fone          .setForeground(Color.white);
            lb_data_nasc     .setForeground(Color.white);
        }
        else  if (cor_frente_label.equals("green"))
        {
            lb_codigo        .setForeground(Color.green);
            lb_nome          .setForeground(Color.green);
            lb_endereco      .setForeground(Color.green);
            lb_numero        .setForeground(Color.green);
            lb_bairro        .setForeground(Color.green);
            lb_cidade        .setForeground(Color.green);
            lb_uf            .setForeground(Color.green);
            lb_fone          .setForeground(Color.green);
            lb_data_nasc     .setForeground(Color.green);
        }
             
    }
    public void gera_cor_botoes()
    {
               ta_classe_gerada.append("        botao_primeiro_registro  .setBackground(Color."+cor_botoes+");\n");
               ta_classe_gerada.append("        botao_registro_anterior  .setBackground(Color."+cor_botoes+");\n");
               ta_classe_gerada.append("        botao_proximo_registro   .setBackground(Color."+cor_botoes+");\n");
               ta_classe_gerada.append("        botao_ultimo_registro    .setBackground(Color."+cor_botoes+");\n");
               ta_classe_gerada.append("        botao_novo               .setBackground(Color."+cor_botoes+");\n");
               ta_classe_gerada.append("        botao_gravar             .setBackground(Color."+cor_botoes+");\n");
               ta_classe_gerada.append("        botao_alterar            .setBackground(Color."+cor_botoes+");\n");
               ta_classe_gerada.append("        botao_excluir            .setBackground(Color."+cor_botoes+");\n");
               ta_classe_gerada.append("        panel_pesquisa.setBackground(Color."+cor_botoes+");\n");

    }
    public void Tela_de_Abertura_desse_Sistema_favor_nao_alterar()
    {
            JOptionPane.showMessageDialog(null, "Olá, esse software foi desenvolvido por Neri Aldoir Neitzke\n" +
               "\nVocê pode: usar a vontade, criar aplicações, incrementar novas funcionalidades, mas não" +
               "\né permitido que altere os dados de autoria do mesmo....!" +
               "\n\nwww.INFORMATICON.com.br - (0xx54) 3330-1222" +
               "\nCarazinho-RS, cep 99500-000" +
               "E-mail: videoaulasneri@gmail.com  ou videoaulas@informaticon.com.br\n\n" +
               "Rumo a 2.000 vídeo aulas!");
    }
    public void gera_cor_form()
    {
            if(cb_cor_form.getSelectedItem().equals("Vermelho"))
              ta_classe_gerada.append("        getContentPane().setBackground(Color.red);\n");
            else if(cb_cor_form.getSelectedItem().equals("Verde"))
                ta_classe_gerada.append("        getContentPane().setBackground(Color.green);\n");
            else if(cb_cor_form.getSelectedItem().equals("Azul"))
                ta_classe_gerada.append("        getContentPane().setBackground(Color.blue);\n");
            else if(cb_cor_form.getSelectedItem().equals("Amarelo"))
                ta_classe_gerada.append("        getContentPane().setBackground(Color.yellow);\n");
            else if(cb_cor_form.getSelectedItem().equals("Preto"))
                ta_classe_gerada.append("        getContentPane().setBackground(Color.black);\n");
            else if(cb_cor_form.getSelectedItem().equals("Branco"))
                ta_classe_gerada.append("        getContentPane().setBackground(Color.white);\n");
    }
    public void posiciona_botoes_demonstracao()
    {
       botao_primeiro_registro  .setBounds(200,linha_botoes,40,30);
       botao_registro_anterior  .setBounds(250,linha_botoes,40,30);
       botao_proximo_registro   .setBounds(300,linha_botoes,40,30);
       botao_ultimo_registro    .setBounds(350,linha_botoes,40,30);
       botao_novo               .setBounds(400,linha_botoes,40,30);
       botao_gravar             .setBounds(450,linha_botoes,40,30);
       botao_alterar            .setBounds(500,linha_botoes,40,30);
       botao_excluir            .setBounds(550,linha_botoes,40,30);
    }
    
    public void cor_botoes()
    {
        if (cor_botoes.equals("red"))
        {
               botao_primeiro_registro  .setBackground(Color.red);
               botao_registro_anterior  .setBackground(Color.red);
               botao_proximo_registro   .setBackground(Color.red);
               botao_ultimo_registro    .setBackground(Color.red);
               botao_novo               .setBackground(Color.red);
               botao_gravar             .setBackground(Color.red);
               botao_alterar            .setBackground(Color.red);
               botao_excluir            .setBackground(Color.red);
        } 
        else if (cor_botoes.equals("red"))
        {
               botao_primeiro_registro  .setBackground(Color.red);
               botao_registro_anterior  .setBackground(Color.red);
               botao_proximo_registro   .setBackground(Color.red);
               botao_ultimo_registro    .setBackground(Color.red);
               botao_novo               .setBackground(Color.red);
               botao_gravar             .setBackground(Color.red);
               botao_alterar            .setBackground(Color.red);
               botao_excluir            .setBackground(Color.red);
        }
        else if (cor_botoes.equals("red"))
        {
               botao_primeiro_registro  .setBackground(Color.red);
               botao_registro_anterior  .setBackground(Color.red);
               botao_proximo_registro   .setBackground(Color.red);
               botao_ultimo_registro    .setBackground(Color.red);
               botao_novo               .setBackground(Color.red);
               botao_gravar             .setBackground(Color.red);
               botao_alterar            .setBackground(Color.red);
               botao_excluir            .setBackground(Color.red);
        }
        else if (cor_botoes.equals("red"))
        {
               botao_primeiro_registro  .setBackground(Color.red);
               botao_registro_anterior  .setBackground(Color.red);
               botao_proximo_registro   .setBackground(Color.red);
               botao_ultimo_registro    .setBackground(Color.red);
               botao_novo               .setBackground(Color.red);
               botao_gravar             .setBackground(Color.red);
               botao_alterar            .setBackground(Color.red);
               botao_excluir            .setBackground(Color.red);
        }
        else if (cor_botoes.equals("red"))
        {
               botao_primeiro_registro  .setBackground(Color.red);
               botao_registro_anterior  .setBackground(Color.red);
               botao_proximo_registro   .setBackground(Color.red);
               botao_ultimo_registro    .setBackground(Color.red);
               botao_novo               .setBackground(Color.red);
               botao_gravar             .setBackground(Color.red);
               botao_alterar            .setBackground(Color.red);
               botao_excluir            .setBackground(Color.red);
        }
        else if (cor_botoes.equals("red"))
        {
               botao_primeiro_registro  .setBackground(Color.red);
               botao_registro_anterior  .setBackground(Color.red);
               botao_proximo_registro   .setBackground(Color.red);
               botao_ultimo_registro    .setBackground(Color.red);
               botao_novo               .setBackground(Color.red);
               botao_gravar             .setBackground(Color.red);
               botao_alterar            .setBackground(Color.red);
               botao_excluir            .setBackground(Color.red);
        }
             
    }
    public void le_configuiracoes()
    {
         try
         {
            FileInputStream arquivo = new FileInputStream ("configuracoes.txt");
            ObjectInputStream  obj_dados  = new ObjectInputStream(arquivo);
            configuracoes obj_leitura= (configuracoes)obj_dados.readObject();
            cb_tipobanco.setSelectedItem(obj_leitura.banco);
            tf_driver.setText(obj_leitura.driver);
            tf_usuario.setText(obj_leitura.usuario);
            tf_senha.setText(obj_leitura.senha);
            java_swing_api();
            if (obj_leitura.formato.equals("horizontal"))
            {
                horizontal.setSelected(true);
                posiciona_tela_demonstrativa_horizontal();
            }
            else
            {
                vertical.setSelected(true);
                posiciona_tela_demonstrativa_vertical();
            }
            cb_cor_frente_labels.setSelectedItem(obj_leitura.cor_frente_label);
            cb_cor_frente_textfields.setSelectedItem(obj_leitura.cor_frente_tf);
            cb_cor_fundo_textfields.setSelectedItem(obj_leitura.cor_fundo_tf);
            cb_cor_form.setSelectedItem(obj_leitura.cor_form);
            cb_cor_botoes.setSelectedItem(obj_leitura.cor_botoes);
            if (obj_leitura.laf)
                lookandfeel.setSelected(true);
            else
                lookandfeel.setSelected(false);

            efetua_conexao();
          }
          catch(Exception erro_grava)
          {
          }
    }
    public void pesquisaviacombobox()
    {
       ta_classe_gerada.append("public void pesquisaviacombobox()\n");
       ta_classe_gerada.append("{\n");
       ta_classe_gerada.append("   try\n");
       ta_classe_gerada.append("   {\n");
       ta_classe_gerada.append("       resultset.first();\n");
       ta_classe_gerada.append("       boolean achou = false;\n");
       ta_classe_gerada.append("       while(!achou)\n");
       ta_classe_gerada.append("       {\n");
       ta_classe_gerada.append("           String pesquisado = resultset.getString(\""+lista_seraogerados.getItem(1)+"\");\n");
       ta_classe_gerada.append("           if (pesquisado.equals(cb_pesquisa.getSelectedItem()))\n");
       ta_classe_gerada.append("           {\n");
       ta_classe_gerada.append("               achou = true;\n");
       ta_classe_gerada.append("           }\n");
       ta_classe_gerada.append("           else\n");
       ta_classe_gerada.append("                resultset.next();\n");
       ta_classe_gerada.append("        }\n");
       ta_classe_gerada.append("        // nas linhas abaixo, mostra_conteudo_tabela();\n");
       ta_classe_gerada.append("        tf_"+lista_seraogerados.getItem(0)+".setText(resultset.getString(\""+lista_seraogerados.getItem(0)+"\"));//Essas duas linhas é necesária\n");
       ta_classe_gerada.append("        mostra_conteudo_nos_campos();\n");
       ta_classe_gerada.append("        cb_pesquisa.setSelectedItem(tf_"+lista_seraogerados.getItem(1)+".getText());\n");
       ta_classe_gerada.append("    }\n");
       ta_classe_gerada.append("    catch(Exception erro)\n");
       ta_classe_gerada.append("    {\n");
       ta_classe_gerada.append("       //  JOptionPane.showMessageDialog(null, \"Não conseguiu localizar via digitação, erro = \"+erro);\n");
       ta_classe_gerada.append("    }\n"); 
       ta_classe_gerada.append("}\n");
    }

    public void pesquisadigitacao()
    {
       ta_classe_gerada.append("public void pesquisadigitacao()\n");
       ta_classe_gerada.append("{\n");
       ta_classe_gerada.append("   try\n");
       ta_classe_gerada.append("   {\n");
       ta_classe_gerada.append("       resultset.first();\n");
       ta_classe_gerada.append("       boolean achou = false;\n");
       ta_classe_gerada.append("       int tamanho_pesquisa = tf_pesquisa.getText().length();\n");
       ta_classe_gerada.append("       while(!achou)\n");
       ta_classe_gerada.append("       {\n");
       ta_classe_gerada.append("           String pesquisado = resultset.getString(\""+lista_seraogerados.getItem(1)+"\").substring(0,(tamanho_pesquisa)).toUpperCase();\n");
       ta_classe_gerada.append("           if (pesquisado.equals(tf_pesquisa.getText().toUpperCase()))\n");
       ta_classe_gerada.append("           {\n");
       ta_classe_gerada.append("               achou = true;\n");
       ta_classe_gerada.append("           }\n");
       ta_classe_gerada.append("           else\n");
       ta_classe_gerada.append("                resultset.next();\n");
       ta_classe_gerada.append("        }\n");
       ta_classe_gerada.append("        // nas linhas abaixo, mostra_conteudo_tabela();\n");
       ta_classe_gerada.append("        tf_"+lista_seraogerados.getItem(0)+".setText(resultset.getString(\""+lista_seraogerados.getItem(0)+"\"));//Essas duas linhas é necesária\n");
       ta_classe_gerada.append("        mostra_conteudo_nos_campos();\n");
       ta_classe_gerada.append("        cb_pesquisa.setSelectedItem(tf_"+lista_seraogerados.getItem(1)+".getText());\n");
       ta_classe_gerada.append("    }\n");
       ta_classe_gerada.append("    catch(Exception erro)\n");
       ta_classe_gerada.append("    {\n");
       ta_classe_gerada.append("         JOptionPane.showMessageDialog(null, \"Não conseguiu localizar via digitação, erro = \"+erro);\n");
       ta_classe_gerada.append("    }\n"); 
       ta_classe_gerada.append("}\n");
    }
    public void gera_todas_as_classes()
    {
             try
             {
                 String tabelas="";
                 int qtd_tb = cb_tabelas.getItemCount();
                 for(int i =0;i<qtd_tb;i++)
                 {
                     nome_tabela = String.valueOf(cb_tabelas.getItemAt(i));
                     tabelas = tabelas+nome_tabela+"\n";
                     st_java_rapido = con_java_rapido.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                     rs_tabela = st_java_rapido.executeQuery("select * from "+nome_tabela);
                     quant_campos = rs_tabela.getMetaData().getColumnCount();
                     pos_linha =120;
                     lista_seraogerados.removeAll();
                     lista_labels_legenda.removeAll();
                     for(int x = 1;x <= quant_campos; x++)
                     {
                         lista_seraogerados.add(String.valueOf(rs_tabela.getMetaData().getColumnName(x)));
                         lista_labels_legenda.add(String.valueOf(rs_tabela.getMetaData().getColumnName(x)));
                      
                     }
                     gera_texto_java();
                 }
                 JOptionPane.showMessageDialog(null, "Parabéns, os arquivos \n"+tabelas+"\n\n foram gerados com sucesso"+
                     "\nno local = "+diretorio);

                 
              }
             catch(SQLException erro)
             {
             }
    }
  
        
}

class configuracoes implements Serializable
{
    String driver, banco, usuario, senha, formato, cor_frente_label, cor_frente_tf;
    String cor_fundo_tf, cor_form, cor_botoes;
    boolean laf;
}


/*VÍDEO AULAS PROF NERI NEITZKE-www.informaticon.com.br-videoaulasneri@gmail.com
Ninguém está autorizado a vender essas vídeo aulas a não ser a 
INFORMATICON ou o próprio professor Neri, se outro vender, denuncie*/