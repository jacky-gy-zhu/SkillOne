# SpringBoot
## resources文件夹中的目录结构
### static：
>保存所有的静态资源。（js、css、images）
### templates：
>保存所有的模版页面。（freemarker、thymeleaf）
### application.properties / application.yml：
>SpringBoot应用的配置文件。
## yaml语法
### k:(空格)v：表示一对键值对（空格必须有）。
>以空格的锁进来控制层级关系；只要是左对齐的一列数据，都是同一层级。
```yaml
server:
    port: 8081
    path: /hello
```
>属性和值也是大小写敏感的。
### 值的写法
>字面量：普通的值（数字、字符串、布尔）
>>字符串不用加上引号
>>""：双引号；不会转义字符串里面的特殊字符；特殊字符会作为本身想表示的意思
```yaml
name: "zhangsan\nlisi"
```
>>''：单引号；会转义字符串里面的特殊字符；
```yaml
name: 'zhangsan\nlisi'
```
>对象、Map（属性和值）（键值对）
>数组（List、Set）