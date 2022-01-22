package com.chinese;


//import com.model.Mod_ChessPoint;
import java.awt.Color;
import java.awt.Font;

/**
 * <b>ChineseChessConst</b>
 * <br>Constant Class to store initial value of most of setting,
 * including Board Width, Flag Word etc.
 * This class is full of static variable and static method,
 * and it doesn't expect having any instance.
 *
 */
public class ChineseChessConst {
    
    //Colour
    public static final short Color_RED = 100;
    public static final short Color_BLACK = 200;
    
    //Screen
    public static final int SCREEN_WIDTH = 700;
    public static final int SCREEN_HEIGHT = 550;
    public static final int SCREEN_H_BORDER = 5;
    public static final int SCREEN_V_BORDER = 32;
    private static double SCREEN_WIDTH_RATIO = 1.0;
    private static double SCREEN_HEIGHT_RATIO = 1.0;
    
    //Menu - Font
    public static final String[] 	MENU_FONT_FAMILIES = {"Serif","SansSerif","Monospaced"};
    public static final int[] 		MENU_FONT_STYLE = {Font.PLAIN,Font.ITALIC,Font.BOLD,Font.ITALIC+Font.BOLD};
    public static final String[] 	MENU_FONT_STYLENAME = {"Plain","Italic","Bold","Bold Italic"};
    public static final int 		MENU_FONT_SIZE = 24;
    public static final Color 		MENU_FONT1_COLOR = Color.BLACK;
    public static final Color 		MENU_FONT2_COLOR = Color.BLUE;
    public static final Color 		MENU_FONT3_COLOR = Color.GRAY;
    
    //FLAG - Font
    public static final String[] FONT_FAMILIES = {"Serif","SansSerif","Monospaced"};
    public static final int[] 	FONT_STYLE = {Font.PLAIN,Font.ITALIC,Font.BOLD,Font.ITALIC+Font.BOLD};
    public static final String[] FONT_STYLENAME = {"Plain","Italic","Bold","Bold Italic"};
    public static final int 	FONT_SIZE = 24;
    
    //FLAG SETTING
    public static final int FLAG_SIZE 			= 48;
    public static final int FLAG_HALF_SIZE		= FLAG_SIZE/2;
    
    //BOARD SETTING
    public static final int LEFT_MARGIN = 15;	 //Left Margin of Main Panel, left spacing between the screen and the board left edge
    public static final int TOP_MARGIN = 5;	 //Top Margin of  Main Panel, top spacing between the screen and the board top edge
    public static final float LINE_WIDTH = 2.5f;
    public static final int MENU_WIDTH = 25;
    public static final int LINE_INTERVAL = 54;	//Change this value will change the board size
    public static final int LINE_INTERVAL_HALF = LINE_INTERVAL/2;
    public static final int NUM_OF_VLINE = 9;
    public static final int NUM_OF_HLINE = 10;
    public static final Color BOARD_LINE_COLOR = Color.BLACK;
    public static final Color BOARD_BG_COLOR = Color.LIGHT_GRAY;
    //public static final Mod_ChessPoint BOARD_BOUND_LEFT_TOP = new Mod_ChessPoint((short)1,(short)1);				//Logical Value of Chess Board Left Top
    //public static final Mod_ChessPoint BOARD_BOUND_RIGHT_DOWN = new Mod_ChessPoint((short)9,(short)10);				//Logical Value of Chess Board Right Down
    
    /** AI Ratio
     * For best case, a step will have 200 marks,
     * i.e. 100 marks in Eat Enemy
     *      + 50 marks in Valid Move
     *      + 50 marks in King Distance
     * e.g. Cannon move from (2, 8) -> (2, 1) to Eat Horse in the first step
     *      A = Horse Value (10) * EAT_ENEMY_VALUE_RATIO
     *      A = (10) / 20 * 100
     *      A = 50
     *      B = Num Of Valid Move(45) * NUMOFVALIDMOVE_VALUE_RATIO
     *      B = (45) / 50 * 50
     *      B = 45
     *      C = Cannon Distance to Enemy King Improvement(8-1) * ENEMY_KING_DISTANCE_VALUE_RATIO
     *      C = (7) / 10 * 50
     *      C = 35
     *      Total Move Marks = A + B + C
     *      Total Move Marks = 50 + 45 + 35
     *      Total Move Marks = 130
     * Remarks: This is the basic move value, in actual case, you may need to minus enemy move value
     */
    public static final float EAT_ENEMY_VALUE_RATIO 			= (1.0f / 20.0f) * 100.0f;  //100% - 20 Full mark
    public static final float NUMOFVALIDMOVE_VALUE_RATIO 		= (1.0f / 50.0f) * 50.0f; //50% - 50 Full mark
    public static final float ENEMY_KING_DISTANCE_VALUE_RATIO           = (1.0f / 17.0f) * 0.0f;   //0% - 17 Full mark
    public static final float APPLY_LEARNING_VALUE_RATIO                = (1.0f / 50.0f) * 0.0f;   //0% - 50 Full mark
    
