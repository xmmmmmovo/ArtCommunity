import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ydcx extends JPanel implements ActionListener {
    String name = new String();
    String tel = new String();
    public JTextField textField_1;
    private JTable p1;
    private ResultSet rs = null;
    DefaultTableModel model;

    public ydcx() {

        this.setLayout(null);

        JLabel label = new JLabel("预订查询");
        label.setFont(new Font("宋体", Font.ITALIC, 14));
        label.setBounds(14, 13, 60, 18);
        this.add(label);

        JLabel label_2 = new JLabel("联系方式");
        label_2.setBounds(59, 93, 72, 18);
        this.add(label_2);

        textField_1 = new JTextField();
        textField_1.setBounds(172, 90, 134, 24);
        this.add(textField_1);
        textField_1.setColumns(10);

        JButton button = new JButton("查询");
        button.addActionListener(this);
        button.setFont(new Font("宋体", Font.BOLD, 15));
        button.setBounds(373, 89, 113, 27);
        this.add(button);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(19, 174, 625, 206);
        add(scrollPane);

        JTable table = new JTable();
        model = (DefaultTableModel) table.getModel();    //获得表格模型
        model.setRowCount(0);    //清空表格中的数据
        model.setColumnIdentifiers(new Object[]{"联系方式", "订房类型", "预订时间"});    //设置表头
        scrollPane.setViewportView(table);


    }

    public void actionPerformed(ActionEvent e) {


        try {
            model.setRowCount(0);
            Bean yd = new Bean();

            String Ctel = "'" + textField_1.getText().trim() + "'";
            System.out.println(Ctel);
            ResultSet rs = yd.executeQuery("SELECT * FROM Orderr WHERE Otel=" + Ctel + "");
            rs.last();
            int num = rs.getRow();
//	        String s[]= {"联系方式","预计到店时间","预订客房类型"};
            if (num == 0) {
                JOptionPane.showMessageDialog(this, "您查找的用户不存在", "系统提示", JOptionPane.WARNING_MESSAGE);
            }

            rs.beforeFirst();

            while (rs.next()) {

                String phone = rs.getString("Otel");
                String type = rs.getString("ORtype");
                String date = rs.getString("Otime");
                model.addRow(new Object[]{phone, type, date});
            }


            yd.close();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

    }
}
