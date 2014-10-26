#ifndef INCLUDED_RTG_REPLAYLOADER_H
#define INCLUDED_RTG_REPLAYLOADER_H

namespace RTG
{
	const int REPLAY_BUTTON_TOTAL	= 7;		// ���v���C�̕ۑ��ɕK�v�ȃ{�^����
	const int MAXIMUM_HISTORY_ENTRY	= 65535;	// �q�X�g���G���g���[��

	struct ReplayEntry
	{
		// �Q�[���̃��v���C�ł́A�����Ă����Ԃ̂ݕK�v
		char	m_Histories[ MAXIMUM_HISTORY_ENTRY * REPLAY_BUTTON_TOTAL ];
	};

	class ReplayLoader
	{
		char			m_Name[ 16 ];
		int				m_Score;
		int				m_Progress;
		::time_t		m_Date;
		ReplayEntry		m_Entry;			// ���v���C�f�[�^�{��
		int				m_EntryTotal;		// �G���g����
	public:
		ReplayLoader();
		~ReplayLoader();
		int Load( const char* pFileName );					// ���[�h�i���v���C�f�[�^��ǂݍ��ށj
		void Open( const char* pFileName );					// ���[�h�i���v���C�f�[�^�͓ǂݍ��܂Ȃ��j
		const char* GetName() const;						// ���O�̎擾
		int GetScore() const;								// �X�R�A�̎擾
		int GetProgress() const;							// �i�s�x�̎擾
		::time_t GetDate() const;							// �����̎擾
		void GetReplayData( ReplayEntry* pEntry ) const;	// ���v���C�̃G���g����ۑ�
	};
}

#endif