#include<stdio.h>
#include<graphics.h>
#include<stdlib.h>
void junxian (int x,float K,float B);
struct GU//����Ľṹ��
{
    int data;
    int open;
    int high;
    int low;
    int close;
    int amount;
    int vol;
    int reservation;
};
struct GU gp[4000];//�ṹ������
int a,b,a1,b1,a2,b2;//a��b�洢��������ڣ�a1��b1�洢�������������Ӧ��ֵ��a2��b2��Ӧ��������Ƿ�����Ӧ���������ݵ�ֵ
char s1[10],s2[10];//�洢�ַ�����������ͼ�ο������
int main ()
{
    int j;
    float  maxrise=0,rise;
    FILE *fp;
    if((fp=fopen("sh000001.day","rb"))==NULL)
    {
        printf("���ܴ��ļ�!");
        exit(0);
    }
    int i=0;
    while(!feof(fp))//��ȡ����
    {
        fread(&gp[i],sizeof(GU),1,fp);
        i++;
    }//����۲��ȡ�������Ƿ���ȷ
  /*  for(i=0;i<=3000;i++)
     {   printf("%8d",gp[i].data);
         printf("%8d",gp[i].open);
         printf("%8d",gp[i].high);
         printf("%8d",gp[i].low);
         printf("%8d",gp[i].close);
         printf("%20d",gp[i].amount);
         printf("%8d\n",gp[i].vol);}*/
    printf("�������������䣨�м��ÿո�ָ����� ");
    scanf("%d %d",&a,&b);
    for(i=0; gp[i].data<=a; i++);//����䣬�ҵ���Ӧ�����iֵ
    a1=i;
    for(i=0; gp[i].data<b; i++);//����䣬�ҵ���Ӧ�����iֵ
    b1=i;
    //printf("a=%d b=%d\n",gp[a1].data,gp[b1].data);����ҵ��������Ƿ���ȷ
    for(i=a1; i<b1; i++)//������ֵ
        for(j=i; j<b1; j++)
        {
            rise=((float)(gp[j].close-gp[i].close))/gp[i].close;
            if(rise>maxrise)
            {
                a2=i,b2=j;
                maxrise=rise;
            }
        }
    printf("����Ƿ����������䣺%d %d\n",gp[a2].data,gp[b2].data);
    printf("��Ӧ���̼ۣ�%.2f %.2f\n",gp[a2].close/100.0,gp[b2].close/100.0);
    printf("�����Ƿ���%.2f%%",maxrise*100);
//��ʼ��ͼ
    int gdriver, gmode;
    gdriver=DETECT;
    initgraph(&gdriver, &gmode,"");
    setbkcolor(7);
    cleardevice();
    setlinestyle(0,0,2);
    setcolor(0);
    line(50,25,50,700);           //��������
    line(50,700,1400,700);
    line(50,25,40,35);
    line(50,25,60,35);
    setlinestyle(1,0,1);
    setcolor(8);
    int x;
    for(x=50; x<=650; x=x+50)          //������
        line(50,x,1400,x);
    setcolor(0);
    int h0,maxmoney=0,minmoney=10000000;
    for(i=a1; i<=b1; i++) //�ҳ����ļ�Ǯ����С�ļ�Ǯ����ȷ�����������K��B��������
    {
        if(gp[i].high>maxmoney)
            maxmoney=gp[i].high;
        if(gp[i].low<minmoney)
            minmoney=gp[i].low;
    }
    h0= (maxmoney-minmoney)/1200;
    for(i=650,j=minmoney/100; i>=50; i=i-50,j=j+h0) //����������ֵ
    {
        gcvt(j,4,s1);
        outtextxy(10,i,s1);
    }
    float K=-0.02*h0;
    float B=minmoney/100-650*K;
    setlinestyle(0,0,1);
    for(i=a1,j=55; i<=b1; i++,j=j+7)
    {
        if(gp[i].open>=gp[i].close)
        {
            setfillstyle(1,2);
            bar(j,(gp[i].open/100-B)/K,j+5,(gp[i].close/100-B)/K); //������
            setcolor(2);
            line(j+2,(gp[i].low/100-B)/K,j+2,(gp[i].high/100-B)/K);//����  ����ͬ��
        }
        else
        {
            setfillstyle(1,4);
            bar(j,(gp[i].close/100-B)/K,j+5,(gp[i].open/100-B)/K);
            setcolor(4);
            line(j+2,(gp[i].low/100-B)/K,j+2,(gp[i].high/100-B)/K);
        }
    }
    junxian (5,K,B);//������
    junxian (10,K,B);
    junxian (20,K,B);
    junxian (60,K,B);
    gcvt(gp[a2].close/100.0,6,s1);//������ת��Ϊ�ַ�������s1��s2��
    gcvt(gp[b2].close/100.0,6,s2);
    setcolor(BLUE);
    settextstyle(SMALL_FONT, HORIZ_DIR, 5);
    outtextxy(55+2+7*(a2-a1),(gp[a2].open/100-B)/K,s1);
    outtextxy(55+2+7*(b2-a1),(gp[b2].open/100-B)/K,s2);
    getch();
    closegraph();
    fclose(fp);
}
void junxian (int x,float K,float B)//�����ߵĺ���
{
    if(x==5)//���ƻ��ߵ���ɫ
        setcolor(15);//��ɫ
    else if(x==10)
        setcolor(14);//��ɫ
    else if(x==20)
        setcolor(5);//��ɫ
    else  if(x==60)
        setcolor(9);//��ɫ
    int s=0,c=0,A,i,j,average1,average2;
    A=a1;
    for(i=A; A<=x; A++)//��������������û�о��۵����
        c++;
    i=x;
    while(i--)//���ǰx����ֵ�ĺ�
    {
        s=s+gp[A].close;
        A--;
    }
    average1=s/x;//�����һ����ֵ
    for(i=1,j=55+2+7*c; a1+c+i<=b1; j=j+7,i++)//����ѭ�����ֵ���Լ�������
    {
        s=s+gp[a1+c+i].close-gp[a1+c+i-x].close;
        average2=s/x;
        line(j,(average1/100-B)/K,j+7,(average2/100-B)/K);
        average1=average2;
    }
}



