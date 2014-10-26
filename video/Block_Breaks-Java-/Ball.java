import java.awt.Graphics;


// ボール基底クラス

public abstract class Ball extends CollisionObject
{
	protected boolean		m_Movable;			// 自由運動するか？

	public abstract void draw( Graphics graphics );
	public abstract boolean update( Player player );

	public void enableMovement()
	{
		m_Movable = true;
	}

	public boolean isMovable()
	{
		return m_Movable;
	}
}
