后台通知接口测试环境的相关参数：

1.后台请求地址

http://124.205.38.84:13135/lottomagic/jfhcp/doRequest

2.AES密钥

5118a4a8015118a4

3.数据包算子

前：4028d896

后：a8690000

测试环境：
点击发送验证码之后，输入111111，进行测试环境注册。
绑定身份证110105199312021302	苏三，省份选江苏，其他信息(银行卡等)随便填就可以。

mvn install:install-file -Dfile=vasoss-iwt-common-1.0-SNAPSHOT.jar -DgroupId=yt -DartifactId=vasoss-iwt-common -Dversion=1.0-SNAPSHOT -Dpackaging=jar -DgeneratePom=true