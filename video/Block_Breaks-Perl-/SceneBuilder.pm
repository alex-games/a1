#!/usr/bin/perl

# シーン構築クラス

package SceneBuilder;

use strict;
use Scene;
use Stage;
use Readonly;

Readonly our $SCENE_STAGE	=> 1;

# コンストラクタ
sub new
{
	# パッケージ名
	my $this = shift;
	
	# メンバ変数
	my $scene_builder = {};
	
	# パッケージ名とオブジェクト名を関連させる
	bless $scene_builder, $this;
	
	return $scene_builder;
}


# 次のシーンに移行
sub create_scene
{
	my $this = shift;

	my $next_scene = shift;
	
	if( $next_scene == $SCENE_STAGE ){
		return new Stage;
	}
}

1;