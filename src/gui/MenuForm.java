/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.DropMode;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import src.dao.CarsDAO;
import src.dao.RentCarsDAO;
import src.models.Cars;
import src.models.RentCars;
import src.models.Users;

/**
 *
 * @author paulo
 */
public class MenuForm extends javax.swing.JFrame {

    private Color colorButtonOn;
    private Color colorButtonOff;
    private final Users user;
    private CarsDAO carsDAO;
    private RentCarsDAO rentCarsDAO;
    private ArrayList<Cars> listCars;
    private ArrayList<RentCars> listHistoryCars;
    
    private Cars chooseCar;
    
    private long startDateMill;
    private long endDateMill;
    private float price;
    
    /**
     * Creates new form MenuForm
     */
    public MenuForm(Users user) {  
        this.user = user;
        initComponents();
        initPersoComponents();
        
    }
    
    
    private void initPersoComponents()
    {
        colorButtonOn = new Color(255,153,51);
        colorButtonOff = new Color(204,102,0);
        
        SetVisible(homePanel);
        errorLabel.setVisible(false);
        
        homeButtonPanel.setBackground(colorButtonOn);
        historyButtonPanel.setBackground(colorButtonOff);
        aboutButtonPanel.setBackground(colorButtonOff);
        rentCarButtonPanel.setBackground(colorButtonOff);
        
        payPalRadioButton.setSelected(true);
        
        String url = "jdbc:mysql://localhost:3308/projetjava?serverTimezone=UTC";
        String username="root";
        String password="ProjetJava2020";

        carsDAO = new CarsDAO(url,username,password);
        rentCarsDAO = new RentCarsDAO(url,username,password);
        
        FieldListCars();
        
        if(user.getCustomer() != "guest")
        {
            FieldHistoryListCar();
        }
        
        
        
    }
    
    private void AddToHistoryListCar(RentCars rentCar)
    {
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        Object[] row = new Object[4];
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        row[0] = rentCar.getCarName();
        row[1] = sdf.format(rentCar.getStartDate());
        row[2] = sdf.format(rentCar.getEndDate());
        row[3] = rentCar.getPrice();
        
        model.addRow(row);
    }
    
    private void FieldHistoryListCar()
    {
        listHistoryCars = new ArrayList<>();
        listHistoryCars = rentCarsDAO.getAllRentCarsByEmailUser(user.getEmail());
        
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        Object[] row = new Object[4];
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        for(int i = 0; i < listHistoryCars.size(); i++)
        {
         
            row[0] = listHistoryCars.get(i).getCarName();
            row[1] = sdf.format(listHistoryCars.get(i).getStartDate());
            row[2] = sdf.format(listHistoryCars.get(i).getEndDate());
            row[3] = listHistoryCars.get(i).getPrice();
            
            model.addRow(row);
        }
        
    }
    
    private void FieldListCars()
    {
        listCars = new ArrayList<>();
        listCars = carsDAO.getAllCars();
        
        DefaultTableModel model = (DefaultTableModel) TableListCars.getModel();
        Object[] row = new Object[3];
        
        for(int i = 0; i < listCars.size(); i++)
        {
            row[0] = listCars.get(i).getCar();
            row[1] = listCars.get(i).getCouleur();
            row[2] = listCars.get(i).getPrix();
            
            model.addRow(row);
        }
    }
    
    private void SetVisible(javax.swing.JPanel panel)
    {        
        homePanel.setVisible(false);
        historyPanel.setVisible(false);
        aboutPanel.setVisible(false);
        rentCarPanel.setVisible(false);
        dayRentCarPanel.setVisible(false);
        finalValidationPanel.setVisible(false);
        paymentPanel.setVisible(false);
        
        panel.setVisible(true);
        
    }
    
