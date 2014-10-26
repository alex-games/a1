#!/usr/bin/perl

use strict;
use OpenGL ':all';

package Scene;

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

sub draw
{
}

sub update
{
}

# 次のシーンに移行
sub set_next_scene
{
}

1;