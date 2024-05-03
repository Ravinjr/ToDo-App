package src.main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ToDoListGui extends JFrame implements ActionListener{

    // taskpanel will act as the container for taskcomponent panel
    // taskcomponent panel will store the task components
    private JPanel taskPanel, taskComponentPanel;
    public ToDoListGui(){
        super("To Do List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addGuiComponents();

    }
    private void addGuiComponents(){
        // banner text
        JLabel bannerLabel=new JLabel("To Do List");
        bannerLabel.setFont(createFont("LEMONMILK-Light.otf",36f));
        bannerLabel.setBounds(
                (CommonConstants.GUI_SIZE.width - bannerLabel.getPreferredSize().width)/2,
                15,
                CommonConstants.BANNER_SIZE.width,
                CommonConstants.BANNER_SIZE.height
        );

        //task panel
        taskPanel=new JPanel();

        // task component panel
        taskComponentPanel=new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        // add scroll to the task panel
        JScrollPane scrollPane=new JScrollPane(taskPanel);
        scrollPane.setBounds(8,70,CommonConstants.TASK_PANEL_SIZE.width,CommonConstants.TASK_PANEL_SIZE.height);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(CommonConstants.TASK_PANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        //change the speed of scroll bar
        JScrollBar verticalScrollBar=scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);


        // add task button
        JButton addTaskButton=new JButton("add task");
        //addTaskButton.setFont(createFont("LEMONMILK-Light.otf",26f));
        addTaskButton.setBounds(-5,CommonConstants.GUI_SIZE.height-88,
                CommonConstants.ADD_TASK_BUTTON_SIZE.width,CommonConstants.ADD_TASK_BUTTON_SIZE.height);
        addTaskButton.addActionListener(this);
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // add to frame
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    private Font createFont(String resource,float size){
        //get font file path
        String filepath=getClass().getClassLoader().getResource(resource).getPath();

        // check to see if the path contains a folder with spaces in them
        if (filepath.contains("%20")){
            String filePath=getClass().getClassLoader().getResource(resource).getPath()
                    .replaceAll("20%"," ");
        }


        //create font
        try {
            File customFontFile=new File(filepath);
            Font customFont=Font.createFont(Font.TRUETYPE_FONT,customFontFile).deriveFont(size);
            return customFont;
        }catch (Exception e){
            System.out.println("Error:"+e);
        }
        return null;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        if(command.equalsIgnoreCase("Add Task")){
            // create task component
            TaskComponent taskComponent=new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            //make the previous task appear diable
            if(taskComponentPanel.getComponentCount()>1){
                TaskComponent previousTask=(TaskComponent) taskComponentPanel
                        .getComponent(taskComponentPanel.getComponentCount()-2);
                previousTask.getTaskField().setBackground(null);
            }
            // make the task field request focus after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }
}

