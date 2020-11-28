/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.statisticsForm.CarsStatisticsForm;
import gui.statisticsForm.RentCarsStatisticsForm;
import gui.statisticsForm.UsersStatisticsForm;
import java.awt.Color;
import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import src.dao.CarsDAO;
import src.dao.RentCarsDAO;
import src.dao.UsersDAO;
import src.models.Cars;
import src.models.RentCars;
import src.models.Users;

/**
 *
 * @author paulo
 */
public class EmployeeMenuForm extends javax.swing.JFrame {  
    
    private Color colorButtonOn;
    private Color colorButtonOff;
    
    private ArrayList<Users> listUsers;
    private ArrayList<Cars> listCars;
    private ArrayList<RentCars> listRentCars;
    
    private CarsDAO carsDAO;
    private UsersDAO usersDAO;
    private RentCarsDAO rentCarsDAO;
    /**
     * Creates new form EmployeeMenuForm
     */
    public EmployeeMenuForm() {
        initComponents();
        initPersoComponents();
        
        
    }
    
    private void initPersoComponents()
    {
        colorButtonOn = new Color(255,153,51);
        colorButtonOff = new Color(204,102,0);
        
        errorSaisiCarPanel.setVisible(false);
        errorSaisiUserPanel.setVisible(false);
        errorSaisiRentCarPanel.setVisible(false);
        
        SetVisible(UsersRightPanel);
        SetColorLeftPanelSelection(UsersLeftPanel);
        
        String url = "jdbc:mysql://localhost:3308/projetjava?serverTimezone=UTC";
        String username="root";
        String password="ProjetJava2020";

        carsDAO = new CarsDAO(url,username,password);
        usersDAO = new UsersDAO(url,username,password);
        rentCarsDAO = new RentCarsDAO(url,username,password);
        
        FieldListUsers();
        FieldListCars();
        FieldListRentCars();
    }
    
    private void SetVisible(javax.swing.JPanel panel)
    {        
        UsersRightPanel.setVisible(false);
        CarsRightPanel.setVisible(false);
        StatisticsRightPanel.setVisible(false);
        RentCarsRightPanel.setVisible(false);
        
        panel.setVisible(true);
        
    }
    
    private void SetColorLeftPanelSelection(javax.swing.JPanel panel)
    {
        StatisticLeftPanel.setBackground(colorButtonOff);
        UsersLeftPanel.setBackground(colorButtonOff);
        CarsLeftPanel.setBackground(colorButtonOff);
        RentCarsLeftPanel.setBackground(colorButtonOff);
        usersStatisticsButtonPanel.setBackground(colorButtonOff);
        carsStatisticsButtonPanel.setBackground(colorButtonOff);
        rentCarsStatisticsButtonPanel.setBackground(colorButtonOff);
        
        panel.setBackground(colorButtonOn);
    }
    
    /**
     *
     */
    private void FieldListUsers()
    {
        listUsers = new ArrayList<>();
        listUsers = usersDAO.getAllUsers();
        
        DefaultTableModel model = (DefaultTableModel) ListUsersTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        
        for(int i = 0; i < listUsers.size(); i++)
        {
            row[0] = listUsers.get(i).getLoginID();
            row[1] = listUsers.get(i).getEmail();
            row[2] = listUsers.get(i).getPassword();
            row[3] = listUsers.get(i).getCustomer();
            
            model.addRow(row);
        }
    }
    
    public void FieldListCars()
    {
        
        listCars = new ArrayList<>();
        listCars = carsDAO.getAllCars();
        
        DefaultTableModel model = (DefaultTableModel) ListCarsTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[7];
        
        for(int i = 0; i < listCars.size(); i++)
        {
            row[0] = listCars.get(i).getCarID();
            row[1] = listCars.get(i).getCar();
            row[2] = listCars.get(i).getCouleur();
            row[3] = listCars.get(i).getPrix();
            row[4] = listCars.get(i).getRedBusiness();
            row[5] = listCars.get(i).getRedIndividual();
            row[6] = listCars.get(i).getStock();
            
            model.addRow(row);
        }
    }
    
    private void FieldListRentCars()
    {
        
        listRentCars = new ArrayList<>();
        listRentCars = rentCarsDAO.getAllRentCars();
        
        DefaultTableModel model = (DefaultTableModel) ListRentCarsTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[7];
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        for(int i = 0; i < listRentCars.size(); i++)
        {           
            row[0] = listRentCars.get(i).getRentCarID();
            row[1] = listRentCars.get(i).getCarName();
            row[2] = listRentCars.get(i).getCarColor();
            row[3] = listRentCars.get(i).getPrice();
            row[4] = listRentCars.get(i).getEmailUser();
            row[5] = sdf.format(listRentCars.get(i).getStartDate());
            row[6] = sdf.format(listRentCars.get(i).getEndDate());
            
            model.addRow(row);
        }
    }
    
