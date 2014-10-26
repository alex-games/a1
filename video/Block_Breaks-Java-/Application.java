import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class Application
{
	// シリアル番号
	private static final long serialVersionUID = 1234;	// アプリケーションシリアル番号

	// ウィンドウ各種設定値
	private static final int WINDOW_TOP		= 120;	// ウィンドウの上端座標
	private static final int WINDOW_LEFT	= 240;	// ウィンドウの左端座標
	public static final int WINDOW_WIDTH	= 640;	// ウィンドウの横幅
	public static final int WINDOW_HEIGHT	= 480;	// ウィンドウの縦幅

	// 2D Canvas
	BufferStrategy		m_BufferStrategy;	// ダブルバッファリング
	JFrame				m_Frame;			// ウィンドウ
	static Insets		m_Insets;			// ウィンドウの装飾の幅

	// ゲーム管理クラス
	FPSManager			m_FPSManager;		// FPS管理クラス
	SceneBuilder		m_SceneBuilder;		// シーン構築クラス
	Scene				m_Scene;			// シーン
	ScoreManager		m_ScoreManager;		// スコア管理クラス

	// 入力管理クラス
	private InputManager		m_InputManager;		// 入力装置管理クラス

	public Application()
	{


		try{
			// ウィンドウの初期化
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gd = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gd.getDefaultConfiguration();
			m_Frame = new JFrame( gc );
			m_Frame.setTitle( "Block Breaks" );
			m_Frame.setBounds(	WINDOW_LEFT,
								WINDOW_TOP,
								WINDOW_WIDTH,
								WINDOW_HEIGHT );
			m_Frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	// ×ボタンで閉じる
			m_Frame.setResizable( false );		// 画面サイズの変更禁止
			m_Frame.setVisible( true );
			m_Frame.createBufferStrategy( 2 );
			m_BufferStrategy = m_Frame.getBufferStrategy();
			// 装飾の幅を考慮し、画面のサイズを変更
			m_Insets = m_Frame.getInsets();
			m_Frame.setBounds(	WINDOW_LEFT,
								WINDOW_TOP,
								WINDOW_WIDTH + m_Insets.left + m_Insets.right,
								WINDOW_HEIGHT + m_Insets.top + m_Insets.bottom );


			// FPS管理クラスの作成
			m_FPSManager = new FPSManager( 60 );	// 60 fps

			// スコア管理クラス
			m_ScoreManager = new ScoreManager();
			m_ScoreManager.reset();

			// シーン構築クラスの作成
			m_SceneBuilder = new SceneBuilder( m_ScoreManager );
			// 初期シーンの構築
			m_Scene = m_SceneBuilder.createScene( SceneBuilder.SceneItem.SCENE_STAGE );

			// 入力装置管理クラス
			m_InputManager = new InputManager();
			m_Frame.addKeyListener( m_InputManager );
		}
		catch( IllegalArgumentException e ){
			e.printStackTrace();
		}
	}

	// キャンバス上でのX座標を得る。
	public static int canvasX( int x )
	{
		return x + m_Insets.left;
	}

	// キャンバス上でのY座標を得る。
	public static int canvasY( int y )
	{
		return y + m_Insets.top;
	}

	public void run()
	{
		int cnt = 1;

		while( true ){
			if( m_FPSManager.hasElasped() ){
				++cnt;
				// 描画対象を取得する。
				Graphics graphics = m_BufferStrategy.getDrawGraphics();
				// ビデオポインタの消失を確認
				if( !m_BufferStrategy.contentsLost() ){
					// 描画領域kを黒で塗りつぶす
					graphics.setColor( Color.black );
					graphics.fillRect( canvasX( 0 ), canvasY( 0 ), WINDOW_WIDTH, WINDOW_WIDTH );

					// 描画領域

					// 状態遷移
					if( m_Scene.hasNextScene() ){
						m_Scene = m_SceneBuilder.createScene( m_Scene.getNextScene() );
						m_Scene.Init();
					}

					// シーンの更新
					m_Scene.update( m_InputManager );
					// シーンの描画
					m_Scene.draw( graphics );

					// 現在のFPSを表示
					graphics.setFont( new Font( "arial", Font.PLAIN, 12 ) );
					graphics.setColor( Color.CYAN );
					graphics.drawString( "FPS : " + m_FPSManager.getFPS(), canvasX( WINDOW_WIDTH  - 120 ), canvasY( WINDOW_HEIGHT - 20 ) );

					// グラフィックスをバッファに書き出す
					m_BufferStrategy.show();
					// グラフィックスの破棄
					graphics.dispose();
				}
			}
			else{
				try{
					Thread.sleep( 1 );
				}
				catch( InterruptedException e ){
					e.printStackTrace();
				}
			}
		}
	}
}
