package graphics;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.CamelUp;
import core.Dice;
import core.GameBet;
import core.Player;
import core.Tile;

@SuppressWarnings("serial")
public class GraphicBoard extends JPanel
{

	private static CamelUp game;
	private static Point[] trackPositions;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File("TrackPositions.dat"));
		JFrame window = new JFrame("Camel Up");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1600, 1200);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		GraphicBoard board = new GraphicBoard();
		window.addMouseListener((new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e)
			{
				//put if it is inside track here
//				if(e.getX()>=1075 && e.getX()<=1475 && e.getY() >= 650 && e.getY() <= 1050)
					//iterate thru tiles and find which one it is in
			}
		}));
		window.add(board);
		game = new CamelUp();
		trackPositions = new Point[16];
		for (int i = 0; i < trackPositions.length; i++) {
			trackPositions[i] = new Point(in.nextInt(), in.nextInt());
		}
		game.roll();
		window.setVisible(true);
		in.close();
	}

	public void paintComponent(Graphics graphics)
	{
		Graphics2D g2D = (Graphics2D) graphics;
		drawDiceRolled(g2D);
		drawBoard(g2D);
		drawPlayer(g2D);
		drawLegBetDock(g2D);
		drawLeaderBoard(g2D);
		repaint();
	}

	public void drawDiceRolled(Graphics2D graphics2D)
	{

		int adjX = 0;
		for (Dice dice : game.getRolled())
		{
			GraphicDice graphicDice = new GraphicDice(new Point(100 + adjX, 100), dice);
			graphicDice.draw(graphics2D);
			adjX += 125;

		}
	}
	
	public void drawBoard(Graphics2D g2)
	{
		Tile[] track = game.getTrack();
		for (int i = 0; i < trackPositions.length; i++)
		{
			GraphicTile graphicTile = new GraphicTile(trackPositions[i].x, trackPositions[i].y, track[i]);
			graphicTile.draw(g2);
		}
		for(int i = 0; i < 6; i++)
		{
			g2.setColor(new Color(244 - i*10, 182 - i*10, 66 - i*10));
			g2.fillRect(1275 + i*25, 550 + i * 25, 300 - i*50, 300 - i * 50);
			g2.setColor(Color.BLACK);
			g2.drawRect(1275 + i*25, 550 + i * 25, 300 - i*50, 300 - i * 50);
		}
	}

	public void drawPlayer(Graphics2D graphics2D)
	{
		Player player = game.getCurrentPlayer();
		GraphicPlayer graphicPlayer = new GraphicPlayer(new Point(625, 700), player);
		graphicPlayer.draw(graphics2D);
	}

	public void drawLegBetDock(Graphics2D graphics2D) {
		GraphicLegBetDock graphicLegBetDock = new GraphicLegBetDock(new Point(500, 500), game.getTopLegs());
		graphicLegBetDock.draw(graphics2D);
	}

	public void drawLeaderBoard(Graphics2D graphics2D) {
		Player[] leaderBoard = Arrays.copyOf(game.getPlayers(), game.getPlayers().length);
		Arrays.sort(leaderBoard);
		graphics2D.setColor(Color.BLACK);
		int x = 300;
		int y = 300;
		graphics2D.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		graphics2D.drawString("Leaderboard", x, y+=20);
		graphics2D.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
		for (int i = 0; i < leaderBoard.length; i++) {
			graphics2D.drawString(i + 1  + ". " + leaderBoard[i].getName() + " " + leaderBoard[i].getCoins(), x, y+=10);
		}
	}


}
