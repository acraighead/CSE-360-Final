import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The GUI Class contains  1 constructor, and 7 methods
 *
 *  The constructor:
 *  GUI()
 *
 * The methods:
 *  SetTable()
 *  addDate()
 *  datePicker()
 *  printNonStudents()
 *  teamInformation()
 *  actionPerformed()
 *  main()
 */

public class GUI extends JFrame implements ActionListener {


    static JFrame frame;
    static JTable table;
    static JMenuBar menuBar;
    static JMenu file, about;
    static JMenuItem load,add_attendance,save,plot,teamInfo;
    static JScrollPane scroll;
    static JFileChooser fileChooser;
    static JDialog dialog,nonStudentDialog,teamInfoDialog;
    static JButton dateButton;

    Ops operations = new Ops();
    String columns[] = {"ID","First Name","Last Name","Program","Level","ASURITE"};
    String picked_day="";
    String picked_month ="";
    String[] months=new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
    String[] days=new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    JComboBox<String> monthList;
    JComboBox<String> dayList;


    /**
     *  The constructor GUI() creates a frame that holds a menu bar.
     *  The menu bar contains two menus: File and About
     *
     *  File contains 4 menu items:
     *  load, to load roster
     *  add_attendance, to add a new attendance column
     *  save, to save the current table
     *  plot, to plot the table data
     *
     *  About contains 1 menu item:
     *  teamInfo, to display our team memeber's name and email
     *
     */
    public GUI(){
        frame = new JFrame("CSE 360 Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileChooser = new JFileChooser();

        menuBar = new JMenuBar();

        file = new JMenu("File");
        about = new JMenu("About");
        menuBar.add(file);
        menuBar.add(about);

        load = new JMenuItem("Load a Roster");
        load.addActionListener(this);
        file.add(load);

        add_attendance = new JMenuItem("Add Attendance");
        add_attendance.addActionListener(this);
        file.add(add_attendance);

        save = new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);

        plot = new JMenuItem("Plot Data");
        plot.addActionListener(this);
        file.add(plot);

        teamInfo = new JMenuItem("Team Info");
        teamInfo.addActionListener(this);
        about.add(teamInfo); //when clicked pop up the team info

        menuBar.add(file);
        menuBar.add(about);

        frame.setJMenuBar(menuBar);
        frame.setSize(800,700);
        frame.setVisible(true);


    }

    /**
     * The method setTable() uses a defaultTableModel
     * to add rows of roster information given by the Ops object
     * A scroll pane is added so the table information will always be viewable
     * */
    public void setTable(){

        int size = operations.getList().size();
        LinkedList<Roster> load_list = operations.getList();

        DefaultTableModel defTable = new DefaultTableModel();

        for(int i=0;i<columns.length;i++){
            defTable.addColumn(columns[i]);
        }

        for(int i=0; i<size;i++){
            String[] row = {String.valueOf(load_list.get(i).getID()),
                    load_list.get(i).getFirst(),
                    load_list.get(i).getLast(),
                    load_list.get(i).getProgram(),
                    load_list.get(i).getLevel(),
                    load_list.get(i).getASU()};
            System.out.println(row[0]);
            defTable.addRow(row);

        }

        table = new JTable();
        table.setModel(defTable);
        table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );


        scroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scroll);

    }

    /**
     * The method addDate() creates a new table with the same table information
     * and adds another column for the user specified date and attendance info
     * given by the Ops object
     * */
    public void addDate(){

        //Ops checking = new Ops();
        System.out.println("Got to add date");

        LinkedList<Attendance> load_attend = operations.getAttend();
        LinkedList<Roster> load_list = operations.getList();
        ArrayList<Integer> time_list = new ArrayList<Integer>();
        Integer[] time_column;

        int length = load_attend.size();
        System.out.println(length);

        String column_header = picked_month +" "+ picked_day;



        int size = operations.getList().size();


        DefaultTableModel defTable = new DefaultTableModel();

        for(int i=0;i<table.getColumnCount();i++){
            if(i>5){
                defTable.addColumn(table.getColumnName(i));
            }else{
                defTable.addColumn(columns[i]);
            }

        }


        for(int i=0;i<table.getRowCount();i++){
            String row[] = new String[table.getColumnCount()];
            for(int j=0;j<table.getColumnCount();j++) {
                if(table.getValueAt(i,j).getClass().getSimpleName().compareTo("Integer")==0){
                    row[j] = String.valueOf(table.getValueAt(i,j));
                } else{
                    row[j] = (String) table.getValueAt(i,j);
                }

            }
            defTable.addRow(row);
        }




        for(int i=0;i<size;i++){
           for(int j=0;j<length;j++){
               if(load_list.get(i).getASU().compareTo(load_attend.get(j).asurite)==0){
                   if(load_attend.get(j).month.compareTo(picked_month)==0 && load_attend.get(j).day.compareTo(picked_day)==0 )
                  time_list.add(load_attend.get(j).hours);
               }
           }
        }

        time_column= new Integer[time_list.size()];

        for(int i=0;i<time_list.size();i++){
            time_column[i] = time_list.get(i);
        }

        defTable.addColumn(column_header,time_column);

        table.setModel(defTable);


    }

    /**
     * The method datePicker() displays a dialog with 2 combo boxes to allow the user
     * to choose a specified date for the attendance column. When the button is pressed
     * the month and day variables are updated
     * */
    public void datePicker(){

        monthList = new JComboBox<String>(months);
        dayList = new JComboBox<String>(days);
        dateButton = new JButton();
        JLabel butLabel = new JLabel("Select");


        dialog = new JDialog(frame,"Choose a Date");
        dialog.setSize(400,300);
        dialog.setLayout(new FlowLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


        dateButton.setBounds(200,100,50,50);
        dateButton.setSize(100,50);
        dateButton.add(butLabel);

        monthList.setSelectedIndex(0);
        dayList.setSelectedIndex(0);

        //monthList.addActionListener(this);
        //dayList.addActionListener(this);
        dateButton.addActionListener(this);


        dialog.add(monthList);
        dialog.add(dayList);
        dialog.add(dateButton);
        dialog.setVisible(true);
    }

    /**
     * The method printNonStudent() will display a dialog with the students email
     * and time spent connected, who are not on the loaded roster
     *
     * The parameter is given by the Ops takeAttendance() method that returns an ArrayList
     * with the students that are not on the roster
     * */
    public void printNonStudents(ArrayList<String> students){
        int size = students.size();
        String delimiter = ",";
        String[] emails = new String[size];
        String[] times = new String[size];

        nonStudentDialog = new JDialog(frame,"Students not on Roster");
        nonStudentDialog.setSize(400,300);
        nonStudentDialog.setLayout(new FlowLayout());
        nonStudentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        nonStudentDialog.setVisible(true);

        JLabel messageLabel = new JLabel(size+" additional students were found:\r\n");

        nonStudentDialog.add(messageLabel);

        for(int i=0; i<size;i++){
            String input[] = students.get(i).split(delimiter);
            JLabel studentText = new JLabel(input[0]+", connected for "+input[1]+" minutes");
            nonStudentDialog.add(studentText);
        }


    }

    /**
     * The method teamInformation() displays a dialog with our team members name and email
     * */
    public void teamInformation(){

        teamInfoDialog = new JDialog(frame,"Team Information");
        teamInfoDialog.setSize(400,300);
        teamInfoDialog.setLayout(new FlowLayout());
        teamInfoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        teamInfoDialog.setVisible(true);

        JLabel label1 = new JLabel("Giovanni DuVall - gduvall@asu.edu");
        JLabel label2 = new JLabel("Christian Chiles - cchiles@asu.edu");
        JLabel label3 = new JLabel("Aaron Craighead - acraighe@asu.edu");
        JLabel label4 = new JLabel("Seth Ryals - sryals1@asu.edu");
        JLabel label5 = new JLabel("Jacob Rollings - jnrollin@asu.edu");

        teamInfoDialog.add(label1);
        teamInfoDialog.add(label2);
        teamInfoDialog.add(label3);
        teamInfoDialog.add(label4);
        teamInfoDialog.add(label5);


    }

    /**
     * The method actionPerformed() is the ActionListener for our GUI functionality.
     * The method checks if any of the menu items are selected and the button found in the
     * datePicker() dialog
     * */
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== load){

            int returnVal = fileChooser.showOpenDialog(GUI.this);

            if(returnVal== JFileChooser.APPROVE_OPTION){
                operations.read(fileChooser.getSelectedFile());
                setTable();
                frame.setVisible(true);
            }

        } else if(e.getSource() == add_attendance){
            datePicker();

        }else if(e.getSource()==dateButton){
            picked_month = (String) monthList.getSelectedItem();
            picked_day = (String) dayList.getSelectedItem();
            dialog.setVisible(false);

            int returnVal = fileChooser.showOpenDialog(GUI.this);

            if(returnVal== JFileChooser.APPROVE_OPTION){
                ArrayList<String> nonStudents = new ArrayList<String>();
                File csv = fileChooser.getSelectedFile();
                nonStudents=operations.takeAttendance(csv,picked_month,picked_day);
                addDate();
                frame.setVisible(true);
                if(nonStudents.size()!=0){
                    printNonStudents(nonStudents);
                }
            }

        } else if(e.getSource() == save){
            operations.save(table);

        } else if(e.getSource() == plot){
            ScatterPlot scatter = new ScatterPlot(operations.getAttend());

        } else if(e.getSource() == teamInfo){
            teamInformation();
        }
    }

    /**
     * The main invokes a method run() which creates a new Object of class GUI
     * which will open the GUI() frame
     * */
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {


                GUI trying = new GUI();

            }

        });
    }

}
