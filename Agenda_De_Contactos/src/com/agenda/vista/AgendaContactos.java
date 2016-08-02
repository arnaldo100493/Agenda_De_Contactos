/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agenda.vista;

import com.agenda.control.ControlContactos;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.agenda.modelo.Contacto;

/**
 *
 * @author FABAME
 */
public class AgendaContactos extends JFrame {

    private ControlContactos cc = new ControlContactos();
    private DefaultTableModel lista = new DefaultTableModel();
    private int tecleo = 0;
    private long telefono = 0;

    /**
     * Creates new form AgendaContactos
     */
    public AgendaContactos() {
        this.initComponents();
        this.setTitle("Agenda de Contactos");
        this.setLocationRelativeTo(null);
        this.setExtendedState(3);
        this.lista.addColumn("Nombre");
        this.lista.addColumn("Telefono");
        this.lista.addColumn("Direccion");
        this.lista.addColumn("Email");
        this.cc.cargarDatos();
        this.cc.listar(this.tableListadoContactos);
        this.listar();
    }

    private boolean validarCampos() {
        if (this.textNombre.getText().isEmpty() | this.textDireccion.getText().isEmpty() | this.textTelefono.getText().isEmpty() | this.textEmail.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void estadoEncendido() {
        this.buttonModificar.setText("Guardar");
        this.menuItemModificar.setText("Guardar");
        this.buttonRegistrar.setEnabled(false);
        this.buttonEliminar.setEnabled(false);
        this.buttonBuscar.setEnabled(false);
        this.textBuscar.setEnabled(false);
        this.menuItemEliminar.setEnabled(false);
    }

    private void estadoApagado() {
        this.buttonModificar.setText("Modificar");
        this.menuItemModificar.setText("Modificar");
        this.buttonRegistrar.setEnabled(true);
        this.buttonEliminar.setEnabled(true);
        this.buttonBuscar.setEnabled(true);
        this.textBuscar.setEnabled(true);
        this.menuItemEliminar.setEnabled(true);
    }

    private void limpiarTxt() {
        this.textNombre.setText("");
        this.textDireccion.setText("");
        this.textTelefono.setText("");
        this.textEmail.setText("");
    }

    private void registrar() {
        if (validarCampos() == false) {
            Contacto c = new Contacto();
            c.setNombre(this.textNombre.getText());
            try {
                c.setTelefono(Long.parseLong(this.textTelefono.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Debes Ingresar solo Numeros");
                this.textTelefono.requestFocus();
            }
            c.setDireccion(textDireccion.getText());
            c.setEmail(textEmail.getText());
            if (cc.verificar(c.getTelefono()) == false) {
                cc.registrar(c);
                cc.escribir();
                listar();
                JOptionPane.showMessageDialog(null, "Registro Exitoso!");
                limpiarTxt();
            } else {
                JOptionPane.showMessageDialog(null, "El Contacto ya Existe");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hay Campos Vacios");
        }
    }

    private void modificar() {
        if (this.tableListadoContactos.getSelectedRow() > -1) {
            if (tecleo == 0) {
                estadoEncendido();
                int seleccion = this.tableListadoContactos.getSelectedRow();
                this.textNombre.setText(String.valueOf(this.lista.getValueAt(seleccion, 0)));
                this.textTelefono.setText(String.valueOf(this.lista.getValueAt(seleccion, 1)));
                this.textDireccion.setText(String.valueOf(this.lista.getValueAt(seleccion, 2)));
                this.textEmail.setText(String.valueOf(this.lista.getValueAt(seleccion, 3)));
                this.telefono = new Long(textTelefono.getText());
                this.tecleo++;
            } else if (tecleo == 1) {
                String nombre = textNombre.getText();
                String dir = textDireccion.getText();
                long tel = new Long(textTelefono.getText());
                String email = textEmail.getText();
                Contacto c = new Contacto(nombre, dir, tel, email);
                this.cc.modificar(this.telefono, c);
                this.cc.escribir();
                this.listar();
                this.estadoApagado();
                this.repaint();
                this.limpiarTxt();
                JOptionPane.showMessageDialog(null, "Contacto Modificado Exitosamente");
                this.tecleo = 0;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes selecciona uno de la Lista");
        }

    }

    private void eliminar() {
        if (this.tableListadoContactos.getSelectedRow() > -1) {
            int seleccion = this.tableListadoContactos.getSelectedRow();
            String nombre = (String) this.lista.getValueAt(seleccion, 0);
            long tele = new Long((String) this.lista.getValueAt(seleccion, 1));
            int op = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar a: " + nombre, "Eliminar", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                this.cc.eliminar(tele);
                this.cc.escribir();
                this.listar();
                this.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes selecciona uno de la Lista");
        }
    }

    private void listar() {
        this.lista.setRowCount(0);
        for (int i = 0; i < this.cc.listar().size(); i++) {
            Contacto c = cc.listar().get(i);
            this.lista.addRow(new String[]{c.getNombre(), c.getTelefono() + "", c.getDireccion(), c.getEmail()});
        }
    }

    private void buscar() {
        String buscar = textBuscar.getText();
        for (int i = 0; i < this.tableListadoContactos.getRowCount(); i++) {
            String bb = (String) this.tableListadoContactos.getValueAt(i, 0);
            if (bb.equalsIgnoreCase(buscar)) {
                this.tableListadoContactos.changeSelection(i, 1, false, false);
                break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuManejo = new javax.swing.JPopupMenu();
        menuItemModificar = new javax.swing.JMenuItem();
        menuItemEliminar = new javax.swing.JMenuItem();
        panelModelador = new javax.swing.JPanel();
        labelNombre = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        labelTelefono = new javax.swing.JLabel();
        textTelefono = new javax.swing.JTextField();
        textDireccion = new javax.swing.JTextField();
        labelDireccion = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        textEmail = new javax.swing.JTextField();
        buttonRegistrar = new javax.swing.JButton();
        panelListadoContactos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableListadoContactos = new javax.swing.JTable();
        panelSeccionOpciones = new javax.swing.JPanel();
        buttonModificar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        buttonBuscar = new javax.swing.JButton();
        textBuscar = new javax.swing.JTextField();

        menuItemModificar.setText("Modificar");
        menuItemModificar.setToolTipText("Modifica el Contacto Selecionado");
        menuItemModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemModificarActionPerformed(evt);
            }
        });
        popupMenuManejo.add(menuItemModificar);

        menuItemEliminar.setText("Eliminar");
        menuItemEliminar.setToolTipText("Elimina el Contacto Selecionado");
        menuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEliminarActionPerformed(evt);
            }
        });
        popupMenuManejo.add(menuItemEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agenda de Contactos");
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/Icono.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelModelador.setBackground(new java.awt.Color(0, 153, 102));
        panelModelador.setBorder(javax.swing.BorderFactory.createTitledBorder("Modelador"));

        labelNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelNombre.setText("Nombre: ");

        textNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textNombreKeyTyped(evt);
            }
        });

        labelTelefono.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelTelefono.setText("Telefono: ");

        textTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textTelefonoKeyTyped(evt);
            }
        });

        labelDireccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelDireccion.setText("Direccion: ");

        labelEmail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelEmail.setText("Email:");

        textEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textEmailKeyTyped(evt);
            }
        });

        buttonRegistrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonRegistrar.setText("Registrar");
        buttonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelModeladorLayout = new javax.swing.GroupLayout(panelModelador);
        panelModelador.setLayout(panelModeladorLayout);
        panelModeladorLayout.setHorizontalGroup(
            panelModeladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModeladorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelModeladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelModeladorLayout.createSequentialGroup()
                        .addGroup(panelModeladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTelefono)
                            .addComponent(labelNombre)
                            .addComponent(labelDireccion)
                            .addComponent(labelEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelModeladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textEmail)
                            .addComponent(textDireccion)
                            .addComponent(textTelefono)
                            .addComponent(textNombre)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModeladorLayout.createSequentialGroup()
                        .addGap(0, 198, Short.MAX_VALUE)
                        .addComponent(buttonRegistrar)))
                .addContainerGap())
        );
        panelModeladorLayout.setVerticalGroup(
            panelModeladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModeladorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelModeladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelModeladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefono)
                    .addComponent(textTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelModeladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDireccion)
                    .addComponent(textDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelModeladorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buttonRegistrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelListadoContactos.setBackground(new java.awt.Color(0, 153, 102));
        panelListadoContactos.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Contactos"));

        tableListadoContactos.setModel(lista);
        tableListadoContactos.setComponentPopupMenu(popupMenuManejo);
        tableListadoContactos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableListadoContactos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableListadoContactos);

        javax.swing.GroupLayout panelListadoContactosLayout = new javax.swing.GroupLayout(panelListadoContactos);
        panelListadoContactos.setLayout(panelListadoContactosLayout);
        panelListadoContactosLayout.setHorizontalGroup(
            panelListadoContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListadoContactosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelListadoContactosLayout.setVerticalGroup(
            panelListadoContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        panelSeccionOpciones.setBackground(new java.awt.Color(0, 102, 102));
        panelSeccionOpciones.setForeground(new java.awt.Color(0, 51, 255));

        buttonModificar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        buttonModificar.setText("Modificar");
        buttonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificarActionPerformed(evt);
            }
        });

        buttonEliminar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        buttonEliminar.setText("Eliminar");
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });

        buttonBuscar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        buttonBuscar.setText("Buscar");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        textBuscar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        textBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textBuscarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelSeccionOpcionesLayout = new javax.swing.GroupLayout(panelSeccionOpciones);
        panelSeccionOpciones.setLayout(panelSeccionOpcionesLayout);
        panelSeccionOpcionesLayout.setHorizontalGroup(
            panelSeccionOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeccionOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonEliminar)
                .addGap(39, 39, 39)
                .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelSeccionOpcionesLayout.setVerticalGroup(
            panelSeccionOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeccionOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSeccionOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSeccionOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSeccionOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textBuscar)
                        .addComponent(buttonBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSeccionOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelModelador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelListadoContactos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelModelador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelListadoContactos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelSeccionOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        registrar();
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        modificar();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void menuItemModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemModificarActionPerformed
        modificar();
    }//GEN-LAST:event_menuItemModificarActionPerformed

    private void menuItemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_menuItemEliminarActionPerformed

    private void textNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyTyped
        char c = evt.getKeyChar();
        if (!(c < '0' | c > '9')) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_textNombreKeyTyped

    private void textTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textTelefonoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && !Character.isISOControl(c)) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_textTelefonoKeyTyped

    private void textEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textEmailKeyTyped
        char c = evt.getKeyChar();
        if (Character.isSpaceChar(c)) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_textEmailKeyTyped

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void textBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarKeyPressed
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER) {
            buscar();
        }
    }//GEN-LAST:event_textBuscarKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JOptionPane.showMessageDialog(null, "Nombre: Carlos Alberto G.M\nCorreo: cgonzalezmonterrosa@gmail.com\nFacebook: www.facebook.com/carlosalbertogonzalezmonterrosa",
                "Informacion", JOptionPane.PLAIN_MESSAGE, new ImageIcon(getClass().getResource("/imagenes/yo.jpg")));
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(AgendaContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendaContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendaContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendaContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendaContactos().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBuscar;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonRegistrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDireccion;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JMenuItem menuItemEliminar;
    private javax.swing.JMenuItem menuItemModificar;
    private javax.swing.JPanel panelListadoContactos;
    private javax.swing.JPanel panelModelador;
    private javax.swing.JPanel panelSeccionOpciones;
    private javax.swing.JPopupMenu popupMenuManejo;
    private javax.swing.JTable tableListadoContactos;
    private javax.swing.JTextField textBuscar;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textTelefono;
    // End of variables declaration//GEN-END:variables
}
