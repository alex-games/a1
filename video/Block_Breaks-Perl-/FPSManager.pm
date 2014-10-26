#!/usr/bin/perl

use strict;
use Time::HiRes;

package FPSManager;

# コンストラクタ
sub new
{
	# パッケージ名
	my $this = shift;
	
	# メンバ変数
	my $fps_manager = {};
	$fps_manager->{cur_time} = 0.0;
	my @time = ();
	$fps_manager->{prev_time} = Time::HiRes::time * 1000;
	$fps_manager->{target_fps} = shift;
	$fps_manager->{cur_fps} = 0.0;
	# 1フレームに必要な時間を計算。
	$fps_manager->{time_needed_per_frame} = int( 1000.0 / $fps_manager->{target_fps} );
	$fps_manager->{next_time} = $fps_manager->{time_needed_per_frame};
	
	# パッケージ名とオブジェクト名を関連させる
	bless $fps_manager, $this;
	
	return $fps_manager;
}

# 次フレームに移行するまでの時間が経過したか？
sub has_elasped
{
	my $this = shift;
	
	# 現在時刻を取得
	$this->{cur_time} = Time::HiRes::time * 1000;
#	print $this->{cur_time} . "\n";
	
	# 経過時間を計算
	my $elasped_time = ( $this->{cur_time} - $this->{prev_time} );
	# 次に経過されるべき時間が過ぎた場合。
	if( $elasped_time >= $this->{next_time} ){
		# 次に目標とする時間を設定
		$this->{next_time} = $this->{time_needed_per_frame} - ( $elasped_time - $this->{next_time} );
		if( $this->{next_time} < 0 ){
			$this->{next_time} = $this->{time_needed_per_frame};
		}
		# 現在のFPS値を測定
		$this->{cur_fps} = 1000.0 / ( $this->{cur_time} - $this->{prev_time} );
		# 履歴の更新
		$this->{prev_time} = $this->{cur_time};
		
		return 1;
	}
	
	return 0;
}

# 現在のFPS値を取得する
sub get_fps
{
	my $this = shift;

	return $this->{cur_fps};
}

1;