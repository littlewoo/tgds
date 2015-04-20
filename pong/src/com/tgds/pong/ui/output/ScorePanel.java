/**
 * File: ScorePanel.java
 * 
 * Copyright John Littlewood, 2015, all rights reserved. 
 */
package com.tgds.pong.ui.output;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.tgds.api2d.game.scoring.ScoreChangeListener;
import com.tgds.api2d.game.scoring.ScoreKeeper;
import com.tgds.pong.game.Player;

/**
 * A panel for showing the score.
 * 
 * @author John Littlewood
 */
public class ScorePanel extends JPanel {

	/**  */
	private static final long serialVersionUID = 8228826245928308873L;

	/** player 1's score label */
	private final JLabel p1Label;

	/** player 2's scare label */
	private final JLabel p2Label;

	/**
	 * constructor
	 * 
	 * @param players the players being scored. If the collection is smaller
	 *            than 2, exceptions will result. If the collection is bigger
	 *            than 2, any elements after the second may be ignored.
	 */
	public ScorePanel(List<Player> players) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED,
		        java.awt.Color.LIGHT_GRAY, java.awt.Color.DARK_GRAY));
		setBackground(java.awt.Color.BLACK);
		setLayout(new GridLayout(1, 3));

		p1Label = new JLabel("0");
		p1Label.setFont(new Font("Impact", Font.PLAIN, 48));
		p1Label.setForeground(java.awt.Color.WHITE);
		p1Label.setHorizontalAlignment(SwingConstants.CENTER);
		add(p1Label);

		p2Label = new JLabel("0");
		p2Label.setFont(new Font("Impact", Font.PLAIN, 48));
		p2Label.setForeground(java.awt.Color.WHITE);
		p2Label.setHorizontalAlignment(SwingConstants.CENTER);
		add(p2Label);

		players.get(0).addScoreChangeListener(new ScoreChangeListener() {
			@Override
			public void onScoreChanged(ScoreKeeper scoreKeeper, int newScore) {
				p1Label.setText("" + newScore);
			}
		});

		players.get(1).addScoreChangeListener(new ScoreChangeListener() {
			@Override
			public void onScoreChanged(ScoreKeeper scoreKeeper, int newScore) {
				p2Label.setText("" + newScore);
			}
		});
	}

}
