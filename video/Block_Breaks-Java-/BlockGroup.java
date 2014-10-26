
// ブロックを保持するクラス

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;



public class BlockGroup
{
	Vector < Block >		m_BlockList;		// ブロックリスト

	private final static int	BLOCK_ARRAY_COL		= 15;		// ブロックの横列
	private final static int	BLOCK_ARRAY_ROW		= 22;		// ブロックの縦列


	// コンストラクタ
	public BlockGroup()
	{
		m_BlockList = new Vector < Block > ();

		for( int i = 0; i < BLOCK_ARRAY_ROW; ++i ){
			for( int j = 0; j < BLOCK_ARRAY_COL; ++j ){
				m_BlockList.add( new NormalBlock( i, j ) );
			}
		}
	}

	// ファイルから読み込む
	public void load( String fileName )
	{
		m_BlockList.clear();
		try{
			FileReader fr = new FileReader( fileName );
			BufferedReader br = new BufferedReader( fr );
			String str;
			int row = 0;
			int col = 0;
			while( ( str = br.readLine() ) != null ){
				StringTokenizer st = new StringTokenizer( str, "," );
				col = 0;
				while( st.hasMoreTokens() ){
					// ブロック識別番号を取得
					int id = Integer.parseInt( st.nextToken() );
					// ブロックの生成
					switch( id ){
						case 0:
							m_BlockList.add( new NoneBlock( row, col ) );
							break;
						case 1:
							m_BlockList.add( new NormalBlock( row, col ) );
							break;
						case 2:
							m_BlockList.add( new FragileBlock( row, col ) );
							break;
						case 3:
							m_BlockList.add( new UnbreakableBlock( row, col ) );
							break;
						case 4:
						case 5:
						case 6:
						case 7:
						case 8:
						case 9:
						case 10:
						case 11:
						case 12:
							m_BlockList.add( new StrongBlock( row, col, id - 3 ) );
						default:
							break;
					}
					++col;
				}
				++row;
			}
		}
		catch( FileNotFoundException e ){
			e.printStackTrace();
			System.exit( -1 );
		}
		catch( IOException e ){
			e.printStackTrace();
			System.exit( -2 );
		}
	}

	// 全ブロックの描画
	public void draw( Graphics graphics )
	{
		Iterator < Block > it = m_BlockList.iterator();
		while( it.hasNext() ){
			Block block = it.next();
			if( !block.isDestroyed() ){
				block.draw( graphics );
			}
		}
	}

	// 全ブロックのアップデート
	public void update( Ball ball, ScoreManager manager )
	{
		Iterator < Block > it = m_BlockList.iterator();
		while( it.hasNext() ){
			Block block = it.next();
			if( block.isCollided( ball ) && ball.isMovable() && !block.isDestroyed() ){
				block.collided( ball );
				ball.collided( block );
				manager.add( block.getScore() );
			}
		}
	}
}
