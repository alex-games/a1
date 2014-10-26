#include "MainFrame.h"

#include "ResourceHandler.h"

#include "Menu.h"
#include "GeneralButtonManager.h"
#include "ScreenShot.h"

#include "GameManager.h"

#include "ScriptCompiler.h"

#include "FontString.h"

#include <iostream>

namespace RTG
{
	MainFrame::MainFrame() : m_pScene( NULL ), m_FPS()
	{
	}

	MainFrame::~MainFrame()
	{
		RTG::ResourceHandler* p = RTG::ResourceHandler::GetInst();

		MAPIL::SafeDelete( m_pScene );

		MAPIL::SafeDelete( p->m_pSaveDataManager );

		MAPIL::SafeDelete( p->m_pGBManager );

		MAPIL::SafeDelete( p->m_pReplaySaver );

		MAPIL::SafeDelete( p->m_pCompiler );

		MAPIL::StopStreamingBuffer( p->m_MenuBGM );
		MAPIL::DeleteStreamingBuffer( p->m_MenuBGM );
		MAPIL::DeleteStaticBuffer( p->m_MenuMoveSE );
		MAPIL::DeleteStaticBuffer( p->m_MenuSelectSE );
	}

	void MainFrame::Init()
	{
		RTG::ResourceHandler* p = RTG::ResourceHandler::GetInst();
		
		// �ݒ�t�@�C���̓ǂݍ���
		p->m_pGameManager = new GameManager();
		p->m_pGameManager->Init();
		p->m_pGameManager->LoadConfigFile( "config/config.dat" );

		// ������
		MAPIL::InitMAPIL( "Confetti", 640, 480 );
		MAPIL::SetIcon( "confetti.ico" );

		// �Z�[�u�f�[�^�Ǘ��N���X�̏�����
		p->m_pSaveDataManager = new SaveDataManager( "save/RTG.dat" );
		p->m_pSaveDataManager->Load();

		// ���v���C�G���g���̍쐬
		p->m_pReplayEntry = new ReplayEntry;

		// �{�^���̊��蓖��
		unsigned short buttons[ GENERAL_BUTTON_TOTAL ];
		p->m_pGameManager->GetButtonMaps( buttons );
		p->m_pGBManager = new GeneralButtonManager();
		p->m_pGBManager->Init( p->m_pReplayEntry );
		for( int i = 0; i < GENERAL_BUTTON_TOTAL; ++i ){
			p->m_pGBManager->Assign( i, ( buttons[ i ] >> 8 ) & 0xFF, buttons[ i ] & 0xFF );
		}

		// ���v���C�쐬�p�N���X�̐���
		p->m_pReplaySaver = new ReplaySaver();

		// �R���p�C���̏�����
		p->m_pCompiler = new ScriptCompiler;
		//p->m_pCompiler->BuildFileStructure( "script/test.isc" );
		p->m_pCompiler->BuildFileStructure( p->m_Archiver, "archive/script/test.isc" );

		// ���j���[��ʗp�̃f�[�^�����[�h
		p->m_MenuBGM = MAPIL::CreateStreamingBuffer( p->m_Archiver, "archive/resource/bgm/rtg_title.wav" );
		p->m_MenuSelectSE = MAPIL::CreateStaticBuffer( p->m_Archiver, "archive/resource/se/select.wav" );
		p->m_MenuMoveSE = MAPIL::CreateStaticBuffer( p->m_Archiver, "archive/resource/se/move.wav" );

		// ���j���[��ʂ֑J��
		m_pScene = new Menu();
		m_pScene->Init();

		// �X�R�A�̍쐬
		p->m_pScore = new Score();

		// �{�X��HP�o�[�̉摜�����[�h
		p->m_BossHPBar = MAPIL::CreateTexture( p->m_Archiver, "archive/resource/texture/BossHPBar.png" );
		// �^�C�g����ʂ̉摜�����[�h
		p->m_TitleTexture = MAPIL::CreateTexture( "material/Resource/title.png" );
		// ���j���[���ڂ̉摜�����[�h
		p->m_MenuTexture[ 0 ] = MAPIL::CreateTexture( "material/Resource/menu1.png" );
		p->m_MenuTexture[ 1 ] = MAPIL::CreateTexture( "material/Resource/menu2.png" );
		p->m_MenuTexture[ 2 ] = MAPIL::CreateTexture( "material/Resource/menu3.png" );
		p->m_MenuTexture[ 3 ] = MAPIL::CreateTexture( "material/Resource/menu4.png" );
		p->m_MenuTexture[ 4 ] = MAPIL::CreateTexture( "material/Resource/menu5.png" );
		p->m_MenuTexture[ 5 ] = MAPIL::CreateTexture( "material/Resource/menu1_rev.png" );
		p->m_MenuTexture[ 6 ] = MAPIL::CreateTexture( "material/Resource/menu2_rev.png" );
		p->m_MenuTexture[ 7 ] = MAPIL::CreateTexture( "material/Resource/menu3_rev.png" );
		p->m_MenuTexture[ 8 ] = MAPIL::CreateTexture( "material/Resource/menu4_rev.png" );
		p->m_MenuTexture[ 9 ] = MAPIL::CreateTexture( "material/Resource/menu5_rev.png" );
		// ���[�f�B���O��ʂ����[�h
		p->m_LoadingTexture = MAPIL::CreateTexture( "material/Resource/loading.png" );
		// �t�H���g�����[�h
		MAPIL::CreateSplitedTexture( p->m_GameFont, "material/Resource/game_font3.png", 5, 8, 32, 32 );
	}

