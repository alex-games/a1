import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


// 通常時のプレーヤークラス

public class NormalPlayer extends Player
{
	private final static int		POSITION_Y	= 400;		// Y座標（固定）
	private final static int		WIDTH		= 30;		// 横幅
	private final static int		HEIGHT		= 10;		// 縦幅

	private final static int		VELOCITY	= 3;		// 速度

	public NormalPlayer()
	{
		super();
		m_BoundBox = new Rectangle( 200, POSITION_Y, WIDTH, HEIGHT );
	}

	public void draw( Graphics graphics )
	{
		graphics.setColor( Color.yellow );
		graphics.drawRect(	Application.canvasX( m_BoundBox.x ),
							Application.canvasY( m_BoundBox.y ),
							m_BoundBox.width,
							m_BoundBox.height );
	}

	public void update( InputManager manager )
	{
		if( manager.isKeepPushed( InputManager.KEY_CODE_RIGHT ) ){
			m_BoundBox.x += VELOCITY;
		}
		else if( manager.isKeepPushed( InputManager.KEY_CODE_LEFT ) ){
			m_BoundBox.x -= VELOCITY;
		}

		// 範囲をオーバーした時の処理
		int limitLeft = Stage.GAME_AREA_FRAME;												// 左端
		int limitRight = Stage.GAME_AREA_FRAME + Stage.GAME_AREA_WIDTH - m_BoundBox.width * 3 / 2;		// 右端
		if( m_BoundBox.x < limitLeft ){
			m_BoundBox.x = limitLeft;
		}
		else if( m_BoundBox.x > limitRight ){
			m_BoundBox.x = limitRight;
		}
	}

	// ディスパッチ処理
	public void collided( CollisionObject obj )
	{
		obj.processCollision( this );
	}

	// プレイヤーとの衝突処理
	protected void processCollision( Player player )
	{
	}

	// ボールとの衝突処理
	protected void processCollision( Ball ball )
	{
	}

	// ブロックとの衝突処理
	protected void processCollision( Block block )
	{
	}

}
