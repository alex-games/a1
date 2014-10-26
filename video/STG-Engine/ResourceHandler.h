#ifndef INCLUDED_RTG_RESOURCEHANDLER_H
#define INCLUDED_RTG_RESOURCEHANDLER_H

#include "Defines.h"

#include "Score.h"
#include "SaveDataManager.h"
#include "ReplaySaver.h"
#include "ScriptCompiler.h"



namespace RTG
{
	struct RandomSeed
	{
		int		m_PosX;
		int		m_PosY;
		int		m_Score;
		int		m_HitTotal;
		int		m_ReflectTotal;
		int		m_Frame;
	};

	// ���\�[�X�Ή��֌W
	struct ResourceMap
	{
		std::vector < int >		m_BGMMap;			// BGM
		std::vector < int >		m_SEMap;			// SE
		std::vector < int >		m_TextureMap;		// �e�N�X�`��
	};

	class GeneralButtonManager;
	struct ReplayEntry;
	class GameManager;
	class ScriptCompiler;
	class ResourceHandler : public MAPIL::Singleton < ResourceHandler >
	{
	private:
		// �f�o�b�O�֘A
		MAPIL::Console						m_Cons;		// �R���\�[���f�o�C�X

		// ���\�[�X�֘A
		ResourceMap							m_ResourceMap;
		ResourceScriptData					m_ResourceScriptData;

		// �������֘A
		MAPIL::Allocator < 3000, 4000 >		m_Alloc;				// �A���P�[�^
	public:
		friend class MAPIL::Singleton < ResourceHandler >;

		Score*								m_pScore;

		SaveDataManager*					m_pSaveDataManager;

		GeneralButtonManager*				m_pGBManager;
		ReplayEntry*						m_pReplayEntry;
		ReplaySaver*						m_pReplaySaver;
		GameManager*						m_pGameManager;

		ScriptCompiler*						m_pCompiler;

		RandomSeed							m_RdmSeed;

		int									m_BossHPBar;
		int									m_TitleTexture;
		int									m_MenuTexture[ 10 ];
		int									m_LoadingTexture;
		int									m_GameFont[ 40 ];

		int									m_MenuBGM;
		int									m_MenuSelectSE;
		int									m_MenuMoveSE;

		int									m_Archiver;

		ResourceHandler();
		~ResourceHandler();
	
		void* AllocMemory();					// �������̊��蓖��
		void ReleaseMemory( void* p );			// �������̊J��

		void SetupHandle();

		int GetTextureHandle( int index );
		int GetBGMHandle( int index );
		int GetSEHandle( int index );
		void ReleaseAllStageResources();			// ���\�[�X�}�b�v�ɕۑ�����Ă��郊�\�[�X��S�ĊJ��
	};
}

#endif