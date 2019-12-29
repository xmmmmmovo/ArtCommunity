import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
@SuppressWarnings("serial")
public class Xitong extends JFrame implements ActionListener{

	private JPanel contentPane;
	private CardLayout yd;
	private Button rb1;
	private Button rb2;
	private Button rb3;
	private Button rb4;
	private Button rb5;
	private Button rb6;
	private Button rb7;
	private Button rb8;
	private Button rb9;
	private Button rb10;
	private JPanel Rpanel;

	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Xitong frame = new Xitong();
//					frame.setVisible(true);
//
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public Xitong() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180,150,1000,530);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Lpanel = new JPanel();
		Lpanel.setBounds(0, 13, 229, 470);
		contentPane.add(Lpanel);
		Lpanel.setLayout(null);
		
		yd=new CardLayout();
		
		JLabel cx = new JLabel("查询");
		cx.setFont(new Font("楷体", Font.BOLD, 19));
		cx.setBounds(14, 42, 56, 29);
		Lpanel.add(cx);
		
		JLabel dj = new JLabel("登记");
		dj.setFont(new Font("楷体", Font.BOLD, 19));
		dj.setBounds(14, 210, 56, 29);
		Lpanel.add(dj);
		
		JLabel tj = new JLabel("统计");
		tj.setFont(new Font("楷体", Font.BOLD, 19));
		tj.setBounds(14, 334, 56, 29);
		Lpanel.add(tj);
		
		Rpanel = new JPanel();
		Rpanel.setBounds(243, 13, 739, 470);
		contentPane.add(Rpanel);
		Rpanel.setLayout(yd);
				
		Rpanel.add(new ydcx(),"p1");
		Rpanel.add(new kfcx(),"p2");
		Rpanel.add(new zscx(),"p3");
		Rpanel.add(new lscx(),"p4");
		Rpanel.add(new yddj(),"p5");
		Rpanel.add(new rzdj(),"p6");
		Rpanel.add(new tfdj(),"p7");
		Rpanel.add(new srtj(),"p8");
		Rpanel.add(new lsxxtj(),"p9");
		Rpanel.add(new huanfang(),"rb10");
		
		rb1 = new Button("预订查询");
		rb1.setFont(new Font("宋体", Font.PLAIN, 14));
		rb1.setBounds(70, 64, 94, 27);
		Lpanel.add(rb1);
		rb1.addActionListener(this);
		
		
		rb2 = new Button("空房查询");
		rb2.setFont(new Font("宋体", Font.PLAIN, 14));
		rb2.setBounds(70, 96, 94, 27);
		Lpanel.add(rb2);
		rb2.addActionListener(this);
		
		rb3 = new Button("住宿查询");
		rb3.setFont(new Font("宋体", Font.PLAIN, 14));
		rb3.setBounds(70, 128, 94, 27);
		Lpanel.add(rb3);
		rb3.addActionListener(this);
		
		rb4 = new Button("历史查询");
		rb4.setFont(new Font("宋体", Font.PLAIN, 14));
		rb4.setBounds(70, 160, 94, 27);
		Lpanel.add(rb4);
		rb4.addActionListener(this);
		
		rb5 = new Button("预订登记");
		rb5.setFont(new Font("宋体", Font.PLAIN, 14));
		rb5.setBounds(70, 231, 94, 27);
		Lpanel.add(rb5);
		rb5.addActionListener(this);
		
		rb6 = new Button("入住登记");
		rb6.setFont(new Font("宋体", Font.PLAIN, 14));
		rb6.setBounds(70, 263, 94, 27);
		Lpanel.add(rb6);
		rb6.addActionListener(this);
		
		rb7 = new Button("退房登记");
		rb7.setFont(new Font("宋体", Font.PLAIN, 14));
		rb7.setBounds(70, 295, 94, 27);
		Lpanel.add(rb7);
		rb7.addActionListener(this);
		
		rb8 = new Button("收入统计");
		rb8.setFont(new Font("宋体", Font.PLAIN, 14));
		rb8.setBounds(59, 357, 94, 27);
		Lpanel.add(rb8);
		rb8.addActionListener(this);
		
		rb9 = new Button("历史信息统计");
		rb9.setFont(new Font("宋体", Font.PLAIN, 14));
		rb9.setBounds(59, 390, 113, 27);
		Lpanel.add(rb9);
		rb9.addActionListener(this);
		
		rb10 = new Button("更换房间");
		rb10.setFont(new Font("宋体", Font.PLAIN, 14));
		rb10.setBounds(59, 433, 113, 27);
		Lpanel.add(rb10);
		rb10.addActionListener(this);
		
		JButton button = new JButton("退出登录");
		button.setBounds(121, 13, 94, 27);
		Lpanel.add(button);
		
		JLabel label = new JLabel("\u6362\u623F");
		label.setFont(new Font("楷体", Font.BOLD, 19));
		label.setBounds(14, 423, 56, 29);
		Lpanel.add(label);
		
		
		this.setTitle("系统功能页面");
		this.setVisible(true);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==rb1){
			yd.show(Rpanel,"p1");
		}
		else if(e.getSource()==rb2){
				yd.show(Rpanel,"p2");
			}
			else if(e.getSource()==rb3){
					yd.show(Rpanel,"p3");
				}
				else if(e.getSource()==rb4){
						yd.show(Rpanel, "p4");
					}
					else if(e.getSource()==rb5){
							yd.show(Rpanel, "p5");
						}
						else if(e.getSource()==rb6){
							yd.show(Rpanel, "p6");
						}
							else if(e.getSource()==rb7){
								yd.show(Rpanel, "p7");
							}
								else if(e.getSource()==rb8){
									yd.show(Rpanel, "p8");
								}
									else if(e.getSource()==rb9){
										yd.show(Rpanel, "p9");
									}
										else if(e.getSource()==rb10){
											yd.show(Rpanel, "rb10");
										}
	}
}
