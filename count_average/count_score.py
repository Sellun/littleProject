import pdfplumber


def is_number(s):
    try:  # 如果能运行float(s)语句，返回True（字符串s是浮点数）
        float(s)
        return True
    except ValueError:  # ValueError为Python的一种标准异常，表示"传入无效的参数"
        pass  # 如果引发了ValueError这种异常，不做任何事情（pass：不做任何事情，一般用做占位语句）
    try:
        import unicodedata  # 处理ASCii码的包
        unicodedata.numeric(s)  # 把一个表示数字的字符串转换为浮点数返回的函数
        return True
    except (TypeError, ValueError):
        pass
    return False

def count_score(path):

    pdf = pdfplumber.open(path)
    dict = {"优": 90, "良": 80, "中": 70, "及格": 60, "不及格": 50}
    sum_score = 0
    sum_credit = 0
    c = 0
    for page in pdf.pages:
        

        for table in page.extract_tables():

            for row in table:
                if row[1] == "必修":
                    sum_credit = sum_credit + float(row[2])
                    if is_number(row[3]):
                        sum_score = sum_score + float(row[3]) * float(row[2])
                    else:
                        sum_score = sum_score + dict[row[3]] * float(row[2])

                if row[6] == "必修":
                    sum_credit = sum_credit + float(row[7])
                    if is_number(row[8]):
                        sum_score = sum_score + float(row[8]) * float(row[7])
                    else:
                        sum_score = sum_score + dict[row[8]] * float(row[7])
            #   print(row[1],row[2],row[3],row[6],row[7],row[8])
            print('---------- 分割线 ----------')
            print("score_sum: ",sum_score, "score_credit: ",sum_credit, "average_score: ",sum_score / sum_credit)

    pdf.close()

if  __name__ == '__main__':
    path = 'my_score.pdf'
    count_score(path)
