file=open("P:\playground\javaplay\\bookmanage\\bookmanage\src\\testdata\\testData.csv",'w')
str1="id,name,usehour,typeid,price,havecnt,buycnt,comment,imagepath\n"
str2='1111'
file.write(str1)

for i in range(7):
    strName=str(i)+','+"name"+str(i)+","
    for j in range(6):
        strName+=str(1)+','
    strName+="null\n"    
    file.write(strName)

file.close()