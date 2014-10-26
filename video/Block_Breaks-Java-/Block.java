import java.awt.Graphics;


// ブロック基底クラス

public abstract class Block extends CollisionObject
{
	protected boolean		m_Reflectable;		// 衝突可能か？
	protected int			m_Score;			// 得点
	protected boolean		m_Destroyed;		// 破壊されたか？

	protected static final int		WIDTH		= 25;		// 横幅
	protected static final int		HEIGHT		= 15;		// 縦幅
	protected static final int		INTERVAL_X	= 5;		// X成分のブロック間隔
	protected static final int		INTERVAL_Y	= 5;		// Y成分のブロック間隔


	protected static final int		OFFSET_X	= 25;		// 描画時のオフセット（X成分）
	protected static final int		OFFSET_Y	= 20;		// 描画時のオフセット（Y成分）

	public abstract void draw( Graphics graphics );
	public abstract void update();

	// 衝突可能か？
	public boolean isReflectable()
	{
		return m_Reflectable;
	}

	// 得点を取得
	public int getScore()
	{
		return m_Score;
	}

	// 破壊されたか
	public boolean isDestroyed()
	{
		return m_Destroyed;
	}
}