    private void DisplayFinalValidationPanel()
    {
        SimpleDateFormat sdfStart = new SimpleDateFormat("dd-MM-yyyy");
        String startDate = sdfStart.format(startDateChooser.getDate());
        
        SimpleDateFormat sdfEnd = new SimpleDateFormat("dd-MM-yyyy");
        String endDate = sdfEnd.format(endDateChooser.getDate());
        
        startDateMill = startDateChooser.getDate().getTime();
        endDateMill = endDateChooser.getDate().getTime();
            
        // get the difference in milliseconds
        long diffMillis = endDateChooser.getDate().getTime() - startDateChooser.getDate().getTime();

        // transform to days
        long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
        
        carNameLabel.setText(chooseCar.getCar());
        startDateFinalValidationLabel.setText(startDate);
        endDateFinalValidationLabel.setText(endDate);
        numberDaysFinalValidationLabel.setText(String.valueOf(diffDays));
        priceFinalValidationLabel.setText(String.valueOf(calculatePriceRentCar(diffDays)));
        
                
    }
    
    public float calculatePriceRentCar(long diffDays)
    {
        price = diffDays * chooseCar.getPrix();
        
        
        switch (user.getCustomer()) {
            case "business":
                price = price - (chooseCar.getRedBusiness()/100)*price;
                
                return price;
            case "individual":
                price = price - (chooseCar.getRedIndividual()/100)*price;
                return price;
            default:
                return price;
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

        leftPanel = new javax.swing.JPanel();
        rentCarButtonPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        historyButtonPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        aboutButtonPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        homeButtonPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        rentCarPanel = new javax.swing.JPanel();
        PanelListCar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableListCars = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rentCarButton = new javax.swing.JButton();
        choseAnotherDatesButton = new javax.swing.JButton();
        historyPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        aboutPanel = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        dayRentCarPanel = new javax.swing.JPanel();
        datePanel = new javax.swing.JPanel();
        startDateChooser = new com.toedter.calendar.JDateChooser();
        endDateChooser = new com.toedter.calendar.JDateChooser();
        startDateLabel = new javax.swing.JLabel();
        endDateLabel = new javax.swing.JLabel();
        numberDaysTextField = new javax.swing.JTextField();
        numberdaysLabel = new javax.swing.JLabel();
        calculateButton = new javax.swing.JButton();
        TitleDayRentLabel = new javax.swing.JLabel();
        ContinueRentButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        errorLabel = new javax.swing.JLabel();
        finalValidationPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        CarNameTitleLabel = new javax.swing.JLabel();
        StartDateFinalValidationLabel = new javax.swing.JLabel();
        EndDateFinalValidationLabel = new javax.swing.JLabel();
        carNameLabel = new javax.swing.JLabel();
        startDateFinalValidationLabel = new javax.swing.JLabel();
        endDateFinalValidationLabel = new javax.swing.JLabel();
        numberDaysTitleFinalValidationLabel = new javax.swing.JLabel();
        numberDaysFinalValidationLabel = new javax.swing.JLabel();
        priceTitleFinalValidationLabel = new javax.swing.JLabel();
        priceFinalValidationLabel = new javax.swing.JLabel();
        validateFinalValidationButton = new javax.swing.JButton();
        backToCarsFinalValidationButton = new javax.swing.JButton();
        paymentPanel = new javax.swing.JPanel();
        payPalRadioButton = new javax.swing.JRadioButton();
        carteCreditsRadioButton = new javax.swing.JRadioButton();
        cartesDebitsRadioButton = new javax.swing.JRadioButton();
        virementRadioButton = new javax.swing.JRadioButton();
        paySafeCardRadioButton = new javax.swing.JRadioButton();
        validatePaymentPanelButton = new javax.swing.JButton();
        backToFinalValidationPanelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leftPanel.setBackground(new java.awt.Color(102, 51, 0));

        rentCarButtonPanel.setBackground(new java.awt.Color(204, 102, 0));
        rentCarButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rentCarButtonPanelMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("RENT A CAR");

        javax.swing.GroupLayout rentCarButtonPanelLayout = new javax.swing.GroupLayout(rentCarButtonPanel);
        rentCarButtonPanel.setLayout(rentCarButtonPanelLayout);
        rentCarButtonPanelLayout.setHorizontalGroup(
            rentCarButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rentCarButtonPanelLayout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(62, 62, 62))
        );
        rentCarButtonPanelLayout.setVerticalGroup(
            rentCarButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rentCarButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        historyButtonPanel.setBackground(new java.awt.Color(204, 102, 0));
        historyButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyButtonPanelMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("HISTORY");

        javax.swing.GroupLayout historyButtonPanelLayout = new javax.swing.GroupLayout(historyButtonPanel);
        historyButtonPanel.setLayout(historyButtonPanelLayout);
        historyButtonPanelLayout.setHorizontalGroup(
            historyButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyButtonPanelLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        historyButtonPanelLayout.setVerticalGroup(
            historyButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        aboutButtonPanel.setBackground(new java.awt.Color(204, 102, 0));
        aboutButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutButtonPanelMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ABOUT");

        javax.swing.GroupLayout aboutButtonPanelLayout = new javax.swing.GroupLayout(aboutButtonPanel);
        aboutButtonPanel.setLayout(aboutButtonPanelLayout);
        aboutButtonPanelLayout.setHorizontalGroup(
            aboutButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aboutButtonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(90, 90, 90))
        );
        aboutButtonPanelLayout.setVerticalGroup(
            aboutButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aboutButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        homeButtonPanel.setBackground(new java.awt.Color(204, 102, 0));
        homeButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeButtonPanelMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("HOME");

        javax.swing.GroupLayout homeButtonPanelLayout = new javax.swing.GroupLayout(homeButtonPanel);
        homeButtonPanel.setLayout(homeButtonPanelLayout);
        homeButtonPanelLayout.setHorizontalGroup(
            homeButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeButtonPanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        homeButtonPanelLayout.setVerticalGroup(
            homeButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rentCarButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(historyButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(homeButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(aboutButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addComponent(homeButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(rentCarButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(historyButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(aboutButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        rightPanel.setBackground(new java.awt.Color(255, 153, 51));

        rentCarPanel.setBackground(new java.awt.Color(255, 153, 51));

        PanelListCar.setBackground(new java.awt.Color(255, 255, 255));

        TableListCars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CAR", "COULEUR", "PRIX"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableListCars);
        if (TableListCars.getColumnModel().getColumnCount() > 0) {
            TableListCars.getColumnModel().getColumn(0).setResizable(false);
            TableListCars.getColumnModel().getColumn(0).setPreferredWidth(280);
            TableListCars.getColumnModel().getColumn(1).setResizable(false);
            TableListCars.getColumnModel().getColumn(2).setResizable(false);
            TableListCars.getColumnModel().getColumn(2).setPreferredWidth(60);
        }

        javax.swing.GroupLayout PanelListCarLayout = new javax.swing.GroupLayout(PanelListCar);
        PanelListCar.setLayout(PanelListCarLayout);
        PanelListCarLayout.setHorizontalGroup(
            PanelListCarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelListCarLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelListCarLayout.setVerticalGroup(
            PanelListCarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));

        rentCarButton.setText("Rent This Car");
        rentCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentCarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(rentCarButton, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 169, Short.MAX_VALUE)
                .addComponent(rentCarButton))
        );

        choseAnotherDatesButton.setText("Chose another dates");
        choseAnotherDatesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choseAnotherDatesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rentCarPanelLayout = new javax.swing.GroupLayout(rentCarPanel);
        rentCarPanel.setLayout(rentCarPanelLayout);
        rentCarPanelLayout.setHorizontalGroup(
            rentCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rentCarPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(rentCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rentCarPanelLayout.createSequentialGroup()
                        .addComponent(choseAnotherDatesButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(rentCarPanelLayout.createSequentialGroup()
                        .addComponent(PanelListCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        rentCarPanelLayout.setVerticalGroup(
            rentCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rentCarPanelLayout.createSequentialGroup()
                .addGroup(rentCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rentCarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rentCarPanelLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(PanelListCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(choseAnotherDatesButton)
                .addGap(35, 35, 35))
        );

        historyPanel.setBackground(new java.awt.Color(255, 153, 51));

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Car", "Start Date", "End Date", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(historyTable);
        if (historyTable.getColumnModel().getColumnCount() > 0) {
            historyTable.getColumnModel().getColumn(0).setResizable(false);
            historyTable.getColumnModel().getColumn(0).setPreferredWidth(200);
            historyTable.getColumnModel().getColumn(1).setResizable(false);
            historyTable.getColumnModel().getColumn(1).setPreferredWidth(40);
            historyTable.getColumnModel().getColumn(2).setResizable(false);
            historyTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            historyTable.getColumnModel().getColumn(3).setResizable(false);
            historyTable.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        javax.swing.GroupLayout historyPanelLayout = new javax.swing.GroupLayout(historyPanel);
        historyPanel.setLayout(historyPanelLayout);
        historyPanelLayout.setHorizontalGroup(
            historyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );
        historyPanelLayout.setVerticalGroup(
            historyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        aboutPanel.setBackground(new java.awt.Color(255, 153, 51));

        javax.swing.GroupLayout aboutPanelLayout = new javax.swing.GroupLayout(aboutPanel);
        aboutPanel.setLayout(aboutPanelLayout);
        aboutPanelLayout.setHorizontalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );
        aboutPanelLayout.setVerticalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );

        homePanel.setBackground(new java.awt.Color(255, 153, 51));

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 493, Short.MAX_VALUE)
        );

        dayRentCarPanel.setBackground(new java.awt.Color(255, 153, 51));

        datePanel.setBackground(new java.awt.Color(255, 153, 51));

        startDateChooser.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                startDateChooserAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        startDateLabel.setText("Start Date");

        endDateLabel.setText("End Date");

        numberDaysTextField.setEditable(false);

        numberdaysLabel.setText("Number Of Days");

        calculateButton.setText("Calculate");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datePanelLayout = new javax.swing.GroupLayout(datePanel);
        datePanel.setLayout(datePanelLayout);
        datePanelLayout.setHorizontalGroup(
            datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datePanelLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startDateLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(endDateLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(numberdaysLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(33, 33, 33)
                .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(datePanelLayout.createSequentialGroup()
                        .addComponent(numberDaysTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(calculateButton)))
                .addContainerGap())
        );
        datePanelLayout.setVerticalGroup(
            datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datePanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(startDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startDateLabel))
                .addGap(29, 29, 29)
                .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDateLabel))
                .addGap(25, 25, 25)
                .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numberDaysTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(calculateButton))
                    .addComponent(numberdaysLabel))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        TitleDayRentLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 28)); // NOI18N
        TitleDayRentLabel.setForeground(new java.awt.Color(255, 255, 255));
        TitleDayRentLabel.setText("choose your dates");

        ContinueRentButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ContinueRentButton.setText("Continue");
        ContinueRentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContinueRentButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setText("Choose Other Dates");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addComponent(errorLabel)
                .addGap(192, 192, 192))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(errorLabel))
        );

        javax.swing.GroupLayout dayRentCarPanelLayout = new javax.swing.GroupLayout(dayRentCarPanel);
        dayRentCarPanel.setLayout(dayRentCarPanelLayout);
        dayRentCarPanelLayout.setHorizontalGroup(
            dayRentCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dayRentCarPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TitleDayRentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
            .addGroup(dayRentCarPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(dayRentCarPanelLayout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addGroup(dayRentCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ContinueRentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        dayRentCarPanelLayout.setVerticalGroup(
            dayRentCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dayRentCarPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(TitleDayRentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(datePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(ContinueRentButton)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        finalValidationPanel.setBackground(new java.awt.Color(255, 153, 51));

        jPanel3.setBackground(new java.awt.Color(255, 153, 51));

        CarNameTitleLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        CarNameTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        CarNameTitleLabel.setText("Car Name :");

        StartDateFinalValidationLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        StartDateFinalValidationLabel.setForeground(new java.awt.Color(255, 255, 255));
        StartDateFinalValidationLabel.setText("Start Date : ");

        EndDateFinalValidationLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EndDateFinalValidationLabel.setForeground(new java.awt.Color(255, 255, 255));
        EndDateFinalValidationLabel.setText("End Date  : ");

        carNameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        carNameLabel.setText("Car Name");

        startDateFinalValidationLabel.setText("StartDate");

        endDateFinalValidationLabel.setText("EndDate");

        numberDaysTitleFinalValidationLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numberDaysTitleFinalValidationLabel.setForeground(new java.awt.Color(255, 255, 255));
        numberDaysTitleFinalValidationLabel.setText("Number Of Days : ");

        numberDaysFinalValidationLabel.setText("NbrDays");

        priceTitleFinalValidationLabel.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        priceTitleFinalValidationLabel.setForeground(new java.awt.Color(204, 255, 0));
        priceTitleFinalValidationLabel.setText("Price : ");

        priceFinalValidationLabel.setText("Price");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CarNameTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(EndDateFinalValidationLabel)
                        .addComponent(StartDateFinalValidationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(priceTitleFinalValidationLabel)
                            .addComponent(numberDaysTitleFinalValidationLabel))
                        .addGap(16, 16, 16)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carNameLabel)
                    .addComponent(startDateFinalValidationLabel)
                    .addComponent(endDateFinalValidationLabel)
                    .addComponent(numberDaysFinalValidationLabel)
                    .addComponent(priceFinalValidationLabel))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CarNameTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartDateFinalValidationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startDateFinalValidationLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EndDateFinalValidationLabel)
                    .addComponent(endDateFinalValidationLabel))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberDaysTitleFinalValidationLabel)
                    .addComponent(numberDaysFinalValidationLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceTitleFinalValidationLabel)
                    .addComponent(priceFinalValidationLabel))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        validateFinalValidationButton.setText("Validate");
        validateFinalValidationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateFinalValidationButtonActionPerformed(evt);
            }
        });

        backToCarsFinalValidationButton.setText("Back to Cars");
        backToCarsFinalValidationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToCarsFinalValidationButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout finalValidationPanelLayout = new javax.swing.GroupLayout(finalValidationPanel);
        finalValidationPanel.setLayout(finalValidationPanelLayout);
        finalValidationPanelLayout.setHorizontalGroup(
            finalValidationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finalValidationPanelLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(finalValidationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(finalValidationPanelLayout.createSequentialGroup()
                        .addComponent(backToCarsFinalValidationButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(validateFinalValidationButton)
                        .addGap(151, 151, 151))
                    .addGroup(finalValidationPanelLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(129, Short.MAX_VALUE))))
        );
        finalValidationPanelLayout.setVerticalGroup(
            finalValidationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finalValidationPanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(finalValidationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validateFinalValidationButton)
                    .addComponent(backToCarsFinalValidationButton))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        paymentPanel.setBackground(new java.awt.Color(255, 153, 51));

        payPalRadioButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        payPalRadioButton.setText("PayPal");
        payPalRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payPalRadioButtonActionPerformed(evt);
            }
        });

        carteCreditsRadioButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        carteCreditsRadioButton.setText("Carte de Credits");
        carteCreditsRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carteCreditsRadioButtonActionPerformed(evt);
            }
        });

        cartesDebitsRadioButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cartesDebitsRadioButton.setText("Cartes de Debits");
        cartesDebitsRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartesDebitsRadioButtonActionPerformed(evt);
            }
        });

        virementRadioButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        virementRadioButton.setText("Virement");
        virementRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                virementRadioButtonActionPerformed(evt);
            }
        });

        paySafeCardRadioButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        paySafeCardRadioButton.setText("PaySafeCard");
        paySafeCardRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paySafeCardRadioButtonActionPerformed(evt);
            }
        });

        validatePaymentPanelButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        validatePaymentPanelButton.setText("Validate");
        validatePaymentPanelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validatePaymentPanelButtonActionPerformed(evt);
            }
        });

        backToFinalValidationPanelButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        backToFinalValidationPanelButton.setText("Back");
        backToFinalValidationPanelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToFinalValidationPanelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paymentPanelLayout = new javax.swing.GroupLayout(paymentPanel);
        paymentPanel.setLayout(paymentPanelLayout);
        paymentPanelLayout.setHorizontalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentPanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(backToFinalValidationPanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 304, Short.MAX_VALUE)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(validatePaymentPanelButton)
                    .addComponent(payPalRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paySafeCardRadioButton)
                    .addComponent(virementRadioButton)
                    .addComponent(cartesDebitsRadioButton)
                    .addComponent(carteCreditsRadioButton))
                .addGap(42, 42, 42))
        );
        paymentPanelLayout.setVerticalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(payPalRadioButton)
                .addGap(41, 41, 41)
                .addComponent(carteCreditsRadioButton)
                .addGap(48, 48, 48)
                .addComponent(cartesDebitsRadioButton)
                .addGap(50, 50, 50)
                .addComponent(virementRadioButton)
                .addGap(50, 50, 50)
                .addComponent(paySafeCardRadioButton)
                .addGap(45, 45, 45)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validatePaymentPanelButton)
                    .addComponent(backToFinalValidationPanelButton))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rentCarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(historyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(aboutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(homePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(dayRentCarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(finalValidationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(paymentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rentCarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(historyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(aboutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(homePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(dayRentCarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightPanelLayout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(finalValidationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(11, 11, 11)))
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(paymentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeButtonPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeButtonPanelMouseClicked
        SetVisible(homePanel);
        
        homeButtonPanel.setBackground(colorButtonOn);
        historyButtonPanel.setBackground(colorButtonOff);
        aboutButtonPanel.setBackground(colorButtonOff);
        rentCarButtonPanel.setBackground(colorButtonOff);
    }//GEN-LAST:event_homeButtonPanelMouseClicked

    private void rentCarButtonPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rentCarButtonPanelMouseClicked
        SetVisible(dayRentCarPanel);
        
        rentCarButtonPanel.setBackground(colorButtonOn);
        homeButtonPanel.setBackground(colorButtonOff);
        historyButtonPanel.setBackground(colorButtonOff);
        aboutButtonPanel.setBackground(colorButtonOff);
        
    }//GEN-LAST:event_rentCarButtonPanelMouseClicked

    private void historyButtonPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyButtonPanelMouseClicked

        SetVisible(historyPanel);
        
        homeButtonPanel.setBackground(colorButtonOff);
        historyButtonPanel.setBackground(colorButtonOn);
        aboutButtonPanel.setBackground(colorButtonOff);
        rentCarButtonPanel.setBackground(colorButtonOff);
    }//GEN-LAST:event_historyButtonPanelMouseClicked

    private void aboutButtonPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutButtonPanelMouseClicked
        
        SetVisible(aboutPanel);
        
        homeButtonPanel.setBackground(colorButtonOff);
        historyButtonPanel.setBackground(colorButtonOff);
        aboutButtonPanel.setBackground(colorButtonOn);
        rentCarButtonPanel.setBackground(colorButtonOff);
    }//GEN-LAST:event_aboutButtonPanelMouseClicked

    private void rentCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentCarButtonActionPerformed
        System.out.println(TableListCars.getSelectedRowCount());
        if(TableListCars.getSelectedRowCount() == 1)
        {
            chooseCar = listCars.get(TableListCars.getSelectedRow());
            DisplayFinalValidationPanel();
            SetVisible(finalValidationPanel);
        }
        
    }//GEN-LAST:event_rentCarButtonActionPerformed

    private void ContinueRentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContinueRentButtonActionPerformed
        if(startDateChooser.getDate() != null && endDateChooser.getDate() != null)
        {
            errorLabel.setVisible(false);
            
            
            SimpleDateFormat sdfStart = new SimpleDateFormat("dd-MM-yyyy");
            String startDate = sdfStart.format(startDateChooser.getDate());
        
            SimpleDateFormat sdfEnd = new SimpleDateFormat("dd-MM-yyyy");
            String endDate = sdfEnd.format(endDateChooser.getDate());
        
        
            if(startDateChooser.getDate().getTime() <= endDateChooser.getDate().getTime() && System.currentTimeMillis() < startDateChooser.getDate().getTime())
            {
                errorLabel.setVisible(false);
            
                // get the difference in milliseconds
                long diffMillis = endDateChooser.getDate().getTime() - startDateChooser.getDate().getTime();

                // transform to days
                long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
                
                //DisplayFinalValidationPanel();
                SetVisible(rentCarPanel);
                
            
            
            }
            else
            {
                errorLabel.setVisible(true);            
            }
        }
        else
        {
            errorLabel.setVisible(true);
        }
        
        
        
        
    }//GEN-LAST:event_ContinueRentButtonActionPerformed

    private void startDateChooserAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_startDateChooserAncestorAdded

    }//GEN-LAST:event_startDateChooserAncestorAdded

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateButtonActionPerformed
        if(startDateChooser.getDate() != null && endDateChooser.getDate() != null)
        {
            if(startDateChooser.getDate().getTime() <= endDateChooser.getDate().getTime() && System.currentTimeMillis() < startDateChooser.getDate().getTime())
            {
                errorLabel.setVisible(false);
            
                // get the difference in milliseconds
                long diffMillis = endDateChooser.getDate().getTime() - startDateChooser.getDate().getTime();

                // transform to days
                long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
            
                numberDaysTextField.setText(String.valueOf(diffDays));
            }
            else
            {
                errorLabel.setVisible(true);            
            }
        }
        
    }//GEN-LAST:event_calculateButtonActionPerformed

    private void backToCarsFinalValidationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToCarsFinalValidationButtonActionPerformed
        SetVisible(rentCarPanel);
    }//GEN-LAST:event_backToCarsFinalValidationButtonActionPerformed

    private void validateFinalValidationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateFinalValidationButtonActionPerformed
        SetVisible(paymentPanel);
    }//GEN-LAST:event_validateFinalValidationButtonActionPerformed

    private void payPalRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payPalRadioButtonActionPerformed
        if(!payPalRadioButton.isSelected())
        {
            payPalRadioButton.setSelected(true);
            
            
        }
        else if(payPalRadioButton.isSelected())
        {
            carteCreditsRadioButton.setSelected(false);
            cartesDebitsRadioButton.setSelected(false);
            virementRadioButton.setSelected(false);
            paySafeCardRadioButton.setSelected(false);
        }
    }//GEN-LAST:event_payPalRadioButtonActionPerformed

    private void carteCreditsRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carteCreditsRadioButtonActionPerformed
        if(!carteCreditsRadioButton.isSelected())
        {
            carteCreditsRadioButton.setSelected(true);
            
            
        }
        else if(carteCreditsRadioButton.isSelected())
        {
            payPalRadioButton.setSelected(false);
            cartesDebitsRadioButton.setSelected(false);
            virementRadioButton.setSelected(false);
            paySafeCardRadioButton.setSelected(false);
        }
    }//GEN-LAST:event_carteCreditsRadioButtonActionPerformed

    private void cartesDebitsRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartesDebitsRadioButtonActionPerformed
        if(!cartesDebitsRadioButton.isSelected())
        {
            cartesDebitsRadioButton.setSelected(true);
            
            
        }
        else if(cartesDebitsRadioButton.isSelected())
        {
            payPalRadioButton.setSelected(false);
            carteCreditsRadioButton.setSelected(false);
            virementRadioButton.setSelected(false);
            paySafeCardRadioButton.setSelected(false);
        }
    }//GEN-LAST:event_cartesDebitsRadioButtonActionPerformed

    private void virementRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_virementRadioButtonActionPerformed
         if(!virementRadioButton.isSelected())
        {
            virementRadioButton.setSelected(true);
            
            
        }
        else if(virementRadioButton.isSelected())
        {
            payPalRadioButton.setSelected(false);
            cartesDebitsRadioButton.setSelected(false);
            carteCreditsRadioButton.setSelected(false);
            paySafeCardRadioButton.setSelected(false);
        }
    }//GEN-LAST:event_virementRadioButtonActionPerformed

    private void paySafeCardRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paySafeCardRadioButtonActionPerformed
         if(!paySafeCardRadioButton.isSelected())
        {
            paySafeCardRadioButton.setSelected(true);
            
            
        }
        else if(paySafeCardRadioButton.isSelected())
        {
            payPalRadioButton.setSelected(false);
            cartesDebitsRadioButton.setSelected(false);
            virementRadioButton.setSelected(false);
            carteCreditsRadioButton.setSelected(false);
        }
    }//GEN-LAST:event_paySafeCardRadioButtonActionPerformed

    private void validatePaymentPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validatePaymentPanelButtonActionPerformed
        RentCars rentCar = new RentCars();
        
        rentCar.setCarName(chooseCar.getCar());
        rentCar.setCarColor(chooseCar.getCouleur());
        rentCar.setEmailUser(user.getEmail());
        rentCar.setCustomerUser(user.getCustomer());
        rentCar.setPrice(price);
        rentCar.setStartDate(startDateMill);
        rentCar.setEndDate(endDateMill);
        
        AddToHistoryListCar(rentCar);
        
        String url = "jdbc:mysql://localhost:3308/projetjava?serverTimezone=UTC";
        String username="root";
        String password="ProjetJava2020";
        
        rentCarsDAO = new RentCarsDAO(url,username,password);
        rentCarsDAO.saveRentCar(rentCar);
        
        JOptionPane.showMessageDialog(this,"We saved the reservetion");
        System.out.println("saved inton rentCars database");
        SetVisible(homePanel);
        
        homeButtonPanel.setBackground(colorButtonOn);
        rentCarButtonPanel.setBackground(colorButtonOff);
    }//GEN-LAST:event_validatePaymentPanelButtonActionPerformed

    private void choseAnotherDatesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choseAnotherDatesButtonActionPerformed
        SetVisible(dayRentCarPanel);
    }//GEN-LAST:event_choseAnotherDatesButtonActionPerformed

    private void backToFinalValidationPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToFinalValidationPanelButtonActionPerformed
        SetVisible(finalValidationPanel);
    }//GEN-LAST:event_backToFinalValidationPanelButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MenuForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CarNameTitleLabel;
    private javax.swing.JButton ContinueRentButton;
    private javax.swing.JLabel EndDateFinalValidationLabel;
    private javax.swing.JPanel PanelListCar;
    private javax.swing.JLabel StartDateFinalValidationLabel;
    private javax.swing.JTable TableListCars;
    private javax.swing.JLabel TitleDayRentLabel;
    private javax.swing.JPanel aboutButtonPanel;
    private javax.swing.JPanel aboutPanel;
    private javax.swing.JButton backToCarsFinalValidationButton;
    private javax.swing.JButton backToFinalValidationPanelButton;
    private javax.swing.JButton calculateButton;
    private javax.swing.JLabel carNameLabel;
    private javax.swing.JRadioButton carteCreditsRadioButton;
    private javax.swing.JRadioButton cartesDebitsRadioButton;
    private javax.swing.JButton choseAnotherDatesButton;
    private javax.swing.JPanel datePanel;
    private javax.swing.JPanel dayRentCarPanel;
    private com.toedter.calendar.JDateChooser endDateChooser;
    private javax.swing.JLabel endDateFinalValidationLabel;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JPanel finalValidationPanel;
    private javax.swing.JPanel historyButtonPanel;
    private javax.swing.JPanel historyPanel;
    private javax.swing.JTable historyTable;
    private javax.swing.JPanel homeButtonPanel;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLabel numberDaysFinalValidationLabel;
    private javax.swing.JTextField numberDaysTextField;
    private javax.swing.JLabel numberDaysTitleFinalValidationLabel;
    private javax.swing.JLabel numberdaysLabel;
    private javax.swing.JRadioButton payPalRadioButton;
    private javax.swing.JRadioButton paySafeCardRadioButton;
    private javax.swing.JPanel paymentPanel;
    private javax.swing.JLabel priceFinalValidationLabel;
    private javax.swing.JLabel priceTitleFinalValidationLabel;
    private javax.swing.JButton rentCarButton;
    private javax.swing.JPanel rentCarButtonPanel;
    private javax.swing.JPanel rentCarPanel;
    private javax.swing.JPanel rightPanel;
    private com.toedter.calendar.JDateChooser startDateChooser;
    private javax.swing.JLabel startDateFinalValidationLabel;
    private javax.swing.JLabel startDateLabel;
    private javax.swing.JButton validateFinalValidationButton;
    private javax.swing.JButton validatePaymentPanelButton;
    private javax.swing.JRadioButton virementRadioButton;
    // End of variables declaration//GEN-END:variables
}
