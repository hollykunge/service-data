# service-data为云雀系统数据管理系统
产生错误建议重新rebuild一下工程
##  离线文件生成借鉴于  
> swagger生成离线文件，转载于https://blog.csdn.net/Alexshi5/article/details/89606411  

##  获取swagger.json内容地址如下：    
> 获取swagger.json文件内容地址 http://ip:port/v2/api-docs  

> 在线访问swagger2页面 http://ip:port/swagger-ui.html  

## 创建如下的目录结构  
> 在target文件下创建红框中的文件结构目录，将http://ip:port/v2/api-docs内容复制到swagger.json文件中  
![image](https://github.com/hollykunge/service-data/blob/master/img/swagger-json.png)  

## 替换图片目录结构中的jar到本地的maven仓库  
  ![image](https://github.com/hollykunge/service-data/blob/master/img/maven-jar.png)
## 执行命令  
> 在service-task文件目录下，执行命令mvn test

