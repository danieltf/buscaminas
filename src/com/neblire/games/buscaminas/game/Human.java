package com.neblire.games.buscaminas.game;

import com.neblire.games.buscaminas.activities.GameActivity;

public class Human extends Player {

	public Human(GameActivity activity, String name) {
		super(activity, name);
	}

	@Override
	public void turn() {
		activity.board.enableInteraction();
	}
}
