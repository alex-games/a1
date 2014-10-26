
// シーン構築クラス
// 新たなシーンの作成はこのクラスを通して行う。

public class SceneBuilder
{
	// 作成できるシーン一覧
	public enum SceneItem
	{
		SCENE_NONE,			// なし
		SCENE_TITLE,		// タイトル画面
		SCENE_STAGE,		// ステージ画面
	}

	private ScoreManager		m_ScoreManager;		// スコア管理クラス

	// コンストラクタ
	public SceneBuilder( ScoreManager manager )
	{
		m_ScoreManager = manager;
	}

	// 新たなシーンを作成
	public Scene createScene( SceneItem scene )
	{
		if( scene == SceneItem.SCENE_STAGE ){
			return new Stage( m_ScoreManager );
		}
		return null;
	}
}
