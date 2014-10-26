import java.awt.Graphics;
import java.awt.Rectangle;


// 存在しないブロック

public class NoneBlock extends Block
{
	// コンストラクタ
	public NoneBlock( int row, int column )
	{
		super();
		m_BoundBox = new Rectangle(	column * ( WIDTH + INTERVAL_X ) + OFFSET_X,
									row * ( HEIGHT + INTERVAL_Y ) + OFFSET_Y,
									WIDTH, HEIGHT );
		m_Reflectable = false;
		m_Score = 0;
		m_Destroyed = true;
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
