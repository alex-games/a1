import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


// 入力を受け付けるクラス

public class InputManager implements KeyListener
{

	public static final int KEY_CODE_RIGHT	= 0;		// 左
	public static final int KEY_CODE_LEFT	= 1;		// 右
	public static final int KEY_CODE_UP		= 2;		// 上
	public static final int KEY_CODE_DOWN	= 3;		// 下
	public static final int KEY_CODE_ENTER	= 4;		// 決定ボタン

	private static final int KEY_STATE_RELEASED		= 0;	// ボタンは離されている
	private static final int KEY_STATE_PUSHED 		= 1;	// ボタンは押されている
	private static final int KEY_STATE_KEEP_PUSHED	= 2;	// ボタンは継続して押されている。

	private int[] m_KeyState;

	// コンストラクタ
	public InputManager()
	{
		m_KeyState = new int [ 5 ];
		for( int i = 0; i < m_KeyState.length; ++i ){
			m_KeyState[ i ] = 0;
		}
	}

	// キーは押されているか？
	boolean isPushed( int key )
	{
		return m_KeyState[ key ] == KEY_STATE_PUSHED;
	}

	// キーは押し続けられているか？
	boolean isKeepPushed( int key )
	{
		return m_KeyState[ key ] == KEY_STATE_KEEP_PUSHED;
	}

	// キーは放されているか？
	boolean isReleased( int key )
	{
		return m_KeyState[ key ] == KEY_STATE_RELEASED;
	}

	// キーが押された時
	public void keyTyped( KeyEvent e )
	{
		int key = e.getKeyCode();

		switch( key ){
			case KeyEvent.VK_RIGHT:		// 右
				m_KeyState[ KEY_CODE_RIGHT ] = KEY_STATE_PUSHED;
				break;
			case KeyEvent.VK_LEFT:		// 左
				m_KeyState[ KEY_CODE_LEFT ] = KEY_STATE_PUSHED;
				break;
			case KeyEvent.VK_UP:		// 上
				m_KeyState[ KEY_CODE_UP ] = KEY_STATE_PUSHED;
				break;
			case KeyEvent.VK_DOWN:		// 下
				m_KeyState[ KEY_CODE_DOWN ] = KEY_STATE_PUSHED;
				break;
			case KeyEvent.VK_Z:			// Zキー
				m_KeyState[ KEY_CODE_ENTER ] = KEY_STATE_PUSHED;
				break;
			default:
				break;
		}
	}

	// キーを押し続けられている時
	public void keyPressed( KeyEvent e )
	{
		int key = e.getKeyCode();

		switch( key ){
			case KeyEvent.VK_RIGHT:		// 右
				m_KeyState[ KEY_CODE_RIGHT ] = KEY_STATE_KEEP_PUSHED;
				break;
			case KeyEvent.VK_LEFT:		// 左
				m_KeyState[ KEY_CODE_LEFT ] = KEY_STATE_KEEP_PUSHED;
				break;
			case KeyEvent.VK_UP:		// 上
				m_KeyState[ KEY_CODE_UP ] = KEY_STATE_KEEP_PUSHED;
				break;
			case KeyEvent.VK_DOWN:		// 下
				m_KeyState[ KEY_CODE_DOWN ] = KEY_STATE_KEEP_PUSHED;
				break;
			case KeyEvent.VK_Z:			// Zキー
				m_KeyState[ KEY_CODE_ENTER ] = KEY_STATE_KEEP_PUSHED;
				break;
			default:
				break;
		}
	}

	// キーが放されている時
	public void keyReleased( KeyEvent e )
	{
		int key = e.getKeyCode();

		switch( key ){
			case KeyEvent.VK_RIGHT:		// 右
				m_KeyState[ KEY_CODE_RIGHT ] = KEY_STATE_RELEASED;
				break;
			case KeyEvent.VK_LEFT:		// 左
				m_KeyState[ KEY_CODE_LEFT ] = KEY_STATE_RELEASED;
				break;
			case KeyEvent.VK_UP:		// 上
				m_KeyState[ KEY_CODE_UP ] = KEY_STATE_RELEASED;
				break;
			case KeyEvent.VK_DOWN:		// 下
				m_KeyState[ KEY_CODE_DOWN ] = KEY_STATE_RELEASED;
				break;
			case KeyEvent.VK_Z:			// Zキー
				m_KeyState[ KEY_CODE_ENTER ] = KEY_STATE_RELEASED;
				break;
			default:
				break;
		}
	}

}
