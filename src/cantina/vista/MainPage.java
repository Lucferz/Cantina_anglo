/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Deberia funcionar ya, no se que onda
package cantina.vista;

import cantina.conexion.JdbcDAOFactory;
import cantina.controlador.ArqueoscajaControl;
import cantina.controlador.ArticulosControl;
import cantina.controlador.CajasControl;
import cantina.controlador.CategoriasControlador;
import cantina.controlador.DetalleVentaControl;
import cantina.controlador.RolesControl;
import cantina.controlador.UsuariosControl;
import cantina.controlador.VentasControlador;
import cantina.modelo.Arqueoscaja;
import cantina.modelo.Articulos;
import cantina.modelo.Categorias;
import cantina.modelo.DetalleVenta;
import cantina.modelo.Usuarios;
import cantina.modelo.Ventas;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author danie
 */
public class MainPage extends javax.swing.JFrame {

    CategoriasControlador cc = new CategoriasControlador();
    ArticulosControl ac = new ArticulosControl();
    VentasControlador vc = new VentasControlador();
    DetalleVentaControl dvc = new DetalleVentaControl();
    UsuariosControl uc = new UsuariosControl();
    CajasControl cajac = new CajasControl();
    RolesControl rc = new RolesControl();
    ArqueoscajaControl aqcontrol = new ArqueoscajaControl();
    DefaultTableModel modelo;

    boolean estado_caja;
    //boolean estado_caja = CajaCerrada();

    int item;
    int totalPagar = 0;
    private int rolActual = 45;

    public int getRolActual() {
        return rolActual;
    }

    public void setRolActual(int rolActual) {
        this.rolActual = rolActual;
    }

    /**
     * Creates new form MainPage
     */
    public MainPage() {
    }

    public MainPage(int rolLogin) {
        initComponents();
        try {
           Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/shopping.png"));
            setIconImage(image);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en icono "+e.toString());
        }

        setLocationRelativeTo(null);
        if (rolLogin == 1) {
            //SI ES ADMINISTRADOR SE LE PREGUNTA SI QUIERE INGRESAR COMO VENDEDOR, PARA EVITAR MOSTRAR DEMASIADAS OPCIONES
            String[] options = new String[]{"Como Administrador", "Como Vendedor"};
            int respuesta = JOptionPane.showOptionDialog(null, "Con qué privilegios desea acceder?", "Elegir modo de priviegio",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);

            if (respuesta == 1) {

                modoVendedor();
            }
        }
        if (rolLogin == 2) {
            modoVendedor();
        }
        estado_caja = CajaCerrada();
        if (estado_caja) {
            cerrarCaja();
            //field
        } else {
            abrirCaja();
        }

        MostrarComboCat();
        MostrarTabArti();
        MostrarComboRol();
        MostrarTabUser();
        MostrarTabVentas();
        MostrarTabArqueo();
        fieldCod.requestFocus();
        ToolTipManager.sharedInstance().setInitialDelay(300);
    }

