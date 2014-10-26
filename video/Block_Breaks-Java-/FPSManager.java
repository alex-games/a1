public class FPSManager
{
	private long		m_CurTime;				// 現在の履歴
	private long[]		m_PrevTimes;			// 過去の時間履歴
	private int			m_TargetFPS;			// 目標とするFPS値
	private float		m_CurFPS;				// 現在のFPS値
	private long		m_NextTime;				// 次に目標とする時間
	private long		m_TimeNeededPerFrame;	// 1フレームに必要な時間

	private final static int	HISTORY_SIZE = 10;	// 履歴サイズ

	// コンストラクタ
	public FPSManager( int fps )
	{
		// 初期化
		m_CurTime = 0;
		m_PrevTimes = new long[ HISTORY_SIZE ];
		for( int i = 0; i < HISTORY_SIZE; ++i ){
			m_PrevTimes[ i ] = System.currentTimeMillis();
		}
		m_CurFPS = 0.0f;

		// 1フレームに必要な時間を計算
		changeFPS( fps );
	}

	public void changeFPS( int fps )
	{
		m_TargetFPS = fps;
		m_TimeNeededPerFrame = ( long ) ( 1000.0 * HISTORY_SIZE / m_TargetFPS );
		m_NextTime = m_TimeNeededPerFrame;
	}

	// 次フレームに移行するまでの時間が経過したか？
	public boolean hasElasped()
	{
		// 現在時刻を取得
		m_CurTime = System.currentTimeMillis();

		// 経過時間を計算
		long elaspedTime = ( m_CurTime - m_PrevTimes[ HISTORY_SIZE - 1 ] ) * HISTORY_SIZE;
		// 次に経過されるべき時間が過ぎた場合
		if( elaspedTime >= m_NextTime ){
			// 次に目標とする時間を設定
			m_NextTime = m_TimeNeededPerFrame - ( elaspedTime - m_NextTime );
			if( m_NextTime < 0 ){
				m_NextTime = m_TimeNeededPerFrame;
			}
			// 現在のFPS値を測定(履歴の中で一番古いものを選択することで、精度を向上させている)
			m_CurFPS = 1000.0f * HISTORY_SIZE / ( m_CurTime - m_PrevTimes[ 0 ] );
			// 履歴の更新
			for( int i = 0; i < HISTORY_SIZE - 1; ++i ){
				m_PrevTimes[ i ] = m_PrevTimes[ i + 1 ];
			}
			m_PrevTimes[ HISTORY_SIZE - 1 ] = m_CurTime;

			return true;
		}

		return false;
	}

	// 現在のFPS値を取得
	public float getFPS()
	{
		return m_CurFPS;
	}
}
