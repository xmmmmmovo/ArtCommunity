import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class zscx extends JPanel implements ActionListener {
    private JTable table;
    DefaultTableModel model;

    /**
     * Create the panel.
     */
    public zscx() {
        setLayout(null);

        JLabel label = new JLabel("住宿查询");
        label.setFont(new Font("宋体", Font.ITALIC, 14));
        label.setBounds(14, 13, 60, 18);
        this.add(label);

        JButton button = new JButton("查询当前入住");
        button.addActionListener(this);
        button.setBounds(158, 81, 146, 27);
        this.add(button);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(60, 159, 399, 213);
        JTable table = new JTable();
        scrollPane.setViewportView(table);
        model = (DefaultTableModel) table.getModel();    //获得表格模型
        model.setRowCount(0);    //清空表格中的数据
        model.setColumnIdentifiers(new Object[]{"客房号", "姓名", "证件号", "入住时间", "付款金额"});    //设置表头
        add(scrollPane);
    }

    public void actionPerformed(ActionEvent e) {
			

        try {
            model.setRowCount(0);
            Bean zs = new Bean();

            ResultSet rs = zs.executeQuery("select * from CHECKIN");

            rs.last();
            int num = rs.getRow();
            if (num == 0) {
                JOptionPane.showMessageDialog(this, "暂无入住信息", "系统提示", JOptionPane.WARNING_MESSAGE);
            }
            rs.beforeFirst();

            while (rs.next()) {

                String zhunum = rs.getString("CIRno");
                String zhuname = rs.getString("CIname");
                String zhuid = rs.getString("CIid");
                String zhutime = rs.getString("CItime");
                String zhumon = rs.getString("CImon");
                model.addRow(new Object[]{zhunum, zhuname, zhuid, zhutime, zhumon});
            }


            zs.close();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

    }

}