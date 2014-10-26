#ifndef INCLUDED_RTG_CONFIGMANAGER_H
#define INCLUDED_RTG_CONFIGMANAGER_H

#include "GeneralButtonManager.h"

namespace RTG
{
	class ConfigManager
	{
	private:
		// �E�B���h�E�֘A
		char				m_IsWndMode;							// �E�B���h�E���[�h��
		int					m_Width;								// �E�B���h�E�̕�
		int					m_Height;								// �E�B���h�E�̍���
		// �{�^���z�u�֘A
		unsigned short		m_ButtonMaps[ GENERAL_BUTTON_TOTAL ];	// �e�{�^���̊��蓖��
		// �T�E���h�֘A
		int					m_SEVolume;			// SE�̃{�����[��
		int					m_BGMVolume;		// BGM�̃{�����[��
	public:
		ConfigManager();
		~ConfigManager();
		void Load( const char* pFileName );						// �R���t�B�O�t�@�C���̓ǂݍ���
		void Save( const char* pFileName );						// �R���t�B�O�t�@�C���ւ̏�������
		char IsWndMode() const;									// �E�B���h�E���[�h���ǂ���
		void SetWndMode( char wndMode );						// �E�B���h�E���[�h
		int GetWidth() const;									// �E�B���h�E�̕����擾
		void SetWidth( int width );								// �E�B���h�E�̕���ݒ�
		int GetHeight() const;									// �E�B���h�E�̍������擾
		void SetHeight( int height );							// �E�B���h�E�̍�����ݒ�
		void GetButtonMaps( unsigned short* pButtonMap ) const;	// �e�{�^���̊��蓖�Ă��擾
		void SetButtonMaps( unsigned short* pButtonMap );		// �e�{�^���̊��蓖�Ă�ݒ�
		int GetSEVolume() const;								// SE�̃{�����[�����擾
		void SetSEVolume( int volume );							// SE�̃{�����[����ݒ�
		int GetBGMVolume() const;								// BGM�̃{�����[�����擾
		void SetBGMVolume( int volume );						// BGM�̃{�����[����ݒ�
	};
}

#endif