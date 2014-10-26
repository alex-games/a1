#ifndef INCLUDED_RTG_SCRIPTEDENEMY_H
#define INCLUDED_RTG_SCRIPTEDENEMY_H

#include "CircleEnemy.h"
#include "Effect2D.h"

#include "EnemyVCPU.h"

#include "ScriptType.h"

namespace RTG
{
	

	class ScriptCompiler;
	class ScriptedEnemy : public CircleEnemy
	{
	private:
		ScriptCompiler*		m_pCompiler;		// �X�N���v�g�R���p�C��
		VM::Data*			m_pScriptCmd;		// �X�N���v�g�R�}���h
		int					m_ScriptID;			// �X�N���v�gID
		EnemyInfo			m_EnemyInfo;		// �X�N���v�g�G���W���ɓn���G���
		EnemyVCPU			m_VirtualMachine;	// ���z���s�}�V��
	public:
		ScriptedEnemy(	ScriptCompiler* pCompiler,
						int id,
						TaskList < CircleEnemyShot >* pList );
		ScriptedEnemy(	ScriptCompiler* pCompiler,
						int id,
						TaskList < CircleEnemyShot >* pList,
						TaskList < Effect2D >* pEffectList,
						int x,
						int y,
						TaskList < CirclePlayer >* pPlayerList,
						StageInfo* pInfo );
		~ScriptedEnemy();
		void Draw();
		bool Move();
		void Damage( int dec );
		int GetHP() const;
		void Init();
		MAPIL::Vector2 < double > GetPos() const;
	};
}

#endif