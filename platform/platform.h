#ifndef __PLATFORM_H__
#define __PLATFORM_H__

#include "../std_types.h"

#pragma once

#define USE_GL
#define USE_GLUT
#define USE_OAL

namespace xPlatform
{

enum eKeyFlags { KF_DOWN = 0x01, KF_SHIFT = 0x08, KF_CTRL = 0x02, KF_ALT = 0x04 };

struct eHandler
{
	eHandler();
	~eHandler();
	virtual void OnLoop() = 0;
	virtual const char* WindowCaption() = 0;
	virtual void OnKey(char key, dword flags) = 0;

	virtual void* VideoData() = 0;
	virtual int	AudioSources() = 0;
	virtual void* AudioData(int source) = 0;
	virtual dword AudioDataReady(int source) = 0;
	virtual void AudioDataUse(int source, dword size) = 0;
};

bool Init(int argc, char* argv[]);
void Loop();
void Done();

eHandler* Handler();

}
//namespace xPlatform

#endif//__PLATFORM_H__