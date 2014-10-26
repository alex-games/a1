import java.awt.Graphics;


// シーン基底クラス。
// 各画面構成はこのクラスを継承して行うこと。

public abstract class Scene
{
	private SceneBuilder.SceneItem		m_NextScene;		// 次のシーン

	// 更新
	public abstract void update( InputManager manager );
	// 描画
	public abstract void draw( Graphics graphics );
	// 初期化
	public abstract void Init();

	// コンストラクタ
	public Scene()
	{
		m_NextScene = SceneBuilder.SceneItem.SCENE_NONE;
	}

	// 次のシーンを取得
	public final SceneBuilder.SceneItem getNextScene()
	{
		return m_NextScene;
	}

	// 遷移可能か？
	public final boolean hasNextScene()
	{
		return m_NextScene != SceneBuilder.SceneItem.SCENE_NONE;
	}

	// 次のシーンを設定する
	public final void setNextScene( SceneBuilder.SceneItem next )
	{
		m_NextScene = next;
	}
}
