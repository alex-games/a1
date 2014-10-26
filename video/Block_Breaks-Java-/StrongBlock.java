import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


// 頑丈なブロック

public class StrongBlock extends Block
{
	private int		m_Durability;		// 頑丈さ

	// コンストラクタ
	public StrongBlock( int row, int column, int durability )
	{
		super();
		m_BoundBox = new Rectangle(	column * ( WIDTH + INTERVAL_X ) + OFFSET_X,
									row * ( HEIGHT + INTERVAL_Y ) + OFFSET_Y,
									WIDTH, HEIGHT );
		m_Reflectable = true;
		m_Score = 1;
		m_Destroyed = false;
		m_Durability = durability;
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

		graphics.setColor( Color.red );
		graphics.drawRect(	Application.canvasX( m_BoundBox.x ),
							Application.canvasY( m_BoundBox.y ),
							m_BoundBox.width,
							m_BoundBox.height );
		graphics.drawString(	"" + m_Durability,
								Application.canvasX( m_BoundBox.x + m_BoundBox.width - 16 ),
								Application.canvasY( m_BoundBox.y + m_BoundBox.height - 2 ) );
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
		--m_Durability;
		if( m_Durability == 0 ){
			m_Destroyed = true;
			m_Score = 500;
		}
	}

	// ブロックとの衝突処理
	protected void processCollision( Block block )
	{
		// 自身との衝突であるため、処理は無し
	}
}
