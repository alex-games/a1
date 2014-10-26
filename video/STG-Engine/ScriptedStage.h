#ifndef INCLUDED_RTG_SCRIPTEDSTAGE_H
#define INCLUDED_RTG_SCRIPTEDSTAGE_H

#include "Scene.h"

#include "ScriptCompiler.h"
#include "TaskList.hpp"
#include "CirclePlayer.h"
#include "CircleEnemy.h"
#include "CircleReflectedShot.h"
#include "Effect2D.h"
#include "Stage1Effect1.h"

#include "StageVCPU.h"
#include "ScriptType.h"
#include "Loading.h"

namespace RTG
{
	enum PlayMode
	{
		PLAY_MODE_REPLAY = 0,
		PLAY_MODE_NORMAL = 1,
	};

	class ScriptCompiler;
	class ScriptedStage : public Scene
	{
	private:
		// �X�N���v�g�R���p�C��
		ScriptCompiler*		m_pCompiler;		// �R���p�C���{��
		VM::Data*			m_pScriptCmd;		// �X�N���v�g�R�}���h
		StageVCPU			m_VirtualMachine;	// ���z�}�V��
		StageInfo			m_StageInfo;		// �X�e�[�W���

		// �X�e�[�W�̏��
		int					m_PlayMode;			// �v���C���[�h
		int					m_Frame;			// �t���[����
		bool				m_Cleared;			// �X�e�[�W�N���A�t���O
		bool				m_GameOvered;		// �Q�[���I�[�o�t���O
		bool				m_Paused;			// �|�[�Y�t���O
		int					m_StageNo;			// �X�e�[�W�ԍ�
		int					m_FinishedCounter;	// �I�����̃J�E���^

		// �e��I�u�W�F�N�g���X�g
		typedef TaskList < CirclePlayer >			CirclePlayerList;
		typedef TaskList < CircleEnemy >			CircleEnemyList;
		typedef TaskList < CircleEnemyShot >		CircleEnemyShotList;
		typedef TaskList < CircleReflectedShot >	CircleReflectedShotList;
		typedef TaskList < Effect2D >				Effect2DList;
		typedef TaskList < Stage1Effect1 >			Stage1Effect1List;
		CirclePlayerList			m_PlayerList;			// �v���C���[���X�g
		CircleEnemyList				m_EnemyList;			// �G���X�g
		CircleEnemyShotList			m_EnemyShotList;		// �G�e���X�g
		CircleReflectedShotList		m_ReflectedShotList;	// ���˒e���X�g
		Effect2DList				m_Effect2DList;			// 2D�G�t�F�N�g���X�g
		Stage1Effect1List			m_Stage1EffectList;		// �|�C���g�X�v���C�g���X�g

		
		ResourceScriptData			m_ResourceScriptData;		// �X�N���v�g�f�[�^�i���\�[�X�j
		ResourceMap					m_ResourceMap;				// �X�N���v�g�e�N�X�`��ID -> ���\�[�X�n���h��ID

		int							m_ReflectSE;
		int							m_BombbedSE;
		int							m_StageBGM;
		int							m_BGTexture[ 2 ];
		int							m_PointSprite;
		int							m_PointSpriteTexture;
		int							m_PointSpriteCamera;

		int							m_ReflectTotal;
		int							m_KillTotal;
		int							m_HitTotal;

		MAPIL::Vector3 < float >	m_Pos;

		Loading						m_Loading;

		
		// ����J���\�b�h�Q
		void UpdatePlayer();					// �v���C���[�̏����X�V
		void UpdateEnemy();						// �G�̏����X�V
		void UpdateEnemyShot();					// �G�̒e�̏����X�V
		void UpdateReflectedShot();				// ���˒e�̏����X�V
		void UpdateEffect2D();					// �G�t�F�N�g�̍X�V
		void UpdateStage1Effect();				// �|�C���g�X�v���C�g�G�t�F�N�g�̍X�V
		void CollidePlayerAndEnemy();			// �v���C���[�ƓG�Ƃ̏Փ˔���
		void CollideEnemyAndReflectedShot();	// �G�Ɣ��˒e�Ƃ̏Փ˔���
	public:
		ScriptedStage( ScriptCompiler* pCompiler, int stage, int playMode );
		~ScriptedStage();
		void Update();				// �f�[�^�X�V
		void Draw();				// �`��
		void Init();				// ������
		bool IsLoading() const;		// ���[�h�����H
	};
}

#endif