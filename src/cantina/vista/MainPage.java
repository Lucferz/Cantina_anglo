/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.vista;

/**
 *
 * @author danie
 */
public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    public MainPage() {
        initComponents();
        setLocationRelativeTo(null);
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarMain = new javax.swing.JPanel();
        btnNewVenta = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnInformes = new javax.swing.JButton();
        logoCont = new javax.swing.JPanel();
        mainLogo = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        mainTitle = new javax.swing.JLabel();
        tabsPanel = new javax.swing.JTabbedPane();
        panelNuevaVenta = new javax.swing.JPanel();
        contMainVenta = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        contPanelEntrada = new javax.swing.JPanel();
        labCod = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        fieldDesc = new javax.swing.JTextField();
        fieldCant = new javax.swing.JTextField();
        fieldPrecUnit = new javax.swing.JTextField();
        fieldPrecTotal = new javax.swing.JTextField();
        fieldStock = new javax.swing.JTextField();
        fieldCod = new javax.swing.JTextField();
        contCierreVenta = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fieldTotalPagar = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        panelProductos = new javax.swing.JPanel();
        panelContEntradaProductos = new javax.swing.JPanel();
        labT2Cod = new javax.swing.JLabel();
        labT2Desc = new javax.swing.JLabel();
        labT2Cant = new javax.swing.JLabel();
        labT2Precio = new javax.swing.JLabel();
        fieldT2Cod = new javax.swing.JTextField();
        fieldT2Desc = new javax.swing.JTextField();
        fieldT2Cant = new javax.swing.JTextField();
        fieldT2Precio = new javax.swing.JTextField();
        btnGuardarProducto = new javax.swing.JButton();
        btnRefreshProductos = new javax.swing.JButton();
        btnBorrarProducto = new javax.swing.JButton();
        btnAddProducto = new javax.swing.JButton();
        btnExportProduc = new javax.swing.JButton();
        contenedorTablat2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        panelVentas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnGenerarResumenVentas = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidebarMain.setBackground(new java.awt.Color(103, 226, 109));

        btnNewVenta.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnNewVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_receive_cash_30px.png"))); // NOI18N
        btnNewVenta.setText("Nueva Venta");
        btnNewVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewVentaActionPerformed(evt);
            }
        });

        btnProductos.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_empty_box_30px.png"))); // NOI18N
        btnProductos.setText("Productos");

        btnConfig.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_settings_30px.png"))); // NOI18N
        btnConfig.setText("Configuración");

        btnVentas.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_list_30px.png"))); // NOI18N
        btnVentas.setText("Ventas");

        btnInformes.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_upload_link_document_30px.png"))); // NOI18N
        btnInformes.setText("Informes");

        logoCont.setBackground(new java.awt.Color(103, 226, 109));
        logoCont.setPreferredSize(new java.awt.Dimension(120, 120));
        logoCont.setLayout(new java.awt.GridLayout(1, 0));

        mainLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_online_shopping_100px_1.png"))); // NOI18N
        logoCont.add(mainLogo);

        javax.swing.GroupLayout sidebarMainLayout = new javax.swing.GroupLayout(sidebarMain);
        sidebarMain.setLayout(sidebarMainLayout);
        sidebarMainLayout.setHorizontalGroup(
            sidebarMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNewVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInformes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logoCont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidebarMainLayout.setVerticalGroup(
            sidebarMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarMainLayout.createSequentialGroup()
                .addComponent(logoCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnNewVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInformes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        getContentPane().add(sidebarMain, java.awt.BorderLayout.WEST);

        header.setBackground(new java.awt.Color(103, 226, 109));

        mainTitle.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        mainTitle.setForeground(new java.awt.Color(255, 255, 255));
        mainTitle.setText("CANTINA ANGLO");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(mainTitle)
                .addContainerGap(1019, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainTitle)
                .addGap(20, 20, 20))
        );

        getContentPane().add(header, java.awt.BorderLayout.NORTH);

        tabsPanel.setTabPlacement(javax.swing.JTabbedPane.RIGHT);

        panelNuevaVenta.setBackground(new java.awt.Color(103, 226, 109));
        panelNuevaVenta.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        labCod.setText("Código");

        jLabel2.setText("Descripción");

        jLabel3.setText("Cantidad");

        jLabel4.setText("Precio Unitario");

        jLabel5.setText("Stock Disponible");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_clear_symbol_30px.png"))); // NOI18N

        jLabel6.setText("Prec. Total");

        javax.swing.GroupLayout contPanelEntradaLayout = new javax.swing.GroupLayout(contPanelEntrada);
        contPanelEntrada.setLayout(contPanelEntradaLayout);
        contPanelEntradaLayout.setHorizontalGroup(
            contPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contPanelEntradaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(contPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labCod)
                    .addComponent(fieldCod, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(fieldDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(contPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(fieldCant, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(contPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(fieldPrecUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(contPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(fieldPrecTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(contPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contPanelEntradaLayout.setVerticalGroup(
            contPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contPanelEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(contPanelEntradaLayout.createSequentialGroup()
                        .addGroup(contPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labCod)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(contPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldPrecUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldPrecTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setText("Total a Pagar");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_request_money_30px.png"))); // NOI18N
        jButton2.setText("Cerrar Venta");

        javax.swing.GroupLayout contCierreVentaLayout = new javax.swing.GroupLayout(contCierreVenta);
        contCierreVenta.setLayout(contCierreVentaLayout);
        contCierreVentaLayout.setHorizontalGroup(
            contCierreVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contCierreVentaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(50, 50, 50)
                .addGroup(contCierreVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fieldTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(115, 115, 115))
        );
        contCierreVentaLayout.setVerticalGroup(
            contCierreVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contCierreVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contCierreVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(fieldTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout contMainVentaLayout = new javax.swing.GroupLayout(contMainVenta);
        contMainVenta.setLayout(contMainVentaLayout);
        contMainVentaLayout.setHorizontalGroup(
            contMainVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contMainVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contMainVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contCierreVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contPanelEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(contMainVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contMainVentaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        contMainVentaLayout.setVerticalGroup(
            contMainVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contMainVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contPanelEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245)
                .addComponent(contCierreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
            .addGroup(contMainVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contMainVentaLayout.createSequentialGroup()
                    .addGap(105, 105, 105)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(297, Short.MAX_VALUE)))
        );

        panelNuevaVenta.add(contMainVenta, java.awt.BorderLayout.CENTER);

        tabsPanel.addTab("tab1", panelNuevaVenta);

        labT2Cod.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        labT2Cod.setText("Código");

        labT2Desc.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        labT2Desc.setText("Descripción");

        labT2Cant.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        labT2Cant.setText("Cantidad");

        labT2Precio.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        labT2Precio.setText("Precio");

        btnGuardarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_save_as_30px_2.png"))); // NOI18N
        btnGuardarProducto.setText("GUARDAR");

        btnRefreshProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_refresh_30px.png"))); // NOI18N
        btnRefreshProductos.setText("ACTUALIZAR");

        btnBorrarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_eraser_30px.png"))); // NOI18N
        btnBorrarProducto.setText("BORRAR");

        btnAddProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_add_30px.png"))); // NOI18N
        btnAddProducto.setText("AGREGAR");

        btnExportProduc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_spreadsheet_file_30px.png"))); // NOI18N
        btnExportProduc.setText("EXPORTAR");

        javax.swing.GroupLayout panelContEntradaProductosLayout = new javax.swing.GroupLayout(panelContEntradaProductos);
        panelContEntradaProductos.setLayout(panelContEntradaProductosLayout);
        panelContEntradaProductosLayout.setHorizontalGroup(
            panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContEntradaProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContEntradaProductosLayout.createSequentialGroup()
                        .addGroup(panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labT2Cod)
                            .addComponent(labT2Desc)
                            .addComponent(labT2Cant)
                            .addComponent(labT2Precio))
                        .addGap(33, 33, 33)
                        .addGroup(panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldT2Desc)
                            .addComponent(fieldT2Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldT2Cant, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldT2Cod, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContEntradaProductosLayout.createSequentialGroup()
                        .addGroup(panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnExportProduc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBorrarProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRefreshProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelContEntradaProductosLayout.setVerticalGroup(
            panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContEntradaProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labT2Cod)
                    .addComponent(fieldT2Cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labT2Desc)
                    .addComponent(fieldT2Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labT2Cant)
                    .addComponent(fieldT2Cant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labT2Precio)
                    .addComponent(fieldT2Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarProducto)
                    .addComponent(btnRefreshProductos))
                .addGap(18, 18, 18)
                .addGroup(panelContEntradaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarProducto)
                    .addComponent(btnAddProducto))
                .addGap(18, 18, 18)
                .addComponent(btnExportProduc)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "DESCRIPCIÓN", "STOCK", "PRECIO"
            }
        ));
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(40);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        javax.swing.GroupLayout contenedorTablat2Layout = new javax.swing.GroupLayout(contenedorTablat2);
        contenedorTablat2.setLayout(contenedorTablat2Layout);
        contenedorTablat2Layout.setHorizontalGroup(
            contenedorTablat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        contenedorTablat2Layout.setVerticalGroup(
            contenedorTablat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorTablat2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelProductosLayout = new javax.swing.GroupLayout(panelProductos);
        panelProductos.setLayout(panelProductosLayout);
        panelProductosLayout.setHorizontalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(panelContEntradaProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contenedorTablat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelProductosLayout.setVerticalGroup(
            panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contenedorTablat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelContEntradaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        tabsPanel.addTab("tab2", panelProductos);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "VENDEDOR", "TOTAL"
            }
        ));
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(60);
        }

        btnGenerarResumenVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_pdf_30px.png"))); // NOI18N
        btnGenerarResumenVentas.setText("GENERAR RESUMEN");

        javax.swing.GroupLayout panelVentasLayout = new javax.swing.GroupLayout(panelVentas);
        panelVentas.setLayout(panelVentasLayout);
        panelVentasLayout.setHorizontalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE)
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addComponent(btnGenerarResumenVentas)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelVentasLayout.setVerticalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnGenerarResumenVentas)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        tabsPanel.addTab("tab3", panelVentas);

        jLabel7.setText("jLabel7");

        jLabel8.setText("jLabel8");

        jLabel9.setText("jLabel9");

        jLabel10.setText("jLabel10");

        jLabel11.setText("jLabel11");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGap(247, 247, 247)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(294, 294, 294)
                        .addComponent(jLabel9))
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(118, 118, 118)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addContainerGap(441, Short.MAX_VALUE))
        );

        tabsPanel.addTab("tab4", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );

        tabsPanel.addTab("tab5", jPanel5);

        getContentPane().add(tabsPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewVentaActionPerformed

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
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProducto;
    private javax.swing.JButton btnBorrarProducto;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnExportProduc;
    private javax.swing.JButton btnGenerarResumenVentas;
    private javax.swing.JButton btnGuardarProducto;
    private javax.swing.JButton btnInformes;
    private javax.swing.JButton btnNewVenta;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnRefreshProductos;
    private javax.swing.JButton btnVentas;
    private javax.swing.JPanel contCierreVenta;
    private javax.swing.JPanel contMainVenta;
    private javax.swing.JPanel contPanelEntrada;
    private javax.swing.JPanel contenedorTablat2;
    private javax.swing.JTextField fieldCant;
    private javax.swing.JTextField fieldCod;
    private javax.swing.JTextField fieldDesc;
    private javax.swing.JTextField fieldPrecTotal;
    private javax.swing.JTextField fieldPrecUnit;
    private javax.swing.JTextField fieldStock;
    private javax.swing.JTextField fieldT2Cant;
    private javax.swing.JTextField fieldT2Cod;
    private javax.swing.JTextField fieldT2Desc;
    private javax.swing.JTextField fieldT2Precio;
    private javax.swing.JTextField fieldTotalPagar;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel labCod;
    private javax.swing.JLabel labT2Cant;
    private javax.swing.JLabel labT2Cod;
    private javax.swing.JLabel labT2Desc;
    private javax.swing.JLabel labT2Precio;
    private javax.swing.JPanel logoCont;
    private javax.swing.JLabel mainLogo;
    private javax.swing.JLabel mainTitle;
    private javax.swing.JPanel panelContEntradaProductos;
    private javax.swing.JPanel panelNuevaVenta;
    private javax.swing.JPanel panelProductos;
    private javax.swing.JPanel panelVentas;
    private javax.swing.JPanel sidebarMain;
    private javax.swing.JTabbedPane tabsPanel;
    // End of variables declaration//GEN-END:variables
}
