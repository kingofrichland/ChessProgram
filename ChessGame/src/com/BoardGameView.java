package com;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_RENDER_SPEED;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

abstract public class BoardGameView extends JFrame {
	private static final long serialVersionUID = 1L;
	
    // FLAG - Style
	//protected final String[] FONT_FAMILIES = {"Serif","SansSerif","Monospaced"};
	//protected final int[] 	FONT_STYLE = {Font.PLAIN,Font.ITALIC,Font.BOLD,Font.ITALIC+Font.BOLD};
	protected final int 	FONT_SIZE = 36;
	protected Font fontObj = null;

	protected final float LINE_WIDTH = 2.5f;
	
	// Container
	JPanel gamePanel = null;
	JButton btn1 = null;
	JButton btn2 = null;
	JButton btn3 = null;
	BoardGameModel data = null;
	
	// Message
	String sMessage = "";
	
	public BoardGameView() {
		super("JFrame - BoardGameView");
		gamePanel = new GamePanel();
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(gamePanel, BorderLayout.CENTER);
		cp.add(createButtonPanel(), BorderLayout.SOUTH);
		this.setSize(400, 300);
		this.setVisible(true);
	}
	
	private JPanel createButtonPanel(){
		btn1 = new JButton("Start"); 
		btn2 = new JButton("Option"); 
		btn3 = new JButton("Quit"); 
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,5));
		p.add(btn1);
		p.add(btn2);
		p.add(btn3);
		return p;
	}

	public void addMouseListener(MouseListener ml){
		gamePanel.addMouseListener(ml);
	}
	
	public void addMouseMotionListener(MouseMotionListener mml){
		gamePanel.addMouseMotionListener(mml);
	}
	
	public void addStartButtonListener(ActionListener al){
		btn1.addActionListener(al);
	}
	
	public void addOptionButtonListener(ActionListener al){
		btn2.addActionListener(al);
	}
	
	public void addQuitButtonListener(ActionListener al){
		btn3.addActionListener(al);
	}

	public void drawModel(BoardGameModel data) {
		this.data = data;
		gamePanel.repaint();
	}
	
	private class GamePanel extends JPanel{
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g){
			// Clear Screen
			g.clearRect(0, 0, getWidth(), getHeight()); 
			// Draw Board
			drawBoard( g, data);
			// Draw Piece on board
			drawPieces( g, data);
			// Draw Message on board
			drawMessage( g);
		}

	}
	
	protected void intializeGraphicsSetting(Graphics2D g2){
        //Basic Setup for line
        g2.setStroke(new BasicStroke(
                LINE_WIDTH,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND)); // Set the line width here
        g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(KEY_RENDERING, VALUE_RENDER_SPEED);
    } 

	
    /**
     *<pre>
     * Draw A Circle
     * Draw A Circle with background color in a specific location, where absoluteX,Y is the center of circle
     *</pre>
     */
    protected void drawCircle(Graphics2D g2, int absoluteX, int absoluteY, Color cColor, int flagSize){
        
    	int FLAG_SIZE = flagSize;
    	int FLAG_HALF_SIZE = FLAG_SIZE / 2;
    	
        //Create flag object
    	Ellipse2D.Float ellipseFlagCircle = new Ellipse2D.Float(
                (float) absoluteX-(FLAG_HALF_SIZE),
                (float) absoluteY-(FLAG_HALF_SIZE),
                (float) FLAG_SIZE,
                (float) FLAG_SIZE);//-(Con_ChessConstant.FLAG_HALF_SIZE)
        
        //Create Fill the object : From left-top WHITE to right-down sColor
        GradientPaint gpWhiteToFlagColor = new GradientPaint(
                (float) (absoluteX-(FLAG_HALF_SIZE)),
                (float) (absoluteY-(FLAG_HALF_SIZE)),
                Color.WHITE,
                (float) (absoluteX+(FLAG_SIZE)),
                (float) (absoluteY+(FLAG_SIZE)),
                cColor);
        
        //Fill the circle
        g2.setPaint(gpWhiteToFlagColor);
        g2.fill(ellipseFlagCircle);
        
        //Draw the outline of the object
        g2.setColor(cColor);
        g2.draw(ellipseFlagCircle);
    }   
    
    protected void drawImage(Graphics2D g2, int absoluteX, int absoluteY, String sFileName, int flagSize) {
        BufferedImage bImage = loadImage(sFileName);
        g2.drawImage(bImage, absoluteX, absoluteY, null);
	}
	
	protected BufferedImage loadImage(String fileName) {
		BufferedImage img = null;
		File f = null;
		try {
			f = new File("src/com/"+fileName);
		    img = ImageIO.read(f);
		} catch (IOException e) {
			System.err.println("File Path:"+f.getAbsolutePath());
			e.printStackTrace();
		}
		return img;
	}
    
    /**
     *<pre>
     * Draw A Word in a specific location
     * X,Y is center of word
     *</pre>
     */
    protected void drawWord(Graphics g2, String sFlagWord, int absoluteX, int absoluteY, Color cColor, int fontSize){
        //Draw Flag In Buffered Image
        FontMetrics fm;
        int iFontWidth = 0;
        int iFontHeight = 0;
        int iX = 0;
        int iY = 0;
        
        //Initialize
        //Font font2 = new Font(FONT_FAMILIES[0],FONT_STYLE[2],fontSize);
        Font font2 = getFont(fontSize);
        g2.setFont(font2);
        fm = g2.getFontMetrics();
        
        //Draw Word
        iFontWidth = fm.stringWidth(sFlagWord);                            	    //Formula to calc word width
        iFontHeight = fm.getAscent()+fm.getDescent();          					//Formula to calc word height
        iX = 0;                                                                 //Formula to calc word left-down X
        iY = 0-fm.getLeading();                                                	//Formula to calc word left-down Y
        iX = iX-iFontWidth/2;                                     				//Formula to calc word centred X
        iY = iY+iFontHeight/2;                                   				//Formula to calc word centred Y
        g2.setColor(cColor);
        g2.drawString( sFlagWord, absoluteX+iX, absoluteY+iY);
    }
    
    private Font getFont(float fontSize){
    	if (fontObj==null){
    		try {
	    		fontObj = Font.createFont(Font.TRUETYPE_FONT, new File("config/wt021.ttf"));
	    		fontObj = fontObj.deriveFont(fontSize);
	 		} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	if (fontObj!=null){
    		fontObj = fontObj.deriveFont(fontSize);
    	}
    	return fontObj;
    }

	abstract public void drawBoard(Graphics g, BoardGameModel data) ;
	abstract public void drawPieces(Graphics g, BoardGameModel data) ;
	abstract public ChessPiece getPiece(BoardGameModel data, int x, int y) ;
	//abstract public int getX(int x) ;
	//abstract public int getY(int y) ;
	abstract public int getXByGraphicalX(int iAbsoluteX) ;
	abstract public int getYByGraphicalY(int iAbsoluteY) ;
	abstract public int getGraphicalXByX(int x) ;
	abstract public int getGraphicalYByY(int y) ;

	public void sendMessage(String s) {
		sMessage = s;
	}
	
	private void drawMessage(Graphics g) {
		if (!"".equals(sMessage)) {
			drawWord(g, sMessage, 250, 300, Color.BLACK, 50);
		}
	}
	
	abstract public boolean movePiece(Move move, BoardGameModel model);
	
	
	
}
