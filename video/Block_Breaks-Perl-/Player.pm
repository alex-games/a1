#!/usr/bin/perl

use strict;
use OpenGL ':all';

package Player;

# コンストラクタ
sub new
{
	# パッケージ名
	my $this = shift;
	
	# メンバ変数
	my $scene = {};
	
	# パッケージ名とオブジェクト名を関連させる
	bless $scene, $this;
	
	return $scene;
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

1;