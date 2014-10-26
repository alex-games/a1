#!/usr/bin/perl

package Block;

use strict;
use OpenGL ':all';
use Readonly;

Readonly our $OFFSET_X		=> 50;	# ブロックの最左端
Readonly our $OFFSET_Y		=> 30;	# ブロックの最上端
Readonly our $WIDTH			=> 25;	# ブロックの幅
Readonly our $HEIGHT			=> 15;	# ブロックの高さ
Readonly our $INTERVAL_X		=> 5;	# ブロック間の幅
Readonly our $INTERVAL_Y		=> 5;	# ブロック間の高さ

# コンストラクタ
sub new
{
	# パッケージ名
	my $this = shift;
	
	# メンバ変数
	my $block = {};
	
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
}

# 貫通するか
sub penetrate
{
}

# ボールと衝突した時の処理
sub process_collision_with_ball
{
}

# スコアを取得
sub get_score
{
}

1;