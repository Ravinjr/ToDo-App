package src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TaskComponent extends JPanel implements ActionListener {
    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;

    public JTextPane getTaskField() {
        return taskField;
    }
    // this panel is used when deleting tasks we can make updates to the task component panel
    private JPanel parentPanel;

    public TaskComponent(JPanel parentPanel){
        this.parentPanel=parentPanel;

        // task field
        taskField=new JTextPane();
        taskField.setBorder(BorderFactory.createLineBorder(Color.black));
        taskField.setPreferredSize(CommonConstants.TASK_FIELD_SIZE);
        taskField.setContentType("text/html");
        taskField.addFocusListener(new FocusListener() {

            // indicate which task field is currently being edited
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.white);
            }

            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(null);
            }
        });

        // check box
        checkBox=new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
        checkBox.addActionListener(this);
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // delete button
        deleteButton=new JButton("X");
        deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
        deleteButton.addActionListener(this);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // add to this task component
        add(checkBox);
        add(taskField);
        add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            // replace all html tags to empty string to grab the main text
            String taskText=taskField.getText().replaceAll("<[^>]*>","");

            // add strike through text
            taskField.setText("<html><s>"+ taskText + "<s><html>");
            
        } else if (!checkBox.isSelected()) {
            String taskText=taskField.getText().replaceAll("<[^>]*>","");
            taskField.setText(taskText);
        }

        if (e.getActionCommand().equalsIgnoreCase("X")){
            // delete this component from the parent panel
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }
}
