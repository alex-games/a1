#!/usr/bin/perl

package StrongBlock;

use strict;
use OpenGL ':all';
use Util;

use base qw(Block CollisionObj);


# コンストラクタ
sub new
{
	# パッケージ名
	my $this = shift;
	# ブロックの場所を取得
	my $row = shift;
	my $column = shift;
	
	# メンバ変数
	my $block = {};
	$block->{pos_x} = ( $Block::WIDTH + $Block::INTERVAL_X ) * $row + $Block::OFFSET_X;
	$block->{pos_y} = ( $Block::HEIGHT + $Block::INTERVAL_Y ) * $column + $Block::OFFSET_Y;
	$block->{destroyed} = $Util::FALSE;
	$block->{penetrate} = $Util::FALSE;
	$block->{score} = 100;
	$block->{durability} = shift;
	
	
	# パッケージ名とオブジェクト名を関連させる
	bless $block, $this;
	
	return $block;
}

# 描画
sub draw
{
	my $this = shift;
	
	if( $this->{destroyed} == $Util::FALSE ){
		glColor3f( 1.0, 0.0, 0.0 );
		glBegin( GL_LINE_LOOP );
		glVertex2f( $this->{pos_x} - 1, $this->{pos_y} );
		glVertex2f( $this->{pos_x} + $Block::WIDTH, $this->{pos_y} );
		glVertex2f( $this->{pos_x} + $Block::WIDTH, $this->{pos_y} + $Block::HEIGHT );
		glVertex2f( $this->{pos_x}, $this->{pos_y} + $Block::HEIGHT );
		glEnd();
		glRasterPos2f( $this->{pos_x} + 9.0, $this->{pos_y} + 10.5 );
		glutBitmapCharacter( GLUT_BITMAP_HELVETICA_10, ord ( $this->{durability} ) );
	}
}

# 更新
sub update
{
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
	
	return $this->{pos_y};
}

# 幅を取得する
sub get_width
{
	my $this = shift;
	
	return $Block::WIDTH;
}

# 高さを取得する
sub get_height
{
	my $this = shift;
	
	return $Block::HEIGHT;
}

# 破壊されたか
sub destroyed
{
	my $this = shift;
	
	return $this->{destroyed};
}

# 貫通するか
sub penetrate
{
	my $this = shift;
	
	return $this->{penetrate};
}

# ボールと衝突した時の処理
sub process_collision_with_ball
{
	my $this = shift;
	
	--$this->{durability};
	if( $this->{durability} <= 0 ){
		$this->{destroyed} = $Util::TRUE;
	}
}

# 得点を取得
sub get_score
{
	my $this = shift;
	
	if( $this->{durability} <= 0 ){
		return $this->{score};
	}
	else{
		return 1;
	}
}

1;