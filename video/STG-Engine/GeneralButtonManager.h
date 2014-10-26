#ifndef INCLUDED_RTG_GENERALBUTTONMANAGER_H
#define INCLUDED_RTG_GENERALBUTTONMANAGER_H

#include "Defines.h"
#include "Keyboard.h"
#include "ReplayLoader.h"

namespace RTG
{
	enum GeneralButton
	{
		GENERAL_BUTTON_UP		= 0,		// ��
		GENERAL_BUTTON_DOWN		= 1,		// ��
		GENERAL_BUTTON_RIGHT	= 2,		// �E
		GENERAL_BUTTON_LEFT		= 3,		// ��
		GENERAL_BUTTON_BARRIER	= 4,		// �o���A�i����{�^���j
		GENERAL_BUTTON_ROT_CW	= 5,		// ���v����]
		GENERAL_BUTTON_ROT_CCW	= 6,		// �����v�����]

		GENERAL_BUTTON_CANCEL	= 7,		// �L�����Z���{�^��
		GENERAL_BUTTON_SS		= 8,		// �X�N���[���V���b�g
		GENERAL_BUTTON_PAUSE	= 9,		// �|�[�Y�{�^��
		GENERAL_BUTTON_TERM		= 10,		// �����I��
		GENERAL_BUTTON_ENTER	= 11,		// ����{�^��
	};

	enum InputDevice
	{
		INPUT_DEVICE_KEYBOARD	= 0,		// �L�[�{�[�h
		INPUT_DEVICE_MOUSE		= 1,		// �}�E�X
		INPUT_DEVICE_GAMEPAD	= 2,		// �Q�[���p�b�h
		INPUT_DEVICE_REPLAY		= 3,		// ���v���C��
	};

	const int GENERAL_BUTTON_TOTAL = 12;	// �{�^����
	const int INPUT_DEVICE_TOTAL = 4;		// �f�o�C�X�̎�ސ�


	class GeneralButtonManager
	{
	private:
		unsigned short		m_ButtonMaps[ GENERAL_BUTTON_TOTAL ];		// �{�^���}�b�v�i���蓖�Ă̏�Ԃ�ێ��j�A���1byte->�f�o�C�X�A����1byte->�{�^��
		bool				m_IsPushedOnce[ GENERAL_BUTTON_TOTAL ];		// �{�^���͌��݉�����Ă��邩�H
		bool				m_IsPushed[ GENERAL_BUTTON_TOTAL ];			// �{�^���͑O���������Ă������H
		Keyboard*			m_pKeyboard;
		ReplayEntry*		m_pReplay;
		int					m_CurFrame;					// ���݂̃t���[��

		int	 repmode;
	public:
		GeneralButtonManager();
		~GeneralButtonManager();
		void Init( ReplayEntry* pReplayEntry );
		void Assign( int button, int dev, int devButton );
		int GetAssignedDev( int button );
		int GetAssignedDevButton( int button );
		void Update();
		void SetReplay( int set );
		bool IsPushedOnce( GeneralButton button ) const;
		bool IsPushed( GeneralButton button ) const;
		void Reset();
		void CopyCurState( char* pPushed ) const;
	};
}

#endif