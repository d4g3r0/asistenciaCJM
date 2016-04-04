package GUI;

import clases.conexion;
import clases.imprimir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class guiPrincipal extends javax.swing.JFrame {

    public guiPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        opciones.add(op1);
        opciones.add(op2);
        op1.setSelected(true);
        iniciar();
    }

    public void iniciar(){
        //limpiar tabla
        DefaultTableModel model = (DefaultTableModel)table_Registro.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        field_ingreso.setText("");
        //obter fecha y hora
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        String hoy = formatofecha.format(fecha);
        //llenar la tabla con los datos del dia 
        try {
            String sql="SELECT * FROM asistencia WHERE fecha='"+hoy+"'";
            String[] registro = new String[4];
            conexion nc = new conexion();
            Connection conex = nc.conectando();
            Statement st = conex.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registro[0]=rs.getString("carne");
                registro[1]=rs.getString("accion");
                registro[2]=String.valueOf(rs.getDate("fecha"));
                registro[3]=String.valueOf(rs.getTime("hora"));
                ((DefaultTableModel)table_Registro.getModel()).addRow(registro);
            }//fin de la busqueda
            nc.cerrarConexion();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }//fin del metodo iniciar
    public void registrar(String carne){
        int op=0;
        //obtener fecha
        Date hoy = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatHour = new SimpleDateFormat("hh:MM:ss");
        String action="";
        if(op1.isSelected()){
            action="Entrada";
        }else if(op2.isSelected()){
            action="Salida";
        }
        try {
            //conexion
            conexion nc = new conexion();
            Connection conex = nc.conectando();
            //revisar si el alumno ya esta registrado ese dia
            String sqlSearch="SELECT carne FROM asistencia where carne='"+carne+"' AND accion='"+action+"' AND fecha='"+formatDate.format(hoy)+"'";
            Statement st0 = conex.createStatement();
            ResultSet rs0 = st0.executeQuery(sqlSearch);
            System.out.println(sqlSearch);
            while(rs0.next()){
               op=1;
            }//fin de la busqueda
            if(carne.equals("")){
                op=2;
            }
            switch (op) {
                case 0:
                    //insertar registro nuevo
                    String sqlInsert = "INSERT INTO asistencia (carne,accion,fecha,hora)VALUES(?,?,?,?)";
                    PreparedStatement st = conex.prepareStatement(sqlInsert);
                    st.setString(1, carne);
                    st.setString(2, action);
                    st.setString(3, String.valueOf(formatDate.format(hoy)));
                    st.setString(4, String.valueOf(formatHour.format(hoy)));
                    st.execute();
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Ya se registro este carne");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "el campo de INGRESO no puede estar vacio");
                    break;
            }//fin de los casos
            nc.cerrarConexion();  
            iniciar();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }//fin del metodo registrar
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opciones = new javax.swing.ButtonGroup();
        panelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_Registro = new javax.swing.JTable();
        panelIngresar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        field_ingreso = new javax.swing.JTextField();
        panelOpciones = new javax.swing.JPanel();
        op1 = new javax.swing.JRadioButton();
        op2 = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelTabla.setLayout(new java.awt.GridLayout(1, 0));

        table_Registro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "Accion", "Fecha", "Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_Registro);

        panelTabla.add(jScrollPane1);

        panelIngresar.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setLabelFor(field_ingreso);
        jLabel1.setText("Ingresar:");
        panelIngresar.add(jLabel1, new java.awt.GridBagConstraints());

        field_ingreso.setPreferredSize(new java.awt.Dimension(120, 32));
        field_ingreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_ingresoActionPerformed(evt);
            }
        });
        panelIngresar.add(field_ingreso, new java.awt.GridBagConstraints());

        op1.setText("Entrada");
        op1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op1ActionPerformed(evt);
            }
        });

        op2.setText("Salida");
        op2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(op1)
                    .addComponent(op2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(op1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(op2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Programa");

        jMenuItem2.setText("Nuevo Estudiante");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Reportes");

        jMenuItem3.setText("Asistencia General");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void field_ingresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_ingresoActionPerformed
        registrar(field_ingreso.getText());
    }//GEN-LAST:event_field_ingresoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        guiNewstudent nuevo = new guiNewstudent();
        nuevo.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void op2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op2ActionPerformed
        field_ingreso.requestFocus();
    }//GEN-LAST:event_op2ActionPerformed

    private void op1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op1ActionPerformed
        field_ingreso.requestFocus();
    }//GEN-LAST:event_op1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        imprimir nuevo = new imprimir();
        try {
            nuevo.mostrar_reporte("asitenciaDia.jrxml");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(guiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(guiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(guiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(guiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new guiPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField field_ingreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton op1;
    private javax.swing.JRadioButton op2;
    private javax.swing.ButtonGroup opciones;
    private javax.swing.JPanel panelIngresar;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JTable table_Registro;
    // End of variables declaration//GEN-END:variables
}