    public static final int AI_ALPLHABETA = 1;
    public static final int AI_MAXTIMEALPLHABETA = 2;
    public static final int AI_METHOD = AI_MAXTIMEALPLHABETA;
    
    public static final int GAMEEND 			= -1;
    public static final int PLAYING				= 1;
    
    //FLAG SETTING - TOP CHESSMAN
    public static final short King1				= 7;
    public static final short Chariot1			= 6;
    public static final short Horse1				= 5;
    public static final short Cannon1				= 4;
    public static final short Servant1			= 3;
    public static final short Chancellor1			= 2;
    public static final short Pawn1				= 1;
    
    //FLAG SETTING - BOTTOM CHESSMAN
    public static final short King2				= -7;
    public static final short Chariot2			= -6;
    public static final short Horse2				= -5;
    public static final short Cannon2				= -4;
    public static final short Servant2			= -3;
    public static final short Chancellor2			= -2;
    public static final short Pawn2				= -1;
    
    public static final int KingValue			= 10000;
    public static final int ChariotValue        = 1400;
    public static final int HorseValue			= 700;
    public static final int CannonValue			= 700;
    public static final int ServantValue        = 250;
    public static final int ChancellorValue     = 250;
    public static final int PawnValue			= 100;
    
    public static final int KingFlexibleValue			= 0;
    public static final int ChariotFlexibleValue        = 6;
    public static final int HorseFlexibleValue			= 12;
    public static final int CannonFlexibleValue			= 6;
    public static final int ServantFlexibleValue        = 1;
    public static final int ChancellorFlexibleValue     = 1;
    public static final int PawnFlexibleValue			= 15;
    
    public static final int KingStepSize		= 1;
    public static final int ChariotStepSize 	= 10;
    public static final int HorseStepSize		= 2;
    public static final int CannonStepSize	= 10;
    public static final int ServantStepSize	= 1;
    public static final int ChancellorStepSize= 2;
    public static final int PawnStepSize		= 1;
    
    public static final String[] FlagWord1 = new String[]{
        "帥","車","馬","砲","仕","相","兵"
    };
    public static final String[] FlagWord2 = new String[]{
        "將","車","馬","炮","士","象","卒"
    };
    /*public static final Color[] FlagColor = new Color[]{
        Color.RED,
        Color.RED,
        Color.RED,
        Color.RED,
        Color.RED,
        Color.RED,
        Color.RED,
        Color.BLACK,
        Color.BLACK,
        Color.BLACK,
        Color.BLACK,
        Color.BLACK,
        Color.BLACK,
        Color.BLACK
    };*/
    public static final short[] FlagColor = new short[]{
        Color_RED,
        Color_RED,
        Color_RED,
        Color_RED,
        Color_RED,
        Color_RED,
        Color_RED,
        Color_BLACK,
        Color_BLACK,
        Color_BLACK,
        Color_BLACK,
        Color_BLACK,
        Color_BLACK,
        Color_BLACK
    };
    
    //Player
    public static short NUM_OF_PLAYER = 2;
    public static final short NO_PLAYER = 0;
    public static final short PLAYER1 = 100;
    public static final short PLAYER2 = 200;
    public static final String PLAYER_NAME1 = "PLAYER_NAME1";
    public static final String PLAYER_NAME2 = "PLAYER_NAME2";
    public static short NUM_OF_CHESSMAN = 16;
    public static final short CHESS_PLAYER_RED = 100;
    public static final short CHESS_PLAYER_BLACK = 200;
    public static final short CHESS_PLAYER_TOP = 0;
    public static final short CHESS_PLAYER_BOTTOM = 1;
    public static final short CHESS_PLAYER_LEFT = 2;
    public static final short CHESS_PLAYER_RIGHT = 3;
    public static final short HUMAN = 100;
    public static final short COMPUTER = 200;
    public static final short CLASSPLAYER = 300;

