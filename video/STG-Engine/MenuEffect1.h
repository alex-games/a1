#ifndef INCLUDED_RTG_MENUEFFECT1_H
#define INCLUDED_RTG_MENUEFFECT1_H

#include "Effect3D.h"

namespace RTG
{
	class MenuEffect1 : public Effect3D
	{
		int							m_Counter;
		MAPIL::Vector3 < float >	m_Pos;
		double						m_Speed;
		double						m_Angle;
		int							m_Index;
		int							m_PSprite;
		MAPIL::ColorARGB < float >	m_Color;
	public:
		MenuEffect1(	int sprite,
						MAPIL::Vector3 < float > pos,
						double speed,
						double angle,
						int index );
		virtual ~MenuEffect1();
		virtual void Draw();
		virtual bool Move();
		int GetIndex() const;
	};
}

#endif