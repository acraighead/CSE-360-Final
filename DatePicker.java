
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class DatePicker extends JFrame implements ActionListener{

	String monSel = "";
	String daySel = "";
	
	String[] months=new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
	String[] days=new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	
	JComboBox<String> monthList = new JComboBox<String>(months);
	JComboBox<String> dayList = new JComboBox<String>(days);
	JButton button = new JButton();
	JLabel butLabel = new JLabel("Select");
	Boolean selected;
	//JFrame frame;
	
	
	
	public DatePicker(){
		//frame = new JFrame("Date Picker");
		//this.setDefaultCloseOperation(JFrame.);
		selected =false;
		setLayout(new FlowLayout());
		setSize(400,300);
		setTitle("Choose a Date");
		button.setBounds(200,100,50,50);
		button.setSize(100,50);
		button.add(butLabel);
		
		monthList.setSelectedIndex(0);
		dayList.setSelectedIndex(0);
		
		monthList.addActionListener(this);
		dayList.addActionListener(this);
		button.addActionListener(this);
		
		
		add(monthList);
		add(dayList);
		add(button);

		setVisible(true);
		
	
	}
	
	public void actionPerformed(ActionEvent e) 
    { 
        // if the state combobox is changed 
        if (e.getSource() == monthList) 
            monSel = (String) monthList.getSelectedItem(); 
        if (e.getSource() == dayList)
        	daySel = (String) dayList.getSelectedItem();
        if (e.getSource() == button){
        	selected = true;
			setVisible(false);
		}


    }
	
	
	public String getMonth()
	{
		return monSel;
	}
	
	public String getDay()
	{
		return daySel;
	}

	public boolean checkButton(){
		return selected;
	}
	
			
	
}
