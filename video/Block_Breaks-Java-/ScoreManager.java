
// スコア管理クラス

public class ScoreManager
{
	private int		m_Score;		// スコア

	// コンストラクタ
	public ScoreManager()
	{
		m_Score = 0;
	}

	// スコアを加算
	public void add( int score )
	{
		m_Score += score;
	}

	// スコアを取得
	public int getScore()
	{
		return m_Score;
	}

	// スコアをリセット
	public void reset()
	{
		m_Score = 0;
	}
}
