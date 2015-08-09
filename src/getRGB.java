import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.jws.Oneway;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class getRGB {
	public static void main(String[] args) {
		new getRGB();
	}

	public Event evt;

	public getRGB() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}
				JFrame frame = new JFrame("Color Picker");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.add(new ColorpanelPane());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				// frame.setResizable(false);
			}
		});
	}

	public class ColorpanelPane extends JPanel implements ItemListener {
		private BufferedImage img;
		// private BufferedImage minimg;
		private JLabel label;
		private JRadioButton Levelnull;
		private JRadioButton Level3;
		private JRadioButton Level45;
		private JRadioButton Level7;
		private JRadioButton jrnull;
		private JRadioButton jrcomplementary;
		private JRadioButton jranalogous;
		private JRadioButton jrtriadic;
		private JRadioButton jrmonochromatic;
		private JButton Levelreset;
		// private JButton select;
		// private JButton turnbutton;
		private JPanel fieldl;
		private JPanel fields;
		private JPanel fieldb;
		private JPanel fieldchoose;
		private JPanel fieldchoose2;
		private JPanel fieldnew;
		private JTextField red;
		private JTextField green;
		private JTextField blue;
		// private JButton surebutton;
		private JLabel chocelabel;
		private JLabel chocelabel2;
		private Double postionL;
		// BufferedImage bi;
		int packedInt;
		boolean isclick = false;
		private Boolean isonbi = false;
		// JScrollPane js;
		Graphics2D g;
		// �ϴ�ѡɫ
		Color mycolor;
		// �ϴ�ѡɫ����Χ36����ɫ��
		int lastarcolor[] = new int[64];
		int lastx, lasty;
		// JLabel minlabel;
		// Choice lst;
		int whatnumber = 1;
		boolean linevisble = false;

		private JSlider jSlider1;
		private JTextField textField1;
		private JLabel label1;
		// ��¼�Ƿ�ѡ�����ƣ������ȣ�0��ʾ��ѡ��1����ɫ��2����ɫ,3��ʾ���
		private int whathaschoose = 0;

		public ColorpanelPane() {
			setLayout(new GridBagLayout());

			fieldnew = new JPanel();
			fieldnew.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			jSlider1 = new JSlider();
			jSlider1.setBounds(15, 70, 400, 50);
			jSlider1.setMaximum(21);
			jSlider1.setMinimum(1);
			// �����Ƿ���JSlider���Ͽ̶�
			jSlider1.setPaintTicks(true);
			// ���ô�̶�֮��ľ���
			jSlider1.setMajorTickSpacing(5);
			// ������С�̶�֮��ľ���
			jSlider1.setMinorTickSpacing(1);
			// �����Ƿ����ֱ�ǣ�����Ϊtrue����JSlider�̶��Ͼͻ�����ֵ����
			jSlider1.setPaintLabels(true);
			fieldnew.add(jSlider1);
			textField1 = new JTextField(10);
			textField1.setBounds(110, 15, 50, 20);
			textField1.setText("1");
			fieldnew.add(textField1);
			jSlider1.setValue(0);
			textField1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int num = 0;
					try {
						num = Integer.parseInt(textField1.getText());
						jSlider1.setValue(num);
					} catch (NumberFormatException yb) {
						textField1.setText("�������");
					}
				}
			});
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			fieldl = new JPanel();
			fieldl.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			label = new JLabel();
			// lst = new Choice();
			// lst.add("1");
			// lst.add("2");
			// lst.add("3");
			// InputStream
			// url=getRGB.class.getClassLoader().getResourceAsStream(path);
			/*
			 * try { img = ImageIO.read(url); } catch (IOException e1) { // TODO
			 * Auto-generated catch block e1.printStackTrace(); }
			 */
			resetlabelcolor(whatnumber);
			// js=new JScrollPane(label);
			// js.setPreferredSize(new Dimension(640, 640));
			// js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			// add(js, gbc);
			fieldl.add(label);
			// fieldl.add(minlabel);
			add(fieldl, gbc);
			g = (Graphics2D) img.getGraphics();
			fields = new JPanel();
			fields.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			fieldchoose = new JPanel();
			// fields.setBorder(new EmptyBorder(15, 15, 50, 30));
			fields.setBounds(0, 0, 50, 50);
			fieldchoose.setPreferredSize(new Dimension(285, 25));
			fieldchoose.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			fieldchoose2 = new JPanel();
			fieldchoose2.setPreferredSize(new Dimension(285, 25));
			fieldchoose2.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			red = new JTextField(3);
			green = new JTextField(3);
			blue = new JTextField(3);
			// surebutton = new JButton("confirm");
			chocelabel = new JLabel();
			chocelabel.setFont(new Font("����", 1, 15));
			chocelabel.setText("no choose");
			chocelabel2 = new JLabel();
			chocelabel2.setFont(new Font("����", 1, 15));
			chocelabel2.setText("no choose");
			chocelabel2.setForeground(Color.WHITE);
			fields.add(red);
			fields.add(green);
			fields.add(blue);
			// fields.add(surebutton);
			fieldchoose.add(chocelabel, BorderLayout.CENTER);
			fieldchoose2.add(chocelabel2, BorderLayout.CENTER);
			fieldb = new JPanel(new GridLayout(9, 1));
			ButtonGroup bg = new ButtonGroup();
			Levelnull = new JRadioButton("null");
			Level3 = new JRadioButton("Level AA(Large Text)");
			Level45 = new JRadioButton("Level AA & Level AAA(Large Text)");
			Level7 = new JRadioButton("Level AAA");
			JPanel fieljr = new JPanel(new GridLayout(2, 2));
			fieljr.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			Levelreset = new JButton("reset");
			bg.add(Levelnull);
			bg.add(Level3);
			bg.add(Level45);
			bg.add(Level7);
			Levelnull.setSelected(true);
			Levelnull.addItemListener(this);
			Level3.addItemListener(this);
			Level45.addItemListener(this);
			Level7.addItemListener(this);
			fieljr.add(Levelnull);
			fieljr.add(Level3);
			fieljr.add(Level45);
			fieljr.add(Level7);
			ButtonGroup bg2 = new ButtonGroup();
			jrnull = new JRadioButton("null");
			jrcomplementary = new JRadioButton("complementary");
			jranalogous = new JRadioButton("analogous");
			jrtriadic = new JRadioButton("triadic");
			jrmonochromatic = new JRadioButton("monochromatic");
			jrnull.setSelected(true);
			bg2.add(jrnull);
			bg2.add(jrcomplementary);
			bg2.add(jranalogous);
			bg2.add(jrtriadic);
			bg2.add(jrmonochromatic);
			jrnull.addItemListener(this);
			jrcomplementary.addItemListener(this);
			jranalogous.addItemListener(this);
			jrtriadic.addItemListener(this);
			jrmonochromatic.addItemListener(this);
			JPanel fieljr2 = new JPanel(new GridLayout(3, 2));
			fieljr2.add(jrnull);
			fieljr2.add(jrcomplementary);
			fieljr2.add(jranalogous);
			fieljr2.add(jrtriadic);
			fieljr2.add(jrmonochromatic);
			fieljr2.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			fieldb.add(fieldnew);
			fieldb.add(fields);
			fieldb.add(fieldchoose);
			fieldb.add(fieldchoose2);
			fieldb.add(fieljr2);
			fieldb.add(fieljr);
			// fieldb.add(select);
			fieldb.add(Levelreset);
			fieldl.add(fieldb);
			jSlider1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {

					textField1.setText(Integer.toString(jSlider1.getValue()));
					if (!chocelabel.getText().equals("no choose")) {
						reset(0);
						setLRGB(Integer.parseInt(textField1.getText()), 21f);
					}
				}
			});

			// surebutton.addActionListener(new ActionListener() {
			// @Override
			// public void actionPerformed(ActionEvent e) {
			// // TODO Auto-generated method stub
			// // isclick=true;
			// Color color = new Color(Integer.parseInt(red.getText()),
			// Integer.parseInt(green.getText()), Integer
			// .parseInt(blue.getText()));
			// // postionL=getL(color);
			// // fields.setBackground(color);
			// //
			// chocelabel.setText("choose:"+color.getRed()+","+color.getGreen()+","+color.getBlue());
			//
			// // int packedInt = img.getRGB(e.getX(), e.getY());
			// // Color color = new Color(packedInt, true);
			// fieldchoose.setBackground(color);
			// if ((color.getRed() + "").trim().equals("255")
			// && (color.getGreen() + "").trim().equals("255")
			// && (color.getBlue() + "").trim().equals("255"))// �㵽����
			// {
			// return;// �㵽����
			// }
			// if (isclick == false) {
			// linevisble = true;
			// isclick = true;
			//
			// postionL = getL(color);
			// fields.setBackground(color);
			// red.setText(Integer.toString(color.getRed()));
			// green.setText(Integer.toString(color.getGreen()));
			// blue.setText(Integer.toString(color.getBlue()));
			// chocelabel.setText("choose:" + color.getRed() + ","
			// + color.getGreen() + "," + color.getBlue());
			// chocelabel2.setText("choose:" + color.getRed() + ","
			// + color.getGreen() + "," + color.getBlue());
			//
			// } else {
			// // ������ǰ��ɫһ��
			// if (color.getRGB() == mycolor.getRGB() && linevisble) {
			// // ��ջ���
			// clearline();
			//
			// label.update(g);
			// label.updateUI();
			// // js.updateUI();
			// linevisble = false;
			// } else {
			// linevisble = true;
			// // �����ǰ����
			// clearline();
			// label.update(g);
			// label.updateUI();
			// // js.updateUI();
			//
			// postionL = getL(color);
			// fields.setBackground(color);
			// red.setText(Integer.toString(color.getRed()));
			// green.setText(Integer.toString(color.getGreen()));
			// blue.setText(Integer.toString(color.getBlue()));
			// chocelabel.setText("choose:" + color.getRed() + ","
			// + color.getGreen() + "," + color.getBlue());
			// chocelabel2.setText("choose:" + color.getRed()
			// + "," + color.getGreen() + ","
			// + color.getBlue());
			//
			// }
			// }
			// setchoosecolor2();
			// }
			// });
			/*
			 * Level45.addActionListener(new ActionListener() {
			 * 
			 * @Override public void actionPerformed(ActionEvent e) { // TODO
			 * Auto-generated method stub
			 * 
			 * } }); Level3.addActionListener(new ActionListener() {
			 * 
			 * @Override public void actionPerformed(ActionEvent e) { // TODO
			 * Auto-generated method stub reset(0); setLRGB(3f, 21f); } });
			 * Level7.addActionListener(new ActionListener() {
			 * 
			 * @Override public void actionPerformed(ActionEvent e) { // TODO
			 * Auto-generated method stub reset(0); setLRGB(7f,21f); } });
			 */
			Levelreset.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					reset(1);
					Levelnull.setSelected(true);
					jrnull.setSelected(true);
					reset(1);
				}
			});
			label.addMouseMotionListener(new MouseAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					if (isclick == false) {
						if (isonbi) {
							packedInt = img.getRGB(e.getX(), e.getY());
							if ((packedInt >> 24) == 0) {
								fields.setBackground(Color.WHITE);
								red.setText("");
								green.setText("");
								blue.setText("");
								chocelabel.setForeground(Color.WHITE);
								fieldchoose2.setBackground(Color.WHITE);
							} else {
								Color color = new Color(packedInt, true);
								fields.setBackground(color);
								red.setText(Integer.toString(color.getRed()));
								green.setText(Integer.toString(color.getGreen()));
								blue.setText(Integer.toString(color.getBlue()));
								chocelabel.setForeground(color);
								fieldchoose2.setBackground(color);
							}
						} else {
							packedInt = img.getRGB(e.getX(), e.getY());
							Color color = new Color(packedInt, true);
							fields.setBackground(color);
							red.setText(Integer.toString(color.getRed()));
							green.setText(Integer.toString(color.getGreen()));
							blue.setText(Integer.toString(color.getBlue()));
							chocelabel.setForeground(color);
							fieldchoose2.setBackground(color);
						}
					}
				}
			});
			/*
			 * for(int i=0;i<img.getWidth();i++) { for(int
			 * j=0;j<img.getHeight();j++) { //���ÿһ����Lֵ Color color = new
			 * Color(img.getRGB(i,j), true); alll[i][j]=getL(color); } }
			 */
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					int packedInt = img.getRGB(e.getX(), e.getY());
					Color color = new Color(packedInt, true);
					// ����Ĳ���͸��
					if ((packedInt >> 24) != 0) {
						fieldchoose.setBackground(color);
						if ((color.getRed() + "").trim().equals("255")
								&& (color.getGreen() + "").trim().equals("255")
								&& (color.getBlue() + "").trim().equals("255"))// �㵽����
						{
							return;// �㵽����
						}
						if (isclick == false) {
							linevisble = true;
							isclick = true;
							// ������
							g.setColor(Color.WHITE);
							setlastarcolor(e.getX(), e.getY());
							g.drawRect(e.getX() - 5, e.getY() - 5, 10, 10);
							g.setColor(Color.BLACK);
							g.drawRect(e.getX()-4, e.getY()-4, 8,8);

							label.update(g);
							label.updateUI();
							// js.updateUI();

							postionL = getL(color);
							fields.setBackground(color);
							red.setText(Integer.toString(color.getRed()));
							green.setText(Integer.toString(color.getGreen()));
							blue.setText(Integer.toString(color.getBlue()));
							chocelabel.setText("choose:" + color.getRed() + ","
									+ color.getGreen() + "," + color.getBlue());
							chocelabel2.setText("choose:" + color.getRed()
									+ "," + color.getGreen() + ","
									+ color.getBlue());

						} else {
							// ������ǰ��ɫһ��
							if (color.getRGB() == mycolor.getRGB()
									&& linevisble) {
								// ��ջ���
								clearline();
								// g.setColor(color);
								// g.drawRect(lastx-1, lasty-1, 10, 10);
								label.update(g);
								label.updateUI();
								// js.updateUI();
								linevisble = false;
							} else {
								linevisble = true;
								// �����ǰ����
								clearline();
								// g.setColor(mycolor);
								// g.drawRect(lastx-1,lasty-1, 10, 10);
								label.update(g);
								label.updateUI();
								// js.updateUI();
								// ������
								g.setColor(Color.WHITE);
								setlastarcolor(e.getX(), e.getY());
								g.drawRect(e.getX() - 5, e.getY() - 5, 10, 10);
								g.setColor(Color.BLACK);
								g.drawRect(e.getX()-4, e.getY()-4, 8,8);

								label.update(g);
								label.updateUI();
								// js.updateUI();

								postionL = getL(color);
								fields.setBackground(color);
								red.setText(Integer.toString(color.getRed()));
								green.setText(Integer.toString(color.getGreen()));
								blue.setText(Integer.toString(color.getBlue()));
								chocelabel.setText("choose:" + color.getRed()
										+ "," + color.getGreen() + ","
										+ color.getBlue());
								chocelabel2.setText("choose:" + color.getRed()
										+ "," + color.getGreen() + ","
										+ color.getBlue());

							}
						}
						chocelabel.setForeground(color);
						chocelabel2.setForeground(color);
						lastx = e.getX();
						lasty = e.getY();
						mycolor = color;
					}
				}
			});
		}

		protected void changeanalogous() {
			// TODO Auto-generated method stub
			// ���ѡ����ɫ��hsl
			// ����ÿһ��hsl��ɫֵ
			float choose[] = RGB2HSL(mycolor.getRed(), mycolor.getGreen(),
					mycolor.getBlue());
			// System.out.println("h="+choose[0]+"s="+choose[1]+"l="+choose[2]);
			// ѡ����ɫ�Ļ���ɫ��������������ɫ����ʾ
			int c = img.getRGB(3, 0);
			for (int i = 0; i < img.getWidth(); i++) {
				for (int j = 0; j < img.getHeight(); j++) {
					// ���ÿһ����Lֵ
					Color tempcolor = new Color(img.getRGB(i, j), true);
					// ����ÿһ��hsl��ɫֵ
					float l[] = RGB2HSL(tempcolor.getRed(),
							tempcolor.getGreen(), tempcolor.getBlue());
					// System.out.println(""+(choose[0]-l[0]));
					// ����hֵ��45����
					if (choose[0] - l[0] >= 0 && choose[0] - l[0] <= 45
							|| choose[0] - l[0] >= -45 && choose[0] - l[0] <= 0) {
						img.setRGB(i, j, img.getRGB(i, j));
					} else {
						img.setRGB(i, j, c & 0x00ffffff);// ����ѱ�����Ϊ͸��
					}
				}
			}
			label.setIcon(new ImageIcon(img));
			isclick = false;
		}

		protected void changemonochromatic() {
			// TODO Auto-generated method stub
			// ���ѡ����ɫ��hsl
			// ����ÿһ��hsl��ɫֵ
			float choose[] = RGB2HSL(mycolor.getRed(), mycolor.getGreen(),
					mycolor.getBlue());
			// System.out.println("h="+choose[0]+"s="+choose[1]+"l="+choose[2]);
			// ѡ����ɫ�Ļ���ɫ��������������ɫ����ʾ
			int c = img.getRGB(3, 0);
			for (int i = 0; i < img.getWidth(); i++) {
				for (int j = 0; j < img.getHeight(); j++) {
					// ���ÿһ����Lֵ
					Color tempcolor = new Color(img.getRGB(i, j), true);
					// ����ÿһ��hsl��ɫֵ
					float l[] = RGB2HSL(tempcolor.getRed(),
							tempcolor.getGreen(), tempcolor.getBlue());
					// System.out.println(""+(choose[0]-l[0]));
					// ����hֵ��45����
					if (choose[0] - l[0] == 0) {
						img.setRGB(i, j, img.getRGB(i, j));
					} else {
						img.setRGB(i, j, c & 0x00ffffff);// ����ѱ�����Ϊ͸��
					}
				}
			}
			label.setIcon(new ImageIcon(img));
			isclick = false;
		}

		protected void changecomplements() {
			// TODO Auto-generated method stub
			// ���ѡ����ɫ��hsl
			// ����ÿһ��hsl��ɫֵ
			float choose[] = RGB2HSL(mycolor.getRed(), mycolor.getGreen(),
					mycolor.getBlue());
			// System.out.println("h="+choose[0]+"s="+choose[1]+"l="+choose[2]);
			// ѡ����ɫ�Ļ���ɫ��������������ɫ����ʾ
			int c = img.getRGB(3, 0);
			for (int i = 0; i < img.getWidth(); i++) {
				for (int j = 0; j < img.getHeight(); j++) {
					// ���ÿһ����Lֵ
					Color tempcolor = new Color(img.getRGB(i, j), true);
					// ����ÿһ��hsl��ɫֵ
					float l[] = RGB2HSL(tempcolor.getRed(),
							tempcolor.getGreen(), tempcolor.getBlue());
					// System.out.println(""+(choose[0]-l[0]));
					// ����hֵ��170-190����
					if (choose[0] - l[0] >= 165 && choose[0] - l[0] <= 195
							|| choose[0] - l[0] >= -195
							&& choose[0] - l[0] <= -65) {
						// if (choose[0] - l[0] == 180 || choose[1] - l[0] ==
						// 180) {
						img.setRGB(i, j, img.getRGB(i, j));
					} else {
						img.setRGB(i, j, c & 0x00ffffff);// ����ѱ�����Ϊ͸��
					}
				}
			}
			label.setIcon(new ImageIcon(img));
			isclick = false;
		}

		protected void changetriadic() {
			// TODO Auto-generated method stub
			// ���ѡ����ɫ��hsl
			// ����ÿһ��hsl��ɫֵ
			float choose[] = RGB2HSL(mycolor.getRed(), mycolor.getGreen(),
					mycolor.getBlue());
			// System.out.println("h="+choose[0]+"s="+choose[1]+"l="+choose[2]);
			// ѡ����ɫ�Ļ���ɫ��������������ɫ����ʾ
			int c = img.getRGB(3, 0);
			for (int i = 0; i < img.getWidth(); i++) {
				for (int j = 0; j < img.getHeight(); j++) {
					// ���ÿһ����Lֵ
					Color tempcolor = new Color(img.getRGB(i, j), true);
					// ����ÿһ��hsl��ɫֵ
					float l[] = RGB2HSL(tempcolor.getRed(),
							tempcolor.getGreen(), tempcolor.getBlue());
					// System.out.println(""+(choose[0]-l[0]));
					// ����h120
					if ((choose[0] - l[0]) % 120 == 0
							|| (l[0] - choose[0]) % 120 == 0) {
						img.setRGB(i, j, img.getRGB(i, j));
					} else {
						img.setRGB(i, j, c & 0x00ffffff);// ����ѱ�����Ϊ͸��
					}
				}
			}
			label.setIcon(new ImageIcon(img));
			isclick = false;
		}

		public float[] RGB2HSL(int r, int g, int b) {
			float H, S, L, var_Min, var_Max, del_Max, del_R, del_G, del_B;
			H = 0;
			var_Min = Math.min(r, Math.min(b, g));
			var_Max = Math.max(r, Math.max(b, g));
			del_Max = var_Max - var_Min;
			L = (var_Max + var_Min) / 2;
			if (del_Max == 0) {
				H = 0;
				S = 0;

			} else {
				if (L < 128) {
					S = 256 * del_Max / (var_Max + var_Min);
				} else {
					S = 256 * del_Max / (512 - var_Max - var_Min);
				}
				del_R = ((360 * (var_Max - r) / 6) + (360 * del_Max / 2))
						/ del_Max;
				del_G = ((360 * (var_Max - g) / 6) + (360 * del_Max / 2))
						/ del_Max;
				del_B = ((360 * (var_Max - b) / 6) + (360 * del_Max / 2))
						/ del_Max;
				if (r == var_Max) {
					H = del_B - del_G;
				} else if (g == var_Max) {
					H = 120 + del_R - del_B;
				} else if (b == var_Max) {
					H = 240 + del_G - del_R;
				}
				if (H < 0) {
					H += 360;
				}
				if (H >= 360) {
					H -= 360;
				}
				if (L >= 256) {
					L = 255;
				}
				if (S >= 256) {
					S = 255;
				}
			}
			float[] hsl = new float[3];
			hsl[0] = H;
			hsl[1] = S;
			hsl[2] = L;
			return hsl;
		}

		/*
		 * public float[] getHsl(int r,int g,int b) { float[] hsl = new
		 * float[3]; float R = r / 255.f; float G = g / 255.f; float B = b /
		 * 255.f; float max, min, diff, r_dist, g_dist, b_dist; max =
		 * Math.max(Math.max(R, G), B); min = Math.min(Math.min(R, G), B); diff
		 * = max - min; hsl[2] = (max + min) / 2; if (diff == 0.f) { hsl[0] =
		 * 0.f; hsl[1] = 0.f; } else { if (hsl[2] < 0.5) { hsl[1] = diff / (max
		 * + min); } else { hsl[1] = diff / (2 - max - min); } r_dist = (max -
		 * R) / diff; g_dist = (max - G) / diff; b_dist = (max - B) / diff; if
		 * (R == max) { hsl[0] = b_dist - g_dist; } else if (G == max) { hsl[1]
		 * = 2 + r_dist - b_dist; } else if (B == max) { hsl[2] = 4 + g_dist -
		 * r_dist; } hsl[0] = hsl[0] * 60; if (hsl[0] < 0) { hsl[0] += 360; } if
		 * (hsl[0] >= 360) { hsl[0] -= 360; } } return hsl;// colorValue = R +
		 * 256 * G + 65536 * B }
		 */
		protected void clearline() {

			// TODO Auto-generated method stub
			g.setColor(new Color(lastarcolor[0]));
			g.drawRect(lastx - 5, lasty - 5, 1, 1);
			g.setColor(new Color(lastarcolor[1]));
			g.drawRect(lastx - 4, lasty - 5, 1, 1);
			g.setColor(new Color(lastarcolor[2]));
			g.drawRect(lastx - 3, lasty - 5, 1, 1);
			g.setColor(new Color(lastarcolor[3]));
			g.drawRect(lastx - 2, lasty - 5, 1, 1);
			g.setColor(new Color(lastarcolor[4]));
			g.drawRect(lastx - 1, lasty - 5, 1, 1);
			g.setColor(new Color(lastarcolor[5]));
			g.drawRect(lastx, lasty - 5, 1, 1);
			g.setColor(new Color(lastarcolor[6]));
			g.drawRect(lastx + 1, lasty - 5, 1, 1);
			g.setColor(new Color(lastarcolor[7]));
			g.drawRect(lastx + 2, lasty - 5, 1, 1);
			g.setColor(new Color(lastarcolor[8]));
			g.drawRect(lastx + 3, lasty - 5, 1, 1);
			g.setColor(new Color(lastarcolor[9]));
			g.drawRect(lastx + 4, lasty - 5, 1, 1);
			g.setColor(new Color(lastarcolor[10]));
			g.drawRect(lastx - 5, lasty - 4, 1, 1);
			g.setColor(new Color(lastarcolor[11]));
			g.drawRect(lastx + 4, lasty - 4, 1, 1);
			g.setColor(new Color(lastarcolor[12]));
			g.drawRect(lastx - 5, lasty - 3, 1, 1);
			g.setColor(new Color(lastarcolor[13]));
			g.drawRect(lastx + 4, lasty - 3, 1, 1);
			g.setColor(new Color(lastarcolor[14]));
			g.drawRect(lastx - 5, lasty - 2, 1, 1);
			g.setColor(new Color(lastarcolor[15]));
			g.drawRect(lastx + 4, lasty - 2, 1, 1);
			g.setColor(new Color(lastarcolor[16]));
			g.drawRect(lastx - 5, lasty - 1, 1, 1);
			g.setColor(new Color(lastarcolor[17]));
			g.drawRect(lastx + 4, lasty - 1, 1, 1);
			g.setColor(new Color(lastarcolor[18]));
			g.drawRect(lastx - 5, lasty, 1, 1);
			g.setColor(new Color(lastarcolor[19]));
			g.drawRect(lastx + 4, lasty, 1, 1);
			g.setColor(new Color(lastarcolor[20]));
			g.drawRect(lastx - 5, lasty + 1, 1, 1);
			g.setColor(new Color(lastarcolor[21]));
			g.drawRect(lastx + 4, lasty + 1, 1, 1);
			g.setColor(new Color(lastarcolor[22]));
			g.drawRect(lastx - 5, lasty + 2, 1, 1);
			g.setColor(new Color(lastarcolor[23]));
			g.drawRect(lastx + 4, lasty + 2, 1, 1);
			g.setColor(new Color(lastarcolor[24]));
			g.drawRect(lastx - 5, lasty + 3, 1, 1);
			g.setColor(new Color(lastarcolor[25]));
			g.drawRect(lastx + 4, lasty + 3, 1, 1);
			g.setColor(new Color(lastarcolor[26]));
			g.drawRect(lastx - 5, lasty + 4, 1, 1);
			g.setColor(new Color(lastarcolor[27]));
			g.drawRect(lastx - 4, lasty + 4, 1, 1);
			g.setColor(new Color(lastarcolor[28]));
			g.drawRect(lastx - 3, lasty + 4, 1, 1);
			g.setColor(new Color(lastarcolor[29]));
			g.drawRect(lastx - 2, lasty + 4, 1, 1);
			g.setColor(new Color(lastarcolor[30]));
			g.drawRect(lastx - 1, lasty + 4, 1, 1);
			g.setColor(new Color(lastarcolor[31]));
			g.drawRect(lastx, lasty + 4, 1, 1);
			g.setColor(new Color(lastarcolor[32]));
			g.drawRect(lastx + 1, lasty + 4, 1, 1);
			g.setColor(new Color(lastarcolor[33]));
			g.drawRect(lastx + 2, lasty + 4, 1, 1);
			g.setColor(new Color(lastarcolor[34]));
			g.drawRect(lastx + 3, lasty + 4, 1, 1);
			g.setColor(new Color(lastarcolor[35]));
			g.drawRect(lastx + 4, lasty + 4, 1, 1);
			g.setColor(new Color(lastarcolor[36]));
			g.drawRect(lastx - 4, lasty - 4, 1, 1);
			g.setColor(new Color(lastarcolor[37]));
			g.drawRect(lastx - 3, lasty - 4, 1, 1);
			g.setColor(new Color(lastarcolor[38]));
			g.drawRect(lastx - 2, lasty - 4, 1, 1);
			g.setColor(new Color(lastarcolor[39]));
			g.drawRect(lastx - 1, lasty - 4, 1, 1);
			g.setColor(new Color(lastarcolor[40]));
			g.drawRect(lastx, lasty - 4, 1, 1);
			g.setColor(new Color(lastarcolor[41]));
			g.drawRect(lastx + 1, lasty - 4, 1, 1);
			g.setColor(new Color(lastarcolor[42]));
			g.drawRect(lastx + 2, lasty - 4, 1, 1);
			g.setColor(new Color(lastarcolor[43]));
			g.drawRect(lastx + 3, lasty - 4, 1, 1);
			g.setColor(new Color(lastarcolor[44]));
			g.drawRect(lastx - 4, lasty - 3, 1, 1);
			g.setColor(new Color(lastarcolor[45]));
			g.drawRect(lastx + 3, lasty - 3, 1, 1);
			g.setColor(new Color(lastarcolor[46]));
			g.drawRect(lastx - 4, lasty - 2, 1, 1);
			g.setColor(new Color(lastarcolor[47]));
			g.drawRect(lastx + 3, lasty - 2, 1, 1);
			g.setColor(new Color(lastarcolor[48]));
			g.drawRect(lastx - 4, lasty - 1, 1, 1);
			g.setColor(new Color(lastarcolor[49]));
			g.drawRect(lastx + 3, lasty - 1, 1, 1);
			g.setColor(new Color(lastarcolor[50]));
			g.drawRect(lastx - 4, lasty, 1, 1);
			g.setColor(new Color(lastarcolor[51]));
			g.drawRect(lastx + 3, lasty, 1, 1);
			g.setColor(new Color(lastarcolor[52]));
			g.drawRect(lastx - 4, lasty + 1, 1, 1);
			g.setColor(new Color(lastarcolor[53]));
			g.drawRect(lastx + 3, lasty + 1, 1, 1);
			g.setColor(new Color(lastarcolor[54]));
			g.drawRect(lastx - 4, lasty + 2, 1, 1);
			g.setColor(new Color(lastarcolor[55]));
			g.drawRect(lastx + 3, lasty + 2, 1, 1);
			g.setColor(new Color(lastarcolor[56]));
			g.drawRect(lastx - 4, lasty + 3, 1, 1);
			g.setColor(new Color(lastarcolor[57]));
			g.drawRect(lastx - 3, lasty + 3, 1, 1);
			g.setColor(new Color(lastarcolor[58]));
			g.drawRect(lastx - 2, lasty + 3, 1, 1);
			g.setColor(new Color(lastarcolor[59]));
			g.drawRect(lastx - 1, lasty + 3, 1, 1);
			g.setColor(new Color(lastarcolor[60]));
			g.drawRect(lastx, lasty + 3, 1, 1);
			g.setColor(new Color(lastarcolor[61]));
			g.drawRect(lastx + 1, lasty + 3, 1, 1);
			g.setColor(new Color(lastarcolor[62]));
			g.drawRect(lastx + 2, lasty + 3, 1, 1);
			g.setColor(new Color(lastarcolor[63]));
			g.drawRect(lastx + 3, lasty + 3, 1, 1);

		}

		protected void setlastarcolor(int x, int y) {
			// TODO Auto-generated method stub
			lastarcolor[0] = img.getRGB(x - 5, y - 5);
			lastarcolor[1] = img.getRGB(x - 4, y - 5);
			lastarcolor[2] = img.getRGB(x - 3, y - 5);
			lastarcolor[3] = img.getRGB(x - 2, y - 5);
			lastarcolor[4] = img.getRGB(x - 1, y - 5);
			lastarcolor[5] = img.getRGB(x, y - 5);
			lastarcolor[6] = img.getRGB(x + 1, y - 5);
			lastarcolor[7] = img.getRGB(x + 2, y - 5);
			lastarcolor[8] = img.getRGB(x + 3, y - 5);
			lastarcolor[9] = img.getRGB(x + 4, y - 5);
			lastarcolor[10] = img.getRGB(x - 5, y - 4);
			lastarcolor[11] = img.getRGB(x + 4, y - 4);
			lastarcolor[12] = img.getRGB(x - 5, y - 3);
			lastarcolor[13] = img.getRGB(x + 4, y - 3);
			lastarcolor[14] = img.getRGB(x - 5, y - 2);
			lastarcolor[15] = img.getRGB(x + 4, y - 2);
			lastarcolor[16] = img.getRGB(x - 5, y - 1);
			lastarcolor[17] = img.getRGB(x + 4, y - 1);
			lastarcolor[18] = img.getRGB(x - 5, y);
			lastarcolor[19] = img.getRGB(x + 4, y);
			lastarcolor[20] = img.getRGB(x - 5, y + 1);
			lastarcolor[21] = img.getRGB(x + 4, y + 1);
			lastarcolor[22] = img.getRGB(x - 5, y + 2);
			lastarcolor[23] = img.getRGB(x + 4, y + 2);
			lastarcolor[24] = img.getRGB(x - 5, y + 3);
			lastarcolor[25] = img.getRGB(x + 4, y + 3);
			lastarcolor[26] = img.getRGB(x - 5, y + 4);
			lastarcolor[27] = img.getRGB(x - 4, y + 4);
			lastarcolor[28] = img.getRGB(x - 3, y + 4);
			lastarcolor[29] = img.getRGB(x - 2, y + 4);
			lastarcolor[30] = img.getRGB(x - 1, y + 4);
			lastarcolor[31] = img.getRGB(x, y + 4);
			lastarcolor[32] = img.getRGB(x + 1, y + 4);
			lastarcolor[33] = img.getRGB(x + 2, y + 4);
			lastarcolor[34] = img.getRGB(x + 3, y + 4);
			lastarcolor[35] = img.getRGB(x + 4, y + 4);
			lastarcolor[36] = img.getRGB(x - 4, y - 4);
			lastarcolor[37] = img.getRGB(x - 3, y - 4);
			lastarcolor[38] = img.getRGB(x - 2, y - 4);
			lastarcolor[39] = img.getRGB(x - 1, y - 4);
			lastarcolor[40] = img.getRGB(x, y - 4);
			lastarcolor[41] = img.getRGB(x + 1, y - 4);
			lastarcolor[42] = img.getRGB(x + 2, y - 4);
			lastarcolor[43] = img.getRGB(x + 3, y - 4);
			lastarcolor[44] = img.getRGB(x - 4, y - 3);
			lastarcolor[45] = img.getRGB(x + 3, y - 3);
			lastarcolor[46] = img.getRGB(x - 4, y - 2);
			lastarcolor[47] = img.getRGB(x + 3, y - 2);
			lastarcolor[48] = img.getRGB(x - 4, y - 1);
			lastarcolor[49] = img.getRGB(x + 3, y - 1);
			lastarcolor[50] = img.getRGB(x - 4, y);
			lastarcolor[51] = img.getRGB(x + 3, y);
			lastarcolor[52] = img.getRGB(x - 4, y + 1);
			lastarcolor[53] = img.getRGB(x + 3, y + 1);
			lastarcolor[54] = img.getRGB(x - 4, y + 2);
			lastarcolor[55] = img.getRGB(x - 3, y + 2);
			lastarcolor[56] = img.getRGB(x - 4, y + 3);
			lastarcolor[57] = img.getRGB(x - 3, y + 3);
			lastarcolor[58] = img.getRGB(x - 2, y + 3);
			lastarcolor[59] = img.getRGB(x - 1, y + 3);
			lastarcolor[60] = img.getRGB(x, y + 3);
			lastarcolor[61] = img.getRGB(x + 1, y + 3);
			lastarcolor[62] = img.getRGB(x + 2, y + 3);
			lastarcolor[63] = img.getRGB(x + 3, y + 3);
		}

		protected void resetlabelcolor(int whatnumber) {
			// TODO Auto-generated method stub
			if (img == null)
				img = new BufferedImage(640, 640, BufferedImage.TYPE_4BYTE_ABGR);// �½�һ������֧��͸����BufferedImage
			for (int i = 0; i < img.getWidth(); i++) {
				for (int j = 0; j < img.getHeight(); j++) {
					/*
					 * int n1=(int) i%256; int n2=(int)
					 * ((i/256f)>(j/256f)?(i/256f)*(i/256f):(j/256f)*(j/256f));
					 * int n3=(int) j%256; if(n1%whatnumber!=0) {
					 * n1=n1-n1%whatnumber; } if(n3%whatnumber!=0) {
					 * n3=n3-n3%whatnumber; }
					 */
					int n1, n2, n3;
					if (whatnumber == 1) {
						n1 = (int) ((i / 5) % 26) * 10;
						n2 = (int) ((i / (5 * 26f)) > (j / (5 * 26f)) ? (i / (5 * 26f))
								* (i / (5 * 26f))
								: (j / (5 * 26f)) * (j / (5 * 26f))) * 10;
						n3 = (int) ((j / 5) % 26) * 10;
					} else if (whatnumber == 2) {
						n1 = (int) ((i / 10) % 16) * 16;
						n2 = (int) ((i / (10 * 16f)) > (j / (10 * 16f)) ? (i / (10 * 16f))
								* (i / (10 * 16f))
								: (j / (10 * 16f)) * (j / (10 * 16f))) * 16;
						n3 = (int) ((j / 10) % 16) * 16;
					} else {
						n1 = (int) ((i / 2) % 47) * 5;
						n2 = (int) ((i / (2 * 47f)) > (j / (2 * 47f)) ? (i / (2 * 47f))
								* (i / (2 * 47f))
								: (j / (2 * 47f)) * (j / (2 * 47f))) * 5;
						n3 = (int) ((j / 2) % 47) * 5;
					}
					/*
					 * if(n1%whatnumber!=0) { n1=n1-n1%whatnumber; }
					 * if(n3%whatnumber!=0) { n3=n3-n3%whatnumber; }
					 */
					Color color = new Color(n1, n2, n3);
					img.setRGB(i, j, color.getRGB());
				}
			}
			Color wcolor = new Color(255, 255, 255);
			img.setRGB(img.getWidth() - 1, img.getHeight() - 1, wcolor.getRGB());
			label.setIcon(new ImageIcon(img));
			/*
			 * if(minimg==null)minimg = new BufferedImage(128, 128,
			 * BufferedImage.TYPE_4BYTE_ABGR);//�½�һ������֧��͸����BufferedImage for(int
			 * i=0;i<minimg.getWidth();i++) { for(int
			 * j=0;j<minimg.getHeight();j++) { Color color=new
			 * Color(img.getRGB(i*32, j*32)); minimg.setRGB(i, j,
			 * color.getRGB()); } } minlabel.setIcon(new ImageIcon(minimg));
			 */
		}

		protected void reset(int ii) {
			// TODO Auto-generated method stub
			linevisble = false;
			isclick = false;
			isonbi = false;
			packedInt = 0;
			/*
			 * Color black = new Color(0,0,0); Double b=getL(black); Color write
			 * = new Color(255,255,255); Double w=getL(write);
			 * System.out.print("b="+b+"w="+w+((w+ 0.05) / (b + 0.05))+"");
			 */
			/*
			 * try { InputStream
			 * url=getRGB.class.getClassLoader().getResourceAsStream(path); img
			 * = ImageIO.read(url); } catch (IOException e1) { // TODO
			 * Auto-generated catch block e1.printStackTrace(); }
			 */
			// minlabel.setIcon(new ImageIcon(minimg));
			// �ȸ��ѡ��������õײ�ͼƬ
			if (whathaschoose == 0) {
				resetlabelcolor(whatnumber);
			} else if (whathaschoose == 1) {
				resetlabelcolor(whatnumber);
				changecomplements();
			} else if (whathaschoose == 2) {
				resetlabelcolor(whatnumber);
				changeanalogous();
			} else if (whathaschoose == 3) {
				resetlabelcolor(whatnumber);
				changetriadic();
			} else if (whathaschoose == 4) {
				resetlabelcolor(whatnumber);
				changemonochromatic();
			}
			if (ii == 1) {
				chocelabel.setText("no choose");
				chocelabel2.setText("no choose");
				whathaschoose = 0;
				resetlabelcolor(whatnumber);
				// �Ƿ���Ҫ����img
				chocelabel.setForeground(Color.WHITE);
				chocelabel2.setForeground(Color.WHITE);
				fieldchoose.setBackground(Color.WHITE);
				fieldchoose2.setBackground(Color.WHITE);
				jSlider1.setValue(0);
			}
		}

		protected void setLRGB(float low, float high) {
			// TODO Auto-generated method stub
			// JOptionPane.showMessageDialog(null,"���ȷ����ʼ����..");
			// if(bi==null)bi = new BufferedImage(img.getWidth(),
			// img.getHeight(),
			// BufferedImage.TYPE_4BYTE_ABGR);//�½�һ������֧��͸����BufferedImage
			int c = img.getRGB(3, 0);
			for (int i = 0; i < img.getWidth(); i++) {
				for (int j = 0; j < img.getHeight(); j++) {
					// ���ÿһ����Lֵ
					Color color = new Color(img.getRGB(i, j), true);
					Double l = getL(color);
					Float temp = (float) ((l + 0.05) / (postionL + 0.05));
					if (temp <= 1) {
						temp = (float) ((postionL + 0.05) / (l + 0.05));
					}
					if (low <= temp && temp <= high) {
						img.setRGB(i, j, img.getRGB(i, j));
					} else {
						/*
						 * Color tempcolor=new Color(255,255,255,0); int
						 * rgb=tempcolor.getRGB(); img.setRGB(i, j, rgb);
						 */
						img.setRGB(i, j, c & 0x00ffffff);// ����ѱ�����Ϊ͸��
					}
				}
			}
			g.setColor(new Color(mycolor.getRGB()));
			g.fillOval(lastx - 15, lasty - 15, 30, 30);
			label.update(g);
			label.setIcon(new ImageIcon(img));
			/*
			 * if(minimg==null)minimg = new BufferedImage(128, 128,
			 * BufferedImage.TYPE_4BYTE_ABGR);//�½�һ������֧��͸����BufferedImage for(int
			 * i=0;i<minimg.getWidth();i++) { for(int
			 * j=0;j<minimg.getHeight();j++) { Color color=new
			 * Color(img.getRGB(i*32, j*32)); minimg.setRGB(i, j,
			 * color.getRGB()); } } minlabel.setIcon(new ImageIcon(minimg));
			 */
			isclick = false;
			isonbi = true;
			linevisble = false;
			setchoosecolor2();
		}

		protected Double getL(Color color) {
			// TODO Auto-generated method stub
			Double tempr, tempg, tempb;
			Float colorred = color.getRed() / 255f;
			Float colorgreen = color.getGreen() / 255f;
			Float colorblue = color.getBlue() / 255f;
			if (colorred < 0.03928) {
				tempr = (double) (colorred / 12.92f);
			} else {
				tempr = Math.pow(((colorred + 0.055f) / 1.055f), 2.4);
			}
			if (colorgreen < 0.03928) {
				tempg = (double) (colorgreen / 12.92f);
			} else {
				tempg = Math.pow(((colorgreen + 0.055f) / 1.055f), 2.4);
			}
			if (colorblue < 0.03928) {
				tempb = (double) (colorblue / 12.92f);
			} else {
				tempb = Math.pow(((colorblue + 0.055f) / 1.055f), 2.4);
			}
			return 0.2126 * tempr + 0.7152 * tempg + 0.0722 * tempb;
		}

		void setchoosecolor2() {
			Color temp = new Color(fieldchoose.getBackground().getRGB());
			fieldchoose2.setBackground(new Color(chocelabel.getForeground()
					.getRGB()));
			chocelabel2.setForeground(temp);
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == Levelnull) {
				jSlider1.setValue(1);
			} else if (e.getSource() == Level3) {
				reset(0);
				setLRGB(3f, 21f);
				jSlider1.setValue(3);
			} else if (e.getSource() == Level45) {
				reset(0);
				setLRGB(4.5f, 21f);
				jSlider1.setValue(4);
			} else if (e.getSource() == Level7) {
				reset(0);
				setLRGB(7f, 21f);
				jSlider1.setValue(7);
			} else if (e.getSource() == jrnull) {

			} else if (e.getSource() == jrcomplementary) {
				changecomplements();
				whathaschoose = 1;
			} else if (e.getSource() == jranalogous) {
				changeanalogous();
				whathaschoose = 2;
			} else if (e.getSource() == jrtriadic) {
				changetriadic();
				whathaschoose = 3;
			} else if (e.getSource() == jrmonochromatic) {
				changemonochromatic();
				whathaschoose = 4;
			}

			else {

			}
		}
	}

}
