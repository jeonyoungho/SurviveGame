package surviveGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class surviveGame extends JFrame {
	private FirstPanel firstPanel = new FirstPanel();
	private StartPanel startPanel = new StartPanel();
	private ControlPanel1 controlPanel1 = new ControlPanel1();
	private ControlPanel2 controlPanel2 = new ControlPanel2();
	private ControlPanel3 controlPanel3 = new ControlPanel3();
	private CreatorPanel creatorPanel = new CreatorPanel();
	private XMLReader xml = new XMLReader("surviveGamexml.xml");
	private GamePanel gamePanel;
	private String mainBGM = "media/bgm.wav";

	public surviveGame() {
		Node blockGameNode = xml.getBlockGameElement();
		Node sizeNode = XMLReader.getNode(blockGameNode, XMLReader.E_SIZE);
		String w = XMLReader.getAttr(sizeNode, "w");
		String h = XMLReader.getAttr(sizeNode, "h");
		setSize(Integer.parseInt(w), Integer.parseInt(h));

		setTitle("Survive Game");
		Container c = getContentPane();

		add(makeToolBar(), BorderLayout.NORTH);
		add(firstPanel, BorderLayout.CENTER);

		Sound.loadAudio(mainBGM);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	// 첫화면
	// 첫화면
	class FirstPanel extends JPanel {
		private Font f = new Font("Arial", Font.ITALIC, 45);
		private JLabel start;
		private JLabel control;
		private JLabel creator;
		private JLabel close;

		public FirstPanel() {
			setLayout(null);

			start = new JLabel("Start");
			start.setFont(f);
			start.setForeground(Color.RED);
			start.setBounds(335, 350, 97, 34);

			control = new JLabel("Control");
			control.setFont(f);
			control.setForeground(Color.RED);
			control.setBounds(310, 420, 147, 34);

			creator = new JLabel("Creator");
			creator.setFont(f);
			creator.setForeground(Color.RED);
			creator.setBounds(306, 490, 154, 34);

			close = new JLabel("Close");
			close.setFont(f);
			close.setForeground(Color.RED);
			close.setBounds(326, 560, 164, 54);

			add(start);
			add(control);
			add(creator);
			add(close);

			// start 마우스 이벤트
			start.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(startPanel, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setForeground(Color.RED);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.CYAN);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.RED);
				}
			});

			// control 마우스 이벤트
			control.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(controlPanel1, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setForeground(Color.RED);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.CYAN);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.RED);
				}
			});

			// creator 마우스 이벤트
			creator.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(creatorPanel, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setForeground(Color.RED);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.CYAN);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.RED);
				}
			});

			// close 마우스 이벤트
			close.addMouseListener(new MouseAdapter() {
				private JFileChooser chooser = new JFileChooser();

				public void mouseReleased(MouseEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "confirm", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						System.exit(0);
					} else
						return;
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.CYAN);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.RED);
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon icon = new ImageIcon("media/firstimg.PNG");
			Image img = icon.getImage();

			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// Control 누르면 나오는 ControlPanel1
	class ControlPanel1 extends JPanel {
		private JLabel home;
		private JLabel next;

		public ControlPanel1() {
			setLayout(null);

			ImageIcon homeIcon1 = new ImageIcon("media/Home1.PNG");
			ImageIcon homeIcon2 = new ImageIcon("media/Home2.PNG");
			ImageIcon nextIcon1 = new ImageIcon("media/Next1.PNG");
			ImageIcon nextIcon2 = new ImageIcon("media/Next2.PNG");

			home = new JLabel(homeIcon1);
			home.setBounds(720, 10, 50, 49);

			next = new JLabel(nextIcon1);
			next.setBounds(600, 600, 45, 45);

			add(home);
			add(next);

			home.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(firstPanel, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setIcon(homeIcon1);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(homeIcon2);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(homeIcon1);
				}
			});

			next.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(controlPanel2, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setIcon(nextIcon1);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(nextIcon2);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(nextIcon1);
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon bg = new ImageIcon("media/Control1.PNG");
			Image bgimg = bg.getImage();
			g.drawImage(bgimg, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// ControalPanel2
	class ControlPanel2 extends JPanel {
		private JLabel home;
		private JLabel prev;
		private JLabel next;

		public ControlPanel2() {
			setLayout(null);

			ImageIcon homeIcon1 = new ImageIcon("media/Home1.PNG");
			ImageIcon homeIcon2 = new ImageIcon("media/Home2.PNG");
			ImageIcon prevIcon1 = new ImageIcon("media/Prev1.PNG");
			ImageIcon nextIcon1 = new ImageIcon("media/Next1.PNG");
			ImageIcon prevIcon2 = new ImageIcon("media/Prev2.PNG");
			ImageIcon nextIcon2 = new ImageIcon("media/Next2.PNG");

			home = new JLabel(homeIcon1);
			home.setBounds(720, 10, 50, 49);

			prev = new JLabel(prevIcon1);
			prev.setBounds(140, 600, 45, 45);

			next = new JLabel(nextIcon1);
			next.setBounds(600, 600, 45, 45);

			add(home);
			add(prev);
			add(next);

			home.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(firstPanel, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setIcon(homeIcon1);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(homeIcon2);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(homeIcon1);
				}
			});

			prev.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(controlPanel1, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setIcon(prevIcon1);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(prevIcon2);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(prevIcon1);
				}
			});

			next.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(controlPanel3, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setIcon(nextIcon1);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(nextIcon2);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(nextIcon1);
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon bg = new ImageIcon("media/Control2.PNG");
			Image bgimg = bg.getImage();
			g.drawImage(bgimg, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// ControlPanel3
	class ControlPanel3 extends JPanel {
		private JLabel home;
		private JLabel prev;

		public ControlPanel3() {
			setLayout(null);

			ImageIcon homeIcon1 = new ImageIcon("media/Home1.PNG");
			ImageIcon homeIcon2 = new ImageIcon("media/Home2.PNG");
			ImageIcon prevIcon1 = new ImageIcon("media/Prev1.PNG");
			ImageIcon prevIcon2 = new ImageIcon("media/Prev2.PNG");

			home = new JLabel(homeIcon1);
			home.setBounds(720, 10, 50, 49);

			prev = new JLabel(prevIcon1);
			prev.setBounds(140, 600, 45, 45);

			add(home);
			add(prev);

			home.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(firstPanel, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setIcon(homeIcon1);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(homeIcon2);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(homeIcon1);
				}
			});

			prev.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(controlPanel2, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setIcon(prevIcon1);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(prevIcon2);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(prevIcon1);
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon bg = new ImageIcon("media/Control3.PNG");
			Image bgimg = bg.getImage();
			g.drawImage(bgimg, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// Creator 누르면 나오는 CreatorPanel
	class CreatorPanel extends JPanel {
		private JLabel home;

		public CreatorPanel() {
			setLayout(null);

			ImageIcon homeIcon1 = new ImageIcon("media/Home1.PNG");
			ImageIcon homeIcon2 = new ImageIcon("media/Home2.PNG");

			home = new JLabel(homeIcon1);
			home.setBounds(720, 10, 50, 49);

			add(home);

			home.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(firstPanel, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setIcon(homeIcon1);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(homeIcon2);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(homeIcon1);
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon icon = new ImageIcon("media/Creator.PNG");
			Image img = icon.getImage();

			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// Start 누르면 나오는 StartPanel
	class StartPanel extends JPanel {
		private JLabel map1;
		private JLabel map2;
		private JLabel home;

		public StartPanel() {
			setLayout(null);

			ImageIcon homeIcon1 = new ImageIcon("media/Home1.PNG");
			ImageIcon homeIcon2 = new ImageIcon("media/Home2.PNG");
			ImageIcon mapIcon1 = new ImageIcon("media/Map1-1.PNG");
			ImageIcon mapIcon2 = new ImageIcon("media/Map1-2.PNG");
			ImageIcon mapIcon3 = new ImageIcon("media/Map2-1.PNG");
			ImageIcon mapIcon4 = new ImageIcon("media/Map2-2.PNG");

			home = new JLabel(homeIcon1);
			home.setBounds(720, 10, 50, 49);

			map1 = new JLabel(mapIcon1);
			map1.setBounds(50, 350, 320, 280);

			map2 = new JLabel(mapIcon3);
			map2.setBounds(400, 350, 320, 280);

			add(home);
			add(map1);
			add(map2);

			home.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					f.add(firstPanel, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setIcon(homeIcon1);
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(homeIcon2);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(homeIcon1);
				}
			});

			map1.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					xml = new XMLReader("surviveGamexml.xml");
					gamePanel = new GamePanel(xml.getGamePanelElement());
					f.add(gamePanel, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setIcon(mapIcon1);

					gamePanel.setFocusable(true);
					gamePanel.requestFocus();
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(mapIcon2);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(mapIcon1);
				}
			});

			map2.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					JFrame f = (JFrame) l.getTopLevelAncestor();
					JPanel p = (JPanel) l.getParent();

					f.remove(p);
					xml = new XMLReader("surviveGamexml2.xml");
					gamePanel = new GamePanel(xml.getGamePanelElement());
					f.add(gamePanel, BorderLayout.CENTER);
					f.revalidate();
					f.repaint();

					l.setIcon(mapIcon3);

					gamePanel.setFocusable(true);
					gamePanel.requestFocus();
				}

				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(mapIcon4);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setIcon(mapIcon3);
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon icon = new ImageIcon("media/firstimg.PNG");
			Image img = icon.getImage();

			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

	// Start 누르면 나오는 Panel
	class GamePanel extends JPanel {

		int score = 0;
		JLabel hpLabel = new JLabel("5");
		JLabel hpImgLabel = new JLabel(new ImageIcon("media/heart.png"));
		JLabel scoreLabel = new JLabel(Integer.toString(score));
		JLabel scoreName = new JLabel("SCORE");
		JLabel escLabel = new JLabel(new ImageIcon("media/esc.png"));
		JLabel gameoverLabel = new JLabel(new ImageIcon("media/gameover.png"));
		JLabel finalScoreLabel;
		JLabel choiceLevelLabel = new JLabel("Choice Level");
		JLabel continueLabel = new JLabel("Continue");
		JLabel homeLabel = new JLabel("Home");
		JLabel exitLabel = new JLabel("Game Exit");
		Font f = new Font("Arial", Font.PLAIN, 45);
		boolean drawOrNot = true;
		ImageIcon bgImg;
		Player player;
		int bulletW, bulletH;
		ImageIcon bulletIcon;
		Block[] block;
		ArrayList<BulletThread> bulletThreadList = new ArrayList<BulletThread>();
		ArrayList<moveThread> moveThreadList = new ArrayList<moveThread>();
		ArrayList<BlockBulletThread> blockBulletThreadList = new ArrayList<BlockBulletThread>();
		ArrayList<Block> blockList = new ArrayList<Block>();
		
		timeLabel tl = new timeLabel();
		TimerThread tt = new TimerThread(tl);
		// ArrayList<TimerThread> TimerThreadList = new ArrayList<TimerThread>();

		public GamePanel(Node gamePanelNode) {
			setLayout(null);
			
			hpLabel.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			hpLabel.setBounds(740,670,30,30);
			hpLabel.setForeground(Color.RED);
			add(hpLabel);
			
			hpImgLabel.setBounds(635,657,100,50);
			add(hpImgLabel);
			
			scoreLabel.setLocation(720, 0);
			scoreName.setForeground(Color.WHITE);
			scoreLabel.setSize(100, 40);
			scoreName.setFont(new Font("TimesRoman", Font.PLAIN, 25));

			scoreName.setLocation(600, 0);
			scoreLabel.setForeground(Color.WHITE);
			scoreName.setSize(100, 40);
			scoreLabel.setFont(new Font("TimesRoman", Font.PLAIN, 25));

			add(scoreLabel);
			add(scoreName);

			continueLabel.setFont(f);
			continueLabel.setBounds(295, 350, 200, 34);
			continueLabel.setForeground(Color.RED);
			continueLabel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.CYAN);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.RED);
				}
			});

			homeLabel.setFont(f);
			homeLabel.setBounds(320, 410, 200, 34);
			homeLabel.setForeground(Color.RED);
			homeLabel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.CYAN);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.RED);
				}

				public void mouseReleased(MouseEvent e) {
					JFrame f = (JFrame) homeLabel.getTopLevelAncestor();
					JPanel p = (JPanel) homeLabel.getParent();
					f.remove(p);
					f.add(firstPanel);
					f.revalidate();
					f.repaint();
				}
			});

			choiceLevelLabel.setFont(f);
			choiceLevelLabel.setBounds(260, 440, 300, 34);
			choiceLevelLabel.setForeground(Color.RED);
			choiceLevelLabel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.CYAN);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.RED);
				}

				public void mouseReleased(MouseEvent e) {
					JFrame f = (JFrame) choiceLevelLabel.getTopLevelAncestor();
					JPanel p = (JPanel) choiceLevelLabel.getParent();
					f.remove(p);
					f.add(startPanel);
					f.revalidate();
					f.repaint();
				}
			});

			exitLabel.setFont(f);
			exitLabel.setBounds(280, 470, 370, 34);
			exitLabel.setForeground(Color.RED);
			exitLabel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.CYAN);
				}

				public void mouseExited(MouseEvent e) {
					JLabel l = (JLabel) e.getSource();
					l.setForeground(Color.RED);
				}

				public void mouseReleased(MouseEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "confirm", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						System.exit(0);
					} else
						return;
				}
			});

			gameoverLabel.setBounds(130, 150, 500, 500);
			escLabel.setBounds(130, 150, 500, 500);

			continueLabel.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					drawOrNot = true;
					remove(escLabel);
					remove(continueLabel);
					remove(homeLabel);
					remove(exitLabel);
					tt.wakeUp();
					for (int i = 0; i < blockBulletThreadList.size(); i++) {
						blockBulletThreadList.get(i).wakeUp();
					}
					for (int i = 0; i < moveThreadList.size(); i++) {
						moveThreadList.get(i).wakeUp();
					}
					for (int i = 0; i < bulletThreadList.size(); i++) {
						bulletThreadList.get(i).wakeUp();
					}
				}
			});

			Node bgNode = XMLReader.getNode(gamePanelNode, XMLReader.E_BG);
			bgImg = new ImageIcon(bgNode.getTextContent());

			// read <Fish><Obj>s from the XML parse tree, make Food objects, and add them to
			// the FishBowl panel.
			Node blockNode = XMLReader.getNode(gamePanelNode, XMLReader.E_BLOCK);
			int blockCnt = Integer.parseInt(XMLReader.getAttr(blockNode, "count"));
			block = new Block[blockCnt * 2];
			NodeList nodeList = blockNode.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				// if(node.getNodeType() != Node.ELEMENT_NODE) {
				// System.out.println("element if문 : " + i);
				// continue;
				// }
				// found!!, <Obj> tag

				if (node.getNodeName().equals(XMLReader.E_OBJ)) {
					int x = Integer.parseInt(XMLReader.getAttr(node, "x"));
					int y = Integer.parseInt(XMLReader.getAttr(node, "y"));
					int w = Integer.parseInt(XMLReader.getAttr(node, "w"));
					int h = Integer.parseInt(XMLReader.getAttr(node, "h"));
					int type = Integer.parseInt(XMLReader.getAttr(node, "type"));

					ImageIcon icon = new ImageIcon(XMLReader.getAttr(node, "img"));
					block[i] = new Block(x, y, w, h, type, icon);
					blockList.add(block[i]);
					add(block[i]);
					block[i].mt.start();

				} else if (node.getNodeName().equals(XMLReader.E_PLAYER)) {
					int x = Integer.parseInt(XMLReader.getAttr(node, "x"));
					int y = Integer.parseInt(XMLReader.getAttr(node, "y"));
					int w = Integer.parseInt(XMLReader.getAttr(node, "w"));
					int h = Integer.parseInt(XMLReader.getAttr(node, "h"));

					ImageIcon icon = new ImageIcon(XMLReader.getAttr(node, "img"));
					player = new Player(x, y, w, h, icon);
					add(player);
				} else if (node.getNodeName().equals(XMLReader.E_BULLET)) {
					bulletW = Integer.parseInt(XMLReader.getAttr(node, "w"));
					bulletH = Integer.parseInt(XMLReader.getAttr(node, "h"));

					bulletIcon = new ImageIcon(XMLReader.getAttr(node, "img"));
				}
			}

			addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					Bullet b = new Bullet(player.getX(), player.getY(), player.getWidth(), player.getHeight(), bulletW,
							bulletH, bulletIcon);
					add(b);
					b.bulletTh.start();
				}
			});

			addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						drawOrNot = false;
						tt.flag = false;
						for (int i = 0; i < blockBulletThreadList.size(); i++) {
							blockBulletThreadList.get(i).flag = false;
						}
						for (int i = 0; i < moveThreadList.size(); i++) {
							moveThreadList.get(i).flag2 = false;
						}
						for (int i = 0; i < bulletThreadList.size(); i++) {
							bulletThreadList.get(i).flag = false;
						}
						add(continueLabel);
						add(homeLabel);
						add(exitLabel);
						add(escLabel);
						revalidate();
						repaint();
					}

					int x = player.getX();
					int y = player.getY();

					switch (e.getKeyChar()) {
					case 'w':
						y -= 5;
						break;
					case 's':
						y += 5;
						break;
					case 'd':
						x += 5;
						break;
					case 'a':
						x -= 5;
						break;
					}

					if ((x < 0) || (x + player.getWidth() > 785) || y < 550 || y + player.getHeight() >= 765) {
						switch (e.getKeyChar()) {
						case 'w':
							y += 5;
							break;
						case 's':
							y -= 5;
							break;
						case 'd':
							x -= 5;
							break;
						case 'a':
							x += 5;
							break;
						}
					} else {
						player.setLocation(x, y);
						repaint();
					}

				}
			});

			// clockLabel.setLocation(255,-230);
			// add(clockLabel);
			add(tl);
			tt.start();
		}

		class Block extends JLabel {
			Image img;
			// private JLabel label = new JLabel();
			moveThread mt;
			int type;
			int hp;

			public Block(int x, int y, int w, int h, int type, ImageIcon icon) {
				this.setBounds(x, y, w, h);
				img = icon.getImage();
				mt = new moveThread(this);
				moveThreadList.add(mt);
				this.type = type;
				hp = type;
			}

			public void paintComponent(Graphics g) {
				if (drawOrNot)
					g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		}

		// Block 움직이는, 총알 발사 스레드
		class moveThread extends Thread {
			private int rand = -1;
			private Block block;
			private int labelX = -1, labelY = -1;
			private int labelW = 0, labelH = 0;
			private int count = 0;
			Container c;
			boolean flag;
			boolean flag2;
			boolean endFlag = false;
			int shoot;

			public moveThread(Block block) {
				flag = true;
				flag2 = true;
				this.block = block;
				labelX = block.getX();
				labelY = block.getY();
				labelW = block.getWidth();
				labelH = block.getHeight();
				shoot = -1;
			}

			synchronized public void wakeUp() {
				flag2 = true;
				notify();
			}

			synchronized public void run() {
				rand = (int) ((Math.random() * 4) + 1);
				c = (Container) block.getParent();
				while (true) {
					if (endFlag) {
						moveThreadList.remove(this);
						c.remove(block);
						c.revalidate();
						c.repaint();
						return;
					}
					if (!flag2) {
						try {
							wait();
						} catch (InterruptedException e) {
							System.out.println("moveThread wait error ! ");
						}
					}
					shoot = (int) (Math.random() * 50);
					if (shoot == 1) {
						BlockBulletThread b = new BlockBulletThread(block);
						blockBulletThreadList.add(b);
						b.start();
					}
					if (!flag) {
						moveThreadList.remove(this);
						c.remove(block);

//					switch (this.block.type) {
//	                  case 1:
//	                     score += 250;
//	                     break;
//	                  case 2:
//	                     score += 100;
//	                     break;
//	                  case 3:
//	                     score += 50;
//	                     break;
//	                  }
//	                scoreLabel.setText(Integer.toString(score));

						c.revalidate();
						c.repaint();
						return;
					}
					if (count == 15) {
						rand = (int) ((Math.random() * 4) + 1);
						count = 0;
					}

					try {
						switch (rand) {
						case 1:
							labelX += 2;
							break;
						case 2:
							labelX -= 2;
							break;
						case 3:
							labelY += 2;
							break;
						case 4:
							labelY -= 2;
							break;
						}
						if ((labelX >= 0 && labelX + labelW <= 785) && (labelY >= 30 && (labelY + labelH) <= 550)) {
							block.setLocation(labelX, labelY);
							c.repaint();
							count++;
						} else {
							switch (rand) {
							case 1:
								labelX -= 2;
								break;
							case 2:
								labelX += 2;
								break;
							case 3:
								labelY -= 2;
								break;
							case 4:
								labelY += 2;
								break;
							}

							count = 15;
						}
						sleep(50);
					} catch (InterruptedException e) {
						moveThreadList.remove(this);
						return;
					}
				}
			}
		}

		// Bullet 움s직이는 스레드
		class BulletThread extends Thread {
			private int x, y;
			private JLabel label;
			private Container c;
			private int speed = 10; // 속도
			boolean flag = true;
			boolean endFlag = false;

			public BulletThread(JLabel label) {
				this.label = label;
				x = label.getX();
				y = label.getY();
			}

			synchronized public void wakeUp() {
				flag = true;
				notify();
			}

			synchronized public void run() {
				c = (Container) label.getParent();
				while (true) {
					if (endFlag) {
						c.remove(label);
						c.revalidate();
						bulletThreadList.remove(this);
						return;
					}
					if (!flag) {
						try {
							wait();
						} catch (InterruptedException e) {
							System.out.println("bulletThread flag error ! ");
							bulletThreadList.remove(this);
							return;
						}
					}
					y -= speed;
					if (y < 0) {
						c.remove(label);
						c.revalidate();
						bulletThreadList.remove(this);
						return;
					}
					
					//블록이 총알에 맞는 코드
					for (int i = 0; i < block.length; i++) {
						if (i % 2 == 1) {
							if (block[i].mt.flag) {
								if ((block[i].getX() <= x && block[i].getX() + block[i].getWidth() >= x)
										&& ((block[i].getY() + block[i].getHeight() >= y) && block[i].getY() < y)) {
									block[i].hp--;
									if (block[i].hp == 0) {
										switch (block[i].type) {
										case 1:
											score += 50;
											break;
										case 2:
											score += 100;
											break;
										case 3:
											score += 200;
											break;
										}
										scoreLabel.setText(Integer.toString(score));
										block[i].mt.flag = false;
										blockList.remove(block[i]);
										if(blockList.isEmpty()) {
											drawOrNot = false;
											tt.flag = false;
											for (int k = 0; k < blockBulletThreadList.size(); k++) {
												blockBulletThreadList.get(k).endFlag = true;
											}
											for (int k = 0; k < moveThreadList.size(); k++) {
												moveThreadList.get(k).endFlag = true;
											}
											for (int k = 0; k < bulletThreadList.size(); k++) {
												bulletThreadList.get(k).endFlag = true;
											}

											finalScoreLabel = new JLabel("SCORE " + Integer.toString(score));
											finalScoreLabel.setFont(f);
											finalScoreLabel.setForeground(Color.BLACK);
											finalScoreLabel.setBounds(250, 370, 300, 34);
											add(finalScoreLabel);

											add(choiceLevelLabel);
											add(gameoverLabel);
										}
									}
									c.remove(label);
									c.revalidate();
									bulletThreadList.remove(this);
									return;
								}
							}
						}
					}
					try {
						label.setLocation(x, y);
						sleep(50);
					} catch (InterruptedException e) {
						System.out.println("bullet Thread error !");
						bulletThreadList.remove(this);
						return;
					}
					c.repaint();
				}
			}
		}

		class BlockBullet extends JLabel {
			Image img;

			public BlockBullet(ImageIcon icon) {
				img = icon.getImage();
			}

			public void paintComponent(Graphics g) {
				if (drawOrNot)
					g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		}

		// 블록이 쏘는 총알 스레드
		class BlockBulletThread extends Thread {
			private int x, y;
			private BlockBullet label = new BlockBullet(new ImageIcon("media/bullet1.png"));
			private Container c;
			private int speed = 10; // 속도
			private int width = 8;
			private int height = 20;
			boolean flag;
			boolean endFlag = false;
			BlockBulletThread b = this;

			public BlockBulletThread(Block block) {
				flag = true;
				this.c = block.getParent();
				x = block.getX() + block.getWidth() / 2 - width / 2;
				y = block.getY() + block.getWidth();
				label.setBounds(x, y, width, height);
				c.add(label);
			}

			synchronized public void wakeUp() {
				flag = true;
				notify();
			}

			synchronized public void run() {
				while (true) {
					if (endFlag) {
						blockBulletThreadList.remove(this);
						c.remove(label);
						c.revalidate();
						c.repaint();
						return;
					}
					if (!flag) {
						try {
							wait();
						} catch (InterruptedException e) {
							System.out.println("BlockBulletThread wait error !");
							return;
						}
					}
					y += speed;
					if (y > 765) {
						blockBulletThreadList.remove(this);
						c.remove(label);
						c.revalidate();
						c.repaint();
						return;
					}
					// java.lang.NullPointerException
					try {
						if ((player.getY() <= y && y < player.getY() + player.getHeight())
								|| ((player.getY() < y + height && y + height <= player.getY() + player.getHeight()))) {
							if ((player.getX() < x + width && x + width <= player.getX() + player.getWidth())
									|| (player.getX() <= x + width && x + width < player.getX() + player.getWidth())) {
								player.hp--;
								hpLabel.setText(Integer.toString(player.hp));
								if (player.hp == 0) { // 맞는 코드
									drawOrNot = false;
									tt.flag = false;
									for (int i = 0; i < blockBulletThreadList.size(); i++) {
										blockBulletThreadList.get(i).endFlag = true;
									}
									for (int i = 0; i < moveThreadList.size(); i++) {
										moveThreadList.get(i).endFlag = true;
									}
									for (int i = 0; i < bulletThreadList.size(); i++) {
										bulletThreadList.get(i).endFlag = true;
									}

									finalScoreLabel = new JLabel("SCORE " + Integer.toString(score));
									finalScoreLabel.setFont(f);
									finalScoreLabel.setForeground(Color.BLACK);
									finalScoreLabel.setBounds(250, 370, 300, 34);
									add(finalScoreLabel);

									add(choiceLevelLabel);
									add(gameoverLabel);
								}
								blockBulletThreadList.remove(this);
								c.remove(label);
								c.revalidate();
								c.repaint();
								return;
							}
						}
					} catch (NullPointerException e) {
						blockBulletThreadList.remove(b);
						c.remove(label);
						c.revalidate();
						c.repaint();
						return;
					}
					try {
						label.setLocation(x, y);
						sleep(50);
					} catch (InterruptedException e) {
						System.out.println("blockbullet Thread error !");
						return;
					}
					c.repaint();
				}
			}
		}

		class Player extends JLabel {
			Image img;
			int hp;

			// private JLabel label = new JLabel();
			public Player(int x, int y, int w, int h, ImageIcon icon) {
				this.setBounds(x, y, w, h);
				this.hp = 5;
				img = icon.getImage();
			}

			public void paintComponent(Graphics g) {
				if (drawOrNot)
					g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		}

		class Bullet extends JLabel {
			private Image img;
			BulletThread bulletTh;

			public Bullet(int playerX, int playerY, int playerW, int playerH, int w, int h, ImageIcon icon) {
				super();
				this.setBounds(playerX + playerW / 2 - w / 2, playerY, w, h);
				img = icon.getImage();
				bulletTh = new BulletThread(this);
				bulletThreadList.add(bulletTh);
			}

			public void paintComponent(Graphics g) {
				if (drawOrNot) {
					g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				}
			}
		}

		public void paintComponent(Graphics g) {
			g.drawImage(bgImg.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		}

		// 시간 레이블
		class timeLabel extends JLabel {
			private int n = 61; // 제한시간

			public timeLabel() {
				setLocation(370, 0);
				setForeground(Color.WHITE);
				setSize(100, 40);
				setText(Integer.toString(n));
				setFont(new Font("TimesRoman", Font.PLAIN, 25));
			}
		}

		// 시간 스레드
		class TimerThread extends Thread {
			JLabel label;
			boolean flag;
			boolean endFlag = false;
			Container c;

			public TimerThread(Component j) {
				c = (Container) j.getParent();
				this.label = (JLabel) j;
				flag = true;
			};

			synchronized public void wakeUp() {
				flag = true;
				notify();
			}

			synchronized public void run() {
				while (true) {
					if (endFlag) {
						c.remove(label);
						return;
					}
					if (!flag) {
						try {
							wait();
						} catch (InterruptedException e) {
							System.out.println("TimerThread error !");
							return;
						}
					}
					int n = Integer.parseInt(label.getText());
					n--;
					if (n <= 10) {
						label.setForeground(Color.RED);
					}
					label.setText(Integer.toString(n));
					try {
						Thread.sleep(1000);
						if (n == 0) {
							drawOrNot = false;
							tt.flag = false;
							for (int i = 0; i < blockBulletThreadList.size(); i++) {
								blockBulletThreadList.get(i).endFlag = true;
							}
							for (int i = 0; i < moveThreadList.size(); i++) {
								moveThreadList.get(i).endFlag = true;
							}
							for (int i = 0; i < bulletThreadList.size(); i++) {
								bulletThreadList.get(i).endFlag = true;
							}

							finalScoreLabel = new JLabel("SCORE " + Integer.toString(score));
							finalScoreLabel.setFont(f);
							finalScoreLabel.setForeground(Color.BLACK);
							finalScoreLabel.setBounds(250, 370, 300, 34);
							add(finalScoreLabel);

							add(choiceLevelLabel);
							add(gameoverLabel);
							revalidate();
							repaint();
							return;
						}
					} catch (InterruptedException e) {
						return;
					}

				}
			}

		}
	}

	private JToolBar makeToolBar() {
		JToolBar tBar = new JToolBar();

		JButton mainSong = new JButton("배경음악");
		mainSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.unloadAudio();
				Sound.loadAudio(mainBGM); // XML 파일 읽기
			}
		});
		tBar.add(mainSong);

		JButton startSong = new JButton("재생");
		startSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.startAudio();
			}
		});
		tBar.add(startSong);

		JButton pauseSong = new JButton("일시정지");
		pauseSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.pauseAudio();
			}
		});
		tBar.add(pauseSong);

		JButton restartSong = new JButton("다시시작");
		restartSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.restartAudio();
			}
		});
		tBar.add(restartSong);

		JButton openSong = new JButton("곡 목록");
		openSong.addActionListener(new ActionListener() {
			private JFileChooser chooser = new JFileChooser();

			public void actionPerformed(ActionEvent e) {
				int ret = chooser.showOpenDialog(surviveGame.this);
				if (ret == JFileChooser.APPROVE_OPTION) {
					String filePath = chooser.getSelectedFile().getPath();
					Sound.unloadAudio();
					Sound.loadAudio(filePath); // XML 파일 읽기
				}
			}
		});
		tBar.add(openSong);

		JButton backBtn = new JButton("종료");
		backBtn.addActionListener(new ActionListener() {
			private JFileChooser chooser = new JFileChooser();

			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "confirm", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else
					return;

			}
		});
		tBar.add(backBtn);
		tBar.addSeparator();

		return tBar;
	}

	public static void main(String[] args) {
		new surviveGame();

	}

}