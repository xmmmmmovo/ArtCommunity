import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class yddj extends JPanel {
    private JTextField textField;
    String ydono;

    /**
     * Create the panel.
     */
    public yddj() {
        setLayout(null);

        JLabel label = new JLabel("预订登记");
        label.setFont(new Font("宋体", Font.ITALIC, 14));
        label.setBounds(14, 13, 60, 18);
        add(label);

        JLabel label_1 = new JLabel("预定手机号");
        label_1.setBounds(71, 90, 72, 18);
        add(label_1);

        JLabel label_2 = new JLabel("预定类型");
        label_2.setBounds(71, 121, 72, 18);
        add(label_2);

        final JComboBox comboBox = new JComboBox();
        comboBox.setBounds(203, 118, 114, 24);
        comboBox.addItem("单人间");
        comboBox.addItem("标间");
        comboBox.addItem("商务间");
        comboBox.addItem("套间");
        add(comboBox);

        JLabel label_3 = new JLabel("当前日期设为预订时间");
        label_3.setBounds(77, 173, 154, 18);
        add(label_3);


        textField = new JTextField();
        textField.setBounds(203, 87, 114, 24);
        add(textField);
        textField.setColumns(10);


        JButton button = new JButton("提交");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /***
                 * 数据库
                 *
                 */
                String ydphone = textField.getText();
                String ydtype = comboBox.getSelectedItem().toString();
                Date yddate = new java.sql.Date(new java.util.Date().getTime());


                Bean yd = new Bean();

                ResultSet rs = null;
                rs = yd.executeQuery("SELECT Rno =MIN(Rno) FROM Room b WHERE (( b.Rstate = '空' ) AND (b.Rtype = '" + ydtype + "')AND(b.ROstate='否'))");
                try {
                    while (rs.next()) {
                        ydono = rs.getString("Rno");

                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                yd.execQuery("INSERT INTO Orderr VALUES ('" + ydphone + "','" + yddate + "','" + ydtype + "','" + ydono + "');");

                yd.executeUpdate("UPDATE Room SET ROstate='是' WHERE(Rno=(SELECT Rno =MIN(Rno) FROM Room b WHERE (( b.Rstate = '空' ) AND (b.Rtype = '" + ydtype + "')AND(b.ROstate='否'))));");
                yd.close();

            }
        });


        button.setBounds(86, 233, 81, 27);
        add(button);

        JButton button_1 = new JButton("重置");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(null);    //所填信息置为空值


            }
        });
        button_1.setBounds(203, 233, 81, 27);
        add(button_1);


    }
}