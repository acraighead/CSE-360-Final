import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;


public class GUI extends JFrame implements ActionListener {
    static JFrame frame;
    static JTable table;
    static JMenuBar menuBar;
    static JMenu file, about;
    static JMenuItem load,add_attendance,save,plot,teamInfo;
    static JScrollPane scroll;
    static JFileChooser fileChooser;
    Ops operations = new Ops();
    //String testData [][] = {{"101","Amit","670000"},{"102","Jai","780000"},{"101","Sachin","700000"}};
    String columns[] = {"ID","First Name","Last Name","Program","Level","ASURITE"};

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

    public void addDate(){
        JTable newTable = new JTable();
        JScrollPane newScroll;
        System.out.println("Got to add date");

        LinkedList<Attendance> load_attend = operations.getAttend();
        LinkedList<Roster> load_list = operations.getList();
        ArrayList<Integer> time_list = new ArrayList<Integer>();
        Integer[] time_column;

        int length = load_attend.size();
        System.out.println(length);

        String column_header = load_attend.get(0).month +" "+ load_attend.get(0).day;



        int size = operations.getList().size();


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



        for(int i=0;i<size;i++){
            for(int j=0;j<length;j++){
                if(load_list.get(i).getASU().compareTo(load_attend.get(j).asurite)==0){
                    time_list.add(load_attend.get(j).hours);
                }
            }

        }

        time_column= new Integer[time_list.size()];

        for(int i=0;i<time_list.size();i++){
            time_column[i] = time_list.get(i);
        }

        defTable.addColumn(column_header,time_column);

        //table = new JTable();
        newTable.setModel(defTable);
        newTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        table=newTable;


        newScroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(newScroll);
        this.setVisible(true);


    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()== load){

            int returnVal = fileChooser.showOpenDialog(GUI.this);

            if(returnVal== JFileChooser.APPROVE_OPTION){
                //String file_name = fileChooser.getSelectedFile().getName();
                //String file_name = csvFile.getName();
                operations.read(fileChooser.getSelectedFile());
                setTable();
                frame.setVisible(true);
            }


        } else if(e.getSource() == add_attendance){

            int returnVal = fileChooser.showOpenDialog(GUI.this);

            if(returnVal== JFileChooser.APPROVE_OPTION){

                operations.takeAttendance(fileChooser.getSelectedFile());
                addDate(); //switch to actual
                frame.setVisible(true);
            }

        } else if(e.getSource() == save){
            operations.save(table);
        } else if(e.getSource() == plot){

        } else if(e.getSource() == teamInfo){

            CreateFrame createAbout = new CreateFrame();
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {


                GUI trying = new GUI();

            }

        });
    }

}
