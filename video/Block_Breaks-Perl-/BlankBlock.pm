#!/usr/bin/perl

package BlankBlock;

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
	$block->{destroyed} = $Util::TRUE;
	$block->{penetrate} = $Util::TRUE;
	$block->{score} = 0;
	
	
	# パッケージ名とオブジェクト名を関連させる
	bless $block, $this;
	
	return $block;
}

# 描画
sub draw
{
}

# 更新
sub update
{
}

# X座標を取得する
sub get_pos_x
{
}

# Y座標を取得する
sub get_pos_y
{
}

# 幅を取得する
sub get_width
{
}

# 高さを取得する
sub get_height
{
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
}

# 得点を取得
sub get_score
{
	my $this = shift;
	
	return $this->{score};
}

1;