    private void FieldListRentCarsFilter(ArrayList<RentCars> listRentCars)
    {
        
        DefaultTableModel model = (DefaultTableModel) ListRentCarsTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[7];
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(listRentCars.size());
        for(int i = 0; i < listRentCars.size(); i++)
        {
            
            row[0] = listRentCars.get(i).getRentCarID();
            row[1] = listRentCars.get(i).getCarName();
            row[2] = listRentCars.get(i).getCarColor();
            row[3] = listRentCars.get(i).getPrice();
            row[4] = listRentCars.get(i).getEmailUser();
            row[5] = sdf.format(listRentCars.get(i).getStartDate());
            row[6] = sdf.format(listRentCars.get(i).getEndDate());
            
            model.addRow(row);
        }
    }
    
    public void FieldListCarFilter(ArrayList<Cars> listCars)
    {
        DefaultTableModel model = (DefaultTableModel) ListCarsTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[7];
        
        for(int i = 0; i < listCars.size(); i++)
        {
            row[0] = listCars.get(i).getCarID();
            row[1] = listCars.get(i).getCar();
            row[2] = listCars.get(i).getCouleur();
            row[3] = listCars.get(i).getPrix();
            row[4] = listCars.get(i).getRedBusiness();
            row[5] = listCars.get(i).getRedIndividual();
            row[6] = listCars.get(i).getStock();
            
            model.addRow(row);
        }
    }
    
