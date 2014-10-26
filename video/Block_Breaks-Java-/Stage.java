import java.awt.Color;
import java.awt.Graphics;


// ステージ画面

public class Stage extends Scene
{
	private BlockGroup		m_BlockGroup;			// ブロック
	private Player			m_Player;				// プレイヤー
	private Ball			m_Ball;					// ボール

	private ScoreManager	m_ScoreManager;			// スコア管理クラス

	public final static int		GAME_AREA_FRAME		= 15;		// フレームの幅
	public final static int		GAME_AREA_WIDTH		= 480;		// ゲーム領域の横幅
	public final static int		GAME_AREA_HEIGHT	= 450;		// ゲーム領域の縦幅

	// コンストラクタ
	public Stage( ScoreManager manager )
	{
		super();
		m_BlockGroup = new BlockGroup();
		m_BlockGroup.load( "data/stage1.dat" );
		m_Player = new NormalPlayer();
		m_Ball = new NormalBall();
		m_ScoreManager = manager;
	}

	// 更新
	public final void update( InputManager manager )
	{
		// 衝突判定
		if( m_Player.isCollided( m_Ball ) ){
			m_Player.collided( m_Ball );
			m_Ball.collided( m_Player );
		}

		if( manager.isKeepPushed( InputManager.KEY_CODE_ENTER ) ){
			m_Ball.enableMovement();
		}

		m_BlockGroup.update( m_Ball, m_ScoreManager );
		m_Player.update( manager );
		m_Ball.update( m_Player );
	}

	// 描画
	public void draw( Graphics graphics )
	{
		m_BlockGroup.draw( graphics );
		m_Player.draw( graphics );
		m_Ball.draw( graphics );

		// 枠の描画
		graphics.setColor( Color.GRAY );
		graphics.drawLine( Application.canvasX( GAME_AREA_FRAME ), Application.canvasY( GAME_AREA_FRAME ), GAME_AREA_WIDTH + 2, Application.canvasY( GAME_AREA_FRAME ) );
		graphics.drawLine( Application.canvasX( GAME_AREA_FRAME ), Application.canvasY( GAME_AREA_FRAME ), Application.canvasX( GAME_AREA_FRAME ), Application.canvasY( Application.WINDOW_HEIGHT ) - 4 );
		graphics.fillRect( Application.canvasX( GAME_AREA_FRAME ), Application.canvasY( GAME_AREA_HEIGHT + GAME_AREA_FRAME ), GAME_AREA_WIDTH - GAME_AREA_FRAME, GAME_AREA_FRAME - 3 );
		graphics.drawLine( Application.canvasX( GAME_AREA_WIDTH ), Application.canvasY( GAME_AREA_FRAME ), Application.canvasX( GAME_AREA_WIDTH ), Application.canvasY( Application.WINDOW_HEIGHT ) - 4 );

		// スコアの描画
		graphics.setColor( Color.pink );
		graphics.drawString( "Score : ", 520, 100 );
		graphics.drawString( "" + m_ScoreManager.getScore(), 570, 100 );

	}

	// 初期化
	public void Init()
	{

	}
}
