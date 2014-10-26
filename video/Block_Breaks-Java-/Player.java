import java.awt.Graphics;


// プレイヤー基底クラス

public abstract class Player extends CollisionObject
{

	public abstract void draw( Graphics graphics );
	public abstract void update( InputManager manager );

	public int getPosX()
	{
		return m_BoundBox.x;
	}

	public int getPosY()
	{
		return m_BoundBox.y;
	}

	public int getWidth()
	{
		return m_BoundBox.width;
	}

	public int getHeight()
	{
		return m_BoundBox.height;
	}
}