    //Game Mode
    public static final short GAME_MODE_FIRST_PLAYER            = PLAYER1; 
    public static final int GAME_MODE_HUMAN_VS_COMPUTER         = 100;
    public static final int GAME_MODE_COMPUTER_VS_HUMAN         = 200;
    public static final int GAME_MODE_COMPUTER_VS_COMPUTER      = 300;
    public static final int GAME_MODE_CLASSPLAYER_VS_CLASSPLAYER = 400;
    public static final int GAME_MODE_HUMAN_VS_CLASSPLAYER      = 500;
    public static final int GAME_MODE_VS  = GAME_MODE_CLASSPLAYER_VS_CLASSPLAYER;
    
    //MIDDLE SQUARE SETTING : 2 players
    //public static final Mod_ChessPoint MIDDLE_SQUARE_LEFT_TOP = new Mod_ChessPoint((short)1,(short)5);				//Logical Value of Middle Square Left Top
    //public static final Mod_ChessPoint MIDDLE_SQUARE_RIGHT_BOTTOM = new Mod_ChessPoint((short)(1+8),(short)(5+1));		//Logical Value of Middle Square Left Top
    //MIDDLE SQUARE SETTING : 4 players
    //public static final int NUM_OF_PLAYER = 4;
    //public static final Mod_ChessPoint MIDDLE_SQUARE_LEFT_TOP = new Mod_ChessPoint(5,5);						//Logical Value of Middle Square Left Top
    //public static final Mod_ChessPoint MIDDLE_SQUARE_RIGHT_BOTTOM = new Mod_ChessPoint(5+8,5+8);				//Logical Value of Middle Square Left Top
    
    //MY SIDE SQUARE SETTING
    //public static Mod_ChessPoint[] MY_SIDE_SQUARE_LEFT_TOP = new Mod_ChessPoint[NUM_OF_PLAYER];				//Logical Value of Middle Square Left Top
    //public static Mod_ChessPoint[] MY_SIDE_SQUARE_RIGHT_BOTTOM = new Mod_ChessPoint[NUM_OF_PLAYER];				//Logical Value of Middle Square Right Bottom
    
    //NINE UNCLE SQUARE SETTING
    //public static final Mod_ChessPoint[] NINE_UNCLE_SQUARE_LEFT_TOP = new Mod_ChessPoint[NUM_OF_PLAYER];				//Logical Value of Nine Uncle Square Left Top
    //public static final Mod_ChessPoint[] NINE_UNCLE_SQUARE_RIGHT_BOTTOM = new Mod_ChessPoint[NUM_OF_PLAYER];			//Logical Value of Nine Uncle Square Right Bottom
    
    //Buffered Image Setting
    public static final int BI_LEFT_MARGIN = FLAG_HALF_SIZE; //Left Margin of Buffered Image, or the Spacing between board edge and the first left vertical line
    public static final int BI_TOP_MARGIN = FLAG_HALF_SIZE;	 //Top Margin of Buffered Image, or the Spacing between board edge and the first top horizontal line
    public static final int BI_WIDTH = (LINE_INTERVAL * (NUM_OF_VLINE - 1 + 1));	 //Width of Buffered Image
    public static final int BI_HEIGHT = (LINE_INTERVAL * (NUM_OF_HLINE - 1 + 1));	 //Height of Buffered Image
    
    //FLAG_TYPE - BLACK
    public static final int FLAG_TYPE_KING 			= 1700;
    public static final int FLAG_TYPE_CHARIOT 		= 1600;
    public static final int FLAG_TYPE_HORSE 		= 1500;
    public static final int FLAG_TYPE_CANNON 		= 1400;
    public static final int FLAG_TYPE_SERVANT 		= 1300;
    public static final int FLAG_TYPE_CHANCELLOR 	= 1200;
    public static final int FLAG_TYPE_PAWN 			= 1100;
    
