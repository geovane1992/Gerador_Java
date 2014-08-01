/*
  Programa Desenvolvido por Neri Aldoir Neitzke - 0xx54 3330 1222
  www.informaticon.com.br   - videoaulasneri@gmail.com ou videoaulas@informaticon.com.br
*/
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
class agenda extends JFrame implements ActionListener
{
     int navega = 0;
     ResultSet resultset;
     Statement statement;
     Connection connection;
     String driver, url, usuario, senha;
    JLabel lb_titulo = new JLabel("Manutenção de agenda - www.informaticon.com.br");

    //Labels dos campos da tabela;
    JLabel label_Nome                = new JLabel("Nome..: ");
    JLabel label_email               = new JLabel("email..: ");

    //Gerando as Imagens dos botoes
    ImageIcon img_primeiro_reg       = new ImageIcon("imagens/primeiro_registro.gif");
    ImageIcon img_registro_ant       = new ImageIcon("imagens/registro_anterior.gif");
    ImageIcon img_proximo_reg        = new ImageIcon("imagens/proximo_registro.gif");
    ImageIcon img_ultimo_reg         = new ImageIcon("imagens/ultimo_registro.gif");
    ImageIcon img_novo               = new ImageIcon("imagens/novo_registro.gif");
    ImageIcon img_grava              = new ImageIcon("imagens/gravar_registro.gif");
    ImageIcon img_altera             = new ImageIcon("imagens/alterar_registro.gif");
    ImageIcon img_exclui             = new ImageIcon("imagens/delete.gif");

    //Gerando os Botões
    JButton botao_primeiro_registro  = new JButton(img_primeiro_reg);
    JButton botao_registro_anterior  = new JButton(img_registro_ant);
    JButton botao_proximo_registro   = new JButton(img_proximo_reg);
    JButton botao_ultimo_registro    = new JButton(img_ultimo_reg);
    JButton botao_novo               = new JButton(img_novo);
    JButton botao_gravar             = new JButton(img_grava);
    JButton botao_alterar            = new JButton(img_altera);
    JButton botao_excluir            = new JButton(img_exclui);
    //Irá gerar todos os textfields dos campos das tabelas
    JTextField tf_Nome                = new JTextField();
    JTextField tf_email               = new JTextField();

