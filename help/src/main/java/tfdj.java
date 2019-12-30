import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class tfdj extends JPanel {
    private JTextField textField;
    String no;

    /**
     * Create the panel.
     */
    public tfdj() {
        setLayout(null);

        JLabel label = new JLabel("退房登记");
        label.setFont(new Font("宋体", Font.ITALIC, 14));
        label.setBounds(14, 13, 60, 18);
        add(label);

        JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u623F\u95F4\u7F16\u53F7");
        label_1.setBounds(55, 187, 125, 18);
        add(label_1);

        textField = new JTextField();
        textField.setBounds(215, 184, 86, 24);
        add(textField);
        textField.setColumns(10);
        String Rnum = textField.getText();
        //退房时对数据库中信息进行更改
        JButton button = new JButton("退房");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /**
                 * 退房
                 * */
                Bean tf = new Bean();
                no = textField.getText();

                tf.executeUpdate("UPDATE Room SET Rstate='空' WHERE Rno='" + no + "'");                    //更改房间入住状态，预定状态
                tf.executeUpdate("UPDATE Room SET ROstate='否' WHERE Rno='" + no + "'");
                tf.executeUpdate("UPDATE CHECKIN SET CIRstate='已退' WHERE CIRno='" + no + "'");
            }
        });
        button.setFont(new Font("楷体", Font.BOLD, 15));
        button.setBounds(127, 367, 113, 27);
        add(button);

        JLabel label_2 = new JLabel("\u62BC\u91D1\u72B6\u6001");
        label_2.setBounds(77, 264, 72, 18);
        add(label_2);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("已交");
        comboBox.addItem("已退");
        comboBox.setBounds(228, 261, 60, 24);
        add(comboBox);

    }
}
