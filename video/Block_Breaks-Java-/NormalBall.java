import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;


// 通常状態のボール

public class NormalBall extends Ball
{

	private double		m_VelocityX;		// X方向速度
	private double		m_VelocityY;		// Y方向の速度

	private Rectangle2D.Double	m_Pos;		// 詳細な場所を保存

	public NormalBall()
	{
		super();
		m_Pos = new Rectangle2D.Double( 200.0, 70.0, 8.0, 8.0 );
		m_BoundBox = m_Pos.getBounds();
		m_VelocityX = 0.3;
		m_VelocityY = -1.0;
		m_Movable = false;
	}

	// 描画
	public void draw( Graphics graphics )
	{
		graphics.setColor( Color.WHITE );
		graphics.fillArc(	Application.canvasX( m_BoundBox.x ),
							Application.canvasY( m_BoundBox.y ),
							m_BoundBox.width,
							m_BoundBox.height,
							0, 360 );
	}

	// 更新
	public boolean update( Player player )
	{
		m_Pos.x += m_VelocityX;
		m_Pos.y += m_VelocityY;

		m_BoundBox = m_Pos.getBounds();

		if( m_BoundBox.y < Stage.GAME_AREA_FRAME ){
			m_VelocityY = - m_VelocityY;
		}
		if( m_BoundBox.x < Stage.GAME_AREA_FRAME ){
			m_VelocityX = - m_VelocityX;
		}
		if( m_BoundBox.x > Stage.GAME_AREA_WIDTH - m_BoundBox.width ){
			m_VelocityX = - m_VelocityX;
		}

		// 時間が経過するにつれ、速度UP
		if( m_VelocityX > 0.0 ){
			m_VelocityX += 0.0002;
		}
		else{
			m_VelocityX -= 0.0002;
		}
		if( m_VelocityY > 0.0 ){
			m_VelocityY += 0.0002;
		}
		else{
			m_VelocityY -= 0.0002;
		}

		// プレイヤー上に固定される状態
		if( !m_Movable ){
			m_Pos.x = player.getPosX() + ( player.getWidth() - m_BoundBox.width ) / 2 ;
			m_Pos.y = player.getPosY() - player.getHeight();
		}

		// 画面外へボールが落ちてしまった。
		if( m_BoundBox.y > Stage.GAME_AREA_HEIGHT ){
			m_Movable = false;
			m_VelocityX = 0.3;
			m_VelocityY = -1.0;
			return false;
		}

		return true;
	}

	// ディスパッチ処理
	public void collided( CollisionObject obj )
	{
		obj.processCollision( this );
	}

	// プレイヤーとの衝突処理
	protected void processCollision( Player player )
	{
		// Y方向の速度を反転させる。
		int side = getIntersectSide( player );
		if( mostEffectedAtTop( side ) ){
			m_VelocityY = - m_VelocityY;
		}
		else if( mostEffectedAtRight( side ) || mostEffectedAtLeft( side ) ){
			m_VelocityX = - m_VelocityX;
		}
	}

	// ボールとの衝突処理
	protected void processCollision( Ball ball )
	{
		// 自身との衝突なので、無視。
	}

	// ブロックとの衝突処理
	protected void processCollision( Block block )
	{
		if( block.isReflectable() ){
			int side = getIntersectSide( block );
			if( mostEffectedAtTop( side ) || mostEffectedAtBottom( side ) ){
				m_VelocityY = - m_VelocityY;
			}
			else if( mostEffectedAtLeft( side ) || mostEffectedAtRight( side ) ){
				m_VelocityX = - m_VelocityX;
			}
		}
	}

}
