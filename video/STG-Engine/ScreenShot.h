#ifndef INCLUDED_RTG_SCREENSHOT_H
#define INCLUDED_RTG_SCREENSHOT_H

#include <tchar.h>

namespace RTG
{
	const int MAXIMUM_SCREEN_SHOT = 10000;		// 10000���B�e�\

	void ScreenShot( const char* pDirPath, const char* pFileName );
}

#endif