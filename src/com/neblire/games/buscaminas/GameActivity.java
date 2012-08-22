package com.neblire.games.buscaminas;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ZoomControls;
import com.neblire.games.buscaminas.R;
import com.neblire.games.buscaminas.game.BoardView;
import com.neblire.games.buscaminas.game.GameConfig;
import com.neblire.games.buscaminas.game.GameControl;
import com.neblire.games.buscaminas.game.Sound;

//	NOTA:	Debido a que no hay ningun activity que se vaya a colocar parcialmente delante de este,
//			los eventos onPause() y onResume() no son necesario implementarlos.

public class GameActivity extends MyActivity {
	public BoardView board;
	public TextView score;
	public TextView info;
	public ZoomControls zoom;
	public GameControl game;
	public int rows;
	
	public boolean ready;
	
	@Override
	protected void onSaveInstanceState(Bundle save) {
		ready = false;
		super.onSaveInstanceState(save);
		save.putBundle("GameControl", game.pause());
		save.putString("score", score.getText().toString());
		save.putString("info", info.getText().toString());
	}

	@Override
	protected void onRestoreInstanceState(Bundle restore) {
		super.onRestoreInstanceState(restore);
	}
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		ready = false;
		Sound.initialize(this);

		GameConfig config = (GameConfig) getIntent().getExtras().get(GameConfig.GAME_CONFIG_KEY);
		
		rows = config.rows;
		createLayout(/*config.rows*/);
		if (bundle != null){
			Bundle b = bundle.getBundle("GameControl");
			score.setText(bundle.getString("score"));
			info.setText(bundle.getString("info"));
			game = new GameControl(this, b);
			game.resume();
			return;
		}
		game = new GameControl(this, config);
		game.start();
	}

	public void onDestroy() {
		super.onDestroy();
		game.stop();
		Sound.release();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		game.resume();
	}

	private void createLayout(/*int rows*/) {
//		switch (rows) {
//		case 8: {
//			setContentView(R.layout.main8);
//			break;
//		}
//		case 9: {
//			setContentView(R.layout.main9);
//			break;
//		}
//		case 10: {
//			setContentView(R.layout.main10);
//			break;
//		}
//		case 11: {
//			setContentView(R.layout.main11);
//			break;
//		}
//		case 12: {
//			setContentView(R.layout.main12);
//			break;
//		}
//		case 13: {
//			setContentView(R.layout.main13);
//			break;
//		}
//		case 14: {
//			setContentView(R.layout.main14);
//			break;
//		}
//		case 15: {
//			setContentView(R.layout.main15);
//			break;
//		}
//		case 16: {
//			setContentView(R.layout.main16);
//			break;
//		}
//		case 17: {
//			setContentView(R.layout.main17);
//			break;
//		}
//		case 18: {
//			setContentView(R.layout.main18);
//			break;
//		}
//		case 19: {
//			setContentView(R.layout.main19);
//			break;
//		}
//		case 20: {
//		setContentView(R.layout.main20);
//			break;
//		}
//		}
		setContentView(R.layout.mainok);
		board = (BoardView) findViewById(R.id.game_view);
		score = (TextView) findViewById(R.id.marcador);
		info = (TextView) findViewById(R.id.info);
		zoom = (ZoomControls) findViewById(R.id.zoom);
		zoom.setVisibility(View.INVISIBLE);
		board.setFocusable(true);
		board.setFocusableInTouchMode(true);
		board.setZoomControls(zoom);
	}
}