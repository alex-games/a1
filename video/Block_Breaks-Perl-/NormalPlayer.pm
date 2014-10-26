#!/usr/bin/perl

package NormalPlayer;

use strict;
use OpenGL ':all';
use Readonly;

use base qw(Player CollisionObj);

Readonly my $POSITION_Y		=> 400;		# Y座標（固定）
Readonly my $WIDTH			=> 30;		# 横幅
Readonly my $HEIGHT			=> 10;		# 縦幅
Readonly my $VELOCITY		=> 3;		# 速度

# コンストラクタ
sub new
{
	# パッケージ名
	my $this = shift;
	
	# メンバ変数
	my $scene = {};
	$scene->{pos_x} = 230;
	
	# パッケージ名とオブジェクト名を関連させる
	bless $scene, $this;
	
	return $scene;
}

# 描画
sub draw
{
	my $this = shift;

	glColor3f( 1.0, 1.0, 0.0 );
	glBegin( GL_LINE_LOOP );
	glVertex2f( $this->{pos_x} - 1, $POSITION_Y );
	glVertex2f( $this->{pos_x} + $WIDTH, $POSITION_Y );
	glVertex2f( $this->{pos_x} + $WIDTH, $POSITION_Y + $HEIGHT );
	glVertex2f( $this->{pos_x}, $POSITION_Y + $HEIGHT );
	glEnd();
}

# 更新
sub update
{
	my $this = shift;
	my $input_manager = shift;

	$this->{pos_x} = $input_manager->get_mouse_pos_x();
	if( $this->{pos_x} > 480 - $WIDTH ){
		$this->{pos_x} = 480 - $WIDTH;
	}
	elsif( $this->{pos_x} < 10 ){
		$this->{pos_x} = 10;
	}
}

# X座標を取得する
sub get_pos_x
{
	my $this = shift;
	
	return $this->{pos_x};
}

# Y座標を取得する
sub get_pos_y
{
	my $this = shift;
	
	return $POSITION_Y;
}

# 幅を取得する
sub get_width
{
	my $this = shift;
	
	return $WIDTH;
}

# 高さを取得する
sub get_height
{
	return $HEIGHT;
}

1;