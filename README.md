#### SpringBoot微信点餐系统 --慕课网

#### 日志的使用 
一个日志方案，通常是使用slf4j作为日志框架的接口，而日志实现则有多种：
* log4j：已经被作者抛弃，觉得其太烂，无法修改，直接重写出了LogBack
* log4j2:和log4j无关，只是Apache借了个名而已，性能最优秀，但是目前bug有点多
* logBack:log4j作者重写的日志框架，十分好;

SpringBoot默认采用slf4j+logBack

* 在SpringBoot中，只需要使用Logger logger = LoggerFactory.getLogger(Xxx.class)(sif4j包)即可获得日志对象;
    * yml文件中可以实现日志文件简单的配置：
        ~~~
            logging:
              pattern:
                #控制台日志输出格式
            #    console: "%d - %msg%n"
              #日志配置文件的路径
            #  config:
              #日志文件输出路径，只是路径名
            #  path: D://log
              #日志文件输出路径，包含文件名
            #  file: D://a.log
              #最低输出日志等级
            #  level:
                #如果下写法在level中可以配置某个类的最低输出日志等级
            #    com.zx.LoggerTest: debug
        ~~~
    * 也可以直接在resources文件夹下添加logback-spring.xml文件，配置日志

#### 实体类getter/setter/toString方法的省略
该框架并非程序运行时动态添加，而是打包时添加到class文件中的,所以不会对性能产生影响
* 在pom.xml中引入lombok jar(SpringBoot中似乎很多jar都可以不写version):
    ~~~
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
            </dependency>
    ~~~
* 然后在IDEA中安装插件lombok,Eclipse去lombok官网下载jar，安装到eclipse中即可
* 然后在实体类上写上注释
~~~
        @Data   ：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
        @Setter：注解在属性上；为属性提供 setting 方法
        @Getter：注解在属性上；为属性提供 getting 方法
        @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
        @@Slf4j:注解在类上，为类提供一个 属性名为log的slf4j日志对象
        @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
        @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法 
~~~

#### BeanUtils工具类的使用
* 可以将A实体类中所有和B实体类中属性名相同的属性的值复制到B，注意，Null值也会被复制过去
* 当含有util.Date属性时，尤其是util.Date为null时，会出错;其子类sql.util是可以被支持的

#### CollectionUtils类的使用
* 可以判断集合是否为空或者元素为0，以及是否包含某些元素等

#### timestamp格式在从数据库取出转为date后，是ms（毫秒）格式，而有些情况下需要返回的是s（秒），也就是需要除以1000

