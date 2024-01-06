/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import ControladorUtiles.Utiles;
import controlador.TDA.listas.Exception.EmptyException;
import controladorA.AutoControlN;
import controladorA.AutoControlP;
import controladorP.PersonaControlN;
import controladorP.PersonaControlP;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.listas.tablas.ModeloTablaAuto;
import vista.listas.tablas.ModeloTablaPersona;

/**
 *
 * @author marian
 */
public class FrmVenta extends javax.swing.JFrame {

    private PersonaControlN personaControl = new PersonaControlN();
    private ModeloTablaPersona mtp = new ModeloTablaPersona();
    private PersonaControlP controlP = new PersonaControlP();
    private AutoControlN autoControl = new AutoControlN();
    private ModeloTablaAuto mta = new ModeloTablaAuto();
    private AutoControlP controlA = new AutoControlP();

    private void cargarVista() {
        int fila = tbP.getSelectedRow();
        int fila1 = tbA.getSelectedRow();
        if (fila < 0) {
//            JOptionPane.showMessageDialog(null, "Escoja un registro de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                controlP.setPersona(mtp.getPersonas().getInfo(fila));
                txtRuc.setText(controlP.getPersona().getRuc());
                txtRuc.setEnabled(false);
                txtDirrecion.setText(controlP.getPersona().getDireccion());
                txtNombre.setText(controlP.getPersona().getNombre());
                txtNumero.setText(controlP.getPersona().getNumeeroTelefono());
                
                
                controlA.setAuto(mta.getAutos().getInfo(fila));
                txtMarca.setText(controlA.getAuto().getMarca());
                txtModelo.setText(controlA.getAuto().getModelo());
                txtColor.setText(controlA.getAuto().getColor());
                Date fechaAnio = controlA.getAuto().getAnio();
                if (fechaAnio != null) {
                    int year = Year.of(fechaAnio.toInstant().atZone(ZoneId.systemDefault()).getYear()).getValue();
                    txtAnio.setText(String.valueOf(year));
                } else {
                    txtAnio.setText("");
                }
                double precio = controlA.getAuto().getPrecio();
                txtPrecio.setText(String.valueOf(precio));
            } catch (Exception ex) {
                Logger.getLogger(FrmVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Boolean verificar() {
        return (!txtRuc.getText().trim().isEmpty()
                && !txtApellido.getText().trim().isEmpty()
                && !txtNombre.getText().trim().isEmpty()
                && !txtDirrecion.getText().trim().isEmpty()
                && !txtNumero.getText().trim().isEmpty()
                && !txtMarca.getText().trim().isEmpty()
                && !txtModelo.getText().trim().isEmpty()
                && !txtColor.getText().trim().isEmpty()
                && !txtAnio.getText().trim().isEmpty()
                && !txtPrecio.getText().trim().isEmpty());
    }

//    private void cargarTabla() {
//        mtp.setPersonas(controlP.all());
//        controlP.setPersonas(controlP.all());
//        tbP.setModel(mtp);
//        tbP.updateUI();
//        mta.setAutos(controlA.all());
//        controlA.setAutos(controlA.all());
//        tbA.setModel(mta);1104821002
//        tbA.updateUI();
//    }
    private void CargarTabla() throws EmptyException{
        mtp.setPersonas(controlP.getPersonas());
        tbP.setModel(mtp);
        tbP.updateUI();
        mta.setAutos(controlA.getAutos());
        tbA.setModel(mta);
        tbA.updateUI();
    }

    private void guardarP() throws EmptyException {
        if (verificar()) {
            try {
                if (Utiles.validadorDeCedula(txtRuc.getText())) {
//                   
                }
                controlP.getPersona().setRuc(txtRuc.getText());
                controlP.getPersona().setNombre(txtNombre.getText());
                controlP.getPersona().setApellido(txtApellido.getText());
                controlP.getPersona().setNumeeroTelefono(txtNumero.getText());
                controlP.getPersona().setDireccion(txtDirrecion.getText());

                controlA.getAuto().setMarca(txtMarca.getText());
                controlA.getAuto().setModelo(txtModelo.getText());
                controlA.getAuto().setColor(txtColor.getText());
                String anioTexto = txtAnio.getText();
                try {
                    int anio = Integer.parseInt(txtAnio.getText());
                    LocalDate fecha = LocalDate.of(anio, 1, 1);
                    controlA.getAuto().setAnio(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Año no válido");
                    return;
                }
        
                try {
                    double precio = Double.parseDouble(txtPrecio.getText());
                    controlA.getAuto().setPrecio(precio);
                } catch (NumberFormatException e) {
                   JOptionPane.showMessageDialog(null, "Precio no válido");
                return;
                }

                if (controlP.persist()&& controlA.persist()) {

                    JOptionPane.showMessageDialog(null, "Datos guardados");
                    CargarTabla();
                    limpiar();
                    controlP.setPersona(null);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar, hubo un error");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Cedula no valida");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiar() throws EmptyException {
        txtRuc.setEnabled(true);
        tbP.clearSelection();
        tbA.clearSelection();
        txtApellido.setText("");
        txtNombre.setText("");
        txtNumero.setText("");
        txtRuc.setText("");
        txtDirrecion.setText("");
        txtColor.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtColor.setText("");
        txtAnio.setText("");
        txtPrecio.setText("");
        CargarTabla();
        controlP.setPersona(null);
    }

    public FrmVenta() throws EmptyException {
        initComponents();
        limpiar();
        CargarTabla();
    }

    public Integer OrdenSeleccionado() {
        String OrdenO = cbxTipo.getSelectedItem().toString();
        if ("Asendende".equals(OrdenO)) {
            return 1;
        }
        if ("Desendente".equals(OrdenO)) {
            return 0;
        }
        return null;
    }

    public Integer OrdenSeleccionado2() {
        String OrdenO = cbxTipo.getSelectedItem().toString();
        if ("Asendende".equals(OrdenO)) {
            return 0;
        }
        if ("Desendente".equals(OrdenO)) {
            return 1;
        }
        return null;
    }
    
    private void modificar() throws EmptyException {
        int fila = tbA.getSelectedRow();
        int fila2 = tbP.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoja un registro de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
               
                controlP.getPersona().setRuc(txtRuc.getText());
                controlP.getPersona().setNombre(txtNombre.getText());
                controlP.getPersona().setApellido(txtApellido.getText());
                controlP.getPersona().setNumeeroTelefono(txtNumero.getText());
                controlP.getPersona().setDireccion(txtDirrecion.getText());

                controlA.getAuto().setMarca(txtMarca.getText());
                controlA.getAuto().setModelo(txtModelo.getText());
                controlA.getAuto().setColor(txtColor.getText());
                String anioTexto = txtAnio.getText();
                try {
                    int anio = Integer.parseInt(txtAnio.getText());
                    LocalDate fecha = LocalDate.of(anio, 1, 1);
                    controlA.getAuto().setAnio(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Año no válido");
                    return;
                }
        
                try {
                    double precio = Double.parseDouble(txtPrecio.getText());
                    controlA.getAuto().setPrecio(precio);
                } catch (NumberFormatException e) {
                   JOptionPane.showMessageDialog(null, "Precio no válido");
                return;
                }

                if (controlA.merge(controlA.getAuto(), fila)) {
                    JOptionPane.showMessageDialog(null, "Datos modificados");
                    cargarVista();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar, hubo un error");
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmVenta.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    private void ordenar() {
        // Obtiene el criterio de ordenamiento seleccionado y el tipo de orden (ascendente/descendente)
        String criterio = cbxPor.getSelectedItem().toString();
        Integer tipo = 0; // Por defecto, tipo ascendente
        String tipoOrden = cbxTipo.getSelectedItem().toString();
        // Verifica el tipo de orden seleccionado
        if (tipoOrden.equals("Descendente")) {
            tipo = 1; // Cambia el tipo a descendente
        }
        try {
            // Llama al método de ordenamiento y actualiza la tabla de pasajeros con los datos ordenados
            mtp.setPersonas(controlP.MetodoQuickSort(controlP.getPersonas(), tipo, criterio));
            tbP.setModel(mtp);
            tbP.updateUI();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDirrecion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        txtAnio = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbP = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbA = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        cbxTipo = new javax.swing.JComboBox<>();
        brnOrdenar = new javax.swing.JButton();
        cbxPor = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("MathJax_Main", 2, 24)); // NOI18N
        jLabel1.setText("Registro Vendedor y Auto Vendido");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Ruc :");

        jLabel3.setText("Nombre :");

        jLabel4.setText("Apellido :");

        jLabel5.setText("Dirrecion :");

        jLabel6.setText("Numero Telefonico");

        jLabel7.setText("Vendedor");

        jLabel8.setText("Auto ");

        jLabel9.setText("Marca .");

        jLabel10.setText("Modelo :");

        jLabel11.setText("Color :");

        jLabel12.setText("Año :");

        jLabel13.setText("Precio :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtNumero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtRuc, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                        .addComponent(txtColor, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMarca, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel10)
                                        .addComponent(txtDirrecion)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel3)
                                        .addComponent(txtNombre)
                                        .addComponent(txtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel12))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDirrecion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jButton2.setText("Modificar");

        jButton3.setText("Eliminar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(71, 71, 71)
                .addComponent(jButton3)
                .addGap(79, 79, 79))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnGuardar)
                    .addComponent(jButton3))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tbP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbP);

        tbA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbA);

        jPanel4.setBackground(new java.awt.Color(153, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente ", "Desendente" }));

        brnOrdenar.setText("Odenar");
        brnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnOrdenarActionPerformed(evt);
            }
        });

        cbxPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ruc", "nombres", "apellidos" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(cbxPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(brnOrdenar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brnOrdenar))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try {
            guardarP();
            cargarVista();
        } catch (EmptyException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void brnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnOrdenarActionPerformed
        // TODO add your handling code here:
       ordenar();
    }//GEN-LAST:event_brnOrdenarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmVenta().setVisible(true);
                } catch (EmptyException ex) {
                    Logger.getLogger(FrmVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brnOrdenar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxPor;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbA;
    private javax.swing.JTable tbP;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtDirrecion;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRuc;
    // End of variables declaration//GEN-END:variables
}
