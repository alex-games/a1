#!/usr/bin/perl

package CollisionObj;

use strict;
use Readonly;

Readonly our $INTERSECT_TOP		=> 1 << 0;
Readonly our $INTERSECT_BOTTOM	=> 1 << 1;
Readonly our $INTERSECT_LEFT	=> 1 << 2;
Readonly our $INTERSECT_RIGHT	=> 1 << 3;

# コンストラクタ
sub new
{
	# パッケージ名
	my $this = shift;
	
	# メンバ変数
	my $col_obj = {};
	
	# パッケージ名とオブジェクト名を関連させる
	bless $col_obj, $this;
	
	return $col_obj;
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

# 衝突しているか？
sub is_collided
{
	my $self = shift;		# 自分自身
	my $peer = shift;		# 相手
	
	my $result = 0;
	
	# 自分自身の当たり判定
	my $self_top = $self->get_pos_y();
	my $self_bottom = $self->get_pos_y() + $self->get_height();
	my $self_left = $self->get_pos_x();
	my $self_right = $self->get_pos_x() + $self->get_width();
	
	# 相手の当たり判定
	my $peer_top = $peer->get_pos_y();
	my $peer_bottom = $peer->get_pos_y() + $peer->get_height();
	my $peer_left = $peer->get_pos_x();
	my $peer_right = $peer->get_pos_x() + $peer->get_width();
	
	# 上辺が交差
	if( $peer_top > $self_top && $peer_top < $self_bottom && $peer_left < $self_right && $peer_right > $self_left ){
		$result |= $INTERSECT_TOP;
	}
	# 下辺が交差
	if( $peer_bottom > $self_top && $peer_bottom < $self_bottom && $peer_left < $self_right && $peer_right > $self_left ){
		$result |= $INTERSECT_BOTTOM;
	}
	# 左辺が交差
	if( $peer_left > $self_left && $peer_left < $self_right && $peer_top < $self_bottom && $peer_bottom > $self_top ){
		$result |= $INTERSECT_LEFT;
	}
	# 右辺が交差
	if( $peer_right > $self_left && $peer_right < $self_right && $peer_top < $self_bottom && $peer_bottom > $self_top ){
		$result |= $INTERSECT_RIGHT;
	}
	
	return $result;
}

sub intersect_at_top
{
	my $this = shift;
	my $value = shift;
	
	return ( $value & $INTERSECT_TOP ) != 0;
}

sub intersect_at_bottom
{
	my $this = shift;
	my $value = shift;
	
	return ( $value & $INTERSECT_BOTTOM ) != 0;
}

sub intersect_at_left
{
	my $this = shift;
	my $value = shift;
	
	return ( $value & $INTERSECT_LEFT ) != 0;
}

sub intersect_at_right
{
	my $this = shift;
	my $value = shift;
	
	return ( $value & $INTERSECT_RIGHT ) != 0;
}




1;