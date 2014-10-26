import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


// 衝突時反射しないブロック

public class FragileBlock extends Block
{
	// コンストラクタ
	public FragileBlock( int row, int column )
	{
		super();
		m_BoundBox = new Rectangle(	column * ( WIDTH + INTERVAL_X ) + OFFSET_X,
									row * ( HEIGHT + INTERVAL_Y ) + OFFSET_Y,
									WIDTH, HEIGHT );
		m_Reflectable = false;
		m_Score = 70;
		m_Destroyed = false;
	}

	public void update()
	{
		if( m_Destroyed ){
			return;
		}
	}

	public void draw( Graphics graphics )
	{
		if( m_Destroyed ){
			return;
		}

		graphics.setColor( Color.green );
		graphics.drawRect(	Application.canvasX( m_BoundBox.x ),
							Application.canvasY( m_BoundBox.y ),
							m_BoundBox.width,
							m_BoundBox.height );
		graphics.drawLine(	Application.canvasX( m_BoundBox.x ),
							Application.canvasY( m_BoundBox.y + 5 ),
							Application.canvasX( m_BoundBox.x + m_BoundBox.width ),
							Application.canvasY( m_BoundBox.y + 5 ) );
		graphics.drawLine(	Application.canvasX( m_BoundBox.x ),
							Application.canvasY( m_BoundBox.y + 10 ),
							Application.canvasX( m_BoundBox.x + m_BoundBox.width ),
							Application.canvasY( m_BoundBox.y + 10 ) );
	}

	// ディスパッチ処理
	public void collided( CollisionObject obj )
	{
		if( !m_Destroyed ){
			obj.processCollision( this );
		}
	}

	// プレイヤーとの衝突処理
	protected void processCollision( Player player )
	{
		// 処理はなし
	}

	// ボールとの衝突処理
	protected void processCollision( Ball ball )
	{
		m_Destroyed = true;
	}

	// ブロックとの衝突処理
	protected void processCollision( Block block )
	{
		// 自身との衝突であるため、処理は無し
	}
}
