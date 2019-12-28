import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class huanfang extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public huanfang() {
		setLayout(null);
		
		JLabel label = new JLabel("\u66F4\u6362\u623F\u95F4");
		label.setBounds(14, 13, 72, 18);
		add(label);
		
		JLabel label_1 = new JLabel("\u539F\u623F\u95F4\u53F7");
		label_1.setBounds(51, 79, 72, 18);
		add(label_1);
		
		textField = new JTextField();
		textField.setBounds(144, 76, 116, 24);
		add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6362\u81F3\u623F\u95F4");
		label_2.setBounds(51, 159, 72, 18);
		add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(144, 156, 116, 24);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("更换房间");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 换房操作
				 * 
				 * */
				//得到原房间号房间编号
			}
		});
		button.setBounds(136, 233, 113, 27);
		add(button);

	}
	
}