    //FLAG_TYPE - RED
    public static final int FLAG_TYPE_CHIEF 		= 2700;
    public static final int FLAG_TYPE_CASTLE 		= 2600;
    public static final int FLAG_TYPE_KNIGHT 		= 2500;
    public static final int FLAG_TYPE_FORTRESS 		= 2400;
    public static final int FLAG_TYPE_OFFICER 		= 2300;
    public static final int FLAG_TYPE_BISHOP 		= 2200;
    public static final int FLAG_TYPE_ARMS 			= 2100;
    
    //DUAL CONSTRUCTOR
    public ChineseChessConst(){}
    
    //FUNCTION
    /*
    public static BufferedImage loadImage(String name) {
        //String imgFileName = "images/"+name+"";
        String imgFileName = ""+name+"";
        URL url = Con_ChessConstant.class.getResource(imgFileName);
        BufferedImage img = null;
        File file = new File(imgFileName);
        try {
            img = ImageIO.read(url);
            //img = ImageIO.read(file);
        } catch (Exception e) {
            System.err.println(""+e.toString()+"\nImage cannot be loaded : "+imgFileName
                    + "\nFilePath: "+file.getAbsolutePath());
        }
        return img;
    }*/
    
    public static void setWScreenRatio(double w){
        SCREEN_WIDTH_RATIO = w;
    }
    
    public static double getWScreenRatio(){
        return SCREEN_WIDTH_RATIO;
    }
    
    public static void setHScreenRatio(double h){
        SCREEN_HEIGHT_RATIO = h;
    }
    
    public static double getHScreenRatio(){
        return SCREEN_HEIGHT_RATIO;
    }
    
    //Convert X,Y to Buffered Image absolute X-axis, Y-axis values
    public static int getGraphicalXByX(int x){
        return getGraphicalXByX(x, false);
    }
    
    //Convert X,Y to absolute X-axis, Y-axis values
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public static int getGraphicalXByX(int x, boolean bIsChessBoardOnly){
        if (bIsChessBoardOnly)
            return (BI_LEFT_MARGIN)+((x-1)*LINE_INTERVAL);
        else
            return (LEFT_MARGIN + BI_LEFT_MARGIN)+((x-1)*LINE_INTERVAL);
    }
    
    //Convert Screen absolute X-axis, Y-axis to X,Y values
    public static int getGraphicalYByY(int y){
        return getGraphicalYByY(y, false);
    }
    
    //Convert X,Y to absolute X-axis, Y-axis values
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public static int getGraphicalYByY(int y, boolean bIsChessBoardOnly){
        if (bIsChessBoardOnly)
            return (BI_TOP_MARGIN)+((y-1)*LINE_INTERVAL);
        else
            return (TOP_MARGIN + BI_TOP_MARGIN)+((y-1)*LINE_INTERVAL);
    }
    
    //Convert Screen absolute X-axis, Y-axis to X,Y values
    public static short getXByGraphicalX(int iAbsoluteX){
        return getXByGraphicalX(iAbsoluteX, false);
    }
    
    //Convert X,Y to absolute X-axis, Y-axis values
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public static short getXByGraphicalX(int iAbsoluteX, boolean bIsChessBoardOnly){
        short result = 0;
        if (bIsChessBoardOnly)
            result = (short)(Math.round((float)(iAbsoluteX-(BI_LEFT_MARGIN))/(LINE_INTERVAL))+1);
        else
            result = (short)(Math.round((float)(iAbsoluteX-(LEFT_MARGIN+BI_LEFT_MARGIN))/(LINE_INTERVAL))+1);
        return result;
    }
    
    //Convert Screen absolute X-axis, Y-axis to X,Y values
    public static short getYByGraphicalY(int iAbsoluteY){
        return getYByGraphicalY(iAbsoluteY, false);
    }
    
    //Convert absolute X-axis, Y-axis values to X,Y
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public static short getYByGraphicalY(int iAbsoluteY, boolean bIsChessBoardOnly){
        short result = 0;
        if (bIsChessBoardOnly)
            result = (short)(Math.round((float)(iAbsoluteY-(BI_TOP_MARGIN))/(LINE_INTERVAL))+1);
        else
            result = (short)(Math.round((float)(iAbsoluteY-(TOP_MARGIN+BI_TOP_MARGIN))/(LINE_INTERVAL))+1);
        return result;
    }
    
