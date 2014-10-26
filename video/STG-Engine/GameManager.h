#ifndef INCLUDED_RTG_GAMEMANAGER_H
#define INCLUDED_RTG_GAMEMANAGER_H

#include "Defines.h"

namespace RTG
{
	class ConfigManager;
	class GameManager : public MAPIL::Singleton < GameManager >
	{
	public:
		friend class MAPIL::Singleton < GameManager >;
		GameManager();
		~GameManager();
		void Init();					// ������

		bool HasTermSig() const;		// �I���V�O�i������M���Ă��邩
		void SetTermSig();				// �I���V�O�i�����Z�b�g����

		void LoadConfigFile( const char* pFileName );
		void SaveConfigFile( const char* pFileName );
		char IsWndMode() const;
		int GetWndWidth() const;
		void SetWndWidth( int width );
		int GetWndHeight() const;
		void SetWndHeight( int height );
		void GetButtonMaps( unsigned short* pButtonMap ) const;
		void SetButtonMaps( unsigned short* pButtonMap );
		int GetSEVolume() const;
		void SetSEVolume( int volume );
		int GetBGMVolume() const;
		void SetBGMVolume( int volume );
	private:
		ConfigManager*			m_pConfigManager;
		bool					m_HasTermSig;
	};
}

#endif