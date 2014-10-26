import java.awt.Rectangle;


// 衝突基底クラス

public abstract class CollisionObject
{
	protected Rectangle		m_BoundBox;		// 衝突判定

	public static final int		INTERSECT_TOP		= 0x1;		// 上辺が交差した
	public static final int		INTERSECT_BOTTOM	= 0x2;		// 下辺が交差した
	public static final int		INTERSECT_LEFT		= 0x4;		// 左辺が交差した
	public static final int		INTERSECT_RIGHT		= 0x8;		// 右辺が交差した


	public abstract void collided( CollisionObject obj );

	protected abstract void processCollision( Player player );
	protected abstract void processCollision( Ball ball );
	protected abstract void processCollision( Block block );

	// 衝突したか？
	public boolean isCollided( CollisionObject obj )
	{
		if( obj.getBoundBox().intersects( m_BoundBox ) ){
			return true;
		}

		return false;
	}

	// 衝突判定を取得する
	protected Rectangle getBoundBox()
	{
		return m_BoundBox;
	}

	// 詳細な衝突判定
	public int getIntersectSide( CollisionObject obj )
	{
		Rectangle peer = obj.getBoundBox();
		int result = 0;
		int weight = 0;			// 当たり判定に寄与した度合い
		int mostEffected = 0;	// 最も寄与した辺

		// 自分自身の当たり判定
		int selfTop = m_BoundBox.y;
		int selfBottom = m_BoundBox.y + m_BoundBox.height;
		int selfLeft = m_BoundBox.x;
		int selfRight = m_BoundBox.x + m_BoundBox.width;

		// 相手の当たり判定
		int peerTop = peer.y;
		int peerBottom = peer.y + peer.height;
		int peerLeft = peer.x;
		int peerRight = peer.x + peer.width;

		// 上辺が交差
		if( peerTop > selfTop && peerTop < selfBottom && peerLeft < selfRight && peerRight > selfLeft ){
			int w = 0;	// 影響度
			if( peerTop > selfTop ){
				w = peerTop - selfTop;
			}
			else if( peerTop < selfBottom ){
				w = selfBottom - peerTop;
			}
			// 影響度の更新
			if( w > weight ){
				weight = w;
				mostEffected = INTERSECT_TOP;
			}
			result |= INTERSECT_TOP;
		}
		// 下辺が交差
		if( peerBottom > selfTop && peerBottom < selfBottom && peerLeft < selfRight && peerRight > selfLeft ){
			int w = 0;	// 影響度
			if( peerBottom > selfTop ){
				w = peerBottom - selfTop;
			}
			else if( peerBottom < selfBottom ){
				w = selfBottom - peerBottom;
			}
			// 影響度の更新
			if( w > weight ){
				weight = w;
				mostEffected = INTERSECT_BOTTOM;
			}
			result |= INTERSECT_BOTTOM;
		}
		// 左辺が交差
		if( peerLeft > selfLeft && peerLeft < selfRight && peerTop < selfBottom && peerBottom > selfTop ){
			int w = 0;		// 影響度
			if( peerLeft > selfLeft ){
				w = peerLeft - selfLeft;
			}
			else if( peerLeft < selfRight ){
				w = selfRight - peerLeft;
			}
			// 影響度の更新
			if( w > weight ){
				weight = w;
				mostEffected = INTERSECT_LEFT;
			}
			result |= INTERSECT_LEFT;
		}
		// 右辺が交差
		if( peerRight > selfLeft && peerRight < selfRight && peerTop < selfBottom && peerBottom > selfTop ){
			int w = 0;		// 影響度
			if( peerRight > selfLeft ){
				w = peerRight - selfLeft;
			}
			else if( peerRight < selfRight ){
				w = selfRight - peerRight;
			}
			// 影響度の更新
			if( w > weight ){
				weight = w;
				mostEffected = INTERSECT_RIGHT;
			}
			result |= INTERSECT_RIGHT;
		}

		return result | mostEffected << 8;
	}

	public static boolean intersectAtTop( int val )
	{
		return ( val & INTERSECT_TOP ) != 0;
	}

	public static boolean intersectAtBottom( int val )
	{
		return ( val & INTERSECT_BOTTOM ) != 0;
	}

	public static boolean intersectAtLeft( int val )
	{
		return ( val & INTERSECT_LEFT ) != 0;
	}

	public static boolean intersectAtRight( int val )
	{
		return ( val & INTERSECT_RIGHT ) != 0;
	}

	public static boolean mostEffectedAtTop( int val )
	{
		return ( val & ( INTERSECT_TOP << 8 ) ) != 0;
	}

	public static boolean mostEffectedAtBottom( int val )
	{
		return ( val & ( INTERSECT_BOTTOM << 8 ) ) != 0;
	}

	public static boolean mostEffectedAtLeft( int val )
	{
		return ( val & ( INTERSECT_LEFT << 8 ) ) != 0;
	}

	public static boolean mostEffectedAtRight( int val )
	{
		return ( val & ( INTERSECT_RIGHT << 8 ) ) != 0;
	}
}