    public static void initializeProgramSetting(){
        
    }
    /*
    public static void initializeArray(){
        //MY SIDE SQUARE SETTING : TOP SIDE
        int TOP = CHESS_PLAYER_TOP;
        MY_SIDE_SQUARE_LEFT_TOP[TOP] =
                new Mod_ChessPoint(MIDDLE_SQUARE_LEFT_TOP.x,(short)(MIDDLE_SQUARE_LEFT_TOP.y - 4));				//Logical Value of Middle Square Left Top
        MY_SIDE_SQUARE_RIGHT_BOTTOM[TOP] =
                new Mod_ChessPoint(MIDDLE_SQUARE_RIGHT_BOTTOM.x,MIDDLE_SQUARE_LEFT_TOP.y);				//Logical Value of Middle Square Left Top
        
        //MY SIDE SQUARE SETTING : BOTTOM SIDE
        int BOTTOM = CHESS_PLAYER_BOTTOM;
        MY_SIDE_SQUARE_LEFT_TOP[BOTTOM] =
                new Mod_ChessPoint(MIDDLE_SQUARE_LEFT_TOP.x,MIDDLE_SQUARE_RIGHT_BOTTOM.y);				//Logical Value of Middle Square Left Top
        MY_SIDE_SQUARE_RIGHT_BOTTOM[BOTTOM] =
                new Mod_ChessPoint(MIDDLE_SQUARE_RIGHT_BOTTOM.x,(short)(MIDDLE_SQUARE_RIGHT_BOTTOM.y+4));				//Logical Value of Middle Square Left Top
        
        int LEFT = CHESS_PLAYER_LEFT;
        int RIGHT = CHESS_PLAYER_RIGHT;
        if (NUM_OF_PLAYER == 4){
            //MY SIDE SQUARE SETTING : LEFT SIDE
            MY_SIDE_SQUARE_LEFT_TOP[LEFT] =
                    new Mod_ChessPoint((short)(MIDDLE_SQUARE_LEFT_TOP.x - 4),MIDDLE_SQUARE_LEFT_TOP.y);				//Logical Value of Middle Square Left Top
            MY_SIDE_SQUARE_RIGHT_BOTTOM[LEFT] =
                    new Mod_ChessPoint(MIDDLE_SQUARE_LEFT_TOP.x,MIDDLE_SQUARE_RIGHT_BOTTOM.y);				//Logical Value of Middle Square Left Top
            
            //MY SIDE SQUARE SETTING : RIGHT SIDE
            MY_SIDE_SQUARE_LEFT_TOP[RIGHT] =
                    new Mod_ChessPoint(MIDDLE_SQUARE_RIGHT_BOTTOM.x,MIDDLE_SQUARE_LEFT_TOP.y);				//Logical Value of Middle Square Left Top
            MY_SIDE_SQUARE_RIGHT_BOTTOM[RIGHT] =
                    new Mod_ChessPoint((short)(MIDDLE_SQUARE_LEFT_TOP.x+4),MIDDLE_SQUARE_RIGHT_BOTTOM.y);				//Logical Value of Middle Square Left Top
        }
        
        //NINE UNCLE SQUARE SETTING : TOP SIDE
        NINE_UNCLE_SQUARE_LEFT_TOP[TOP] =
                new Mod_ChessPoint((short)(MY_SIDE_SQUARE_LEFT_TOP[TOP].x + 3), MY_SIDE_SQUARE_LEFT_TOP[TOP].y);				//Logical Value of Middle Square Left Top
        NINE_UNCLE_SQUARE_RIGHT_BOTTOM[TOP] =
                new Mod_ChessPoint((short)(NINE_UNCLE_SQUARE_LEFT_TOP[TOP].x + 2), (short)(NINE_UNCLE_SQUARE_LEFT_TOP[TOP].y + 2));				//Logical Value of Middle Square Left Top
        
        //NINE UNCLE SQUARE SETTING : BOTTOM SIDE
        NINE_UNCLE_SQUARE_LEFT_TOP[BOTTOM] =
                new Mod_ChessPoint((short)(MY_SIDE_SQUARE_LEFT_TOP[BOTTOM].x + 3), (short)(MY_SIDE_SQUARE_RIGHT_BOTTOM[BOTTOM].y - 2));				//Logical Value of Middle Square Left Top
        NINE_UNCLE_SQUARE_RIGHT_BOTTOM[BOTTOM] =
                new Mod_ChessPoint((short)(NINE_UNCLE_SQUARE_LEFT_TOP[BOTTOM].x + 2), (short)(NINE_UNCLE_SQUARE_LEFT_TOP[BOTTOM].y + 2));				//Logical Value of Middle Square Left Top
        
        if (NUM_OF_PLAYER == 4){
            //NINE UNCLE SQUARE SETTING : LEFT SIDE
            NINE_UNCLE_SQUARE_LEFT_TOP[LEFT] =
                    new Mod_ChessPoint(MY_SIDE_SQUARE_LEFT_TOP[LEFT].x, (short)(MY_SIDE_SQUARE_LEFT_TOP[LEFT].y + 3));				//Logical Value of Middle Square Left Top
            NINE_UNCLE_SQUARE_RIGHT_BOTTOM[LEFT] =
                    new Mod_ChessPoint((short)(NINE_UNCLE_SQUARE_LEFT_TOP[LEFT].x + 2), (short)(NINE_UNCLE_SQUARE_LEFT_TOP[LEFT].y + 2));				//Logical Value of Middle Square Left Top
            
            //NINE UNCLE SQUARE SETTING : RIGHT SIDE
            NINE_UNCLE_SQUARE_LEFT_TOP[RIGHT] =
                    new Mod_ChessPoint((short)(MY_SIDE_SQUARE_RIGHT_BOTTOM[RIGHT].x - 2), (short)(MY_SIDE_SQUARE_RIGHT_BOTTOM[RIGHT].y + 3));				//Logical Value of Middle Square Left Top
            NINE_UNCLE_SQUARE_RIGHT_BOTTOM[RIGHT] =
                    new Mod_ChessPoint((short)(NINE_UNCLE_SQUARE_LEFT_TOP[RIGHT].x + 2), (short)(NINE_UNCLE_SQUARE_LEFT_TOP[RIGHT].y + 2));				//Logical Value of Middle Square Left Top
        }
    }
    */
    /**
     * This function is to control the player index.
     * It return the index of the player next to the given player
     * For example:
     * There are 4 players in the game: P1, P2, P3, P4 etc.
     * NEXT_PLAYER(P1) will be P2; NEXT_PLAYER(P4) will be P1; etc.
     */
    public static short NEXT_PLAYER(short iPlayerIndex){
        short nextPlayer = PLAYER1;
        if (iPlayerIndex == PLAYER1){
            nextPlayer = PLAYER2;
        }else{
            nextPlayer = PLAYER1;
        }
        
        //Another implementation of Next Player
        //iCurrPlayerIndex = (iCurrPlayerIndex+1) % Con_ChessConstant.NUM_OF_PLAYER;
        
        return nextPlayer;
    }
    
    /**
     * Return the direction type
     */
    public static short getPlayerDirType(short iChessmanTypeIndex){
        if (iChessmanTypeIndex > 0)
            return CHESS_PLAYER_BOTTOM;
        else
            return CHESS_PLAYER_TOP;
        
    }
    
    /**
     * Return the direction type
     */
    public static String getPlayerName(short iPlayerIndex){
        String sPlayerName = PLAYER_NAME1;
        if (iPlayerIndex == PLAYER1){
            sPlayerName = PLAYER_NAME1;
        }else{
            sPlayerName = PLAYER_NAME2;
        }
        return sPlayerName;
    }


    // I20100003 START
    /*
     * For 1 dimension board, convert the position to relative X
     */
    public static short getX(int pos){
        short x = (short)(pos % NUM_OF_VLINE);
        return (x==0)?NUM_OF_VLINE:x;
    }

    /*
     * For 1 dimension board, convert the position to relative Y
     */
    public static short getY(int pos){
        short y = (short)(pos / NUM_OF_VLINE);
        return (pos%NUM_OF_VLINE==0)?y:(short)(y+1);
    }
    // I20100003 END


}