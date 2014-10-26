#!/usr/bin/perl

use strict;
use OpenGL ':all';

package Ball;

# コンストラクタ
sub new
{
	# パッケージ名
	my $this = shift;
	
	# メンバ変数
	my $ball = {};
	
	# パッケージ名とオブジェクト名を関連させる
	bless $ball, $this;
	
	return $ball;
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

# プレイヤーとの衝突判定
sub collision_with_player
{
}

# ブロックとの衝突判定
sub collision_with_block
{
}


1;