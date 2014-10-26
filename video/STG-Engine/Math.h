#ifndef INCLUDED_RTG_MATH_H
#define INCLUDED_RTG_MATH_H

#include "Defines.h"

namespace RTG
{
	const int		RAND_MAXIMUM = 65535;		// �����_���֐��ő�l

	extern int g_RandCount;

	int Rand( int posX, int posY, int score, int hitTotal, int reflectTotal, int frame );

	int Rand();

	void ResetRandSeed();
}

#endif