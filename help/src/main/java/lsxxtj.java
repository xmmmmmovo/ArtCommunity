import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class lsxxtj extends JPanel implements ActionListener {
    private JTextField textField;
    private JTable table;
    DefaultTableModel model;

    /**
     * Create the panel.
     */
    public lsxxtj() {
        setLayout(null);

        JLabel label = new JLabel("历史查询");
        label.setFont(new Font("宋体", Font.ITALIC, 14));
        label.setBounds(14, 13, 60, 18);
        add(label);

        JLabel label_1 = new JLabel("请输入房间编号");
        label_1.setBounds(81, 81, 105, 18);
        add(label_1);

        textField = new JTextField();
        textField.setBounds(200, 78, 86, 24);
        add(textField);
        textField.setColumns(10);

        //查询数据库
        JButton b1 = new JButton("查询");
        b1.addActionListener(this);
        b1.setFont(new Font("宋体", Font.BOLD, 15));
        b1.setBounds(233, 13, 113, 27);
        this.add(b1);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(81, 173, 359, 152);
        JTable table = new JTable();
        scrollPane.setViewportView(table);
        model = (DefaultTableModel) table.getModel();    //获得表格模型
        model.setRowCount(0);    //清空表格中的数据
        model.setColumnIdentifiers(new Object[]{"编号", "姓名", "证件号", "到店时间", "离店时间", "付款金额", "经办人姓名"});    //设置表头
        add(scrollPane);
    }

    public void actionPerformed(ActionEvent e) {


        try {
            model.setRowCount(0);
            Bean zs = new Bean();

            ResultSet rs = zs.executeQuery("select * from history");

            rs.last();
            int num = rs.getRow();
            if (num == 0) {
                JOptionPane.showMessageDialog(this, "暂无历史信息", "系统提示", JOptionPane.WARNING_MESSAGE);
            }
            rs.beforeFirst();

            while (rs.next()) {
                String Hno = rs.getString("Hno");
                String HCname = rs.getString("HCname");
                String HCid = rs.getString("HCid");
                String Hatime = rs.getString("Hatime");
                String Hltime = rs.getString("Hltime");
                String Hmon = rs.getString("Hmon");
                String HMname = rs.getString("HMname");
                model.addRow(new Object[]{Hno, HCname, HCid, Hatime, Hltime, Hmon, HMname});
            }


            zs.close();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

    }

}