    private void modoVendedor() {
        //Quitar tabs que no van a ser accesibles
        tabsPanel.remove(panelConfig);
        tabsPanel.remove(panelVentas);
        //ocultando sus botones accesores del sidebar
        btnConfig.setVisible(false);
        btnVentas.setVisible(false);
        btnActualizarProductos.setEnabled(false);
        btnBorrarProducto.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked"})
    private void MostrarComboCat() {
        this.jComboBox2Categorias.setModel(cc.Obt_Cat());
    }

    private void MostrarTabArti() {
        ac.cargar_tabla_arti(jTableArticulos);
        modelo = (DefaultTableModel) jTableArticulos.getModel();
        
                        //TRATAMIENTO ESTETICO A COLUMNAS
        
        //Dejar tamaño fijo a columna ID
        jTableArticulos.getColumnModel().getColumn(0).setMaxWidth(45);
        jTableArticulos.getColumnModel().getColumn(0).setMinWidth(70);
        
            //Rango de tamaños para columna codigo
        jTableArticulos.getColumnModel().getColumn(1).setMinWidth(120);
        jTableArticulos.getColumnModel().getColumn(1).setMaxWidth(155);
        
            //Columna descripcion
        jTableArticulos.getColumnModel().getColumn(2).setMinWidth(150);
        jTableArticulos.getColumnModel().getColumn(2).setMaxWidth(235);
        
            //Columna stock
        jTableArticulos.getColumnModel().getColumn(3).setMinWidth(55);
        jTableArticulos.getColumnModel().getColumn(3).setMaxWidth(85);
        
            //Columna  Precio Costo
        jTableArticulos.getColumnModel().getColumn(4).setMinWidth(70);
        jTableArticulos.getColumnModel().getColumn(4).setMaxWidth(130);

            //Columna  Precio Venta
        jTableArticulos.getColumnModel().getColumn(5).setMinWidth(70);
        jTableArticulos.getColumnModel().getColumn(5).setMaxWidth(130);

            //Columna  Precio Categoría
        jTableArticulos.getColumnModel().getColumn(6).setMinWidth(70);
        jTableArticulos.getColumnModel().getColumn(6).setMaxWidth(120);

        
        
        
    }

    private void MostrarTabArqueo() {//Cambiar el JTable por el de Arqueo
        //MUESTRA LA TABLA DE HISTORIAL DE ARQUEOS
        aqcontrol.cargarTabArqueo(tableHistArq);
        modelo = (DefaultTableModel) tableHistArq.getModel();
        
         //TRATAMIENTO ESTETICO A COLUMNAS
        
        //Rango de tamaños para columna ID
        tableHistArq.getColumnModel().getColumn(0).setMaxWidth(45);
        tableHistArq.getColumnModel().getColumn(0).setMinWidth(55);
        
            //Rango de tamaños para columna USUARIO
        tableHistArq.getColumnModel().getColumn(1).setMinWidth(60);
        tableHistArq.getColumnModel().getColumn(1).setMaxWidth(80);
        
            //Columna FECHA APERTURA
        tableHistArq.getColumnModel().getColumn(2).setMinWidth(140);
        tableHistArq.getColumnModel().getColumn(2).setMaxWidth(200);
        
            //Columna MONTO INICIAL
        tableHistArq.getColumnModel().getColumn(3).setMinWidth(80);
        tableHistArq.getColumnModel().getColumn(3).setMaxWidth(100);

        
        //Columna  FECHA CIERRE
        tableHistArq.getColumnModel().getColumn(4).setMinWidth(140);
        tableHistArq.getColumnModel().getColumn(4).setMaxWidth(200);  
        
        //Columna  MONTO FINAL  
        tableHistArq.getColumnModel().getColumn(5).setMinWidth(80);
        tableHistArq.getColumnModel().getColumn(5).setMaxWidth(100);  
        
        //Columna  TOTAL DE VENTAS
        tableHistArq.getColumnModel().getColumn(6).setMinWidth(100);
        tableHistArq.getColumnModel().getColumn(6).setMaxWidth(120);  
        
        //Columna  CONFIRMADO
        tableHistArq.getColumnModel().getColumn(7).setMinWidth(100);
        tableHistArq.getColumnModel().getColumn(7).setMaxWidth(120);
        
        //Columna ADMIN
        tableHistArq.getColumnModel().getColumn(8).setMinWidth(60);
        tableHistArq.getColumnModel().getColumn(8).setMaxWidth(80);
        
    }

    private void MostrarComboRol() {
        this.jComboBoxRolUsers.setModel(rc.Obt_Rol());//Crear el obtROl()
    }

    private void MostrarTabUser() {
        uc.cargar_tabla_user(jTableUsers);
        modelo = (DefaultTableModel) jTableUsers.getModel();
         
                //TRATAMIENTO ESTETICO A COLUMNAS
        
        //Rango de tamaños para columna ID
        jTableUsers.getColumnModel().getColumn(0).setMaxWidth(45);
        jTableUsers.getColumnModel().getColumn(0).setMinWidth(55);
        
            //Rango de tamaños para columna nombre
        jTableUsers.getColumnModel().getColumn(1).setMinWidth(100);
        jTableUsers.getColumnModel().getColumn(1).setMaxWidth(140);
        
            //Columna contraseña
        jTableUsers.getColumnModel().getColumn(2).setMinWidth(120);
        jTableUsers.getColumnModel().getColumn(2).setMaxWidth(140);
        
            //Columna ROL
        jTableUsers.getColumnModel().getColumn(3).setMinWidth(100);
        jTableUsers.getColumnModel().getColumn(3).setMaxWidth(120);

        
        //ColumnaEstado
        jTableUsers.getColumnModel().getColumn(4).setMinWidth(65);
        jTableUsers.getColumnModel().getColumn(4).setMaxWidth(65);  
        


    }

    private void MostrarTabVentas() {
        vc.cargar_tabla_venta(jTableVentas);
        modelo = (DefaultTableModel) jTableVentas.getModel();
        //TRATAMIENTO ESTETICO A COLUMNAS
        DefaultTableCellRenderer AlinearCenter = new DefaultTableCellRenderer();//Para alinear xd
        AlinearCenter.setHorizontalAlignment(SwingConstants.CENTER);//.LEFT .RIGHT .CENTER
        
        //Alineacion de titulos
        ((DefaultTableCellRenderer) jTableVentas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
        //Dejar tamaño fijo a columna ID
        jTableVentas.getColumnModel().getColumn(0).setMinWidth(60);
        jTableVentas.getColumnModel().getColumn(0).setMaxWidth(100);
        
            //Rango de tamaños para columna FECHA
        jTableVentas.getColumnModel().getColumn(1).setMinWidth(200);
        jTableVentas.getColumnModel().getColumn(1).setMaxWidth(250);
        
            //Columna USUARIO
        jTableVentas.getColumnModel().getColumn(2).setMinWidth(100);
        jTableVentas.getColumnModel().getColumn(2).setMaxWidth(150);
        jTableVentas.getColumnModel().getColumn(2).setCellRenderer(AlinearCenter);
        
            //Columna TOTAL
        jTableVentas.getColumnModel().getColumn(3).setMinWidth(130);
        jTableVentas.getColumnModel().getColumn(3).setMaxWidth(170);
        jTableVentas.getColumnModel().getColumn(3).setCellRenderer(AlinearCenter);
        
            //Columna de Monto de Ajuste
        jTableVentas.getColumnModel().getColumn(4).setCellRenderer(AlinearCenter);
        jTableVentas.getColumnModel().getColumn(4).setMinWidth(100);
        jTableVentas.getColumnModel().getColumn(4).setMinWidth(150);
            
        
    }

    private void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void cerrarCaja() {
        estado_caja = false; //al pedo
        tabsPanel.setSelectedComponent(panelCajero);
        tabsPanel.setEnabledAt(0, estado_caja);
        btnNewVenta.setEnabled(false);
        tabsPanel.setToolTipTextAt(0, "Recuerda abrir tu caja para poder realizar ventas");
        btnNewVenta.setToolTipText("Recuerda abrir tu caja para poder realizar ventas");
        fieldEstadoCaja.setText("Cerrado");
        fieldEstadoCaja.setForeground(new Color(204, 0, 0));
        fieldMontoInicialCaja.setText("-");

        //botones
        btnAbrirCaja.setEnabled(true);
        btnAbrirCaja.setToolTipText("Clic aquí para abrir caja");
        btnCerrarCaja.setEnabled(false);
        btnCerrarCaja.setToolTipText("La caja no está abierta");

        //Ventana Confirmar arqueo
        fieldArqTotalSistema.setValue(null);
        fieldArqFechaRemision.setValue(null);
        fieldArqValorRemitido.setValue(null);

        if (cajaConfirmada()) {
            fieldArqEstadoCaja.setText("CONFIRMADO");
            btnConfirmArqueo.setEnabled(false);
            btnConfirmArqueo.setToolTipText("No hay remisión sin confirmar");
            fieldArqMontoFinal.setEditable(false);
            fieldArqMontoFinal.setToolTipText("Aquí aparecerá el monto a ser guardado");

            fieldArqMontoAper.setValue(null);
            fieldArqTotalIngresos.setValue(null);
            fieldArqTotalSistema.setValue(null);
            fieldArqFechaRemision.setValue(null);
            fieldArqValorRemitido.setValue(null);

        } else {
            fieldArqEstadoCaja.setText("Aguardando Confirmación");
            fieldEstadoCaja.setText("Cerrado, aguardando confirmación");
            btnAbrirCaja.setEnabled(false);
            btnAbrirCaja.setToolTipText("La caja debe primero ser confirmada");
            btnConfirmArqueo.setEnabled(true);
            btnConfirmArqueo.setToolTipText(null);
            fieldArqMontoFinal.setEditable(true);
            fieldArqMontoAper.setValue(montoInicCaja());
            fieldArqTotalIngresos.setValue((ArqueoSistema() - montoInicCaja()));
            fieldArqTotalSistema.setValue(ArqueoSistema());
            fieldArqFechaRemision.setValue(fechaCierreCaja());
            fieldArqValorRemitido.setValue(montoRemitido());
            fieldArqMontoFinal.setValue(montoRemitido());

        }
        //fieldArqEstadoCaja.setForeground(new Color(204,0,0));

    }

    public void abrirCaja() {
        estado_caja = true;
        fieldEstadoCaja.setText("Abierto");
        tabsPanel.setEnabledAt(0, estado_caja);
        btnNewVenta.setEnabled(true);
        tabsPanel.setToolTipTextAt(0, "Registrar nueva venta");
        btnNewVenta.setToolTipText("Registrar nueva venta");
        fieldEstadoCaja.setText("Abierto");
        fieldEstadoCaja.setForeground(new Color(0, 153, 0));

        btnAbrirCaja.setEnabled(false);
        btnAbrirCaja.setToolTipText("La caja ya está abierta");
        btnCerrarCaja.setEnabled(true);
        btnCerrarCaja.setToolTipText("Cerrar caja");

        fieldMontoInicialCaja.setValue((montoInicCaja()));
        fieldArqMontoAper.setValue(montoInicCaja());
        FieldFechaAperturaCaja.setValue(fechaInicialCaja());
        fieldArqEstadoCaja.setText("Abierta, sin remitir");
        btnConfirmArqueo.setEnabled(false);
        btnConfirmArqueo.setToolTipText("No hay remisión sin confirmar");
        fieldArqMontoFinal.setEditable(false);
        fieldArqFechaRemision.setValue(null);

    }
    
    //**********************************************************
    //              FORMATEAR FECHA PARA LEER MEJOR EN PANTALLA
    //**********************************************************
     //Cuando usa fecha y hora
        //formato---> miércoles 9 de marzo de 2022 a las 12:39:07
    private String prettyFechaHora(Date uglyFecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        String fechaRetorno = "";
        LocalDateTime localdatet = uglyFecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        
        fechaRetorno = localdatet.format(formatter) + " a las " + localdatet.toLocalTime();
        return fechaRetorno;
    }
    //Formato---> 9/03/2022 a las 12:39:07
    private String prettyFechaHoraCorto(Date uglyFecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        String fechaRetorno = "";
        LocalDateTime localdatet = uglyFecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        
        fechaRetorno = localdatet.format(formatter) + " a las " + localdatet.toLocalTime();
        return fechaRetorno;
    }
        //Formato---> 9/03/2022 
      private String prettyFechaCorto(Date uglyFecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        String fechaRetorno = "";
        LocalDateTime localdatet = uglyFecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        
        fechaRetorno = localdatet.format(formatter);
        return fechaRetorno;
    }
     
    
    //******************************************
    public void setEstado_caja(boolean estado_caja) {
        this.estado_caja = estado_caja;
        if (estado_caja) {
            abrirCaja();
        } else {
            cerrarCaja();
        }

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        dialogAbrirCaja = new javax.swing.JDialog();
        contValoresApertura = new javax.swing.JPanel();
        labelSaldoInicial = new javax.swing.JLabel();
        btnConfirmarAbrirCaja = new javax.swing.JButton();
        btnCancelarAbrirCaja = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        fieldSaldoInicAbrirCaja = new javax.swing.JFormattedTextField();
        dialogCerrarCaja = new javax.swing.JDialog();
        contValoresCierre = new javax.swing.JPanel();
        contBilletes = new javax.swing.JPanel();
        contTituloBilletes = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        contBilletesCount = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        spin2mil = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        spin5mil = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        spin10mil = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        spin20mil = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        spin50mil = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        spin100mil = new javax.swing.JSpinner();
        contMonedas = new javax.swing.JPanel();
        contTituloMonedas = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        contMonedasCount = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        spin50gs = new javax.swing.JSpinner();
        jLabel21 = new javax.swing.JLabel();
        spin100gs = new javax.swing.JSpinner();
        jLabel22 = new javax.swing.JLabel();
        spin500gs = new javax.swing.JSpinner();
        jLabel23 = new javax.swing.JLabel();
        spin1000gs = new javax.swing.JSpinner();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        contTotal = new javax.swing.JPanel();
        labelRemitSaldo1 = new javax.swing.JLabel();
        labelRemitSaldo2 = new javax.swing.JLabel();
        labelRemitSaldo3 = new javax.swing.JLabel();
        btnCancelarArqueo = new javax.swing.JButton();
        btnRemitirArqueoMuestra = new javax.swing.JButton();
        fieldTotalMuestra = new javax.swing.JFormattedTextField();
        dialogAdmUsers = new javax.swing.JDialog();
        scroll2AdmUsr = new javax.swing.JScrollPane();
        jTableUsers = new javax.swing.JTable();
        contSidebarAdmUser = new javax.swing.JPanel();
        scroll1AdmUsr = new javax.swing.JScrollPane();
        contAdmUsr1 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        fieldIdUser = new javax.swing.JTextField();
        fieldNomUser = new javax.swing.JTextField();
        fieldPassUser = new javax.swing.JTextField();
        fieldEstadoUser = new javax.swing.JTextField();
        jComboBoxRolUsers = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        contAdmUser = new javax.swing.JPanel();
        btnReactivarUser = new javax.swing.JButton();
        btnDesactivarUser = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();
        btnModUser = new javax.swing.JButton();
        dialogVerifArqueo = new javax.swing.JDialog();
        contGestArq = new javax.swing.JPanel();
        labelTitleGestArq = new javax.swing.JLabel();
        labelGestArq1 = new javax.swing.JLabel();
        labelGestArq2 = new javax.swing.JLabel();
        labelGestArq3 = new javax.swing.JLabel();
        labelGestArq4 = new javax.swing.JLabel();
        labelGestArq5 = new javax.swing.JLabel();
        labelGestArq6 = new javax.swing.JLabel();
        labelGestArq8 = new javax.swing.JLabel();
        btnConfirmArqueo = new javax.swing.JButton();
        fieldArqEstadoCaja = new javax.swing.JTextField();
        fieldArqTotalSistema = new javax.swing.JFormattedTextField();
        fieldArqFechaRemision = new javax.swing.JFormattedTextField();
        fieldArqValorRemitido = new javax.swing.JFormattedTextField();
        fieldArqMontoFinal = new javax.swing.JFormattedTextField();
        labelMontoInicial = new javax.swing.JLabel();
        fieldArqMontoAper = new javax.swing.JFormattedTextField();
        labelTotalVentas = new javax.swing.JLabel();
        fieldArqTotalIngresos = new javax.swing.JFormattedTextField();
        dialogHistorialArqueo = new javax.swing.JDialog();
        contDiagHistTitle = new javax.swing.JPanel();
        labelHistArqs = new javax.swing.JLabel();
        scrollHistArq = new javax.swing.JScrollPane();
        tableHistArq = new javax.swing.JTable();
        sidebarMain = new javax.swing.JPanel();
        logoCont = new javax.swing.JPanel();
        mainLogo = new javax.swing.JLabel();
        contSidebarButtons = new javax.swing.JPanel();
        btnNewVenta = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnCajero = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        mainTitle = new javax.swing.JLabel();
        fieldUserName = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        tabsPanel = new javax.swing.JTabbedPane();
        panelNuevaVenta = new javax.swing.JPanel();
        contMainVenta = new javax.swing.JPanel();
        contPanelEntrada = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        fieldCod = new javax.swing.JTextField();
        labCod = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        fieldId = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fieldDesc = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fieldCant = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        fieldPrecUnit = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        fieldPrecTotal = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        fieldStock = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVentaItems = new javax.swing.JTable();
        contCierreVenta = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        btnCerrarVenta = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fieldTotalPagar = new javax.swing.JFormattedTextField();
        panelProductos = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelContEntradaProductos = new javax.swing.JPanel();
        contSidebarT2 = new javax.swing.JPanel();
        contBtnProductos = new javax.swing.JPanel();
        btnActualizarProductos = new javax.swing.JButton();
        btnBorrarProducto = new javax.swing.JButton();
        btnGuardarProducto = new javax.swing.JButton();
        btnExportProduc = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        contInsertProd = new javax.swing.JPanel();
        labT2Cod = new javax.swing.JLabel();
        labT2Desc = new javax.swing.JLabel();
        labT2StockDisp = new javax.swing.JLabel();
        labT2PrecioCosto = new javax.swing.JLabel();
        fieldT2Cod = new javax.swing.JTextField();
        fieldT2Desc = new javax.swing.JTextField();
        fieldT2Stock = new javax.swing.JTextField();
        fieldT2PrecioCosto = new javax.swing.JTextField();
        labT2PrecioVenta = new javax.swing.JLabel();
        fieldT2PrecioVenta = new javax.swing.JTextField();
        labT2Categorias = new javax.swing.JLabel();
        jComboBox2Categorias = new javax.swing.JComboBox<>();
        fieldT2Id = new javax.swing.JTextField();
        labT2Cod1 = new javax.swing.JLabel();
        btnLimpiarProd = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        contenedorTablat2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableArticulos = new javax.swing.JTable();
        panelVentas = new javax.swing.JPanel();
        contBtnVentas = new javax.swing.JPanel();
        labelResumenVentas = new javax.swing.JLabel();
        btnResumenVentasDiario = new javax.swing.JButton();
        btnResumenVentaSiete = new javax.swing.JButton();
        btnResumenVentaMes = new javax.swing.JButton();
        btnResumenVentaAnho = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableVentas = new javax.swing.JTable();
        panelConfig = new javax.swing.JPanel();
        panelContConfig = new javax.swing.JPanel();
        btnGestionarUsuariosAdmin = new javax.swing.JButton();
        btnAbrirGestorArqueoAdmin = new javax.swing.JButton();
        btnDialogHistorialArq = new javax.swing.JButton();
        panelContAcercaDe = new javax.swing.JPanel();
        labelAcercaDE = new javax.swing.JLabel();
        labelAD1 = new javax.swing.JLabel();
        labelAD2 = new javax.swing.JLabel();
        labelAD3 = new javax.swing.JLabel();
        labelAD4 = new javax.swing.JLabel();
        labelAD5 = new javax.swing.JLabel();
        labelAD6 = new javax.swing.JLabel();
        separatorAD = new javax.swing.JSeparator();
        separatorAD1 = new javax.swing.JSeparator();
        panelCajero = new javax.swing.JPanel();
        labelTitleCajero = new javax.swing.JLabel();
        labelDescripCajero = new javax.swing.JLabel();
        contBtnAbrir = new javax.swing.JPanel();
        btnAbrirCaja = new javax.swing.JButton();
        contBtnCerrarCaja = new javax.swing.JPanel();
        btnCerrarCaja = new javax.swing.JButton();
        contEstadodeCaja = new javax.swing.JPanel();
        labelPanelCajero1 = new javax.swing.JLabel();
        labelPanelCajero2 = new javax.swing.JLabel();
        fieldEstadoCaja = new javax.swing.JTextField();
        FieldFechaAperturaCaja = new javax.swing.JFormattedTextField();
        fieldMontoInicialCaja = new javax.swing.JFormattedTextField();
        labelPanelCajero4 = new javax.swing.JLabel();

        dialogAbrirCaja.setMinimumSize(new java.awt.Dimension(534, 200));
        dialogAbrirCaja.setModal(true);
        dialogAbrirCaja.setResizable(false);
        dialogAbrirCaja.getContentPane().setLayout(new javax.swing.BoxLayout(dialogAbrirCaja.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        contValoresApertura.setBackground(new java.awt.Color(153, 153, 255));
        contValoresApertura.setEnabled(false);
        contValoresApertura.setLayout(new java.awt.GridBagLayout());

        labelSaldoInicial.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        labelSaldoInicial.setText("Saldo Inicial:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 0, 30);
        contValoresApertura.add(labelSaldoInicial, gridBagConstraints);

        btnConfirmarAbrirCaja.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnConfirmarAbrirCaja.setText("CONFIRMAR APERTURA");
        btnConfirmarAbrirCaja.setToolTipText("Confirmar apertura de caja");
        btnConfirmarAbrirCaja.setNextFocusableComponent(btnConfirmarAbrirCaja);
        btnConfirmarAbrirCaja.setOpaque(false);
        btnConfirmarAbrirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarAbrirCajaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 0, 30);
        contValoresApertura.add(btnConfirmarAbrirCaja, gridBagConstraints);

        btnCancelarAbrirCaja.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnCancelarAbrirCaja.setText("CANCELAR");
        btnCancelarAbrirCaja.setMaximumSize(new java.awt.Dimension(163, 23));
        btnCancelarAbrirCaja.setMinimumSize(new java.awt.Dimension(163, 23));
        btnCancelarAbrirCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelarAbrirCajaMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 0, 30);
        contValoresApertura.add(btnCancelarAbrirCaja, gridBagConstraints);

        jLabel27.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel27.setText("Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 20);
        contValoresApertura.add(jLabel27, gridBagConstraints);

        fieldSaldoInicAbrirCaja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        fieldSaldoInicAbrirCaja.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        fieldSaldoInicAbrirCaja.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        fieldSaldoInicAbrirCaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldSaldoInicAbrirCajaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldSaldoInicAbrirCajaKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 147;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 0, 30);
        contValoresApertura.add(fieldSaldoInicAbrirCaja, gridBagConstraints);

        dialogAbrirCaja.getContentPane().add(contValoresApertura);

        dialogAbrirCaja.setLocationRelativeTo(null);

        dialogCerrarCaja.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogCerrarCaja.setMaximumSize(new java.awt.Dimension(100, 500));
        dialogCerrarCaja.setMinimumSize(new java.awt.Dimension(900, 400));
        dialogCerrarCaja.setModal(true);
        dialogCerrarCaja.setPreferredSize(new java.awt.Dimension(900, 400));
        dialogCerrarCaja.setResizable(false);
        dialogCerrarCaja.setSize(new java.awt.Dimension(750, 300));
        dialogCerrarCaja.getContentPane().setLayout(new javax.swing.BoxLayout(dialogCerrarCaja.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        contValoresCierre.setBackground(new java.awt.Color(102, 102, 255));
        contValoresCierre.setPreferredSize(new java.awt.Dimension(750, 300));
        contValoresCierre.setLayout(new java.awt.GridBagLayout());

        contBilletes.setBackground(new java.awt.Color(204, 204, 255));
        contBilletes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        contBilletes.setMinimumSize(new java.awt.Dimension(135, 202));
        contBilletes.setPreferredSize(new java.awt.Dimension(200, 250));
        contBilletes.setRequestFocusEnabled(false);
        contBilletes.setLayout(new java.awt.BorderLayout());

        contTituloBilletes.setBackground(new java.awt.Color(204, 204, 255));

        jLabel10.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_paper_money_25px.png"))); // NOI18N
        jLabel10.setText("Billetes");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout contTituloBilletesLayout = new javax.swing.GroupLayout(contTituloBilletes);
        contTituloBilletes.setLayout(contTituloBilletesLayout);
        contTituloBilletesLayout.setHorizontalGroup(
            contTituloBilletesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contTituloBilletesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contTituloBilletesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addGroup(contTituloBilletesLayout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())))
        );
        contTituloBilletesLayout.setVerticalGroup(
            contTituloBilletesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contTituloBilletesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        contBilletes.add(contTituloBilletes, java.awt.BorderLayout.NORTH);

        contBilletesCount.setBackground(new java.awt.Color(204, 204, 255));
        contBilletesCount.setLayout(new java.awt.GridBagLayout());

        jLabel14.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel14.setText("2.000 Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 0);
        contBilletesCount.add(jLabel14, gridBagConstraints);

        spin2mil.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        spin2mil.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spin2mil.setEditor(new javax.swing.JSpinner.NumberEditor(spin2mil, "0"));
        spin2mil.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin2milStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        contBilletesCount.add(spin2mil, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel15.setText("5.000 Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 0);
        contBilletesCount.add(jLabel15, gridBagConstraints);

        spin5mil.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        spin5mil.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spin5mil.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin5milStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 10);
        contBilletesCount.add(spin5mil, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel16.setText("10.000 Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        contBilletesCount.add(jLabel16, gridBagConstraints);

        spin10mil.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        spin10mil.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spin10mil.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin10milStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        contBilletesCount.add(spin10mil, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel17.setText("20.000 Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        contBilletesCount.add(jLabel17, gridBagConstraints);

        spin20mil.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        spin20mil.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spin20mil.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin20milStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        contBilletesCount.add(spin20mil, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel18.setText("50.000 Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        contBilletesCount.add(jLabel18, gridBagConstraints);

        spin50mil.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        spin50mil.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spin50mil.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin50milStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        contBilletesCount.add(spin50mil, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel19.setText("100.000 Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 0);
        contBilletesCount.add(jLabel19, gridBagConstraints);

        spin100mil.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        spin100mil.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spin100mil.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin100milStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        contBilletesCount.add(spin100mil, gridBagConstraints);

        contBilletes.add(contBilletesCount, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.35;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        contValoresCierre.add(contBilletes, gridBagConstraints);

        contMonedas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        contMonedas.setPreferredSize(new java.awt.Dimension(200, 250));
        contMonedas.setRequestFocusEnabled(false);
        contMonedas.setLayout(new java.awt.BorderLayout());

        contTituloMonedas.setBackground(new java.awt.Color(204, 204, 255));

        jLabel11.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_coins_25px_2.png"))); // NOI18N
        jLabel11.setText("Monedas");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout contTituloMonedasLayout = new javax.swing.GroupLayout(contTituloMonedas);
        contTituloMonedas.setLayout(contTituloMonedasLayout);
        contTituloMonedasLayout.setHorizontalGroup(
            contTituloMonedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contTituloMonedasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contTituloMonedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        contTituloMonedasLayout.setVerticalGroup(
            contTituloMonedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contTituloMonedasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(10, 10, 10)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        contMonedas.add(contTituloMonedas, java.awt.BorderLayout.NORTH);

        contMonedasCount.setBackground(new java.awt.Color(204, 204, 255));
        contMonedasCount.setLayout(new java.awt.GridBagLayout());

        jLabel20.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel20.setText("50 Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        contMonedasCount.add(jLabel20, gridBagConstraints);

        spin50gs.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        spin50gs.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spin50gs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin50gsStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        contMonedasCount.add(spin50gs, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel21.setText("100 Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        contMonedasCount.add(jLabel21, gridBagConstraints);

        spin100gs.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        spin100gs.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spin100gs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin100gsStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        contMonedasCount.add(spin100gs, gridBagConstraints);

        jLabel22.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel22.setText("500 Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        contMonedasCount.add(jLabel22, gridBagConstraints);

        spin500gs.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        spin500gs.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spin500gs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin500gsStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        contMonedasCount.add(spin500gs, gridBagConstraints);

        jLabel23.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel23.setText("1.000 Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        contMonedasCount.add(jLabel23, gridBagConstraints);

        spin1000gs.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        spin1000gs.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spin1000gs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin1000gsStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        contMonedasCount.add(spin1000gs, gridBagConstraints);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.15;
        contMonedasCount.add(jPanel14, gridBagConstraints);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.15;
        contMonedasCount.add(jPanel15, gridBagConstraints);

        contMonedas.add(contMonedasCount, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.35;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        contValoresCierre.add(contMonedas, gridBagConstraints);

        contTotal.setBackground(new java.awt.Color(204, 204, 255));
        contTotal.setLayout(new java.awt.GridBagLayout());

        labelRemitSaldo1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        labelRemitSaldo1.setText("TOTAL:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(183, 10, 0, 0);
        contTotal.add(labelRemitSaldo1, gridBagConstraints);

        labelRemitSaldo2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        labelRemitSaldo2.setText("Gs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(183, 10, 0, 10);
        contTotal.add(labelRemitSaldo2, gridBagConstraints);

        labelRemitSaldo3.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        labelRemitSaldo3.setText("SALDO DE MUESTRA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(38, 10, 0, 0);
        contTotal.add(labelRemitSaldo3, gridBagConstraints);

        btnCancelarArqueo.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnCancelarArqueo.setText("CANCELAR");
        btnCancelarArqueo.setToolTipText("Cancelar cierre de caja");
        btnCancelarArqueo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelarArqueoMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 38, 0);
        contTotal.add(btnCancelarArqueo, gridBagConstraints);

        btnRemitirArqueoMuestra.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnRemitirArqueoMuestra.setText("REMITIR ARQUEO");
        btnRemitirArqueoMuestra.setToolTipText("Enviar valor para su revisión");
        btnRemitirArqueoMuestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemitirArqueoMuestraActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 38, 0);
        contTotal.add(btnRemitirArqueoMuestra, gridBagConstraints);

        fieldTotalMuestra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        fieldTotalMuestra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        fieldTotalMuestra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fieldTotalMuestraKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldTotalMuestraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldTotalMuestraKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 103;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(176, 38, 44, 0);
        contTotal.add(fieldTotalMuestra, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        contValoresCierre.add(contTotal, gridBagConstraints);

        dialogCerrarCaja.getContentPane().add(contValoresCierre);

        dialogCerrarCaja.setLocationRelativeTo(null);

        dialogAdmUsers.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogAdmUsers.setTitle("Gestion de Usuarios");
        dialogAdmUsers.setMinimumSize(new java.awt.Dimension(950, 468));
        dialogAdmUsers.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        dialogAdmUsers.setResizable(false);
        dialogAdmUsers.setSize(new java.awt.Dimension(950, 468));

        jTableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableUsers.getTableHeader().setReorderingAllowed(false);
        jTableUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableUsersMousePressed(evt);
            }
        });
        scroll2AdmUsr.setViewportView(jTableUsers);

        dialogAdmUsers.getContentPane().add(scroll2AdmUsr, java.awt.BorderLayout.CENTER);

        jLabel39.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        jLabel39.setText("ID:");

        jLabel31.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        jLabel31.setText("Nombre:");

        jLabel32.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        jLabel32.setText("Contraseña:");

        jLabel33.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        jLabel33.setText("Estado:");

        jLabel34.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        jLabel34.setText("Rol:");

        fieldIdUser.setEditable(false);
        fieldIdUser.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        fieldIdUser.setToolTipText("Código único identificador del usuario.");

        fieldNomUser.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        fieldNomUser.setToolTipText("Nombre de usuario");

        fieldPassUser.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        fieldPassUser.setToolTipText("Contraseña de acceso del usuario");

        fieldEstadoUser.setEditable(false);
        fieldEstadoUser.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        fieldEstadoUser.setToolTipText("Si el usuario puede o no iniciar sesión.");

        jComboBoxRolUsers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxRolUsers.setToolTipText("Nivel de privilegio que posee el usuario.");

        jLabel7.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jLabel7.setText("Usuarios");

        javax.swing.GroupLayout contAdmUsr1Layout = new javax.swing.GroupLayout(contAdmUsr1);
        contAdmUsr1.setLayout(contAdmUsr1Layout);
        contAdmUsr1Layout.setHorizontalGroup(
            contAdmUsr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contAdmUsr1Layout.createSequentialGroup()
                .addGroup(contAdmUsr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contAdmUsr1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(contAdmUsr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(contAdmUsr1Layout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jComboBoxRolUsers, 0, 145, Short.MAX_VALUE))
                            .addGroup(contAdmUsr1Layout.createSequentialGroup()
                                .addGroup(contAdmUsr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(contAdmUsr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldEstadoUser)
                                    .addComponent(fieldIdUser)
                                    .addComponent(fieldNomUser)
                                    .addComponent(fieldPassUser)))))
                    .addGroup(contAdmUsr1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addContainerGap())
        );
        contAdmUsr1Layout.setVerticalGroup(
            contAdmUsr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contAdmUsr1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(contAdmUsr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contAdmUsr1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel39))
                    .addComponent(fieldIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(contAdmUsr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contAdmUsr1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel31))
                    .addComponent(fieldNomUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(contAdmUsr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contAdmUsr1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel32))
                    .addComponent(fieldPassUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(contAdmUsr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contAdmUsr1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel33))
                    .addComponent(fieldEstadoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(contAdmUsr1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contAdmUsr1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel34))
                    .addComponent(jComboBoxRolUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        scroll1AdmUsr.setViewportView(contAdmUsr1);

        btnReactivarUser.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnReactivarUser.setText("Reactivar");
        btnReactivarUser.setToolTipText("Al reactivar un usuario desactivado, podrá volver a iniciar sesión.");
        btnReactivarUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnReactivarUserMouseReleased(evt);
            }
        });

        btnDesactivarUser.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnDesactivarUser.setText("Desactivar");
        btnDesactivarUser.setToolTipText("Desactivar usuario (no podrá iniciar sesión).");
        btnDesactivarUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDesactivarUserMouseReleased(evt);
            }
        });

        btnAddUser.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnAddUser.setText("Agregar");
        btnAddUser.setToolTipText("Agregar nuevo usuario con los datos proporcionados");
        btnAddUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddUserMouseReleased(evt);
            }
        });

        btnModUser.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnModUser.setText("Modificar");
        btnModUser.setToolTipText("Guardar modificación a los datos del usuario.");
        btnModUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnModUserMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout contAdmUserLayout = new javax.swing.GroupLayout(contAdmUser);
        contAdmUser.setLayout(contAdmUserLayout);
        contAdmUserLayout.setHorizontalGroup(
            contAdmUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contAdmUserLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(contAdmUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDesactivarUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(contAdmUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReactivarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contAdmUserLayout.setVerticalGroup(
            contAdmUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contAdmUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contAdmUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddUser)
                    .addComponent(btnModUser))
                .addGap(35, 35, 35)
                .addGroup(contAdmUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDesactivarUser)
                    .addComponent(btnReactivarUser))
                .addContainerGap())
        );

        javax.swing.GroupLayout contSidebarAdmUserLayout = new javax.swing.GroupLayout(contSidebarAdmUser);
        contSidebarAdmUser.setLayout(contSidebarAdmUserLayout);
        contSidebarAdmUserLayout.setHorizontalGroup(
            contSidebarAdmUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contSidebarAdmUserLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(contSidebarAdmUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll1AdmUsr)
                    .addComponent(contAdmUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        contSidebarAdmUserLayout.setVerticalGroup(
            contSidebarAdmUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contSidebarAdmUserLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(scroll1AdmUsr, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contAdmUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        dialogAdmUsers.getContentPane().add(contSidebarAdmUser, java.awt.BorderLayout.WEST);

        dialogAdmUsers.setLocationRelativeTo(null);

        dialogVerifArqueo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogVerifArqueo.setTitle("Gestionando Arqueo");
        dialogVerifArqueo.setMinimumSize(new java.awt.Dimension(547, 565));
        dialogVerifArqueo.setModal(true);
        dialogVerifArqueo.setPreferredSize(new java.awt.Dimension(1402, 650));
        dialogVerifArqueo.setResizable(false);

        contGestArq.setPreferredSize(new java.awt.Dimension(1402, 650));

        labelTitleGestArq.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        labelTitleGestArq.setText("Gestión de Caja");

        labelGestArq1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelGestArq1.setText("Estado de la caja:");

        labelGestArq2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelGestArq2.setText("Total del sistema (Gs.):");

        labelGestArq3.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelGestArq3.setText("Última remisión:");

        labelGestArq4.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelGestArq4.setText("Valor remitido (Gs.):");

        labelGestArq5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        labelGestArq5.setForeground(new java.awt.Color(51, 51, 51));
        labelGestArq5.setText("Si los montos coinciden, deja el siguiente valor sin editar,");

        labelGestArq6.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelGestArq6.setText("Monto final (Gs.):");

        labelGestArq8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        labelGestArq8.setForeground(new java.awt.Color(51, 51, 51));
        labelGestArq8.setText("ese será el valor guardado en el cierre de caja.");

        btnConfirmArqueo.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnConfirmArqueo.setText("CONFIRMAR CIERRE");
        btnConfirmArqueo.setToolTipText("Clic aquí para confirmar los valores de arqueo.");
        btnConfirmArqueo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmArqueoActionPerformed(evt);
            }
        });

        fieldArqEstadoCaja.setEditable(false);
        fieldArqEstadoCaja.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        fieldArqEstadoCaja.setForeground(new java.awt.Color(51, 51, 51));
        fieldArqEstadoCaja.setText("Aguardando Confirmacion");

        fieldArqTotalSistema.setEditable(false);
        fieldArqTotalSistema.setToolTipText("Cuánto debe haber en caja según los registros.");

        fieldArqFechaRemision.setEditable(false);
        fieldArqFechaRemision.setToolTipText("Fecha y hora de remisión de saldo de muestra.");

        fieldArqValorRemitido.setEditable(false);
        fieldArqValorRemitido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        fieldArqValorRemitido.setToolTipText("Saldo de muestra remitido por cajero.");

        fieldArqMontoFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        fieldArqMontoFinal.setToolTipText("Monto a ser guardado como arqueo final.");
        fieldArqMontoFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldArqMontoFinalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldArqMontoFinalKeyTyped(evt);
            }
        });
        setTransferHandler(null);

        labelMontoInicial.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelMontoInicial.setText("Monto de Apertura:");

        fieldArqMontoAper.setEditable(false);
        fieldArqMontoAper.setToolTipText("Con cuanto dinero se abrio la caja.");

        labelTotalVentas.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelTotalVentas.setText("Total Ingresos (Gs.):");

        fieldArqTotalIngresos.setEditable(false);
        fieldArqTotalIngresos.setToolTipText("Monto total de los ingresos recopilados en el sistema.");

        javax.swing.GroupLayout contGestArqLayout = new javax.swing.GroupLayout(contGestArq);
        contGestArq.setLayout(contGestArqLayout);
        contGestArqLayout.setHorizontalGroup(
            contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contGestArqLayout.createSequentialGroup()
                .addGroup(contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contGestArqLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(labelTitleGestArq)
                        .addGap(0, 1023, Short.MAX_VALUE))
                    .addGroup(contGestArqLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contGestArqLayout.createSequentialGroup()
                                .addComponent(labelGestArq6)
                                .addGap(72, 72, 72)
                                .addComponent(fieldArqMontoFinal))
                            .addGroup(contGestArqLayout.createSequentialGroup()
                                .addComponent(labelGestArq4)
                                .addGap(54, 54, 54)
                                .addComponent(fieldArqValorRemitido))
                            .addGroup(contGestArqLayout.createSequentialGroup()
                                .addComponent(labelGestArq3)
                                .addGap(82, 82, 82)
                                .addComponent(fieldArqFechaRemision))
                            .addGroup(contGestArqLayout.createSequentialGroup()
                                .addComponent(labelGestArq2)
                                .addGap(34, 34, 34)
                                .addComponent(fieldArqTotalSistema))
                            .addGroup(contGestArqLayout.createSequentialGroup()
                                .addComponent(labelGestArq1)
                                .addGap(68, 68, 68)
                                .addComponent(fieldArqEstadoCaja))
                            .addGroup(contGestArqLayout.createSequentialGroup()
                                .addComponent(labelMontoInicial)
                                .addGap(59, 59, 59)
                                .addComponent(fieldArqMontoAper))
                            .addGroup(contGestArqLayout.createSequentialGroup()
                                .addComponent(btnConfirmArqueo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(contGestArqLayout.createSequentialGroup()
                                .addComponent(labelTotalVentas)
                                .addGap(51, 51, 51)
                                .addComponent(fieldArqTotalIngresos)))))
                .addGap(38, 38, 38))
            .addGroup(contGestArqLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelGestArq5)
                    .addComponent(labelGestArq8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contGestArqLayout.setVerticalGroup(
            contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contGestArqLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(labelTitleGestArq)
                .addGap(29, 29, 29)
                .addGroup(contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGestArq1)
                    .addComponent(fieldArqEstadoCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMontoInicial)
                    .addComponent(fieldArqMontoAper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotalVentas)
                    .addComponent(fieldArqTotalIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelGestArq2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldArqTotalSistema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelGestArq3)
                    .addComponent(fieldArqFechaRemision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelGestArq4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldArqValorRemitido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelGestArq5)
                .addGap(6, 6, 6)
                .addComponent(labelGestArq8)
                .addGap(18, 18, 18)
                .addGroup(contGestArqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelGestArq6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldArqMontoFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnConfirmArqueo)
                .addGap(99, 99, 99))
        );

        dialogVerifArqueo.setLocationRelativeTo(null);
        fieldArqMontoFinal.setTransferHandler(null);

        javax.swing.GroupLayout dialogVerifArqueoLayout = new javax.swing.GroupLayout(dialogVerifArqueo.getContentPane());
        dialogVerifArqueo.getContentPane().setLayout(dialogVerifArqueoLayout);
        dialogVerifArqueoLayout.setHorizontalGroup(
            dialogVerifArqueoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogVerifArqueoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(contGestArq, javax.swing.GroupLayout.DEFAULT_SIZE, 1381, Short.MAX_VALUE)
                .addContainerGap())
        );
        dialogVerifArqueoLayout.setVerticalGroup(
            dialogVerifArqueoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogVerifArqueoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contGestArq, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addContainerGap())
        );

        dialogHistorialArqueo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogHistorialArqueo.setLocationByPlatform(true);
        dialogHistorialArqueo.setMinimumSize(new java.awt.Dimension(980, 502));
        dialogHistorialArqueo.setResizable(false);
        dialogHistorialArqueo.setLocationRelativeTo(null);
        //dialogHistorialArqueo.setSize(getBounds().height, getBounds().width);

        labelHistArqs.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        labelHistArqs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHistArqs.setText("Historial de Arqueos");
        labelHistArqs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout contDiagHistTitleLayout = new javax.swing.GroupLayout(contDiagHistTitle);
        contDiagHistTitle.setLayout(contDiagHistTitleLayout);
        contDiagHistTitleLayout.setHorizontalGroup(
            contDiagHistTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(contDiagHistTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contDiagHistTitleLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(labelHistArqs, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        contDiagHistTitleLayout.setVerticalGroup(
            contDiagHistTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(contDiagHistTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contDiagHistTitleLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(labelHistArqs)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        dialogHistorialArqueo.getContentPane().add(contDiagHistTitle, java.awt.BorderLayout.NORTH);

        tableHistArq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollHistArq.setViewportView(tableHistArq);

        dialogHistorialArqueo.getContentPane().add(scrollHistArq, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1080, 1080));
        setMinimumSize(new java.awt.Dimension(1100, 650));
        setPreferredSize(new java.awt.Dimension(1100, 650));

        sidebarMain.setBackground(new java.awt.Color(103, 226, 109));
        sidebarMain.setPreferredSize(new java.awt.Dimension(135, 900));

        logoCont.setBackground(new java.awt.Color(103, 226, 109));
        logoCont.setPreferredSize(new java.awt.Dimension(120, 120));
        logoCont.setLayout(new java.awt.GridLayout(1, 0));

        mainLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_online_shopping_100px_1.png"))); // NOI18N
        logoCont.add(mainLogo);

        contSidebarButtons.setBackground(new java.awt.Color(103, 226, 109));
        contSidebarButtons.setLayout(new java.awt.GridBagLayout());

        btnNewVenta.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnNewVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_receive_cash_30px.png"))); // NOI18N
        btnNewVenta.setText("Nueva Venta");
        btnNewVenta.setToolTipText("Registrar nueva venta");
        btnNewVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewVentaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnNewVentaMouseReleased(evt);
            }
        });
        btnNewVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewVentaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 0, 10);
        contSidebarButtons.add(btnNewVenta, gridBagConstraints);

        btnProductos.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_empty_box_30px.png"))); // NOI18N
        btnProductos.setText("Productos");
        btnProductos.setToolTipText("Ir a la pestaña Productos");
        btnProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnProductosMouseReleased(evt);
            }
        });
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(18, 10, 0, 10);
        contSidebarButtons.add(btnProductos, gridBagConstraints);

        btnConfig.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_settings_30px.png"))); // NOI18N
        btnConfig.setText("Configuración");
        btnConfig.setToolTipText("Ir a la pestaña Configuración");
        btnConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfigMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnConfigMouseReleased(evt);
            }
        });
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(18, 10, 0, 10);
        contSidebarButtons.add(btnConfig, gridBagConstraints);

        btnVentas.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_list_30px.png"))); // NOI18N
        btnVentas.setText("Ventas");
        btnVentas.setToolTipText("Ir a la pestaña Ventas");
        btnVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentasMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnVentasMouseReleased(evt);
            }
        });
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(18, 10, 0, 10);
        contSidebarButtons.add(btnVentas, gridBagConstraints);

        btnCajero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_cash_counter_30px.png"))); // NOI18N
        btnCajero.setText("Cajero");
        btnCajero.setMaximumSize(new java.awt.Dimension(135, 39));
        btnCajero.setMinimumSize(new java.awt.Dimension(135, 39));
        btnCajero.setPreferredSize(new java.awt.Dimension(111, 39));
        btnCajero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCajeroMouseReleased(evt);
            }
        });
        btnCajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCajeroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(18, 10, 0, 10);
        contSidebarButtons.add(btnCajero, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(103, 226, 109));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(18, 10, 0, 10);
        contSidebarButtons.add(jPanel4, gridBagConstraints);

        jPanel13.setBackground(new java.awt.Color(103, 226, 109));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0.25;
        contSidebarButtons.add(jPanel13, gridBagConstraints);

        javax.swing.GroupLayout sidebarMainLayout = new javax.swing.GroupLayout(sidebarMain);
        sidebarMain.setLayout(sidebarMainLayout);
        sidebarMainLayout.setHorizontalGroup(
            sidebarMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contSidebarButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(logoCont, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );
        sidebarMainLayout.setVerticalGroup(
            sidebarMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarMainLayout.createSequentialGroup()
                .addComponent(logoCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(contSidebarButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(sidebarMain, java.awt.BorderLayout.WEST);

        header.setBackground(new java.awt.Color(103, 226, 109));

        mainTitle.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        mainTitle.setForeground(new java.awt.Color(255, 255, 255));
        mainTitle.setText("CANTINA ANGLO");

        fieldUserName.setEditable(false);
        fieldUserName.setBackground(new java.awt.Color(103, 226, 109));
        fieldUserName.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        fieldUserName.setBorder(null);

        jLabel29.setFont(new java.awt.Font("Roboto Medium", 0, 13)); // NOI18N
        jLabel29.setText("Usuario:");

        btnCerrarSesion.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnCerrarSesion.setText("CERRAR SESION");
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(mainTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 706, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnCerrarSesion)
                .addGap(63, 63, 63))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainTitle)
                    .addComponent(fieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(btnCerrarSesion))
                .addGap(15, 15, 15))
        );

        getContentPane().add(header, java.awt.BorderLayout.NORTH);

        panelNuevaVenta.setBackground(new java.awt.Color(103, 226, 109));
        panelNuevaVenta.setLayout(new java.awt.BorderLayout());

        contMainVenta.setLayout(new java.awt.BorderLayout());

        contPanelEntrada.setLayout(new java.awt.GridBagLayout());

        fieldCod.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        fieldCod.setToolTipText("Escanea el producto con tu lector");
        fieldCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fieldCodKeyPressed(evt);
            }
        });

        labCod.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        labCod.setText("Cód. de Barras");
        labCod.setToolTipText("Escanea el producto con tu lector");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldCod)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labCod, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labCod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 7);
        contPanelEntrada.add(jPanel1, gridBagConstraints);

        fieldId.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        fieldId.setToolTipText("Código único de producto en el inventario");

        jLabel12.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel12.setText("Id Producto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fieldId)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        contPanelEntrada.add(jPanel2, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel2.setText("Descripción");

        fieldDesc.setEditable(false);
        fieldDesc.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        fieldDesc.setToolTipText("Descripción del producto");
        fieldDesc.setFocusable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        contPanelEntrada.add(jPanel3, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel3.setText("Cantidad");

        fieldCant.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        fieldCant.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldCantFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldCant, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        contPanelEntrada.add(jPanel6, gridBagConstraints);

        fieldPrecUnit.setEditable(false);
        fieldPrecUnit.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel4.setText("Precio Unitario");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldPrecUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldPrecUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        contPanelEntrada.add(jPanel7, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel6.setText("Prec. Total");

        fieldPrecTotal.setEditable(false);
        fieldPrecTotal.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        fieldPrecTotal.setToolTipText("Precio Unitario x Cantidad");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldPrecTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldPrecTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        contPanelEntrada.add(jPanel8, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Roboto Light", 1, 12)); // NOI18N
        jLabel5.setText("Stock Disponible");

        fieldStock.setEditable(false);
        fieldStock.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        fieldStock.setToolTipText("Cantidad restante de este producto en inventario");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldStock))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        contPanelEntrada.add(jPanel9, gridBagConstraints);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_clear_symbol_30px.png"))); // NOI18N
        jButton1.setToolTipText("Eliminar de esta venta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        contPanelEntrada.add(jPanel10, gridBagConstraints);

        contMainVenta.add(contPanelEntrada, java.awt.BorderLayout.NORTH);

        jTableVentaItems.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTableVentaItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Nº", "ID", "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVentaItems.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableVentaItems);
        if (jTableVentaItems.getColumnModel().getColumnCount() > 0) {
            jTableVentaItems.getColumnModel().getColumn(0).setMinWidth(15);
            jTableVentaItems.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTableVentaItems.getColumnModel().getColumn(1).setMinWidth(20);
            jTableVentaItems.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTableVentaItems.getColumnModel().getColumn(2).setResizable(false);
            jTableVentaItems.getColumnModel().getColumn(3).setMinWidth(50);
            jTableVentaItems.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTableVentaItems.getColumnModel().getColumn(6).setPreferredWidth(40);
        }

        contMainVenta.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        contCierreVenta.setPreferredSize(new java.awt.Dimension(468, 80));

        btnCerrarVenta.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnCerrarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_request_money_30px.png"))); // NOI18N
        btnCerrarVenta.setText("Cerrar Venta");
        btnCerrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(btnCerrarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        jLabel1.setText("Total a Cobrar:");

        jLabel13.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel13.setText("Gs.");

        fieldTotalPagar.setEditable(false);
        fieldTotalPagar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        fieldTotalPagar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        fieldTotalPagar.setPreferredSize(new java.awt.Dimension(50, 21));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(fieldTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contCierreVentaLayout = new javax.swing.GroupLayout(contCierreVenta);
        contCierreVenta.setLayout(contCierreVentaLayout);
        contCierreVentaLayout.setHorizontalGroup(
            contCierreVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contCierreVentaLayout.createSequentialGroup()
                .addContainerGap(498, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );
        contCierreVentaLayout.setVerticalGroup(
            contCierreVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        contMainVenta.add(contCierreVenta, java.awt.BorderLayout.SOUTH);

        panelNuevaVenta.add(contMainVenta, java.awt.BorderLayout.CENTER);

        tabsPanel.addTab("", new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_receive_cash_30px.png")), panelNuevaVenta, ""); // NOI18N

        panelProductos.setLayout(new javax.swing.BoxLayout(panelProductos, javax.swing.BoxLayout.LINE_AXIS));

        jSplitPane1.setDividerSize(0);

        contSidebarT2.setMinimumSize(new java.awt.Dimension(332, 200));
        contSidebarT2.setPreferredSize(new java.awt.Dimension(332, 580));
        contSidebarT2.setLayout(new java.awt.GridBagLayout());

        contBtnProductos.setMaximumSize(new java.awt.Dimension(330, 175));
        contBtnProductos.setMinimumSize(new java.awt.Dimension(330, 175));
        contBtnProductos.setPreferredSize(new java.awt.Dimension(330, 175));
        contBtnProductos.setLayout(new java.awt.GridBagLayout());

        btnActualizarProductos.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnActualizarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_edit_property_30px.png"))); // NOI18N
        btnActualizarProductos.setText("MODIFICAR");
        btnActualizarProductos.setToolTipText("Guarda las modificaciones hechas");
        btnActualizarProductos.setMaximumSize(new java.awt.Dimension(400, 400));
        btnActualizarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProductosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(30, 10, 0, 10);
        contBtnProductos.add(btnActualizarProductos, gridBagConstraints);

        btnBorrarProducto.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnBorrarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_eraser_30px.png"))); // NOI18N
        btnBorrarProducto.setText("BORRAR");
        btnBorrarProducto.setToolTipText("Elimina el producto seleccionado del inventario");
        btnBorrarProducto.setMaximumSize(new java.awt.Dimension(400, 400));
        btnBorrarProducto.setMinimumSize(new java.awt.Dimension(123, 39));
        btnBorrarProducto.setPreferredSize(new java.awt.Dimension(123, 39));
        btnBorrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarProductoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(30, 10, 0, 10);
        contBtnProductos.add(btnBorrarProducto, gridBagConstraints);

        btnGuardarProducto.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnGuardarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_add_30px.png"))); // NOI18N
        btnGuardarProducto.setText("AGREGAR");
        btnGuardarProducto.setToolTipText("Guarda los datos en un nuevo registro");
        btnGuardarProducto.setMaximumSize(new java.awt.Dimension(400, 400));
        btnGuardarProducto.setMinimumSize(new java.awt.Dimension(123, 39));
        btnGuardarProducto.setPreferredSize(new java.awt.Dimension(123, 39));
        btnGuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProductoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(30, 10, 0, 10);
        contBtnProductos.add(btnGuardarProducto, gridBagConstraints);

        btnExportProduc.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        btnExportProduc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_spreadsheet_file_30px.png"))); // NOI18N
        btnExportProduc.setText("EXPORTAR");
        btnExportProduc.setToolTipText("Exportar inventario");
        btnExportProduc.setMaximumSize(new java.awt.Dimension(400, 400));
        btnExportProduc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnExportProducMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(30, 10, 0, 10);
        contBtnProductos.add(btnExportProduc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.weighty = 0.05;
        contSidebarT2.add(contBtnProductos, gridBagConstraints);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setMinimumSize(new java.awt.Dimension(264, 250));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(264, 250));
        jScrollPane4.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jScrollPane4ComponentResized(evt);
            }
        });

        labT2Cod.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        labT2Cod.setText("ID Producto:");

        labT2Desc.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        labT2Desc.setText("Descripción:");

        labT2StockDisp.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        labT2StockDisp.setText("Stock disponible:");

        labT2PrecioCosto.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        labT2PrecioCosto.setText("Precio de Costo:");

        fieldT2Cod.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        fieldT2Cod.setToolTipText("Código de barras del producto");

        fieldT2Desc.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N

        fieldT2Stock.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        fieldT2Stock.setToolTipText("Unidades en existencia");
        fieldT2Stock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldT2StockKeyTyped(evt);
            }
        });

        fieldT2PrecioCosto.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        fieldT2PrecioCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldT2PrecioCostoKeyTyped(evt);
            }
        });

        labT2PrecioVenta.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        labT2PrecioVenta.setText("Precio de Venta:");

        fieldT2PrecioVenta.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        fieldT2PrecioVenta.setToolTipText("Recuerda usar valores redondeados para optimizar cobro.");
        fieldT2PrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldT2PrecioCostoKeyTyped(evt);
            }
        });

        labT2Categorias.setFont(new java.awt.Font("Roboto Black", 0, 13)); // NOI18N
        labT2Categorias.setText("Categorias:");

        jComboBox2Categorias.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jComboBox2Categorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        fieldT2Id.setEditable(false);
        fieldT2Id.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        fieldT2Id.setToolTipText("No te preocupes por este campo, se rellenará automáticamente.");
        fieldT2Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldT2IdActionPerformed(evt);
            }
        });

        labT2Cod1.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        labT2Cod1.setText("Código:");

        btnLimpiarProd.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnLimpiarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_eraser_tool_20px.png"))); // NOI18N
        btnLimpiarProd.setText("Limpiar Campos");
        btnLimpiarProd.setToolTipText("");
        btnLimpiarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarProdActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_add_20px_1.png"))); // NOI18N
        jLabel8.setToolTipText("Agregar nueva categoría");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel8MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout contInsertProdLayout = new javax.swing.GroupLayout(contInsertProd);
        contInsertProd.setLayout(contInsertProdLayout);
        contInsertProdLayout.setHorizontalGroup(
            contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contInsertProdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contInsertProdLayout.createSequentialGroup()
                        .addGroup(contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labT2Desc)
                            .addComponent(labT2StockDisp)
                            .addComponent(labT2PrecioCosto)
                            .addComponent(labT2PrecioVenta)
                            .addComponent(labT2Cod1)
                            .addComponent(labT2Cod))
                        .addGap(26, 26, 26))
                    .addGroup(contInsertProdLayout.createSequentialGroup()
                        .addComponent(labT2Categorias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiarProd)
                    .addComponent(fieldT2Id, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(fieldT2PrecioVenta, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fieldT2PrecioCosto, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fieldT2Stock, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fieldT2Desc, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBox2Categorias, 0, 165, Short.MAX_VALUE))
                    .addComponent(fieldT2Cod, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        contInsertProdLayout.setVerticalGroup(
            contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contInsertProdLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btnLimpiarProd)
                .addGap(20, 20, 20)
                .addGroup(contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labT2Cod)
                    .addComponent(fieldT2Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labT2Cod1)
                    .addComponent(fieldT2Cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labT2Desc)
                    .addComponent(fieldT2Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labT2StockDisp)
                    .addComponent(fieldT2Stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labT2PrecioCosto)
                    .addComponent(fieldT2PrecioCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labT2PrecioVenta)
                    .addComponent(fieldT2PrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(contInsertProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labT2Categorias)
                    .addComponent(jComboBox2Categorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fieldT2Stock.setTransferHandler(null);
        fieldT2PrecioCosto.setTransferHandler(null);
        fieldT2PrecioVenta.setTransferHandler(null);

        jScrollPane4.setViewportView(contInsertProd);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.85;
        gridBagConstraints.weighty = 0.95;
        contSidebarT2.add(jScrollPane4, gridBagConstraints);

        javax.swing.GroupLayout panelContEntradaProductosLayout = new javax.swing.GroupLayout(panelContEntradaProductos);
        panelContEntradaProductos.setLayout(panelContEntradaProductosLayout);
        panelContEntradaProductosLayout.setHorizontalGroup(
            panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contSidebarT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelContEntradaProductosLayout.setVerticalGroup(
            panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContEntradaProductosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(contSidebarT2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(panelContEntradaProductos);

        jTableArticulos.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTableArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableArticulosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableArticulos);

        javax.swing.GroupLayout contenedorTablat2Layout = new javax.swing.GroupLayout(contenedorTablat2);
        contenedorTablat2.setLayout(contenedorTablat2Layout);
        contenedorTablat2Layout.setHorizontalGroup(
            contenedorTablat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
        );
        contenedorTablat2Layout.setVerticalGroup(
            contenedorTablat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorTablat2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(contenedorTablat2);

        panelProductos.add(jSplitPane1);

        tabsPanel.addTab("", new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_empty_box_30px.png")), panelProductos); // NOI18N

        panelVentas.setLayout(new java.awt.BorderLayout());

        labelResumenVentas.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        labelResumenVentas.setText("Genera tus resúmenes");

        btnResumenVentasDiario.setFont(new java.awt.Font("Roboto Light", 1, 15)); // NOI18N
        btnResumenVentasDiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_pdf_30px.png"))); // NOI18N
        btnResumenVentasDiario.setText("Hoy");
        btnResumenVentasDiario.setToolTipText("Ventas hechas hoy");
        btnResumenVentasDiario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnResumenVentasDiario.setMaximumSize(new java.awt.Dimension(183, 39));
        btnResumenVentasDiario.setMinimumSize(new java.awt.Dimension(183, 39));
        btnResumenVentasDiario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnResumenVentasDiarioMouseReleased(evt);
            }
        });

        btnResumenVentaSiete.setFont(new java.awt.Font("Roboto Light", 1, 15)); // NOI18N
        btnResumenVentaSiete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_pdf_30px.png"))); // NOI18N
        btnResumenVentaSiete.setText("Últimos 7 días");
        btnResumenVentaSiete.setToolTipText("Ventas de los últimos 7 días ");
        btnResumenVentaSiete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnResumenVentaSiete.setMaximumSize(new java.awt.Dimension(183, 39));
        btnResumenVentaSiete.setMinimumSize(new java.awt.Dimension(183, 39));
        btnResumenVentaSiete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnResumenVentaSieteMouseReleased(evt);
            }
        });

        btnResumenVentaMes.setFont(new java.awt.Font("Roboto Light", 1, 15)); // NOI18N
        btnResumenVentaMes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_pdf_30px.png"))); // NOI18N
        btnResumenVentaMes.setText("Mes Actual");
        btnResumenVentaMes.setToolTipText("Ventas de este mes");
        btnResumenVentaMes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnResumenVentaMes.setMaximumSize(new java.awt.Dimension(183, 39));
        btnResumenVentaMes.setMinimumSize(new java.awt.Dimension(183, 39));
        btnResumenVentaMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnResumenVentaMesMouseReleased(evt);
            }
        });

        btnResumenVentaAnho.setFont(new java.awt.Font("Roboto Light", 1, 15)); // NOI18N
        btnResumenVentaAnho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_pdf_30px.png"))); // NOI18N
        btnResumenVentaAnho.setText("Año Actual");
        btnResumenVentaAnho.setToolTipText("Ventas de este año");
        btnResumenVentaAnho.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnResumenVentaAnho.setMaximumSize(new java.awt.Dimension(183, 39));
        btnResumenVentaAnho.setMinimumSize(new java.awt.Dimension(183, 39));
        btnResumenVentaAnho.setPreferredSize(new java.awt.Dimension(183, 39));
        btnResumenVentaAnho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnResumenVentaAnhoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout contBtnVentasLayout = new javax.swing.GroupLayout(contBtnVentas);
        contBtnVentas.setLayout(contBtnVentasLayout);
        contBtnVentasLayout.setHorizontalGroup(
            contBtnVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contBtnVentasLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(contBtnVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelResumenVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnResumenVentaSiete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResumenVentaMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResumenVentasDiario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResumenVentaAnho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        contBtnVentasLayout.setVerticalGroup(
            contBtnVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contBtnVentasLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(labelResumenVentas)
                .addGap(30, 30, 30)
                .addComponent(btnResumenVentasDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnResumenVentaSiete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnResumenVentaMes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnResumenVentaAnho, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelVentas.add(contBtnVentas, java.awt.BorderLayout.WEST);

        jTableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "VENDEDOR", "TOTAL"
            }
        ));
        jScrollPane3.setViewportView(jTableVentas);
        if (jTableVentas.getColumnModel().getColumnCount() > 0) {
            jTableVentas.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTableVentas.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTableVentas.getColumnModel().getColumn(2).setPreferredWidth(60);
        }

        panelVentas.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        tabsPanel.addTab("", new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_list_30px.png")), panelVentas); // NOI18N

        panelConfig.setLayout(new java.awt.BorderLayout());

        panelContConfig.setPreferredSize(new java.awt.Dimension(988, 200));
        panelContConfig.setLayout(new java.awt.GridBagLayout());

        btnGestionarUsuariosAdmin.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnGestionarUsuariosAdmin.setText("GESTIONAR USUARIOS");
        btnGestionarUsuariosAdmin.setToolTipText("Agrega, edita, desactiva usuarios");
        btnGestionarUsuariosAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnGestionarUsuariosAdminMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 389;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 193, 0, 227);
        panelContConfig.add(btnGestionarUsuariosAdmin, gridBagConstraints);

        btnAbrirGestorArqueoAdmin.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnAbrirGestorArqueoAdmin.setText("GESTIONAR ARQUEO DE CAJA");
        btnAbrirGestorArqueoAdmin.setToolTipText("Revisa, modifica, confirma arqueo de caja.");
        btnAbrirGestorArqueoAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirGestorArqueoAdminActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 193, 21, 0);
        panelContConfig.add(btnAbrirGestorArqueoAdmin, gridBagConstraints);

        btnDialogHistorialArq.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnDialogHistorialArq.setText("HISTORIAL DE ARQUEOS");
        btnDialogHistorialArq.setToolTipText("Revisa el histórico de arqueos");
        btnDialogHistorialArq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDialogHistorialArqActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 86;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 18, 21, 227);
        panelContConfig.add(btnDialogHistorialArq, gridBagConstraints);

        panelConfig.add(panelContConfig, java.awt.BorderLayout.NORTH);

        panelContAcercaDe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        panelContAcercaDe.setForeground(new java.awt.Color(102, 102, 102));
        panelContAcercaDe.setLayout(new java.awt.GridBagLayout());

        labelAcercaDE.setFont(new java.awt.Font("Roboto Medium", 0, 20)); // NOI18N
        labelAcercaDE.setForeground(new java.awt.Color(102, 102, 102));
        labelAcercaDE.setText("Acerca de este Sistema");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 10, 0, 0);
        panelContAcercaDe.add(labelAcercaDE, gridBagConstraints);

        labelAD1.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        labelAD1.setForeground(new java.awt.Color(102, 102, 102));
        labelAD1.setText("Desarrollado por: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 96, 0, 0);
        panelContAcercaDe.add(labelAD1, gridBagConstraints);

        labelAD2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelAD2.setForeground(new java.awt.Color(102, 102, 102));
        labelAD2.setText("Lucas Frutos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 13, 0, 0);
        panelContAcercaDe.add(labelAD2, gridBagConstraints);

        labelAD3.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelAD3.setForeground(new java.awt.Color(102, 102, 102));
        labelAD3.setText("Daniel Maldonado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 13, 0, 0);
        panelContAcercaDe.add(labelAD3, gridBagConstraints);

        labelAD4.setFont(new java.awt.Font("Roboto Medium", 0, 16)); // NOI18N
        labelAD4.setForeground(new java.awt.Color(102, 102, 102));
        labelAD4.setText("Contactos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 96, 0, 0);
        panelContAcercaDe.add(labelAD4, gridBagConstraints);

        labelAD5.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelAD5.setForeground(new java.awt.Color(102, 102, 102));
        labelAD5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_whatsapp_25px_1.png"))); // NOI18N
        labelAD5.setText("+595 992 205 206");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        panelContAcercaDe.add(labelAD5, gridBagConstraints);

        labelAD6.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelAD6.setForeground(new java.awt.Color(102, 102, 102));
        labelAD6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_whatsapp_25px_1.png"))); // NOI18N
        labelAD6.setText("+595 975 333 205");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 30, 0);
        panelContAcercaDe.add(labelAD6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 833;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 96, 0, 57);
        panelContAcercaDe.add(separatorAD, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 833;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 96, 0, 57);
        panelContAcercaDe.add(separatorAD1, gridBagConstraints);

        panelConfig.add(panelContAcercaDe, java.awt.BorderLayout.CENTER);

        tabsPanel.addTab("", new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_settings_30px.png")), panelConfig); // NOI18N

        panelCajero.setLayout(new java.awt.GridBagLayout());

        labelTitleCajero.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        labelTitleCajero.setText("Herramientas para cajero");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weighty = 0.3;
        panelCajero.add(labelTitleCajero, gridBagConstraints);

        labelDescripCajero.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        labelDescripCajero.setText("Este es tu punto de inicio y de cierre para cada jornada laboral");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weighty = 0.2;
        panelCajero.add(labelDescripCajero, gridBagConstraints);

        btnAbrirCaja.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnAbrirCaja.setText("ABRIR CAJA");
        btnAbrirCaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrirCaja.setMaximumSize(new java.awt.Dimension(150, 50));
        btnAbrirCaja.setPreferredSize(new java.awt.Dimension(150, 50));
        btnAbrirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCajaActionPerformed(evt);
            }
        });
        contBtnAbrir.add(btnAbrirCaja);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        panelCajero.add(contBtnAbrir, gridBagConstraints);

        contBtnCerrarCaja.setLayout(new javax.swing.BoxLayout(contBtnCerrarCaja, javax.swing.BoxLayout.LINE_AXIS));

        btnCerrarCaja.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        btnCerrarCaja.setText("CERRAR CAJA");
        btnCerrarCaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrarCaja.setMaximumSize(new java.awt.Dimension(150, 50));
        btnCerrarCaja.setMinimumSize(new java.awt.Dimension(150, 50));
        btnCerrarCaja.setPreferredSize(new java.awt.Dimension(150, 50));
        btnCerrarCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarCajaActionPerformed(evt);
            }
        });
        contBtnCerrarCaja.add(btnCerrarCaja);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        panelCajero.add(contBtnCerrarCaja, gridBagConstraints);

        contEstadodeCaja.setLayout(new java.awt.GridBagLayout());

        labelPanelCajero1.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelPanelCajero1.setText("Estado de Caja:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(18, 11, 18, 11);
        contEstadodeCaja.add(labelPanelCajero1, gridBagConstraints);

        labelPanelCajero2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelPanelCajero2.setText("Monto inicial:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(18, 11, 18, 11);
        contEstadodeCaja.add(labelPanelCajero2, gridBagConstraints);

        fieldEstadoCaja.setEditable(false);
        fieldEstadoCaja.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        fieldEstadoCaja.setForeground(new java.awt.Color(204, 0, 0));
        fieldEstadoCaja.setText("Abierto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(18, 11, 18, 11);
        contEstadodeCaja.add(fieldEstadoCaja, gridBagConstraints);

        FieldFechaAperturaCaja.setEditable(false);
        FieldFechaAperturaCaja.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(18, 11, 18, 11);
        contEstadodeCaja.add(FieldFechaAperturaCaja, gridBagConstraints);

        fieldMontoInicialCaja.setEditable(false);
        fieldMontoInicialCaja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        fieldMontoInicialCaja.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(18, 11, 18, 11);
        contEstadodeCaja.add(fieldMontoInicialCaja, gridBagConstraints);

        labelPanelCajero4.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        labelPanelCajero4.setText("Fecha de Apertura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(18, 11, 18, 11);
        contEstadodeCaja.add(labelPanelCajero4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.weightx = 1.0;
        panelCajero.add(contEstadodeCaja, gridBagConstraints);

        tabsPanel.addTab("", new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_cash_counter_30px.png")), panelCajero); // NOI18N

        getContentPane().add(tabsPanel, java.awt.BorderLayout.CENTER);
        tabsPanel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewVentaActionPerformed
        // TODO add your handling code here:
        tabsPanel.setSelectedComponent(panelNuevaVenta);
        fieldCod.requestFocus();
    }//GEN-LAST:event_btnNewVentaActionPerformed

    private void btnProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnProductosMouseClicked

    private void btnNewVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewVentaMouseClicked
//        tabsPanel.setSelectedIndex(0);         

    }//GEN-LAST:event_btnNewVentaMouseClicked

    private void btnVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnVentasMouseClicked

    private void btnConfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnConfigMouseClicked

    private void btnNewVentaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewVentaMouseReleased
        // TODO add your handling code here:
        //tabsPanel.setSelectedIndex(0);   

    }//GEN-LAST:event_btnNewVentaMouseReleased

    private void btnProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseReleased
        // TODO add your handling code here:


    }//GEN-LAST:event_btnProductosMouseReleased

    private void btnVentasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentasMouseReleased

    private void btnConfigMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfigMouseReleased

    private void btnCerrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarVentaActionPerformed
        // TODO add your handling code here:
        modelo = (DefaultTableModel) jTableVentaItems.getModel();
        RegistrarVenta();
        RegistrarDetalle();
        ActualizarStock();
        LimpiarTable();
        fieldTotalPagar.setValue(null);
        fieldCod.requestFocus();
        MostrarTabVentas();
        MostrarTabArti();
    }//GEN-LAST:event_btnCerrarVentaActionPerformed

    private void jScrollPane4ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jScrollPane4ComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane4ComponentResized

    private void btnGuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProductoActionPerformed
        // TODO add your handling code here:
        int flag = 0;
        try {
            Integer idarticulo = null;
            ArticulosControl ac = new ArticulosControl();
            Integer precioVenta = null;
            Integer precioCosto = null;
            Integer stock = null;
            String Codigo = fieldT2Cod.getText();
            String descripcion = fieldT2Desc.getText();
            String stockStr = fieldT2Stock.getText();
            String precioCStr = fieldT2PrecioCosto.getText();
            String precioVStr = fieldT2PrecioVenta.getText();
            Integer categoria = null;
            Categorias cat = null;
            Boolean estado = true;
            Date date = TimestampToDate();

            if (jComboBox2Categorias.getSelectedIndex() != 0) {
                categoria = jComboBox2Categorias.getSelectedIndex();
                cat = new CategoriasControlador().buscarId(categoria);
            }

            if (stockStr != null) {
                stock = Integer.parseInt(stockStr);
            }
            if (precioCStr != null) {
                precioCosto = Integer.parseInt(precioCStr);
            }
            if (precioVStr != null) {
                precioVenta = Integer.parseInt(precioVStr);
            }

            Articulos a = null;
            a = new Articulos(idarticulo, Codigo, precioCosto, descripcion,
                    estado, precioVenta, stock, date, cat);
            ac.insertar(a);
            LimpiarProd();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error en la carga del articulo, por favor intente de nuevo\n" + e,
                    "Error en la carga", 2);
            flag = 1;
        }
        if (flag == 0) {
            JOptionPane.showMessageDialog(null, "Articulo guardado");
        }
        MostrarTabArti();
    }//GEN-LAST:event_btnGuardarProductoActionPerformed

    private void btnBorrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarProductoActionPerformed
        // TODO add your handling code here:
        try {
            if (!"".equals(fieldT2Id.getText())) {
                int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este producto?");
                if (pregunta == 0) {
                    int id = Integer.parseInt(fieldT2Id.getText());
                    ac.eliminarIdLogico(id);
                    LimpiarTable();
                    LimpiarProd();
                    MostrarTabArti();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBorrarProductoActionPerformed

    private void jTableArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableArticulosMouseClicked
        // TODO add your handling code here:
        int fila = jTableArticulos.rowAtPoint(evt.getPoint());
        fieldT2Id.setText(jTableArticulos.getValueAt(fila, 0).toString());
        fieldT2Cod.setText(jTableArticulos.getValueAt(fila, 1).toString());
        fieldT2Desc.setText(jTableArticulos.getValueAt(fila, 2).toString());
        fieldT2Stock.setText(jTableArticulos.getValueAt(fila, 3).toString());
        fieldT2PrecioCosto.setText(jTableArticulos.getValueAt(fila, 4).toString());
        fieldT2PrecioVenta.setText(jTableArticulos.getValueAt(fila, 5).toString());
        jComboBox2Categorias.setSelectedItem(jTableArticulos.getValueAt(fila, 6).toString());
    }//GEN-LAST:event_jTableArticulosMouseClicked

    private void btnActualizarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProductosActionPerformed
        // TODO add your handling code here:
        if (!"".equals(fieldT2Cod.getText())) {

            String[] options = new String[2];
            options[0] = "SÍ";
            options[1] = "NO";
            int pregunta = JOptionPane.showOptionDialog(getContentPane(), "¿Esta seguro de que quiere MODIFICAR este producto?", "Confirmar Modificación", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);

            //int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de Modificar este producto?");
            if (pregunta == 0) {

                try {
                    Integer idarticulo = Integer.parseInt(fieldT2Id.getText());
                    ArticulosControl ac = new ArticulosControl();
                    Integer precioVenta = Integer.parseInt(fieldT2PrecioVenta.getText());;
                    Integer precioCosto = Integer.parseInt(fieldT2PrecioCosto.getText());
                    Integer stock = Integer.parseInt(fieldT2Stock.getText());
                    String Codigo = fieldT2Cod.getText();
                    String descripcion = fieldT2Desc.getText();
                    Integer categoria = jComboBox2Categorias.getSelectedIndex();;
                    Categorias cat = new CategoriasControlador().buscarId(categoria);;
                    Boolean estado = true;
                    Date date = TimestampToDate();
                    if (!"".equals(fieldT2PrecioVenta.getText()) && !"".equals(fieldT2Cod.getText()) && !"".equals(fieldT2Stock.getText())
                            && !"".equals(fieldT2Desc.getText()) && null != cat) {
                        Articulos a = new Articulos(idarticulo, Codigo, precioCosto, descripcion,
                                estado, precioVenta, stock, date, cat);
                        ac.modificar(a);
                        LimpiarProd();
                        MostrarTabArti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Algunos de los campos obligatorios estan vacios",
                                "Error en la Actualizacion", 1);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Hubo un error en la Modificacion del articulo, "
                            + "por favor intente de nuevo\n" + e.getMessage(), "Error en la Modificacion", 2);
                }
            }
        }
    }//GEN-LAST:event_btnActualizarProductosActionPerformed

    private void fieldCodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldCodKeyPressed
        // TODO add your handling code here:
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!"".equals(fieldCod.getText())) {
                    String cod = fieldCod.getText();
                    Articulos a = ac.buscarCod(cod);
                    Integer stockTEMP = 0;
                    if (a.getDescripcion() != null) {
                        fieldId.setText(a.getIdarticulo() + "");
                        fieldDesc.setText(a.getDescripcion());
                        fieldPrecUnit.setText(a.getPrecioVenta() + "");
                        if(jTableVentaItems.getRowCount() > 0){
                            for (int i =0; i<jTableVentaItems.getRowCount(); i++){
                                System.out.println("Entro el for");
                                if (jTableVentaItems.getValueAt(i, 1).toString().equals(fieldId.getText())){
                                    stockTEMP++;
                                    System.out.println("stockTEMP = "+stockTEMP +"\n el valor extraido es: "+jTableVentaItems.getValueAt(i, 1));
                                }
                            }
                        }
                        Integer stock = a.getStock()-stockTEMP;
                        fieldStock.setText(stock.toString());
                        System.out.println("El stock temporal es: "+stock);
                        fieldCant.requestFocus();

                    } else {
                        limpiarVenta();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Escanee el codigo con el lector de codigo de barras o bien ingrese el id");
                    fieldCod.requestFocus();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un error al guardar el articulo en la venta:\n" + e.getMessage());
            fieldCod.setText("");
            fieldCod.requestFocus();
        }
    }//GEN-LAST:event_fieldCodKeyPressed

    private void limpiarVenta() {
        fieldCod.setText("");
        fieldId.setText("");
        fieldDesc.setText("");
        fieldCant.setText("");
        fieldPrecUnit.setText("");
        fieldPrecTotal.setText("");
        fieldStock.setText("");
        fieldCod.requestFocus();
    }

    private void fieldCantFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldCantFocusGained
        // TODO add your handling code here:
        //Agregue este if porque cuando se da clic en cantidad cuando todo el resto esta vacio entra en un loop, 
        //  y el error ya no se puede quitar...
        System.out.println("Field cod: " + fieldCod.getText() + "\nField id: " + fieldId.getText());
        if (fieldCod.getText().equals("") || fieldId.getText().equals("")) {
            //nada, xd, estan vacios los codigos
        } else {
            try {
                int stock = Integer.parseInt(fieldStock.getText());
                String cod = fieldCod.getText();
                int id = Integer.parseInt(fieldId.getText());
                String desc = fieldDesc.getText();
                fieldCant.setText("1");
                int cantidad = Integer.parseInt(fieldCant.getText());
                int precio = Integer.parseInt(fieldPrecUnit.getText());
                int total = (precio * cantidad);
                if (stock > 0) {
                    item++;
                    ArrayList lista = new ArrayList();
                    modelo = (DefaultTableModel) jTableVentaItems.getModel();
                    lista.add(item);
                    lista.add(id);
                    lista.add(cod);
                    lista.add(desc);
                    lista.add(cantidad);
                    lista.add(precio);
                    lista.add(total);
                    Object[] obj = new Object[7];
                    for (int i = 0; i < obj.length; i++) {
                        obj[i] = lista.get(i);
                    }
                    modelo.addRow(obj);
                    jTableVentaItems.setModel(modelo);
                    fieldPrecTotal.setText(total + "");
                    limpiarVenta();
                    totalPagar();
                } else {
                    JOptionPane.showMessageDialog(null, "El Stock es insuficiente");
                    limpiarVenta();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Eventfocus cant: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_fieldCantFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            modelo = (DefaultTableModel) jTableVentaItems.getModel();
            modelo.removeRow(jTableVentaItems.getSelectedRow());
            totalPagar();
            fieldCod.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ningun elemento seleccionado para remover\n" + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLimpiarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarProdActionPerformed
        // TODO add your handling code here:
        LimpiarProd();

    }//GEN-LAST:event_btnLimpiarProdActionPerformed

    private void fieldT2IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldT2IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldT2IdActionPerformed

    private void jLabel8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseReleased
        // TODO add your handling code here:
        boolean flag = false;
        try {
            String cat = JOptionPane.showInputDialog("Ingrese el nombre de la nueva Categoria");
            Integer id = null;
            Boolean estado = true;
            Date date = TimestampToDate();
            if (!"".equals(cat) && null != cat) {
                Categorias c = new Categorias(id, cat, estado, date);
                cc.insertar(c);
                flag = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en la carga de la nueva Categoria", 1);
        }
        if (flag) {
            JOptionPane.showMessageDialog(null, "Categoria agregada");
            MostrarComboCat();
        }

    }//GEN-LAST:event_jLabel8MouseReleased

    private void btnCancelarArqueoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarArqueoMouseReleased
        // TODO add your handling code here:
        System.out.println("Cancelado Cerrar Caja");
        fieldTotalMuestra.setValue(null);
        dialogCerrarCaja.dispose();
    }//GEN-LAST:event_btnCancelarArqueoMouseReleased

    private void btnCerrarCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCajaActionPerformed
        // TODO add your handling code here:
        dialogCerrarCaja.setVisible(true);

    }//GEN-LAST:event_btnCerrarCajaActionPerformed

    private void btnCancelarAbrirCajaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarAbrirCajaMouseReleased
        // TODO add your handling code here:
        System.out.println("Cancelado proceso Abrir caja");
        fieldSaldoInicAbrirCaja.setText("");
        dialogAbrirCaja.setVisible(false);
    }//GEN-LAST:event_btnCancelarAbrirCajaMouseReleased

    private void spin2milStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin2milStateChanged
        // TODO add your handling code here:
        totalMuestraCaja();

    }//GEN-LAST:event_spin2milStateChanged

    private void spin5milStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin5milStateChanged
        // TODO add your handling code here:
        totalMuestraCaja();
    }//GEN-LAST:event_spin5milStateChanged

    private void spin10milStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin10milStateChanged
        // TODO add your handling code here:
        totalMuestraCaja();
    }//GEN-LAST:event_spin10milStateChanged

    private void spin20milStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin20milStateChanged
        // TODO add your handling code here:
        totalMuestraCaja();
    }//GEN-LAST:event_spin20milStateChanged

    private void spin50milStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin50milStateChanged
        // TODO add your handling code here:
        totalMuestraCaja();
    }//GEN-LAST:event_spin50milStateChanged

    private void spin100milStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin100milStateChanged
        // TODO add your handling code here:
        totalMuestraCaja();
    }//GEN-LAST:event_spin100milStateChanged

    private void spin50gsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin50gsStateChanged
        // TODO add your handling code here:
        totalMuestraCaja();
    }//GEN-LAST:event_spin50gsStateChanged

    private void spin100gsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin100gsStateChanged
        // TODO add your handling code here:
        totalMuestraCaja();
    }//GEN-LAST:event_spin100gsStateChanged

    private void spin500gsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin500gsStateChanged
        // TODO add your handling code here:
        totalMuestraCaja();
    }//GEN-LAST:event_spin500gsStateChanged

    private void spin1000gsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin1000gsStateChanged
        // TODO add your handling code here:
        totalMuestraCaja();
    }//GEN-LAST:event_spin1000gsStateChanged

    private void fieldT2StockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldT2StockKeyTyped
        // TODO add your handling code here:
        esNumero(evt);
    }//GEN-LAST:event_fieldT2StockKeyTyped

    private void fieldT2PrecioCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldT2PrecioCostoKeyTyped
        // TODO add your handling code here:
        esNumero(evt);
    }//GEN-LAST:event_fieldT2PrecioCostoKeyTyped

    private void fieldSaldoInicAbrirCajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldSaldoInicAbrirCajaKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnConfirmarAbrirCaja.requestFocus();
        } else {
            esNumero(evt);
        }
    }//GEN-LAST:event_fieldSaldoInicAbrirCajaKeyTyped

    private void btnCerrarSesionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseReleased
        Login2 login = new Login2();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCerrarSesionMouseReleased

    private void btnCajeroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCajeroMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCajeroMouseReleased

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        // TODO add your handling code here:
        tabsPanel.setSelectedComponent(panelProductos);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        // TODO add your handling code here:
        tabsPanel.setSelectedComponent(panelVentas);

    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        // TODO add your handling code here:
        tabsPanel.setSelectedComponent(panelConfig);

    }//GEN-LAST:event_btnConfigActionPerformed

    private void btnCajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCajeroActionPerformed
        // TODO add your handling code here:
        tabsPanel.setSelectedComponent(panelCajero);

    }//GEN-LAST:event_btnCajeroActionPerformed

    private void btnAbrirCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCajaActionPerformed
        // TODO add your handling code here:
        dialogAbrirCaja.setVisible(true);
    }//GEN-LAST:event_btnAbrirCajaActionPerformed

    private void btnAbrirGestorArqueoAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirGestorArqueoAdminActionPerformed
        // TODO add your handling code here:
        dialogVerifArqueo.setVisible(true);
    }//GEN-LAST:event_btnAbrirGestorArqueoAdminActionPerformed

    private void btnGestionarUsuariosAdminMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGestionarUsuariosAdminMouseReleased
        // TODO add your handling code here:
        dialogAdmUsers.setVisible(true);
    }//GEN-LAST:event_btnGestionarUsuariosAdminMouseReleased

    private void jTableUsersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsersMousePressed
        // TODO add your handling code here:
        int fila = jTableUsers.rowAtPoint(evt.getPoint());
        fieldIdUser.setText(jTableUsers.getValueAt(fila, 0).toString());
        fieldNomUser.setText(jTableUsers.getValueAt(fila, 1).toString());
        fieldPassUser.setText(jTableUsers.getValueAt(fila, 2).toString());
        jComboBoxRolUsers.setSelectedItem(jTableUsers.getValueAt(fila, 3).toString());
        fieldEstadoUser.setText(jTableUsers.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_jTableUsersMousePressed

    private void btnAddUserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddUserMouseReleased
        // TODO add your handling code here:
        boolean flag = false;
        try {
            Usuarios u = null;
            Integer id = null;
            String nom = fieldNomUser.getText();
            String pass = fieldPassUser.getText();
            Integer fk_rol = null;
            if (jComboBoxRolUsers.getSelectedIndex() != 0) {
                fk_rol = jComboBoxRolUsers.getSelectedIndex();
            }
            boolean estado = true;
            Date fecha = TimestampToDate();
            if (nom.equals("") || nom.equals(null) || pass.equals("") || pass.equals(null) || fk_rol == 0) {
                JOptionPane.showMessageDialog(null, "No se pudo crear el nuevo usuario porque hay campos obligatorios sin valores asignados");
            } else {
                u = new Usuarios(id, nom, estado, pass, fk_rol, fecha);
                uc.insertar(u);
                flag = true;
            }
            LimpiarUser();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema en el registro de nuevo usuario\n" + e.toString());
            flag = false;
        }
        MostrarTabUser();
        if (flag) {
            JOptionPane.showMessageDialog(null, "Nuevo Usuario Registrado con Exito");
        }
    }//GEN-LAST:event_btnAddUserMouseReleased

    private void btnModUserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModUserMouseReleased
        // TODO add your handling code here:
        if (!"".equals(fieldIdUser.getText())) {

            String[] options = new String[2];
            options[0] = "SÍ";
            options[1] = "NO";
            int pregunta = JOptionPane.showOptionDialog(getContentPane(), "¿Esta seguro de que quiere MODIFICAR este USUARIO?", "Confirmar Modificación", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);

            //int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de Modificar este producto?");
            if (pregunta == 0) {

                try {
                    Integer id = Integer.parseInt(fieldIdUser.getText());
                    String nom = fieldNomUser.getText();
                    String pass = fieldPassUser.getText();
                    Boolean estado = null;
                    if (fieldEstadoUser.getText().equals("Activo")) {
                        estado = true;
                    } else {
                        estado = false;
                    }
                    Date fecha = TimestampToDate();
                    Integer rol = jComboBoxRolUsers.getSelectedIndex();
                    if (!"".equals(nom) && !"".equals(pass) && 0 != rol) {
                        Usuarios u = new Usuarios(id, nom, estado, pass, rol, fecha);
                        uc.modificar(u);
                        LimpiarUser();
                        MostrarTabUser();
                    } else {
                        JOptionPane.showMessageDialog(null, "Algunos de los campos obligatorios estan vacios",
                                "Error en la Actualizacion", 1);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Hubo un error en la Modificacion del Usuario, "
                            + "por favor intente de nuevo\n" + e.getMessage(), "Error en la Modificacion", 2);
                }
            } else {
                LimpiarUser();
            }
        }
    }//GEN-LAST:event_btnModUserMouseReleased

    private void btnDesactivarUserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesactivarUserMouseReleased
        // TODO add your handling code here:
        try {
            if (!"".equals(fieldIdUser.getText())) {
                String[] options = new String[2];
                options[0] = "SÍ";
                options[1] = "NO";
                int pregunta = JOptionPane.showOptionDialog(getContentPane(), "¿Esta seguro de que quiere DESACTIVAR a este USUARIO?",
                        "Confirmar Desactivacion", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
                if (pregunta == 0) {
                    int id = Integer.parseInt(fieldIdUser.getText());
                    uc.eliminarLogico(id);
                    LimpiarTable();
                    LimpiarUser();
                    MostrarTabUser();
                }
            } else {
                LimpiarUser();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnDesactivarUserMouseReleased

    private void btnReactivarUserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReactivarUserMouseReleased
        // TODO add your handling code here:
        try {
            if (!"".equals(fieldIdUser.getText())) {
                String[] options = new String[2];
                options[0] = "SÍ";
                options[1] = "NO";
                int pregunta = JOptionPane.showOptionDialog(getContentPane(), "¿Esta seguro de que quiere REACTIVAR a este USUARIO?",
                        "Confirmar Reactivacion", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
                if (pregunta == 0) {
                    int id = Integer.parseInt(fieldIdUser.getText());
                    uc.ReactivarUser(id);
                    LimpiarTable();
                    LimpiarUser();
                    MostrarTabUser();
                }
            } else {
                LimpiarUser();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnReactivarUserMouseReleased

    private void btnExportProducMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportProducMouseReleased
        try {
            Connection con = JdbcDAOFactory.obtenerConeccion();
            JasperReport reporte = JasperCompileManager.compileReport("C://Program Files (x86)/cantina_anglo/Inventario.jrxml");
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, null, con);
            JasperViewer.viewReport(imprimir, false);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_btnExportProducMouseReleased

    private void btnConfirmarAbrirCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarAbrirCajaActionPerformed
        // TODO add your handling code here:
        try {
            Integer id = null;
            Integer fkCaja = 1;
            Integer fkUsuario = uc.buscarPorNombre(fieldUserName.getText()).getIdusuario();
            Date fechaInicio = TimestampToDate();
            Date fechaFin = null;
            Integer montoinicial = Integer.parseInt(fieldSaldoInicAbrirCaja.getValue().toString());
            Integer montofinal = null;
            Integer totalventas = null;
            boolean confirmado = false;
            Integer fk_admin = null;
            boolean estado = true;
            Arqueoscaja aqc = new Arqueoscaja(id, fkCaja, fkUsuario, fechaInicio, 
                    fechaFin, montoinicial, montofinal, totalventas, confirmado,fk_admin ,estado);
            aqcontrol.insertar(aqc);
            abrirCaja(); // Este metodo se encarga de desactivar el boton Abrir y habilitar venta
            dialogAbrirCaja.setVisible(false);
            MostrarTabArqueo();
            JOptionPane.showMessageDialog(null, "Caja abierta con exito\nMonto: " + fieldSaldoInicAbrirCaja.getText() + " Gs");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo el siguiente error\n" + e.toString(), "Error en btn Abrir Caja", 0);
        }
        fieldSaldoInicAbrirCaja.setText("");
    }//GEN-LAST:event_btnConfirmarAbrirCajaActionPerformed

    private void btnRemitirArqueoMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemitirArqueoMuestraActionPerformed
        // TODO add your handling code here:
        try {
            Arqueoscaja aqc = aqcontrol.UltimoElemento();
            String userActual = fieldUserName.getText();
            String userCaja = uc.buscarIdINT(aqc.getFkUsuario()).getNombre();
            if (userCaja.equals(userActual)) {
                Date fechaFin = TimestampToDate();
                Integer montofinal = Integer.parseInt(fieldTotalMuestra.getValue().toString());
                Integer totalventas = vc.TotalVentasPArqueo();
                aqc.setFechaFin(fechaFin);
                aqc.setMontoFinal(montofinal);
                aqc.setTotalVentas(totalventas);
                aqcontrol.modificar(aqc);
                dialogCerrarCaja.dispose();
                JOptionPane.showMessageDialog(null, "Valor de arqueo remitido: " + fieldTotalMuestra.getText() + " Gs");
                cerrarCaja();
                FieldFechaAperturaCaja.setText("-");
                MostrarTabArqueo();
            } else {
                JOptionPane.showMessageDialog(null, "El usuario que haga el arqueo, debe ser el mismo que hizo la apertura\n"
                        + "El usuario que abrio la caja: " + userCaja
                        + "\nEl usuario que intenta cerrar: " + userActual, "Error en el cierre de caja", 2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo el siguiente error\n" + e.toString());
        }
        fieldTotalMuestra.setText("");
    }//GEN-LAST:event_btnRemitirArqueoMuestraActionPerformed

    private void fieldTotalMuestraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldTotalMuestraKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_fieldTotalMuestraKeyPressed

    private void fieldTotalMuestraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldTotalMuestraKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            btnRemitirArqueoMuestra.requestFocus();
        } else {
            esNumero(evt);
        }
    }//GEN-LAST:event_fieldTotalMuestraKeyTyped

    private void fieldTotalMuestraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldTotalMuestraKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnRemitirArqueoMuestra.requestFocus();
        }
    }//GEN-LAST:event_fieldTotalMuestraKeyReleased

    private void fieldSaldoInicAbrirCajaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldSaldoInicAbrirCajaKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnConfirmarAbrirCaja.requestFocus();
        }
    }//GEN-LAST:event_fieldSaldoInicAbrirCajaKeyReleased

    private void btnResumenVentasDiarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResumenVentasDiarioMouseReleased
        // TODO add your handling code here:
        Timestamp fecha_fin = new Timestamp(System.currentTimeMillis());
        int dia = fecha_fin.getDate();
        int anho = fecha_fin.getYear();
        int mes = fecha_fin.getMonth();
        Timestamp fecha_ini = new Timestamp(anho, mes, dia, 0, 0, 0, 0);

        try {
            Connection con = JdbcDAOFactory.obtenerConeccion();
            Map parametros = new HashMap();
            parametros.put("fecha_ini", fecha_ini);
            parametros.put("fecha_fin", fecha_fin);
            JasperReport reporte = JasperCompileManager.compileReport("C://Program Files (x86)/cantina_anglo/Informe_ven.jrxml");
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, con);
            JasperViewer.viewReport(imprimir, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_btnResumenVentasDiarioMouseReleased

    private void btnResumenVentaSieteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResumenVentaSieteMouseReleased
        // TODO add your handling code here:
        Timestamp fecha_fin = new Timestamp(System.currentTimeMillis());
        Timestamp fecha_ini = new Timestamp(System.currentTimeMillis() - 604800000);

        try {
            Connection con = JdbcDAOFactory.obtenerConeccion();
            Map parametros = new HashMap();
            parametros.put("fecha_ini", fecha_ini);
            parametros.put("fecha_fin", fecha_fin);
            JasperReport reporte = JasperCompileManager.compileReport("C://Program Files (x86)/cantina_anglo/Informe_ven.jrxml");
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, con);
            JasperViewer.viewReport(imprimir, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_btnResumenVentaSieteMouseReleased

    private void btnResumenVentaMesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResumenVentaMesMouseReleased
        // TODO add your handling code here:
        Timestamp fecha_fin = new Timestamp(System.currentTimeMillis());
        int anho = fecha_fin.getYear();
        int mes = fecha_fin.getMonth();
        Timestamp fecha_ini = new Timestamp(anho, mes, 1, 0, 0, 0, 0);

        try {
            Connection con = JdbcDAOFactory.obtenerConeccion();
            Map parametros = new HashMap();
            parametros.put("fecha_ini", fecha_ini);
            parametros.put("fecha_fin", fecha_fin);
            JasperReport reporte = JasperCompileManager.compileReport("C://Program Files (x86)/cantina_anglo/Informe_ven.jrxml");
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, con);
            JasperViewer.viewReport(imprimir, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_btnResumenVentaMesMouseReleased

    private void btnResumenVentaAnhoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResumenVentaAnhoMouseReleased
        // TODO add your handling code here:
        Timestamp fecha_fin = new Timestamp(System.currentTimeMillis());
        int anho = fecha_fin.getYear();
        Timestamp fecha_ini = new Timestamp(anho, 0, 1, 0, 0, 0, 0);

        try {
            Connection con = JdbcDAOFactory.obtenerConeccion();
            Map parametros = new HashMap();
            parametros.put("fecha_ini", fecha_ini);
            parametros.put("fecha_fin", fecha_fin);
            JasperReport reporte = JasperCompileManager.compileReport("C://Program Files (x86)/cantina_anglo/Informe_ven.jrxml");
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, con);
            JasperViewer.viewReport(imprimir, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_btnResumenVentaAnhoMouseReleased

    private void fieldArqMontoFinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldArqMontoFinalKeyTyped
        // TODO add your handling code here
        esNumero(evt);

    }//GEN-LAST:event_fieldArqMontoFinalKeyTyped

    private void btnConfirmArqueoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmArqueoActionPerformed
        // TODO add your handling code here:
        String[] opciones = new String[]{"Sí, Confirmar", "Cancelar"};
        int answer = JOptionPane.showOptionDialog(null,
                "Está seguro de que confirma el siguiente valor?\n" + fieldArqMontoFinal.getText() + " Gs", "Elegir modo de priviegio",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]);
        if (answer == 0) {
            try {
                Integer mntSis =ArqueoSistema();
                Integer mntConfirmado = (Integer) fieldArqMontoFinal.getValue();
                Arqueoscaja aq = aqcontrol.UltimoElemento();
                aq.setConfirmado(true);
                Integer fk_admin = uc.buscarPorNombre(fieldUserName.getText()).getIdusuario();
                aq.setFkAdmin(fk_admin);
                if (!fieldArqMontoFinal.equals(fieldArqValorRemitido)) {
                    aq.setMontoFinal(Integer.parseInt(fieldArqMontoFinal.getValue().toString()));
                }
                if(!Objects.equals(mntConfirmado, mntSis)){
                    InsertarAjuste(mntConfirmado,mntSis);
                    MostrarTabVentas();
                }
                aqcontrol.modificar(aq);
                dialogVerifArqueo.dispose();
                JOptionPane.showMessageDialog(null, "Ajuste de arqueo completado");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "btnConfirmarArqueo: \n" + e.toString());
            }
            cerrarCaja();
            MostrarTabArqueo();
        } else {
            fieldArqMontoFinal.requestFocus();
        }
        //

    }//GEN-LAST:event_btnConfirmArqueoActionPerformed

    private void fieldArqMontoFinalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldArqMontoFinalKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnConfirmArqueo.requestFocus();
        }
    }//GEN-LAST:event_fieldArqMontoFinalKeyReleased

    private void btnDialogHistorialArqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDialogHistorialArqActionPerformed
        // TODO add your handling code here:
        dialogHistorialArqueo.setVisible(true);
    }//GEN-LAST:event_btnDialogHistorialArqActionPerformed

    private void esNumero(java.awt.event.KeyEvent evt) {
        //Rechaza el tecleo si no es un Numero
        char TestChar = evt.getKeyChar();
        if (!(Character.isDigit(TestChar))) {
            evt.consume();
        }
    }

    private void esLetra(java.awt.event.KeyEvent evt) {
        //Rechaza el tecleo si no es una Letra 
        //ACTUALMENTE NO ESTA EN USO ESTE METODO, PERO POR SI ACASO
        char TestChar = evt.getKeyChar();
        if (!(Character.isAlphabetic(TestChar))) {
            evt.consume();
        }
    }

    private static boolean isMaximized(int state) {
        return (state & MAXIMIZED_BOTH) == MAXIMIZED_BOTH;
    }

    private int totalMuestraCaja() {
        int total = 0;
        total += (Integer) spin50gs.getValue() * 50;
        total += (Integer) spin100gs.getValue() * 100;
        total += (Integer) spin500gs.getValue() * 500;
        total += (Integer) spin1000gs.getValue() * 1000;
        total += (Integer) spin2mil.getValue() * 2000;
        total += (Integer) spin5mil.getValue() * 5000;
        total += (Integer) spin10mil.getValue() * 10000;
        total += (Integer) spin20mil.getValue() * 20000;
        total += (Integer) spin50mil.getValue() * 50000;
        total += (Integer) spin100mil.getValue() * 100000;
        fieldTotalMuestra.setValue(total);

        return total;
    }

    private void LimpiarProd() {
        fieldT2Id.setText("");
        fieldT2Cod.setText("");
        fieldT2Desc.setText("");
        fieldT2Stock.setText("");
        fieldT2PrecioCosto.setText("");
        fieldT2PrecioVenta.setText("");
        jComboBox2Categorias.setSelectedIndex(0);
    }

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
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPage(0).setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField FieldFechaAperturaCaja;
    private javax.swing.JButton btnAbrirCaja;
    private javax.swing.JButton btnAbrirGestorArqueoAdmin;
    private javax.swing.JButton btnActualizarProductos;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnBorrarProducto;
    private javax.swing.JButton btnCajero;
    private javax.swing.JButton btnCancelarAbrirCaja;
    private javax.swing.JButton btnCancelarArqueo;
    private javax.swing.JButton btnCerrarCaja;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCerrarVenta;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnConfirmArqueo;
    private javax.swing.JButton btnConfirmarAbrirCaja;
    private javax.swing.JButton btnDesactivarUser;
    private javax.swing.JButton btnDialogHistorialArq;
    private javax.swing.JButton btnExportProduc;
    private javax.swing.JButton btnGestionarUsuariosAdmin;
    private javax.swing.JButton btnGuardarProducto;
    private javax.swing.JButton btnLimpiarProd;
    private javax.swing.JButton btnModUser;
    private javax.swing.JButton btnNewVenta;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnReactivarUser;
    private javax.swing.JButton btnRemitirArqueoMuestra;
    private javax.swing.JButton btnResumenVentaAnho;
    private javax.swing.JButton btnResumenVentaMes;
    private javax.swing.JButton btnResumenVentaSiete;
    private javax.swing.JButton btnResumenVentasDiario;
    private javax.swing.JButton btnVentas;
    private javax.swing.JPanel contAdmUser;
    private javax.swing.JPanel contAdmUsr1;
    private javax.swing.JPanel contBilletes;
    private javax.swing.JPanel contBilletesCount;
    private javax.swing.JPanel contBtnAbrir;
    private javax.swing.JPanel contBtnCerrarCaja;
    private javax.swing.JPanel contBtnProductos;
    private javax.swing.JPanel contBtnVentas;
    private javax.swing.JPanel contCierreVenta;
    private javax.swing.JPanel contDiagHistTitle;
    private javax.swing.JPanel contEstadodeCaja;
    private javax.swing.JPanel contGestArq;
    private javax.swing.JPanel contInsertProd;
    private javax.swing.JPanel contMainVenta;
    private javax.swing.JPanel contMonedas;
    private javax.swing.JPanel contMonedasCount;
    private javax.swing.JPanel contPanelEntrada;
    private javax.swing.JPanel contSidebarAdmUser;
    private javax.swing.JPanel contSidebarButtons;
    private javax.swing.JPanel contSidebarT2;
    private javax.swing.JPanel contTituloBilletes;
    private javax.swing.JPanel contTituloMonedas;
    private javax.swing.JPanel contTotal;
    private javax.swing.JPanel contValoresApertura;
    private javax.swing.JPanel contValoresCierre;
    private javax.swing.JPanel contenedorTablat2;
    private javax.swing.JDialog dialogAbrirCaja;
    private javax.swing.JDialog dialogAdmUsers;
    private javax.swing.JDialog dialogCerrarCaja;
    private javax.swing.JDialog dialogHistorialArqueo;
    private javax.swing.JDialog dialogVerifArqueo;
    private javax.swing.JTextField fieldArqEstadoCaja;
    private javax.swing.JFormattedTextField fieldArqFechaRemision;
    private javax.swing.JFormattedTextField fieldArqMontoAper;
    private javax.swing.JFormattedTextField fieldArqMontoFinal;
    private javax.swing.JFormattedTextField fieldArqTotalIngresos;
    private javax.swing.JFormattedTextField fieldArqTotalSistema;
    private javax.swing.JFormattedTextField fieldArqValorRemitido;
    private javax.swing.JTextField fieldCant;
    private javax.swing.JTextField fieldCod;
    private javax.swing.JTextField fieldDesc;
    private javax.swing.JTextField fieldEstadoCaja;
    private javax.swing.JTextField fieldEstadoUser;
    private javax.swing.JTextField fieldId;
    private javax.swing.JTextField fieldIdUser;
    private javax.swing.JFormattedTextField fieldMontoInicialCaja;
    private javax.swing.JTextField fieldNomUser;
    private javax.swing.JTextField fieldPassUser;
    private javax.swing.JTextField fieldPrecTotal;
    private javax.swing.JTextField fieldPrecUnit;
    private javax.swing.JFormattedTextField fieldSaldoInicAbrirCaja;
    private javax.swing.JTextField fieldStock;
    private javax.swing.JTextField fieldT2Cod;
    private javax.swing.JTextField fieldT2Desc;
    private javax.swing.JTextField fieldT2Id;
    private javax.swing.JTextField fieldT2PrecioCosto;
    private javax.swing.JTextField fieldT2PrecioVenta;
    private javax.swing.JTextField fieldT2Stock;
    private javax.swing.JFormattedTextField fieldTotalMuestra;
    private javax.swing.JFormattedTextField fieldTotalPagar;
    private javax.swing.JTextField fieldUserName;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox2Categorias;
    private javax.swing.JComboBox<String> jComboBoxRolUsers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTableArticulos;
    private javax.swing.JTable jTableUsers;
    private javax.swing.JTable jTableVentaItems;
    private javax.swing.JTable jTableVentas;
    private javax.swing.JLabel labCod;
    private javax.swing.JLabel labT2Categorias;
    private javax.swing.JLabel labT2Cod;
    private javax.swing.JLabel labT2Cod1;
    private javax.swing.JLabel labT2Desc;
    private javax.swing.JLabel labT2PrecioCosto;
    private javax.swing.JLabel labT2PrecioVenta;
    private javax.swing.JLabel labT2StockDisp;
    private javax.swing.JLabel labelAD1;
    private javax.swing.JLabel labelAD2;
    private javax.swing.JLabel labelAD3;
    private javax.swing.JLabel labelAD4;
    private javax.swing.JLabel labelAD5;
    private javax.swing.JLabel labelAD6;
    private javax.swing.JLabel labelAcercaDE;
    private javax.swing.JLabel labelDescripCajero;
    private javax.swing.JLabel labelGestArq1;
    private javax.swing.JLabel labelGestArq2;
    private javax.swing.JLabel labelGestArq3;
    private javax.swing.JLabel labelGestArq4;
    private javax.swing.JLabel labelGestArq5;
    private javax.swing.JLabel labelGestArq6;
    private javax.swing.JLabel labelGestArq8;
    private javax.swing.JLabel labelHistArqs;
    private javax.swing.JLabel labelMontoInicial;
    private javax.swing.JLabel labelPanelCajero1;
    private javax.swing.JLabel labelPanelCajero2;
    private javax.swing.JLabel labelPanelCajero4;
    private javax.swing.JLabel labelRemitSaldo1;
    private javax.swing.JLabel labelRemitSaldo2;
    private javax.swing.JLabel labelRemitSaldo3;
    private javax.swing.JLabel labelResumenVentas;
    private javax.swing.JLabel labelSaldoInicial;
    private javax.swing.JLabel labelTitleCajero;
    private javax.swing.JLabel labelTitleGestArq;
    private javax.swing.JLabel labelTotalVentas;
    private javax.swing.JPanel logoCont;
    private javax.swing.JLabel mainLogo;
    private javax.swing.JLabel mainTitle;
    private javax.swing.JPanel panelCajero;
    private javax.swing.JPanel panelConfig;
    private javax.swing.JPanel panelContAcercaDe;
    private javax.swing.JPanel panelContConfig;
    private javax.swing.JPanel panelContEntradaProductos;
    private javax.swing.JPanel panelNuevaVenta;
    private javax.swing.JPanel panelProductos;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JScrollPane scroll1AdmUsr;
    private javax.swing.JScrollPane scroll2AdmUsr;
    private javax.swing.JScrollPane scrollHistArq;
    private javax.swing.JSeparator separatorAD;
    private javax.swing.JSeparator separatorAD1;
    private javax.swing.JPanel sidebarMain;
    private javax.swing.JSpinner spin1000gs;
    private javax.swing.JSpinner spin100gs;
    private javax.swing.JSpinner spin100mil;
    private javax.swing.JSpinner spin10mil;
    private javax.swing.JSpinner spin20mil;
    private javax.swing.JSpinner spin2mil;
    private javax.swing.JSpinner spin500gs;
    private javax.swing.JSpinner spin50gs;
    private javax.swing.JSpinner spin50mil;
    private javax.swing.JSpinner spin5mil;
    private javax.swing.JTable tableHistArq;
    private javax.swing.JTabbedPane tabsPanel;
    // End of variables declaration//GEN-END:variables

    private void totalPagar() {
        totalPagar = 0;
        int numFila = jTableVentaItems.getRowCount();
        for (int i = 0; i < numFila; i++) {
            int cal = Integer.parseInt(String.valueOf(jTableVentaItems.getModel().getValueAt(i, 6)));
            totalPagar += cal;
        }
        fieldTotalPagar.setValue(totalPagar);
    }

    private Date TimestampToDate() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = new Date(ts.getTime());
        return date;
    }

    public void setUser(String nombre) {
        fieldUserName.setText(nombre);
    }

    public void RegistrarVenta() {
        try {
            Integer id = null;
            Boolean estado = true;
            Integer idcaja = 1;
            String nomUser = fieldUserName.getText();
            Usuarios user = uc.buscarPorNombre(nomUser);
            Integer monto = Integer.parseInt(fieldTotalPagar.getValue().toString());
            Date fecha = TimestampToDate();
            Boolean ajuste = false;
            Ventas ven = new Ventas(id, idcaja, fecha, monto, estado,ajuste, user);
            if (monto != 0 && monto != null) {
                vc.insertar(ven);
            } else {
                JOptionPane.showMessageDialog(null, "La venta no se puede realizar sin items");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la venta\n" + e.toString());
        }
    }

    private void RegistrarDetalle() {
        try {
            DetalleVenta dv = null;
            Integer iddv = null;
            Integer descuento = null;
            Date fecha = TimestampToDate();
            boolean estado = true;
            Integer idven = vc.buscarMaxId();
            Ventas fkVenta = vc.buscar(idven);
            for (int i = 0; i < jTableVentaItems.getRowCount(); i++) {
                Integer id = Integer.parseInt(jTableVentaItems.getValueAt(i, 1).toString());
                Integer cant = Integer.parseInt(jTableVentaItems.getValueAt(i, 4).toString());
                Integer precio = Integer.parseInt(jTableVentaItems.getValueAt(i, 5).toString());
                Articulos fkArticulos = ac.buscarId(id);
                dv = new DetalleVenta(iddv, fkArticulos, fkVenta, cant, precio, descuento, estado, fecha);
                dvc.insertar(dv);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la carga de la lista de articulos de venta:\n" + e.toString());
        }
    }

    private void ActualizarStock() {
        try {
            for (int i = 0; i < jTableVentaItems.getRowCount(); i++) {
                Integer id = Integer.parseInt(jTableVentaItems.getValueAt(i, 1).toString());
                Integer cant = Integer.parseInt(jTableVentaItems.getValueAt(i, 4).toString());
                Articulos arti = ac.buscarId(id);
                Integer stock_actual = arti.getStock() - cant;
                ac.ActualizarStock(stock_actual, id);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la actualizacion del stock\n" + e.toString());
        }
    }

    /*
    ****************************************************************************************************************
                            METODOS PARA CIERRE Y APERTURA DE CAJA
    ****************************************************************************************************************

     */
    private boolean CajaCerrada() {
        Boolean retorno = null;
        try {
            Arqueoscaja aq = aqcontrol.UltimoElemento();
            Date cajaCerrada = aq.getFechaFin();
            if (cajaCerrada == null) {//si esta nulo es pq no tiene una fecha de fin, por lo tanto no se cerro
                retorno = false;
            } else {
                retorno = true;//si no es nulo es pq tiene fecha de fin, o sea se cerro
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return retorno;//Aca recien se retorna por el tema del try-catch
    }

    private boolean cajaConfirmada() {
        Boolean retorno = null;
        try {
            Arqueoscaja aq = aqcontrol.UltimoElemento();
            Date cajaCerrada = aq.getFechaFin();
            if (cajaCerrada == null) {//si esta nulo es pq no tiene una fecha de fin, por lo tanto no se cerro
                retorno = false;
            } else {
                retorno = aq.getConfirmado();
                //System.out.println("estado de caja: "+ retorno.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return retorno;//Aca recien se retorna por el tema del try-catch
    }

    private int montoInicCaja() {
        int retorno = 0;
        try {
            Arqueoscaja aq = aqcontrol.UltimoElemento();
//            Date cajaCerrada = aq.getFechaFin();
            retorno = aq.getMontoInicial();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return retorno;//Aca recien se retorna por el tema del try-catch
    }

    private String fechaInicialCaja() {
        String formatFecha = null;
        
        try {
            Arqueoscaja aq = aqcontrol.UltimoElemento();
            Date fechaInicioCaja = aq.getFechaInicio();
            
            formatFecha = prettyFechaHora(fechaInicioCaja);
            
                    //recuerdos del metodo anterior, antes de crear una funcion
            //DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);        
            //LocalDateTime ldate = fechaInicioCaja.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); 
            //formatFecha = ldate.format(formatter) + " a las " + ldate.toLocalTime();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return formatFecha;
    }

    private String fechaCierreCaja() {
        String formatFecha = null;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        try {
            Arqueoscaja aqc = aqcontrol.UltimoElemento();
            Date fechaCierreCaja = aqc.getFechaFin();
            LocalDateTime localdate = fechaCierreCaja.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            formatFecha = localdate.format(formatter1) + " a las " + localdate.toLocalTime();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "fechaCierreCaja() " + e.toString());
        }
        return formatFecha;

    }

    private Integer montoRemitido() {
        Integer remitido = 0;
        try {
            Arqueoscaja aq = aqcontrol.UltimoElemento();
            remitido = aq.getMontoFinal();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return remitido;
    }

    private Integer ArqueoSistema() {
        Arqueoscaja aq = aqcontrol.UltimoElemento();
        Integer total = null;
        Date cajaAbierta = null;
        total = aq.getMontoInicial() == null ? 0 : aq.getMontoInicial();//Operador Ternario
        try {
            Date cajaCerrada = aq.getFechaFin();
            if (cajaCerrada != null) {
                cajaAbierta = aq.getFechaInicio();
                Integer suma = vc.SumTotalVenArqueo(cajaAbierta, cajaCerrada);
                total = Integer.sum(total, suma);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El error fue: " + e.toString());
        }
        if (total == null) {
            total = 0;
            return total;
        } else {
            return total;
        }

    }

    private void LimpiarUser() {
        fieldIdUser.setText("");
        fieldNomUser.setText("");
        fieldPassUser.setText("");
        fieldEstadoUser.setText("");
        jComboBoxRolUsers.setSelectedIndex(0);
    }
    
    private void InsertarAjuste(Integer mntConfirmado, Integer mntSis){
        try{
            //Calculo de la venta "ajuste"
            Integer mntAjuste = mntConfirmado - mntSis;//Este vendria siendo el total en el constructor
            //Resto de los parametros
            Integer id = null;
            Boolean estado = true;
            Integer idcaja = 1;
            String nomUser = fieldUserName.getText();
            Usuarios user = uc.buscarPorNombre(nomUser);
            Date fecha = TimestampToDate();
            Boolean ajuste = true;//Campo que indica que es un ajuste
            Ventas ven = new Ventas(id, idcaja, fecha, mntAjuste, estado,ajuste, user);
            vc.insertar(ven);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en insercion de ajuste: \n"+e.toString());
        }
        
    }
}
