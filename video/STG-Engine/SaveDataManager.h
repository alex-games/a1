#ifndef INCLUDED_RTG_SAVEDATAMANAGER_H
#define INCLUDED_RTG_SAVEDATAMANAGER_H

#include <iostream>

namespace RTG
{
	enum StageProgress
	{
		STAGE_PROGRESS_STAGE1			= 0,
		STAGE_PROGRESS_COMPLETED		= 1,
	};

	// �X�R�A�G���g��
	struct ScoreEntry
	{
		char		m_Name[ 16 ];		// ���O
		int			m_Score;			// ���_
		int			m_Progress;			// �i�s�x
		::time_t	m_Date;				// ����
		bool		m_Used;				// �g�p����
	};

	class SaveDataManager
	{
		bool			m_Updated;
		const char*		m_pFileName;
		
		struct Configuration
		{
		};
		// �Q�[���i�s�x
		struct Progress
		{
			int			m_RunCount;			// �N����
		};
		// �X�R�A
		struct ScoreHistory
		{
			ScoreEntry		m_Entry[ 32 ];
		};

		Configuration			m_Configuration;	// �Q�[���ݒ�
		Progress				m_Progress;			// �Q�[���i�s�x
		ScoreHistory			m_ScoreHistory;		// �X�R�A����

	public:
		SaveDataManager( const char* pFileName );
		~SaveDataManager();
		void UpdateScore( const char* pName, int score, int progress );
		void IncRunCount();
		bool IsUpdated() const;
		ScoreEntry GetScoreEntry( int rank ) const;
		void Save();
		void Load();
	};
}

#endif