    public void FieldListUserFilter(ArrayList<Users> listUsers)
    {
        DefaultTableModel model = (DefaultTableModel) ListUsersTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        
        for(int i = 0; i < listUsers.size(); i++)
        {
            row[0] = listUsers.get(i).getLoginID();
            row[1] = listUsers.get(i).getEmail();
            row[2] = listUsers.get(i).getPassword();
            row[3] = listUsers.get(i).getCustomer();
            
            model.addRow(row);
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

        LeftPanel = new javax.swing.JPanel();
        StatisticLeftPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        UsersLeftPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        CarsLeftPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        RentCarsLeftPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        RightPanel = new javax.swing.JPanel();
        UsersRightPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListUsersTable = new javax.swing.JTable();
        addUserButton = new javax.swing.JButton();
        modifyUserButton = new javax.swing.JButton();
        deleteUserButton = new javax.swing.JButton();
        actualizeUserButton = new javax.swing.JButton();
        filterUserButton = new javax.swing.JButton();
        filterUserTextField = new javax.swing.JTextField();
        filterUserComboBox = new javax.swing.JComboBox<>();
        errorSaisiUserPanel = new javax.swing.JLabel();
        CarsRightPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListCarsTable = new javax.swing.JTable();
        modifyCarButton = new javax.swing.JButton();
        deleteCarButton = new javax.swing.JButton();
        addCarButton = new javax.swing.JButton();
        actualizeCarButton = new javax.swing.JButton();
        filterCarComboBox = new javax.swing.JComboBox<>();
        filterCarTextField = new javax.swing.JTextField();
        filterCarButton = new javax.swing.JButton();
        errorSaisiCarPanel = new javax.swing.JLabel();
        StatisticsRightPanel = new javax.swing.JPanel();
        usersStatisticsButtonPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        carsStatisticsButtonPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        rentCarsStatisticsButtonPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        RentCarsRightPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListRentCarsTable = new javax.swing.JTable();
        modifyRentCarButton = new javax.swing.JButton();
        deleteRentCarButton = new javax.swing.JButton();
        addRentCarButton = new javax.swing.JButton();
        actualizeRentCarButton = new javax.swing.JButton();
        filterRentCarComboBox = new javax.swing.JComboBox<>();
        filterRentCarTextField = new javax.swing.JTextField();
        filterRentCarButton = new javax.swing.JButton();
        errorSaisiRentCarPanel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LeftPanel.setBackground(new java.awt.Color(102, 51, 0));

        StatisticLeftPanel.setBackground(new java.awt.Color(204, 102, 0));
        StatisticLeftPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StatisticLeftPanelMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Statistics");

        javax.swing.GroupLayout StatisticLeftPanelLayout = new javax.swing.GroupLayout(StatisticLeftPanel);
        StatisticLeftPanel.setLayout(StatisticLeftPanelLayout);
        StatisticLeftPanelLayout.setHorizontalGroup(
            StatisticLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatisticLeftPanelLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel1)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        StatisticLeftPanelLayout.setVerticalGroup(
            StatisticLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatisticLeftPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        UsersLeftPanel.setBackground(new java.awt.Color(204, 102, 0));
        UsersLeftPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UsersLeftPanelMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Users");

        javax.swing.GroupLayout UsersLeftPanelLayout = new javax.swing.GroupLayout(UsersLeftPanel);
        UsersLeftPanel.setLayout(UsersLeftPanelLayout);
        UsersLeftPanelLayout.setHorizontalGroup(
            UsersLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsersLeftPanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        UsersLeftPanelLayout.setVerticalGroup(
            UsersLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsersLeftPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        CarsLeftPanel.setBackground(new java.awt.Color(204, 102, 0));
        CarsLeftPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CarsLeftPanelMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cars");

        javax.swing.GroupLayout CarsLeftPanelLayout = new javax.swing.GroupLayout(CarsLeftPanel);
        CarsLeftPanel.setLayout(CarsLeftPanelLayout);
        CarsLeftPanelLayout.setHorizontalGroup(
            CarsLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CarsLeftPanelLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel3)
                .addContainerGap(125, Short.MAX_VALUE))
        );
        CarsLeftPanelLayout.setVerticalGroup(
            CarsLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CarsLeftPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        RentCarsLeftPanel.setBackground(new java.awt.Color(204, 102, 0));
        RentCarsLeftPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RentCarsLeftPanelMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rent Cars");

        javax.swing.GroupLayout RentCarsLeftPanelLayout = new javax.swing.GroupLayout(RentCarsLeftPanel);
        RentCarsLeftPanel.setLayout(RentCarsLeftPanelLayout);
        RentCarsLeftPanelLayout.setHorizontalGroup(
            RentCarsLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RentCarsLeftPanelLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RentCarsLeftPanelLayout.setVerticalGroup(
            RentCarsLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RentCarsLeftPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LeftPanelLayout = new javax.swing.GroupLayout(LeftPanel);
        LeftPanel.setLayout(LeftPanelLayout);
        LeftPanelLayout.setHorizontalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(StatisticLeftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(CarsLeftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(UsersLeftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(RentCarsLeftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        LeftPanelLayout.setVerticalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftPanelLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(StatisticLeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(UsersLeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(CarsLeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(RentCarsLeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        RightPanel.setBackground(new java.awt.Color(255, 153, 51));

        UsersRightPanel.setBackground(new java.awt.Color(255, 153, 51));

        ListUsersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserID", "Email", "Password", "Customer"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ListUsersTable);
        if (ListUsersTable.getColumnModel().getColumnCount() > 0) {
            ListUsersTable.getColumnModel().getColumn(0).setResizable(false);
            ListUsersTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            ListUsersTable.getColumnModel().getColumn(1).setResizable(false);
            ListUsersTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            ListUsersTable.getColumnModel().getColumn(2).setResizable(false);
            ListUsersTable.getColumnModel().getColumn(3).setResizable(false);
        }

        addUserButton.setText("Add");
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserButtonActionPerformed(evt);
            }
        });

        modifyUserButton.setText("Modify");
        modifyUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyUserButtonActionPerformed(evt);
            }
        });

        deleteUserButton.setText("Delete");
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        actualizeUserButton.setText("Actualize");
        actualizeUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizeUserButtonActionPerformed(evt);
            }
        });

        filterUserButton.setText("Filter");
        filterUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterUserButtonActionPerformed(evt);
            }
        });

        filterUserComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Email", "Password", "Customer", "ID" }));

        errorSaisiUserPanel.setForeground(new java.awt.Color(255, 0, 0));
        errorSaisiUserPanel.setText("Probleme de saisi");

        javax.swing.GroupLayout UsersRightPanelLayout = new javax.swing.GroupLayout(UsersRightPanel);
        UsersRightPanel.setLayout(UsersRightPanelLayout);
        UsersRightPanelLayout.setHorizontalGroup(
            UsersRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsersRightPanelLayout.createSequentialGroup()
                .addGroup(UsersRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UsersRightPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(UsersRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(deleteUserButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modifyUserButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addUserButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(actualizeUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(filterUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(filterUserTextField)
                            .addComponent(filterUserComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(UsersRightPanelLayout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(errorSaisiUserPanel)))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        UsersRightPanelLayout.setVerticalGroup(
            UsersRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsersRightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errorSaisiUserPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(UsersRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UsersRightPanelLayout.createSequentialGroup()
                        .addComponent(actualizeUserButton)
                        .addGap(68, 68, 68)
                        .addComponent(modifyUserButton)
                        .addGap(28, 28, 28)
                        .addComponent(deleteUserButton)
                        .addGap(27, 27, 27)
                        .addComponent(addUserButton)
                        .addGap(77, 77, 77)
                        .addComponent(filterUserButton)
                        .addGap(31, 31, 31)
                        .addComponent(filterUserTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(filterUserComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsersRightPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );

        CarsRightPanel.setBackground(new java.awt.Color(255, 153, 51));

        ListCarsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Car", "Couleur", "Price", "Business %", "Individual %", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ListCarsTable);
        if (ListCarsTable.getColumnModel().getColumnCount() > 0) {
            ListCarsTable.getColumnModel().getColumn(0).setResizable(false);
            ListCarsTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            ListCarsTable.getColumnModel().getColumn(1).setResizable(false);
            ListCarsTable.getColumnModel().getColumn(2).setResizable(false);
            ListCarsTable.getColumnModel().getColumn(2).setPreferredWidth(12);
            ListCarsTable.getColumnModel().getColumn(3).setResizable(false);
            ListCarsTable.getColumnModel().getColumn(3).setPreferredWidth(10);
            ListCarsTable.getColumnModel().getColumn(4).setResizable(false);
            ListCarsTable.getColumnModel().getColumn(4).setPreferredWidth(30);
            ListCarsTable.getColumnModel().getColumn(5).setResizable(false);
            ListCarsTable.getColumnModel().getColumn(5).setPreferredWidth(30);
            ListCarsTable.getColumnModel().getColumn(6).setResizable(false);
            ListCarsTable.getColumnModel().getColumn(6).setPreferredWidth(30);
        }

        modifyCarButton.setText("Modify");
        modifyCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyCarButtonActionPerformed(evt);
            }
        });

        deleteCarButton.setText("Delete");
        deleteCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCarButtonActionPerformed(evt);
            }
        });

        addCarButton.setText("Add");
        addCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCarButtonActionPerformed(evt);
            }
        });

        actualizeCarButton.setText("Actualize");
        actualizeCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizeCarButtonActionPerformed(evt);
            }
        });

        filterCarComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Car Name", "Color", "Reduction Business", "Reduction Individual", "Price", "Stock", "ID" }));
        filterCarComboBox.setToolTipText("");

        filterCarButton.setText("Filter");
        filterCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterCarButtonActionPerformed(evt);
            }
        });

        errorSaisiCarPanel.setForeground(new java.awt.Color(255, 0, 0));
        errorSaisiCarPanel.setText("Probleme de saisi");

        javax.swing.GroupLayout CarsRightPanelLayout = new javax.swing.GroupLayout(CarsRightPanel);
        CarsRightPanel.setLayout(CarsRightPanelLayout);
        CarsRightPanelLayout.setHorizontalGroup(
            CarsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CarsRightPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(CarsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(filterCarComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterCarTextField)
                    .addComponent(filterCarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
            .addGroup(CarsRightPanelLayout.createSequentialGroup()
                .addGroup(CarsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CarsRightPanelLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(modifyCarButton)
                        .addGap(84, 84, 84)
                        .addComponent(deleteCarButton)
                        .addGap(117, 117, 117)
                        .addComponent(addCarButton)
                        .addGap(79, 79, 79)
                        .addComponent(actualizeCarButton))
                    .addGroup(CarsRightPanelLayout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(errorSaisiCarPanel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CarsRightPanelLayout.setVerticalGroup(
            CarsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CarsRightPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(errorSaisiCarPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(CarsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CarsRightPanelLayout.createSequentialGroup()
                        .addComponent(filterCarButton)
                        .addGap(18, 18, 18)
                        .addComponent(filterCarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filterCarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(CarsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyCarButton)
                    .addComponent(deleteCarButton)
                    .addComponent(addCarButton)
                    .addComponent(actualizeCarButton))
                .addGap(32, 32, 32))
        );

        StatisticsRightPanel.setBackground(new java.awt.Color(102, 51, 0));

        usersStatisticsButtonPanel.setBackground(new java.awt.Color(204, 102, 0));
        usersStatisticsButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersStatisticsButtonPanelMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("USERS");

        javax.swing.GroupLayout usersStatisticsButtonPanelLayout = new javax.swing.GroupLayout(usersStatisticsButtonPanel);
        usersStatisticsButtonPanel.setLayout(usersStatisticsButtonPanelLayout);
        usersStatisticsButtonPanelLayout.setHorizontalGroup(
            usersStatisticsButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usersStatisticsButtonPanelLayout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(105, 105, 105))
        );
        usersStatisticsButtonPanelLayout.setVerticalGroup(
            usersStatisticsButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersStatisticsButtonPanelLayout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(jLabel5)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        carsStatisticsButtonPanel.setBackground(new java.awt.Color(204, 102, 0));
        carsStatisticsButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carsStatisticsButtonPanelMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CARS");

        javax.swing.GroupLayout carsStatisticsButtonPanelLayout = new javax.swing.GroupLayout(carsStatisticsButtonPanel);
        carsStatisticsButtonPanel.setLayout(carsStatisticsButtonPanelLayout);
        carsStatisticsButtonPanelLayout.setHorizontalGroup(
            carsStatisticsButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carsStatisticsButtonPanelLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel6)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        carsStatisticsButtonPanelLayout.setVerticalGroup(
            carsStatisticsButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carsStatisticsButtonPanelLayout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rentCarsStatisticsButtonPanel.setBackground(new java.awt.Color(204, 102, 0));
        rentCarsStatisticsButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rentCarsStatisticsButtonPanelMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("RENT CARS");

        javax.swing.GroupLayout rentCarsStatisticsButtonPanelLayout = new javax.swing.GroupLayout(rentCarsStatisticsButtonPanel);
        rentCarsStatisticsButtonPanel.setLayout(rentCarsStatisticsButtonPanelLayout);
        rentCarsStatisticsButtonPanelLayout.setHorizontalGroup(
            rentCarsStatisticsButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rentCarsStatisticsButtonPanelLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel7)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        rentCarsStatisticsButtonPanelLayout.setVerticalGroup(
            rentCarsStatisticsButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rentCarsStatisticsButtonPanelLayout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout StatisticsRightPanelLayout = new javax.swing.GroupLayout(StatisticsRightPanel);
        StatisticsRightPanel.setLayout(StatisticsRightPanelLayout);
        StatisticsRightPanelLayout.setHorizontalGroup(
            StatisticsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatisticsRightPanelLayout.createSequentialGroup()
                .addComponent(usersStatisticsButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carsStatisticsButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rentCarsStatisticsButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        StatisticsRightPanelLayout.setVerticalGroup(
            StatisticsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(usersStatisticsButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(carsStatisticsButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rentCarsStatisticsButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        RentCarsRightPanel.setBackground(new java.awt.Color(255, 153, 51));

        ListRentCarsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Car", "Color", "Price", "Email", "Start Date", "End Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(ListRentCarsTable);
        if (ListRentCarsTable.getColumnModel().getColumnCount() > 0) {
            ListRentCarsTable.getColumnModel().getColumn(0).setResizable(false);
            ListRentCarsTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            ListRentCarsTable.getColumnModel().getColumn(1).setResizable(false);
            ListRentCarsTable.getColumnModel().getColumn(2).setResizable(false);
            ListRentCarsTable.getColumnModel().getColumn(2).setPreferredWidth(12);
            ListRentCarsTable.getColumnModel().getColumn(3).setResizable(false);
            ListRentCarsTable.getColumnModel().getColumn(3).setPreferredWidth(10);
            ListRentCarsTable.getColumnModel().getColumn(4).setResizable(false);
            ListRentCarsTable.getColumnModel().getColumn(4).setPreferredWidth(30);
            ListRentCarsTable.getColumnModel().getColumn(5).setResizable(false);
            ListRentCarsTable.getColumnModel().getColumn(5).setPreferredWidth(30);
            ListRentCarsTable.getColumnModel().getColumn(6).setResizable(false);
            ListRentCarsTable.getColumnModel().getColumn(6).setPreferredWidth(30);
        }

        modifyRentCarButton.setText("Modify");
        modifyRentCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyRentCarButtonActionPerformed(evt);
            }
        });

        deleteRentCarButton.setText("Delete");
        deleteRentCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRentCarButtonActionPerformed(evt);
            }
        });

        addRentCarButton.setText("Add");
        addRentCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRentCarButtonActionPerformed(evt);
            }
        });

        actualizeRentCarButton.setText("Actualize");
        actualizeRentCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizeRentCarButtonActionPerformed(evt);
            }
        });

        filterRentCarComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Car", "Color", "Price", "Email", "Start Date", "End Date", "ID" }));
        filterRentCarComboBox.setToolTipText("");

        filterRentCarButton.setText("Filter");
        filterRentCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterRentCarButtonActionPerformed(evt);
            }
        });

        errorSaisiRentCarPanel.setForeground(new java.awt.Color(255, 0, 0));
        errorSaisiRentCarPanel.setText("Probleme de saisi");

        javax.swing.GroupLayout RentCarsRightPanelLayout = new javax.swing.GroupLayout(RentCarsRightPanel);
        RentCarsRightPanel.setLayout(RentCarsRightPanelLayout);
        RentCarsRightPanelLayout.setHorizontalGroup(
            RentCarsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RentCarsRightPanelLayout.createSequentialGroup()
                .addGroup(RentCarsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RentCarsRightPanelLayout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(errorSaisiRentCarPanel))
                    .addGroup(RentCarsRightPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(RentCarsRightPanelLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(modifyRentCarButton)
                .addGap(18, 18, 18)
                .addComponent(deleteRentCarButton)
                .addGap(18, 18, 18)
                .addComponent(addRentCarButton)
                .addGap(18, 18, 18)
                .addComponent(actualizeRentCarButton)
                .addGap(148, 148, 148)
                .addComponent(filterRentCarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(RentCarsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(filterRentCarComboBox, 0, 102, Short.MAX_VALUE)
                    .addComponent(filterRentCarTextField))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        RentCarsRightPanelLayout.setVerticalGroup(
            RentCarsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RentCarsRightPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(errorSaisiRentCarPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(RentCarsRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyRentCarButton)
                    .addComponent(deleteRentCarButton)
                    .addComponent(addRentCarButton)
                    .addComponent(actualizeRentCarButton)
                    .addComponent(filterRentCarButton)
                    .addComponent(filterRentCarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(filterRentCarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout RightPanelLayout = new javax.swing.GroupLayout(RightPanel);
        RightPanel.setLayout(RightPanelLayout);
        RightPanelLayout.setHorizontalGroup(
            RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 833, Short.MAX_VALUE)
            .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(UsersRightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(CarsRightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(StatisticsRightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(RightPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RentCarsRightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        RightPanelLayout.setVerticalGroup(
            RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 542, Short.MAX_VALUE)
            .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(UsersRightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(CarsRightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(StatisticsRightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(RightPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RentCarsRightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LeftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(RightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StatisticLeftPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StatisticLeftPanelMouseClicked
       SetVisible(StatisticsRightPanel);
       SetColorLeftPanelSelection(StatisticLeftPanel);
    }//GEN-LAST:event_StatisticLeftPanelMouseClicked

    private void UsersLeftPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsersLeftPanelMouseClicked
       SetVisible(UsersRightPanel);
       SetColorLeftPanelSelection(UsersLeftPanel);
    }//GEN-LAST:event_UsersLeftPanelMouseClicked

    private void CarsLeftPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CarsLeftPanelMouseClicked
       SetVisible(CarsRightPanel);
       SetColorLeftPanelSelection(CarsLeftPanel);
    }//GEN-LAST:event_CarsLeftPanelMouseClicked

    private void addCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCarButtonActionPerformed
        errorSaisiCarPanel.setVisible(false);
        
        new AddCarForm().setVisible(true);
    }//GEN-LAST:event_addCarButtonActionPerformed

    private void actualizeCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizeCarButtonActionPerformed
        errorSaisiCarPanel.setVisible(false);
        
        FieldListCars();
    }//GEN-LAST:event_actualizeCarButtonActionPerformed

    private void deleteCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCarButtonActionPerformed
        errorSaisiCarPanel.setVisible(false);
        
        int[] listCarsDelete;
        listCarsDelete = ListCarsTable.getSelectedRows();
        
        for(int i = 0; i < listCarsDelete.length; i++)
        {
            carsDAO.deleteCarById((long) ListCarsTable.getModel().getValueAt(listCarsDelete[i],0));
        }
        FieldListCars();
       
        
    }//GEN-LAST:event_deleteCarButtonActionPerformed

    private void modifyCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyCarButtonActionPerformed
        errorSaisiCarPanel.setVisible(false);
        
        if(ListCarsTable.getSelectedRowCount() == 1)
        {
          int selectedCar = ListCarsTable.getSelectedRow();
       
          Cars car = carsDAO.getCarById((long) ListCarsTable.getModel().getValueAt(selectedCar,0));
          System.out.println(car.getCarID());
       
          new ModifyCarForm(car).setVisible(true);  
        }
        
    }//GEN-LAST:event_modifyCarButtonActionPerformed

    private void filterCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterCarButtonActionPerformed
        try
        {
            errorSaisiCarPanel.setVisible(false);
            
            if(filterCarComboBox.getSelectedItem() == "Car Name")
        {
            listCars = new ArrayList<>();
            listCars = carsDAO.getAllCarsByCarName(filterCarTextField.getText());
        }
        else if(filterCarComboBox.getSelectedItem() == "Color")
        {
            listCars = new ArrayList<>();
            listCars = carsDAO.getAllCarsByColor(filterCarTextField.getText());
            
        }
        else if(filterCarComboBox.getSelectedItem() == "Reduction Business")
        {
            listCars = new ArrayList<>();
            listCars = carsDAO.getAllCarsByRedBusiness(Float.parseFloat(filterCarTextField.getText()));
            
        }
        else if(filterCarComboBox.getSelectedItem() == "Reduction Individual")
        {
            listCars = new ArrayList<>();
            listCars = carsDAO.getAllCarsByRedIndividual(Float.parseFloat(filterCarTextField.getText()));
            
        }
        else if(filterCarComboBox.getSelectedItem() == "Price")
        {
            listCars = new ArrayList<>();
            listCars = carsDAO.getAllCarsByPrice(Float.parseFloat(filterCarTextField.getText()));
            
        }
        else if(filterCarComboBox.getSelectedItem() == "Stock")
        {
            listCars = new ArrayList<>();
            listCars = carsDAO.getAllCarsByStock(Integer.parseInt(filterCarTextField.getText()));
            
        }
        
        else if(filterCarComboBox.getSelectedItem() == "ID")
        {
            listCars = new ArrayList<>();
            listCars = carsDAO.getAllCarsById(Long.parseLong(filterCarTextField.getText()));
            
        }
        
        FieldListCarFilter(listCars);
        }
        catch(NumberFormatException e)
        {
            errorSaisiCarPanel.setVisible(true);
        }
        
    }//GEN-LAST:event_filterCarButtonActionPerformed

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed
        errorSaisiUserPanel.setVisible(false);
        
        new AddUserForm().setVisible(true);
    }//GEN-LAST:event_addUserButtonActionPerformed

    private void modifyUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyUserButtonActionPerformed
        errorSaisiUserPanel.setVisible(false);
        
        if(ListUsersTable.getSelectedRowCount() == 1)
        {
            int selectedUser = ListUsersTable.getSelectedRow();
       
            Users user = usersDAO.getUserById((long) ListUsersTable.getModel().getValueAt(selectedUser,0));
            System.out.println(user.getLoginID());
       
            new ModifyUserForm(user).setVisible(true);
        }
        
    }//GEN-LAST:event_modifyUserButtonActionPerformed

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
        errorSaisiUserPanel.setVisible(false);
        
        int[] listUsersDelete;
        listUsersDelete = ListUsersTable.getSelectedRows();
        
        for(int i = 0; i < listUsersDelete.length; i++)
        {
            usersDAO.deleteUserById((long) ListUsersTable.getModel().getValueAt(listUsersDelete[i],0));
        }
        FieldListUsers();
    }//GEN-LAST:event_deleteUserButtonActionPerformed

    private void actualizeUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizeUserButtonActionPerformed
        errorSaisiUserPanel.setVisible(false);
        
        FieldListUsers();
    }//GEN-LAST:event_actualizeUserButtonActionPerformed

    private void filterUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterUserButtonActionPerformed
        try
        {
            if(filterUserComboBox.getSelectedItem() == "Email")
        {
            listUsers = new ArrayList<>();
            listUsers = usersDAO.getAllUsersByEmail(filterUserTextField.getText());
        }
        else if(filterUserComboBox.getSelectedItem() == "Customer")
        {
            listUsers = new ArrayList<>();
            listUsers = usersDAO.getAllUsersByCustomer(filterUserTextField.getText());
            
        }
        else if(filterUserComboBox.getSelectedItem() == "Password")
        {
            listUsers = new ArrayList<>();
            listUsers = usersDAO.getAllUsersByPassword(filterUserTextField.getText());
            
        }
        else if(filterUserComboBox.getSelectedItem() == "ID")
        {
            listUsers = new ArrayList<>();
            listUsers = usersDAO.getAllUsersById(Long.parseLong(filterUserTextField.getText()));
            
        }
        FieldListUserFilter(listUsers); 
        }
        catch(NumberFormatException e)
        {
            errorSaisiUserPanel.setVisible(true);
        }
        
       
    }//GEN-LAST:event_filterUserButtonActionPerformed

    private void RentCarsLeftPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RentCarsLeftPanelMouseClicked
        SetVisible(RentCarsRightPanel);
        SetColorLeftPanelSelection(RentCarsLeftPanel);
    }//GEN-LAST:event_RentCarsLeftPanelMouseClicked

    private void modifyRentCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyRentCarButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modifyRentCarButtonActionPerformed

    private void deleteRentCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRentCarButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteRentCarButtonActionPerformed

    private void addRentCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRentCarButtonActionPerformed
        
    }//GEN-LAST:event_addRentCarButtonActionPerformed

    private void actualizeRentCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizeRentCarButtonActionPerformed
        errorSaisiRentCarPanel.setVisible(false);
        
        FieldListRentCars();
    }//GEN-LAST:event_actualizeRentCarButtonActionPerformed

    private void filterRentCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterRentCarButtonActionPerformed
        try
        {
            if(filterRentCarComboBox.getSelectedItem() == "Email")
        {
            listRentCars = new ArrayList<>();
            listRentCars = rentCarsDAO.getAllRentCarsByEmailUser(filterRentCarTextField.getText());
        }
        else if(filterRentCarComboBox.getSelectedItem() == "Color")
        {
            listRentCars = new ArrayList<>();
            listRentCars = rentCarsDAO.getAllRentCarsByCarColor(filterRentCarTextField.getText());
            
        }
        else if(filterRentCarComboBox.getSelectedItem() == "Price")
        {
            listRentCars = new ArrayList<>();
            listRentCars = rentCarsDAO.getAllRentCarsByPrice(Float.parseFloat(filterRentCarTextField.getText()));
            
        }
        else if(filterRentCarComboBox.getSelectedItem() == "Car")
        {
            listRentCars = new ArrayList<>();
            listRentCars = rentCarsDAO.getAllRentCarsByCarName(filterRentCarTextField.getText());
            
        }
         
        FieldListRentCarsFilter(listRentCars); 
        }
        catch(NumberFormatException e)
        {
            errorSaisiRentCarPanel.setVisible(true);
        }
    }//GEN-LAST:event_filterRentCarButtonActionPerformed

    private void usersStatisticsButtonPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersStatisticsButtonPanelMouseClicked
        SetColorLeftPanelSelection(usersStatisticsButtonPanel);
        new UsersStatisticsForm().setVisible(true);
    }//GEN-LAST:event_usersStatisticsButtonPanelMouseClicked

    private void carsStatisticsButtonPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carsStatisticsButtonPanelMouseClicked
       SetColorLeftPanelSelection(carsStatisticsButtonPanel);
       new CarsStatisticsForm().setVisible(true);
    }//GEN-LAST:event_carsStatisticsButtonPanelMouseClicked

    private void rentCarsStatisticsButtonPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rentCarsStatisticsButtonPanelMouseClicked
       SetColorLeftPanelSelection(rentCarsStatisticsButtonPanel);
       new RentCarsStatisticsForm().setVisible(true);
    }//GEN-LAST:event_rentCarsStatisticsButtonPanelMouseClicked

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
            java.util.logging.Logger.getLogger(EmployeeMenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeMenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeMenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeMenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeMenuForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CarsLeftPanel;
    private javax.swing.JPanel CarsRightPanel;
    private javax.swing.JPanel LeftPanel;
    private javax.swing.JTable ListCarsTable;
    private javax.swing.JTable ListRentCarsTable;
    private javax.swing.JTable ListUsersTable;
    private javax.swing.JPanel RentCarsLeftPanel;
    private javax.swing.JPanel RentCarsRightPanel;
    private javax.swing.JPanel RightPanel;
    private javax.swing.JPanel StatisticLeftPanel;
    private javax.swing.JPanel StatisticsRightPanel;
    private javax.swing.JPanel UsersLeftPanel;
    private javax.swing.JPanel UsersRightPanel;
    private javax.swing.JButton actualizeCarButton;
    private javax.swing.JButton actualizeRentCarButton;
    private javax.swing.JButton actualizeUserButton;
    private javax.swing.JButton addCarButton;
    private javax.swing.JButton addRentCarButton;
    private javax.swing.JButton addUserButton;
    private javax.swing.JPanel carsStatisticsButtonPanel;
    private javax.swing.JButton deleteCarButton;
    private javax.swing.JButton deleteRentCarButton;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JLabel errorSaisiCarPanel;
    private javax.swing.JLabel errorSaisiRentCarPanel;
    private javax.swing.JLabel errorSaisiUserPanel;
    private javax.swing.JButton filterCarButton;
    private javax.swing.JComboBox<String> filterCarComboBox;
    private javax.swing.JTextField filterCarTextField;
    private javax.swing.JButton filterRentCarButton;
    private javax.swing.JComboBox<String> filterRentCarComboBox;
    private javax.swing.JTextField filterRentCarTextField;
    private javax.swing.JButton filterUserButton;
    private javax.swing.JComboBox<String> filterUserComboBox;
    private javax.swing.JTextField filterUserTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton modifyCarButton;
    private javax.swing.JButton modifyRentCarButton;
    private javax.swing.JButton modifyUserButton;
    private javax.swing.JPanel rentCarsStatisticsButtonPanel;
    private javax.swing.JPanel usersStatisticsButtonPanel;
    // End of variables declaration//GEN-END:variables
}
