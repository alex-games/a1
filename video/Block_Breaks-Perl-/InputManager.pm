#!/usr/bin/perl

use strict;

package InputManager;

# コンストラクタ
sub new
{
	# パッケージ名
	my $this = shift;
	
	# メンバ変数
	my $input_manager = {};
	my @keys = ();
	$#keys = 256;
	$input_manager->{keys} = \@keys;
	$input_manager->{mouse_pos_x} = 0;
	$input_manager->{mouse_pos_y} = 0;
	$input_manager->{button} = 200;
	
	# パッケージ名とオブジェクト名を関連させる
	bless $input_manager, $this;
	
	return $input_manager;
}

# キーが入力されたことを通知
sub notify_key_input
{
	my $this = shift;
	my $code = shift;
	
	@{$this->{keys}}[ $code ] = 1;
}

# マウスが入力されたことを通知
sub notify_mouse_input
{
	my $this = shift;
	my $x = shift;
	my $y = shift;
	
	$this->{mouse_pos_x} = $x;
	$this->{mouse_pos_y} = $y;
}

# マウスがクリックされたことを通知
sub notify_mouse_click
{
	my $this = shift;
	my $button = shift;
	
	$this->{button} = $button;
}

# 左側のマウスがクリックされたか？
sub is_mouse_left_clicked
{
	my $this = shift;
	
	return $this->{button} == 0;
}


# キーが押されているか？
sub is_pushed
{
	my $this = shift;
	my $code = shift;
	
	return @{$this->{keys}}[ $code ];
}

# キーの入力状態を初期化
sub clear
{
	my $this = shift;
	
	for( my $i = 0; $i < 256; ++$i ){
		@{$this->{keys}}[ $i ] = 0;
	}
	
	$this->{button} = 200;
}

# キーの状態を得る
sub get_key_state
{
	my $this = shift;
	
	return @{$this->{keys}};
}

# マウスのX成分の移動量を得る
sub get_mouse_pos_x
{
	my $this = shift;
	
	return $this->{mouse_pos_x};
}

# マウスのY成分の移動量を得る
sub get_mouse_pos_y
{
	my $this = shift;
	
	return $this->{mouse_pos_y};
}


1;