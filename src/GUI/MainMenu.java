package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class MainMenu extends JFrame implements WindowListener
{
	JFrame frame;
	double xScale;
	double yScale;
	Image bg;
	Image orig;
	JButton singlePlayer;
	JButton multiPlayer;
	JButton settings;
	
	public static void main(String[] args)
	{
		new MainMenu();
	}
	
	public MainMenu()
	{
		setTitle("Pokemin Tower Defense");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			System.out.println("Unable to set operating system look and feel");
		}
		
		//line below sets frame to be half of width and height of screen, and puts it in the middle
		setBounds((int) (screen.getWidth()/4), (int) (screen.getHeight()/4), (int) (screen.getWidth()/2), (int) (screen.getHeight()/2));
		addComponentListener(new resizeListener());
		ImageIcon bgIcon = createImageIcon("/images/mainMenuBackground.png");
		bg = bgIcon.getImage();
		orig = bg;
		
		singlePlayer = new JButton("Single Player");
		singlePlayer.setBounds(this.getWidth()/4, this.getHeight()/5, this.getWidth()/2, this.getHeight()/10);
		multiPlayer = new JButton("Online");
		multiPlayer.setBounds(this.getWidth()/4, (this.getHeight()*2)/5, this.getWidth()/2, this.getHeight()/10);
		settings = new JButton("Settings");
		settings.setBounds(this.getWidth()/4, (this.getHeight()*3)/5, this.getWidth()/2, this.getHeight()/10);
		
		
		this.add(singlePlayer);
		this.add(multiPlayer);
		this.add(settings);
		frame = this;
		
		setVisible(true);
	}
	
	
	/**
	 * Creates an image icon based on the given URL
	 * @param url The location of the target image
	 * @return An ImageIcon created from the image found at the url
	 */
	ImageIcon createImageIcon(String url)
	{
		URL imageURL = getClass().getResource(url);
		if(imageURL != null)
		{
			return new ImageIcon(imageURL, "");
		}
		else
		{
			System.out.println("Error loading image at " + url);
			return null;
		}
	}
	
	/**
	 * Listens to the GUI and resizes the buttons
	 * and image while the frame is being resized
	 * @author Desone
	 *
	 */
	class resizeListener implements ComponentListener
	{
		public void componentResized(ComponentEvent arg0)
		{
			
			bg = orig.getScaledInstance(frame.getSize().width, frame.getSize().height, Image.SCALE_FAST);
			singlePlayer.setBounds(frame.getWidth()/4, frame.getHeight()/5, frame.getWidth()/2, frame.getHeight()/10);
			multiPlayer.setBounds(frame.getWidth()/4, (frame.getHeight()*2)/5, frame.getWidth()/2, frame.getHeight()/10);
			settings.setBounds(frame.getWidth()/4, (frame.getHeight()*3)/5, frame.getWidth()/2, frame.getHeight()/10);
			repaint();
		}
		public void componentShown(ComponentEvent arg0){}
		public void componentHidden(ComponentEvent arg0){}
		public void componentMoved(ComponentEvent arg0){}
	}
	
	public void windowActivated(WindowEvent arg0){}
	public void windowClosed(WindowEvent arg0){}
	public void windowClosing(WindowEvent arg0){}
	public void windowDeactivated(WindowEvent arg0){}
	public void windowDeiconified(WindowEvent arg0){}
	public void windowIconified(WindowEvent arg0){}
	public void windowOpened(WindowEvent arg0){}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Insets insets = getInsets();
		g.drawImage(bg, insets.left, insets.top, this);
		multiPlayer.grabFocus();
		settings.grabFocus();
		singlePlayer.grabFocus();
	}

}