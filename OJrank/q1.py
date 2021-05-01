import requests
from bs4 import BeautifulSoup
import sys
import time

def isint (a):
    try:
        if int(a)<7 and int(a)>0 :
            return 1
        else:
            return 0
    except:
        return 0
def isnum (a):
    try:
        if int(a)<18 and int(a)>0 :
            return 1
        else:
            return 0
    except:
        return 0
def isy (a):
    if a=='y'or a=='n' or  a=='Y' or  a=='N':
        return 1
    else:
        return 0
num =10
a=input("1.计科1班      2.计科2班      3.网工1班      4.网工2班     5.软工1班     6.软工2班\n请输入你要查询的班级所对应的数字后回车：")
while(isint(a)==0):
   a=input("输入错误，请重新输入：")
a=int(a)
b=input("请输入要查询第几次作业（1~17）：")
while(isnum(b)==0):
   b=input("输入错误，请重新输入：")
num=int(b)
c=input("是否输出总题数统计（y/n):")
while(isy(c)==0):
    c=input("输入有误，请输入y/n/Y/N:")
if a==1:
    nummin = 202005565400
    nummax = 202005565440
if a==2:
    nummin = 202005565500
    nummax = 202005565540
if a==3:
    nummin = 202005565200
    nummax = 202005565240
if a==4:
    nummin = 202005565300
    nummax = 202005565340
if a==5:
    nummin = 202005565600
    nummax = 202005565640
if a==6:
    nummin = 202005565700
    nummax = 202005565740
try:
    r = requests.get("http://202.197.224.59/exam/index.php/solution/stat/cids/300,301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316",timeout=3)
    r.encoding = r.apparent_encoding
    if r.status_code==200:
        print("访问成功")
except:
    h=input("请检查是否连接校园网,程序结束请重新运行(按任意键回车后关闭窗口）")
    sys.exit(0)

soup = BeautifulSoup(r.text,"html.parser")
data=soup.select("#standing")
s=str(data)
newsoup=BeautifulSoup(s,"html.parser")
p=0
for tr in newsoup.table.children:
    p=p+1
    if(p==2 or p==3 or p==1):
        continue
    else:
        tr1=str(tr)
        trsoup=BeautifulSoup(tr1,"html.parser")
        count=0
        try:
            for child in trsoup.tr.children:
                count = count + 1
                if (count == 2):
                    try:
                        q = int(child.string)
                        if (q >= nummin and q <= nummax):
                            pass
                        else:
                            break
                    except ValueError:
                        break

                if (count == 3):
                    if len(child.string)==2:
                        print(child.string,end="       ")
                    if len(child.string) == 3:
                        print(child.string, end="     ")
                    if len(child.string) == 4:
                        print(child.string, end="   ")
                if (count == 5 and c=='y' or c=='Y'):
                    print("{:5}".format(child.string,chr(12288)),end="  ")
                    continue
                if (count == 5 + num):
                    print("{:7}".format(child.string,chr(12288)))
                    continue
        except:
            break
print("此次程序运行截止时间为：",end="")
print( time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
h = input("按任意键回车后关闭窗口")