    JPanel panel_pesquisa = new JPanel();
    JLabel lb_pesquisa = new JLabel("Pesquisar.: ");
    JTextField tf_pesquisa = new JTextField();
    JComboBox cb_pesquisa = new JComboBox();
    agenda()
    {
        setTitle("Formulário de manutenção de agenda - www.informaticon.com.br - Prof Neri");
        setSize(800,600);
        setLocation(150,80);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        getContentPane().setLayout(null);
        panel_pesquisa.setLayout(null);
        panel_pesquisa.setBounds(100,50,600,50);
        lb_pesquisa.setBounds(10,20,100,20);
        tf_pesquisa.setBounds(90,20,200,20);
        cb_pesquisa.setBounds(330,20,200,20);
        lb_titulo.setBounds(100,20,500,30);
        lb_titulo.setFont(new Font("Arial",Font.BOLD,16));
        lb_titulo.setForeground(Color.blue);

    //Gera cor frente dos lanbels setForegroundColor()
        label_Nome               .setForeground(Color.black);
        label_email              .setForeground(Color.black);


    //Gera cor frente dos texfields setForegroundColor()
        tf_Nome               .setForeground(Color.blue);
        tf_email              .setForeground(Color.blue);


    //Gera cor frente de fundo texfields setBackroundColor()
        tf_Nome               .setBackground(Color.white);
        tf_email              .setBackground(Color.white);

        botao_primeiro_registro  .setBackground(Color.white);
        botao_registro_anterior  .setBackground(Color.white);
        botao_proximo_registro   .setBackground(Color.white);
        botao_ultimo_registro    .setBackground(Color.white);
        botao_novo               .setBackground(Color.white);
        botao_gravar             .setBackground(Color.white);
        botao_alterar            .setBackground(Color.white);
        botao_excluir            .setBackground(Color.white);
        panel_pesquisa.setBackground(Color.white);
        
        //Posicionando os componentes labels e textfields da tabela
        label_Nome            .setBounds(100,120,100,20);
        tf_Nome               .setBounds(200,120,1785,20);
        label_email           .setBounds(100,150,100,20);
        tf_email              .setBounds(200,150,1785,20);

        //Posicionando os botoes com setBounds()
        botao_primeiro_registro  .setBounds(100,210,40,30);
        botao_registro_anterior  .setBounds(150,210,40,30);
        botao_proximo_registro   .setBounds(200,210,40,30);
        botao_ultimo_registro    .setBounds(250,210,40,30);
        botao_novo               .setBounds(300,210,40,30);
        botao_gravar             .setBounds(350,210,40,30);
        botao_alterar            .setBounds(400,210,40,30);
        botao_excluir            .setBounds(450,210,40,30);

        //Mensagens dos Botoes
        botao_primeiro_registro  .setToolTipText("Volta para o primeiro Registro");
        botao_registro_anterior  .setToolTipText("Volta para o Registro Anterior");
        botao_proximo_registro   .setToolTipText("Avança para o próximo Registro");
        botao_ultimo_registro    .setToolTipText("Avança para o último Registro");
        botao_novo               .setToolTipText("Insere um novo registro");
        botao_gravar             .setToolTipText("Grava os dados cadastrados");
        botao_alterar            .setToolTipText("Alterar dados");
        botao_excluir            .setToolTipText("Excluir o Registro corrente");

        //adActionListener(this)
        botao_primeiro_registro  .addActionListener(this);
        botao_registro_anterior  .addActionListener(this);
        botao_proximo_registro   .addActionListener(this);
        botao_ultimo_registro    .addActionListener(this);
        botao_novo               .addActionListener(this);
        botao_gravar             .addActionListener(this);
        botao_alterar            .addActionListener(this);
        botao_excluir            .addActionListener(this);
        tf_pesquisa              .addActionListener(this);
        cb_pesquisa              .addActionListener(this);
        panel_pesquisa.add(lb_pesquisa);
        panel_pesquisa.add(tf_pesquisa);
        panel_pesquisa.add(cb_pesquisa);
        getContentPane().add(panel_pesquisa);
        getContentPane().add(lb_titulo);
        
        //Adicionando Labels no GetContenPane()
        getContentPane().add(label_Nome);
        getContentPane().add(tf_Nome);
        getContentPane().add(label_email);
        getContentPane().add(tf_email);

        //adicionando os botoes no getContentPane()
        getContentPane().add(botao_primeiro_registro);
        getContentPane().add(botao_registro_anterior);
        getContentPane().add(botao_proximo_registro);
        getContentPane().add(botao_ultimo_registro);
        getContentPane().add(botao_novo);
        getContentPane().add(botao_gravar);
        getContentPane().add(botao_alterar);
        getContentPane().add(botao_excluir);

       //lookandfeel();

        driver       = "org.gjt.mm.mysql.Driver";
        url          = "jdbc:mysql://localhost/sysimob";
        usuario      = "root";
        senha        = "123456";
        try
        {
              Class.forName(driver);
              connection = DriverManager.getConnection(url,usuario,senha);
              statement  = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
              resultset  = statement.executeQuery("select * from agenda");
              JOptionPane.showMessageDialog(null,"Conexãoo feita com sucesso");
              while(resultset.next())
                   cb_pesquisa.addItem(resultset.getString("email"));
              resultset.first();
              mostra_conteudo_nos_campos();
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
    public static void main(String args[])
    {
        JFrame form = new agenda();
        form.setVisible(true);
    }
   public void lookandfeel()
   {
        try
        {
            UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não conseguiu setar o novo LookAndFeel!!!");
        }
    }
        public void mostra_conteudo_nos_campos()
        {
               try
               {
                   tf_Nome               .setText(resultset.getString("Nome"));
                   tf_email              .setText(resultset.getString("email"));

                       cb_pesquisa.setSelectedItem(tf_email.getText());
               }
               catch(SQLException erro_sql)
               {
                   if (navega == 1) 
                       JOptionPane.showMessageDialog(null,"Não foi possível retornar pois você já está no primeiro registro da tabela");
                   else if (navega == 2) 
                       JOptionPane.showMessageDialog(null,"Não foi possível avançar pois você já está no último registro da tabela");
                   else 
                       JOptionPane.showMessageDialog(null,"Não mostrar os dados "+erro_sql);
                   navega =0;
               }
         }

    //Acoes
      public void actionPerformed(ActionEvent acao)
      {
            if (acao.getSource() == botao_primeiro_registro)
                vai_primeiro_registro();
            else if (acao.getSource() == botao_proximo_registro)
                vai_proximo_registro();
            else if (acao.getSource() == botao_registro_anterior)
                vai_registro_anterior();
            else if (acao.getSource() == botao_ultimo_registro)
                vai_ultimo_registro();
            else if (acao.getSource() == botao_gravar)
                gravar();
            else if (acao.getSource() == botao_excluir)
                excluir();
            else if (acao.getSource() == botao_alterar)
                alterar();
            else if (acao.getSource() == botao_novo)
                novo_registro();
            else if (acao.getSource() == tf_pesquisa)
                pesquisadigitacao();
            else if (acao.getSource() == cb_pesquisa)
                pesquisaviacombobox();
      }
    //acao proximo registro
    public void vai_proximo_registro()
    {
        try
        {
             navega=2;
             resultset.next();
             mostra_conteudo_nos_campos();
        }
        catch(SQLException erro_sql)
        {
            JOptionPane.showMessageDialog(null,"Não mostrar os dados "+erro_sql);
        }
    }

    //acao registro anterior
    public void vai_registro_anterior()
    {
        try
        {
             navega=1;
             resultset.previous();
             mostra_conteudo_nos_campos();
        }
        catch(SQLException erro_sql)
        {
            JOptionPane.showMessageDialog(null,"Não mostrar os dados "+erro_sql);
        }
    }

    //acao ultimo registro
    public void vai_ultimo_registro()
    {
        try
        {
             resultset.last();
             mostra_conteudo_nos_campos();
        }
        catch(SQLException erro_sql)
        {
            JOptionPane.showMessageDialog(null,"Não mostrar os dados "+erro_sql);
        }
    }

    //acao proximo registro
    public void vai_primeiro_registro()
    {
        try
        {
             resultset.first();
             mostra_conteudo_nos_campos();
        }
        catch(SQLException erro_sql)
        {
            JOptionPane.showMessageDialog(null,"Não mostrar os dados "+erro_sql);
        }
    }
        public void novo_registro()
        {
             int ult_cod=0;
             try
            {
                 resultset.last();
                 String codigo = resultset.getString("Nome");
                 ult_cod = Integer.parseInt(codigo) + 1;
            }
            catch(SQLException erro)
            {
                 JOptionPane.showMessageDialog(null, "Erro no novo registro = "+erro);
            }
            tf_Nome               .setText(""+ult_cod);
            tf_email              .setText("");
            tf_Nome.setEditable(false);
            tf_email.requestFocus();
         }

        //método para gravar no banco registro
        public void gravar()
        {
             try
            {
                 String sql_insert = "insert into agenda ( "+
            "Nome, "+
            "email "+
            ") values ('"+
            tf_Nome.getText() + "','"+
            tf_email.getText() + "'"+
            ")";
              statement.executeUpdate(sql_insert);
              JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso!");
              resultset  = statement.executeQuery("select * from agenda");
              resultset.last();
            }
            catch(SQLException erro)
            {
                 JOptionPane.showMessageDialog(null, "Erro no novo registro = "+erro);
            }
         }

        //método para gravar no banco registro
        public void alterar()
        {
             try
            {
                 String sql_alterar = "update agenda set "+
                 "email = '"+ tf_email.getText()+
                  "' where Nome = "+tf_Nome.getText();
                  System.out.println("sql = " + sql_alterar);
                  statement.executeUpdate(sql_alterar);
                  JOptionPane.showMessageDialog(null, "Altração realizada com sucesso!");
                  resultset  = statement.executeQuery("select * from agenda");
                  resultset.first();
            }
            catch(SQLException erro)
            {
                 JOptionPane.showMessageDialog(null, "Erro no novo registro = "+erro);
            }
         }

         // procedimento para exclusão de registro
     public void excluir()
     {
       try
       {
           String nome = "Deletar agenda : "+tf_email.getText()+" ?";
           System.out.println("nome = " + nome);
           int opcao_escolhida = JOptionPane.showConfirmDialog(null,nome,"Exclusão ",JOptionPane.YES_NO_OPTION);
           if (opcao_escolhida == JOptionPane.YES_OPTION)
           {
               String  sql = "DELETE FROM agenda Where Nome = "+ tf_Nome.getText();
               System.out.println("sql = " + sql);
               int conseguiu_excluir = statement.executeUpdate(sql);
               if (conseguiu_excluir == 1)
               {
                  JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
                  resultset  = statement.executeQuery("select * from agenda");
                  resultset.first();
                  mostra_conteudo_nos_campos();
               }
           }
           else
             return;
       }
       catch(SQLException erro)
       {
             JOptionPane.showMessageDialog(null, "Erro ao tentar excluir registro = "+erro);
       }
   }
public void pesquisadigitacao()
{
   try
   {
       resultset.first();
       boolean achou = false;
       int tamanho_pesquisa = tf_pesquisa.getText().length();
       while(!achou)
       {
           String pesquisado = resultset.getString("email").substring(0,(tamanho_pesquisa)).toUpperCase();
           if (pesquisado.equals(tf_pesquisa.getText().toUpperCase()))
           {
               achou = true;
           }
           else
                resultset.next();
        }
        // nas linhas abaixo, mostra_conteudo_tabela();
        tf_Nome.setText(resultset.getString("Nome"));//Essas duas linhas é necesária
        mostra_conteudo_nos_campos();
        cb_pesquisa.setSelectedItem(tf_email.getText());
    }
    catch(Exception erro)
    {
         JOptionPane.showMessageDialog(null, "Não conseguiu localizar via digitação, erro = "+erro);
    }
}
public void pesquisaviacombobox()
{
   try
   {
       resultset.first();
       boolean achou = false;
       while(!achou)
       {
           String pesquisado = resultset.getString("email");
           if (pesquisado.equals(cb_pesquisa.getSelectedItem()))
           {
               achou = true;
           }
           else
                resultset.next();
        }
        // nas linhas abaixo, mostra_conteudo_tabela();
        tf_Nome.setText(resultset.getString("Nome"));//Essas duas linhas é necesária
        mostra_conteudo_nos_campos();
        cb_pesquisa.setSelectedItem(tf_email.getText());
    }
    catch(Exception erro)
    {
       //  JOptionPane.showMessageDialog(null, "Não conseguiu localizar via digitação, erro = "+erro);
    }
}
}
