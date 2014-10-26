#ifndef INCLUDED_SCRIPTTYPE_H
#define INCLUDED_SCRIPTTYPE_H

#include "TaskList.hpp"
#include "CircleEnemyShot.h"
#include "CircleEnemy.h"
#include "NormalPlayer.h"
#include "Effect2D.h"


namespace RTG
{
	// �X�N���v�g�G���W���ɓn���G���
	struct EnemyInfo
	{
		TaskList < CircleEnemyShot >*	m_pShotList;
		TaskList < CirclePlayer >*		m_pPlayerList;
		TaskList < Effect2D >*			m_pEffect2DList;
		double							m_Angle;
		int								m_Counter;
		double							m_Speed;
		int								m_HP;
		float							m_PosX;
		float							m_PosY;
		unsigned int					m_ImgID;
		RandomSeed*						m_pRandSeed;
		float							m_ColRadius;
		int								m_Score;
		int								m_IsBoss;
	};

	// �X�N���v�g�G���W���ɓn���X�e�[�W���
	class ScriptCompiler;
	struct StageInfo
	{
		ScriptCompiler*					m_pCompiler;
		TaskList < CircleEnemy >*		m_pEnemyList;
		TaskList < CircleEnemyShot >*	m_pEnemyShotList;
		TaskList < CirclePlayer >*		m_pPlayerList;
		TaskList < Effect2D >*			m_pEffect2DList;
		int*							m_pFrame;
		RandomSeed*						m_pRandSeed;
	};

}

#endif