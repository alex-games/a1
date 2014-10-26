#!/usr/bin/perl

use strict;
use OpenGL ':all';
use Time::HiRes 'sleep';
use Readonly;

use SceneBuilder;
use FPSManager;
use InputManager;

use constant APP_NAME				=> 'Block Breaks';		# ウィンドウ名
Readonly my $WINDOW_INITIAL_POS_X	=> 100;					# ウィンドウの初期位置（X座標）
use constant WINDOW_INITIAL_POS_Y	=> 100;					# ウィンドウの初期位置（Y座標）
use constant WINDOW_WIDTH			=> 640;					# ウィンドウの幅
use constant WINDOW_HEIGHT			=> 480;					# ウィンドウの高さ

# グローバル変数
our $scene_builder = new SceneBuilder;
our $scene = $scene_builder->create_scene( $SceneBuilder::SCENE_STAGE );
our $fps_manager = new FPSManager( 60 );
our $input_manager = new InputManager();

our @keys = ();
$#keys = 256;

# GLUTの初期化
glutInit();
# ダブルバッファモードに設定
glutInitDisplayMode( GLUT_RGBA | GLUT_DOUBLE );
# ウィンドウの作成
glutInitWindowPosition( $WINDOW_INITIAL_POS_X, WINDOW_INITIAL_POS_Y );
glutInitWindowSize( WINDOW_WIDTH, WINDOW_HEIGHT );
glutCreateWindow( APP_NAME );
# サイズが変更された時に呼び出される関数を設定
glutReshapeFunc( \&resize );
# 画面描画に使用する関数を設定
glutDisplayFunc( \&draw );
glutIdleFunc( \&idle );
# キーボード入力取得関数を設定
glutKeyboardFunc( \&keyboard );
# マウス入力取得関数を設定
glutPassiveMotionFunc( \&mouse );
glutMouseFunc( \&mouse_click );
# メッセージループ
glutMainLoop();

# キーボード入力取得関数
sub keyboard
{
	# 入力されたキーコードを通知
	my ( $key, $x, $y ) = @_;
	$input_manager->notify_key_input( $key );
}

# マウス入力取得関数
sub mouse
{
	# 入力されたマウスの状態を通知
	my ( $x, $y ) = @_;
	$input_manager->notify_mouse_input( $x, $y );
}

# マウス入力取得関数（クリック）
sub mouse_click
{
	# クリックを検知
	my $button = $_[ 0 ];
	$input_manager->notify_mouse_click( $button );
}

sub idle
{
	if( $fps_manager->has_elasped() == 1 ){
		glutPostRedisplay();
	}
	else{
		Time::HiRes::sleep( 0.017 );
	}
}

sub resize
{
	# ウィンドウサイズの固定化
	glutReshapeWindow( WINDOW_WIDTH, WINDOW_HEIGHT );
}

# 描画開始
sub begin_rendering
{
	# 画面サイズを戻す
	&resize;

	# 画面のクリア
	glClear( GL_COLOR_BUFFER_BIT );
	
	# カメラの設定
	glMatrixMode( GL_PROJECTION );
	glPushMatrix();
	glOrtho( 0, WINDOW_WIDTH, WINDOW_HEIGHT, 0, -1, 1 );
	glMatrixMode( GL_MODELVIEW );
	glPushMatrix();
	glLoadIdentity();
}

# 描画終了
sub end_rendering
{
	# カメラの設定解除
	glMatrixMode( GL_PROJECTION );
	glPopMatrix();
	glMatrixMode( GL_MODELVIEW );
	glPopMatrix();
	
	# 更新
	glutSwapBuffers();
	
	# キー入力の状態をクリア
	$input_manager->clear();
}

our $count = 0;
our @digit = ();


# 描画関数
sub draw
{
	# 描画開始
	&begin_rendering;
	
	# シーンの更新
	$scene->update( $input_manager );
	# シーンの描画
	$scene->draw();
	
	# FPS値描画
	++$count;
	if( ( $count % 60 ) == 0 ){
		my $fps = $fps_manager->get_fps();
		@digit[ 0 ] = ( $fps * 10 ) % 10;
		@digit[ 2 ] = $fps / 10;
		@digit[ 1 ] = $fps % 10;
	}
	if( $input_manager->is_pushed( 122 ) == 1 ){
		glRasterPos2f( 550.0, 440.0 );
		glutBitmapCharacter( GLUT_BITMAP_HELVETICA_18, ord $digit[ 2 ] );
		glRasterPos2f( 560.0, 440.0 );
		glutBitmapCharacter( GLUT_BITMAP_HELVETICA_18, ord $digit[ 1 ] );
		glRasterPos2f( 570.0, 440.0 );
		glutBitmapCharacter( GLUT_BITMAP_HELVETICA_18, ord '.' );
		glRasterPos2f( 580.0, 440.0 );
		glutBitmapCharacter( GLUT_BITMAP_HELVETICA_18, ord $digit[ 0 ] );
	}
	
	# 入力をクリア
	$input_manager->clear();
	
	# 描画終了
	&end_rendering;

}