	void MainFrame::ShowLoading()
	{
		static int cnt = 100;
		static bool rev = false;
		ResourceHandler* p = ResourceHandler::GetInst();
		MAPIL::BeginRendering2DGraphics();
		MAPIL::DrawTexture( p->m_TitleTexture, 300.0f, 180.0f, 0.0f );
		MAPIL::DrawTexture(	p->m_LoadingTexture, 500.0f, 420.0f, 0.0f,
							MAPIL::GetColorARGBValue( (unsigned char)cnt, (unsigned char)cnt, (unsigned char)cnt, (unsigned char)cnt ) );
		MAPIL::EndRendering2DGraphics();
		if( rev ){
			cnt -= 15;
		}
		else{
			cnt += 15;
		}
		if( cnt > 240 ){
			rev = !rev;
		}
		if( cnt < 100 ){
			rev = !rev;
		}

		p->m_pGBManager->Reset();
	}

	void MainFrame::Run()
	{
		RTG::ResourceHandler* p = RTG::ResourceHandler::GetInst();

		while( !MAPIL::ProcessMessage() ){
			if( m_FPS.DoesElapseNextTime() ){

				// �{�^���̏�Ԃ��擾
				p->m_pGBManager->Update();

				// �X�N���[���V���b�g
				if( p->m_pGBManager->IsPushedOnce( GENERAL_BUTTON_SS ) ){
					ScreenShot( "screencapture", "ScreenShot" );
				}

				// �I������
				if( p->m_pGBManager->IsPushedOnce( GENERAL_BUTTON_TERM ) ){
					break;
				}
				if( p->m_pGameManager->HasTermSig() ){
					break;
				}

				// �`��ݒ�
				MAPIL::BeginRendering();
				MAPIL::EnableBlending();
				MAPIL::DisableLighting();
				MAPIL::EnableZBuffering();
				MAPIL::SetTextureMode( MAPIL::TEXTURE_MODE_2D );
				MAPIL::SetCullingMode( MAPIL::CULL_MODE_DISABLED );
				MAPIL::SetAlphaBlendingMode( MAPIL::ALPHA_BLEND_MODE_SEMI_TRANSPARENT );
				//p->m_GraphicsCtrl->SetAlphaBlendMode( MAPIL::ALPHA_BLEND_MODE_ADD );
				MAPIL::SetViewPort( 0, 0, 640, 480 );

				// �V�[���J��
				if( m_pScene->HasNextScene() ){
					Scene* pDeleteScene = m_pScene;
					m_pScene = m_pScene->GetNextScene();
					MAPIL::SafeDelete( pDeleteScene );
					m_pScene->Init();
				}			
	
				// ���[�f�B���O���
				if( m_pScene->IsLoading() ){
					ShowLoading();
				}
				// �ʏ���
				else{
					m_pScene->Draw();
					m_pScene->Update();
				}

				static int cnt = 0;
				static double fps = 0.0;

				if( ( cnt % 60 ) == 0 ){
					fps = m_FPS.GetNowFPS();
				}

				MAPIL::BeginRendering2DGraphics();
				FontString s;
				s.Set( "FPS %.2f", fps );
				s.Draw( 500.0f, 460.0f, 0xFFFFFF00 );
				MAPIL::EndRendering2DGraphics();

				++cnt;

				MAPIL::EndRendering();
			}
			else{
				Sleep( 1 );
			}
		}
	}
}