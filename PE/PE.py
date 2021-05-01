import requests
import time
import winsound
import win32gui
import win32con
url="http://202.197.224.51:9842/navcou/timrib/culot/timtab.do"
#url="http://202.197.224.51:9842/navcou/timrib/culot/pick.do"
headers={
'Accept':'application/json, text/javascript, */*; q=0.01',
'Accept-Encoding':'gzip, deflate',
'Accept-Language':'zh-CN,zh;q=0.9',
'Connection':'keep-alive',
'Content-Length':'26',
'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8',
'Cookie':'JSESSIONID=188AF42CAFA8134F758E4DD8006A1415',
'Host':'202.197.224.51:9842',
'Origin':'http://202.197.224.51:9842',
'Referer':'http://202.197.224.51:9842/navcou/timrib/culot/repert',
'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.72 Safari/537.36',
'X-Requested-With':'XMLHttpRequest',
}
# data={
# 'subo.mark':2792215091771308,
# 'subo.stave': 2793700841794767,
# }
data={
'subo.mark': 2792215091771295
}
res1=requests.post(url,headers=headers,data=data)
print(res1.json())
while True:
    res=requests.post(url,headers=headers,data=data)
    if res1.text!=res.text:
        winsound.Beep(600,1000)
        qq = win32gui.FindWindow(None, "谢艳艳")
        win32gui.SendMessage(qq, 258, 22, 2080193)
        win32gui.SendMessage(qq, 770, 0, 0)
        win32gui.SendMessage(qq, win32con.WM_KEYDOWN, win32con.VK_RETURN, 0)  
        win32gui.SendMessage(qq, win32con.WM_KEYUP, win32con.VK_RETURN, 0)
        break
    res1=res
    time.sleep(30)
print("ok")

