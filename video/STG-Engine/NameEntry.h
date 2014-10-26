#ifndef INCLUDED_RTG_NAMEENTRY_H
#define INCLUDED_RTG_NAMEENTRY_H

#include "Defines.h"

namespace RTG
{

	const int NAME_CHAR_BUF_SIZE = 128;
	

	class NameEntry
	{
	private:
		int			m_CurPos;							// ���ݑI�𒆂̕���
		int			m_EffectiveCharTotal;				// �L���ȕ�����
		char		m_NameChar[ NAME_CHAR_BUF_SIZE ];		// ���O�Ɏg�p�ł��镶��
	public:
		NameEntry();
		~NameEntry();
		void SetEffectiveChar( const char* pStr );
		char GetCurChar() const;
		void Advance( int offset );
	};

	const char* GetEffectiveChar();
}

#endif