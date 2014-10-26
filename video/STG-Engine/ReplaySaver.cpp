#include "Defines.h"

#include "ReplaySaver.h"

#include <time.h>

#include <MAPIL/Algorithm/LZ.h>
#include <MAPIL/Algorithm/Caesar.h>
#include <MAPIL/Algorithm/XOR.h>

namespace RTG
{
	ReplaySaver::ReplaySaver() : m_CurFrame( 0 )
	{
	}

	ReplaySaver::~ReplaySaver()
	{
		m_CurFrame = 0;
	}

	void ReplaySaver::Save(	const char* pFileName, const char* pName, int score, int progress )
	{
		std::ofstream fOut( pFileName, std::ios::binary | std::ios::out );

		::strncpy( m_Name, pName, 16 );
		m_Score = score;
		m_Progress = progress;
		::time( &m_Date );

		// �e����̏�������
		fOut.write( m_Name, sizeof( m_Name ) );
		fOut.write( (char*)( &m_Score ), sizeof( m_Score ) );
		fOut.write( (char*)( &m_Progress ), sizeof( m_Progress ) );
		fOut.write( (char*)( &m_Date ), sizeof( m_Date ) );

		// ���k
		char* pComp = new char[ MAXIMUM_HISTORY_ENTRY * REPLAY_BUTTON_TOTAL ];	// ���k��̃f�[�^
		int compSize = 0;
		MAPIL::LZ lz( 200, 3 );
		lz.Compress( m_Entry.m_Histories, sizeof( m_Entry.m_Histories ), &pComp, MAXIMUM_HISTORY_ENTRY * REPLAY_BUTTON_TOTAL, &compSize );

		// �Í���
		MAPIL::Caesar cs( 3 );			// �V�[�U�[�Í�
		cs.Encrypt( pComp, compSize );
		MAPIL::XOR xor( 10 );			// XOR�Í�
		xor.Encrypt( pComp, compSize );

		// �f�[�^��������
		fOut.write( (char*)( &compSize ), sizeof( compSize ) );
		fOut.write( pComp, compSize );
		fOut.close();

		// ��n��
		MAPIL::SafeDeleteArray( pComp );
	}

	void ReplaySaver::Add( char* pEntry )
	{
		int offset = m_CurFrame * REPLAY_BUTTON_TOTAL;
		for( int i = 0; i < REPLAY_BUTTON_TOTAL; ++i ){
			m_Entry.m_Histories[ offset + i ]  = pEntry[ i ];	// ���v���C�{�^���� * ���݂̃t���[�� + �{�^��
		}
		++m_CurFrame;
	}

	void ReplaySaver::Reset()
	{
		m_CurFrame = 0;
	}
}