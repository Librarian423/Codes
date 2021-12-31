 #include <stdio.h>
#include <Windows.h>
#include <conio.h>

#define DINOSAUR_DISTANCE_FROM_TOP_Y 12
#define SPACE_ASCII 32
#define TREE_DISTANCE_FROM_TOP_Y 20
#define TREE_DISTANCE_FROM_RIGHT_X 45


void InitConsoleConfig();
void DrawDino(int);
void GotoXY(int x, int y);
int GetKeyDown(void);
void DrawTree(int);
bool Collision(const int treex, const int dinoY);

int main(int argc, char* argv[])
{
	InitConsoleConfig();
	int dinosourY = DINOSAUR_DISTANCE_FROM_TOP_Y;
	int treeX = TREE_DISTANCE_FROM_RIGHT_X;
	static int gravity = 3;
	bool walking = true;
	bool jumping = false;
	int score = 0;
	int speed = 70;
	while (true)
	{

		if ( Collision(treeX, dinosourY) ) // 충돌 감지
		{
			break;
			
		}
		else// 시간이 지날 때 마다 점수 증가
		{
			score++;
			SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 0x0007);
			printf("Score: %d", score);
		}
		if (GetKeyDown() == 32 && walking)
		{
			jumping = true;
			walking = false;

		}
		if (jumping)
		{
			dinosourY -= 3;

		}

		else
		{
			dinosourY += 3;

		}

		if (dinosourY <= 3)

		{
			jumping = false;
		}
		if (DINOSAUR_DISTANCE_FROM_TOP_Y <= dinosourY) //Y값의 좌표가 12보다 커지면 Y 값의 좌표를 12로 고정
		{
			dinosourY = DINOSAUR_DISTANCE_FROM_TOP_Y;
			walking = true;
		}

		treeX -= 3;
		if (treeX <= 0){
			treeX = TREE_DISTANCE_FROM_RIGHT_X;
		}

		DrawDino(dinosourY);
		DrawTree(treeX);

		speed--; // 속도 증가
		if ( speed <= 30 )// 속도 증가 제한
		{
			speed = 40;
		}
		Sleep(speed);

		system("cls");
		
	}
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 0x0007);
	printf("Score:  %d\n", score);
	printf("Game Over\n");
	return 0;
}

void InitConsoleConfig() {
	
	system("mode con: cols=100 lines=25");
}

void GotoXY(int x, int y){
	COORD pos;
	pos.X = x;
	pos.Y = y;

	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), pos);

}

void DrawTree(int treex) {

	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 0x0002);


	GotoXY(treex, TREE_DISTANCE_FROM_TOP_Y + 1);
	printf("####");
	GotoXY(treex, TREE_DISTANCE_FROM_TOP_Y + 2);
	printf(" ## ");
	GotoXY(treex, TREE_DISTANCE_FROM_TOP_Y + 3);
	printf(" ## ");
	GotoXY(treex, TREE_DISTANCE_FROM_TOP_Y + 4);
	printf(" ## ");
}

bool Collision(const int treex, const int dinosaurY)
{
	GotoXY(0, 0);
	if ( treex <= 8 && treex >= 4 && dinosaurY > 8 )
	{
		return true;
	}
	return false;
}

int GetKeyDown(void){

	if (_kbhit() != 0)//keyboard hit
	{
		return _getch();

	}

	return 0;

}



void DrawDino(int dinosaurY)
{
	static bool legDraw = true;
	GotoXY(0, dinosaurY);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 0x001);
	printf("        &&&&&&& \n");
	printf("       && &&&&&&\n");
	printf("       &&&&&&&&&\n");
	printf("&      &&&     ========>> \n");
	printf("&&     &&&&&&&  \n");

	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 0x004);

	printf("&&&   &&&&&     \n");
	printf(" &&  &&&&&&&&&& \n");
	printf(" &&&&&&&&&&&    \n");
	printf("  &&&&&&&&&&    \n");
	printf("    &&&&&&&&    \n");
	printf("     &&&&&&     \n");

	if (legDraw)
	{
		printf("     &    &&&     \n");
		printf("     &&           ");
		legDraw = false;
	}
	else
	{
		printf("     &&&  &       \n");
		printf("          &&      ");
		legDraw = true;
	}

}
